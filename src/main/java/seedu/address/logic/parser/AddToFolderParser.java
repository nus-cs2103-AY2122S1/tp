package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddToFolderCommand;
import seedu.address.logic.commands.CreateFolderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;

import java.util.List;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class AddToFolderParser implements Parser<AddToFolderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddToFolderCommand
     * and returns an AddToFolderCommand object for execution.
     * @throws ParseException if the given {@code folderName} is invalid.
     */
    public AddToFolderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, new Prefix(""));
        List<String> allValues = argMultimap.getAllValues(new Prefix(""));
        System.out.println(allValues);
        if (allValues.size() <= 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToFolderCommand.MESSAGE_USAGE));
        }
        Index index = extractContactIndex(allValues);
        FolderName folderName = extractFolderName(allValues);
        return new AddToFolderCommand(index,folderName);
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
            String curr = allValues.get(i).trim();
            if (!curr.equals(">>")) {
                stringBuilder.append(curr);
            }
        }
        return ParserUtil.parseFolderName(stringBuilder.toString());
    }

    /**
     * Extracts the elements relevant to the index from {@code List} of inputs
     * and returns an Index {@code Index}
     * @param allValues {@code List} of inputs
     * @return folder name {@code Index}
     * @throws ParseException if the given {@code folderName} is invalid.
     */
    private Index extractContactIndex(List<String> allValues) throws ParseException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < allValues.size(); i++) {
            try {
                int intValue = Integer.parseInt(allValues.get(i));
                stringBuilder.append(allValues.get(i));
                allValues.remove(i);
            } catch (NumberFormatException e) {
//                System.out.println("Input String cannot be parsed to Integer.");
                continue;
            }
        }
        return ParserUtil.parseIndex(stringBuilder.toString());
    }
}