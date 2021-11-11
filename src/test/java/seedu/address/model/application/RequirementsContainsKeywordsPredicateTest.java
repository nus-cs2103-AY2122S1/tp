package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequirementsContainsKeywordsPredicateTest {

    private List<String> keywords = new ArrayList<>();

    private RequirementsContainsKeywordsPredicate predicate = new RequirementsContainsKeywordsPredicate(keywords);

    @BeforeEach
    public void setUp() {
        keywords.add("CV");
    }

    @Test
    public void test() {

        assertTrue(predicate.test(BYTEDANCE));

        assertFalse(predicate.test(AMAZON));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(predicate.equals(predicate));

        // different types -> returns false
        assertFalse(predicate.equals(1));

        // null -> returns false
        assertFalse(predicate.equals(null));

        // same keywords -> returns true
        assertTrue(predicate.equals(new RequirementsContainsKeywordsPredicate(keywords)));

        // different keywords -> returns false
        List<String> otherKeywords = new ArrayList<>();
        assertFalse(predicate.equals(new RequirementsContainsKeywordsPredicate(otherKeywords)));
    }
}
