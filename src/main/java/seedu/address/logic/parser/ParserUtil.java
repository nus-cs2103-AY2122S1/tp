package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INDEX_NOT_PARSED;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALLED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTEREST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.category.Category;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.IsCalled;
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

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_COUNT = "Count is not a non-zero unsigned integer. "
            + "It cannot be bigger than 2147483647 (i.e., MAX_VALUE). \n";
    public static final String EMPTY_FIELD_MESSAGE = "Fields provided can be anything but cannot be an empty string \n"
            + "Found one violation at: ";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (i.e., if it is not a number, or it is more than
     * the MAX_VALUE). Different error messages will be displayed based on whether input is an integer or not.
     */
    public static Index parseIndex(String indexString, String exceptionMessage) throws ParseException {
        Index returnIndex;
        if (indexString.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, exceptionMessage));
        }
        if (isPositiveInteger(indexString)) {
            try {
                int intIndex = Integer.parseInt(indexString.trim());
                returnIndex = Index.fromOneBased(intIndex);
            } catch (NumberFormatException e) {
                throw new ParseException(MESSAGE_INDEX_NOT_PARSED);
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, exceptionMessage));
        }
        return returnIndex;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Name.MESSAGE_CONSTRAINTS));
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Phone.MESSAGE_CONSTRAINTS));
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Email.MESSAGE_CONSTRAINTS));
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        if (address == null) {
            return new Address(null);
        }

        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Address.MESSAGE_CONSTRAINTS));
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String gender} into an {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        if (gender == null) {
            return new Gender(null);
        }

        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Gender.MESSAGE_CONSTRAINTS));
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String age} into an {@code Age}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code age} is invalid.
     */
    public static Age parseAge(String age) throws ParseException {
        if (age == null) {
            return new Age(null);
        }

        String trimmedAge = age.trim();
        if (!Age.isValidAge(trimmedAge)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Age.MESSAGE_CONSTRAINTS));
        }
        return new Age(trimmedAge);
    }

    /**
     * Parses a {@code String isCalled} into an {@code isCalled}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code isCalled} is invalid.
     */
    public static IsCalled parseIsCalled(String isCalled) throws ParseException {
        if (isCalled == null) {
            return new IsCalled(null);
        }

        String trimmedCalled = isCalled.trim();
        if (!IsCalled.isValidIsCalled(trimmedCalled)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, IsCalled.MESSAGE_CONSTRAINTS));
        }
        return new IsCalled(trimmedCalled);
    }

    /**
     * Parses a {@code String interest} into a {@code Interest}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interest} is invalid.
     */
    public static Interest parseInterest(String interest) throws ParseException {
        requireNonNull(interest);
        String trimmedInterest = interest.trim();

        if (!Interest.isValidInterest(trimmedInterest)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Interest.MESSAGE_CONSTRAINTS));
        }
        return new Interest(trimmedInterest);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static InterestsList parseInterests(Collection<String> interests) throws ParseException {
        requireNonNull(interests);
        final InterestsList interestsList = new InterestsList();
        for (String interest : interests) {
            try {
                interestsList.addInterest(parseInterest(interest));
            } catch (IllegalArgumentException e) {
                throw new ParseException("Duplicate arguments are not allowed!");
            }
        }
        return interestsList;
    }

    /**
     * Parses {@code oneBasedInteger} into an {@code Integer} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified integer is invalid (not non-zero unsigned integer).
     */
    public static Integer parseInteger(String oneBasedInteger) throws ParseException {
        String trimmedInteger = oneBasedInteger.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedInteger)) {
            throw new ParseException(MESSAGE_INVALID_COUNT);
        }
        return Integer.parseInt(trimmedInteger);
    }

    /**
     * Parses a {@code String category} into a {@code category}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code category} is invalid.
     */
    public static Category parseCategory(String category) throws ParseException {
        requireNonNull(category);
        String trimmedCategory = category.trim();
        if (!Category.isValidCategory(trimmedCategory)) {
            throw new ParseException(Category.MESSAGE_CONSTRAINTS);
        }
        return new Category(trimmedCategory);
    }

    /**
     * Checks if the string provided at the respective prefix is empty
     * @param test The string to be checked
     * @param prefix The prefix the string has
     * @throws ParseException when the string is empty
     */
    public static boolean checkEmptyString(String test, Prefix prefix) throws ParseException {
        requireNonNull(test);
        requireNonNull(prefix);
        if (test.length() == 0) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EMPTY_FIELD_MESSAGE + prefix.getPrefix()
            ));
        }
        return true;
    }

    /**
     * Parses a String into a {@code NameContainsKeyWordPredicate}
     * @param nameKeywords the keywords to be parsed
     * @return The predicate
     */
    public static NameContainsKeywordsPredicate getNamePredicate(String nameKeywords, boolean isFindAll) {
        requireNonNull(nameKeywords);
        String[] nameKeywordsArr = nameKeywords.split("\\s+");
        return new NameContainsKeywordsPredicate(Arrays.asList(nameKeywordsArr), isFindAll);
    }

    /**
     * Parses a String into a {@code PhoneContainsNumberPredicate}
     * @param numbers the numbers to be parsed
     * @return The predicate
     */
    public static PhoneContainsNumberPredicate getPhonePredicate(String numbers, boolean isFindAll) {
        requireNonNull(numbers);
        String[] numbersArr = numbers.split("\\s+");
        return new PhoneContainsNumberPredicate(Arrays.asList(numbersArr), isFindAll);
    }

    /**
     * Parses a String into a {@code EmailContainsKeywordsPredicate}
     * @param emailKeywords the keywords to be parsed
     * @return The predicate
     */
    public static EmailContainsKeywordsPredicate getEmailPredicate(String emailKeywords, boolean isFindAll) {
        requireNonNull(emailKeywords);
        String[] emailKeywordsArr = emailKeywords.split("\\s+");
        return new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywordsArr), isFindAll);
    }

    /**
     * Parses a String into a {@code CalledPredicate}
     * @param calledKeywords the keywords to be parsed
     * @return The predicate
     */
    public static CalledPredicate getCalledPredicate(String calledKeywords, boolean isFindAll) {
        requireNonNull(calledKeywords);
        String[] calledKeywordsArr = calledKeywords.split("\\s+");
        return new CalledPredicate(Arrays.asList(calledKeywordsArr), isFindAll);
    }

    /**
     * Parses a String into a {@code AddressContainsKeywordsPredicate}
     * @param addressKeywords the keywords to be parsed
     * @return The predicate
     */
    public static AddressContainsKeywordsPredicate getAddressPredicate(String addressKeywords, boolean isFindAll) {
        requireNonNull(addressKeywords);
        String[] addressKeywordsArr = addressKeywords.split("\\s+");
        return new AddressContainsKeywordsPredicate(Arrays.asList(addressKeywordsArr), isFindAll);
    }

    /**
     * Parses a String into a {@code GenderContainsKeywordPredicate}
     * @param genderKeywords the keywords to be parsed
     * @return The predicate
     */
    public static GenderContainsKeywordPredicate getGenderPredicate(String genderKeywords, boolean isFindAll) {
        requireNonNull(genderKeywords);
        String[] genderKeywordsArr = genderKeywords.split("\\s+");
        return new GenderContainsKeywordPredicate(Arrays.asList(genderKeywordsArr), isFindAll);
    }

    /**
     * Parses a String into a {@code AgeContainsValuePredicate}
     * @param ageValues the values to be parsed
     * @return The predicate
     */
    public static AgeContainsValuePredicate getAgePredicate(String ageValues, boolean isFindAll) {
        requireNonNull(ageValues);
        String[] ageValuesArr = ageValues.split("\\s+");
        return new AgeContainsValuePredicate(Arrays.asList(ageValuesArr), isFindAll);
    }

    /**
     * Parses a String into a {@code InterestContainsKeywordsPredicate}
     * @param interestKeywords the keywords to be parsed
     * @return The predicate
     */
    public static InterestContainsKeywordsPredicate getInterestPredicate(String interestKeywords, boolean isFindAll) {
        requireNonNull(interestKeywords);
        String[] interestKeywordsArr = interestKeywords.split("\\s+");
        return new InterestContainsKeywordsPredicate(Arrays.asList(interestKeywordsArr), isFindAll);
    }

    /**
     * Checks if the string supplied is either "t", "true", "f" or "false". (case-insensitive)
     * @param s The String to be checked
     * @throws ParseException if the String is not one of the accepted forms
     */
    public static boolean checkTrueOrFalse(String s) throws ParseException {
        String[] testStrings = s.split("\\s+");

        for (String test : testStrings) {
            test = test.toLowerCase(Locale.ROOT);
            if (!(test.equals("t") || test.equals("true")
                    || test.equals("f") || test.equals("false"))) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, "'d/' can only be followed by 't','f', 'true', or 'false'"
                ));
            }
        }
        return true;
    }

    /**
     * Checks if the string supplied is either "m", "male", "f", "female" or "n.a". (case-insensitive)
     * @param s The String to be checked
     * @throws ParseException if the String is not one of the accepted forms
     */
    public static boolean checkMaleOrFemale(String s) throws ParseException {
        String[] testStrings = s.split("\\s+");

        for (String test : testStrings) {
            test = test.toLowerCase(Locale.ROOT);
            if (!(test.equals("m") || test.equals("male")
                    || test.equals("f") || test.equals("female") || test.equals("n.a"))) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        "'g/' can only be followed by 'm','f', 'male', 'female' or 'N.A'"
                ));
            }
        }
        return true;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap) {
        requireNonNull(argumentMultimap);
        return Stream.of(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_CALLED,
                        PREFIX_ADDRESS, PREFIX_GENDER, PREFIX_AGE, PREFIX_INTEREST)
                .anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Checks whether the parameter that has been input is a whole number (can also be a whole number greater
     * than Integer.MAX_VALUE)
     *
     * @param arg the index parameter that has been entered by the user
     * @return a boolean representing whether the index is a positive integer or not
     */
    public static boolean isPositiveInteger(String arg) {
        boolean isZero = true;
        try {
            for (char c : arg.trim().toCharArray()) {
                Integer.parseInt(String.valueOf(c));
                if (isZero && c != '0') {
                    isZero = false;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return !isZero;
    }
}
