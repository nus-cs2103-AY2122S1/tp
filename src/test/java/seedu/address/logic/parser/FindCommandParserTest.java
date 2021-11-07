package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL_OF_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.FindCommandParser.FindDescriptor;
import static seedu.address.testutil.Assert.assertThrows;

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
    public void findDescriptor_validPrefixInput_addToPredicateList() throws ParseException {
        String validNamePrefixInput = " n/John Doe";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validNamePrefixInput,
                PREFIX_NAME);
        FindDescriptor findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String validPhonePrefixInput = " p/12345678";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validPhonePrefixInput,
                PREFIX_PHONE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String validEmailPrefixInput = " e/johndoe@abc.com";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validEmailPrefixInput,
                PREFIX_EMAIL);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String validRolePrefixInput = " r/Teacher";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validRolePrefixInput, PREFIX_ROLE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String validEmploymentTypePrefixInput = " et/Temporary";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validEmploymentTypePrefixInput,
                PREFIX_EMPLOYMENT_TYPE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String validExpectedSalaryPrefixInput = " s/3500";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validExpectedSalaryPrefixInput,
                PREFIX_EXPECTED_SALARY);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String validLevelOfEducationPrefixInput = " l/Masters";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validLevelOfEducationPrefixInput,
                PREFIX_LEVEL_OF_EDUCATION);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String validExperiencePrefixInput = " y/3";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validExperiencePrefixInput,
                PREFIX_EXPERIENCE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String validTagPrefixInput = " t/old";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validTagPrefixInput,
                PREFIX_TAG);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String validInterviewPrefixInput = " i/2021-10-29, 20:00";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validInterviewPrefixInput,
                PREFIX_INTERVIEW);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String validNotesPrefixInput = " nt/This candidate is good.";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validNotesPrefixInput,
                PREFIX_NOTES);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String validDonePrefixInputOne = " d/Done";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validDonePrefixInputOne,
                PREFIX_DONE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String validDonePrefixInputTwo = " d/Not Done";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(validDonePrefixInputTwo,
                PREFIX_DONE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());

        String multipleValidPrefixInput =
                " n/John Doe p/12345678 e/johndoe@abc.com r/Teacher et/Temporary s/3500 l/Masters "
                        + "y/3 t/old i/2021-10-29, 20:00 d/Done";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(multipleValidPrefixInput,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE, PREFIX_EMPLOYMENT_TYPE,
                PREFIX_EXPECTED_SALARY, PREFIX_LEVEL_OF_EDUCATION, PREFIX_EXPERIENCE, PREFIX_TAG,
                PREFIX_INTERVIEW, PREFIX_DONE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertFalse(findDescriptor.getPredicates().isEmpty());
    }


    @Test
    public void findDescriptor_emptyPrefixInput_emptyPredicatesList() throws ParseException {
        String emptyNamePrefixInput = " n/";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyNamePrefixInput, PREFIX_NAME);
        FindDescriptor findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyPhonePrefixInput = " p/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyPhonePrefixInput, PREFIX_PHONE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyEmailPrefixInput = " e/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyEmailPrefixInput, PREFIX_EMAIL);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyRolePrefixInput = " r/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyRolePrefixInput, PREFIX_ROLE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyEmploymentTypePrefixInput = " et/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyEmploymentTypePrefixInput, PREFIX_EMPLOYMENT_TYPE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyExpectedSalaryPrefixInput = " s/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyExpectedSalaryPrefixInput, PREFIX_EXPECTED_SALARY);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyLevelOfEducationPrefixInput = " l/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyLevelOfEducationPrefixInput,
                PREFIX_LEVEL_OF_EDUCATION);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyExperiencePrefixInput = " y/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyExperiencePrefixInput,
                PREFIX_EXPERIENCE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyTagPrefixInput = " t/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyTagPrefixInput, PREFIX_TAG);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyInterviewPrefixInput = " i/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyInterviewPrefixInput, PREFIX_INTERVIEW);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyNotesPrefixInput = " nt/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyNotesPrefixInput, PREFIX_NOTES);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String emptyDonePrefixInput = " d/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(emptyDonePrefixInput, PREFIX_DONE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String multipleEmptyPrefixInput = " n/ p/ e/ r/ et/ s/ l/ y/ t/ i/ nt/ d/";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(multipleEmptyPrefixInput,
                PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_ROLE, PREFIX_EMPLOYMENT_TYPE,
                PREFIX_EXPECTED_SALARY, PREFIX_LEVEL_OF_EDUCATION, PREFIX_EXPERIENCE, PREFIX_TAG,
                PREFIX_INTERVIEW, PREFIX_NOTES, PREFIX_DONE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());
    }

    @Test
    public void findDescriptor_blankPrefixInput_emptyPredicatesList() throws ParseException {
        String blankNamePrefixInput = " n/  ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankNamePrefixInput, PREFIX_NAME);
        FindDescriptor findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankPhonePrefixInput = " p/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankPhonePrefixInput, PREFIX_PHONE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankEmailPrefixInput = " e/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankEmailPrefixInput, PREFIX_EMAIL);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankRolePrefixInput = " r/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankRolePrefixInput, PREFIX_ROLE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankEmploymentTypePrefixInput = " et/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankEmploymentTypePrefixInput, PREFIX_EMPLOYMENT_TYPE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankExpectedSalaryPrefixInput = " s/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankExpectedSalaryPrefixInput,
                PREFIX_EXPECTED_SALARY);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankLevelOfEducationPrefixInput = " l/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankLevelOfEducationPrefixInput,
                PREFIX_LEVEL_OF_EDUCATION);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankExperiencePrefixInput = " y/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankExperiencePrefixInput,
                PREFIX_EXPERIENCE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankTagPrefixInput = " t/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankTagPrefixInput, PREFIX_TAG);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankInterviewPrefixInput = " i/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankInterviewPrefixInput, PREFIX_INTERVIEW);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankNotesPrefixInput = " nt/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankNotesPrefixInput, PREFIX_NOTES);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String blankDonePrefixInput = " d/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(blankDonePrefixInput, PREFIX_DONE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());

        String multipleBlankPrefixInput = " n/   p/   e/   r/   et/   s/   l/   y/   t/   i/   nt/   d/  ";
        argMultimap = ArgumentTokenizer.tokenizeWithoutPreamble(multipleBlankPrefixInput,
                PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_ROLE, PREFIX_EMPLOYMENT_TYPE,
                PREFIX_EXPECTED_SALARY, PREFIX_LEVEL_OF_EDUCATION, PREFIX_EXPERIENCE, PREFIX_TAG,
                PREFIX_INTERVIEW, PREFIX_NOTES, PREFIX_DONE);
        findDescriptor = new FindDescriptor(argMultimap);
        assertTrue(findDescriptor.getPredicates().isEmpty());
    }

    @Test
    public void findDescriptor_invalidPrefixInput_parseExceptionThrown() throws ParseException {
        String invalidNamePrefixInput = " n/@#$"; // non-alphanumeric characters
        assertThrows(ParseException.class, () ->
                new FindDescriptor(ArgumentTokenizer.tokenizeWithoutPreamble(invalidNamePrefixInput, PREFIX_NAME)));

        String invalidPhonePrefixInput = " p/abc"; // non-numeric
        assertThrows(ParseException.class, () ->
                new FindDescriptor(ArgumentTokenizer.tokenizeWithoutPreamble(invalidPhonePrefixInput, PREFIX_PHONE)));

        String invalidEmailPrefixInput = " e/peterjack@"; // missing domain name
        assertThrows(ParseException.class, () ->
                new FindDescriptor(ArgumentTokenizer.tokenizeWithoutPreamble(invalidEmailPrefixInput, PREFIX_EMAIL)));

        String invalidRolePrefixInput = " r/@#$%^&*()"; // only non-alphanumeric
        assertThrows(ParseException.class, () ->
                new FindDescriptor(ArgumentTokenizer.tokenizeWithoutPreamble(invalidRolePrefixInput, PREFIX_ROLE)));

        String invalidEmploymentTypePrefixInput = " et/longtime"; // none of the employment types
        // start with this keyword
        assertThrows(ParseException.class, () ->
                new FindDescriptor(ArgumentTokenizer.tokenizeWithoutPreamble(invalidEmploymentTypePrefixInput,
                        PREFIX_EMPLOYMENT_TYPE)));

        String invalidExpectedSalaryPrefixInput = " s/-100"; // negative number
        assertThrows(ParseException.class, () ->
                new FindDescriptor(ArgumentTokenizer.tokenizeWithoutPreamble(invalidExpectedSalaryPrefixInput,
                        PREFIX_EXPECTED_SALARY)));

        String invalidLevelOfEducationTypePrefixInput = " l/Kindergarten"; // none of the levels of education
        // start with this keyword
        assertThrows(ParseException.class, () ->
                new FindDescriptor(ArgumentTokenizer.tokenizeWithoutPreamble(invalidLevelOfEducationTypePrefixInput,
                        PREFIX_LEVEL_OF_EDUCATION)));

        String invalidExperiencePrefixInput1 = " y/800"; // large number
        assertThrows(ParseException.class, () ->
                new FindDescriptor(ArgumentTokenizer.tokenizeWithoutPreamble(invalidExperiencePrefixInput1,
                        PREFIX_EXPERIENCE)));

        String invalidTagPrefixInput = " t/old(70)"; // brackets
        assertThrows(ParseException.class, () ->
                new FindDescriptor(ArgumentTokenizer.tokenizeWithoutPreamble(invalidTagPrefixInput, PREFIX_TAG)));

        String invalidDonePrefixInput = " d/Wrong"; // Neither "Done" nor "Not Done"
        assertThrows(ParseException.class, () ->
                new FindDescriptor(ArgumentTokenizer.tokenizeWithoutPreamble(invalidDonePrefixInput, PREFIX_DONE)));

        String multipleInvalidPrefixInput =
                " n/@#$ p/abc e/peterjack@ r/@#$%^&*() et/longterm s/-100 l/Kindergarten y/800 "
                        + "t/old(70) i/monday d/Wrong";
        assertThrows(ParseException.class, () ->
                new FindDescriptor(ArgumentTokenizer.tokenizeWithoutPreamble(multipleInvalidPrefixInput,
                        PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_ROLE, PREFIX_EMPLOYMENT_TYPE,
                        PREFIX_EXPECTED_SALARY, PREFIX_LEVEL_OF_EDUCATION, PREFIX_EXPERIENCE,
                        PREFIX_TAG, PREFIX_INTERVIEW, PREFIX_DONE)));
    }
}
