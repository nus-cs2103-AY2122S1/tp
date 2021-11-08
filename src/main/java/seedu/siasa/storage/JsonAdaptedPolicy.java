package seedu.siasa.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.siasa.commons.exceptions.IllegalValueException;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.CoverageExpiryDate;
import seedu.siasa.model.policy.PaymentStructure;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.Title;
import seedu.siasa.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Policy}.
 */
public class JsonAdaptedPolicy {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Policy's %s field is missing!";

    private final String title;
    private final JsonAdaptedPaymentStructure paymentStructure;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String coverageExpiryDate;
    private final JsonAdaptedCommission commission;
    private final JsonAdaptedContact owner;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given policy details.
     */
    @JsonCreator
    public JsonAdaptedPolicy(@JsonProperty("title") String title,
                             @JsonProperty("paymentStructure") JsonAdaptedPaymentStructure paymentStructure,
                             @JsonProperty("coverageExpiryDate") String coverageExpiryDate,
                             @JsonProperty("commission") JsonAdaptedCommission commission,
                             @JsonProperty("owner") JsonAdaptedContact owner,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.title = title;
        this.paymentStructure = paymentStructure;
        this.coverageExpiryDate = coverageExpiryDate;
        this.commission = commission;
        this.owner = owner;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        title = source.getTitle().toString();
        paymentStructure = new JsonAdaptedPaymentStructure(source.getPaymentStructure());
        coverageExpiryDate = source.getCoverageExpiryDate().map(date -> date.toString()).orElse(null);
        commission = new JsonAdaptedCommission(source.getCommission());
        owner = new JsonAdaptedContact(source.getOwner());
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    public JsonAdaptedContact getOwner() {
        return owner;
    }

    /**
     * Converts this Jackson-friendly adapted policy object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted policy.
     */
    public Policy toModelType(Contact policyOwner) throws IllegalValueException {
        final List<Tag> policyTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            policyTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (paymentStructure == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, PaymentStructure.class.getSimpleName()));
        }

        final PaymentStructure modelPaymentStructure = paymentStructure.toModelType();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final CoverageExpiryDate modelCoverageExpiryDate;
        if (coverageExpiryDate != null) {
            try {
                LocalDate.parse(coverageExpiryDate, formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalValueException(e.getMessage());
            }

            LocalDate date = LocalDate.parse(coverageExpiryDate, formatter);
            modelCoverageExpiryDate = new CoverageExpiryDate(date);
        } else {
            modelCoverageExpiryDate = null;
        }

        if (commission == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Commission.class.getSimpleName()));
        }

        final Commission modelCommission = commission.toModelType(modelPaymentStructure);

        if (policyOwner == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Contact.class.getSimpleName()));
        }

        final Set<Tag> modelTags = new HashSet<>(policyTags);
        return new Policy(modelTitle, modelPaymentStructure, modelCoverageExpiryDate, modelCommission, policyOwner,
                modelTags);
    }
}
