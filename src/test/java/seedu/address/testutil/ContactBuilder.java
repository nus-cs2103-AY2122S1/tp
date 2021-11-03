package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.contact.Address;
import seedu.address.model.contact.CategoryCode;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;
import seedu.address.model.contact.Rating;
import seedu.address.model.contact.Review;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Contact objects.
 */
public class ContactBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REVIEW = "Great place";
    public static final String DEFAULT_CATEGORY_CODE = "att";
    public static final String DEFAULT_RATING = "2";

    private CategoryCode category;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Review review;
    private Set<Tag> tags;
    private Rating rating;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public ContactBuilder() {
        category = new CategoryCode(DEFAULT_CATEGORY_CODE);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        review = new Review(DEFAULT_REVIEW);
        tags = new HashSet<>();
        rating = new Rating(DEFAULT_RATING);
    }

    /**
     * Initializes the ContactBuilder with the data of {@code contactToCopy}.
     */
    public ContactBuilder(Contact contactToCopy) {
        category = contactToCopy.getCategoryCode();
        name = contactToCopy.getName();
        phone = contactToCopy.getPhone();
        email = contactToCopy.getEmail();
        address = contactToCopy.getAddress();
        review = contactToCopy.getReview();
        tags = new HashSet<>(contactToCopy.getTags());
        rating = contactToCopy.getRating();
    }

    /**
     * Sets the {@code Name} of the {@code Contact} that we are building.
     */
    public ContactBuilder withCategoryCode(String code) {
        this.category = new CategoryCode(code);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Contact} that we are building.
     */
    public ContactBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Contact} that we are building.
     */
    public ContactBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Contact} that we are building.
     */
    public ContactBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Contact} that we are building.
     */
    public ContactBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Contact} that we are building.
     */
    public ContactBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Contact} that we are building.
     */
    public ContactBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the empty {@code Rating} of the {@code Contact} that we are building.
     */
    public ContactBuilder withRating() {
        this.rating = new Rating();
        return this;
    }

    /**
     * Sets the {@code Review} of the {@code Contact} that we are building.
     */
    public ContactBuilder withReview(String review) {
        this.review = new Review(review);
        return this;
    }

    public Contact build() {
        return new Contact(category, name, phone, email, address, review, tags, rating);
    }

}
