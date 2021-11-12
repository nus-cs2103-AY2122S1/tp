package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALLED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTEREST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.AgeContainsValuePredicate;
import seedu.address.model.person.predicates.CalledPredicate;
import seedu.address.model.person.predicates.CombinedPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.GenderContainsKeywordPredicate;
import seedu.address.model.person.predicates.InterestContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsNumberPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindAllCommandParser implements Parser<FindAllCommand> {

    public static final String EMPTY_FIELD_MESSAGE = "Fields provided can be anything but cannot be an empty string \n"
            + "Found one violation at: ";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAllCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_CALLED,
                        PREFIX_ADDRESS, PREFIX_GENDER, PREFIX_AGE, PREFIX_INTEREST);

        if (!ParserUtil.areAnyPrefixesPresent(argMultimap) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAllCommand.MESSAGE_USAGE));
        }

        String nameKeywords = argMultimap.getValue(PREFIX_NAME).orElse(null);
        String phoneNumbers = argMultimap.getValue(PREFIX_PHONE).orElse(null);
        String emailKeywords = argMultimap.getValue(PREFIX_EMAIL).orElse(null);
        String calledKeywords = argMultimap.getValue(PREFIX_CALLED).orElse(null);
        String addressKeywords = argMultimap.getValue(PREFIX_ADDRESS).orElse(null);
        String genderKeywords = argMultimap.getValue(PREFIX_GENDER).orElse(null);
        String ageValues = argMultimap.getValue(PREFIX_AGE).orElse(null);
        String interestKeywords = argMultimap.getValue(PREFIX_INTEREST).orElse(null);

        ArrayList<Predicate<Person>> predicates = new ArrayList<>();

        if (nameKeywords != null) {
            ParserUtil.checkEmptyString(nameKeywords, PREFIX_NAME);
            NameContainsKeywordsPredicate namePredicate = ParserUtil.getNamePredicate(nameKeywords, true);
            predicates.add(namePredicate);
        }
        if (phoneNumbers != null) {
            ParserUtil.checkEmptyString(phoneNumbers, PREFIX_PHONE);
            PhoneContainsNumberPredicate phonePredicate = ParserUtil.getPhonePredicate(phoneNumbers, true);
            predicates.add(phonePredicate);
        }
        if (emailKeywords != null) {
            ParserUtil.checkEmptyString(emailKeywords, PREFIX_EMAIL);
            EmailContainsKeywordsPredicate emailPredicate = ParserUtil.getEmailPredicate(emailKeywords, true);
            predicates.add(emailPredicate);
        }
        if (calledKeywords != null) {
            ParserUtil.checkEmptyString(calledKeywords, PREFIX_CALLED);
            ParserUtil.checkTrueOrFalse(calledKeywords);
            CalledPredicate calledPredicate = ParserUtil.getCalledPredicate(calledKeywords, true);
            predicates.add(calledPredicate);
        }
        if (addressKeywords != null) {
            ParserUtil.checkEmptyString(addressKeywords, PREFIX_ADDRESS);
            AddressContainsKeywordsPredicate addressPredicate = ParserUtil.getAddressPredicate(addressKeywords, true);
            predicates.add(addressPredicate);
        }
        if (genderKeywords != null) {
            ParserUtil.checkEmptyString(genderKeywords, PREFIX_GENDER);
            ParserUtil.checkMaleOrFemale(genderKeywords);
            GenderContainsKeywordPredicate genderPredicate = ParserUtil.getGenderPredicate(genderKeywords, true);
            predicates.add(genderPredicate);
        }
        if (ageValues != null) {
            ParserUtil.checkEmptyString(ageValues, PREFIX_AGE);
            AgeContainsValuePredicate agePredicate = ParserUtil.getAgePredicate(ageValues, true);
            predicates.add(agePredicate);
        }
        if (interestKeywords != null) {
            ParserUtil.checkEmptyString(interestKeywords, PREFIX_INTEREST);
            InterestContainsKeywordsPredicate interestPredicate = ParserUtil.getInterestPredicate(
                    interestKeywords, true
            );
            predicates.add(interestPredicate);
        }

        CombinedPredicate combinedPredicate = new CombinedPredicate(predicates, true);

        return new FindAllCommand(combinedPredicate);
    }
}
