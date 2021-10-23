package seedu.address.model.person.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Phone;
import seedu.address.testutil.CustomerBuilder;

class CustomerContainsPhonePredicateTest {
    private static final String DUMMY_PHONE_NUMBER = "98765432";
    private static final String DIFFERENT_PHONE_NUMBER = "12345678";

    @Test
    public void test_customerContainsPhone_returnsTrue() {
        CustomerContainsPhonePredicate predicate =
                new CustomerContainsPhonePredicate(new Phone(DUMMY_PHONE_NUMBER));

        assertTrue(predicate.test(new CustomerBuilder().withPhone(DUMMY_PHONE_NUMBER).build()));
    }

    @Test
    public void test_customerDoesNotContainPhone_returnsFalse() {
        CustomerContainsPhonePredicate predicate =
                new CustomerContainsPhonePredicate(new Phone(DUMMY_PHONE_NUMBER));

        assertFalse(predicate.test(new CustomerBuilder().withPhone(DIFFERENT_PHONE_NUMBER).build()));
    }
}
