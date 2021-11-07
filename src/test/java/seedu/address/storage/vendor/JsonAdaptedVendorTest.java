package seedu.address.storage.vendor;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.vendor.JsonAdaptedVendor.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.vendor.TypicalVendors.ELLE_VENDOR;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.OperatingHours;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.ServiceName;
import seedu.address.model.vendor.VendorId;
import seedu.address.storage.JsonAdaptedTag;

class JsonAdaptedVendorTest {
    private static final String INVALID_VID = "asdas @";
    private static final String INVALID_NAME = "   ";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_OPERATING_HOURS = "12388 1000-1300";
    private static final Double INVALID_COST = -10.00;
    private static final String INVALID_SERVICE_NAME = "Service!";

    private static final String VALID_VID = ELLE_VENDOR.getVendorId().toString();
    private static final String VALID_NAME = ELLE_VENDOR.getName().toString();
    private static final String VALID_PHONE = ELLE_VENDOR.getPhone().toString();
    private static final String VALID_EMAIL = ELLE_VENDOR.getEmail().toString();
    private static final String VALID_ADDRESS = ELLE_VENDOR.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = ELLE_VENDOR.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_OPERATING_HOURS = ELLE_VENDOR.getOperatingHours().operatingHoursStringRep;
    private static final Double VALID_COST = ELLE_VENDOR.getCost().value;
    private static final String VALID_SERVICE_NAME = ELLE_VENDOR.getServiceName().toString();

    @Test
    public void toModelType_validVendorDetails_returnsVendor() throws Exception {
        JsonAdaptedVendor vendor = new JsonAdaptedVendor(ELLE_VENDOR);
        assertEquals(ELLE_VENDOR, vendor.toModelType());
    }

    @Test
    public void toModelType_invalidVid_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, INVALID_VID, VALID_PHONE,
                        VALID_SERVICE_NAME, VALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = VendorId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_nullVid_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, null, VALID_PHONE,
                        VALID_SERVICE_NAME, VALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, VendorId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(INVALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_VID, VALID_PHONE,
                        VALID_SERVICE_NAME, VALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(null, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_VID, VALID_PHONE,
                        VALID_SERVICE_NAME, VALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_VID, INVALID_PHONE,
                        VALID_SERVICE_NAME, VALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_VID, null,
                        VALID_SERVICE_NAME, VALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, INVALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_VID, VALID_PHONE,
                        VALID_SERVICE_NAME, VALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, null, VALID_TAGS, VALID_ADDRESS, VALID_VID, VALID_PHONE,
                        VALID_SERVICE_NAME, VALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, VALID_TAGS, INVALID_ADDRESS, VALID_VID, VALID_PHONE,
                        VALID_SERVICE_NAME, VALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, VALID_TAGS, null, VALID_VID, VALID_PHONE,
                        VALID_SERVICE_NAME, VALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_invalidCost_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_VID, VALID_PHONE,
                        VALID_SERVICE_NAME, INVALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = Cost.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_nullCost_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_VID, VALID_PHONE,
                        VALID_SERVICE_NAME, null, VALID_OPERATING_HOURS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Cost.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_invalidServiceName_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_VID, VALID_PHONE,
                        INVALID_SERVICE_NAME, VALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = ServiceName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_nullServiceName_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_VID, VALID_PHONE,
                        null, VALID_COST, VALID_OPERATING_HOURS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ServiceName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_invalidOperatingHours_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_VID, VALID_PHONE,
                        VALID_SERVICE_NAME, VALID_COST, INVALID_OPERATING_HOURS);
        String expectedMessage = OperatingHours.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }

    @Test
    public void toModelType_nullOperatingHours_throwsIllegalValueException() {
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_VID, VALID_PHONE,
                        VALID_SERVICE_NAME, VALID_COST, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, OperatingHours.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, vendor::toModelType);
    }


    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedVendor vendor =
                new JsonAdaptedVendor(VALID_NAME, VALID_EMAIL, invalidTags, VALID_ADDRESS, VALID_VID, VALID_PHONE,
                        VALID_SERVICE_NAME, VALID_COST, VALID_OPERATING_HOURS);
        assertThrows(IllegalValueException.class, vendor::toModelType);
    }


}
