package ru.koleslena.banner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO: may be should move to other web application (war)
 *
 * @since 11.09.15.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "")
    public String defaultIndex() {
        return "index";
    }

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }
}
