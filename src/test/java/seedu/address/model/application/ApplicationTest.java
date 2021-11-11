package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_AND_TIME_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_AND_TIME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENTS_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BYTEDANCE;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicationBuilder;

/**
 * Contains tests for the Application entity.
 */
public class ApplicationTest {

    @Test
    public void isSameApplication() {
        // same object -> returns true
        assertTrue(AMAZON.isSameApplication(AMAZON));

        // null -> returns false
        assertFalse(AMAZON.isSameApplication(null));

        // same name and position, all other attributes different -> returns true
        Application editedAmazon = new ApplicationBuilder(AMAZON).withDeadline(VALID_DEADLINE_BYTEDANCE)
                .build();
        assertTrue(AMAZON.isSameApplication(editedAmazon));

        // different name and position, all other attributes same -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withCompany(VALID_NAME_BYTEDANCE)
                .withPosition(VALID_POSITION_BYTEDANCE).build();
        assertFalse(AMAZON.isSameApplication(editedAmazon));

        // different name, all other attributes same -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withCompany(VALID_NAME_BYTEDANCE).build();
        assertFalse(AMAZON.isSameApplication(editedAmazon));

        // different position, all other attributes same -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withPosition(VALID_POSITION_BYTEDANCE).build();
        assertFalse(AMAZON.isSameApplication(editedAmazon));

        // name differs in case, all other attributes same -> returns false
        Application editedBytedance = new ApplicationBuilder(BYTEDANCE).withCompany(
                VALID_NAME_BYTEDANCE.toLowerCase()).build();
        assertFalse(BYTEDANCE.isSameApplication(editedBytedance));

        // position differs in case, all other attributes same -> returns false
        editedBytedance = new ApplicationBuilder(BYTEDANCE).withPosition(
                VALID_POSITION_BYTEDANCE.toLowerCase()).build();
        assertFalse(BYTEDANCE.isSameApplication(editedBytedance));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BYTEDANCE + " ";
        editedBytedance = new ApplicationBuilder(BYTEDANCE).withCompany(nameWithTrailingSpaces).build();
        assertFalse(BYTEDANCE.isSameApplication(editedBytedance));

        // name has trailing spaces, all other attributes same -> returns false
        String positionWithTrailingSpaces = VALID_POSITION_BYTEDANCE + " ";
        editedBytedance = new ApplicationBuilder(BYTEDANCE).withPosition(positionWithTrailingSpaces).build();
        assertFalse(BYTEDANCE.isSameApplication(editedBytedance));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Application amazonCopy = new ApplicationBuilder(AMAZON).build();
        assertTrue(AMAZON.equals(amazonCopy));

        // same object -> returns true
        assertTrue(AMAZON.equals(AMAZON));

        // null -> returns false
        assertFalse(AMAZON.equals(null));

        // different type -> returns false
        assertFalse(AMAZON.equals(5));

        // different application -> returns false
        assertFalse(AMAZON.equals(BYTEDANCE));

        // different name -> returns false
        Application editedAmazon = new ApplicationBuilder(AMAZON).withCompany(VALID_NAME_BYTEDANCE).build();
        assertFalse(AMAZON.equals(editedAmazon));

        // different position -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withPosition(VALID_POSITION_BYTEDANCE).build();
        assertFalse(AMAZON.equals(editedAmazon));

        // different deadline -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withDeadline(VALID_DEADLINE_BYTEDANCE).build();
        assertFalse(AMAZON.equals(editedAmazon));

        // different completion -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withCompletion(VALID_COMPLETION_BYTEDANCE).build();
        assertFalse(AMAZON.equals(editedAmazon));

        // different status -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withStatus(VALID_STATUS_BYTEDANCE).build();
        assertFalse(AMAZON.equals(editedAmazon));

        // different priority -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withPriority(VALID_PRIORITY_BYTEDANCE).build();
        assertFalse(AMAZON.equals(editedAmazon));

        // different requirements -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withRequirements(VALID_REQUIREMENTS_BYTEDANCE).build();
        assertFalse(AMAZON.equals(editedAmazon));

        // different interview date and times -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON)
                .withInterviewDateAndTime(VALID_INTERVIEW_DATE_AND_TIME_BYTEDANCE).build();
        assertFalse(AMAZON.equals(editedAmazon));
    }

    @Test
    public void getApplicationInfo_success() {
        Application editedAmazon = new ApplicationBuilder(AMAZON)
                .withCompletion("Completed").withStatus("Accepted")
                .withInterviewDateAndTime(VALID_INTERVIEW_DATE_AND_TIME_AMAZON).build();

        assertTrue(editedAmazon.isCompleted());

        assertTrue(editedAmazon.isAccepted());

        assertFalse(editedAmazon.isRejected());

        assertTrue(editedAmazon.hasInterviewTime());
    }

    @Test
    public void applicationToString_success() {
        String bytedanceString = "Company: ByteDance; Position: Web Developer; Deadline: Nov 29 2021; "
                + "Completion: Completed; Decision: Rejected; Priority: High; Requirements: [CV]; "
                + "Interview Date and Time: [Dec 12 2021, 05:30]";
        assertEquals(BYTEDANCE.toString(), bytedanceString);
    }

    @Test
    public void toHashCode_success() {
        Application amazonCopy = new ApplicationBuilder(AMAZON).build();
        assertEquals(AMAZON.hashCode(), amazonCopy.hashCode());
    }

}
