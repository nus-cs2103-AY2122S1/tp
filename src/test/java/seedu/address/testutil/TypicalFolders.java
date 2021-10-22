package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Folder} objects to be used in tests.
 */
public class TypicalFolders {

    public static final Folder CS2103 = new Folder(new FolderName("CS2103"));
    public static final Folder TEAM_PROJECT = new Folder(new FolderName(("Team Project")));
    public static final Folder CCA = new Folder(new FolderName("CCA"));
    public static final Folder EXCLUDED_FOLDER = new Folder(new FolderName("Excluded folder"));

    private TypicalFolders() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons and folders.
     */
    public static AddressBook getTypicalAddressBookWithFolders() {
        AddressBook ab = new AddressBook();
        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Folder folder : getTypicalFolders()) {
            ab.addFolder(folder);
        }
        return ab;
    }

    public static List<Folder> getTypicalFolders() {
        return new ArrayList<>(Arrays.asList(CS2103, TEAM_PROJECT, CCA));
    }
}
