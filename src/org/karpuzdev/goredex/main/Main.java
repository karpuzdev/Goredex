package org.karpuzdev.goredex.main;

import org.karpuzdev.goredex.bot.CodexiaBot;
import org.springframework.boot.SpringApplication;

import org.karpuzdev.goredex.web.SpringMain;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Main {

    public static final Logger logger = Logger.getLogger("Goredex");

    public static void main(String[] args) {

        logger.info("Goredex is started!");

        Map<String, String> envMap = new HashMap<>(System.getenv());
        for (String arg : args) {
            if (arg.contains("--")) {
                String[] splitted = arg.split("--");
                envMap.put(splitted[0], splitted[1]);
            } else {
                envMap.put(arg, "");
            }
        }

        if (!envMap.containsKey("-nospring")) {
            logger.info("Spring is calling...");
            SpringApplication.run(SpringMain.class, args);
            logger.info("Spring is called!");
        } else {
            logger.info("-nospring flag found. Spring won't work.");
        }

        if (!envMap.containsKey("-nobot")) {
            logger.info("CodexiaBot is calling...");
            new CodexiaBot().start(envMap);
            logger.info("CodexiaBot is called!");
        } else {
            logger.info("-nobot flag found. CodexiaBot won't work.");
        }

    }

}
