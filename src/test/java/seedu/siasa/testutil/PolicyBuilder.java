package seedu.siasa.testutil;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.ExpiryDate;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.Price;
import seedu.siasa.model.policy.Title;

public class PolicyBuilder {
    public static final int DEFAULT_COMMISSION = 10;
    public static final int DEFAULT_PRICE = 100;
    public static final LocalDate DEFAULT_EXPIRY_DATE = LocalDate.now().plusYears(1);
    public static final String DEFAULT_TITLE = "Full Life Plan";

    private Title title;
    private Price price;
    private ExpiryDate expiryDate;
    private Commission commission;

    private Person owner;

    /**
     * Creates a {@code PolicyBuilder} with the default details and set to owner.
     */
    public PolicyBuilder(Person owner) {
        requireNonNull(owner);
        this.title = new Title(DEFAULT_TITLE);
        this.price = new Price(DEFAULT_PRICE);
        this.expiryDate = new ExpiryDate(DEFAULT_EXPIRY_DATE);
        this.commission = new Commission(DEFAULT_COMMISSION);
        this.owner = owner;
    }

    /**
     * Initializes the PolicyBuilder with the data of {@code policyToCopy}.
     */
    public PolicyBuilder(Policy policyToCopy) {
        requireNonNull(policyToCopy);
        this.title = policyToCopy.getTitle();
        this.price = policyToCopy.getPrice();
        this.expiryDate = policyToCopy.getExpiryDate();
        this.commission = policyToCopy.getCommission();
        this.owner = policyToCopy.getOwner();
    }

    public PolicyBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    public PolicyBuilder withPrice(int price) {
        this.price = new Price(price);
        return this;
    }

    public PolicyBuilder withCommission(int commission) {
        this.commission = new Commission(commission);
        return this;
    }

    public PolicyBuilder withExpiryDate(LocalDate date) {
        this.expiryDate = new ExpiryDate(date);
        return this;
    }

    public PolicyBuilder withOwner(Person owner) {
        this.owner = owner;
        return this;
    }

    public Policy build() {
        return new Policy(title, price, expiryDate, commission, owner);
    }

}
