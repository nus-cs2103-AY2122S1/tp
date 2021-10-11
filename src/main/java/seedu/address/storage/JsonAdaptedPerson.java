package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.CategoryCode;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Review;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String category;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String review;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String rating;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("category") String category, @JsonProperty("name") String name,
                             @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                             @JsonProperty("address") String address,
                             @JsonProperty("rating") String rating,
                             @JsonProperty("review") String review,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.category = category;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.rating = rating;
        this.review = review;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        category = source.getCategoryCode().toString();
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        review = source.getReview().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        rating = source.getRating().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        if (category == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CategoryCode.class.getSimpleName()));
        }
        if (!CategoryCode.isValidCategory(category)) {
            throw new IllegalValueException(CategoryCode.MESSAGE_CONSTRAINTS);
        }
        final CategoryCode modelCategory = new CategoryCode(category);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (review == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Review.class.getSimpleName()));
        }
        if (!Review.isValidReview(review)) {
            throw new IllegalValueException(Review.MESSAGE_CONSTRAINTS);
        }
        final Review modelReview = new Review(review);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (rating == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName()));
        }
        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        }
        final Rating modelRating = new Rating(rating);

        return new Person(modelCategory, modelName, modelPhone, modelEmail, modelAddress, modelReview, modelTags, modelRating);

    }

}
