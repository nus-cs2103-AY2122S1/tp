package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOLDER_NAME_CCA;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateFolderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;

class CreateFolderCommandParserTest {

    @Test
    public void parse_allFieldsPresent_success() {
        Folder expectedFolder = new Folder(new FolderName(VALID_FOLDER_NAME_CCA));
        CreateFolderCommand expectedCreateFolderCommand = new CreateFolderCommand(expectedFolder);
        CreateFolderCommandParser createFolderCommandParser = new CreateFolderCommandParser();

        CommandParserTestUtil.assertParseSuccess(createFolderCommandParser,
                " " + VALID_FOLDER_NAME_CCA,
                expectedCreateFolderCommand);
    }

    @Test
    public void parse_folderNameWithWhitespace_success() {
        Folder expectedFolder = new Folder(new FolderName(VALID_FOLDER_NAME_CCA + " " + VALID_FOLDER_NAME_CCA));
        CreateFolderCommand expectedCreateFolderCommand = new CreateFolderCommand(expectedFolder);
        CreateFolderCommandParser createFolderCommandParser = new CreateFolderCommandParser();
        CommandParserTestUtil.assertParseSuccess(createFolderCommandParser,
                " " + VALID_FOLDER_NAME_CCA + " " + VALID_FOLDER_NAME_CCA,
                expectedCreateFolderCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        CreateFolderCommandParser createFolderCommandParser = new CreateFolderCommandParser();
        assertThrows(ParseException.class, () -> createFolderCommandParser.parse(" "));
    }

    @Test
    public void parse_nullValue_failure() {
        CreateFolderCommandParser createFolderCommandParser = new CreateFolderCommandParser();
        assertThrows(NullPointerException.class, () -> createFolderCommandParser.parse(null));
    }
}
