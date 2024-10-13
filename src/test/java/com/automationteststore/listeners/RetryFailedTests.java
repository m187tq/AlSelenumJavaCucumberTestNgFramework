package com.automationteststore.listeners;

import com.automationteststore.enums.ConfigProperties;
import com.automationteststore.utils.PropertyUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Implements {@link IRetryAnalyzer}.<p>
 * Helps in rerunning the failed tests.<p>
 */
public class RetryFailedTests implements IRetryAnalyzer {

    private int count = 0;
    private int retries = 1;

    /**
     * Return true when needs to be retried and false otherwise.
     * Maximum will retry for one time.
     * Retry will happen if user desires to and set the value in the property file
     */
    @Override
    public boolean retry(ITestResult result) {
        boolean value = false;

        if (PropertyUtils.get(ConfigProperties.RETRYFAILEDTESTS).equalsIgnoreCase("yes")) {
            value = count < retries;
            count++;
        }
        return value;
    }

}
