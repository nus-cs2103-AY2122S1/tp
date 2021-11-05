package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.DeleteMultipleCommand.MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_PREDICATE;
import static seedu.address.logic.commands.DeleteMultipleCommand.MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_VALUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.DeleteMultipleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicate.MultiplePredicates;
import seedu.address.model.person.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicate.NationalityContainsKeywordsPredicate;
import seedu.address.model.person.predicate.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicate.TagContainsKeywordsPredicate;
import seedu.address.model.person.predicate.TutorialGroupContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new DeleteMultipleCommand object
 */
public class DeleteMultipleCommandParser implements Parser<DeleteMultipleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMultipleCommand
     * and returns a DeleteMultipleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMultipleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NATIONALITY,
                        PREFIX_TUTORIAL_GROUP, PREFIX_TAG);

        ArrayList<Predicate<Person>> predicateList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()
                && !argMultimap.getValue(PREFIX_NAME).get().trim().isEmpty()) {
            List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
            int numOfEmptyValue = (int) nameKeywords.stream()
                    .filter(String::isEmpty).count();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_VALUE, "Name",
                                DeleteMultipleCommand.MESSAGE_USAGE));
            }
            predicateList.add(new NameContainsKeywordsPredicate(nameKeywords));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()
                && !argMultimap.getValue(PREFIX_PHONE).get().trim().isEmpty()) {
            List<String> phoneKeywords = argMultimap.getAllValues(PREFIX_PHONE);
            int numOfEmptyValue = (int) phoneKeywords.stream()
                    .filter(String::isEmpty).count();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_VALUE, "Phone",
                                DeleteMultipleCommand.MESSAGE_USAGE));
            }
            predicateList.add(new PhoneContainsKeywordsPredicate(phoneKeywords));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()
                && !argMultimap.getValue(PREFIX_EMAIL).get().trim().isEmpty()) {
            List<String> emailKeywords = argMultimap.getAllValues(PREFIX_EMAIL);
            int numOfEmptyValue = (int) emailKeywords.stream()
                    .filter(String::isEmpty).count();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_VALUE, "Email",
                                DeleteMultipleCommand.MESSAGE_USAGE));
            }
            predicateList.add(new EmailContainsKeywordsPredicate(emailKeywords));
        }
        if (argMultimap.getValue(PREFIX_NATIONALITY).isPresent()
                && !argMultimap.getValue(PREFIX_NATIONALITY).get().trim().isEmpty()) {
            List<String> nationalityKeywords = argMultimap.getAllValues(PREFIX_NATIONALITY);
            int numOfEmptyValue = (int) nationalityKeywords.stream()
                    .filter(String::isEmpty).count();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_VALUE, "Nationality",
                                DeleteMultipleCommand.MESSAGE_USAGE));
            }
            predicateList.add(new NationalityContainsKeywordsPredicate(nationalityKeywords));
        }
        if (argMultimap.getValue(PREFIX_TUTORIAL_GROUP).isPresent()
                && !argMultimap.getValue(PREFIX_TUTORIAL_GROUP).get().trim().isEmpty()) {
            List<String> tutorialGroupKeywords = argMultimap.getAllValues(PREFIX_TUTORIAL_GROUP);
            int numOfEmptyValue = (int) tutorialGroupKeywords.stream()
                    .filter(String::isEmpty).count();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_VALUE, "Tutorial Group",
                                DeleteMultipleCommand.MESSAGE_USAGE));
            }
            predicateList.add(new TutorialGroupContainsKeywordsPredicate(tutorialGroupKeywords));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()
                && !argMultimap.getValue(PREFIX_TAG).get().trim().isEmpty()) {
            List<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);
            int numOfEmptyValue = (int) tagKeywords.stream()
                    .filter(String::isEmpty).count();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_VALUE, "Tag",
                                DeleteMultipleCommand.MESSAGE_USAGE));
            }
            predicateList.add(new TagContainsKeywordsPredicate(tagKeywords));
        }

        switch (predicateList.size()) {
        case (0):
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_PREDICATE,
                            DeleteMultipleCommand.MESSAGE_USAGE));
        case (1):
            return new DeleteMultipleCommand(predicateList.get(0));
        default:
            MultiplePredicates predicate = new MultiplePredicates(predicateList);
            return new DeleteMultipleCommand(predicate);
        }
    }
}

