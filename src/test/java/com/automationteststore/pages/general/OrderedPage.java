package com.automationteststore.pages.general;

import com.automationteststore.enums.WebElements;

public interface OrderedPage {

    void assertIfElementsAreOrderedAsInTheExpectedList(WebElements[] expectedWebElements);

}
