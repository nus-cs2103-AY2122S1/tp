package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeletePersonFromFolderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;

public class DeletePersonFromFolderCommandParserTest {

    private static String stringIndex = "1";
    private Folder testFolder1 = new Folder(new FolderName("TestFolder1"));

    @Test
    public void parse_properFolderAndIndexInput_success() throws ParseException {
        String successStringInput = " 1 >> TestFolder1";
        Index validIndex = ParserUtil.parseIndex(stringIndex, new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletePersonFromFolderCommand.MESSAGE_USAGE)));
        DeletePersonFromFolderCommand expectedDeletePersonFromFolderCommand =
                new DeletePersonFromFolderCommand(validIndex, testFolder1);
        DeletePersonFromFolderCommandParser deletePersonFromFolderCommandParser =
                new DeletePersonFromFolderCommandParser();
        assertParseSuccess(
                deletePersonFromFolderCommandParser,
                successStringInput,
                expectedDeletePersonFromFolderCommand);
    }

    @Test
    public void parse_properFolderAndIndexInputWithWrongSymbol_failure() {
        String successStringInput = " 1 | TestFolder1";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletePersonFromFolderCommand.MESSAGE_USAGE);
        DeletePersonFromFolderCommandParser deletePersonFromFolderCommandParser =
                new DeletePersonFromFolderCommandParser();
        assertParseFailure(
                deletePersonFromFolderCommandParser,
                successStringInput,
                expectedErrorMessage);
    }

    @Test
    public void parse_properFolderAndInvalidIndex_failure() {
        String successStringInput = " -1 >> TestFolder1";
        String expectedErrorMessage = ParserUtil.MESSAGE_INVALID_INDEX;
        DeletePersonFromFolderCommandParser deletePersonFromFolderCommandParser =
                new DeletePersonFromFolderCommandParser();
        assertParseFailure(
                deletePersonFromFolderCommandParser,
                successStringInput,
                expectedErrorMessage);
    }

    @Test
    public void parse_invalidFolderButValidIndex_failure() {
        String successStringInput = " 1 >>      ";
        String expectedErrorMessage = FolderName.MESSAGE_CONSTRAINTS;
        DeletePersonFromFolderCommandParser deletePersonFromFolderCommandParser =
                new DeletePersonFromFolderCommandParser();
        assertParseFailure(
                deletePersonFromFolderCommandParser,
                successStringInput,
                expectedErrorMessage);
    }

}
