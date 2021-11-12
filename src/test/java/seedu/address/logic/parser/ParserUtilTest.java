package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INDEX_NOT_PARSED;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.areAnyPrefixesPresent;
import static seedu.address.logic.parser.ParserUtil.checkEmptyString;
import static seedu.address.logic.parser.ParserUtil.checkMaleOrFemale;
import static seedu.address.logic.parser.ParserUtil.checkTrueOrFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CalledCommand;
import seedu.address.logic.commands.DisplayCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.interests.Interest;
import seedu.address.model.person.interests.InterestsList;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.AgeContainsValuePredicate;
import seedu.address.model.person.predicates.CalledPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.GenderContainsKeywordPredicate;
import seedu.address.model.person.predicates.InterestContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsNumberPredicate;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GENDER = "Cheetah";
    private static final String INVALID_AGE = "22.5";
    private static final String INVALID_INTEREST = "";
    private static final String INVALID_TEST_STRING = "";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "87654321";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_GENDER = "M";
    private static final String VALID_AGE = "22";
    private static final String VALID_INTEREST_1 = "Running";
    private static final String VALID_INTEREST_2 = "Rolling";
    private static final String VALID_PREFIX_1 = "n/";
    private static final String VALID_PREFIX_2 = "a/";
    private static final String VALID_TEST_STRING = "THIS IS A VALID STRING";

    private static final String WHITESPACE = " \t\r\n";


    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a", MESSAGE_INVALID_COMMAND_FORMAT));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INDEX_NOT_PARSED), ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1), MESSAGE_INDEX_NOT_PARSED));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1", DisplayCommand.MESSAGE_USAGE));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  ", CalledCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseAddress_null_returnsEmptyAddress() throws Exception {
        Address expectedAddress = new Address(null);
        assertEquals(expectedAddress, ParserUtil.parseAddress(null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseGender_null_returnsEmptyGender() throws Exception {
        Gender expectedGender = new Gender(null);
        assertEquals(expectedGender, ParserUtil.parseGender(null));
    }

    @Test
    public void parseGender_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGender(INVALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithoutWhitespace_returnsGender() throws Exception {
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(VALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithWhitespace_returnsTrimmedGender() throws Exception {
        String genderWithWhitespace = WHITESPACE + VALID_GENDER + WHITESPACE;
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(genderWithWhitespace));
    }

    @Test
    public void parseAge_null_returnsEmptyAge() throws Exception {
        Age expectedAge = new Age(null);
        assertEquals(expectedAge, ParserUtil.parseAge(null));
    }

    @Test
    public void parseAge_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAge(INVALID_AGE));
    }

    @Test
    public void parseAge_validValueWithoutWhitespace_returnsAge() throws Exception {
        Age expectedAge = new Age(VALID_AGE);
        assertEquals(expectedAge, ParserUtil.parseAge(VALID_AGE));
    }

    @Test
    public void parseAge_validValueWithWhitespace_returnsTrimmedAge() throws Exception {
        String ageWithWhitespace = WHITESPACE + VALID_AGE + WHITESPACE;
        Age expectedAge = new Age(VALID_AGE);
        assertEquals(expectedAge, ParserUtil.parseAge(ageWithWhitespace));
    }

    @Test
    public void parseInterest_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInterest(null));
    }

    @Test
    public void parseInterest_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInterest(INVALID_INTEREST));
    }

    @Test
    public void parseInterest_validValueWithoutWhitespace_returnsInterest() throws Exception {
        Interest expectedInterest = new Interest(VALID_INTEREST_1);
        assertEquals(expectedInterest, ParserUtil.parseInterest(VALID_INTEREST_1));
    }

    @Test
    public void parseInterest_validValueWithWhitespace_returnsTrimmedInterest() throws Exception {
        String interestWithWhitespace = WHITESPACE + VALID_INTEREST_1 + WHITESPACE;
        Interest expectedInterest = new Interest(VALID_INTEREST_1);
        assertEquals(expectedInterest, ParserUtil.parseInterest(interestWithWhitespace));
    }

    @Test
    public void parseInterests_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInterest(null));
    }

    @Test
    public void parseInterests_collectionWithInvalidInterests_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInterests(
                Arrays.asList(VALID_INTEREST_1, INVALID_INTEREST)
        ));
    }

    @Test
    public void parseInterests_emptyCollection_returnsEmptyInterestsList() throws Exception {
        assertTrue(ParserUtil.parseInterests(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseInterests_collectionWithValidInterests_returnsInterestsList() throws Exception {
        InterestsList actualInterestsList = ParserUtil.parseInterests(
                Arrays.asList(VALID_INTEREST_1, VALID_INTEREST_2)
        );
        InterestsList expectedInterestsList = new InterestsList();
        expectedInterestsList.addInterest(new Interest(VALID_INTEREST_1));
        expectedInterestsList.addInterest(new Interest(VALID_INTEREST_2));
        assertEquals(expectedInterestsList, actualInterestsList);
    }

    @Test
    public void checkEmptyString_nullTest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.checkEmptyString(null, new Prefix(VALID_PREFIX_1)));
    }

    @Test
    public void checkEmptyString_nullPrefix_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.checkEmptyString(
                VALID_TEST_STRING, null)
        );
    }

    @Test
    public void checkEmptyString_invalidTest_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.checkEmptyString(
                INVALID_TEST_STRING, new Prefix(VALID_PREFIX_2)
        ));
    }

    @Test
    public void checkEmptyString_validTestAndValidPrefix_returnsTrue() throws ParseException {
        assertTrue(checkEmptyString(VALID_TEST_STRING, new Prefix(VALID_PREFIX_1)));
    }

    @Test
    public void checkTrueOrFalse_nullTest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.checkTrueOrFalse(null));
    }

    @Test
    public void checkTrueOrFalse_invalidTest_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.checkTrueOrFalse("n"));
        assertThrows(ParseException.class, () -> ParserUtil.checkTrueOrFalse("mm"));
        assertThrows(ParseException.class, () -> ParserUtil.checkTrueOrFalse("ff"));
        assertThrows(ParseException.class, () -> ParserUtil.checkTrueOrFalse("trues"));
    }

    @Test
    public void checkTrueOrFalse_validTest_returnsTrue() throws ParseException {
        assertTrue(checkTrueOrFalse("t"));
        assertTrue(checkTrueOrFalse("f"));
        assertTrue(checkTrueOrFalse("true"));
        assertTrue(checkTrueOrFalse("false"));
    }

    @Test
    public void checkMaleOrFemale_nullTest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.checkMaleOrFemale(null));
    }

    @Test
    public void checkMaleOrFemale_invalidTest_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.checkMaleOrFemale("n"));
        assertThrows(ParseException.class, () -> ParserUtil.checkMaleOrFemale("mm"));
        assertThrows(ParseException.class, () -> ParserUtil.checkMaleOrFemale("9238"));
        assertThrows(ParseException.class, () -> ParserUtil.checkMaleOrFemale("males"));
    }

    @Test
    public void checkMaleOrFemale_validTest_returnsTrue() throws ParseException {
        assertTrue(checkMaleOrFemale("m"));
        assertTrue(checkMaleOrFemale("f"));
        assertTrue(checkMaleOrFemale("male"));
        assertTrue(checkMaleOrFemale("female"));
        assertTrue(checkMaleOrFemale("n.a"));
    }

    @Test
    public void areAnyPrefixesPresent_nullArgMultiMap_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.areAnyPrefixesPresent(null));
    }

    @Test
    public void areAnyPrefixesPresent_emptyArgMultiMap_returnsFalse() {
        ArgumentMultimap emptyMap = new ArgumentMultimap();
        assertFalse(areAnyPrefixesPresent(emptyMap));
    }

    @Test
    public void areAnyPrefixesPresent_argMultiMapWithEmptyPrefixValue_returnsTrue() {
        ArgumentMultimap testMap = new ArgumentMultimap();
        testMap.put(new Prefix(VALID_PREFIX_1), "");
        assertTrue(areAnyPrefixesPresent(testMap));
    }

    @Test
    public void areAnyPrefixesPresent_argMultiMapWithNonEmptyPrefixValue_returnsTrue() {
        ArgumentMultimap testMap = new ArgumentMultimap();
        testMap.put(new Prefix(VALID_PREFIX_1), "non empty string");
        assertTrue(areAnyPrefixesPresent(testMap));
    }

    @Test
    public void areAnyPrefixesPresent_argMultiMapWithMultipleNonEmptyPrefixValue_returnsTrue() {
        ArgumentMultimap testMap = new ArgumentMultimap();
        testMap.put(new Prefix(VALID_PREFIX_1), "non empty string");
        testMap.put(new Prefix(VALID_PREFIX_2), "another non empty string");
        assertTrue(areAnyPrefixesPresent(testMap));
    }

    @Test
    public void getNamePredicate_nullKeywords_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.getNamePredicate(null, true));
    }

    @Test
    public void getNamePredicate_emptyKeywords_returnsNameContainsKeywordsPredicate() {
        NameContainsKeywordsPredicate expectedPredicate = new NameContainsKeywordsPredicate(
                Arrays.asList(""), true
        );
        assertEquals(ParserUtil.getNamePredicate("", true), expectedPredicate);
    }

    @Test
    public void getNamePredicate_nonEmptyKeywords_returnsNameContainsKeywordsPredicate() {
        NameContainsKeywordsPredicate expectedPredicate = new NameContainsKeywordsPredicate(
                Arrays.asList("name1", "name2"), true
        );
        assertEquals(ParserUtil.getNamePredicate("name1 name2", true), expectedPredicate);
    }

    @Test
    public void getPhonePredicate_nullKeywords_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.getPhonePredicate(null, true));
    }

    @Test
    public void getPhonePredicate_emptyKeywords_returnsNameContainsKeywordsPredicate() {
        PhoneContainsNumberPredicate expectedPredicate = new PhoneContainsNumberPredicate(
                Arrays.asList(""), true
        );
        assertEquals(ParserUtil.getPhonePredicate("", true), expectedPredicate);
    }

    @Test
    public void getPhonePredicate_nonEmptyKeywords_returnsNameContainsKeywordsPredicate() {
        PhoneContainsNumberPredicate expectedPredicate = new PhoneContainsNumberPredicate(
                Arrays.asList("98765432", "67253827"), true
        );
        assertEquals(ParserUtil.getPhonePredicate("98765432 67253827", true), expectedPredicate);
    }

    @Test
    public void getEmailPredicate_nullKeywords_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.getEmailPredicate(null, true));
    }

    @Test
    public void getEmailPredicate_emptyKeywords_returnsNameContainsKeywordsPredicate() {
        EmailContainsKeywordsPredicate expectedPredicate = new EmailContainsKeywordsPredicate(
                Arrays.asList(""), true
        );
        assertEquals(ParserUtil.getEmailPredicate("", true), expectedPredicate);
    }

    @Test
    public void getEmailPredicate_nonEmptyKeywords_returnsNameContainsKeywordsPredicate() {
        EmailContainsKeywordsPredicate expectedPredicate = new EmailContainsKeywordsPredicate(
                Arrays.asList("email1", "email2"), true
        );
        assertEquals(ParserUtil.getEmailPredicate("email1 email2", true), expectedPredicate);
    }

    @Test
    public void getCalledPredicate_nullKeywords_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.getCalledPredicate(null, true));
    }

    @Test
    public void getCalledPredicate_emptyKeywords_returnsNameContainsKeywordsPredicate() {
        CalledPredicate expectedPredicate = new CalledPredicate(
                Arrays.asList(""), true
        );
        assertEquals(ParserUtil.getCalledPredicate("", true), expectedPredicate);
    }

    @Test
    public void getCalledPredicate_nonEmptyKeywords_returnsNameContainsKeywordsPredicate() {
        CalledPredicate expectedPredicate = new CalledPredicate(
                Arrays.asList("true", "false"), true
        );
        assertEquals(ParserUtil.getCalledPredicate("true false", true), expectedPredicate);
    }

    @Test
    public void getAddressPredicate_nullKeywords_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.getAddressPredicate(null, true));
    }

    @Test
    public void getAddressPredicate_emptyKeywords_returnsNameContainsKeywordsPredicate() {
        AddressContainsKeywordsPredicate expectedPredicate = new AddressContainsKeywordsPredicate(
                Arrays.asList(""), true
        );
        assertEquals(ParserUtil.getAddressPredicate("", true), expectedPredicate);
    }

    @Test
    public void getAddressPredicate_nonEmptyKeywords_returnsNameContainsKeywordsPredicate() {
        AddressContainsKeywordsPredicate expectedPredicate = new AddressContainsKeywordsPredicate(
                Arrays.asList("address1", "address2"), true
        );
        assertEquals(ParserUtil.getAddressPredicate("address1 address2", true), expectedPredicate);
    }

    @Test
    public void getGenderPredicate_nullKeywords_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.getGenderPredicate(null, true));
    }

    @Test
    public void getGenderPredicate_emptyKeywords_returnsNameContainsKeywordsPredicate() {
        GenderContainsKeywordPredicate expectedPredicate = new GenderContainsKeywordPredicate(
                Arrays.asList(""), true
        );
        assertEquals(ParserUtil.getGenderPredicate("", true), expectedPredicate);
    }

    @Test
    public void getGenderPredicate_nonEmptyKeywords_returnsNameContainsKeywordsPredicate() {
        GenderContainsKeywordPredicate expectedPredicate = new GenderContainsKeywordPredicate(
                Arrays.asList("male", "female"), true
        );
        assertEquals(ParserUtil.getGenderPredicate("male female", true), expectedPredicate);
    }

    @Test
    public void getAgePredicate_nullKeywords_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.getAgePredicate(null, true));
    }

    @Test
    public void getAgePredicate_emptyKeywords_returnsNameContainsKeywordsPredicate() {
        AgeContainsValuePredicate expectedPredicate = new AgeContainsValuePredicate(
                Arrays.asList(""), true
        );
        assertEquals(ParserUtil.getAgePredicate("", true), expectedPredicate);
    }

    @Test
    public void getAgePredicate_nonEmptyKeywords_returnsNameContainsKeywordsPredicate() {
        AgeContainsValuePredicate expectedPredicate = new AgeContainsValuePredicate(
                Arrays.asList("11", "22"), true
        );
        assertEquals(ParserUtil.getAgePredicate("11 22", true), expectedPredicate);
    }

    @Test
    public void getInterestPredicate_nullKeywords_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.getInterestPredicate(null, true));
    }

    @Test
    public void getInterestPredicate_emptyKeywords_returnsNameContainsKeywordsPredicate() {
        InterestContainsKeywordsPredicate expectedPredicate = new InterestContainsKeywordsPredicate(
                Arrays.asList(""), true
        );
        assertEquals(ParserUtil.getInterestPredicate("", true), expectedPredicate);
    }

    @Test
    public void getInterestPredicate_nonEmptyKeywords_returnsNameContainsKeywordsPredicate() {
        InterestContainsKeywordsPredicate expectedPredicate = new InterestContainsKeywordsPredicate(
                Arrays.asList("male", "female"), true
        );
        assertEquals(ParserUtil.getInterestPredicate("male female", true), expectedPredicate);
    }


}
