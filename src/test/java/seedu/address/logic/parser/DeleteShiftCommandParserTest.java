package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.DeleteShiftCommand;
import seedu.address.model.person.Name;

public class DeleteShiftCommandParserTest {

    private final DeleteShiftCommandParser parser = new DeleteShiftCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void prefix_missing_throwsParseException() {
        assertParseFailure(parser, "deleteShift", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteShiftCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "deleteShift d/monday-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void prefix_duplicate_throwsParseException() {
        assertParseFailure(parser, "deleteShift -i 1 -n Alice Pauline d/monday-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        LocalDate[] dates = DateTimeUtil.getDisplayedDateArray();
        DeleteShiftCommand expectedNameCommand = new DeleteShiftCommand(null, new Name("testing"),
                "monday-1", dates[0], dates[1]);
        DeleteShiftCommand expectedIndexCommand = new DeleteShiftCommand(Index.fromOneBased(1), null,
                "monday-1", dates[0], dates[1]);
        assertParseSuccess(parser, " -n testing d/monday-1", expectedNameCommand);
        assertParseSuccess(parser, " -i 1 d/monday-1", expectedIndexCommand);
    }
}
