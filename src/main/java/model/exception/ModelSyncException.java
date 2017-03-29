package model.exception;

/**
 * Created by Rajmund Staniek on 29-Mar-17.
 */
public class ModelSyncException extends Exception {
    public ModelSyncException() {
        super();
    }

    public ModelSyncException(String message) {
        super(message);
    }

    public ModelSyncException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelSyncException(Throwable cause) {
        super(cause);
    }

    protected ModelSyncException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
