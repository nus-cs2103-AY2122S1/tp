package seedu.address.model.util;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.model.guest.RoomNumber;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.OperatingHours;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.ReadOnlyVendorBook;
import seedu.address.model.vendor.ServiceName;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorBook;
import seedu.address.model.vendor.VendorId;

/**
 * Contains utility methods for populating {@code VendorBook} and {@code GuestBook} with sample data.
 */
public class SampleDataUtil {
    public static Guest[] getSampleGuests() {
        return new Guest[]{
            new Guest(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), getTagSet("Deluxe"),
                new RoomNumber("217"), new PassportNumber("EC4744643")),
            new Guest(new Name("Bernice Yu"), new Email("berniceyu@example.com"),
                getTagSet("Vip", "Peanut allergy"),
                new RoomNumber("101"), new PassportNumber("FG4741690")),
            new Guest(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"),
                getTagSet("Suite"),
                new RoomNumber("201"), new PassportNumber("SE2391854")),
        };
    }

    public static Vendor[] getSampleVendors() {
        return new Vendor[]{
            new Vendor(new Name("Jeremy Chinese Delivery"), new Email("jchidelivery@example.com"),
                getTagSet("Chinese", "Non halal"), new VendorId("001"), new Phone("80180880"),
                new ServiceName("Food"), new Address("12 Clementi Rd, 231123"), new Cost(12.99),
                new OperatingHours(LocalTime.of(8, 0), LocalTime.of(15, 30), new ArrayList<DayOfWeek>(
                                Arrays.asList(DayOfWeek.of(1), DayOfWeek.of(3), DayOfWeek.of(5))), "135 0800-1530")),
            new Vendor(new Name("Bing Massage Parlour"), new Email("bmassage@example.com"),
                getTagSet("Foot massage", "Body massage"), new VendorId("002"), new Phone("67381280"),
                new ServiceName("Massage"), new Address("75 Clementi Rd, 211823"), new Cost(40.00),
                new OperatingHours(LocalTime.of(16, 0), LocalTime.of(22, 30), new ArrayList<DayOfWeek>(
                                Arrays.asList(DayOfWeek.of(1), DayOfWeek.of(2), DayOfWeek.of(3),
                                        DayOfWeek.of(4), DayOfWeek.of(5))), "12345 1600-2230")),
        };
    }

    public static ReadOnlyGuestBook getSampleGuestBook() {
        GuestBook sampleGuestBook = new GuestBook();

        for (Guest sampleGuest : getSampleGuests()) {
            sampleGuestBook.addGuest(sampleGuest);

        }

        return sampleGuestBook;
    }

    public static ReadOnlyVendorBook getSampleVendorBook() {
        VendorBook sampleVendorBook = new VendorBook();
        for (Vendor sampleVendor : getSampleVendors()) {
            sampleVendorBook.addVendor(sampleVendor);
        }
        return sampleVendorBook;
    }

    public static ReadOnlyGuestBook getSampleArchive() {
        return new Archive();
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
