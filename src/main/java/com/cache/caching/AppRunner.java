package com.cache.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(AppRunner.class);

    BookRepository bookRepository;

    public AppRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("articles fetching..."+bookRepository.getArticles());
        logger.info("articles fetching..."+bookRepository.getArticles());
        logger.info("articles fetching..."+bookRepository.getArticles());
        logger.info("articles fetching..."+bookRepository.getArticles());
    }
}
