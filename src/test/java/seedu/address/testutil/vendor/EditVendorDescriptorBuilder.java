package seedu.address.testutil.vendor;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.vendor.EditVendorCommand.EditVendorDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.ServiceName;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorId;

/**
 * A utility class to help with building EditVendorDescriptor objects.
 */
public class EditVendorDescriptorBuilder {

    private final EditVendorDescriptor descriptor;

    public EditVendorDescriptorBuilder() {
        descriptor = new EditVendorDescriptor();
    }

    public EditVendorDescriptorBuilder(EditVendorDescriptor descriptor) {
        this.descriptor = new EditVendorDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditVendorDescriptor} with fields containing {@code Vendor}'s details
     */
    public EditVendorDescriptorBuilder(Vendor vendor) {
        descriptor = new EditVendorDescriptor();
        descriptor.setName(vendor.getName());
        descriptor.setEmail(vendor.getEmail());
        descriptor.setTags(vendor.getTags());
        descriptor.setPhone(vendor.getPhone());
        descriptor.setAddress(vendor.getAddress());
        descriptor.setVendorId(vendor.getVendorId());
        descriptor.setOperatingHours(vendor.getOperatingHours());
        descriptor.setServiceName(vendor.getServiceName());
        descriptor.setCost(vendor.getCost());
    }

    /**
     * Sets the {@code Name} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code VendorId} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withVendorId(String vendorId) {
        descriptor.setVendorId(new VendorId(vendorId));
        return this;
    }

    /**
     * Sets the {@code Cost} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withCost(String cost) {
        try {
            descriptor.setCost(new Cost(Double.parseDouble(cost)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code ServiceName} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withServiceName(String serviceName) {
        descriptor.setServiceName(new ServiceName(serviceName));
        return this;
    }

    /**
     * Sets the {@code OperatingHours} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withOperatingHours(String operatingHours) {
        try {
            descriptor.setOperatingHours(ParserUtil.parseOperatingHours(operatingHours));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditVendorDescriptor}
     * that we are building.
     */
    public EditVendorDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditVendorDescriptor build() {
        return descriptor;
    }
}
