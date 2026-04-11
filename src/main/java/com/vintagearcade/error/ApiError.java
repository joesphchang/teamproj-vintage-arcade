package com.vintagearcade.error;

/**
 * The type Api error.
 */
public class ApiError {

    private int status;
    private String message;

    /**
     * Instantiates a new Api error.
     *
     * @param status  the status
     * @param message the message
     */
    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}
