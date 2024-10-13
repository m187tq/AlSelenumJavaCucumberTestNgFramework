package com.automationteststore.factories;

import com.automationteststore.helperutilities.PageException;

public interface JobsClassPageFactory {

    Class<?> createPageClass(String pageName) throws PageException, PageException;

}
