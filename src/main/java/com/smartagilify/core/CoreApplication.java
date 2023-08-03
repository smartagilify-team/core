package com.smartagilify.core;

import com.smartagilify.core.environments.ServerProperties;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.text.MessageFormat;

@EnableAspectJAutoProxy
@RequiredArgsConstructor
@SpringBootApplication
public class CoreApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CoreApplication.class);
    private final ServerProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

    public void run(String... args) {
        final String env = MessageFormat.format("using environment: [{0}]", properties.getEnvironment());
        logger.info(env);
    }

}
