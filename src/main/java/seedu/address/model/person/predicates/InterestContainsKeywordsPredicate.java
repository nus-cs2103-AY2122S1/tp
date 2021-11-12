package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import seedu.address.model.person.Person;
import seedu.address.model.person.interests.Interest;

/**
 * Tests that a {@code Person}'s {@code Interests} contains the keywords given.
 */
public class InterestContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> interests;
    private boolean isFindAll;

    /**
     * Constructor for the InterestContainsKeywordsPredicate class
     * @param interests The interests to compare against the {@code Person}'s {@code InterestsList}
     * @param isFindAll True if all keywords need to match, false otherwise
     */
    public InterestContainsKeywordsPredicate(List<String> interests, boolean isFindAll) {
        requireNonNull(interests);
        this.interests = interests;
        this.isFindAll = isFindAll;
    }

    @Override
    public boolean test(Person person) {
        ArrayList<Interest> personInterestsList = person.getInterests().getAllInterests();
        if (isFindAll) {
            return interests.stream().allMatch(interest -> personInterestsList
                    .stream().anyMatch(interest1 -> interest1.value.toLowerCase(Locale.ROOT)
                            .contains(interest.toLowerCase(Locale.ROOT))));
        }
        return interests.stream().anyMatch(interest -> personInterestsList
                        .stream().anyMatch(interest1 -> interest1.value.toLowerCase(Locale.ROOT)
                        .contains(interest.toLowerCase(Locale.ROOT))));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterestContainsKeywordsPredicate // instanceof handles nulls
                && interests.equals(((InterestContainsKeywordsPredicate) other).interests))
                && isFindAll == ((InterestContainsKeywordsPredicate) other).isFindAll; // state check
    }
}
