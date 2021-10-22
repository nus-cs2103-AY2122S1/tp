package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Date;
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

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("id") String id, @JsonProperty("date") String date,
                           @JsonProperty("amount") String amount, @JsonProperty("customer") String customer,
                           @JsonProperty("isComplete") String isComplete) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.customer = customer;
        this.isComplete = isComplete;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        id = String.valueOf(source.getId());
        date = source.getDate().toString();
        amount = source.getAmount().toString();
        customer = source.getCustomer().toString();
        isComplete = String.valueOf(source.getIsComplete());;

    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Order toModelType() throws IllegalValueException {

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

        Order newOrder = new Order(modelCustomer, modelDate, modelAmount);

        newOrder.setId(modelId);

        if (isComplete == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "isComplete"));
        }
        if (isComplete.equals("true")) {
            newOrder.setIsComplete(true);
        } else if (isComplete.equals("false")) {
            newOrder.setIsComplete(false);
        } else {
            throw new IllegalValueException("Something is wrong");
        }

        return newOrder;
    }
}
