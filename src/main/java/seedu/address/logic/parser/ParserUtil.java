package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.client.Address;
import seedu.address.model.client.Email;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.ID;
import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;
import seedu.address.model.product.UnitPrice;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        requireNonNull(oneBasedIndex);

        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);

        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phoneNumber} into a {@code PhoneNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phoneNumber} is invalid.
     */
    public static PhoneNumber parsePhoneNumber(String phoneNumber) throws ParseException {
        requireNonNull(phoneNumber);

        String trimmedPhoneNumber = phoneNumber.trim();
        if (!PhoneNumber.isValidPhoneNumber(trimmedPhoneNumber)) {
            throw new ParseException(PhoneNumber.MESSAGE_CONSTRAINTS);
        }

        return new PhoneNumber(trimmedPhoneNumber);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);

        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }

        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);

        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }

        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String unitPrice} into a {@code UnitPrice}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code unitPrice} is invalid.
     */
    public static UnitPrice parseUnitPrice(String unitPrice) throws ParseException {
        requireNonNull(unitPrice);

        String trimmedUnitPrice = unitPrice.trim();
        if (!UnitPrice.isValidUnitPrice(trimmedUnitPrice)) {
            throw new ParseException(UnitPrice.MESSAGE_CONSTRAINTS);
        }

        return new UnitPrice(trimmedUnitPrice);
    }

    /**
     * Parses a {@code String quantity} into a {@code Quantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param quantity Quantity of a product as a string.
     * @return Quantity of a product as a {@code Quantity}.
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static Quantity parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);

        String trimmedQuantity = quantity.trim();
        if (!Quantity.isValidQuantity(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }

        return new Quantity(trimmedQuantity);
    }

    /**
     * Parses {@code Collection<String> orders} into a {@code Set<Order>}.
     */
    public static Set<Order> parseOrders(Collection<String> orders, Model model) throws ParseException {
        requireNonNull(orders);

        final Set<Order> orderSet = new HashSet<>();
        for (String order : orders) {
            orderSet.add(parseOrder(order, model));
        }

        return orderSet;
    }

    /**
     * Parses a {@code String order} into a {@code Order}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code order} is invalid.
     */
    public static Order parseOrder(String order, Model model) throws ParseException {
        requireNonNull(order);

        String trimmedOrder = order.trim();
        if (!trimmedOrder.matches(Order.REGEX)) {
            throw new ParseException(Order.MESSAGE_CONSTRAINTS);
        }

        String[] args = trimmedOrder.split(" ");
        ID productId = new ID(args[0]);
        Quantity quantity = new Quantity(args[1]);
        LocalDate time = getDate(args[2]);

        Order orderToAdd;
        try {
            orderToAdd = new Order(productId, quantity, time, model);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }

        return orderToAdd;
    }

    private static LocalDate getDate(String timeStr) throws ParseException {
        if (timeStr.length() <= 5) {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            timeStr = String.format("%d/%s", year, timeStr);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        LocalDate time;

        try {
            time = LocalDate.parse(timeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException(Order.MESSAGE_CONSTRAINTS);
        }

        return time;
    }
}
