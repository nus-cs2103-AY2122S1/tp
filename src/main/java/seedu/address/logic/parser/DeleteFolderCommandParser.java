package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteFolderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;

import java.util.List;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DeleteFolderCommandParser implements Parser<DeleteFolderCommand>  {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteFolderCommand
     * and returns an DeleteFolderCommand object for execution.
     * @throws ParseException if the given {@code folderName} is invalid.
     */
    public DeleteFolderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, new Prefix(""));
        List<String> allValues = argMultimap.getAllValues(new Prefix(""));
        if (allValues.size() <= 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteFolderCommand.MESSAGE_USAGE));
        }
        FolderName folderName = extractFolderName(allValues);

        return new DeleteFolderCommand(new Folder(folderName));
    }

    /**
     * Extracts the elements relevant to the folder name from {@code List} of inputs
     * and returns a folder name {@code String}
     * @param allValues {@code List} of inputs
     * @return folder name {@code String}
     * @throws ParseException if the given {@code folderName} is invalid.
     */
    private FolderName extractFolderName(List<String> allValues) throws ParseException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < allValues.size(); i++) {
            if (i > 1) {
                stringBuilder.append(" ").append(allValues.get(i));
                continue;
            }
            stringBuilder.append(allValues.get(i));
        }
        return ParserUtil.parseFolderName(stringBuilder.toString());
    }

}
