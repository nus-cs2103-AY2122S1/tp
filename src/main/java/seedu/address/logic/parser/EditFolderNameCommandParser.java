package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.EditFolderNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;

public class EditFolderNameCommandParser implements Parser<EditFolderNameCommand> {

    private static final String EDIT_COMMAND_SEPARATOR = "|";
    public static final String REGEX_FOR_MULTIPLE_FOLDER_SPLIT = "\\"
            + EDIT_COMMAND_SEPARATOR;

    /**
     * Parses the given {@code String} of arguments in the
     * context of the EditFolderNameCommand
     * and returns an EditFolderNameCommand object for execution.
     * @throws ParseException if the given {@code folderName} is invalid.
     */
    public EditFolderNameCommand parse(String args) throws ParseException {
        if (args.length() == 0) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditFolderNameCommand.MESSAGE_USAGE));
        }
        FolderName[] folderNames = extractIndividualFolders(args);
        assert folderNames.length == 2 : "Should have exactly 2 folders only!";

        return new EditFolderNameCommand(new Folder(folderNames[0]), new Folder(folderNames[1]));
    }

    /**
     * Segments the joined string of folder name into
     * individual {@code FolderName[]} objects.
     * @param folderStringNames {@code String} Inputs of multiple
     * folder names joined together.
     * @return An array of folder names {@code FolderName[]}
     * @throws ParseException if the 2 given {@code folderNames} are
     * of an invalid format.
     */
    private FolderName[] extractIndividualFolders(String folderStringNames) throws ParseException {
        int foldersLength = folderStringNames.length();
        String[] folders = folderStringNames.split(REGEX_FOR_MULTIPLE_FOLDER_SPLIT);

        boolean startsWithInvalidInput = folderStringNames.startsWith(EDIT_COMMAND_SEPARATOR);
        boolean endsWithInvalidInput = folderStringNames.startsWith(EDIT_COMMAND_SEPARATOR,
                foldersLength - 1);
        boolean isInvalidInputLength = folders.length != 2;
        if (startsWithInvalidInput || endsWithInvalidInput || isInvalidInputLength) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditFolderNameCommand.MESSAGE_USAGE));
        }

        FolderName[] allFolderNames = new FolderName[2];
        for (int k = 0; k < folders.length; k++) {
            FolderName currentFolder = ParserUtil.parseFolderName(folders[k]);
            allFolderNames[k] = currentFolder;
        }

        return allFolderNames;
    }

}
