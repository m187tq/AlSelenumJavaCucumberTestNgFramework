package com.automationteststore.helperutilities.navigation;

import com.automationteststore.webdriverutilities.WebDrv;

import java.util.Iterator;
import java.util.Set;

public class NavigateToNewTab {
    public static void navigateToNewTab() throws Exception {
        String parent = WebDrv.getInstance().getWebDriver().getWindowHandle();
        Set<String> s1 = WebDrv.getInstance().getWebDriver().getWindowHandles();
        Iterator<String> I1 = s1.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                Thread.sleep(500);
                WebDrv.getInstance().getWebDriver().switchTo().window(child_window);

            }
        }
    }

    public static void closeChildWindow() throws Exception {
        String parent = WebDrv.getInstance().getWebDriver().getWindowHandle();
        Set<String> s1 = WebDrv.getInstance().getWebDriver().getWindowHandles();
        Iterator<String> I1 = s1.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                Thread.sleep(500);
                WebDrv.getInstance().getWebDriver().switchTo().window(child_window).close();

            }
        }
        WebDrv.getInstance().getWebDriver().switchTo().window(parent);
    }


}
