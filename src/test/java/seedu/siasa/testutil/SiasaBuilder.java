package seedu.siasa.testutil;

import seedu.siasa.model.Siasa;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.Policy;

/**
 * A utility class to help with building objects.
 * Example usage: <br>
 *     {@code Siasa ab = new SiasaBuilder().withPerson("John", "Doe").build();}
 */
public class SiasaBuilder {

    private Siasa siasa;

    public SiasaBuilder() {
        siasa = new Siasa();
    }

    public SiasaBuilder(Siasa siasa) {
        this.siasa = siasa;
    }

    /**
     * Adds a new {@code Contact} to the {@code Siasa} that we are building.
     */
    public SiasaBuilder withContact(Contact contact) {
        siasa.addContact(contact);
        return this;
    }

    /**
     * Adds a new {@code Policy} to the {@code Siasa} that we are building.
     */
    public SiasaBuilder withPolicy(Policy policy) {
        siasa.addPolicy(policy);
        return this;
    }

    public Siasa build() {
        return siasa;
    }
}
