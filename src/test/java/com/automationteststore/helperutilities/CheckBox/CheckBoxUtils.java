package com.automationteststore.helperutilities.CheckBox;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by sadheshviveganandan on 09/04/2019.
 */
public class CheckBoxUtils {

    public static void assertCheckBoxesAreUnTickedByDefaults(List<WebElement> checkBoxesList) {
        for (int i = 0; i < checkBoxesList.size(); i++) {
            Assert.assertFalse(checkBoxesList.get(i).isSelected());

        }
    }
}
