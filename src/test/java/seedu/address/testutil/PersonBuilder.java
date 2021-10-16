package seedu.address.testutil;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_REMARK = "Can't attend midterms";
    public static final Collection<String> DEFAULT_MODULE_CODES = Set.of("CS2030S T12", "CS2040");

    private Name name;
    private Email email;
    private Remark remark;
    private Set<ModuleCode> moduleCodes;
    private Phone phone;
    private TeleHandle teleHandle;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        moduleCodes = DEFAULT_MODULE_CODES.stream()
                .map(SampleDataUtil::parseModuleCode).collect(Collectors.toSet());
        phone = new Phone("");
        teleHandle = new TeleHandle("");
        remark = new Remark("");
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        email = personToCopy.getEmail();
        remark = personToCopy.getRemark();
        moduleCodes = new HashSet<>(personToCopy.getModuleCodes());
        phone = personToCopy.getPhone();
        teleHandle = personToCopy.getTeleHandle();
    }

    /**e
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code moduleCodes} into a {@code Set<ModuleCode>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withModuleCodes(String... moduleCodes) {
        this.moduleCodes = SampleDataUtil.getModuleCodeSet(moduleCodes);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code TeleHandle} of the {@code Person} that we are building.
     */
    public PersonBuilder withTeleHandle(String teleHandle) {
        this.teleHandle = new TeleHandle(teleHandle);
        return this;
    }

    public Person build() {
        return new Person(name, email, moduleCodes, phone, teleHandle, remark);
    }

}
