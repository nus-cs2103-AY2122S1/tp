package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Member;
import seedu.address.testutil.MemberBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddMemberCommand}.
 */
public class AddCommandIntegrationTest {

    /**
     * Stands for model defined in backend.
     */
    private Model model;

    /**
     * Sets up the modelManger.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());
    }

    /**
     * Checks if adding mew member will succeed.
     */
    @Test
    public void execute_newMember_success() {
        Member validMember = new MemberBuilder().build();

        Model expectedModel = new ModelManager(new Account(), model.getEzFoodie(), new UserPrefs());
        expectedModel.addMember(validMember);

        assertCommandSuccess(new AddMemberCommand(validMember), model,
                String.format(AddMemberCommand.MESSAGE_SUCCESS, validMember), expectedModel);
    }

    /**
     * Checks if adding duplicate member will throw CommandException.
     */
    @Test
    public void execute_duplicateMember_throwsCommandException() {
        Member memberInList = model.getEzFoodie().getMemberList().get(0);
        assertCommandFailure(new AddMemberCommand(memberInList), model, AddMemberCommand.MESSAGE_DUPLICATE_MEMBER);
    }
}
