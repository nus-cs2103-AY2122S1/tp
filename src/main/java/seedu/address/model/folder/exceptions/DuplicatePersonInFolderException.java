package seedu.address.model.folder.exceptions;

/**
 * Signals that the contact already exists in folder (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePersonInFolderException extends RuntimeException {
    public DuplicatePersonInFolderException() {
        super("Folder already contains contact");
    }
}
