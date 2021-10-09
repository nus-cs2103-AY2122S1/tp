package seedu.address.model.person.customer;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


public class Customer extends Person {


    // Customer specific data fields
    private final LoyaltyPoints loyaltyPoints;
    private final AllergyList allergies;
    private final SrList specialRequests;



    /**
     * Every field must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Address address, LoyaltyPoints loyaltyPoints,
                    AllergyList allergies, SrList specialRequests, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.allergies = allergies;
        this.loyaltyPoints = loyaltyPoints;
        this.specialRequests = specialRequests;
    }

    /**
     * Every field except for Allergies must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Address address, LoyaltyPoints loyaltyPoints,
                    SrList specialRequests, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.allergies = new AllergyList();
        this.loyaltyPoints = loyaltyPoints;
        this.specialRequests = specialRequests;
    }

    /**
     * Every field except for special requests must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Address address, LoyaltyPoints loyaltyPoints,
                    AllergyList allergies, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.allergies = allergies;
        this.loyaltyPoints = loyaltyPoints;
        this.specialRequests = new SrList();
    }

    /**
     * Every field  except for allergies and special requests must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Address address, LoyaltyPoints loyaltyPoints,
                    Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.allergies = new AllergyList();
        this.loyaltyPoints = loyaltyPoints;
        this.specialRequests = new SrList();
    }

    /**
     * Every field except for loyalty points must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Address address,
                    AllergyList allergies, SrList specialRequests, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.allergies = allergies;
        this.loyaltyPoints = new LoyaltyPoints("0000");
        this.specialRequests = specialRequests;
    }


    /**
     * * Every field except for allergies must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Address address, SrList specialRequests, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.allergies = new AllergyList();
        this.loyaltyPoints = new LoyaltyPoints("0000");
        this.specialRequests = specialRequests;
    }

    /**
     * Every field except special requests must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Address address,
                    AllergyList allergies, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.allergies = allergies;
        this.loyaltyPoints = new LoyaltyPoints("0000");
        this.specialRequests = new SrList();
    }

    /**
     * Every field must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.allergies = new AllergyList();
        this.loyaltyPoints = new LoyaltyPoints("0000");
        this.specialRequests = new SrList();
    }

    public Name getName() {
        return super.getName();
    }

    public Phone getPhone() {
        return super.getPhone();
    }

    public Email getEmail() {
        return super.getEmail();
    }

    public Address getAddress() {
        return super.getAddress();
    }
    public LoyaltyPoints getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public SrList getSpecialRequests() {
        return specialRequests;
    }

    public AllergyList getAllergies() {
        return allergies;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return super.getTags();
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameCustomer(Customer otherCustomer) {
        if (otherCustomer == this) {
            return true;
        }

        return otherCustomer != null
                && otherCustomer.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Customer)) {
            return false;
        }

        Customer otherCustomer = (Customer) other;
        return otherCustomer.getName().equals(getName())
                && otherCustomer.getPhone().equals(getPhone())
                && otherCustomer.getEmail().equals(getEmail())
                && otherCustomer.getAddress().equals(getAddress())
                && otherCustomer.getAllergies().equals(getAllergies())
                && otherCustomer.getSpecialRequests().equals(getSpecialRequests())
                && otherCustomer.getLoyaltyPoints().equals(getLoyaltyPoints())
                && otherCustomer.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return super.hashCode() + Objects.hash(allergies, loyaltyPoints, specialRequests);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Loyalty Points: ")
                .append(getLoyaltyPoints())
                .append("; Allergies: ")
                .append(getAllergies())
                .append("; Special Requests: ")
                .append(getSpecialRequests());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
