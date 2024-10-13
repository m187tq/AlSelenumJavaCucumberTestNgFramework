package com.automationteststore.utils;

import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Utility to take base64 screenshot.
 *
 * <pre>
 * <b>
 * <a href="https://www.youtube.com/channel/UC6PTXUHb6j4Oxf0ccdRI11A">Testing Mini Bytes Youtube channel</a>
 * </b>
 * </pre>
 */
public final class ScreenshotUtils {

    /**
     * Private constructor to avoid external instantiation
     */
    private ScreenshotUtils() {
    }

    /**
     * Captures screenshot of the current page, constructs a base64 string from the image and return to the caller.
     * There is no temporary screenshot image generated here. If user needs separate screenshot image, they can construct
     * a new method. It is advisable to use this method for many reasons.
     *
     * @return Image in the form of Base64 String which can be appended directly to report
     */
    public static String getBase64Image() {
        return ((TakesScreenshot) WebDrv.getInstance().getWebDriver()).getScreenshotAs(OutputType.BASE64);
    }

}
