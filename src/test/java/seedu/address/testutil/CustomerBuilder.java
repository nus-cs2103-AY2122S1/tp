package seedu.address.testutil;


import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.customer.Allergy;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.customer.LoyaltyPoints;
import seedu.address.model.person.customer.SpecialRequest;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;


/**
 * A utility class to help with building Person objects.
 */
public class CustomerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_LP = "3000";
    public static final String DEFAULT_ALLERGIES = "Peanuts, Apples";
    public static final String DEFAULT_SPECIALREQUESTS = "Window seating";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private LoyaltyPoints loyaltyPoints;
    private Set<Allergy> allergies;
    private Set<SpecialRequest> specialRequests;
    private Set<Tag> tags;

    /**
     * Creates a {@code CustomerBuilder} with the default details.
     */
    public CustomerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        loyaltyPoints = new LoyaltyPoints(DEFAULT_LP);
        allergies = new HashSet<>();
        specialRequests = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the CustomerBuilder with the data of {@code customerToCopy}.
     */
    public CustomerBuilder(Customer customerToCopy) {
        name = customerToCopy.getName();
        phone = customerToCopy.getPhone();
        email = customerToCopy.getEmail();
        address = customerToCopy.getAddress();
        loyaltyPoints = customerToCopy.getLoyaltyPoints();
        allergies = new HashSet<>(customerToCopy.getAllergies());
        specialRequests = new HashSet<>(customerToCopy.getSpecialRequests());
        tags = new HashSet<>(customerToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Customer} that we are
     * building.
     */
    public CustomerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code LoyaltyPoints} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withLoyaltyPoints(String loyaltyPoints) {
        this.loyaltyPoints = new LoyaltyPoints(loyaltyPoints);
        return this;
    }
    /**
     * Sets the {@code AllergyList} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withAllergies(String ... allergies) {
        this.allergies = SampleDataUtil.getAllergySet(allergies);
        return this;
    }

    /**
     * Sets the {@code Set<SpecialRequest>} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withSpecialRequests(String ... specialRequests) {
        this.specialRequests = SampleDataUtil.getSpecialRequestSet(specialRequests);
        return this;
    }

    public Customer build() {
        return new Customer(name, phone, email, address, loyaltyPoints, allergies, specialRequests, tags);
    }

}
