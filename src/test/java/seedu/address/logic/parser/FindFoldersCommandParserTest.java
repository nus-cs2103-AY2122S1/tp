package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindFoldersCommand;
import seedu.address.model.folder.FolderNameContainsKeywordsPredicate;

public class FindFoldersCommandParserTest {

    private FindFoldersCommandParser parser = new FindFoldersCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        String expectedParseException = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindFoldersCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "     ", expectedParseException);
        assertParseFailure(parser, "-folders -flags -ignore", expectedParseException);
    }

    @Test
    public void parse_validArgs_returnsFindFoldersCommand() {
        // no leading and trailing whitespaces
        FindFoldersCommand expectedFindFoldersCommand =
                new FindFoldersCommand(new FolderNameContainsKeywordsPredicate(Arrays.asList("CS2103", "CCA")));
        assertParseSuccess(parser, "CS2103 CCA", expectedFindFoldersCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2103 \n \t CCA  \t", expectedFindFoldersCommand);
    }

}
