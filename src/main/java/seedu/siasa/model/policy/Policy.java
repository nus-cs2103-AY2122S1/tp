package seedu.siasa.model.policy;

import static seedu.siasa.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class Policy {

    private final Title title;
    private final Price price;
    private final ExpiryDate expiryDate;
    private final Commission commission;

    public Policy(Title title, Price price, ExpiryDate expiryDate, Commission commission) {
        requireAllNonNull(title, price, expiryDate, commission);
        this.title = title;
        this.price = price;
        this.expiryDate = expiryDate;
        this.commission = commission;
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

    public boolean isSamePolicy(Policy otherPolicy) {
        if (otherPolicy == this) {
            return true;
        }

        // TODO: check for equal owner
        return otherPolicy != null
                && otherPolicy.getTitle().equals(getTitle());
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
        // TODO: check for equal owner
        return otherPolicy.getTitle().equals(getTitle())
                && otherPolicy.getPrice().equals(getPrice())
                && otherPolicy.getCommission().equals(getCommission())
                && otherPolicy.getExpiryDate().equals(getExpiryDate());
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
