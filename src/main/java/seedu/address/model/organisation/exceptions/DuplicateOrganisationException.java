package seedu.address.model.organisation.exceptions;

public class DuplicateOrganisationException extends RuntimeException {
    public DuplicateOrganisationException() {
        super("Operation would result in duplicate organisations");
    }
}
