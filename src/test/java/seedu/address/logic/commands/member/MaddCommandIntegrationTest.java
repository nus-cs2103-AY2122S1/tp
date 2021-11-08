package seedu.address.logic.commands.member;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMembers.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.member.Member;
import seedu.address.testutil.MemberBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code PaddCommand}.
 */
public class MaddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newMember_success() {
        Member validMember = new MemberBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addMember(validMember);
        assertCommandSuccess(new MaddCommand(validMember), model,
                String.format(MaddCommand.MESSAGE_SUCCESS, validMember), expectedModel);
    }

    @Test
    public void execute_duplicateMember_throwsCommandException() {
        Member memberInList = model.getAddressBook().getMemberList().get(0);
        assertCommandFailure(new MaddCommand(memberInList), model, MaddCommand.MESSAGE_DUPLICATE_MEMBER);
    }

}
