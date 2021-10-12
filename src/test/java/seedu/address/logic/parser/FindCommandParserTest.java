package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL_OF_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.FindCommandParser.FindDescriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        predicates.add(namePredicate);

        FindCommand expectedFindCommand = new FindCommand(predicates);
        assertParseSuccess(parser, "n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "n/\n Alice \n \t Bob  \t", expectedFindCommand);

        // blank inputs for prefixes
        ArrayList<Predicate<Person>> emptyPredicates = new ArrayList<>();

        FindCommand emptyFindCommand = new FindCommand(emptyPredicates);
        assertParseSuccess(parser, "n/ y/", emptyFindCommand);
    }

    // Find Descriptor tests
    @Test
    public void findDescriptor_emptyPrefixInput_emptyPredicatesList() throws ParseException {
        String emptyNamePrefixInput = " n/";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyNamePrefixInput, PREFIX_NAME);
        FindDescriptor findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyEmailPrefixInput = " e/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyEmailPrefixInput, PREFIX_EMAIL);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyPhonePrefixInput = " p/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyPhonePrefixInput, PREFIX_PHONE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyRolePrefixInput = " r/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyRolePrefixInput, PREFIX_ROLE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyLevelOfEducationPrefixInput = " l/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyLevelOfEducationPrefixInput,
                PREFIX_LEVEL_OF_EDUCATION);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String multipleEmptyPrefixInput = " n/ e/ p/ r/ l/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(multipleEmptyPrefixInput,
                PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_ROLE, PREFIX_LEVEL_OF_EDUCATION);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());
    }

    @Test
    public void findDescriptor_blankPrefixInput_emptyPredicatesList() throws ParseException {
        String blankNamePrefixInput = " n/  ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankNamePrefixInput, PREFIX_NAME);
        FindDescriptor findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankEmailPrefixInput = " e/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankEmailPrefixInput, PREFIX_EMAIL);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankPhonePrefixInput = " p/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankPhonePrefixInput, PREFIX_PHONE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankRolePrefixInput = " r/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankRolePrefixInput, PREFIX_ROLE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankLevelOfEducationPrefixInput = " l/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankLevelOfEducationPrefixInput,
                PREFIX_LEVEL_OF_EDUCATION);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String multipleBlankPrefixInput = " n/   e/   p/   r/   l/   ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(multipleBlankPrefixInput,
                PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_ROLE, PREFIX_LEVEL_OF_EDUCATION);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());
    }

}
