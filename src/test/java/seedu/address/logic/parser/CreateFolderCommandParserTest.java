package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.CreateFolderCommand;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;

import static seedu.address.logic.commands.CommandTestUtil.VALID_FOLDER_NAME_CCA;

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

}