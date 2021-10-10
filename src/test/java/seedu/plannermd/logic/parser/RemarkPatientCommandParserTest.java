package seedu.plannermd.logic.parser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.remarkcommand.RemarkPatientCommand;
import seedu.plannermd.model.person.Remark;

class RemarkPatientCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkPatientCommand.MESSAGE_USAGE);

    private RemarkPatientCommandParser parser = new RemarkPatientCommandParser();

    @Test
    public void parse_validArgs_returnsRemarkCommand() {
        //Non-empty remark
        assertParseSuccess(parser, "1" + REMARK_DESC_AMY,
                new RemarkPatientCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK_AMY)));

        //empty remark
        assertParseSuccess(parser, "1 " + PREFIX_REMARK,
                new RemarkPatientCommand(INDEX_FIRST_PERSON, new Remark("")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //remark not prefiexed with delimitter
        assertParseFailure(parser, "1 " + VALID_REMARK_AMY, MESSAGE_INVALID_FORMAT);

        //remark prefiexed with invalid delimitter
        assertParseFailure(parser, "1 i/" + VALID_REMARK_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingFields_throwsParseException() {
        // no remark specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index specified
        assertParseFailure(parser, REMARK_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // no index and no remark specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        // negative index
        assertParseFailure(parser, "-5" + REMARK_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + REMARK_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }
}
