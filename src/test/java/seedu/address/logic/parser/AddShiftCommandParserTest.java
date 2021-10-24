package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddShiftCommand;
import seedu.address.model.person.Name;

public class AddShiftCommandParserTest {
    private AddShiftCommandParser parser = new AddShiftCommandParser();
    private static final LocalDate START_DATE = LocalDate.of(1, 1, 1);

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void prefix_missing_throwsParseException() {
        assertParseFailure(parser, "addShift", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddShiftCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "addShift d/monday-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void prefix_duplicate_throwsParseException() {
        assertParseFailure(parser, "addShift -i 1 n/testingName d/monday-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        AddShiftCommand expectedNameCommand = new AddShiftCommand(null, new Name("testing"),
                "monday-1", START_DATE);
        AddShiftCommand expectedIndexCommand = new AddShiftCommand(Index.fromOneBased(1), null,
                "monday-1", START_DATE);
        assertParseSuccess(parser, " -n testing d/monday-1", expectedNameCommand);
        assertParseSuccess(parser, " -i 1 d/monday-1", expectedIndexCommand);
    }
}
