package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.allPrefixLess;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.mapper.PrefixMapper;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final ClientId clientId;
    private final Name name;
    private final Email email;

    // Data fields
    private final Phone phone;
    private final Address address;
    private final RiskAppetite riskAppetite;
    private final DisposableIncome disposableIncome;
    private final CurrentPlan currentPlan;
    private final LastMet lastMet;
    private final NextMeeting nextMeeting;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Client(ClientId clientId, Name name, Phone phone, Email email, Address address, RiskAppetite riskAppetite,
                  DisposableIncome disposableIncome, CurrentPlan currentPlan, LastMet lastMet, NextMeeting nextMeeting,
                  Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags, riskAppetite, disposableIncome, currentPlan, lastMet,
            nextMeeting
        );
        this.clientId = clientId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.riskAppetite = riskAppetite;
        this.disposableIncome = disposableIncome;
        this.currentPlan = currentPlan;
        this.lastMet = lastMet;
        this.nextMeeting = nextMeeting;
        addTags(tags);
    }

    /**
     * Assigns {@code tags} to the {@code Client}.
     *
     * @param tags Tags to be added.
     */
    public void addTags(Set<Tag> tags) {
        this.tags.addAll(tags);
        this.tags.forEach(tag -> tag.addClient(this));
    }

    public boolean isSameDayMeeting(LocalDate date) {
        return nextMeeting.isSameDay(date);
    }

    /**
     * Removes lingering references to this client.
     * Invokes this when the client is no longer referenced.
     */
    public void delete() {
        tags.forEach(tag -> tag.removeClient(this));
    }

    /**
     * Returns true if the client has next meeting.
     *
     * @return true if the client has next meeting.
     */
    public boolean hasNextMeeting() {
        return !nextMeeting.isNullMeeting();
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == null) {
            return false;

        }

        if (otherClient == this) {
            return true;
        }

        return otherClient.getClientId().equals(getClientId())
            || (otherClient.getName().equals(getName()) && otherClient.getEmail().equals(getEmail()));
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public LocalDate getNextMeetingDate() {
        return nextMeeting.getDate();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, lastMet, currentPlan, tags);
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getClientId().equals(getClientId())
            && otherClient.getName().equals(getName())
            && otherClient.getPhone().equals(getPhone())
            && otherClient.getEmail().equals(getEmail())
            && otherClient.getAddress().equals(getAddress())
            && otherClient.getRiskAppetite().equals(getRiskAppetite())
            && otherClient.getDisposableIncome().equals(getDisposableIncome())
            && otherClient.getCurrentPlan().equals(getCurrentPlan())
            && otherClient.getLastMet().equals(getLastMet())
            && otherClient.getTags().equals(getTags());

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(": ")
            .append(getClientId())
            .append("; Name: ")
            .append(getName())
            .append("; Email: ")
            .append(getEmail())
            .append("; Phone: ")
            .append(getPhone())
            .append("; Address: ")
            .append(getAddress())
            .append("; Risk Appetite: ")
            .append(getRiskAppetite())
            .append("; Disposable Income: ")
            .append(getDisposableIncome())
            .append("; current plans: ")
            .append(getCurrentPlan())
            .append("; Last Met: ")
            .append(getLastMet())
            .append("; Next Meeting: ")
            .append(getNextMeeting());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    public ClientId getClientId() {
        return clientId;
    }

    public Phone getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public RiskAppetite getRiskAppetite() {
        return riskAppetite;
    }

    public DisposableIncome getDisposableIncome() {
        return disposableIncome;
    }

    public CurrentPlan getCurrentPlan() {
        return currentPlan;
    }

    public LastMet getLastMet() {
        return lastMet;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public NextMeeting getNextMeeting() {
        return nextMeeting;
    }

    /**
     * Stores the details to edit the client with. Each non-empty field value will replace the
     * corresponding field value of the client.
     */
    public static class EditClientDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private RiskAppetite riskAppetite;
        private DisposableIncome disposableIncome;
        private Set<Tag> tags;
        private LastMet lastMet;
        private NextMeeting nextMeeting;
        private CurrentPlan currentPlan;

        public EditClientDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditClientDescriptor(EditClientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setRiskAppetite(toCopy.riskAppetite);
            setDisposableIncome(toCopy.disposableIncome);
            setLastMet(toCopy.lastMet);
            setNextMeeting(toCopy.nextMeeting);
            setCurrentPlan(toCopy.currentPlan);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, riskAppetite, disposableIncome,
                    currentPlan, lastMet, nextMeeting, tags);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<LastMet> getLastMet() {
            return Optional.ofNullable(lastMet);
        }

        public void setLastMet(LastMet lastMet) {
            this.lastMet = lastMet;
        }

        public Optional<NextMeeting> getNextMeeting() {
            return Optional.ofNullable(nextMeeting);
        }

        public void setNextMeeting(NextMeeting nextMeeting) {
            this.nextMeeting = nextMeeting;
        }

        public Optional<CurrentPlan> getCurrentPlan() {
            return Optional.ofNullable(currentPlan);
        }

        public void setCurrentPlan(CurrentPlan currentPlan) {
            this.currentPlan = currentPlan;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<RiskAppetite> getRiskAppetite() {
            return Optional.ofNullable(riskAppetite);
        }

        public void setRiskAppetite(RiskAppetite riskAppetite) {
            this.riskAppetite = riskAppetite;
        }

        public Optional<DisposableIncome> getDisposableIncome() {
            return Optional.ofNullable(disposableIncome);
        }

        public void setDisposableIncome(DisposableIncome disposableIncome) {
            this.disposableIncome = disposableIncome;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns a {@code EditClientDescriptor} with the attributes from {@code toCopy}.
         */
        public static EditClientDescriptor copyClientDescriptor(Client toCopy) {
            assert toCopy != null;
            EditClientDescriptor editClientDescriptor = new EditClientDescriptor();

            Arrays.stream(allPrefixLess(PREFIX_CLIENTID, PREFIX_TAG))
                    .map(PrefixMapper::getAttributeAndSetFunction)
                    .forEach(f -> f.accept(editClientDescriptor, toCopy));

            editClientDescriptor.setTags(toCopy.tags);
            return editClientDescriptor;
        }

        /**
         * Creates and returns a {@code Client} with the details of {@code clientToEdit}
         * edited with {@code editClientDescriptor}.
         */
        public static Client createEditedClient(Client clientToEdit, EditClientDescriptor editClientDescriptor) {
            assert clientToEdit != null;

            EditClientDescriptor clientDescriptor = copyClientDescriptor(clientToEdit);
            Arrays.stream(allPrefixLess(PREFIX_CLIENTID, PREFIX_TAG))
                    .map(PrefixMapper::getEditAndSetFunction)
                    .forEach(f -> f.accept(editClientDescriptor, clientDescriptor));

            NextMeeting updatedNextMeeting = clientDescriptor.nextMeeting.copyNextMeeting();
            if (!updatedNextMeeting.isNullMeeting()) {
                updatedNextMeeting.setWithWho(clientDescriptor.name);
            }

            clientDescriptor.setNextMeeting(updatedNextMeeting);
            editClientDescriptor.getTags().ifPresent(clientDescriptor::setTags);

            return clientDescriptor.createClient(clientToEdit.getClientId());
        }

        /**
         * Creates and returns a {@code Client} with the {@code LastMet} date being replaced by
         * the {@code NextMeeting} date and {@code NextMeeting} being set to {@code NullMeeting}
         */
        public static Client createEditedMeetingOverClient(Client clientToEdit) {
            assert clientToEdit != null;

            EditClientDescriptor clientDescriptor = copyClientDescriptor(clientToEdit);
            LastMet updatedLastMet = clientToEdit.getLastMet().getLaterLastMet(
                    clientToEdit.getNextMeeting().convertToLastMet()
            );
            clientDescriptor.setLastMet(updatedLastMet);
            clientDescriptor.setNextMeeting(NextMeeting.NULL_MEETING);

            return clientDescriptor.createClient(clientToEdit.clientId);
        }

        /**
         * Returns a new {@code Client} with the given {@code ClientId} and attributes set
         * in this {@code EditClientDescriptor}.
         */
        public Client createClient(ClientId clientId) {
            return new Client(clientId, name, phone, email, address, riskAppetite, disposableIncome,
                    currentPlan, lastMet, nextMeeting, tags);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditClientDescriptor)) {
                return false;
            }

            // state check
            EditClientDescriptor e = (EditClientDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getLastMet().equals(e.getLastMet())
                    && getNextMeeting().equals(e.getNextMeeting())
                    && getCurrentPlan().equals(e.getCurrentPlan())
                    && getDisposableIncome().equals(e.getDisposableIncome())
                    && getRiskAppetite().equals(e.getRiskAppetite())
                    && getNextMeeting().equals(e.getNextMeeting())
                    && getTags().equals(e.getTags());
        }
    }
}
