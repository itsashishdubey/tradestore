package com.ad;

import com.ad.callermock.ExpiryCheckScheduler;
import com.ad.callermock.RandomDataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main class which start the application
 */
@ComponentScan("com.ad")
@Configuration
public class StartApplication {

    private static final Logger logger = LoggerFactory.getLogger(StartApplication.class);
    public static void main(String[] args) {


        ApplicationContext applicationContext =
                    new AnnotationConfigApplicationContext(StartApplication.class);

        logger.info("Application Started");

        RandomDataLoader dataLoader = applicationContext.getBean(RandomDataLoader.class);
        dataLoader.scheduleDataLoader();

        ExpiryCheckScheduler expiryCheckScheduler = applicationContext.getBean(ExpiryCheckScheduler.class);
        expiryCheckScheduler.checkAndSetExpiry();

    }
}
