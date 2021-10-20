package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Folder}.
 */
public class JsonAdaptedFolder {

    public static final String FOLDER_MISSING_FIELD_MESSAGE_FORMAT = "Folder's %s field is missing!";

    private final String folderName;
    private final List<JsonAdaptedPerson> contacts = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedFolder} with the given folder details.
     */
    public JsonAdaptedFolder(@JsonProperty("folderName") String folderName,
                             @JsonProperty("contacts") List<JsonAdaptedPerson> contacts) {
        this.folderName = folderName;
        if (contacts != null) {
            this.contacts.addAll(contacts);
        }
    }

    /**
     * Converts a given {@code Folder} into this class for Jackson use.
     */
    public JsonAdaptedFolder(Folder source) {
        this.folderName = source.getFolderName().toString();
        this.contacts.addAll(source.getContacts().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted folder object into the model's {@code Folder} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted folder.
     */
    public Folder toModelType() throws IllegalValueException {
        final List<Person> folderContacts = new ArrayList<>();
        for (JsonAdaptedPerson contact : this.contacts) {
            folderContacts.add(contact.toModelType());
        }

        if (this.folderName == null) {
            throw new IllegalValueException(String.format(FOLDER_MISSING_FIELD_MESSAGE_FORMAT,
                    FolderName.class.getSimpleName()));
        }
        if (!FolderName.isValidName(this.folderName)) {
            throw new IllegalValueException(FolderName.MESSAGE_CONSTRAINTS);
        }
        final FolderName modelFolderName = new FolderName(this.folderName);
        final Set<Person> modelContacts = new HashSet<>(folderContacts);
        return new Folder(modelFolderName, modelContacts);
    }
}
