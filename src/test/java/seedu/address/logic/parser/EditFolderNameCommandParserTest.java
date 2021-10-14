package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.folder.FolderName.MESSAGE_CONSTRAINTS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditFolderNameCommand;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;

public class EditFolderNameCommandParserTest {

    private Folder TestFolder1 = new Folder(new FolderName("TestFolder1"));
    private Folder TestFolder2 = new Folder(new FolderName("TestFolder2"));
    private Folder TestFolder3 = new Folder(new FolderName("TestFolder3"));
    private Folder TestFolder4 = new Folder(new FolderName("TestFolder4"));

    @Test
    public void parse_properFolderInput_success() {
        String successStringInput = " TestFolder1 | TestFolder2";
        EditFolderNameCommand expectedEditFolderNameCommand = new EditFolderNameCommand(
                TestFolder1, TestFolder2);
        EditFolderNameCommandParser editFolderNameCommandParser = new EditFolderNameCommandParser();
        assertParseSuccess(
                editFolderNameCommandParser,
                successStringInput,
                expectedEditFolderNameCommand);
    }

    @Test
    public void parse_folderSeparatorAtStart_failure() {
        String failureStringInput = " | TestFolder1 | TestFolder2";
        String expectedFailureMessage = String.format(
                MESSAGE_INVALID_COMMAND_FORMAT,
                EditFolderNameCommand.MESSAGE_USAGE);
        EditFolderNameCommandParser editFolderNameCommandParser = new EditFolderNameCommandParser();
        assertParseFailure(
                editFolderNameCommandParser,
                failureStringInput,
                expectedFailureMessage);
    }

    @Test
    public void parse_multipleFolderNameSpecified_failure() {
        String failureStringInput = " TestFolder1 | TestFolder2 | TestFolder3";
        String expectedFailureMessage = String.format(
                MESSAGE_INVALID_COMMAND_FORMAT,
                EditFolderNameCommand.MESSAGE_USAGE);
        EditFolderNameCommandParser editFolderNameCommandParser = new EditFolderNameCommandParser();
        assertParseFailure(
                editFolderNameCommandParser,
                failureStringInput,
                expectedFailureMessage);
    }

    @Test
    public void parse_blankFolderNameSpecified_failure() {
        String failureStringInput1 = "        | TestFolder2 ";
        String failureStringInput2 = " TestFolder2 |       ";
        String failureStringInput3 = "        |       ";

        String expectedFailureMessage = String.format(MESSAGE_CONSTRAINTS);
        EditFolderNameCommandParser editFolderNameCommandParser = new EditFolderNameCommandParser();
        assertParseFailure(
                editFolderNameCommandParser,
                failureStringInput1,
                expectedFailureMessage);

        assertParseFailure(
                editFolderNameCommandParser,
                failureStringInput2,
                expectedFailureMessage);

        assertParseFailure(
                editFolderNameCommandParser,
                failureStringInput3,
                expectedFailureMessage);
    }

}
