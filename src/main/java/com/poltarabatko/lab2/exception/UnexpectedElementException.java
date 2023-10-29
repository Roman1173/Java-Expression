package com.poltarabatko.lab2.exception;

/**
 * UnexpectedElementException implementation
 * @author poltarabatko
 */

public class UnexpectedElementException extends RuntimeException{

    /**
     * Constructs a new UnexpectedElementException with the specified error message.
     *
     * @param message The detailed error message describing the unexpected element.
     */
    public UnexpectedElementException(String message) {
        super(message);
    }
}
