package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeletePersonFromFolderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;

public class DeletePersonFromFolderCommandParser
        implements Parser<DeletePersonFromFolderCommand> {

    /**
     * Parses the given {@code String} of arguments in the
     * context of the DeletePersonFromFolderCommand
     * and returns an DeletePersonFromFolderCommand object for execution.
     * @throws ParseException if the given index and folder name is invalid.
     */
    public DeletePersonFromFolderCommand parse(String args) throws ParseException {
        if (args.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeletePersonFromFolderCommand.MESSAGE_USAGE));
        }
        String[] inputsToConvertToCommand = extractIndividualFolderAndIndex(args);
        assert inputsToConvertToCommand.length == 2
                : "Should have exactly 2 inputs of Index and Folder strings only!";

        Index indexToRemove = extractIndex(inputsToConvertToCommand);
        Folder folderToRemoveFrom = extractFolder(inputsToConvertToCommand);

        return new DeletePersonFromFolderCommand(indexToRemove, folderToRemoveFrom);
    }

    /**
     * Sieves out the folder name string and creates and folder object.
     * @param inputsToConvertToCommand String[] of index and folder name as string.
     * @return Folder as a new folder object created
     * @throws ParseException if given folder name is invalid.
     */
    private Folder extractFolder(String[] inputsToConvertToCommand) throws ParseException {
        String folderNameString = inputsToConvertToCommand[1];
        FolderName currentFolder = ParserUtil.parseFolderName(folderNameString);
        return new Folder(currentFolder);
    }

    /**
     * Sieves out the index string and creates and Index object.
     * @param inputsToConvertToCommand String[] of index and folder name as string.
     * @return Index object.
     * @throws ParseException if the given index given is invalid.
     */
    private Index extractIndex(
            String[] inputsToConvertToCommand) throws ParseException {
        String indexString = inputsToConvertToCommand[0];
        return ParserUtil.parseIndex(indexString, new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletePersonFromFolderCommand.MESSAGE_USAGE)));
    }

    /**
     * Segments the joined string of folder name and index into
     * individual {@code String[]} objects.
     * @param indexAndFolderJoined {@code String} Inputs of
     * folder names and index joined together.
     * @return An array of Index and folder name string {@code String[]}
     * @throws ParseException if either index or folder names are
     * of an invalid format.
     */
    private String[] extractIndividualFolderAndIndex(
            String indexAndFolderJoined) throws ParseException {
        int inputsLength = indexAndFolderJoined.length();
        String[] inputsOfIndexAndFolder = indexAndFolderJoined
                .split(DeletePersonFromFolderCommand.COMMAND_IDENTIFIER);

        boolean startsWithInvalidInput = indexAndFolderJoined
                .startsWith(DeletePersonFromFolderCommand.COMMAND_IDENTIFIER);
        boolean endsWithInvalidInput = indexAndFolderJoined
                .startsWith(DeletePersonFromFolderCommand.COMMAND_IDENTIFIER,
                        inputsLength - 2);
        boolean isInvalidInputLength = inputsOfIndexAndFolder.length != 2;

        if (startsWithInvalidInput || endsWithInvalidInput || isInvalidInputLength) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeletePersonFromFolderCommand.MESSAGE_USAGE));
        }

        return inputsOfIndexAndFolder;
    }

}
