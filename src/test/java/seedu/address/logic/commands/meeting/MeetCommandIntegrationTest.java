package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBookObjects.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code MeetCommand}.
 */
public class MeetCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newMeeting_success() {
        Meeting validMeeting = new MeetingBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addMeeting(validMeeting);

        assertCommandSuccess(new MeetCommand(validMeeting), model,
            String.format(MeetCommand.MESSAGE_SUCCESS, validMeeting), expectedModel);
    }

    @Test
    public void execute_conflictingMeeting_throwsCommandException() {
        Meeting meetingInList = model.getAddressBook().getMeetingList().get(0);
        assertCommandFailure(new MeetCommand(meetingInList), model, MeetCommand.MESSAGE_MEETING_CONFLICT);
    }
}
