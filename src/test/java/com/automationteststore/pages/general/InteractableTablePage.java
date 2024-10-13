package com.automationteststore.pages.general;

import com.automationteststore.enums.WebElements;
import com.automationteststore.exceptions.TableRowDoesNotExistException;

public interface InteractableTablePage extends TablePage {

    void clickTableElementOnRow(WebElements tableEditButton, int rowNumber, String pageName)
            throws TableRowDoesNotExistException;

}
