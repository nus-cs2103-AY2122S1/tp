package seedu.modulink.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.tag.Mod;

public class ModuleContainsKeywordsPredicateTest {
    @Test
    public void equals() throws ParseException {
        Set<Mod> firstPredicateKeywordList = Set.of(new Mod("CS2100"));
        Set<Mod> secondPredicateKeywordList = Set.of(new Mod("CS2103T"));

        ModuleContainsKeywordsPredicate firstPredicate =
                new ModuleContainsKeywordsPredicate(firstPredicateKeywordList);
        ModuleContainsKeywordsPredicate secondPredicate =
                new ModuleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleContainsKeywordsPredicate firstPredicateCopy =
                new ModuleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test() throws ParseException {
        Set<Mod> firstPredicateKeywordList = Set.of(new Mod("CS2100 need member"));
        Set<Mod> secondPredicateKeywordList = Set.of(new Mod("CS2100 need group"));

        ModuleContainsKeywordsPredicate firstPredicate = new ModuleContainsKeywordsPredicate(firstPredicateKeywordList);
        ModuleContainsKeywordsPredicate secondPredicate =
                new ModuleContainsKeywordsPredicate(secondPredicateKeywordList);
        Person person = new Person(new Name("alex"), new StudentId("A1234589R"), new Phone("12332110"),
                new Email("alex@example.com"), new GitHubUsername("alexyeoh"),
                null, false,
                firstPredicateKeywordList, false);

        Person personWithDifferentMod = new Person(new Name("alex"), new StudentId("A1234589R"), new Phone("12332110"),
                new Email("alex@example.com"), new GitHubUsername("alexyeoh"),
                null, false,
                secondPredicateKeywordList, false);

        assertTrue(firstPredicate.test(person));
        assertTrue(secondPredicate.test(personWithDifferentMod));

        assertFalse(firstPredicate.test(personWithDifferentMod));
        assertFalse(secondPredicate.test(person));
    }

    @Test
    public void testOnOwnProfile() throws ParseException {
        Set<Mod> firstPredicateKeywordList = Set.of(new Mod("CS2100 need member"));
        ModuleContainsKeywordsPredicate firstPredicate = new ModuleContainsKeywordsPredicate(firstPredicateKeywordList);
        Person myProfile = new Person(new Name("alex"), new StudentId("A1234589R"), new Phone("12332110"),
                new Email("alex@example.com"), new GitHubUsername("alexyeoh"),
                null, false,
                firstPredicateKeywordList, true);

        assertFalse(firstPredicate.test(myProfile));
    }
}
