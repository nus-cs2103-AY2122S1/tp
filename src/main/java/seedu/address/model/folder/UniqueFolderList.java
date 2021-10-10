package seedu.address.model.folder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.folder.exceptions.DuplicateFolderException;
import seedu.address.model.folder.exceptions.FolderNotFoundException;
import seedu.address.model.person.Person;

/**
 * A list of folders that enforces uniqueness between its elements and does not allow nulls.
 * A Folder is considered unique by comparing using {@code Folder#isSameFolder(Folder)}. As such, adding and updating of
 * folders uses Folder#isSameFolder(Folder) for equality so as to ensure that the folder being added or updated is
 * unique in terms of identity in the UniqueFolderList. However, the removal of a folder uses Folder#equals(Object) so
 * as to ensure that the folder with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Folder#isSameFolder(Folder)
 */
public class UniqueFolderList implements Iterable<Folder> {

    private final ObservableList<Folder> internalList = FXCollections.observableArrayList();
    private final ObservableList<Folder> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent folder as the given argument.
     */
    public boolean contains(Folder toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameFolder);
    }

    /**
     * Adds a folder to the list.
     * The folder must not already exist in the list.
     */
    public void add(Folder toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateFolderException();
        }
        internalList.add(toAdd);
    }

    /**
     * Adds a contact to the folder.
     * The contact must not already exist in the folder.
     */
    public void addContact(Person contact, FolderName name) {
        requireNonNull(contact);
//        for (int i = 0; i < internalList.size(); i++) {
//            Folder folder = internalList.get(i);
//            if (folder.getFolderName() == name) {
//                folder.addContacts(contact);
//                break;
//            }
//        }
        internalList.stream()
                .filter(folder -> folder.getFolderName() == name)
                .findFirst()
                .get()
                .addContacts(contact);
    }

    /**
     * Replaces the folder {@code target} in the list with {@code editedFolder}.
     * {@code target} must exist in the list.
     * The folder identity of {@code editedFolder} must not be the same as another existing folder in the list.
     */
    public void setFolder(Folder target, Folder editedFolder) {
        requireAllNonNull(target, editedFolder);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new FolderNotFoundException();
        }

        if (!target.isSameFolder(editedFolder) && contains(editedFolder)) {
            throw new DuplicateFolderException();
        }

        internalList.set(index, editedFolder);
    }

    /**
     * Removes the equivalent folder from the list.
     * The folder must exist in the list.
     */
    public void remove(Folder toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new FolderNotFoundException();
        }
    }

    public void setFolders(UniqueFolderList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code folders}.
     * {@code folders} must not contain duplicate folders.
     */
    public void setFolders(List<Folder> listFolders) {
        requireAllNonNull(listFolders);
        if (!foldersAreUnique(listFolders)) {
            throw new DuplicateFolderException();
        }

        internalList.setAll(listFolders);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Folder> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Folder> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueFolderList // instanceof handles nulls
                        && internalList.equals(((UniqueFolderList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code folders} contains only unique folders.
     */
    private boolean foldersAreUnique(List<Folder> listFolders) {
        for (int i = 0; i < listFolders.size() - 1; i++) {
            for (int j = i + 1; j < listFolders.size(); j++) {
                if (listFolders.get(i).isSameFolder(listFolders.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
