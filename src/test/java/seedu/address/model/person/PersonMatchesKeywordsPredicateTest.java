package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand.FindCondition;
import seedu.address.testutil.PersonBuilder;

public class PersonMatchesKeywordsPredicateTest {

    @Test
    public void test_oneField_matchesKeywords_returnsTrue() {

        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();

        // One keyword
        predicate.setNameKeywords(List.of("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Match all keywords
        predicate.setNameKeywords(List.of("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Mixed-case keywords
        predicate.setNameKeywords(List.of("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keyword is substring of field word
        predicate.setNameKeywords(List.of("lee"));
        assertTrue(predicate.test(new PersonBuilder().withName("Aileen").build()));
    }

    @Test
    public void test_oneField_matchesKeywords_returnsFalse() {

        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();

        // Only one matching keyword
        predicate.setNameKeywords(List.of("Alice", "Tan"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Lee").build()));
    }

    @Test
    public void test_tagsMatchKeywords_returnsTrue() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();

        // Match tag name exactly
        predicate.setTagKeywords(List.of("Math"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Math", "paid").build()));

        // Mixed-case keywords
        predicate.setTagKeywords(List.of("MaTh"));
        assertTrue(predicate.test(new PersonBuilder().withTags("math").build()));

        // Match all tag keywords
        predicate.setTagKeywords(List.of("Math", "paid"));
        predicate.setCondition(FindCondition.ALL);
        assertTrue(predicate.test(new PersonBuilder().withTags("Math", "paid").build()));

        // Match one tag keyword
        predicate.setTagKeywords(List.of("Math", "paid"));
        predicate.setCondition(FindCondition.ANY);
        assertTrue(predicate.test(new PersonBuilder().withTags("Math", "science").build()));

        // Match none tag keywords
        predicate.setTagKeywords(List.of("Math", "paid"));
        predicate.setCondition(FindCondition.NONE);
        assertTrue(predicate.test(new PersonBuilder().withTags("unpaid", "science").build()));
    }

    @Test
    public void test_tagsMatchKeywords_returnsFalse() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();

        // Keyword does not match tag name exactly
        predicate.setTagKeywords(List.of("Math"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Mathematics").build()));

        // Does not match all tag keywords
        predicate.setTagKeywords(List.of("Math", "paid"));
        predicate.setCondition(FindCondition.ALL);
        assertFalse(predicate.test(new PersonBuilder().withTags("Math", "unpaid").build()));

        // Does not match any tag keyword
        predicate.setTagKeywords(List.of("Math", "paid"));
        predicate.setCondition(FindCondition.ANY);
        assertFalse(predicate.test(new PersonBuilder().withTags("science").build()));

        // Does not match none tag keywords
        predicate.setTagKeywords(List.of("Math", "paid"));
        predicate.setCondition(FindCondition.NONE);
        assertFalse(predicate.test(new PersonBuilder().withTags("Math").build()));
    }

    @Test
    public void test_multipleFields_AllMatchKeywords_returnsTrue() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        predicate.setNameKeywords(List.of("Alice"));
        predicate.setAddressKeywords(List.of("clementi"));
        predicate.setPhoneKeywords(List.of("9876"));
        predicate.setEmailKeywords(List.of("gmail"));
        predicate.setParentPhoneKeywords(List.of("5555"));
        predicate.setParentEmailKeywords(List.of("john"));
        predicate.setTagKeywords(List.of("Math", "paid"));

        // Default condition is set to match all
        Person person = new PersonBuilder().withName("Alice Tan").withAddress("Clementi Road").withPhone("98765432")
            .withEmail("alice@gmail.com").withParentPhone("99995555").withParentEmail("john.tan@gmail.com")
            .withTags("Math", "paid").build();
        assertTrue(predicate.test(person));

        // Explicitly set condition to match all
        predicate.setCondition(FindCondition.ALL);
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_multipleFields_AnyMatchKeywords_returnsTrue() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        // Does not match
        predicate.setNameKeywords(List.of("Alice", "Tan"));

        // Match
        predicate.setAddressKeywords(List.of("clementi"));

        Person person = new PersonBuilder().withName("John").withAddress("Clementi Road").build();
        predicate.setCondition(FindCondition.ANY);
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_multipleFields_NoneMatchKeywords_returnsTrue() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        predicate.setNameKeywords(List.of("Alice", "Tan"));
        predicate.setAddressKeywords(List.of("clementi"));
        predicate.setPhoneKeywords(List.of("9876"));
        predicate.setEmailKeywords(List.of("gmail"));
        predicate.setParentPhoneKeywords(List.of("5555"));
        predicate.setParentEmailKeywords(List.of("john"));
        predicate.setTagKeywords(List.of("Math", "paid"));

        // Default condition is set to match all
        Person person = new PersonBuilder().withName("John").withAddress("Yishun ave").withPhone("99998888")
            .withEmail("john@nus.edu.sg").withParentPhone("988665544").withParentEmail("benny@gmail.com")
            .withTags("Science", "unpaid").build();

        // Explicitly set condition to match all
        predicate.setCondition(FindCondition.NONE);
        assertTrue(predicate.test(person));
    }

}
