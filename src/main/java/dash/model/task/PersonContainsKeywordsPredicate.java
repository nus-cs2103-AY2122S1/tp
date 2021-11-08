package dash.model.task;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import dash.model.person.Person;

/**
 * Tests that a {@code Task}'s {@code TaskDescription} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public PersonContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .allMatch(keyword -> {
                    Set<Person> personSet = task.getPeople();
                    boolean condition = false;
                    for (Person p : personSet) {
                        String i = p.getName().fullName;
                        condition = keyword.equals(i);
                        if (condition) {
                            break;
                        }
                    }
                    return condition;
                });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }

}
