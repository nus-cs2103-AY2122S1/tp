package seedu.address.model.participant;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Participant in an event.
 * Guarantees: name, phone, email, address, tags, birthDate, notes, nextOfKins are present and not null, field values
 * are validated, immutable.
 */
public class Participant {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final ParticipantId id;
    private final BirthDate birthDate;
    private final Set<Note> notes = new HashSet<>();
    private final ArrayList<NextOfKin> nextOfKins = new ArrayList<>();


    /**
     * Every field must be present and not null.
     *
     * @param name       Name object of the person.
     * @param phone      Phone object of the person.
     * @param email      Email object of the person.
     * @param address    Address object of the person.
     * @param tags       tags of the person.
     * @param birthDate  birthdate of the person.
     * @param notes      notes attached by the manager.
     * @param nextOfKin nextOfKin of the person.
     */
    public Participant(Name name, Phone phone, Email email, Address address, Set<Tag> tags, BirthDate birthDate,
                       Set<Note> notes, Collection<NextOfKin> nextOfKin) {
        requireAllNonNull(name, phone, email, address, tags, birthDate, notes);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.birthDate = birthDate;
        this.notes.addAll(notes);
        this.nextOfKins.addAll(nextOfKin);
        this.id = ParticipantId.of(this);
    }

    public Name getName() {
        return name;
    }

    public String getFullName() {
        return name.toString();
    }

    public Phone getPhone() {
        return phone;
    }

    public String getPhoneValue() {
        return phone.toString();
    }

    public Email getEmail() {
        return email;
    }

    public String getEmailValue() {
        return email.toString();
    }

    public Address getAddress() {
        return address;
    }

    public String getAddressValue() {
        return address.toString();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * @return this object's birthDate.
     */
    public BirthDate getBirthDate() {
        return birthDate;
    }

    public String getBirthDateString() {
        return birthDate.toString();
    }

    /**
     * Returns an Arraylist of next of kin that represents the participant's next of kins.
     *
     * @return this object's nextOfKins.
     */
    public ArrayList<NextOfKin> getNextOfKins() {
        return nextOfKins;
    }

    public ParticipantId getParticipantId() {
        return id;
    }

    public String getParticipantIdValue() {
        return id.toString();
    }

    /**
     * Adds a note to set of notes.
     *
     * @param note note to be added.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Removes a specific Note from the set.
     *
     * @param note    A Note to be removed.
     */
    public void removeNote(Note note) {
        notes.remove(note);
    }

    /**
     * Returns an immutable note set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Note> getNotes() {
        return Collections.unmodifiableSet(notes);
    }

    /**
     * Returns true if both participants have the same name.
     * This defines a weaker notion of equality between two persons.
     * This allow Participant to both pass in Person and Participant objects.
     */
    public boolean isSameParticipant(Participant otherParticipant) {
        if (otherParticipant == this) {
            return true;
        }

        return otherParticipant != null
                && otherParticipant.getName().equals(getName())
                && otherParticipant.getBirthDate().equals(getBirthDate());
    }

    /**
     * Returns true if both participants have the same identity and data fields.
     * This defines a stronger notion of equality between two participants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Participant)) {
            return false;
        }

        Participant otherParticipant = (Participant) other;
        return otherParticipant.getName().equals(getName())
                && otherParticipant.getPhone().equals(getPhone())
                && otherParticipant.getEmail().equals(getEmail())
                && otherParticipant.getAddress().equals(getAddress())
                && otherParticipant.getTags().equals(getTags())
                && otherParticipant.getBirthDate().equals(getBirthDate())
                && otherParticipant.getNotes().equals(getNotes())
                && otherParticipant.getNextOfKins().equals(getNextOfKins());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(), getTags(), birthDate, notes, nextOfKins);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());


        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Note> notes = getNotes();
        if (!notes.isEmpty()) {
            builder.append("; Notes: ");
            notes.forEach(builder::append);
        }

        ArrayList<NextOfKin> nextOfKins = getNextOfKins();
        if (!nextOfKins.isEmpty()) {
            builder.append("; Next Of Kins: ");
            nextOfKins.forEach(builder::append);
        }

        return builder.toString();
    }
}
