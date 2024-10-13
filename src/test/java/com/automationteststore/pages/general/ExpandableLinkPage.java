package com.automationteststore.pages.general;

public interface ExpandableLinkPage extends LinkPage {

    void assertCorrectExpandedContent(String expectedExpandedContent);

}
