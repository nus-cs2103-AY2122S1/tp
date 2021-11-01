package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteFolderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;

public class DeleteFolderCommandParser implements Parser<DeleteFolderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteFolderCommand
     * and returns an DeleteFolderCommand object for execution.
     * @throws ParseException if the given {@code folderName} is invalid.
     */
    public DeleteFolderCommand parse(String args) throws ParseException {
        if (args.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteFolderCommand.MESSAGE_USAGE));
        }
        FolderName folderName = ParserUtil.parseFolderName(args);
        return new DeleteFolderCommand(new Folder(folderName));
    }

}
