package seedu.address.model.residency;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.residency.exceptions.DuplicatePersonRegException;
import seedu.address.model.residency.exceptions.DuplicateRoomRegException;
import seedu.address.model.residency.exceptions.ResidencyNotFoundException;
import seedu.address.model.room.Room;

public class ResidencyBook implements ReadOnlyResidencyBook {

    private final ObservableList<Residency> internalList = FXCollections.observableArrayList();
    private final ObservableList<Residency> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    private final HashMap<Room, Residency> roomMap = new HashMap<>();
    private final HashMap<Person, Residency> guestMap = new HashMap<>();
    private final boolean allowDuplicates;

    public ResidencyBook(boolean allowDuplicates) {
        this.allowDuplicates = allowDuplicates;
    }

    /**
     * Registers the residency of a set of guests in a room.
     *
     * @param room The {@code Room} to stay in
     * @param guests The {@code Set} of {@code Person}s to stay in the room
     * @throws DuplicateRoomRegException if the {@code Room} is already registered.
     * @throws DuplicatePersonRegException if any {@code Person} is already registered.
     */
    public void register(Room room, Set<Person> guests) {
        requireNonNull(room);
        requireAllNonNull(guests);

        register(new Residency(room, guests));
    }

    /**
     * Registers a {@code Residency}.
     */
    public void register(Residency residency) {
        requireNonNull(residency);
        Room room = residency.getRoom();
        Set<Person> guests = residency.getGuests();

        if (roomMap.containsKey(room) && !allowDuplicates) {
            throw new DuplicateRoomRegException();
        }
        for (Person guest : guests) {
            if (guestMap.containsKey(guest) && !allowDuplicates) {
                throw new DuplicatePersonRegException(guest);
            }
        }

        internalList.add(residency);
        roomMap.put(room, residency);
        for (Person guest : guests) {
            guestMap.put(guest, residency);
        }
    }

    /**
     * Removes and de-registers a {@code Residency}, making the room and guests
     * available for new registrations.
     *
     * @throws ResidencyNotFoundException if the given residency is not registered.
     */
    public void remove(Residency residency) {
        requireNonNull(residency);
        Room room = residency.getRoom();
        Set<Person> guests = residency.getGuests();

        if (!internalList.contains(residency)) {
            throw new ResidencyNotFoundException();
        }

        internalList.remove(residency);
        roomMap.remove(room);
        for (Person guest : guests) {
            guestMap.remove(guest);
        }
    }

    /**
     * Replaces a {@code Person} object with a new one in the relevant {@code Residency},
     * if it exists, and updates the registries accordingly.
     *
     * @param personToEdit The Person object to replace
     * @param editedPerson The Person object to replace with
     */
    public void edit(Person personToEdit, Person editedPerson) {
        requireAllNonNull(personToEdit, editedPerson);
        List<Residency> residencies = getResidencies(personToEdit);

        for (Residency residency : residencies) {
            remove(residency);
            residency.setGuest(personToEdit, editedPerson);
            register(residency);
        }
    }

    /**
     * Gets the {@code Residency} containing this {@code Room}, if it exists.
     *
     * @param room The room to get the residency of
     * @return An {@code Optional} with the {@code Residency} present if it exists,
     *         otherwise an empty Optional
     */
    public Optional<Residency> getResidency(Room room) {
        requireNonNull(room);
        return Optional.ofNullable(roomMap.get(room));
    }

    /**
     * Gets the {@code Residency} containing this {@code Person}, if it exists.
     *
     * @param guest The {@code Person} to get the residency of
     * @return An {@code Optional} with the {@code Residency} present if it exists,
     *         otherwise an empty Optional
     */
    public Optional<Residency> getResidency(Person guest) {
        requireNonNull(guest);
        return Optional.ofNullable(guestMap.get(guest));
    }

    /**
     * Gets the List of {@code Residency} containing this {@code Person}, if it exists.
     *
     * @param guest The {@code Person} to get the residency of
     * @return A {@code List} with the {@code Residency} that the {@code Person} is in.
     */
    public List<Residency> getResidencies(Person guest) {
        requireNonNull(guest);
        List<Residency> relevantResidencies = internalList.stream()
                .filter(residency -> residency.getGuests().contains(guest)).collect(Collectors.toUnmodifiableList());
        return relevantResidencies;
    }

    public void setResidencies(List<Residency> residencies) {
        internalList.clear();
        guestMap.clear();
        roomMap.clear();
        for (Residency residency : residencies) {
            register(residency);
        }
    }

    @Override
    public Map<Person, Residency> getGuestMap() {
        return Collections.unmodifiableMap(guestMap);
    }

    @Override
    public Map<Room, Residency> getRoomMap() {
        return Collections.unmodifiableMap(roomMap);
    }

    @Override
    public ObservableList<Residency> asUnmodifiableObservableList() {
        Comparator<Residency> cmp = new Comparator<Residency>() {
            @Override
            public int compare(Residency o1, Residency o2) {
                return o2.getCheckInTime().compareTo(o1.getCheckInTime());
            }
        };
        FXCollections.sort(internalList, cmp);
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResidencyBook // instanceof handles nulls
                && allowDuplicates == ((ResidencyBook) other).allowDuplicates
                && internalList.equals(((ResidencyBook) other).internalList));
    }
}
