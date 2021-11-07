package seedu.address.model.uniquelist.exceptions;

/**
 * Signals that the operation is unable to find the specified item.
 */
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super("Item does not appear in the list.");
    }

}
