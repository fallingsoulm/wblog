package com.wblog.web.event;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.wblog.common.exception.BusinessException;
import com.wblog.common.module.notice.api.NoticeMessageApi;
import com.wblog.info.config.BlogConfigProperties;
import io.github.fallingsoulm.easy.archetype.job.entity.JobVo;
import io.github.fallingsoulm.easy.archetype.job.invoke.JobMonitorEvent;
import io.github.fallingsoulm.easy.archetype.job.invoke.JobMonitorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 定时任务监控事件监听者
 *
 * @author luyanan
 * @since 2021/8/1
 **/
@Component
public class JobMonitorEventListener implements ApplicationListener<JobMonitorEvent> {


    @Autowired
    private NoticeMessageApi noticeMessageApi;

    @Autowired
    private BlogConfigProperties blogConfigProperties;

    @Override
    public void onApplicationEvent(JobMonitorEvent event) {
        Object source = event.getSource();
        if (!(source instanceof JobMonitorVo)) {

            return;
        }
        BlogConfigProperties.job job = blogConfigProperties.getJob();
        if (null == job || StrUtil.isBlank(job.getNoticeToken())) {

            throw new BusinessException("job.noticeToken的配置不能为空");
        }
        JobMonitorVo jobMonitorVo = (JobMonitorVo) source;
        JobVo jobVo = jobMonitorVo.getJobVo();
        noticeMessageApi.sendMessage(job.getNoticeToken(), jobVo.getJobName(), getContent(jobMonitorVo), "html");
    }

    private static String getContent(JobMonitorVo jobMonitorVo) {

        StringBuffer sb = new StringBuffer();
        sb.append("id:").append(jobMonitorVo.getJobVo().getJobId()).append("\n")
                .append("标题:").append(jobMonitorVo.getJobVo().getJobName()).append("\n");

        JobMonitorVo.Status status = JobMonitorVo.Status.getByStatus(jobMonitorVo.getStatus());
        sb.append("开始时间为:").append(DateUtil.date(jobMonitorVo.getExecuteStartTime()).toString("yyyy-MM-dd HH:mm:ss")).append("\n");
        sb.append("状态:" + status.getDesp()).append("\n");
        if (status.equals(JobMonitorVo.Status.ERROR)) {
            sb.append("错误信息为:" + jobMonitorVo.getErrorMsg()).append("\n");
        }
        if (status.equals(JobMonitorVo.Status.SUCCESS) && null != jobMonitorVo.getExecuteEndTime()) {
            sb.append("结束时间为:").append(DateUtil.date(jobMonitorVo.getExecuteEndTime()).toString("yyyy-MM-dd HH:mm:ss")).append("\n");

            sb.append("耗时:").append((jobMonitorVo.getExecuteEndTime() - jobMonitorVo.getExecuteStartTime()) / 1000).append("/s").append("\n");
        }
        return sb.toString();
    }
}
