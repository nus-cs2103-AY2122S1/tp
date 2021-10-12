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
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.customer.Allergy;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.customer.LoyaltyPoints;
import seedu.address.model.person.customer.SpecialRequest;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Customer}.
 */
class JsonAdaptedCustomer {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Customer's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String loyaltyPoints;
    private final List<JsonAdaptedAllergy> allergies = new ArrayList<>();
    private final List<JsonAdaptedSpecialRequest> specialRequests = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCustomer} with the given customer details.
     */
    @JsonCreator
    public JsonAdaptedCustomer(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("loyaltyPoints") String loyaltyPoints,
                             @JsonProperty("allergies") List<JsonAdaptedAllergy> allergies,
                             @JsonProperty("specialRequests") List<JsonAdaptedSpecialRequest> specialRequests,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.loyaltyPoints = loyaltyPoints;
        if (allergies != null) {
            this.allergies.addAll(allergies);
        }
        if (specialRequests != null) {
            this.specialRequests.addAll(specialRequests);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedCustomer(Customer source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        loyaltyPoints = source.getLoyaltyPoints().value;
        allergies.addAll(source.getAllergies().stream()
                .map(JsonAdaptedAllergy::new)
                .collect(Collectors.toList()));
        specialRequests.addAll(source.getSpecialRequests().stream()
                .map(JsonAdaptedSpecialRequest::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted customer object into the model's {@code Customer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted customer.
     */
    public Customer toModelType() throws IllegalValueException {
        final List<Tag> customerTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            customerTags.add(tag.toModelType());
        }
        final List<Allergy> customerAllergies = new ArrayList<>();
        for (JsonAdaptedAllergy allergy : allergies) {
            customerAllergies.add(allergy.toModelType());
        }
        final List<SpecialRequest> customerSpecialRequests = new ArrayList<>();
        for (JsonAdaptedSpecialRequest specialRequest : specialRequests) {
            customerSpecialRequests.add(specialRequest.toModelType());
        }
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

        if (loyaltyPoints == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LoyaltyPoints.class.getSimpleName()));
        }
        if (!LoyaltyPoints.isValidLoyaltyPoints(loyaltyPoints)) {
            throw new IllegalValueException(LoyaltyPoints.MESSAGE_CONSTRAINTS);
        }
        final LoyaltyPoints modelLoyaltyPoints = new LoyaltyPoints(loyaltyPoints);

        final Set<Tag> modelTags = new HashSet<>(customerTags);
        final Set<Allergy> modelAllergies = new HashSet<>(customerAllergies);
        final Set<SpecialRequest> modelSpecialRequests = new HashSet<>(customerSpecialRequests);
        return new Customer(modelName, modelPhone, modelEmail, modelAddress, modelLoyaltyPoints,
                modelAllergies, modelSpecialRequests, modelTags);
    }

}
