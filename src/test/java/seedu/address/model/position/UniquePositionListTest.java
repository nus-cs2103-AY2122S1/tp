package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_TITLE_ADMIN_ASSISTANT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;
import static seedu.address.testutil.TypicalPositions.BOOKKEEPER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.position.exceptions.DuplicatePositionException;
import seedu.address.model.position.exceptions.PositionNotFoundException;
import seedu.address.testutil.PositionBuilder;

public class UniquePositionListTest {

    private final UniquePositionList uniquePositionList = new UniquePositionList();

    @Test
    public void contains_nullPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePositionList.contains(null));
    }

    @Test
    public void contains_positionNotInList_returnsFalse() {
        assertFalse(uniquePositionList.contains(ADMIN_ASSISTANT));
    }

    @Test
    public void contains_positionInList_returnsTrue() {
        uniquePositionList.add(ADMIN_ASSISTANT);
        assertTrue(uniquePositionList.contains(ADMIN_ASSISTANT));
    }

    @Test
    public void contains_positionWithSameIdentityFieldsInList_returnsTrue() {
        uniquePositionList.add(ADMIN_ASSISTANT);
        Position editedAdminAssistant = new PositionBuilder(ADMIN_ASSISTANT)
                .withTitle(VALID_TITLE_ADMIN_ASSISTANT)
                .build();
        assertTrue(uniquePositionList.contains(editedAdminAssistant));
    }

    @Test
    public void add_nullPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePositionList.add(null));
    }

    @Test
    public void add_duplicatePosition_throwsDuplicatePositionException() {
        uniquePositionList.add(ADMIN_ASSISTANT);
        assertThrows(DuplicatePositionException.class, () -> uniquePositionList.add(ADMIN_ASSISTANT));
    }

    @Test
    public void setPosition_nullTargetPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePositionList.setPosition(null, ADMIN_ASSISTANT));
    }

    @Test
    public void setPosition_nullEditedPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePositionList.setPosition(ADMIN_ASSISTANT, null));
    }

    @Test
    public void setPosition_targetPositionNotInList_throwsPositionNotFoundException() {
        assertThrows(PositionNotFoundException.class, () ->
                uniquePositionList.setPosition(ADMIN_ASSISTANT, ADMIN_ASSISTANT));
    }

    @Test
    public void setPosition_editedPositionIsSamePosition_success() {
        uniquePositionList.add(ADMIN_ASSISTANT);
        uniquePositionList.setPosition(ADMIN_ASSISTANT, ADMIN_ASSISTANT);
        UniquePositionList expectedUniquePositionList = new UniquePositionList();
        expectedUniquePositionList.add(ADMIN_ASSISTANT);
        assertEquals(expectedUniquePositionList, uniquePositionList);
    }

    @Test
    public void setPosition_editedPositionHasSameIdentity_success() {
        uniquePositionList.add(ADMIN_ASSISTANT);
        Position editedAdminAssistant = new PositionBuilder(ADMIN_ASSISTANT).build();
        uniquePositionList.setPosition(ADMIN_ASSISTANT, editedAdminAssistant);
        UniquePositionList expectedUniquePositionList = new UniquePositionList();
        expectedUniquePositionList.add(editedAdminAssistant);
        assertEquals(expectedUniquePositionList, uniquePositionList);
    }

    @Test
    public void setPosition_editedPositionHasDifferentIdentity_success() {
        uniquePositionList.add(ADMIN_ASSISTANT);
        uniquePositionList.setPosition(ADMIN_ASSISTANT, BOOKKEEPER);
        UniquePositionList expectedUniquePositionList = new UniquePositionList();
        expectedUniquePositionList.add(BOOKKEEPER);
        assertEquals(expectedUniquePositionList, uniquePositionList);
    }

    @Test
    public void setPosition_editedPositionHasNonUniqueIdentity_throwsDuplicatePositionException() {
        uniquePositionList.add(ADMIN_ASSISTANT);
        uniquePositionList.add(BOOKKEEPER);
        assertThrows(DuplicatePositionException.class, () ->
                uniquePositionList.setPosition(ADMIN_ASSISTANT, BOOKKEEPER));
    }

    @Test
    public void remove_nullPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePositionList.remove(null));
    }

    @Test
    public void remove_positionDoesNotExist_throwsPositionNotFoundException() {
        assertThrows(PositionNotFoundException.class, () -> uniquePositionList.remove(ADMIN_ASSISTANT));
    }

    @Test
    public void remove_existingPosition_removesPosition() {
        uniquePositionList.add(ADMIN_ASSISTANT);
        uniquePositionList.remove(ADMIN_ASSISTANT);
        UniquePositionList expectedUniquePositionList = new UniquePositionList();
        assertEquals(expectedUniquePositionList, uniquePositionList);
    }

    @Test
    public void setPositions_nullUniquePositionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePositionList.setPositions((UniquePositionList) null));
    }

    @Test
    public void setPositions_uniquePositionList_replacesOwnListWithProvidedUniquePositionList() {
        uniquePositionList.add(ADMIN_ASSISTANT);
        UniquePositionList expectedUniquePositionList = new UniquePositionList();
        expectedUniquePositionList.add(BOOKKEEPER);
        uniquePositionList.setPositions(expectedUniquePositionList);
        assertEquals(expectedUniquePositionList, uniquePositionList);
    }

    @Test
    public void setPositions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePositionList.setPositions((List<Position>) null));
    }

    @Test
    public void setPositions_list_replacesOwnListWithProvidedList() {
        uniquePositionList.add(ADMIN_ASSISTANT);
        List<Position> positionList = Collections.singletonList(BOOKKEEPER);
        uniquePositionList.setPositions(positionList);
        UniquePositionList expectedUniquePositionList = new UniquePositionList();
        expectedUniquePositionList.add(BOOKKEEPER);
        assertEquals(expectedUniquePositionList, uniquePositionList);
    }

    @Test
    public void setPositions_listWithDuplicatePositions_throwsDuplicatePositionException() {
        List<Position> listWithDuplicatePositions = Arrays.asList(ADMIN_ASSISTANT, ADMIN_ASSISTANT);
        assertThrows(DuplicatePositionException.class, () ->
                uniquePositionList.setPositions(listWithDuplicatePositions));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniquePositionList.asUnmodifiableObservableList().remove(0));
    }
}
