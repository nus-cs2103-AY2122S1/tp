package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.ExportCommandAll;
import seedu.address.logic.commands.ExportCommandIndex;

public class ExportCommandParserTest {

    private ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_validArgs_returnsExportCommandIndex() {
        assertParseSuccess(parser, "1", new ExportCommandIndex(INDEX_FIRST_CONTACT));
    }

    @Test
    public void parse_validArgs_returnsExportCommandAll() {
        assertParseSuccess(parser, "", new ExportCommandAll());
    }

    @Test
    public void parse_invalidArgsType_throwsParseException() {
        assertParseFailure(parser, "AIRZONE",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsIndex_throwsParseException() {
        assertParseFailure(parser, "-1",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
    }
}
