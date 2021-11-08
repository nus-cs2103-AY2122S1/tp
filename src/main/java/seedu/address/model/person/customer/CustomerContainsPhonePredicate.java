package seedu.address.model.person.customer;

import java.util.function.Predicate;

import seedu.address.model.person.Phone;

public class CustomerContainsPhonePredicate implements Predicate<Customer> {
    private final Phone phone;

    public CustomerContainsPhonePredicate(Phone phone) {
        this.phone = phone;
    }

    @Override
    public boolean test(Customer customer) {
        return customer.getPhone().equals(phone);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same objects
                || (other instanceof CustomerContainsPhonePredicate // instanceof handles null
                && phone.equals(((CustomerContainsPhonePredicate) other).phone));
    }
}
