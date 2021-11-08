package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.DeleteCommand;
import seedu.fast.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        Index[] result;
        if (isRangeInput(args)) {
            String[] rangedIndex = spiltRangeInput(args);
            result = parseRangeInput(rangedIndex);
        } else {
            String[] multipleIndexes = args.trim().split(" ");
            result = checkAndParseArgs(multipleIndexes);
        }

        return new DeleteCommand(result);
    }

    private Index[] checkAndParseArgs(String[] args) throws ParseException {
        Index[] result = new Index[args.length];
        int count = 0;

        for (String indexString : args) {
            try {
                Index index = ParserUtil.parseIndex(indexString);
                result[count++] = index;
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
            }
        }

        return result;
    }

    private boolean isRangeInput(String args) {
        String testArgs = args.trim().replace(" ", "");
        return testArgs.length() >= 3 && countDash(testArgs) == 1;
    }

    // should return a string array of length 2
    private String[] spiltRangeInput(String args) {
        String[] rangedIndexes = args.trim().split("-");
        return rangedIndexes;
    }

    private Index[] parseRangeInput(String[] args) throws ParseException {
        assert args.length == 2 : "Invalid range input";

        Index[] result;
        Index startIndex;
        Index endIndex;
        try {
            startIndex = ParserUtil.parseIndex(args[0]);
            endIndex = ParserUtil.parseIndex(args[1]);

            if (!isValidRange(startIndex, endIndex)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }

            result = new Index[endIndex.getOneBased() - startIndex.getOneBased() + 1];
        } catch (ParseException | NegativeArraySizeException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), e);
        }

        return generateIndexesInRange(result, startIndex, endIndex);
    }

    private Index[] generateIndexesInRange(Index[] result, Index startIndex, Index endIndex) {
        int count = 0;
        for (int i = startIndex.getOneBased(); i <= endIndex.getOneBased(); i++) {
            result[count++] = Index.fromOneBased(i);
        }

        return result;
    }

    private long countDash(String str) {
        long count = str.chars().filter(c -> c == '-').count();
        return count;
    }

    private boolean isValidRange(Index start, Index end) {
        return end.getOneBased() - start.getOneBased() >= 0;
    }
}
