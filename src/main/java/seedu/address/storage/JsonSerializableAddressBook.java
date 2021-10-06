package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendsList;
import seedu.address.model.ReadOnlyFriendsList;
import seedu.address.model.friend.Friend;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedFriend> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedFriend> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyFriendsList source) {
        persons.addAll(source.getFriendsList().stream().map(JsonAdaptedFriend::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FriendsList toModelType() throws IllegalValueException {
        FriendsList friendsList = new FriendsList();
        for (JsonAdaptedFriend jsonAdaptedFriend : persons) {
            Friend friend = jsonAdaptedFriend.toModelType();
            if (friendsList.hasFriend(friend)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            friendsList.addFriend(friend);
        }
        return friendsList;
    }

}
