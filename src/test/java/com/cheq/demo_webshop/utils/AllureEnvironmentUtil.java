package com.cheq.demo_webshop.utils;

import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class AllureEnvironmentUtil {

    private static final Logger logger = LoggerUtil.getLogger(AllureEnvironmentUtil.class);

    private AllureEnvironmentUtil() {
    }

    public static void writeEnvironment(Map<String, String> env) {
        File envFile = new File("target/allure-results/environment.properties");
        envFile.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(envFile)) {
            for (Map.Entry<String, String> entry : env.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            logger.error("Failed to write Allure environment file", e);
        }
    }
}