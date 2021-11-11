package seedu.intern.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.intern.testutil.Assert.assertThrows;
import static seedu.intern.testutil.TypicalApplicants.ALICE;
import static seedu.intern.testutil.TypicalApplicants.getTypicalInternWatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.exceptions.DuplicateApplicantException;
import seedu.intern.testutil.ApplicantBuilder;

public class InternWatcherTest {

    private final InternWatcher internWatcher = new InternWatcher();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internWatcher.getApplicantList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internWatcher.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInternWatcher_replacesData() {
        InternWatcher newData = getTypicalInternWatcher();
        internWatcher.resetData(newData);
        assertEquals(newData, internWatcher);
    }

    @Test
    public void resetData_withDuplicateApplicants_throwsDuplicateApplicantException() {
        // Two applicants with the same identity fields
        Applicant editedAlice = new ApplicantBuilder(ALICE).withSkills(VALID_SKILL_JAVA)
                .build();
        List<Applicant> newApplicants = Arrays.asList(ALICE, editedAlice);
        InternWatcherStub newData = new InternWatcherStub(newApplicants);

        assertThrows(DuplicateApplicantException.class, () -> internWatcher.resetData(newData));
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internWatcher.hasApplicant(null));
    }

    @Test
    public void hasApplicant_applicantNotInInternWatcher_returnsFalse() {
        assertFalse(internWatcher.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantInInternWatcher_returnsTrue() {
        internWatcher.addApplicant(ALICE);
        assertTrue(internWatcher.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantWithSameIdentityFieldsInInternWatcher_returnsTrue() {
        internWatcher.addApplicant(ALICE);
        Applicant editedAlice = new ApplicantBuilder(ALICE).withSkills(VALID_SKILL_JAVA)
                .build();
        assertTrue(internWatcher.hasApplicant(editedAlice));
    }

    @Test
    public void getApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internWatcher.getApplicantList().remove(0));
    }

    /**
     * A stub ReadOnlyInternWatcher whose applicants list can violate interface constraints.
     */
    private static class InternWatcherStub implements ReadOnlyInternWatcher {
        private final ObservableList<Applicant> applicants = FXCollections.observableArrayList();

        InternWatcherStub(Collection<Applicant> applicants) {
            this.applicants.setAll(applicants);
        }

        @Override
        public ObservableList<Applicant> getApplicantList() {
            return applicants;
        }
    }

}
