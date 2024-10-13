package com.automationteststore.helperutilities.select;


import com.automationteststore.helperutilities.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class DropDownHelper {

    private WebDriver driver;
    private Logger log = LoggerHelper.getLogger(DropDownHelper.class);

    public DropDownHelper(WebDriver driver) {
        this.driver = driver;
        log.info("driver is: " + driver);
    }

    public void selectUsingValue(WebElement element, String value) {
        Select select = new Select(element);
        log.info("selectUsingValue and value is: " + value);
        select.selectByValue(value);
    }

    public void selectUsingIndex(WebElement element, int index) {
        Select select = new Select(element);
        log.info("selectUsingIndex and index is: " + index);
        select.selectByIndex(index);
    }

    public void selectUsingVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        log.info(select.getFirstSelectedOption().getText() + " is selected successfully");
        select.selectByVisibleText(visibleText);
    }

    public void deSelectUsingValue(WebElement element, String value) {
        Select select = new Select(element);
        log.info("deSelectUsingValue and value is: " + value);
        select.deselectByValue(value);
    }

    public void deSelectUsingIndex(WebElement element, int index) {
        Select select = new Select(element);
        log.info("deSelectUsingIndex and index is: " + index);
        select.deselectByIndex(index);
    }

    public void deSelectUsingVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        log.info("deselectByVisibleText and visibleText is: " + visibleText);
        select.deselectByVisibleText(visibleText);
    }

    public List<String> getAllDropDownData(WebElement element) {
        Select select = new Select(element);
        List<WebElement> elementList = select.getOptions();
        List<String> valueList = new LinkedList<String>();
        for (WebElement ele : elementList) {
            log.info(ele.getText());
            valueList.add(ele.getText());
        }
        return valueList;
    }

    public void selectByValueFromDropDown(WebElement dropDownElement, String value) {
        Select dropdown = new Select(dropDownElement);
        if (value != null && !(value.isEmpty())) {
            dropdown.selectByValue(value.trim());
        }
    }

    public boolean checkDropdownHasAllTheValues(WebElement dropdownElement, List<List<String>> data) {
        int counter = 0;
        Select dropdown = new Select(dropdownElement);
        List<WebElement> options = dropdown.getOptions();

        for (WebElement valueInDropdown : options) {
            loop:
            for (int i = 0; i < data.get(0).size(); i++) {
                if (valueInDropdown.getText().equals((data.get(0).get(i).trim()))) {
                    counter++;
                    break loop;
                }
            }
        }

        if (counter == data.get(0).size()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean assertFirstValueInDropDown(WebElement dropdownElement, String value) {
        boolean result = true;
        Select dropdown = new Select(dropdownElement);
        result = dropdown.getOptions().get(0).getText().equals(value);
        return result;
    }

    public void selectFromDropdown(Consumer<Select> consumer, WebElement element) {
        consumer.accept(new Select(element));

    }
}
