package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContainsKeywordsPredicate;
import seedu.address.model.person.ContainsKeywordsPredicate.PersonField;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindCommand and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_TAG);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        FindConditions conditions = new FindConditions();
        conditions.put(PersonField.NAME, argMultimap.getAllValues(PREFIX_NAME));
        conditions.put(PersonField.PHONE, argMultimap.getAllValues(PREFIX_PHONE));
        conditions.put(PersonField.EMAIL, argMultimap.getAllValues(PREFIX_EMAIL));
        conditions.put(PersonField.ADDRESS, argMultimap.getAllValues(PREFIX_ADDRESS));
        conditions.put(PersonField.TAG, argMultimap.getAllValues(PREFIX_TAG));

        return new FindCommand(conditions);
    }

    private static class FindConditions implements Predicate<Person> {
        private final Map<PersonField, List<String>> inputs;

        private FindConditions() {
            inputs = new LinkedHashMap<>();
        }

        @Override
        public boolean test(Person t) {
            Predicate<Person> collectivePredicate = inputs.entrySet().stream()
                    .flatMap(entry -> entry.getValue().stream().map(v -> getPredicate(v, entry.getKey())))
                    .reduce(x -> true, Predicate::and);
            return collectivePredicate.test(t);
        }

        @Override
        public String toString() {
            Optional<String> str = inputs.entrySet().stream().filter(entry -> !entry.getValue().isEmpty())
                    .map(entry -> getConditionString(entry.getKey(), entry.getValue())).reduce((a, b) -> a + " " + b);
            return str.orElse("");
        }

        private void put(PersonField field, List<String> values) {
            inputs.put(field, values);
        }

        private Predicate<Person> getPredicate(String args, PersonField field) {
            String trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                return p -> true;
            }

            String[] keywords = trimmedArgs.split("\\s+");
            return new ContainsKeywordsPredicate(Arrays.asList(keywords), field);
        }

        private String getConditionString(PersonField field, List<String> list) {
            Optional<String> listString = list.stream().reduce((a, b) -> a + ", " + b);
            return field + ": " + listString.orElse("");
        }

    }

}
