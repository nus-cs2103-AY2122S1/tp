package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

import seedu.address.model.tag.Tag;

import javax.swing.text.html.Option;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Compulsory Identity fields
    private final ClientId clientId;
    private final Name name;
    private final Email email;

    // Data fields
    private final Optional<Phone> phone;
    private final Optional<Address> address;
    private final Optional<RiskAppetite> riskAppetite;
    private final Optional<DisposableIncome> disposableIncome;
    private final Optional<CurrentPlan> currentPlan;
    private final Optional<LastMet> lastMet;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(ClientId clientId, Name name, Phone phone, Email email, Address address, RiskAppetite riskAppetite,
        DisposableIncome disposableIncome ,Set<Tag> tags) {

        requireAllNonNull(name, email, tags);
        this.clientId = clientId;
        this.name = name;
        this.phone = phone == null ? Optional.empty() : Optional.of(phone);
        this.email = email;
        this.address = address == null ? Optional.empty() : Optional.of(address);
        this.riskAppetite = riskAppetite == null ? Optional.empty() : Optional.of(riskAppetite);
        this.disposableIncome = disposableIncome == null ? Optional.empty() : Optional.of(disposableIncome);
        this.currentPlan = currentPlan == null ? Optional.empty() : Optional.of(currentPlan);
        this.lastMet = lastMet == null ? Optional.empty() : Optional.of(lastMet);
        this.tags.addAll(tags);
    }

    public ClientId getClientId() { return clientId; }

    public Name getName() {
        return name;
    }

    public Optional<Phone> getPhone() {

        return phone;

    }

    public Email getEmail() {
        return email;
    }

    public Optional<Address> getAddress() {

        return address;
    }

    public Optional<LastMet> getLastMet() {
        return lastMet;
    }

    public Optional<CurrentPlan> getCurrentPlan() {
        return currentPlan;
    }

    public Optional<RiskAppetite> getRiskAppetite() {

        return riskAppetite;
    }

    public Optional<DisposableIncome> getDisposableIncome() {
        return disposableIncome;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same email.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == null) {
            return false;

        }
        if (otherPerson == this) {
            return true;
        }
        return otherPerson != null
                &&  otherPerson.getClientId().equals(getClientId())
                    || otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getClientId().equals(getClientId())
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getRiskAppetite().equals(getRiskAppetite())
                && otherPerson.getDisposableIncome().equals(getDisposableIncome())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Client ID: ")
                .append(getClientId())
                .append("; Name: ")
                .append(getName())
                .append("; Email: ")
                .append(getEmail())
                .append("; Last Met: ")
                .append(getLastMet())
                .append("; current plans: ")
                .append(getCurrentPlan());

        if (!phone.isEmpty()) {
            builder.append("; Phone: ").append(getPhone().get().value);
        }

        if (!address.isEmpty()) {
            builder.append("; Address: ").append(getAddress().get().value);
        }

        if (!riskAppetite.isEmpty()) {
            builder.append("; Risk Appetite: ").append(getRiskAppetite().get().value);
        }

        if (!disposableIncome.isEmpty()) {
            builder.append("; Disposable Income: ").append(getDisposableIncome().get().value);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
