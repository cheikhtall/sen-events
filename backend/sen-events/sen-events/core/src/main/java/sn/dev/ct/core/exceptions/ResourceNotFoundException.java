package sn.dev.ct.core.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entity, Object id) {
        super("Resource " + entity + " with id " + id + " not found");
    }
}
