package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.CreateFolderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;

public class CreateFolderCommandParser implements Parser<CreateFolderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateFolderCommand
     * and returns an CreateFolderCommand object for execution.
     * @throws ParseException if the given {@code folderName} is invalid.
     */
    public CreateFolderCommand parse(String args) throws ParseException {
        if (args.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateFolderCommand.MESSAGE_USAGE));
        }
        FolderName folderName = ParserUtil.parseFolderName(args);
        return new CreateFolderCommand(new Folder(folderName));
    }

}
