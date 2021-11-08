package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteFolderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;

public class DeleteFolderCommandParserTest {

    @Test
    public void parse_allFieldsPresent_success() {
        String testFolderName = "TEST1";
        Folder expectedFolder = new Folder(new FolderName(testFolderName));
        DeleteFolderCommand expectedDeleteFolderCommand = new DeleteFolderCommand(expectedFolder);
        DeleteFolderCommandParser deleteFolderCommandParser = new DeleteFolderCommandParser();

        assertParseSuccess(deleteFolderCommandParser,
                " " + testFolderName,
                expectedDeleteFolderCommand);
    }


    @Test
    public void parse_folderNameWithWhitespace_success() {
        String testFolderNameWithSpace = "TEST 1" + " " + "TEST 1";
        Folder expectedFolder = new Folder(new FolderName(testFolderNameWithSpace));
        DeleteFolderCommand expectedDeleteFolderCommand = new DeleteFolderCommand(expectedFolder);
        DeleteFolderCommandParser deleteFolderCommandParser = new DeleteFolderCommandParser();
        CommandParserTestUtil.assertParseSuccess(deleteFolderCommandParser,
                " " + testFolderNameWithSpace,
                expectedDeleteFolderCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        DeleteFolderCommandParser deleteFolderCommandParser = new DeleteFolderCommandParser();
        assertThrows(ParseException.class, () -> deleteFolderCommandParser.parse(" "));
    }

    @Test
    public void parse_nullValue_failure() {
        DeleteFolderCommandParser deleteFolderCommandParser = new DeleteFolderCommandParser();
        assertThrows(NullPointerException.class, () -> deleteFolderCommandParser.parse(null));
    }

}

