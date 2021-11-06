package seedu.address.model.person.supplier;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.ParserUtil.DATE_TIME_FORMATTERS;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Supplier's delivery details in RHRH.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeliveryDetail(String)}
 */
public class DeliveryDetails {

    public static final String MESSAGE_CONSTRAINTS =
            "Delivery details is wrongly formatted. You need to input a date in yyyy-mm-dd or dd-mm-yyyy "
                    + "format and a time in HH:mm or HHmm (24hr clock) format (eg: 1800 or 18:00 for 6 pm). "
                    + "You can choose to entire enter a date first or time first in any of the formats mentioned";

    private static DateTimeFormatter chosenFormat = null;

    public final LocalDateTime deliveryDetails;
    private final String deliveryDetailsString;

    /**
     * Constructs a {@code Name}.
     *
     * @param deliveryDetails A valid delivery detail.
     */
    public DeliveryDetails(String deliveryDetails) {
        requireNonNull(deliveryDetails);
        checkArgument(isValidDeliveryDetail(deliveryDetails), MESSAGE_CONSTRAINTS);
        this.deliveryDetails = LocalDateTime.parse(deliveryDetails, chosenFormat);
        this.deliveryDetailsString = this.deliveryDetails.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a"));
    }

    /**
     * Returns true if a given string is a valid delivery detail.
     */
    public static boolean isValidDeliveryDetail(String test) {
        LocalDateTime temp = null;
        for (DateTimeFormatter dateTimeFormatter : DATE_TIME_FORMATTERS) {
            try {
                temp = LocalDateTime.parse(test, dateTimeFormatter);
                chosenFormat = dateTimeFormatter;
            } catch (DateTimeException e) {
                //do nothing
            }
        }
        return temp != null;
    }

    /**
     * Returns the original date time string entered into the app by the user.
     */
    public String getUnformattedDeliveryDetailsString() {
        return this.deliveryDetails.format(chosenFormat);
    }

    @Override
    public String toString() {
        return this.deliveryDetailsString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliveryDetails // instanceof handles nulls
                && this.deliveryDetails.equals(((DeliveryDetails) other).deliveryDetails)); // state check
    }

    @Override
    public int hashCode() {
        return this.deliveryDetails.hashCode();
    }
}
