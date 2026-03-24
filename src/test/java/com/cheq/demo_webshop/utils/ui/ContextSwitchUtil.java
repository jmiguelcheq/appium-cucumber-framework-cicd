package com.cheq.demo_webshop.utils.ui;

import java.util.Set;
import org.apache.logging.log4j.Logger;
import com.cheq.demo_webshop.utils.common.LoggerUtil;
import io.appium.java_client.android.AndroidDriver;

public class ContextSwitchUtil {

    private static final Logger logger = LoggerUtil.getLogger(ContextSwitchUtil.class);

    private final AndroidDriver driver;

    public ContextSwitchUtil(AndroidDriver driver) {
        this.driver = driver;
    }

    public Set<String> getAvailableContexts() {
        Set<String> contexts = driver.getContextHandles();
        logger.info("Available contexts: {}", contexts);
        return contexts;
    }

    public void switchToNativeContext() {
        driver.context("NATIVE_APP");
        logger.info("Switched context to NATIVE_APP");
    }

    public void switchToFirstWebView() {
        Set<String> contexts = getAvailableContexts();

        for (String context : contexts) {
            if (context.toUpperCase().contains("WEBVIEW")) {
                driver.context(context);
                logger.info("Switched context to {}", context);
                return;
            }
        }

        throw new IllegalStateException("No WEBVIEW context found");
    }

    public String getCurrentContext() {
        String context = driver.getContext();
        logger.info("Current context: {}", context);
        return context;
    }

    public boolean isNativeContext() {
        return "NATIVE_APP".equalsIgnoreCase(getCurrentContext());
    }

    public boolean isWebViewContext() {
        return getCurrentContext().toUpperCase().contains("WEBVIEW");
    }
}
