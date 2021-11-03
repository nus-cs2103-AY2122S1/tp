package seedu.unify.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

import seedu.unify.logic.commands.FindCommand;
import seedu.unify.logic.parser.exceptions.ParseException;
import seedu.unify.model.task.NameContainsKeywordsPredicate;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.TaskContainsDatePredicate;
import seedu.unify.model.task.TaskContainsTagPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs;
        ArrayList<Predicate<Task>> predicates = new ArrayList<>();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            String date = argMultimap.getValue(PREFIX_DATE).get();
            predicates.add(new TaskContainsDatePredicate(ParserUtil.parseDate(date).getLocalDate()));
            args = StringUtils.remove(args, PREFIX_DATE + date);
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
            predicates.add(new TaskContainsTagPredicate(ParserUtil.parseTags(tags)));
            for (String tag : tags) {
                args = StringUtils.remove(args, PREFIX_TAG + tag);
            }
        }

        trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        //noinspection unchecked
        return new FindCommand(combinePredicate(predicates));
    }

    private Predicate<Task> combinePredicate(ArrayList<Predicate<Task>> predicates) {
        return predicates.stream().reduce(Predicate<Task>::and).orElse(task->true);
    }

}
