package ro.ing.storemanagementtool.exception;

public class ProductCreationException extends RuntimeException {
    public ProductCreationException(String errorMessage) {
        super(errorMessage);
    }
}
