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
import seedu.address.model.person.SortOrder;
import seedu.address.model.person.customer.SortByCustomer;
import seedu.address.model.person.employee.JobTitle;
import seedu.address.model.person.employee.Leaves;
import seedu.address.model.person.employee.Salary;
import seedu.address.model.person.employee.SortByEmployee;
import seedu.address.model.person.supplier.DeliveryDetails;
import seedu.address.model.person.supplier.SortBySupplier;
import seedu.address.model.person.supplier.SupplyType;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_LEAVES = "a";
    private static final String INVALID_SALARY = "8we9";
    private static final String INVALID_JOB_TITLE = "% manager";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_SUPPLY_TYPE = "Chicken & Beef";
    private static final String INVALID_DELIVERY_DETAILS = "Monday 2-4 pm";
    private static final String INVALID_SORT_BY = "t";
    private static final String INVALID_SORTING_ORDER = "123";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_LEAVES = "14";
    private static final String VALID_SALARY = "4000";
    private static final String VALID_JOB_TITLE = "Sales Team Lead";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_SUPPLY_TYPE = "Chicken and Beef";
    private static final String VALID_DELIVERY_DETAILS = "1400 2021-10-19";
    private static final String VALID_SORT_BY = "n";
    private static final String VALID_SORT_BY_WITH_CAPS = "N";
    private static final String VALID_SORTING_ORDER = "a";
    private static final String VALID_SORTING_ORDER_WITH_CAPS = "A";

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
        LocalDateTime expected = LocalDateTime.parse("2021-11-11T20:00");
        assertEquals(expected, ParserUtil.parseDateTime("  2021-11-11 2000   "));
        assertEquals(expected, ParserUtil.parseDateTime("  2021-11-11 20:00   "));
        assertEquals(expected, ParserUtil.parseDateTime("  2000 2021-11-11   "));
        assertEquals(expected, ParserUtil.parseDateTime("  20:00 2021-11-11   "));
        assertEquals(expected, ParserUtil.parseDateTime("  11-11-2021 2000   "));
        assertEquals(expected, ParserUtil.parseDateTime("  11-11-2021 20:00   "));
        assertEquals(expected, ParserUtil.parseDateTime("  2000 11-11-2021   "));
        assertEquals(expected, ParserUtil.parseDateTime("  20:00 11-11-2021   "));
    }

    @Test
    public void parseDateTime_invalidDateTime_failure() {
        assertThrows(ParseException.class, MESSAGE_INVALID_DATE_TIME_FORMAT, ()
            -> ParserUtil.parseDateTime("11/11/2021 2000"));
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
    public void parseLeaves_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLeaves((String) null));
    }

    @Test
    public void parseLeaves_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLeaves(INVALID_LEAVES));
    }

    @Test
    public void parseLeaves_validValueWithoutWhitespace_returnsLeaves() throws Exception {
        Leaves expectedLeaves = new Leaves(VALID_LEAVES);
        assertEquals(expectedLeaves, ParserUtil.parseLeaves(VALID_LEAVES));
    }

    @Test
    public void parseLeaves_validValueWithWhitespace_returnsTrimmedLeaves() throws Exception {
        String leavesWithWhitespace = WHITESPACE + VALID_LEAVES + WHITESPACE;
        Leaves expectedLeaves = new Leaves(VALID_LEAVES);
        assertEquals(expectedLeaves, ParserUtil.parseLeaves(leavesWithWhitespace));
    }

    @Test
    public void parseSalary_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSalary((String) null));
    }

    @Test
    public void parseSalary_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSalary(INVALID_SALARY));
    }

    @Test
    public void parseSalary_validValueWithoutWhitespace_returnsSalary() throws Exception {
        Salary expectedSalary = new Salary(VALID_SALARY);
        assertEquals(expectedSalary, ParserUtil.parseSalary(VALID_SALARY));
    }

    @Test
    public void parseSalary_validValueWithWhitespace_returnsTrimmedSalary() throws Exception {
        String salaryWithWhitespace = WHITESPACE + VALID_SALARY + WHITESPACE;
        Salary expectedSalary = new Salary(VALID_SALARY);
        assertEquals(expectedSalary, ParserUtil.parseSalary(salaryWithWhitespace));
    }

    @Test
    public void parseJobTitle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseJobTitle((String) null));
    }

    @Test
    public void parseJobTitle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseJobTitle(INVALID_JOB_TITLE));
    }

    @Test
    public void parseJobTitle_validValueWithoutWhitespace_returnsName() throws Exception {
        JobTitle expectedJobTitle = new JobTitle(VALID_JOB_TITLE);
        assertEquals(expectedJobTitle, ParserUtil.parseJobTitle(VALID_JOB_TITLE));
    }

    @Test
    public void parseJobTitle_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String jobTitleWithWhitespace = WHITESPACE + VALID_JOB_TITLE + WHITESPACE;
        JobTitle expectedJobTitle = new JobTitle(VALID_JOB_TITLE);
        assertEquals(expectedJobTitle, ParserUtil.parseJobTitle(jobTitleWithWhitespace));
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
    public void parseSupplyType_validValueWithoutWhitespace_returnsSupplyType() throws Exception {
        SupplyType expectedSupplyType = new SupplyType(VALID_SUPPLY_TYPE);
        assertEquals(expectedSupplyType, ParserUtil.parseSupplyType(VALID_SUPPLY_TYPE));
    }

    @Test
    public void parseSupplyType_validValueWithWhitespace_returnsTrimmedSupplyType() throws Exception {
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
    public void parseDeliveryDetails_validValueWithoutWhitespace_returnsDeliveryDetails() throws Exception {
        DeliveryDetails expectedDeliveryDetails = new DeliveryDetails(VALID_DELIVERY_DETAILS);
        assertEquals(expectedDeliveryDetails, ParserUtil.parseDeliveryDetails(VALID_DELIVERY_DETAILS));
    }

    @Test
    public void parseDeliveryDetails_validValueWithWhitespace_returnsTrimmedDeliveryDetails() throws Exception {
        String deliveryDetailsWithWhitespace = WHITESPACE + VALID_DELIVERY_DETAILS + WHITESPACE;
        DeliveryDetails expectedDeliveryDetails = new DeliveryDetails(VALID_DELIVERY_DETAILS);
        assertEquals(expectedDeliveryDetails, ParserUtil.parseDeliveryDetails(deliveryDetailsWithWhitespace));
    }

    @Test
    public void parseSortByCustomer_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSortByCustomer((String) null));
    }

    @Test
    public void parseSortByCustomer_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSortByCustomer(INVALID_SORT_BY));
    }

    @Test
    public void parseSortByCustomer_validValueWithoutWhitespace_returnsSortBy() throws Exception {
        SortByCustomer expectedSortByCustomer = new SortByCustomer(VALID_SORT_BY);
        assertEquals(expectedSortByCustomer, ParserUtil.parseSortByCustomer(VALID_SORT_BY));
    }

    @Test
    public void parseSortByCustomer_validCapsValueWithoutWhitespace_returnsSortBy() throws Exception {
        SortByCustomer expectedSortByCustomer = new SortByCustomer(VALID_SORT_BY);
        assertEquals(expectedSortByCustomer, ParserUtil.parseSortByCustomer(VALID_SORT_BY_WITH_CAPS));
    }

    @Test
    public void parseSortByCustomer_validValueWithWhitespace_returnsTrimmedSortBy() throws Exception {
        String sortByWithWhitespace = WHITESPACE + VALID_SORT_BY + WHITESPACE;
        SortByCustomer expectedSortByCustomer = new SortByCustomer(VALID_SORT_BY);
        assertEquals(expectedSortByCustomer, ParserUtil.parseSortByCustomer(sortByWithWhitespace));
    }

    @Test
    public void parseSortByCustomer_validCapsValueWithWhitespace_returnsTrimmedAndLowerCasedSortBy() throws Exception {
        String sortByWithWhitespaceAndCaps = WHITESPACE + VALID_SORT_BY_WITH_CAPS + WHITESPACE;
        SortByCustomer expectedSortByCustomer = new SortByCustomer(VALID_SORT_BY);
        assertEquals(expectedSortByCustomer, ParserUtil.parseSortByCustomer(sortByWithWhitespaceAndCaps));
    }

    @Test
    public void parseSortByEmployee_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSortByEmployee((String) null));
    }

    @Test
    public void parseSortByEmployee_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSortByEmployee(INVALID_SORT_BY));
    }

    @Test
    public void parseSortByEmployee_validValueWithoutWhitespace_returnsSortBy() throws Exception {
        SortByEmployee expectedSortByEmployee = new SortByEmployee(VALID_SORT_BY);
        assertEquals(expectedSortByEmployee, ParserUtil.parseSortByEmployee(VALID_SORT_BY));
    }

    @Test
    public void parseSortByEmployee_validCapsValueWithoutWhitespace_returnsSortBy() throws Exception {
        SortByEmployee expectedSortByEmployee = new SortByEmployee(VALID_SORT_BY);
        assertEquals(expectedSortByEmployee, ParserUtil.parseSortByEmployee(VALID_SORT_BY_WITH_CAPS));
    }

    @Test
    public void parseSortByEmployee_validValueWithWhitespace_returnsTrimmedSortBy() throws Exception {
        String sortByWithWhitespace = WHITESPACE + VALID_SORT_BY + WHITESPACE;
        SortByEmployee expectedSortByEmployee = new SortByEmployee(VALID_SORT_BY);
        assertEquals(expectedSortByEmployee, ParserUtil.parseSortByEmployee(sortByWithWhitespace));
    }

    @Test
    public void parseSortByEmployee_validCapsValueWithWhitespace_returnsTrimmedAndLowerCasedSortBy() throws Exception {
        String sortByWithWhitespaceAndCaps = WHITESPACE + VALID_SORT_BY_WITH_CAPS + WHITESPACE;
        SortByEmployee expectedSortByEmployee = new SortByEmployee(VALID_SORT_BY);
        assertEquals(expectedSortByEmployee, ParserUtil.parseSortByEmployee(sortByWithWhitespaceAndCaps));
    }

    @Test
    public void parseSortBySupplier_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSortBySupplier((String) null));
    }

    @Test
    public void parseSortBySupplier_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSortBySupplier(INVALID_SORT_BY));
    }

    @Test
    public void parseSortBySupplier_validValueWithoutWhitespace_returnsSortBy() throws Exception {
        SortBySupplier expectedSortBySupplier = new SortBySupplier(VALID_SORT_BY);
        assertEquals(expectedSortBySupplier, ParserUtil.parseSortBySupplier(VALID_SORT_BY));
    }

    @Test
    public void parseSortBySupplier_validCapsValueWithoutWhitespace_returnsSortBy() throws Exception {
        SortBySupplier expectedSortBySupplier = new SortBySupplier(VALID_SORT_BY);
        assertEquals(expectedSortBySupplier, ParserUtil.parseSortBySupplier(VALID_SORT_BY_WITH_CAPS));
    }

    @Test
    public void parseSortBySupplier_validValueWithWhitespace_returnsTrimmedSortBy() throws Exception {
        String sortByWithWhitespace = WHITESPACE + VALID_SORT_BY + WHITESPACE;
        SortBySupplier expectedSortBySupplier = new SortBySupplier(VALID_SORT_BY);
        assertEquals(expectedSortBySupplier, ParserUtil.parseSortBySupplier(sortByWithWhitespace));
    }

    @Test
    public void parseSortBySupplier_validCapsValueWithWhitespace_returnsTrimmedAndLowerCasedSortBy() throws Exception {
        String sortByWithWhitespaceAndCaps = WHITESPACE + VALID_SORT_BY_WITH_CAPS + WHITESPACE;
        SortBySupplier expectedSortBySupplier = new SortBySupplier(VALID_SORT_BY);
        assertEquals(expectedSortBySupplier, ParserUtil.parseSortBySupplier(sortByWithWhitespaceAndCaps));
    }

    @Test
    public void parseSortingOrder_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSortingOrder((String) null));
    }

    @Test
    public void parseSortingOrder_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSortingOrder(INVALID_SORTING_ORDER));
    }

    @Test
    public void parseSortingOrder_validValueWithoutWhitespace_returnsSortingOrder() throws Exception {
        SortOrder expectedSortOrder = new SortOrder(VALID_SORTING_ORDER);
        assertEquals(expectedSortOrder, ParserUtil.parseSortingOrder(VALID_SORTING_ORDER));
    }

    @Test
    public void parseSortingOrder_validCapsValueWithoutWhitespace_returnsSortingOrder() throws Exception {
        SortOrder expectedSortOrder = new SortOrder(VALID_SORTING_ORDER);
        assertEquals(expectedSortOrder, ParserUtil.parseSortingOrder(VALID_SORTING_ORDER_WITH_CAPS));
    }

    @Test
    public void parseSortBySupplier_validValueWithWhitespace_returnsTrimmedSortingOrder() throws Exception {
        String sortingOrderWithWhitespace = WHITESPACE + VALID_SORTING_ORDER + WHITESPACE;
        SortOrder expectedSortingOrder = new SortOrder(VALID_SORTING_ORDER);
        assertEquals(expectedSortingOrder, ParserUtil.parseSortingOrder(sortingOrderWithWhitespace));
    }

    @Test
    public void parseSortBySupplier_validCapsValueWithWhitespace_returnsTrimmedSortingOrder() throws Exception {
        String sortingOrderWithWhitespace = WHITESPACE + VALID_SORTING_ORDER_WITH_CAPS + WHITESPACE;
        SortOrder expectedSortingOrder = new SortOrder(VALID_SORTING_ORDER);
        assertEquals(expectedSortingOrder, ParserUtil.parseSortingOrder(sortingOrderWithWhitespace));
    }
}
