package com.wblog.info.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.enums.FilePathEnum;
import com.wblog.common.exception.BusinessException;
import com.wblog.common.module.info.vo.*;
import com.wblog.common.module.system.api.SysUserApi;
import com.wblog.common.module.system.vo.SysUserVo;
import com.wblog.info.component.FileTemplatePlus;
import com.wblog.info.config.BlogConfigProperties;
import com.wblog.info.entity.GitSynDataEntity;
import com.wblog.info.manage.IGitSynDataManage;
import com.wblog.info.mq.service.IGitSynDataMqService;
import com.wblog.info.service.*;
import com.wblog.info.utils.JgitUtils;
import com.wblog.info.utils.charset.CharsetUtils;
import io.github.fallingsoulm.easy.archetype.data.file.FileTemplate;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.PageInfoContentHandler;
import io.github.fallingsoulm.easy.archetype.data.redis.RedisKeyGenerator;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 * git?????????????????? serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-07
 */
@Service
@Slf4j
public class GitSynDataServiceImpl implements IGitSynDataService {


    @Autowired
    private IGitSynDataManage iGitSynDataManage;


    @Autowired
    private MybatisPlusUtils plusUtils;

    @Autowired
    private BlogConfigProperties blogConfigProperties;

    @Autowired
    private IGitSynService gitSynService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IClassifyService classifyService;

    @Autowired
    private IGitSynHistoryService gitSynHistoryService;
    @Autowired
    private SysUserApi userApi;

    @Autowired
    private RedisKeyGenerator redisKeyGenerator;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IGitSynDataMqService gitSynDataMqService;

    @Autowired
    private FileTemplatePlus fileTemplatePlus;

