package seedu.address.testutil.vendor;

import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_DEFAULT;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.OperatingHours;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.ServiceName;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorId;

public class VendorBuilder {

    public static final Name DEFAULT_NAME = new Name("Jims Massage Parlour");
    public static final Email DEFAULT_EMAIL = new Email("jim@gmail.com");
    public static final VendorId DEFAULT_VENDOR_ID = new VendorId(VENDOR_ID_DEFAULT.toString());
    public static final Address DEFAULT_ADDRESS = new Address("123, Jurong West Ave 6, #08-111");
    public static final Phone DEFAULT_PHONE = new Phone("85355255");
    public static final Cost DEFAULT_COST = new Cost(10.00);
    public static final ServiceName DEFAULT_SERVICE_NAME = new ServiceName("Massage");
    public static final OperatingHours DEFAULT_OPERATING_HOURS =
            new OperatingHours(LocalTime.of(8, 0), LocalTime.of(15, 30), new ArrayList<DayOfWeek>(
                    Arrays.asList(DayOfWeek.of(1), DayOfWeek.of(3), DayOfWeek.of(5))), "135 0800-1530");

    private Name name;
    private Email email;
    private Set<Tag> tags;
    private Cost cost;
    private OperatingHours operatingHours;
    private ServiceName serviceName;
    private Address address;
    private VendorId vendorId;
    private Phone phone;

    /**
     * Creates a {@code VendorBuilder} with the default details.
     */
    public VendorBuilder() {
        name = DEFAULT_NAME;
        email = DEFAULT_EMAIL;
        tags = new HashSet<>();
        cost = DEFAULT_COST;
        operatingHours = DEFAULT_OPERATING_HOURS;
        address = DEFAULT_ADDRESS;
        vendorId = DEFAULT_VENDOR_ID;
        phone = DEFAULT_PHONE;
        serviceName = DEFAULT_SERVICE_NAME;
    }

    /**
     * Initializes the VendorBuilder with the data of {@code vendorToCopy}.
     */
    public VendorBuilder(Vendor vendorToCopy) {
        name = vendorToCopy.getName();
        email = vendorToCopy.getEmail();
        tags = new HashSet<>(vendorToCopy.getTags());
        address = vendorToCopy.getAddress();
        vendorId = vendorToCopy.getVendorId();
        phone = vendorToCopy.getPhone();
        serviceName = vendorToCopy.getServiceName();
        operatingHours = vendorToCopy.getOperatingHours();
        cost = vendorToCopy.getCost();
    }

    /**
     * Sets the {@code Name} of the {@code Vendor} that we are building.
     */
    public VendorBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Vendor} that we are building.
     */
    public VendorBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Vendor} that we are building.
     */
    public VendorBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Vendor} that we are building.
     */
    public VendorBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code VendorId} of the {@code Vendor} that we are building.
     */
    public VendorBuilder withVendorId(String vendorId) {
        this.vendorId = new VendorId(vendorId);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Vendor} that we are building.
     */
    public VendorBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Cost} of the {@code Vendor} that we are building.
     */
    public VendorBuilder withCost(String cost) {
        try {
            this.cost = new Cost(Double.parseDouble(cost));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code ServiceName} of the {@code Vendor} that we are building.
     */
    public VendorBuilder withServiceName(String serviceName) {
        this.serviceName = new ServiceName(serviceName);
        return this;
    }

    /**
     * Sets the {@code OperatingHours} of the {@code Vendor} that we are building.
     */
    public VendorBuilder withOperatingHours(String operatingHours) {
        try {
            this.operatingHours = ParserUtil.parseOperatingHours(operatingHours);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Vendor build() {
        return new Vendor(name, email, tags, vendorId, phone, serviceName, address, cost, operatingHours);
    }
}
