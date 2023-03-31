package com.padmanabhasmac.springbootmongodb.exceptions;

public class TodoCollectionException extends Exception {

    private static final long serialVersionID = 1L;

    public TodoCollectionException(String message) {
        super(message);
    }

    public static String notFoundException(String id) {
        return "Todo with id " + id + " not found";
    }

    public static String alreadyExistsException(String todo) {
        return "Todo with name " + todo + " already exists";
    }

}
