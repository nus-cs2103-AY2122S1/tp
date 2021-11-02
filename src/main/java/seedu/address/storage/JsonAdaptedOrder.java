package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Date;
import seedu.address.model.Label;
import seedu.address.model.order.Amount;
import seedu.address.model.order.Customer;
import seedu.address.model.order.Order;


/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final String id;
    private final String date;
    private final String amount;
    private final String customer;
    private final String isComplete;
    private final String label;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("id") String id, @JsonProperty("date") String date,
                           @JsonProperty("amount") String amount, @JsonProperty("customer") String customer,
                           @JsonProperty("isComplete") String isComplete, @JsonProperty("label") String label) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.customer = customer;
        this.isComplete = isComplete;
        this.label = label;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        id = String.valueOf(source.getId());
        date = source.getDate().dateString;
        amount = source.getAmount().toString();
        customer = source.getCustomer().toString();
        isComplete = String.valueOf(source.getIsComplete());
        label = source.getLabel().toString();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (label == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Label.class.getSimpleName()));
        }
        if (!Label.isValidLabel(label)) {
            throw new IllegalValueException(Label.MESSAGE_CONSTRAINTS);
        }
        final Label modelLabel = new Label(label);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "id"));
        }
        final long modelId = Long.parseLong(id);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(amount);

        if (customer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Customer.class.getSimpleName()));
        }
        if (!Customer.isValidCustomer(customer)) {
            throw new IllegalValueException(Customer.MESSAGE_CONSTRAINTS);
        }
        final Customer modelCustomer = new Customer(customer);

        Order newOrder = new Order(modelLabel, modelCustomer, modelDate, modelAmount);

        newOrder.setId(modelId);

        if (isComplete == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "isComplete"));
        }
        if (isComplete.equals("true")) {
            newOrder.markCompleted();
        } else if (isComplete.equals("false")) {
            // intentionally allow fall through
        } else {
            throw new IllegalValueException("isComplete field is not in the correct format");
        }

        return newOrder;
    }
}
