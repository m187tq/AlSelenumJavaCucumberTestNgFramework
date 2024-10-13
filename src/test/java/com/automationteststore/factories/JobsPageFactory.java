package com.automationteststore.factories;

import com.automationteststore.helperutilities.PageException;

public interface JobsPageFactory {

    Object createPage(String pageName) throws PageException, PageException;

}
