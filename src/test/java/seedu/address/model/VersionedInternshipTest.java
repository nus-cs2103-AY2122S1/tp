package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;
import static seedu.address.testutil.TypicalApplications.GRAB;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.application.Application;


public class VersionedInternshipTest {
    private List<Application> one = new ArrayList<>();
    private List<Application> two = new ArrayList<>();
    private List<Application> three = new ArrayList<>();

    private Internship stateOne = new Internship();
    private Internship stateTwo = new Internship();
    private Internship stateThree = new Internship();

    private VersionedInternship oneStateInternship;
    private VersionedInternship twoStatesInternship;
    private VersionedInternship threeStatesInternship;

    @BeforeEach
    public void setUp() {
        one.add(AMAZON);

        two.add(AMAZON);
        two.add(BYTEDANCE);

        three.add(AMAZON);
        three.add(BYTEDANCE);
        three.add(GRAB);

        stateOne.setApplications(one);
        stateTwo.setApplications(two);
        stateThree.setApplications(three);

        oneStateInternship = new VersionedInternship(stateOne);

        twoStatesInternship = new VersionedInternship(stateOne);
        twoStatesInternship.commit(stateTwo);

        threeStatesInternship = new VersionedInternship(stateOne);
        threeStatesInternship.commit(stateTwo);
        threeStatesInternship.commit(stateThree);
    }

    @Test
    public void constructor_success() {
        assertEquals(stateOne, new VersionedInternship(stateOne).getCurrentVersion());
    }


    @Test
    public void commit_success() {
        //without purging
        VersionedInternship versionedInternship = new VersionedInternship(new Internship());
        versionedInternship.commit(stateOne);
        assertEquals(stateOne, versionedInternship.getCurrentVersion());

        //with purging
        versionedInternship.commit(stateTwo);
        versionedInternship.shiftCurrentStatePointer(-1);
        versionedInternship.commit(stateThree);
        assertEquals(stateThree, versionedInternship.getCurrentVersion());
    }

    @Test
    public void undo_success() {
        twoStatesInternship.undo();
        assertEquals(oneStateInternship, twoStatesInternship);
    }

    @Test
    public void redo_success() {
        VersionedInternship initial = new VersionedInternship(stateOne);
        initial.commit(stateTwo);
        initial.undo();
        initial.redo();
        assertEquals(twoStatesInternship, initial);
    }

    @Test
    public void test_canUndo() {
        assertFalse(oneStateInternship.canUndo());
        assertTrue(twoStatesInternship.canUndo());
    }

    @Test
    public void test_canRedo() {
        assertFalse(twoStatesInternship.canRedo());
        twoStatesInternship.undo();
        assertTrue(twoStatesInternship.canRedo());
    }

    @Test
    public void getCurrentVersion_success() {
        assertEquals(stateOne, oneStateInternship.getCurrentVersion());
    }

}
