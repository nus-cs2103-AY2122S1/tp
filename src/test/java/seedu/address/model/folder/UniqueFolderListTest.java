package seedu.address.model.folder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFolders.CCA;
import static seedu.address.testutil.TypicalFolders.CS2103;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.folder.exceptions.DuplicateFolderException;
import seedu.address.model.folder.exceptions.FolderNotFoundException;



class UniqueFolderListTest {
    private final UniqueFolderList uniqueFolderList = new UniqueFolderList();

    @Test
    public void contains_nullFolder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFolderList.contains(null));
    }

    @Test
    public void contains_folderNotInList_returnsFalse() {
        assertFalse(uniqueFolderList.contains(CS2103));
    }

    @Test
    public void contains_folderInList_returnsTrue() {
        uniqueFolderList.add(CS2103);
        assertTrue(uniqueFolderList.contains(CS2103));
    }

    @Test
    public void contains_folderWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFolderList.add(CCA);
        Folder ccaDuplicate = new Folder(new FolderName("CCA"));
        assertTrue(uniqueFolderList.contains(ccaDuplicate));
    }

    @Test
    public void add_nullFolder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFolderList.add(null));
    }

    @Test
    public void add_duplicateFolder_throwsDuplicateFolderException() {
        uniqueFolderList.add(CCA);
        assertThrows(DuplicateFolderException.class, () -> uniqueFolderList.add(CCA));
    }

    @Test
    public void setFolder_nullTargetFolder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFolderList.setFolder(null, CCA));
    }

    @Test
    public void setFolder_nullEditedFolder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFolderList.setFolder(CCA, null));
    }

    @Test
    public void setFolder_targetFolderNotInList_throwsFolderNotFoundException() {
        assertThrows(FolderNotFoundException.class, () -> uniqueFolderList.setFolder(CCA, CCA));
    }

    @Test
    public void setFolder_editedFolderIsSameFolder_success() {
        uniqueFolderList.add(CCA);
        uniqueFolderList.setFolder(CCA, CCA);
        UniqueFolderList expectedUniqueFolderList = new UniqueFolderList();
        expectedUniqueFolderList.add(CCA);
        assertEquals(expectedUniqueFolderList, uniqueFolderList);
    }

    @Test
    public void setFolder_editedFolderHasSameIdentity_success() {
        uniqueFolderList.add(CCA);
        Folder ccaDuplicate = new Folder(new FolderName("CCA"));
        uniqueFolderList.setFolder(CCA, ccaDuplicate);
        UniqueFolderList expectedUniqueFolderList = new UniqueFolderList();
        expectedUniqueFolderList.add(ccaDuplicate);
        assertEquals(expectedUniqueFolderList, uniqueFolderList);
    }

    @Test
    public void setFolder_editedFolderHasDifferentIdentity_success() {
        uniqueFolderList.add(CCA);
        uniqueFolderList.setFolder(CCA, CS2103);
        UniqueFolderList expectedUniqueFolderList = new UniqueFolderList();
        expectedUniqueFolderList.add(CS2103);
        assertEquals(expectedUniqueFolderList, uniqueFolderList);
    }

    @Test
    public void setFolder_editedFolderHasNonUniqueIdentity_throwsDuplicateFolderException() {
        uniqueFolderList.add(CCA);
        uniqueFolderList.add(CS2103);
        assertThrows(DuplicateFolderException.class, () -> uniqueFolderList.setFolder(CCA, CS2103));
    }

    @Test
    public void remove_nullFolder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFolderList.remove(null));
    }

    @Test
    public void remove_folderDoesNotExist_throwsFolderNotFoundException() {
        assertThrows(FolderNotFoundException.class, () -> uniqueFolderList.remove(CCA));
    }

    @Test
    public void remove_existingFolder_removesFolder() {
        uniqueFolderList.add(CCA);
        uniqueFolderList.remove(CCA);
        UniqueFolderList expectedUniqueFolderList = new UniqueFolderList();
        assertEquals(expectedUniqueFolderList, uniqueFolderList);
    }

    @Test
    public void setFolders_nullUniqueFolderList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFolderList.setFolders((UniqueFolderList) null));
    }

    @Test
    public void setFolders_uniqueFolderList_replacesOwnListWithProvidedUniqueFolderList() {
        uniqueFolderList.add(CCA);
        UniqueFolderList expectedUniqueFolderList = new UniqueFolderList();
        expectedUniqueFolderList.add(CS2103);
        uniqueFolderList.setFolders(expectedUniqueFolderList);
        assertEquals(expectedUniqueFolderList, uniqueFolderList);
    }

    @Test
    public void setFolders_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFolderList.setFolders((List<Folder>) null));
    }

    @Test
    public void setFolders_list_replacesOwnListWithProvidedList() {
        uniqueFolderList.add(CCA);
        List<Folder> folderList = Collections.singletonList(CS2103);
        uniqueFolderList.setFolders(folderList);
        UniqueFolderList expectedUniqueFolderList = new UniqueFolderList();
        expectedUniqueFolderList.add(CS2103);
        assertEquals(expectedUniqueFolderList, uniqueFolderList);
    }

    @Test
    public void setFolders_listWithDuplicateFolders_throwsDuplicateFolderException() {
        List<Folder> listWithDuplicateFolders = Arrays.asList(CCA, CCA);
        assertThrows(DuplicateFolderException.class, () -> uniqueFolderList.setFolders(listWithDuplicateFolders));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueFolderList
                .asUnmodifiableObservableList()
                .remove(0));
    }
}
