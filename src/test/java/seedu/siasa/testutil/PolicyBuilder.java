package seedu.siasa.testutil;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.CoverageExpiryDate;
import seedu.siasa.model.policy.PaymentStructure;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.Title;
import seedu.siasa.model.tag.Tag;
import seedu.siasa.model.util.SampleDataUtil;

public class PolicyBuilder {

    public static final int DEFAULT_COMMISSION_PERCENTAGE = 10;
    public static final int DEFAULT_COMMISSION_NUMBER_OF_PAYMENTS = 10;
    public static final int DEFAULT_PAYMENT_AMOUNT = 100;
    public static final int DEFAULT_PAYMENT_FREQUENCY = 1;
    public static final int DEFAULT_NUMBER_OF_PAYMENTS = 10;
    public static final LocalDate DEFAULT_EXPIRY_DATE = LocalDate.of(2021, 12, 12);
    public static final String DEFAULT_TITLE = "Full Life Plan";

    private Title title;
    private PaymentStructure paymentStructure;
    private CoverageExpiryDate coverageExpiryDate;
    private Commission commission;
    private Set<Tag> tags;

    private Contact owner;

    /**
     * Creates a {@code PolicyBuilder} with the default details and  owner.
     */
    public PolicyBuilder(Contact owner) {
        requireNonNull(owner);
        this.title = new Title(DEFAULT_TITLE);
        this.paymentStructure =
                new PaymentStructure(DEFAULT_PAYMENT_AMOUNT, DEFAULT_PAYMENT_FREQUENCY, DEFAULT_NUMBER_OF_PAYMENTS);
        this.coverageExpiryDate = new CoverageExpiryDate(DEFAULT_EXPIRY_DATE);
        this.commission = new Commission(DEFAULT_COMMISSION_PERCENTAGE, DEFAULT_COMMISSION_NUMBER_OF_PAYMENTS);
        this.owner = owner;
        this.tags = new HashSet<>();
    }

    /**
     * Initializes the PolicyBuilder with the data of {@code policyToCopy}.
     */
    public PolicyBuilder(Policy policyToCopy) {
        requireNonNull(policyToCopy);
        this.title = policyToCopy.getTitle();
        this.paymentStructure = policyToCopy.getPaymentStructure();
        this.coverageExpiryDate = policyToCopy.getCoverageExpiryDate().orElse(null);
        this.commission = policyToCopy.getCommission();
        this.owner = policyToCopy.getOwner();
        this.tags = new HashSet<>(policyToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withPaymentStructure(int paymentAmount, int paymentFrequency, int numberOfPayments) {
        this.paymentStructure = new PaymentStructure(paymentAmount, paymentFrequency, numberOfPayments);
        return this;
    }

    /**
     * Sets the {@code paymentAmount} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withPaymentStructure(int paymentAmount) {
        this.paymentStructure = new PaymentStructure(paymentAmount, 1, 1);
        return this;
    }

    /**
     * Sets the {@code Commission} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withCommission(int commissionPercentage, int numberOfPayments) {
        this.commission = new Commission(commissionPercentage, numberOfPayments);
        return this;
    }

    /**
     * Sets the {@code Commission} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withCommission(int commissionPercentage) {
        this.commission = new Commission(commissionPercentage, 0);
        return this;
    }

    /**
     * Sets the {@code ExpiryDate} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withExpiryDate(LocalDate date) {
        this.coverageExpiryDate = new CoverageExpiryDate(date);
        return this;
    }

    /**
     * Removes the {@code ExpiryDate} of the {@code Policy} that we are building.
     * @return
     */
    public PolicyBuilder withNoExpiryDate() {
        this.coverageExpiryDate = null;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Policy} that we are building.
     */
    public PolicyBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Owner} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withOwner(Contact owner) {
        this.owner = owner;
        return this;
    }

    public Policy build() {
        return new Policy(title, paymentStructure, coverageExpiryDate, commission, owner, tags);
    }

}
