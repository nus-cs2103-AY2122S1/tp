package seedu.address.model.person;

import static seedu.address.commons.util.StringUtil.containsStringIgnoreCase;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTPLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISPOSABLEINCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTMET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKAPPETITE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.parser.ArgumentMultimap;

/**
 * Tests that a {@code Person}'s attributes matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final ArgumentMultimap keywords;

    public PersonContainsKeywordsPredicate(ArgumentMultimap keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String[] generalKeywords = keywords.getPreamble().split(" ");
        boolean checkGeneral = generalKeywords[0].isBlank() || Arrays.stream(generalKeywords).anyMatch(x -> {
                boolean checkAttribute = Stream.of(person.getName(), person.getPhone(),
                        person.getEmail(), person.getAddress(), person.getRiskAppetite(),
                        person.getDisposableIncome(), person.getLastMet(), person.getCurrentPlan())
                        .map(Object::toString).anyMatch(y -> containsStringIgnoreCase(y, x));

                boolean checkAttributeTag = person.getTags().stream()
                    .anyMatch(y -> containsStringIgnoreCase(y.tagName, x));
                return checkAttribute || checkAttributeTag;
            }
        );

        boolean checkName = keywords.getValue(PREFIX_NAME)
                .map(x -> containsStringIgnoreCase(person.getName().toString(), x)).orElse(true);
        boolean checkPhone = keywords.getValue(PREFIX_PHONE)
                .map(x -> containsStringIgnoreCase(person.getPhone().toString(), x)).orElse(true);
        boolean checkEmail = keywords.getValue(PREFIX_EMAIL)
                .map(x -> containsStringIgnoreCase(person.getEmail().toString(), x)).orElse(true);
        boolean checkAddress = keywords.getValue(PREFIX_ADDRESS)
                .map(x -> containsStringIgnoreCase(person.getAddress().toString(), x)).orElse(true);
        boolean checkRiskAppetite = keywords.getValue(PREFIX_RISKAPPETITE)
                .map(x -> containsStringIgnoreCase(person.getRiskAppetite().toString(), x)).orElse(true);
        boolean checkDisposableIncome = keywords.getValue(PREFIX_DISPOSABLEINCOME)
                .map(x -> containsStringIgnoreCase(person.getDisposableIncome().toString(), x)).orElse(true);
        boolean checkLastMet = keywords.getValue(PREFIX_LASTMET)
                .map(x -> containsStringIgnoreCase(person.getLastMet().toString(), x)).orElse(true);
        boolean checkCurrentPlan = keywords.getValue(PREFIX_CURRENTPLAN)
                .map(x -> containsStringIgnoreCase(person.getCurrentPlan().toString(), x)).orElse(true);
        boolean checkTags = keywords.getValue(PREFIX_TAG)
                .map(x -> person.getTags().stream().anyMatch(y -> containsStringIgnoreCase(y.tagName, x))).orElse(true);

        return checkGeneral && checkName && checkPhone && checkEmail && checkAddress && checkRiskAppetite
                && checkDisposableIncome && checkLastMet && checkCurrentPlan && checkTags;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }

}

