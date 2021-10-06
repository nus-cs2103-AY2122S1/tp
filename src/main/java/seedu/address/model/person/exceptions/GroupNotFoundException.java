package seedu.address.model.person.exceptions;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException() {
        super("Group does not exist");
    }
}