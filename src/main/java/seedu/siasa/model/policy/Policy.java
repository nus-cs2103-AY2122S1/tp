package seedu.siasa.model.policy;

import static seedu.siasa.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.siasa.model.person.Person;

/**
 * Represents a Policy in SIASA.
 * Guarantees: details and owner are present and not null, field values are validated, immutable.
 */
public class Policy {

    private final Title title;
    private final Price price;
    private final ExpiryDate expiryDate;
    private final Commission commission;

    // Policy should always have an owner
    private final Person owner;

    /**
     * Every field must be present and not null.
     */
    public Policy(Title title, Price price, ExpiryDate expiryDate, Commission commission, Person owner) {
        requireAllNonNull(title, price, expiryDate, commission, owner);
        this.title = title;
        this.price = price;
        this.expiryDate = expiryDate;
        this.commission = commission;
        this.owner = owner;
    }

    public Title getTitle() {
        return title;
    }

    public Price getPrice() {
        return price;
    }

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    public Commission getCommission() {
        return commission;
    }

    public Person getOwner() {
        return owner;
    }

    /**
     *  Returns true if both policies have the same title and owner (by identity).
     *  this defines a weaker notion of equality between two policies.
     */
    public boolean isSamePolicy(Policy otherPolicy) {
        if (otherPolicy == this) {
            return true;
        }

        // Note the use of isSamePerson instead of equals
        return otherPolicy != null
                && otherPolicy.getTitle().equals(getTitle())
                && otherPolicy.getOwner().isSamePerson(getOwner());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Policy)) {
            return false;
        }

        Policy otherPolicy = (Policy) other;
        // Note the use of isSamePerson instead of equals
        return otherPolicy.getTitle().equals(getTitle())
                && otherPolicy.getPrice().equals(getPrice())
                && otherPolicy.getCommission().equals(getCommission())
                && otherPolicy.getExpiryDate().equals(getExpiryDate())
                && otherPolicy.getOwner().isSamePerson(getOwner());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, price, commission, expiryDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Price: ")
                .append(getPrice())
                .append("; Commission: ")
                .append(getCommission())
                .append("; Expiry Date: ")
                .append(getExpiryDate());

        return builder.toString();
    }
}
