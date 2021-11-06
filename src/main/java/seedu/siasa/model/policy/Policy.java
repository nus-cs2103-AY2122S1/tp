package seedu.siasa.model.policy;

import static seedu.siasa.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.tag.Tag;

/**
 * Represents a Policy in SIASA.
 * Guarantees: details and owner are present and not null, field values are validated, immutable.
 */
public class Policy {

    public static final String MESSAGE_NO_EXPIRY = "No coverage expiry defined.";

    private final Title title;
    private final PaymentStructure paymentStructure;
    private final CoverageExpiryDate coverageExpiryDate;
    private final Commission commission;
    private final Set<Tag> tags = new HashSet<>();

    // Policy should always have an owner
    private final Contact owner;

    /**
     * coverageExpiryDate can be optional and hence null.
     */
    public Policy(Title title, PaymentStructure paymentStructure, CoverageExpiryDate coverageExpiryDate,
                  Commission commission, Contact owner, Set<Tag> tags) {
        requireAllNonNull(title, paymentStructure, commission, owner, tags);
        this.title = title;
        this.paymentStructure = paymentStructure;
        this.coverageExpiryDate = coverageExpiryDate;
        this.commission = commission;
        this.owner = owner;
        this.tags.addAll(tags);
    }

    public Title getTitle() {
        return title;
    }

    public PaymentStructure getPaymentStructure() {
        return paymentStructure;
    }

    public Optional<CoverageExpiryDate> getCoverageExpiryDate() {
        return Optional.ofNullable(coverageExpiryDate);
    }

    public Commission getCommission() {
        return commission;
    }

    public Contact getOwner() {
        return owner;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public int compareTotalCommission(Policy other) {
        return Double.compare(getTotalCommission(), other.getTotalCommission());
    }

    public double getTotalCommission() {
        return ((double) commission.commissionPercentage / 100) * (double) paymentStructure.paymentAmount
                * (double) commission.numberOfPayments;
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
                && otherPolicy.getOwner().isSameContact(getOwner());
    }

    /**
     * Returns true if both policies have the same owner (by identity)
     * and the policy titles differ by less than 2 edit distance.
     */
    public boolean isSimilarPolicy(Policy otherPolicy) {
        return otherPolicy != null
                && otherPolicy.getOwner().isSameContact(getOwner())
                && otherPolicy.getTitle().isSimilarTo(getTitle());
    }

    public boolean isExpiringBefore(LocalDate cutOff) {
        return getCoverageExpiryDate().map(date -> date.value.isBefore(cutOff)).orElse(false);
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
                && otherPolicy.getPaymentStructure().equals(getPaymentStructure())
                && otherPolicy.getCommission().equals(getCommission())
                && otherPolicy.getCoverageExpiryDate().equals(getCoverageExpiryDate())
                && otherPolicy.getOwner().isSameContact(getOwner())
                && otherPolicy.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, paymentStructure, commission, coverageExpiryDate, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Payment Structure: ")
                .append(getPaymentStructure())
                .append("; Commission: ")
                .append(getCommission());
        if (getCoverageExpiryDate().isPresent()) {
            builder.append("; Expiry Date: ")
                    .append(getCoverageExpiryDate().get());
        }
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
