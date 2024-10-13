package com.automationteststore.pages.general;

public interface LinkPage {

    void assertCorrectLinkTitle(String expectedLinkName);

    void clickLink(String linkName);

}
