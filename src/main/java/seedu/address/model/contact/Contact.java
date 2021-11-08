package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Contact in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Contact {

    // Identity fields
    private final CategoryCode categoryCode;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Review review;
    private final Set<Tag> tags = new HashSet<>();
    private final Rating rating;

    /**
     * Every field must be present and not null.
     */
    public Contact(CategoryCode categoryCode, Name name, Phone phone, Email email, Address address,
                   Review review, Set<Tag> tags, Rating rating) {
        requireAllNonNull(categoryCode, name, phone, email, address, tags);
        this.categoryCode = categoryCode;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.review = review;
        this.address = address;
        this.tags.addAll(tags);
        this.rating = rating;
    }

    public CategoryCode getCategoryCode() {
        return categoryCode;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Rating getRating() {
        return rating;
    }

    public Review getReview() {
        return review;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both contacts have the same name.
     * This defines a weaker notion of equality between two contacts.
     */
    public boolean isSameContact(Contact otherContact) {
        if (otherContact == this) {
            return true;
        }

        return otherContact != null
                && otherContact.getName().equals(getName());
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

        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherContact = (Contact) other;
        return otherContact.getName().equals(getName())
                && otherContact.getPhone().equals(getPhone())
                && otherContact.getEmail().equals(getEmail())
                && otherContact.getAddress().equals(getAddress())
                && otherContact.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Category: ")
                .append(getCategoryCode())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Review: ")
                .append(getReview());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append("; Rating: ")
                .append(getRating());

        return builder.toString();
    }

    /**
     * Returns the formatted export string for each Contact.
     */
    public String toExportFormat() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
            .append(getName())
            .append("\nCategory: ")
            .append(getCategoryCode())
            .append("\nPhone: ")
            .append(getPhone())
            .append("\nEmail: ")
            .append(getEmail())
            .append("\nAddress: ")
            .append(getAddress())
            .append("\nReview: ")
            .append(getReview());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }

        builder.append("\nRating: ")
            .append(getRating())
            .append("\n-------------------------------------------------------\n");

        return builder.toString();
    }

}
