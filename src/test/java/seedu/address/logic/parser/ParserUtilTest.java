package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.RoomNumber;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.ServiceName;
import seedu.address.model.vendor.VendorId;

public class ParserUtilTest {
    private static final String INVALID_NAME = "   ";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_ROOM_NUMBER = " ";
    private static final String INVALID_PASSPORT_NUMBER = " ";
    private static final String INVALID_COST = "-10.00";
    private static final String INVALID_OPERATING_HOURS = "1230800-1300";
    private static final String INVALID_SERVICE_NAME = " ";
    private static final String INVALID_VENDOR_ID = " ";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "Friend";
    private static final String VALID_TAG_2 = "Neighbour";
    private static final String VALID_ROOM_NUMBER = "123";
    private static final String VALID_PASSPORT_NUMBER = "1231231D";
    private static final String VALID_COST = "10.00";
    private static final String VALID_OPERATING_HOURS = "123 0800-1300";
    private static final String VALID_SERVICE_NAME = "Massage";
    private static final String VALID_VENDOR_ID = "001";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, (
        ) -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
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
    public void parseRoomNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRoomNumber((String) null));
    }

    @Test
    public void parseRoomNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRoomNumber(INVALID_ROOM_NUMBER));
    }

    @Test
    public void parseRoomNumber_validValueWithoutWhitespace_returnsRoomNumber() throws Exception {
        RoomNumber expectedRoomNumber = new RoomNumber(VALID_ROOM_NUMBER);
        assertEquals(expectedRoomNumber, ParserUtil.parseRoomNumber(VALID_ROOM_NUMBER));
    }

    @Test
    public void parseRoomNumber_validValueWithWhitespace_returnsTrimmedRoomNumber() throws Exception {
        String roomNumberWithWhiteSpace = WHITESPACE + VALID_ROOM_NUMBER + WHITESPACE;
        RoomNumber expectedRoomNumber = new RoomNumber(VALID_ROOM_NUMBER);
        assertEquals(expectedRoomNumber, ParserUtil.parseRoomNumber(roomNumberWithWhiteSpace));
    }

    @Test
    public void parsePassportNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePassportNumber((String) null));
    }

    @Test
    public void parsePassportNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePassportNumber(INVALID_PASSPORT_NUMBER));
    }

    @Test
    public void parsePassportNumber_validValueWithoutWhitespace_returnsPassportNumber() throws Exception {
        PassportNumber expectedPassportNumber = new PassportNumber(VALID_PASSPORT_NUMBER);
        assertEquals(expectedPassportNumber, ParserUtil.parsePassportNumber(VALID_PASSPORT_NUMBER));
    }

    @Test
    public void parsePassportNumber_validValueWithWhitespace_returnsTrimmedPassportNumber() throws Exception {
        String passportNumberWithWhitespace = WHITESPACE + VALID_PASSPORT_NUMBER + WHITESPACE;
        PassportNumber expectedPassportNumber = new PassportNumber(VALID_PASSPORT_NUMBER);
        assertEquals(expectedPassportNumber, ParserUtil.parsePassportNumber(passportNumberWithWhitespace));
    }

    @Test
    public void parseCost_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCost((String) null));
    }

    @Test
    public void parseCost_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCost(INVALID_COST));
    }

    @Test
    public void parseCost_validValueWithoutWhitespace_returnsCost() throws Exception {
        Cost expectedCost = new Cost(Double.parseDouble(VALID_COST));
        assertEquals(expectedCost, ParserUtil.parseCost(VALID_COST));
    }

    @Test
    public void parseCost_validValueWithWhitespace_returnsTrimmedCost() throws Exception {
        String costWithWhiteSpace = WHITESPACE + VALID_COST + WHITESPACE;
        Cost expectedCost = new Cost(Double.parseDouble(VALID_COST));
        assertEquals(expectedCost, ParserUtil.parseCost(costWithWhiteSpace));
    }

    @Test
    public void parseServiceName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseServiceName((String) null));
    }

    @Test
    public void parseServiceName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseServiceName(INVALID_SERVICE_NAME));
    }

    @Test
    public void parseServiceName_validValueWithoutWhitespace_returnsServiceName() throws Exception {
        ServiceName expectedServiceName = new ServiceName(VALID_SERVICE_NAME);
        assertEquals(expectedServiceName, ParserUtil.parseServiceName(VALID_SERVICE_NAME));
    }

    @Test
    public void parseServiceName_validValueWithWhitespace_returnsTrimmedServiceName() throws Exception {
        String serviceNameWithWhiteSpace = WHITESPACE + VALID_SERVICE_NAME + WHITESPACE;
        ServiceName expectedServiceName = new ServiceName(VALID_SERVICE_NAME);
        assertEquals(expectedServiceName, ParserUtil.parseServiceName(serviceNameWithWhiteSpace));
    }

    @Test
    public void parseVendorId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseVendorId((String) null));
    }

    @Test
    public void parseVendorId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVendorId(INVALID_VENDOR_ID));
    }

    @Test
    public void parseVendorId_validValueWithoutWhitespace_returnsVendorId() throws Exception {
        VendorId expectedVendorId = new VendorId(VALID_VENDOR_ID);
        assertEquals(expectedVendorId, ParserUtil.parseVendorId(VALID_VENDOR_ID));
    }

    @Test
    public void parseVendorId_validValueWithWhitespace_returnsTrimmedVendorId() throws Exception {
        String vendorIdWithWhiteSpace = WHITESPACE + VALID_VENDOR_ID + WHITESPACE;
        VendorId expectedVendorId = new VendorId(VALID_VENDOR_ID);
        assertEquals(expectedVendorId, ParserUtil.parseVendorId(vendorIdWithWhiteSpace));
    }

}
