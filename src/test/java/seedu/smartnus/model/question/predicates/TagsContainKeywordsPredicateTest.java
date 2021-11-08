package seedu.smartnus.model.question.predicates;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.smartnus.testutil.QuestionBuilder;

public class TagsContainKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagsContainKeywordsPredicate firstPredicate = new TagsContainKeywordsPredicate(firstPredicateKeywordList);
        TagsContainKeywordsPredicate secondPredicate = new TagsContainKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same list of keywords -> returns true
        TagsContainKeywordsPredicate secondPredicateCopy =
                new TagsContainKeywordsPredicate(secondPredicateKeywordList);
        assertTrue(secondPredicate.equals(secondPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainKeywords_returnsTrue() {
        // One keyword
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(Collections.singletonList("Math"));
        assertTrue(predicate.test(new QuestionBuilder().withTags("English", "Math").build()));

        // Multiple keywords
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("Java", "style"));
        assertTrue(predicate.test(new QuestionBuilder().withTags("Java", "style").build()));

        // Only one matching keyword
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("MIPS", "Math"));
        assertTrue(predicate.test(new QuestionBuilder().withTags("MIPS", "datapath").build()));

        // Mixed-case keywords
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("dATaPAtH"));
        assertTrue(predicate.test(new QuestionBuilder().withTags("datapath").build()));
    }

    @Test
    public void test_tagsDoNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new QuestionBuilder().withTags("CS2103T").build()));

        // Non-matching keyword
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("MIPS"));
        assertFalse(predicate.test(new QuestionBuilder().withTags("CS2103T", "Java", "style").build()));

        // Keywords match importance but does not match tags
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("1", "Java", "style"));
        assertFalse(predicate.test(new QuestionBuilder().withTags("CS2103T").withImportance("1").build()));
    }
}
