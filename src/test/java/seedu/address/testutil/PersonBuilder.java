package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.CurrentPlan;
import seedu.address.model.person.DisposableIncome;
import seedu.address.model.person.Email;
import seedu.address.model.person.LastMet;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RiskAppetite;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_CLIENTID ="1";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_LASTMET = "24-09-2021";
    public static final String DEFAULT_CURRENTPLAN = "Prudential PRUwealth";
    public static final String DEFAULT_RISKAPPETITE = "3";
    public static final String DEFAULT_DISPOSABLEINCOME = "10000";

    private ClientId clientId;
    private Name name;
    private Email email;
    private LastMet lastMet;
    private CurrentPlan currentPlan;
    private Optional<Phone> phone;
    private Optional<Address> address;
    private Optional<RiskAppetite> riskAppetite;
    private Optional<DisposableIncome> disposableIncome;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        clientId = new ClientId(DEFAULT_CLIENTID);
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        lastMet = new LastMet(DEFAULT_LASTMET);
        currentPlan = new CurrentPlan(DEFAULT_CURRENTPLAN);
        phone = Optional.of(new Phone(DEFAULT_PHONE));
        address = Optional.of(new Address(DEFAULT_ADDRESS));
        riskAppetite = Optional.of(new RiskAppetite(DEFAULT_RISKAPPETITE));
        disposableIncome = Optional.of(new DisposableIncome(DEFAULT_DISPOSABLEINCOME));
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        clientId = personToCopy.getClientId();
        name = personToCopy.getName();
        email = personToCopy.getEmail();
        phone = personToCopy.getPhone();
        address = personToCopy.getAddress();
        riskAppetite = personToCopy.getRiskAppetite();
        disposableIncome = personToCopy.getDisposableIncome();
        currentPlan = personToCopy.getCurrentPlan();
        lastMet = personToCopy.getLastMet();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code ClientId} of the {@code Person} that we are building.
     */
    public PersonBuilder withClientId(String clientId) {
        this.clientId = new ClientId(clientId);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = Optional.of(new Address(address));
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
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = Optional.of(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code RiskAppetite} of the {@code Person} that we are building.
     */
    public PersonBuilder withRiskAppetite(String riskAppetite) {
        this.riskAppetite = Optional.of(new RiskAppetite(riskAppetite));
        return this;
    }
  
    /**
     * Sets the {@code DisposableIncome} of the {@code Person} that we are building.
     */
    public PersonBuilder withDisposableIncome(String disposableIncome) {
        this.disposableIncome = Optional.of(new DisposableIncome(disposableIncome));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withLastMet(String lastMetDate) {
        this.lastMet = new LastMet(lastMetDate);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withCurrentPlan(String currentPlan) {
        this.currentPlan = new CurrentPlan(currentPlan);
        return this;
    }

    public Person build() {
        return new Person(clientId, name, phone.get(), email, address.get(), riskAppetite.get(),
            disposableIncome.get(), currentPlan, lastMet, tags);
    }

}
