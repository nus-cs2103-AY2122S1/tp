package seedu.address.logic.parser.abcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.abcommand.AbCommand.MESSAGE_ADDRESSBOOK_NOT_VALID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.abcommand.AbDeleteCommand;

public class AbDeleteCommandParserTest {
    private final AbDeleteCommandParser parser = new AbDeleteCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AbDeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidFileName_throwsParseException() {
        String invalidFileName = "dddd<>\0ddd";
        assertParseFailure(parser, invalidFileName, String.format(MESSAGE_ADDRESSBOOK_NOT_VALID, invalidFileName));
    }

    @Test
    public void parse_validArg_returnAbDeleteCommand() {
        String input = "addressbook";
        Path filePath = Path.of(FileUtil.convertToAddressBookPathString(input, Path.of("data")));
        AbDeleteCommand expectedAbDeleteCommand = new AbDeleteCommand(input, filePath);
        assertParseSuccess(parser, " " + input + "  ", expectedAbDeleteCommand);
    }
}
