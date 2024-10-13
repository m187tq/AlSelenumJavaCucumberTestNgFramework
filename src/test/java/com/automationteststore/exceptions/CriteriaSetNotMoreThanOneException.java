package com.automationteststore.exceptions;

public class CriteriaSetNotMoreThanOneException extends Exception {
    public CriteriaSetNotMoreThanOneException(String message) {
        super(message);
    }

    public static void checkIfCriterisSetIsOverOneOrElseThrowException(String string, int criteriaSetNumber)
            throws CriteriaSetNotMoreThanOneException {
        if (criteriaSetNumber <= 1) {
            throw new CriteriaSetNotMoreThanOneException("Criteria set number must be over one");
        }
    }
}
