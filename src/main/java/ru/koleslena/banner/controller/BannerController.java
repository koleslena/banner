package ru.koleslena.banner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.koleslena.banner.model.Banner;
import ru.koleslena.banner.service.BannerService;
import ru.koleslena.banner.view.BannerFile;
import ru.koleslena.banner.view.BannerShort;
import ru.koleslena.banner.view.BannerView;
import ru.koleslena.banner.view.BannerViewStatistics;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * @since 07.09.15.
 */
@Controller
@RequestMapping( value = "banner" )
public class BannerController {

    private static Logger logger = LoggerFactory.getLogger(BannerController.class);

    protected static final String JSON_UTF_8 = "application/json;charset=UTF-8";
    protected static final String ACCEPT_JSON_UTF_8 = "Accept=" + JSON_UTF_8;

    @Autowired
    private BannerService bannerService;

    @RequestMapping(method = RequestMethod.GET, value = "/", headers = { ACCEPT_JSON_UTF_8 })
    public @ResponseBody
    Page<BannerShort> getBanners(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        if (logger.isDebugEnabled()) {
            logger.debug("GET method banners page view invoked");
        }

        return bannerService.getAll(pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", headers = { ACCEPT_JSON_UTF_8 })
    public @ResponseBody
    BannerViewStatistics getBanner(@PathVariable("id") Long id) {
        if (logger.isDebugEnabled()) {
            logger.debug("GET method banner view invoked, id={}", id);
        }

        return bannerService.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/next/{id}", headers = { ACCEPT_JSON_UTF_8 })
    public @ResponseBody
    BannerView getNextBanner(@PathVariable("id") Long id) {
        if (logger.isDebugEnabled()) {
            logger.debug("GET method next banner invoked, id={}", id);
        }

        return bannerService.getNext(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public @ResponseBody
    BannerFile saveBanner(@RequestParam(value = "name", required = true) String name,
                     @RequestParam(value = "ref", required = true) String ref,
                     @RequestParam(value = "content", required = true) MultipartFile content) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("POST method banner save invoked for [name={}, ref={}, file={}]", name, ref, content.getName());
        }

        return bannerService.saveBanner(name, ref, content.getBytes());
    }
}
