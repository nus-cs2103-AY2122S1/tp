package seedu.address.model.folder.exceptions;

/**
 * Signals that the operation is unable to find the specified folder.
 */
public class FolderNotFoundException extends RuntimeException {
    public FolderNotFoundException() {
        super("Folder not found. Please try again.");
    }
}
