package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_SECOND_PERSON;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_THIRD_PERSON;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_FIRST_PERSON;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_FOURTH_PERSON;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_SECOND_PERSON;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_THIRD_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.guest.EditGuestCommand.EditGuestDescriptor;
import seedu.address.logic.commands.vendor.EditVendorCommand.EditVendorDescriptor;
import seedu.address.model.Model;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.PassportNumberContainsKeywordsPredicate;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorId;
import seedu.address.model.vendor.VendorIdContainsKeywordsPredicate;
import seedu.address.testutil.guest.EditGuestDescriptorBuilder;
import seedu.address.testutil.vendor.EditVendorDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // Guests
    public static final String VALID_NAME_ALICE = "Alice Pauline";
    public static final String VALID_EMAIL_ALICE = "alice@example.com";
    public static final String VALID_TAG_ALICE = "Vip";
    public static final String VALID_ROOM_NUMBER_ALICE = "20202";
    public static final String VALID_PASSPORT_NUMBER_ALICE = PASSPORT_NUMBER_FIRST_PERSON.toString();
    public static final String NAME_DESC_ALICE = " " + PREFIX_NAME + VALID_NAME_ALICE;
    public static final String ROOM_NUMBER_DESC_ALICE = " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_ALICE;
    public static final String EMAIL_DESC_ALICE = " " + PREFIX_EMAIL + VALID_EMAIL_ALICE;
    public static final String PASSPORT_NUMBER_DESC_ALICE = " " + PREFIX_PASSPORT_NUMBER + VALID_PASSPORT_NUMBER_ALICE;
    public static final String TAG_DESC_ALICE = " " + PREFIX_TAG + VALID_TAG_ALICE;

    public static final String VALID_NAME_BENSON = "Benson Meier";
    public static final String VALID_EMAIL_BENSON = "benson@example.com";
    public static final String VALID_TAG_BENSON = "Normal room";
    public static final String VALID_ROOM_NUMBER_BENSON = "20201";
    public static final String VALID_PASSPORT_NUMBER_BENSON = PASSPORT_NUMBER_SECOND_PERSON.toString();
    public static final String NAME_DESC_BENSON = " " + PREFIX_NAME + VALID_NAME_BENSON;
    public static final String ROOM_NUMBER_DESC_BENSON = " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_BENSON;
    public static final String EMAIL_DESC_BENSON = " " + PREFIX_EMAIL + VALID_EMAIL_BENSON;
    public static final String PASSPORT_NUMBER_DESC_BENSON = " " + PREFIX_PASSPORT_NUMBER
            + VALID_PASSPORT_NUMBER_BENSON;
    public static final String TAG_DESC_BENSON = " " + PREFIX_TAG + VALID_TAG_BENSON;

    public static final String VALID_NAME_CARL = "Carl Kurz";
    public static final String VALID_EMAIL_CARL = "carl@example.com";
    public static final String VALID_TAG_CARL = "Deluxe suite";
    public static final String VALID_ROOM_NUMBER_CARL = "12321";
    public static final String VALID_PASSPORT_NUMBER_CARL = PASSPORT_NUMBER_THIRD_PERSON.toString();
    public static final String NAME_DESC_CARL = " " + PREFIX_NAME + VALID_NAME_CARL;
    public static final String ROOM_NUMBER_DESC_CARL = " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_CARL;
    public static final String EMAIL_DESC_CARL = " " + PREFIX_EMAIL + VALID_EMAIL_CARL;
    public static final String PASSPORT_NUMBER_DESC_CARL = " " + PREFIX_PASSPORT_NUMBER + VALID_PASSPORT_NUMBER_CARL;
    public static final String TAG_DESC_CARL = " " + PREFIX_TAG + VALID_TAG_CARL;

    // Vendors
    public static final String VALID_NAME_DANIEL = "Daniel Meier";
    public static final String VALID_EMAIL_DANIEL = "cornelia@example.com";
    public static final String VALID_TAG_DANIEL = "Foot massage";
    public static final String VALID_ADDRESS_DANIEL = "10th street";
    public static final String VALID_PHONE_DANIEL = "87652533";
    public static final String VALID_VENDOR_ID_DANIEL = VENDOR_ID_FIRST_PERSON.toString();
    public static final String VALID_SERVICE_NAME_DANIEL = "Massage";
    public static final String VALID_COST_DANIEL = "20";
    public static final String VALID_OPERATING_HOURS_DANIEL = "123 0800-1300";
    public static final String NAME_DESC_DANIEL = " " + PREFIX_NAME + VALID_NAME_DANIEL;
    public static final String EMAIL_DESC_DANIEL = " " + PREFIX_EMAIL + VALID_EMAIL_DANIEL;
    public static final String ADDRESS_DESC_DANIEL = " " + PREFIX_ADDRESS + VALID_ADDRESS_DANIEL;
    public static final String TAG_DESC_DANIEL = " " + PREFIX_TAG + VALID_TAG_DANIEL;
    public static final String PHONE_DESC_DANIEL = " " + PREFIX_PHONE + VALID_PHONE_DANIEL;
    public static final String VENDOR_ID_DESC_DANIEL = " " + PREFIX_VENDOR_ID + VALID_VENDOR_ID_DANIEL;
    public static final String SERVICE_NAME_DESC_DANIEL = " " + PREFIX_SERVICE_NAME + VALID_SERVICE_NAME_DANIEL;
    public static final String COST_DESC_DANIEL = " " + PREFIX_COST + VALID_COST_DANIEL;
    public static final String OPERATING_HOURS_DESC_DANIEL =
            " " + PREFIX_OPERATING_HOURS + VALID_OPERATING_HOURS_DANIEL;


    public static final String VALID_NAME_ELLE = "Elle Meyer";
    public static final String VALID_EMAIL_ELLE = "elle@example.com";
    public static final String VALID_TAG_ELLE = "Halal";
    public static final String VALID_ADDRESS_ELLE = "michegan ave";
    public static final String VALID_PHONE_ELLE = "9482224";
    public static final String VALID_VENDOR_ID_ELLE = VENDOR_ID_SECOND_PERSON.toString();
    public static final String VALID_SERVICE_NAME_ELLE = "Food";
    public static final String VALID_COST_ELLE = "12";
    public static final String VALID_OPERATING_HOURS_ELLE = "12345 1100-2200";
    public static final String NAME_DESC_ELLE = " " + PREFIX_NAME + VALID_NAME_ELLE;
    public static final String EMAIL_DESC_ELLE = " " + PREFIX_EMAIL + VALID_EMAIL_ELLE;
    public static final String ADDRESS_DESC_ELLE = " " + PREFIX_ADDRESS + VALID_ADDRESS_ELLE;
    public static final String PHONE_DESC_ELLE = " " + PREFIX_PHONE + VALID_PHONE_ELLE;
    public static final String VENDOR_ID_DESC_ELLE = " " + PREFIX_VENDOR_ID + VALID_VENDOR_ID_ELLE;
    public static final String SERVICE_NAME_DESC_ELLE = " " + PREFIX_SERVICE_NAME + VALID_SERVICE_NAME_ELLE;
    public static final String COST_DESC_ELLE = " " + PREFIX_COST + VALID_COST_ELLE;
    public static final String OPERATING_HOURS_DESC_ELLE =
            " " + PREFIX_OPERATING_HOURS + VALID_OPERATING_HOURS_ELLE;

    public static final String VALID_NAME_FIONA = "Fiona Kunz";
    public static final String VALID_EMAIL_FIONA = "fiona@example.com";
    public static final String VALID_TAG_FIONA = "Discounted";
    public static final String VALID_ADDRESS_FIONA = "little tokyo";
    public static final String VALID_PHONE_FIONA = "9482427";
    public static final String VALID_VENDOR_ID_FIONA = VENDOR_ID_THIRD_PERSON.toString();
    public static final String VALID_SERVICE_NAME_FIONA = "Dry Cleaning";
    public static final String VALID_COST_FIONA = "40";
    public static final String VALID_OPERATING_HOURS_FIONA = "234567 0700-2300";
    public static final String NAME_DESC_FIONA = " " + PREFIX_NAME + VALID_NAME_FIONA;
    public static final String EMAIL_DESC_FIONA = " " + PREFIX_EMAIL + VALID_EMAIL_FIONA;
    public static final String ADDRESS_DESC_FIONA = " " + PREFIX_ADDRESS + VALID_ADDRESS_FIONA;
    public static final String PHONE_DESC_FIONA = " " + PREFIX_PHONE + VALID_PHONE_FIONA;
    public static final String VENDOR_ID_DESC_FIONA = " " + PREFIX_VENDOR_ID + VALID_VENDOR_ID_FIONA;
    public static final String SERVICE_NAME_DESC_FIONA = " " + PREFIX_SERVICE_NAME + VALID_SERVICE_NAME_FIONA;
    public static final String COST_DESC_FIONA = " " + PREFIX_COST + VALID_COST_FIONA;
    public static final String OPERATING_HOURS_DESC_FIONA =
            " " + PREFIX_OPERATING_HOURS + VALID_OPERATING_HOURS_FIONA;

    public static final String VALID_NAME_GEORGE = "George Best";
    public static final String VALID_EMAIL_GEORGE = "george@example.com";
    public static final String VALID_TAG_GEORGE = "Happy hour";
    public static final String VALID_ADDRESS_GEORGE = "4th street";
    public static final String VALID_PHONE_GEORGE = "9482442";
    public static final String VALID_VENDOR_ID_GEORGE = VENDOR_ID_FOURTH_PERSON.toString();
    public static final String VALID_SERVICE_NAME_GEORGE = "Bartending";
    public static final String VALID_COST_GEORGE = "17";
    public static final String VALID_OPERATING_HOURS_GEORGE = "135 1800-2359";
    public static final String NAME_DESC_GEORGE = " " + PREFIX_NAME + VALID_NAME_GEORGE;
    public static final String EMAIL_DESC_GEORGE = " " + PREFIX_EMAIL + VALID_EMAIL_GEORGE;
    public static final String ADDRESS_DESC_GEORGE = " " + PREFIX_ADDRESS + VALID_ADDRESS_GEORGE;
    public static final String PHONE_DESC_GEORGE = " " + PREFIX_PHONE + VALID_PHONE_GEORGE;
    public static final String VENDOR_ID_DESC_GEORGE = " " + PREFIX_VENDOR_ID + VALID_VENDOR_ID_GEORGE;
    public static final String SERVICE_NAME_DESC_GEORGE = " " + PREFIX_SERVICE_NAME + VALID_SERVICE_NAME_GEORGE;
    public static final String COST_DESC_GEORGE = " " + PREFIX_COST + VALID_COST_GEORGE;
    public static final String OPERATING_HOURS_DESC_GEORGE =
            " " + PREFIX_OPERATING_HOURS + VALID_OPERATING_HOURS_DANIEL;

    // Guest Tags
    public static final String VALID_TAG_VIP = "Vip";
    public static final String VALID_TAG_DELUXE_ROOM = "Deluxe room";
    public static final String TAG_DESC_VIP = " " + PREFIX_TAG + VALID_TAG_VIP;
    public static final String TAG_DESC_DELUXE_ROOM = " " + PREFIX_TAG + VALID_TAG_DELUXE_ROOM;

    // Vendor Tags
    public static final String VALID_TAG_HIGH_RATINGS = "High ratings";
    public static final String VALID_TAG_CHEAP = "Cheap";
    public static final String TAG_DESC_HIGH_RATINGS = " " + PREFIX_TAG + VALID_TAG_HIGH_RATINGS;
    public static final String TAG_DESC_CHEAP = " " + PREFIX_TAG + VALID_TAG_CHEAP;

    // Invalid fields
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME; // names cannot be empty
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_VENDOR_ID_DESC = " " + PREFIX_VENDOR_ID + "1#2"; //'#' not allowed in staff id
    public static final String INVALID_ROOM_NUMBER_DESC = " " + PREFIX_ROOM_NUMBER + "-1";
    // room numbers must be a number greater than 0.
    public static final String INVALID_PASSPORT_NUMBER_DESC = " " + PREFIX_PASSPORT_NUMBER + "@3333";
    // passport numbers should be alphanumeric
    public static final String INVALID_COST_DESC_NOT_DOUBLE = " " + PREFIX_COST + "ab"; // 'ab' not allowed in cost
    public static final String INVALID_COST_DESC_NOT_POSITIVE = " " + PREFIX_COST + "0.0"; // 'ab' not allowed in cost
    // '8' not allowed in operating hours days
    public static final String INVALID_OPERATING_HOURS_DESC = " " + PREFIX_OPERATING_HOURS + "12348 1000-1300";
    // '!' not allowed in service names
    public static final String INVALID_SERVICE_NAME_DESC = " " + PREFIX_SERVICE_NAME + "Service!";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditGuestDescriptor DESC_ALICE;
    public static final EditGuestDescriptor DESC_BENSON;
    public static final EditVendorDescriptor DESC_DANIEL;
    public static final EditVendorDescriptor DESC_ELLE;

    static {
        DESC_ALICE = new EditGuestDescriptorBuilder()
                .withName(VALID_NAME_ALICE)
                .withEmail(VALID_EMAIL_ALICE)
                .withTags(VALID_TAG_ALICE)
                .withRoomNumber(VALID_ROOM_NUMBER_ALICE)
                .withPassportNumber(VALID_PASSPORT_NUMBER_ALICE)
                .build();

        DESC_BENSON = new EditGuestDescriptorBuilder()
                .withName(VALID_NAME_BENSON)
                .withEmail(VALID_EMAIL_BENSON)
                .withTags(VALID_TAG_VIP, VALID_TAG_DELUXE_ROOM)
                .withRoomNumber(VALID_ROOM_NUMBER_BENSON)
                .withPassportNumber(VALID_PASSPORT_NUMBER_BENSON)
                .build();

        DESC_DANIEL = new EditVendorDescriptorBuilder()
                .withName(VALID_NAME_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .withTags(VALID_TAG_DANIEL)
                .withAddress(VALID_ADDRESS_DANIEL)
                .withPhone(VALID_PHONE_DANIEL)
                .withVendorId(VALID_VENDOR_ID_DANIEL)
                .withCost(VALID_COST_DANIEL)
                .withOperatingHours(VALID_OPERATING_HOURS_DANIEL)
                .withServiceName(VALID_SERVICE_NAME_DANIEL)
                .build();

        DESC_ELLE = new EditVendorDescriptorBuilder()
                .withName(VALID_NAME_ELLE)
                .withEmail(VALID_EMAIL_ELLE)
                .withTags(VALID_TAG_ELLE)
                .withAddress(VALID_ADDRESS_ELLE)
                .withPhone(VALID_PHONE_ELLE)
                .withVendorId(VALID_VENDOR_ID_ELLE)
                .withCost(VALID_COST_ELLE)
                .withOperatingHours(VALID_OPERATING_HOURS_ELLE)
                .withServiceName(VALID_SERVICE_NAME_ELLE)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the guest book, filtered guest list and selected guest in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        GuestBook expectedGuestBook = new GuestBook(actualModel.getGuestBook());
        List<Guest> expectedFilteredList = new ArrayList<>(actualModel.getFilteredGuestList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedGuestBook, actualModel.getGuestBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredGuestList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the guest at the given {@code PassportNumber} in the
     * {@code model}'s guest book.
     */
    public static void showGuestAtPassportNumber(Model model, PassportNumber targetPassportNumber) {

        Optional<Guest> guest = model.getGuest(targetPassportNumber);

        assert (guest.isPresent());

        String passportNumber = guest.get().getPassportNumber().value;

        model.updateFilteredGuestList(new PassportNumberContainsKeywordsPredicate(Arrays.asList(passportNumber)));

        assertEquals(1, model.getFilteredGuestList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the vendor at the given {@code VendorId} in the
     * {@code model}'s vendor book.
     */
    public static void showVendorAtVendorId(Model model, VendorId targetVendorId) {

        Optional<Vendor> vendor = model.getVendor(targetVendorId);

        assert (vendor.isPresent());

        String vendorId = vendor.get().getVendorId().value;

        model.updateFilteredVendorList(new VendorIdContainsKeywordsPredicate(Arrays.asList(vendorId)));

        assertEquals(1, model.getFilteredVendorList().size());
    }


}
