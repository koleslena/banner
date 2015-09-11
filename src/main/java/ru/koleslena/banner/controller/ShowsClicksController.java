package ru.koleslena.banner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.koleslena.banner.service.QueueUpdateProvider;

/**
 * @since 09.09.15.
 */
@Controller
public class ShowsClicksController {

    private static Logger logger = LoggerFactory.getLogger(ShowsClicksController.class);

    @Autowired
    private QueueUpdateProvider queueUpdateProvider;

    @RequestMapping(method = RequestMethod.GET, value = "/show/{id}")
    @ResponseStatus(HttpStatus.OK)
    void show(@PathVariable("id") Long id) {
        if (logger.isDebugEnabled()) {
            logger.debug("GET method show invoked, id={}", id);
        }

        queueUpdateProvider.addToShowsQueue(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/click/{id}")
    void click(@PathVariable("id") Long id) {
        if (logger.isDebugEnabled()) {
            logger.debug("GET method click invoked, id={}", id);
        }

        queueUpdateProvider.addToClicksQueue(id);
    }
}
