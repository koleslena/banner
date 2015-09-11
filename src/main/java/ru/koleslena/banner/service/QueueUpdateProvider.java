package ru.koleslena.banner.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.koleslena.banner.utils.CollectionsUtils;

import java.util.*;
import java.util.concurrent.*;

/**
 * @since 08.09.15.
 */
@Service
public class QueueUpdateProvider {

    public static final int MAX_ELEMENTS = 10;

    private static Logger logger = LoggerFactory.getLogger(QueueUpdateProvider.class);

    @Autowired
    private ShowsClicksService showsClicksService;

    private BlockingQueue<Long> showsSynchronousQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<Long> clicksSynchronousQueue = new LinkedBlockingQueue<>();

    public void addToShowsQueue(Long bannerId) {
        showsSynchronousQueue.add(bannerId);
    }

    public void addToClicksQueue(Long bannerId) {
        clicksSynchronousQueue.add(bannerId);
    }

    @Scheduled(fixedDelay=30000, initialDelay = 5000)
    public void processUpdateClicks() {
        logger.info("Process updating clicks is running");

        List<Long> list = new ArrayList<>();
        clicksSynchronousQueue.drainTo(list, MAX_ELEMENTS);

        if (!list.isEmpty()) {
            Map<Long, List<Long>> m = CollectionsUtils.getCountListIdMap(list);

            for (Long counts : m.keySet()) {
                List<Long> ids = m.get(counts);
                if (!ids.isEmpty()) {
                    showsClicksService.updateClicks(ids, counts);
                    logger.info("Process updating clicks: updated {} banners", ids.size());
                }
            }
        }

        logger.info("Process updating clicks is ending");
    }

    @Scheduled(fixedDelay=30000, initialDelay = 5000)
    public void processUpdateShows() {
        logger.info("Process updating shows is running");

        List<Long> list = new ArrayList<>();
        showsSynchronousQueue.drainTo(list, MAX_ELEMENTS);

        if (!list.isEmpty()) {
            Map<Long, List<Long>> m = CollectionsUtils.getCountListIdMap(list);

            for (Long counts : m.keySet()) {
                List<Long> ids = m.get(counts);
                if (!ids.isEmpty()) {
                    showsClicksService.updateShows(ids, counts);
                    logger.info("Process updating shows: updated {} banners", ids.size());
                }
            }
        }

        logger.info("Process updating shows is ending");
    }


}
