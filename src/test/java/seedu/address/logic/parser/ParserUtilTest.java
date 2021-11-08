package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalProducts.CANNON;
import static seedu.address.testutil.TypicalProducts.DAISY;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;
import seedu.address.model.client.Address;
import seedu.address.model.client.Email;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.ID;
import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Product;
import seedu.address.model.product.Quantity;
import seedu.address.model.product.UnitPrice;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE_NUMBER = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_UNIT_PRICE = "-3";
    private static final String INVALID_QUANTITY = "abc";
    private static final String INVALID_ORDER = "abc";
    private static final String INVALID_ORDER_ID = "0 1 1/1";
    private static final String INVALID_ORDER_QUANTITY = CANNON.getId() + " 2 1/1";
    private static final String INVALID_ORDER_TIME = CANNON.getId() + " 1 13/56";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_Phone_Number = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_UNIT_PRICE = "10.00";
    private static final String VALID_QUANTITY = "123";
    private static final String VALID_ORDER_1 = CANNON.getId() + " 1 1/1";
    private static final String VALID_ORDER_2 = DAISY.getId() + " 455 2020/12/31";

    private static final String WHITESPACE = " \t\r\n";

    private static final Order ORDER_1 = new Order(CANNON.getName(), new Quantity("1"), LocalDate.now());
    private static final Order ORDER_2 = new Order(DAISY.getName(), new Quantity("100"), LocalDate.now());

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class,
                Index.MESSAGE_CONSTRAINTS, () -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_CLIENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_CLIENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName(null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhoneNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhoneNumber(null));
    }

    @Test
    public void parsePhoneNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhoneNumber(INVALID_PHONE_NUMBER));
    }

    @Test
    public void parsePhoneNumber_validValueWithoutWhitespace_returnsPhone() throws Exception {
        PhoneNumber expectedPhone = new PhoneNumber(VALID_Phone_Number);
        assertEquals(expectedPhone, ParserUtil.parsePhoneNumber(VALID_Phone_Number));
    }

    @Test
    public void parsePhoneNumber_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneNumberWithWhitespace = WHITESPACE + VALID_Phone_Number + WHITESPACE;
        PhoneNumber expectedPhone = new PhoneNumber(VALID_Phone_Number);
        assertEquals(expectedPhone, ParserUtil.parsePhoneNumber(phoneNumberWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress(null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail(null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseUnitPrice_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseUnitPrice(null));
    }

    @Test
    public void parseUnitPrice_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseUnitPrice(INVALID_UNIT_PRICE));
    }

    @Test
    public void parseUnitPrice_validValueWithoutWhitespace_returnsUnit() throws Exception {
        UnitPrice expectedUnit = new UnitPrice(VALID_UNIT_PRICE);
        assertEquals(expectedUnit, ParserUtil.parseUnitPrice(VALID_UNIT_PRICE));
    }

    @Test
    public void parseUnitPrice_validValueWithWhitespace_returnsTrimmedUnit() throws Exception {
        String unitPriceWithWhitespace = WHITESPACE + VALID_UNIT_PRICE + WHITESPACE;
        UnitPrice expectedUnit = new UnitPrice(VALID_UNIT_PRICE);
        assertEquals(expectedUnit, ParserUtil.parseUnitPrice(unitPriceWithWhitespace));
    }

    @Test
    public void parseQuantity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuantity(null));
    }

    @Test
    public void parseQuantity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithoutWhitespace_returnsQuantity() throws Exception {
        Quantity expectedQuantity = new Quantity(VALID_QUANTITY);
        assertEquals(expectedQuantity, ParserUtil.parseQuantity(VALID_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithWhitespace_returnsTrimmedQuantity() throws Exception {
        String quantityWithWhitespace = WHITESPACE + VALID_QUANTITY + WHITESPACE;
        Quantity expectedQuantity = new Quantity(VALID_QUANTITY);
        assertEquals(expectedQuantity, ParserUtil.parseQuantity(quantityWithWhitespace));
    }

    @Test
    public void parseOrder_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrder(null, new ModelStub()));
    }

    @Test
    public void parseOrder_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrder(INVALID_ORDER, new ModelStub()));
    }

    @Test
    public void parseOrder_invalidId_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrder(INVALID_ORDER_ID, new ModelStub()));
    }

    @Test
    public void parseOrder_invalidQuantity_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrder(INVALID_ORDER_QUANTITY, new ModelStub()));
    }

    @Test
    public void parseOrder_invalidTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrder(INVALID_ORDER_TIME, new ModelStub()));
    }

    @Test
    public void parseOrder_validValueWithoutWhitespace_returnsOrder() throws Exception {
        assertEquals(ORDER_1, ParserUtil.parseOrder(VALID_ORDER_1, new ModelStub()));
    }

    @Test
    public void parseOrder_validValueWithWhitespace_returnsTrimmedOrder() throws Exception {
        String orderWithWhitespace = WHITESPACE + VALID_ORDER_2 + WHITESPACE;
        assertEquals(ORDER_2, ParserUtil.parseOrder(orderWithWhitespace, new ModelStub()));
    }

    @Test
    public void parseOrders_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrders(null, new ModelStub()));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrders(List.of(VALID_ORDER_1), null));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrders(null, null));
    }

    @Test
    public void parseOrders_collectionWithInvalidOrders_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrders(
                Arrays.asList(VALID_ORDER_1, INVALID_ORDER), new ModelStub()));
    }

    @Test
    public void parseOrders_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseOrders(Collections.emptyList(), new ModelStub()).isEmpty());
    }

    @Test
    public void parseOrders_collectionWithValidOrders_returnsOrderSet() throws Exception {
        Set<Order> actualOrderSet = ParserUtil.parseOrders(
                Arrays.asList(VALID_ORDER_1, VALID_ORDER_2), new ModelStub());
        Set<Order> expectedOrderSet = new HashSet<>(Arrays.asList(ORDER_1, ORDER_2));

        assertEquals(expectedOrderSet, actualOrderSet);
    }

    private class ModelStub extends ModelManager {
        @Override
        public boolean hasProduct(ID productId) {
            return productId.getId() != 0;
        }

        @Override
        public Product getProductById(ID productId) {
            if (productId.equals(CANNON.getId())) {
                return CANNON;
            } else {
                return DAISY;
            }
        }
    }
}
