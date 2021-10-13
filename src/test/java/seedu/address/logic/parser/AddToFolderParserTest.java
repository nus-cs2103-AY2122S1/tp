package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOLDER_NAME_CCA;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddToFolderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.folder.FolderName;

class AddToFolderParserTest {

    private AddToFolderParser parser = new AddToFolderParser();

    @Test
    public void parse_allFieldsPresent_success() {
        FolderName expectedFolder = new FolderName(VALID_FOLDER_NAME_CCA);
        AddToFolderCommand expectedAddToFolderCommand = new AddToFolderCommand(INDEX_FIRST_PERSON,
                expectedFolder);
        CommandParserTestUtil.assertParseSuccess(parser,
                " 1 >> " + VALID_FOLDER_NAME_CCA,
                expectedAddToFolderCommand);
    }

    @Test
    public void parse_folderNameWithWhitespace_success() {
        FolderName expectedFolder = new FolderName(VALID_FOLDER_NAME_CCA + " " + VALID_FOLDER_NAME_CCA);
        AddToFolderCommand expectedAddToFolderCommand = new AddToFolderCommand(INDEX_FIRST_PERSON,
                expectedFolder);
        CommandParserTestUtil.assertParseSuccess(parser,
                " 1 >> " + VALID_FOLDER_NAME_CCA + " " + VALID_FOLDER_NAME_CCA,
                expectedAddToFolderCommand);
    }

    @Test
    public void parse_missingFolderName_failure() {
        assertThrows(ParseException.class, () -> parser.parse(" 1 >> "));
    }

    @Test
    public void parse_missingIndex_failure() {
        assertThrows(ParseException.class, () -> parser.parse(" >> CS2103"));
    }

    @Test
    public void parse_invalidSyntax_failure() {
        assertThrows(ParseException.class, () -> parser.parse(" > CS2103"));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        assertThrows(ParseException.class, () -> parser.parse(" "));
    }

    @Test
    public void parse_nullValue_failure() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));

    }
}
