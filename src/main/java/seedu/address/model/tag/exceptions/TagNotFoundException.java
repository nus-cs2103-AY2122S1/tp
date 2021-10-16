package seedu.address.model.tag.exceptions;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException() {
        super("Non-existent tag cannot be removed.");
    }
}
