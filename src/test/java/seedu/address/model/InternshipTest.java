package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.application.Application;
import seedu.address.model.application.exceptions.DuplicateApplicationException;
import seedu.address.testutil.ApplicationBuilder;

public class InternshipTest {

    private final Internship internship = new Internship();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internship.getApplicationList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internship.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInternship_replacesData() {
        Internship newData = getTypicalInternship();
        internship.resetData(newData);
        assertEquals(newData, internship);
    }

    @Test
    public void resetData_withDuplicateApplications_throwsDuplicateApplicationException() {
        // Two applications with the same identity fields
        Application editedAmazon = new ApplicationBuilder(AMAZON)
                .build();
        List<Application> newApplications = Arrays.asList(AMAZON, editedAmazon);
        InternshipStub newData = new InternshipStub(newApplications);

        assertThrows(DuplicateApplicationException.class, () -> internship.resetData(newData));
    }

    @Test
    public void hasApplication_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internship.hasApplication(null));
    }

    @Test
    public void hasApplication_applicationNotInInternship_returnsFalse() {
        assertFalse(internship.hasApplication(AMAZON));
    }

    @Test
    public void hasApplication_applicationInInternship_returnsTrue() {
        internship.addApplication(AMAZON);
        assertTrue(internship.hasApplication(AMAZON));
    }

    @Test
    public void hasApplication_applicationWithSameIdentityFieldsInInternship_returnsTrue() {
        internship.addApplication(AMAZON);
        Application editedApplication = new ApplicationBuilder(AMAZON)
                .build();
        assertTrue(internship.hasApplication(editedApplication));
    }

    @Test
    public void getApplicationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internship.getApplicationList().remove(0));
    }

    /**
     * A stub ReadOnlyInternship whose applications list can violate interface constraints.
     */
    private static class InternshipStub implements ReadOnlyInternship {
        private final ObservableList<Application> applications = FXCollections.observableArrayList();

        InternshipStub(Collection<Application> applications) {
            this.applications.setAll(applications);
        }

        @Override
        public ObservableList<Application> getApplicationList() {
            return applications;
        }
    }

}
