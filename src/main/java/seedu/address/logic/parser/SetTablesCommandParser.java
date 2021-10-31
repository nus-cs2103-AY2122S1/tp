package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.logic.commands.SetTablesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.table.Table;

public class SetTablesCommandParser implements Parser<SetTablesCommand> {

    public static final String MESSAGE_INVALID_SIZE_OR_COUNT =
            "Ensure table size and number of tables are positive integers";
    /**
     * Parses the given {@code String} of arguments in the context of the SetTablesCommand
     * and returns a SetTablesCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetTablesCommand parse(String args) throws ParseException {

        // Trim leading and trailing whitespaces
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetTablesCommand.MESSAGE_USAGE));
        }

        // Split input by commas
        String[] inputSplit = trimmedArgs.split("\\s*,\\s*");
        if (inputSplit.length == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetTablesCommand.MESSAGE_USAGE));
        }

        // Split each already split argument by character 'x' representing multiply
        String[][] inputSplitWithCount = new String[inputSplit.length][];
        for (int i = 0; i < inputSplitWithCount.length; i++) {
            inputSplitWithCount[i] = inputSplit[i].split("\\s*x\\s*");
            if (!(inputSplitWithCount[i].length == 1 || inputSplitWithCount[i].length == 2)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetTablesCommand.MESSAGE_USAGE));
            }
        }

        // Convert input into list of tables
        List<Integer> result;
        try {
            result = flattenArray(inputSplitWithCount);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetTablesCommand.MESSAGE_USAGE));
        }

        return new SetTablesCommand(result);
    }

    private List<Integer> flattenArray(String[][] input) throws NumberFormatException, ParseException {
        ArrayList<Integer> listOfTableSizes = new ArrayList<>();
        for (String[] stringArray : input) {
            listOfTableSizes.addAll(flattenInnerArray(stringArray));
        }
        return listOfTableSizes;
    }

    private List<Integer> flattenInnerArray(String[] array) throws NumberFormatException, ParseException {
        assert array.length == 1 || array.length == 2;
        int tableSize = Integer.parseInt(array[0]);

        if (!Table.checkIfValidValue(tableSize)) {
            throw new ParseException(MESSAGE_INVALID_SIZE_OR_COUNT);
        }
        if (array.length == 1) {
            return Collections.singletonList(tableSize);
        } else { //array.length == 2
            int numberOfTables = Integer.parseInt(array[1]);
            if (!Table.checkIfValidValue(numberOfTables)) {
                throw new ParseException(MESSAGE_INVALID_SIZE_OR_COUNT);
            }
            ArrayList<Integer> result = new ArrayList<>();
            for (int i = 0; i < numberOfTables; i++) {
                result.add(tableSize);
            }
            return result;
        }
    }
}
