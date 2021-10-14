package seedu.address.model.person;

import static seedu.address.commons.util.StringUtil.containsIgnoreCase;
import static seedu.address.logic.parser.CliSyntax.ALL_PREFIXES;
import static seedu.address.model.person.PrefixMapper.PREFIX_FUNCTION_MAP;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
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
                        .map(Object::toString).anyMatch(y -> containsIgnoreCase(y, x));

                boolean checkAttributeTag = person.getTags().stream()
                    .anyMatch(y -> containsIgnoreCase(y.tagName, x));
                return checkAttribute || checkAttributeTag;
            }
        );

        boolean checkAttributes = Arrays.stream(ALL_PREFIXES)
                .map(prefix -> {
                    Function<Person, String> getAttribute = PREFIX_FUNCTION_MAP.get(prefix).andThen(Object::toString);
                    String personAttribute = getAttribute.apply(person);
                    Optional<String> keyword = keywords.getValue(prefix);
                    return keyword.map(x -> containsIgnoreCase(personAttribute, x)).orElse(true);
                })
                .reduce(true, (x, y) -> x && y);

        return checkGeneral && checkAttributes;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }

}

