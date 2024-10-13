package com.automationteststore.pages.general;

public interface SummaryErrorMessagePage {
    public void assertOnSummaryErrorMessage(String expectedErrorMessage, String errorElementId);

    public void assertOnSummaryErrorMessage(String expectedErrorMessage);
}
