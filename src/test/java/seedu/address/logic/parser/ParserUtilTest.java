package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE_TIME_FORMAT;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_NUMBER_OF_PEOPLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.supplier.DeliveryDetails;
import seedu.address.model.person.supplier.SupplyType;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_SUPPLY_TYPE = "Chicken & Beef";
    private static final String INVALID_DELIVERY_DETAILS = "Monday 2-4 pm";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_SUPPLY_TYPE = "Chicken and Beef";
    private static final String VALID_DELIVERY_DETAILS = "Every Monday 2pm";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseNumberOfPeople_nonZeroUnsignedInteger_success() throws Exception {
        assertEquals(1, ParserUtil.parseNumberOfPeople("1"));
        assertEquals(1, ParserUtil.parseNumberOfPeople("   1  "));
    }

    @Test
    public void parseNumberOfPeople_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_NUMBER_OF_PEOPLE, ()
            -> ParserUtil.parseNumberOfPeople(" a"));

        assertThrows(ParseException.class, MESSAGE_INVALID_NUMBER_OF_PEOPLE, ()
            -> ParserUtil.parseNumberOfPeople("-1 "));
    }

    @Test
    public void parseDateTime_validDateTime_success() throws Exception {
        LocalDateTime expected = LocalDateTime.parse("2021-11-11 2000", ParserUtil.DATE_TIME_FORMATTER);
        assertEquals(expected, ParserUtil.parseDateTime("  2021-11-11 2000   "));
    }

    @Test
    public void parseDateTime_invalidDateTime_failure() {
        assertThrows(ParseException.class, MESSAGE_INVALID_DATE_TIME_FORMAT, ()
            -> ParserUtil.parseDateTime("11-11-2021 2000"));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
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
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
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
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
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
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseSupplyType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSupplyType((String) null));
    }

    @Test
    public void parseSupplyType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSupplyType(INVALID_SUPPLY_TYPE));
    }

    @Test
    public void parseSupplyType_validValueWithoutWhitespace_returnsName() throws Exception {
        SupplyType expectedSupplyType = new SupplyType(VALID_SUPPLY_TYPE);
        assertEquals(expectedSupplyType, ParserUtil.parseSupplyType(VALID_SUPPLY_TYPE));
    }

    @Test
    public void parseSupplyType_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String supplyTypeWithWhitespace = WHITESPACE + VALID_SUPPLY_TYPE + WHITESPACE;
        SupplyType expectedSupplyType = new SupplyType(VALID_SUPPLY_TYPE);
        assertEquals(expectedSupplyType, ParserUtil.parseSupplyType(supplyTypeWithWhitespace));
    }

    @Test
    public void parseDeliveryDetails_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeliveryDetails((String) null));
    }

    @Test
    public void parseDeliveryDetails_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeliveryDetails(INVALID_DELIVERY_DETAILS));
    }

    @Test
    public void parseDeliveryDetails_validValueWithoutWhitespace_returnsName() throws Exception {
        DeliveryDetails expectedDeliveryDetails = new DeliveryDetails(VALID_DELIVERY_DETAILS);
        assertEquals(expectedDeliveryDetails, ParserUtil.parseDeliveryDetails(VALID_DELIVERY_DETAILS));
    }

    @Test
    public void parseDeliveryDetails_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String deliveryDetailsWithWhitespace = WHITESPACE + VALID_DELIVERY_DETAILS + WHITESPACE;
        DeliveryDetails expectedDeliveryDetails = new DeliveryDetails(VALID_DELIVERY_DETAILS);
        assertEquals(expectedDeliveryDetails, ParserUtil.parseDeliveryDetails(deliveryDetailsWithWhitespace));
    }
}
