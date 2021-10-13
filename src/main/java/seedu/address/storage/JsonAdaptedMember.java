package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.data.Name;
import seedu.address.model.data.member.Address;
import seedu.address.model.data.member.Email;
import seedu.address.model.data.member.Member;
import seedu.address.model.data.member.Phone;
import seedu.address.model.position.Position;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

/**
 * Jackson-friendly version of {@link Member}.
 */
class JsonAdaptedMember {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Member's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedPosition> attachedPositions = new ArrayList<>();
    private final List<JsonAdaptedTask> attachedTasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedMember} with the given member details.
     */
    @JsonCreator
    public JsonAdaptedMember(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("attachedPosition") List<JsonAdaptedPosition> attachedPositions,
                             @JsonProperty("attachedTasks") List<JsonAdaptedTask> attachedTasks) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (attachedPositions != null) {
            this.attachedPositions.addAll(attachedPositions);
        }
        if (attachedTasks != null) {
            this.attachedTasks.addAll(attachedTasks);
        }
    }

    /**
     * Converts a given {@code Member} into this class for Jackson use.
     */
    public JsonAdaptedMember(Member source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().isPresent() ? source.getEmail().get().value : null;
        address = source.getAddress().isPresent() ? source.getAddress().get().value : null;
        attachedPositions.addAll(source.getPositions().stream()
                .map(JsonAdaptedPosition::new)
                .collect(Collectors.toList()));
        source.getTaskList().iterator().forEachRemaining((task -> attachedTasks.add(new JsonAdaptedTask(task))));
    }

    /**
     * Converts this Jackson-friendly adapted member object into the model's {@code Member} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted member.
     */

    public Member toModelType() throws IllegalValueException {
        final List<Position> memberPositions = new ArrayList<>();
        for (JsonAdaptedPosition position : attachedPositions) {
            memberPositions.add(position.toModelType());
        }

        final List<Task> memberTaskList = new ArrayList<>();
        for (JsonAdaptedTask task : attachedTasks) {
            memberTaskList.add(task.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        Email modelEmail;
        if (email == null) {
            modelEmail = null;
        } else {
            if (!Email.isValidEmail(email)) {
                throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
            }
            modelEmail = new Email(email);
        }

        Address modelAddress;
        if (address == null) {
            modelAddress = null;
        } else {
            if (!Address.isValidAddress(address)) {
                throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
            }
            modelAddress = new Address(address);
        }

        final Set<Position> modelPositions = new HashSet<>(memberPositions);
        final TaskList modelTaskList = new TaskList();
        modelTaskList.setTasks(memberTaskList);
        return new Member(modelName, modelPhone, modelEmail, modelAddress, modelPositions, modelTaskList);
    }

}
