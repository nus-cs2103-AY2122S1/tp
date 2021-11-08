package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.candidate.RemarkCandidateCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Remark;

class RemarkCandidateCommandParserTest {

    private RemarkCandidateCommandParser parser = new RemarkCandidateCommandParser();
    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        assertParseFailure(parser, model.getFilteredPersonList().size()
                + VALID_REMARK_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ParserUtil.MESSAGE_INVALID_INDEX) + RemarkCandidateCommand.MESSAGE_USAGE);
    }

    @Test
    public void execute_validPersonIndex_success() {
        Index index = Index.fromOneBased(model.getFilteredPersonList().size() - 1);
        RemarkCandidateCommand expectedCommand = new RemarkCandidateCommand(index, new Remark(VALID_REMARK_BOB));
        String userInput = " " + (index.getOneBased()) + " " + PREFIX_REMARK + VALID_REMARK_BOB;
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
