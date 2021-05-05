package com.wblog.info.api;

import com.apes.hub.api.module.info.vo.ClassifyVo;
import com.apes.hub.api.uitils.RespEntity;
import com.apes.hub.core.constant.Version;
import com.apes.hub.core.datascope.annotation.GlobalDataScope;
import com.apes.hub.info.service.IClassifyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2020/10/13
 * <p>分类</p>
 **/
@RestController
@Api(value = "分类")
@RequestMapping(Version.VERSION_1 + "classify")
public class ClassifyApiController {

    @Autowired
    private IClassifyService classifyService;

    @GlobalDataScope
    @GetMapping("list")
    public RespEntity<List<Map<String, String>>> classifys() {

        List<Map<String, String>> res = new ArrayList<>();
        List<ClassifyVo> list =
                classifyService.findList(null);
        for (ClassifyVo classifyVo : list) {
            Map<String, String> map = new HashMap<>();
            map.put("value", classifyVo.getId() + "");
            map.put("label", classifyVo.getName());
            res.add(map);
        }
        return RespEntity.success(res);
    }

}