    @Override
    public PageInfo<GitSynDataVo> findByPage(PageRequestParams<GitSynDataVo> pageRequestParams) {
        PageRequestParams<GitSynDataEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, GitSynDataEntity.class);
        PageInfo<GitSynDataEntity> entityPageInfo = iGitSynDataManage.findByPage(params,
                null == pageRequestParams.getParams() ? null : pageRequestParams.getParams().getStatusList());
        return plusUtils.convertPageInfo(entityPageInfo, GitSynDataVo.class, contentList -> {
            List<Long> userIds = contentList
                    .stream()
                    .filter(a -> null != a.getUserId())
                    .map(GitSynDataVo::getUserId)
                    .distinct()
                    .collect(Collectors.toList());
            List<SysUserVo> data = userApi.findByIds(userIds).getData();
            for (GitSynDataVo gitSynDataVo : contentList) {
                // ??????????????????/??????/???????????????
                Integer enableCount = articleService.count(ArticleVo.builder().gitId(gitSynDataVo.getId())
                        .status(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue()).build());
                Integer stopCount = articleService.count(ArticleVo.builder().gitId(gitSynDataVo.getId())
                        .status(ConstantEnum.ARTICLE_STATUS_STOP.getValue()).build());
                Integer discardNum = articleService.count(ArticleVo.builder().gitId(gitSynDataVo.getId())
                        .status(ConstantEnum.ARTICLE_STATUS_DISCARD.getValue()).build());

                gitSynDataVo.setEnableNum(enableCount);
                gitSynDataVo.setStopNum(stopCount);
                gitSynDataVo.setDiscardNum(discardNum);
                for (SysUserVo userVo : data) {
                    if (null != gitSynDataVo.getUserId() && gitSynDataVo.getUserId().equals(userVo.getUserId())) {
                        gitSynDataVo.setUserName(userVo.getUserName());
                    }
                }
            }
        });
    }

    @Override
    public GitSynDataVo findById(Long id) {
        GitSynDataEntity gitSynDataEntity = iGitSynDataManage.findById(id);
        if (gitSynDataEntity == null) {
            return null;
        }

        GitSynDataVo gitSynDataVo = BeanUtils.copyProperties(gitSynDataEntity, GitSynDataVo.class);
        if (null != gitSynDataEntity.getUserId()) {
            gitSynDataVo.setUserName(userApi.findById(gitSynDataEntity.getUserId()).getData().getUserName());
        }
        return gitSynDataVo;
    }

    @Override
    public List<GitSynDataVo> findList(GitSynDataVo gitSynDataVo) {
        GitSynDataEntity GitSynDataEntity = BeanUtils.copyProperties(gitSynDataVo, GitSynDataEntity.class);
        List<GitSynDataEntity> list = iGitSynDataManage.list(GitSynDataEntity);
        return BeanUtils.copyList(list, GitSynDataVo.class);
    }

    @Override
    public List<GitSynDataVo> findByIds(List<Long> ids) {
        List<GitSynDataEntity> entities = iGitSynDataManage.findByIds(ids);
        return BeanUtils.copyList(entities, GitSynDataVo.class);
    }

    @Override
    public Long save(GitSynDataVo gitSynDataVo) {
        GitSynDataEntity gitSynDataEntity = doGitSynDataEntitySave(gitSynDataVo, true);

        return gitSynDataEntity.getId();
    }

    private GitSynDataEntity doGitSynDataEntitySave(GitSynDataVo gitSynDataVo, boolean thre) {
        GitSynDataEntity one = this.iGitSynDataManage.findOne(GitSynDataEntity.builder().gitUrl(gitSynDataVo.getGitUrl()).build());
        if (null != one) {
            if (thre) {
                throw new BusinessException(gitSynDataVo.getGitUrl() + ":????????????,???????????????");
            } else {
                return null;
            }
        }
        GitSynDataEntity gitSynDataEntity = BeanUtils.copyProperties(gitSynDataVo, GitSynDataEntity.class);


        if (StrUtil.isBlank(gitSynDataVo.getProjectName())) {
            String projectName = getProjectName(gitSynDataEntity.getGitUrl());
            gitSynDataEntity.setProjectName(projectName);
        }
        if (StrUtil.isBlank(gitSynDataEntity.getOwner())) {
            String userName = getUserName(gitSynDataEntity.getGitUrl());
            gitSynDataEntity.setOwner(userName);
        }
        if (StrUtil.isBlank(gitSynDataEntity.getBranch())) {
            gitSynDataEntity.setBranch("master");
        }
//        gitSynDataEntity.setBasePath(projectName + "##" + userName);
        gitSynDataEntity.setSource(setSource(gitSynDataEntity.getGitUrl()));
        iGitSynDataManage.insert(gitSynDataEntity);
        return gitSynDataEntity;
    }

    private Integer setSource(String gitUrl) {
        if (gitUrl.contains("gitee.com")) {
            return ConstantEnum.GIT_SYN_DATA_SOURCE_GITEE.getValue();
        } else if (gitUrl.contains("github.com")) {
            return ConstantEnum.GIT_SYN_DATA_SOURCE_GITHUB.getValue();
        } else {
            return ConstantEnum.GIT_SYN_DATA_SOURCE_UNKNOWN.getValue();
        }
    }

    @Override
    public void update(GitSynDataVo gitSynDataVo) {
        GitSynDataEntity gitSynDataEntity = BeanUtils.copyProperties(gitSynDataVo, GitSynDataEntity.class);
        iGitSynDataManage.update(gitSynDataEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iGitSynDataManage.deleteBatch(ids);
    }

    @Override
    public void deleteById(Long id) {
        iGitSynDataManage.deleteById(GitSynDataEntity.builder().id(id).build());
    }

//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public Long createUser(Long id) {
//        GitSynDataEntity gitSynDataEntity = iGitSynDataManage.findById(id);
//        if (null == gitSynDataEntity) {
//            throw new CustomException("?????????");
//        }
//        String userName = getUserName(gitSynDataEntity.getGitUrl());
//        String email = userName + "@" + URLUtil.getHost(URLUtil.url(gitSynDataEntity.getGitUrl())).getHost();
//        SysUserVo userVo = SysUserVo.builder().email(email).userName(userName).password(userName).sex("1")
//                .userType(ConstantEnum.USER_TYPE_REGISTER.getValue().toString()).build();
//
//
//        RespEntity<Long> respEntity = userApi.save(userVo);
//        Long userId = respEntity.getData();
//
//        this.iGitSynDataManage.update(GitSynDataEntity.builder().id(id).userId(userId).build());
//        return id;
//    }


    private static String getUserName(String str) {
        str = str.substring(str.indexOf(".com") + 5, str.lastIndexOf("/"));
        str = str.replace("/", "#");
        return str;
    }

    /**
     * <p>???????????????</p>
     *
     * @param str
     * @return {@link String}
     * @author luyanan
     * @since 2020/6/9
     */
    private static String getProjectName(String str) {
        str = str.substring(str.lastIndexOf("/")).substring(1);
        str = str.substring(0, str.lastIndexOf(".git"));
        return str;
    }

    @Override
    public void syn(Long id) {
//        GitSynDataVo dataVo = this.findById(id);
        gitSynDataMqService.syn(id);
    }

    @Override
    public void synAll() {
        log.info("git??????????????????");
        // 1. ??????????????????????????????
        List<GitSynDataEntity> list = this.iGitSynDataManage.list(GitSynDataEntity
                .builder()
                .status(ConstantEnum.GIT_SYN_DATA_STATUS_ENABLE.getValue())
                .build());
        log.info("??????????????????????????????:{}", list.size());
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        for (GitSynDataEntity gitSynDataEntity : list) {
            // ??????????????????
            gitSynDataMqService.syn(gitSynDataEntity.getId());
        }
        log.info("git??????????????????");
    }


    @Override
    public void getGitUrlFromGitee() {
// 4fb6aa09ddb7e7a00e07ceaf2bc57575
//????????????https://gitee.com/api/v5/swagger#/getV5SearchRepositories
        String word = "??????";
        Integer totaoPage = getGiteeSearchRepositoriesResult(1, blogConfigProperties.getGiteeAccessToken(), word);
        if (null != totaoPage) {
            for (Integer i = 2; i < totaoPage + 1; i++) {
                try {
                    getGiteeSearchRepositoriesResult(i, blogConfigProperties.getGiteeAccessToken(), word);
                    // ?????????????????????,??????????????????
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * <p>??????gitee?????????????????????</p>
     *
     * @author luyanan
     * @since 2020/6/22
     */
    private Integer getGiteeSearchRepositoriesResult(Integer page, String accessToken, String word) {

        String url = "https://gitee.com/api/v5/search/repositories";
        Map<String, Object> params = new HashMap<>(6);
        params.put("access_token", accessToken);
        // ???????????????
        params.put("q", word);
        params.put("page", page);
        params.put("per_page", 20);
        params.put("sort", "stars_count");
        params.put("order", "desc");
//        String object = HttpUtil.get("https://gitee.com/api/v5/search/repositories", params);
        HttpResponse execute = HttpUtil.createGet(url).form(params).execute();
        String total_page = execute.header("total_page");
//        System.out.println(object);
        System.out.println("total_page" + total_page);
        List<GitSynDataVo> gitSynDataVos = giteeSearchRepositoriesParser(execute.body());
        if (CollectionUtil.isNotEmpty(gitSynDataVos)) {
            // ????????????
            this.saveBatch(gitSynDataVos);
        }

        return Integer.valueOf(total_page);
    }


    /**
     * <p>gitee ????????????????????????</p>
     *
     * @param result
     * @return {@link GitSynDataVo}
     * @author luyanan
     * @since 2020/6/22
     */
    public List<GitSynDataVo> giteeSearchRepositoriesParser(String result) {


        List<GitSynDataVo> gitSynDataVos = new ArrayList<>();
        if (StrUtil.isBlank(result)) {
            return null;
        }

        JSONArray objects = JSON.parseArray(result);
        if (null == objects || objects.size() == 0) {
            return null;
        }
        for (int i = 0; i < objects.size(); i++) {
            JSONObject jsonObject = objects.getJSONObject(i);
            String html_url = jsonObject.getString("html_url");
            GitSynDataVo gitSynDataVo = GitSynDataVo
                    .builder()
                    .gitUrl(html_url)
                    .projectName(jsonObject.getString("path"))
                    .owner(jsonObject.getString("project_creator"))
                    .description(jsonObject.getString("description"))
                    .status(ConstantEnum.GIT_SYN_DATA_STATUS_CHECKING.getValue())
                    .build();
            gitSynDataVos.add(gitSynDataVo);
        }
        return gitSynDataVos;
    }

    @Override
    public void getGitUrlFromGithub() {
        // https://developer.github.com/v3/search/#constructing-a-search-query
        // ?????????, The Search API has a custom rate limit. For requests using Basic Authentication, OAuth, or client ID and secret, you can make up to 30 requests per minute. For unauthenticated requests, the rate limit allows you to make up to 10 requests per minute.
        // ???????????????  1000??? To satisfy that need, the GitHub Search API provides up to 1,000 results for each search.
        String word = "??????";
        String sort = "stars|forks|updated";
        String url = "https://api.github.com/search/repositories?q=" + word + "&order=desc";
        for (String s : sort.split("\\|")) {
            for (int i = 1; i < 35; i++) {
                String githubUrl = url + "&page=" + i + "&sort=" + s;
                System.out.println(githubUrl);
                String result = HttpUtil
                        .createGet(githubUrl)
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
                        .header("Host", "api.github.com")
                        .header("Accept", "application/vnd.github.v3+json")
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Accept-Language", "zh-CN,zh;q=0.9")
                        .header("Cache-Control", "max-age=0")
                        .header("Connection", "keep-alive")
                        .header("Sec-Fetch-Mode", "navigate")
                        .header("Sec-Fetch-Site", "cross-site")
                        .header("Upgrade-Insecure-Requests", "1")
                        .setConnectionTimeout(500000)
                        .setReadTimeout(500000)
                        .setEncodeUrlParams(true)
                        .setFollowRedirects(true)
                        .timeout(500000)
                        .execute()
                        .body();
                List<GitSynDataVo> gitSynDataVos = githubSearchRepositoriesParser(result);
                this.saveBatch(gitSynDataVos);
                // ????????????, ????????????sleep
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * <p>github ????????????</p>
     *
     * @param result
     * @return {@link List< GitSynDataVo>}
     * @author luyanan
     * @since 2020/6/22
     */
    private List<GitSynDataVo> githubSearchRepositoriesParser(String result) {
        List<GitSynDataVo> gitSynDataVos = new ArrayList<>();
        if (StrUtil.isBlank(result)) {
            return gitSynDataVos;
        }
        JSONArray items = JSON.parseObject(result).getJSONArray("items");
        if (items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                JSONObject item = items.getJSONObject(i);
                GitSynDataVo gitSynDataVo = GitSynDataVo
                        .builder()
                        .gitUrl(item.getString("clone_url"))
                        .description(item.getString("description"))
                        .projectName(item.getString("name"))
                        .owner(item.getJSONObject("owner").getString("login"))
                        .status(ConstantEnum.GIT_SYN_DATA_STATUS_CHECKING.getValue())
                        .build();
                gitSynDataVos.add(gitSynDataVo);
            }
        }
        return gitSynDataVos;
    }

    @Override
    public void saveBatch(List<GitSynDataVo> gitSynDataVos) {


        if (CollectionUtil.isEmpty(gitSynDataVos)) {
            return;
        }
        for (GitSynDataVo gitSynDataVo : gitSynDataVos) {
            doGitSynDataEntitySave(gitSynDataVo, false);
        }
    }

    @Override
    public void doGitSynData(Long id) {
        GitSynDataVo gitSynDataVo = this.findById(id);
        log.info("????????????:" + gitSynDataVo.getGitUrl());
        String gitSynPath = blogConfigProperties.getGitSynPath();
        try {
            System.gc();
            // ??????????????????????????????????????????
            FileUtil.clean(gitSynPath);
            String localPath = gitSynPath + gitSynDataVo.getId() + gitSynDataVo.getProjectName();
            // 1. ????????????????????????
            doCloneOrPullProject(gitSynDataVo, localPath);
            // 2. ????????????
            List<GitSynVo> newGitSynVos = doReadFile(gitSynDataVo, localPath);
            if (CollectionUtil.isEmpty(newGitSynVos)) {
                return;
            }
            // 3. ??????????????????????????????,?????????????????????/??????/????????????
            // ??????gitId ?????????????????????
            List<GitSynVo> gitSynVoList = gitSynService.findList(GitSynVo.builder().gitId(gitSynDataVo.getId()).build());
            // ??????????????????
            int saveSize = saveHandler(newGitSynVos, gitSynVoList, gitSynDataVo);
            // ??????????????????
            int updateSize = updateHandler(newGitSynVos, gitSynVoList, gitSynDataVo);
            // ??????????????????
            int deleteSize = deleteHandler(newGitSynVos, gitSynVoList, gitSynDataVo);

            // 4. ??????????????????
            gitSynHistoryService.save(GitSynHistoryVo.builder()
                    .gitId(gitSynDataVo.getId())
                    .addNum(saveSize)
                    .updateNum(updateSize)
                    .deleteNum(deleteSize)
                    .createTime(new Date())
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileUtil.clean(gitSynPath);

    }

    /**
     * <p>??????????????????</p>
     *
     * @param newGitSynVos ?????????????????????
     * @param gitSynVoList ????????????
     * @author luyanan
     * @since 2020/7/1
     */
    private int deleteHandler(List<GitSynVo> newGitSynVos, List<GitSynVo> gitSynVoList, GitSynDataVo gitSynDataVo) {
        // ?????????  ??????????????????????????????,???????????????
        List<GitSynVo> deleteVos = gitSynVoList
                .stream()
                .filter(gitSynVo -> !newGitSynVos
                        .stream()
                        .map(GitSynVo::getPath)
                        .collect(Collectors.toList())
                        .contains(gitSynVo.getPath()))
                .collect(Collectors.toList());
        log.info("gitUrl:{},????????????????????????:{}", gitSynDataVo.getGitUrl(), deleteVos.size());
        // ?????????
        List<Long> deleteArticleId = deleteVos.stream().map(GitSynVo::getArticleId).distinct().collect(Collectors.toList());
        articleService.deleteByIds(deleteArticleId);
        if (!deleteVos.isEmpty()) {
            gitSynService.deleteByIds(deleteVos.stream().map(GitSynVo::getId).distinct().collect(Collectors.toList()));
        }
        return deleteVos.size();
    }

    /**
     * <p>??????????????????</p>
     *
     * @param newGitSynVos ?????????????????????
     * @param gitSynVoList ????????????
     * @author luyanan
     * @since 2020/7/1
     */
    private int updateHandler(List<GitSynVo> newGitSynVos, List<GitSynVo> gitSynVoList, GitSynDataVo gitSynDataVo) {
        // ?????????  ??????????????????, md5???????????????????????????
        List<GitSynVo> updateVos = gitSynVoList.stream().filter(gitSynVo -> {
            for (GitSynVo newGitSynVo : newGitSynVos) {
                if (gitSynVo.getPath().equals(newGitSynVo.getPath()) && !gitSynVo.getMd5().equals(newGitSynVo.getMd5())) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        log.info("gitUrl:{},????????????????????????:{}", gitSynDataVo.getGitUrl(), updateVos.size());

        // ??????
        for (GitSynVo up : updateVos) {
//            File file = new File(blogConfigProperties.getGitSynPath() + up.getPath());
//            String content = FileUtil.readString(file, "utf-8");
            articleService.update(ArticleVo.builder().id(up.getArticleId()).content(up.getContent()).build());
        }

        if (!updateVos.isEmpty()) {
            for (GitSynVo updateVo : updateVos) {
                gitSynService.update(updateVo);
            }
        }
        return updateVos.size();
    }

    /**
     * <p>??????????????????</p>
     *
     * @param newGitSynVos ?????????????????????
     * @param gitSynVoList ????????????
     * @author luyanan
     * @since 2020/7/1
     */
    private int saveHandler(List<GitSynVo> newGitSynVos, List<GitSynVo> gitSynVoList, GitSynDataVo gitSynDataVo) {
        // ?????????  ?????? ???, ????????????????????????????????????,
        List<GitSynVo> saveVos = newGitSynVos
                .stream()
                .filter(newGitSynVo -> !gitSynVoList.stream()
                        .map(GitSynVo::getPath)
                        .collect(Collectors.toList())
                        .contains(newGitSynVo.getPath()))
                .collect(Collectors.toList());

        log.info("gitUrl:{},????????????????????????:{}", gitSynDataVo.getGitUrl(), saveVos.size());
        // ??????

        for (GitSynVo a : saveVos) {
            try {
                log.info("gitUrl:{},????????????????????????:{}", gitSynDataVo.getGitUrl(), a.getPath());
                File file = new File(blogConfigProperties.getGitSynPath() + "/" + a.getPath());
                String[] classifyNames = file.getAbsolutePath()
                        .replace(new File(blogConfigProperties.getGitSynPath())
                                .getAbsolutePath(), "")
                        .replace(gitSynDataVo.getProjectName(), "")
                        .replace(file.getName(), "").split("\\\\");
                List<String> collect = Arrays.stream(classifyNames).filter(aa -> StrUtil.isNotBlank(aa)).collect(Collectors.toList());
                Collections.reverse(collect);
                String classifyName = collect.stream().filter(aa -> StrUtil.isNotBlank(aa)).findFirst().orElse("??????");
                String title = file.getName().replace(".md", "");

                // ????????????????????????

                String content = contentLocalImageReplace(file.getAbsolutePath());
                Long classifyId = classifyService.save(ClassifyVo.builder().userId(gitSynDataVo.getUserId()).createBy(gitSynDataVo.getUserId()).name(classifyName).build());
                ArticleVo articleVo = ArticleVo
                        .builder()
                        .title(title)
                        .classifyId(classifyId)
                        .source(ConstantEnum.ARTICLE_SOURCE_ORIGINAL.getValue())
                        .view(0L)
                        .content(content)
                        .gitId(gitSynDataVo.getId())
                        .userId(gitSynDataVo.getUserId())
                        .createBy(gitSynDataVo.getUserId())
                        .status(ConstantEnum.ARTICLE_STATUS_CHECKING.getValue())
                        .build();
                log.info("?????????????????????:{}", JSON.toJSONString(articleVo));
                if (StrUtil.isBlank(articleVo.getContent())) {
                    continue;
                }
                Long articleId = articleService.save(articleVo);
                a.setArticleId(articleId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // ?????????????????????
        if (!saveVos.isEmpty()) {
            gitSynService.saveBatch(saveVos);
        }
        return saveVos.size();
    }

    private Pattern imagePattern = Pattern.compile("!\\[.+?\\]\\((.+?)\\)");

    /**
     * @param filePath ????????????
     * @return {@link String}
     * @author luyanan
     * @since 2020/11/28
     */
    public String contentLocalImageReplace(String filePath) {
        log.info("???????????????:" + filePath);
        // ????????????????????????
        if (!FileUtil.exist(filePath)) {
            return null;
        }
        File file = new File(filePath);

        String content = FileUtil.readString(file, "utf-8");
        Matcher matcher = imagePattern.matcher(content);
        while (matcher.find()) {

            String group = matcher.group();
            String imagePath = ReUtil.get("\\((.+?)\\)", group, 0);

            imagePath = imagePath.trim().substring(1, imagePath.length() - 1);
            // ??????????????????
            if (imagePath.startsWith("http")) {
                continue;
            }
            String imageFile = file.getParent() + "/" + imagePath;

            try {
                File file1 = new File(imageFile);
                String upload = fileTemplatePlus.upload(FilePathEnum.BLOG_INFO.getPath(), file1.getName(),
                        FileUtil.getInputStream(file1));
                if (StrUtil.isNotBlank(upload)) {
                    content = content.replace(imagePath, upload);
                }
            } catch (IORuntimeException e) {
                log.error("?????????????????????{}", imageFile);
            }
        }
        return content;
    }

    public static void main(String[] args) {

        String filePath = "D:/data/git/14MyNote/????????????java?????????/????????????java?????????.md";
        GitSynDataServiceImpl gitSynService = new GitSynDataServiceImpl();
        gitSynService.contentLocalImageReplace(filePath);
    }

    /**
     * <p>???????????????????????? GitSynVo</p>
     *
     * @param gitSynDataVo
     * @param localPath
     * @return {@link List< GitSynVo>}
     * @author luyanan
     * @since 2020/7/1
     */
    private List<GitSynVo> doReadFile(GitSynDataVo gitSynDataVo, String localPath) {
        log.debug("????????????:{},???????????????:{}", gitSynDataVo, localPath);
        String filePath = localPath + (StrUtil.isBlank(gitSynDataVo.getBasePath()) ? "" : gitSynDataVo.getBasePath());
        List<File> fileList = FileUtil.loopFiles(new File(filePath), new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".md");
            }
        });
        if (fileList.isEmpty()) {
            return null;
        }
        log.info("??????:{},???????????????????????????:{}", localPath, fileList.size());

        List<GitSynVo> newGitSynVos = fileList.stream().map(f -> {
            try {
                //md5,????????????
                // ????????????????????????????????????
//                Charset charset = CharsetUtils.getCharset(f);
                String javaEncode = null;
                try {
                    javaEncode = CharsetUtils.getJavaEncode(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                if (null == charset) {
//                    charset = Charset.forName("utf-8");
//                }
                if (StrUtil.isBlank(javaEncode)) {
                    javaEncode = "utf-8";
                }
                String content = FileUtil.readString(f, javaEncode);
                if (StrUtil.isBlank(content)) {
                    return null;
                }
                String contentMd5 = SecureUtil.md5(content);
                content = null;
                return GitSynVo
                        .builder()
                        .gitId(gitSynDataVo.getId())
                        .path(getFileRelativePath(blogConfigProperties.getGitSynPath(), f).replace("\\", "/"))
                        .md5(contentMd5)
                        // ????????????
                        .charset(javaEncode)
//                        .content(content)
                        .build();

            } catch (IORuntimeException e) {
                e.printStackTrace();
            }
            return null;
        }).filter(a -> null != a)
                .collect(Collectors.toList());

        log.info("??????:{},?????????????????????:{}", localPath, newGitSynVos.size());
        return newGitSynVos;
    }

    /**
     * <p>????????????????????????</p>
     *
     * @param gitSynDataVo
     * @param localPath
     * @author luyanan
     * @since 2020/7/1
     */
    private void doCloneOrPullProject(GitSynDataVo gitSynDataVo, String localPath) throws Exception {
        // 1. ??????git???????????????????????????
        log.debug("??????/????????????:{},?????????:{}", gitSynDataVo.getGitUrl(), localPath);
        JgitUtils.gitCloneOrPull("", "", gitSynDataVo.getGitUrl(), gitSynDataVo.getBranch(), localPath);
    }

    @Override
    public void updateArticleStatus(GitSynDataVo gitSynDataVo) {

        articleService.update(gitSynDataVo.getId(), gitSynDataVo.getStatus());
    }

    @Override
    public void updateStatus(Long[] ids, Integer status) {
        //?????????????????????,????????????????????????id, ?????????????????????id,?????????????????????
        // ?????????????????????,??????????????????

        List<GitSynDataEntity> gitSynDataEntityList = Arrays
                .stream(ids)
                .distinct()
                .map(a -> GitSynDataEntity
                        .builder()
                        .id(a)
                        .status(status)
                        .createTime(new Date())
                        .build())
                .collect(Collectors.toList());


        if (ConstantEnum.GIT_SYN_DATA_STATUS_ENABLE.compareStatus(status)) {
            for (GitSynDataEntity gitSynDataEntity : gitSynDataEntityList) {
                GitSynDataEntity entity = this.iGitSynDataManage.findById(gitSynDataEntity.getId());
                if (null == entity.getUserId()) {
                    // ????????????
                    String userName = getUserName(entity.getGitUrl());
                    String email = userName + "@" + URLUtil.getHost(URLUtil.url(entity.getGitUrl())).getHost();
                    SysUserVo userVo = SysUserVo
                            .builder()
                            .email(email)
                            .nickName(userName)
                            .userName(userName)
                            .password(userName)
                            .sex("1")
                            .userType(ConstantEnum.USER_TYPE_REGISTER.getValue().toString())
                            .build();


                    RespEntity respEntity = userApi.save(userVo);
                    Object userId = respEntity.getData();
                    gitSynDataEntity.setUserId(Long.valueOf(userId.toString()));
                }
            }

        }

        this.iGitSynDataManage.updateBatch(gitSynDataEntityList);
        //  ?????????????????????,??????????????????MQ?????????????????????
        if (status.equals(ConstantEnum.GIT_SYN_DATA_STATUS_ENABLE.getValue())) {
            for (GitSynDataEntity gitSynDataEntity : gitSynDataEntityList) {
                gitSynDataMqService.syn(gitSynDataEntity.getId());
            }
        }

    }

    /**
     * <p>???????????????????????????</p>
     *
     * @param rootPath root??????
     * @param file     ??????
     * @return {@link String}
     * @author luyanan
     * @since 2020/6/12
     */
    private static String getFileRelativePath(String rootPath, File file) {
        String str = file.getParent().replace(rootPath.replace("/", "\\"), "") + "\\" + file.getName();
        return str.replace("\\", "/");
    }


}
