package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalApplications.AMAZON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InterviewContainsKeywordsPredicateTest {

    private List<String> keywords = new ArrayList<>();

    private InterviewContainsKeywordsPredicate predicate = new InterviewContainsKeywordsPredicate(keywords);

    @BeforeEach
    public void setUp() {
        keywords.add("test");
    }

    @Test
    public void test() {
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
        assertTrue(predicate.equals(new InterviewContainsKeywordsPredicate(keywords)));

        // different keywords -> returns false
        List<String> otherKeywords = new ArrayList<>();
        assertFalse(predicate.equals(new InterviewContainsKeywordsPredicate(otherKeywords)));
    }
}
