package seedu.address.logic.commands.task;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMembers.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.member.Member;
import seedu.address.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for TlistCommand.
 */
class TlistCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_tlistSuccessfullyShown_showsSameList() {
        Index validMemberId = TypicalIndexes.INDEX_FIRST;
        Member targetMember = model.getFilteredMemberList().get(0);
        expectedModel.loadTaskList(targetMember);
        assertCommandSuccess(new TlistCommand(validMemberId), model,
                TlistCommand.MESSAGE_SUCCESS + " of " + targetMember.getName(), expectedModel);
    }
}
