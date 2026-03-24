package com.cheq.demo_webshop.utils.common;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import net.minidev.json.JSONObject;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cheq.demo_webshop.listener.StepListener;
import com.cheq.demo_webshop.utils.api.JsonSerializerUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;

/**
 * Utility class for Allure reporting functions such as:
 * - attaching UI screenshots
 * - attaching text and JSON content
 * - attaching API request/response details
 * - writing Allure environment properties
 */
public class AllureUtil {

    private static final Logger logger = LoggerUtil.getLogger(AllureUtil.class);
    private final AndroidDriver driver;

    public AllureUtil(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * Attaches plain text content to the Allure report.
     *
     * @param title   attachment title
     * @param content text content to attach
     */
    public static void attachText(String title, String content) {
        if (content == null) {
            content = "null";
        }

        Allure.addAttachment(
                title,
                "text/plain",
                new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)),
                ".txt"
        );
    }

    /**
     * Attaches JSON content to the Allure report.
     *
     * @param title   attachment title
     * @param content JSON content to attach
     */
    public static void attachJson(String title, String content) {
        if (content == null) {
            content = "{}";
        }

        Allure.addAttachment(
                title,
                "application/json",
                new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)),
                ".json"
        );
    }

    /**
     * Attaches plain text content to the current UI step in Allure.
     *
     * @param title   attachment title
     * @param message text content to attach
     */
    public void attachStepText(String title, String message) {
        Allure.getLifecycle().updateStep(step -> step.setName("📸 " + StepListener.currentStep));
        Allure.addAttachment(
                title,
                "text/plain",
                new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8)),
                ".txt"
        );
    }

    /**
     * Captures a screenshot from the current Android driver
     * and attaches it to the Allure report.
     */
    public void captureAndAttachScreenshot() {
        try {
            if (driver == null) {
                logger.warn("Skipping screenshot capture because driver is null.");
                return;
            }

            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.getLifecycle().updateStep(step -> step.setName("📸 " + StepListener.currentStep));
            Allure.addAttachment(
                    "Screenshot",
                    "image/png",
                    new ByteArrayInputStream(screenshotBytes),
                    ".png"
            );
        } catch (Exception e) {
            logger.warn("Skipping screenshot capture: {}", e.getMessage());
        }
    }

    /**
     * Writes environment.properties file for Allure report.
     *
     * @param env key-value environment properties
     */
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
    
    public static void createExecutorInfo() {
        String user = System.getProperty("user.name");
        String host = null;
        String projectDir = System.getProperty("user.dir");
        String reportPath = projectDir + "/target/allure-report/index.html";

        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            //
        }

        String formattedUser = user
                .replaceAll("([a-z])([A-Z])", "$1 $2")  // split camelCase
                .replaceAll("[_.]", " ")                // replace underscores/dots if any
                .replaceAll("\\s+", " ")                // normalize spaces
                .trim();

        JSONObject executor = new JSONObject();
        executor.put("name", formattedUser + " (" + host + ")");
        executor.put("type", "local");
        executor.put("buildName", "SellersPortal Smoke Test - " + LocalDate.now());
        executor.put("reportUrl", "file:///" + reportPath.replace("\\", "/"));

        try (FileWriter file = new FileWriter("target/allure-results/executor.json")) {
            file.write(executor.toJSONString());
        } catch (IOException e) {
            //
        }
    }

    private static String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
    
    public static void writeCategories() {
        File categoriesFile = new File("target/allure-results/categories.json");
        categoriesFile.getParentFile().mkdirs();

        String json = "[\n"
                + "  {\n"
                + "    \"name\": \"Assertion Failures\",\n"
                + "    \"matchedStatuses\": [\"failed\"],\n"
                + "    \"messageRegex\": \".*(AssertionError|expected:|but was:).*\"\n"
                + "  },\n"
                + "  {\n"
                + "    \"name\": \"Element Not Found\",\n"
                + "    \"matchedStatuses\": [\"failed\", \"broken\"],\n"
                + "    \"messageRegex\": \".*(NoSuchElementException|Element not found|no such element).*\"\n"
                + "  },\n"
                + "  {\n"
                + "    \"name\": \"Timeout Issues\",\n"
                + "    \"matchedStatuses\": [\"failed\", \"broken\"],\n"
                + "    \"messageRegex\": \".*(TimeoutException|timed out|timeout).*\"\n"
                + "  },\n"
                + "  {\n"
                + "    \"name\": \"Driver / Session Issues\",\n"
                + "    \"matchedStatuses\": [\"broken\"],\n"
                + "    \"messageRegex\": \".*(SessionNotCreatedException|WebDriverException|driver error|Could not start a new session).*\"\n"
                + "  },\n"
                + "  {\n"
                + "    \"name\": \"API Validation Failures\",\n"
                + "    \"matchedStatuses\": [\"failed\"],\n"
                + "    \"messageRegex\": \".*(status code|response body|API validation|expected status).*\"\n"
                + "  }\n"
                + "]";

        try (FileWriter writer = new FileWriter(categoriesFile)) {
            writer.write(json);
            logger.info("Allure categories.json written successfully at {}", categoriesFile.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to write Allure categories file", e);
        }
    }
    
    public static void copyHistoryToResults() {
        Path sourceHistory = Paths.get("reports", "allure-history");
        Path targetHistory = Paths.get("target", "allure-results", "history");

        if (!Files.exists(sourceHistory)) {
            logger.info("No previous Allure history found at {}", sourceHistory.toAbsolutePath());
            return;
        }

        try {
            copyDirectory(sourceHistory, targetHistory);
            logger.info("Copied Allure history to results: {} -> {}", sourceHistory.toAbsolutePath(), targetHistory.toAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to copy Allure history to results", e);
        }
    }

    public static void copyHistoryFromReport() {
        Path sourceHistory = Paths.get("reports", "allure-reports", "history");
        Path targetHistory = Paths.get("reports", "allure-history");

        if (!Files.exists(sourceHistory)) {
            logger.warn("Allure report history folder not found at {}", sourceHistory.toAbsolutePath());
            return;
        }

        try {
            copyDirectory(sourceHistory, targetHistory);
            logger.info("Copied Allure history from report: {} -> {}", sourceHistory.toAbsolutePath(), targetHistory.toAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to copy Allure history from report", e);
        }
    }

    private static void copyDirectory(Path source, Path target) throws IOException {
        if (!Files.exists(source)) {
            return;
        }

        try (Stream<Path> paths = Files.walk(source)) {
            paths.forEach(path -> {
                try {
                    Path relativePath = source.relativize(path);
                    Path targetPath = target.resolve(relativePath);

                    if (Files.isDirectory(path)) {
                        Files.createDirectories(targetPath);
                    } else {
                        Files.createDirectories(targetPath.getParent());
                        Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Failed copying file: " + path, e);
                }
            });
        } catch (RuntimeException e) {
            if (e.getCause() instanceof IOException) {
                throw (IOException) e.getCause();
            }
            throw e;
        }
    }

    /**
     * Attaches API request details to Allure report.
     *
     * @param method      HTTP method
     * @param endpoint    API endpoint
     * @param requestBody request payload object
     * @param headers     request headers
     */
    public static void attachRequestDetails(String method, String endpoint, Object requestBody, Map<String, String> headers) {
        attachText("API Method", method);
        attachText("API Endpoint", endpoint);

        if (headers != null && !headers.isEmpty()) {
            attachJson("API Headers", JsonSerializerUtil.toJson(headers));
        }

        if (requestBody != null) {
            attachJson("API Request Body", JsonSerializerUtil.toJson(requestBody));
        }
    }

    /**
     * Attaches API response details to Allure report.
     *
     * @param statusCode   response status code
     * @param responseBody response body content
     */
    public static void attachResponseDetails(int statusCode, String responseBody) {
        attachText("API Response Status", String.valueOf(statusCode));
        attachJson("API Response Body", responseBody);
    }
}
