package seedu.track2gather.testutil;

import seedu.track2gather.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.model.person.attributes.Address;
import seedu.track2gather.model.person.attributes.CaseNumber;
import seedu.track2gather.model.person.attributes.Email;
import seedu.track2gather.model.person.attributes.Name;
import seedu.track2gather.model.person.attributes.NextOfKinAddress;
import seedu.track2gather.model.person.attributes.NextOfKinName;
import seedu.track2gather.model.person.attributes.NextOfKinPhone;
import seedu.track2gather.model.person.attributes.Period;
import seedu.track2gather.model.person.attributes.Phone;
import seedu.track2gather.model.person.attributes.QuarantineAddress;
import seedu.track2gather.model.person.attributes.ShnPeriod;
import seedu.track2gather.model.person.attributes.WorkAddress;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setCaseNumber(person.getCaseNumber());
        descriptor.setHomeAddress(person.getHomeAddress());
        descriptor.setWorkAddress(person.getWorkAddress());
        descriptor.setQuarantineAddress(person.getQuarantineAddress());
        descriptor.setShnPeriod(person.getShnPeriod());
        descriptor.setNextOfKinName(person.getNextOfKinName());
        descriptor.setNextOfKinPhone(person.getNextOfKinPhone());
        descriptor.setNextOfKinAddress(person.getNextOfKinAddress());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code CaseNumber} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withCaseNumber(String caseNumber) {
        descriptor.setCaseNumber(new CaseNumber(caseNumber));
        return this;
    }

    /**
     * Sets the {@code HomeAddress} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withHomeAddress(String homeAddress) {
        descriptor.setHomeAddress(new Address(homeAddress));
        return this;
    }

    /**
     * Sets the {@code WorkAddress} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withWorkAddress(String workAddress) {
        descriptor.setWorkAddress(new WorkAddress(new Address(workAddress)));
        return this;
    }

    /**
     * Sets the {@code QuarantineAddress} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withQuarantineAddress(String quarantineAddress) {
        descriptor.setQuarantineAddress(new QuarantineAddress(new Address(quarantineAddress)));
        return this;
    }

    /**
     * Sets the {@code ShnPeriod} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withShnPeriod(String shnPeriod) {
        descriptor.setShnPeriod(new ShnPeriod(new Period(shnPeriod)));
        return this;
    }

    /**
     * Sets the {@code NextOfKinName} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNextOfKinName(String nextOfKinName) {
        descriptor.setNextOfKinName(new NextOfKinName(new Name(nextOfKinName)));
        return this;
    }

    /**
     * Sets the {@code NextOfKinPhone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNextOfKinPhone(String nextOfKinPhone) {
        descriptor.setNextOfKinPhone(new NextOfKinPhone(new Phone(nextOfKinPhone)));
        return this;
    }

    /**
     * Sets the {@code NextOfKinAddress} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNextOfKinAddress(String nextOfKinAddress) {
        descriptor.setNextOfKinAddress(new NextOfKinAddress(new Address(nextOfKinAddress)));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
