package com.phuc.pcoreservice.schedule;


import com.phuc.pcoreservice.dto.FingerprintDTO;

import com.phuc.pcoreservice.service.IProfileService;
import com.phuc.pcoreservice.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class FingerprintTask {
    private static final Logger logger = LoggerFactory.getLogger(FingerprintTask.class);

    @Value("${cron.task.path.request}")
    private Integer totalRequest;

    @Value("${fingerprint.key}")
    private String fingerprintKey;

    @Value("${fingerprint.url}")
    private String fingerprintUrl;

    @Autowired
    private IProfileService profileService;

    @Scheduled(cron = "${cron.task.save.fingerprint}")
    public void importFingerprint() throws InterruptedException {
        logger.info("start import fingerprint....");
        ExecutorService executor = Executors.newCachedThreadPool();
        Random rand = new Random();
        String type = CommonUtil.OPTION_LIST[rand.nextInt(CommonUtil.OPTION_LIST.length)];
        String urlRequest = String.format(fingerprintUrl, type, fingerprintKey );

        List<FingerprintDTO> list = new ArrayList<>();
        for (int i = 0; i < totalRequest; i++){
            executor.execute(new InsertFingerprintTask( list, urlRequest, type));
            Thread.sleep(5000);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Chờ xử lý hết các request còn chờ trong Queue ...
        }
        profileService.saveFingerprint(list);
        logger.info("import fingerprint success!");
    }
}
