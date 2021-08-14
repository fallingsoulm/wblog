package com.wblog.bi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面跳转
 *
 * @author luyanan
 * @since 2021/8/2
 **/
@Controller
public class PageController {

    /**
     * 表单设计URL
     */
    private static final String REDIRECT_UREPORT_DESIGNER = "redirect:/ureport/designer";


    /**
     * 跳转至表单设计
     *
     * @return java.lang.String
     * @since 2021/8/2
     */
    @GetMapping("designer")
    public String designer() {
        return REDIRECT_UREPORT_DESIGNER;
    }


}
