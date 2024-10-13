package com.automationteststore.pages.general;

import com.automationteststore.enums.WebElements;
import com.automationteststore.exceptions.TableRowDoesNotExistException;

import java.util.Map;

public interface TablePage {

    void assertTableHasElementInEveryRow(WebElements deleteLink);

    void assertCorrectTableContents(Map<String, String> fieldDataFromPage) throws TableRowDoesNotExistException;
}
