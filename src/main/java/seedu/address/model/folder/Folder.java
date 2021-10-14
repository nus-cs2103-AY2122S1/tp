package seedu.address.model.folder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;


/**
 * Represents a Folder in UNIon.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Folder {

    // Identity fields
    private final FolderName folderName;

    // Data fields
    private final Set<Person> contacts;

    /**
     * Every field must be present and not null.
     */
    public Folder(FolderName folderName, Set<Person> contacts) {
        requireAllNonNull(folderName, contacts);

        this.folderName = folderName;
        this.contacts = contacts;
    }

    /**
     * Every field must be present and not null.
     */
    public Folder(FolderName folderName) {
        requireAllNonNull(folderName);

        this.folderName = folderName;
        this.contacts = new HashSet<>();
    }

    public FolderName getFolderName() {
        return this.folderName;
    }

    public Set<Person> getContacts() {
        return this.contacts;
    }

    /**
     * Checks if folder contains a specific person
     *
     * @param target
     * @return true if person exists in folder
     */
    public boolean hasContact(Person target) {
        for (Person contact : contacts) {
            if (contact.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public void addContacts(Person contact) {
        this.contacts.add(contact);
    }

    public void setAll(Folder oldFolder) {
        this.contacts.addAll(oldFolder.contacts);
    }

    /**
     * Returns true if both folders have the same name.
     * This defines a weaker notion of equality between two folders.
     */
    public boolean isSameFolder(Folder otherFolder) {
        if (otherFolder == this) {
            return true;
        }

        return otherFolder != null
                && otherFolder.getFolderName().equals(getFolderName());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Folder)) {
            return false;
        }

        Folder folder = (Folder) other;
        return Objects.equals(folderName, folder.folderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(folderName, contacts);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getFolderName());
        return builder.toString();
    }
}
