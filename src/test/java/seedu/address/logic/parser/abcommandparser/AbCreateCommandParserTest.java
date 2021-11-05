package seedu.address.logic.parser.abcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.abcommand.AbCommand.MESSAGE_ADDRESSBOOK_NOT_VALID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.abcommand.AbCreateCommand;

public class AbCreateCommandParserTest {
    private final AbCreateCommandParser parser = new AbCreateCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AbCreateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidFileName_throwsParseException() {
        String invalidFileName = "dddd<>\0ddd";
        assertParseFailure(parser, invalidFileName, String.format(MESSAGE_ADDRESSBOOK_NOT_VALID, invalidFileName));
    }

    @Test
    public void parse_validArg_returnAbCreateCommand() {
        String input = "addressbook";
        Path filePath = Path.of(FileUtil.convertToAddressBookPathString(input, Path.of("data")));
        AbCreateCommand expectedAbCreateCommand = new AbCreateCommand(input, filePath);
        assertParseSuccess(parser, " " + input + "  ", expectedAbCreateCommand);
    }
}
