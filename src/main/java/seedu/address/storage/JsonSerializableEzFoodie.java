package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.EzFoodie;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.member.Member;

/**
 * An Immutable EzFoodie that is serializable to JSON format.
 */
@JsonRootName(value = "ezfoodie")
class JsonSerializableEzFoodie {
    /**
     * Stands for message od duplicated members.
     */
    public static final String MESSAGE_DUPLICATE_MEMBER = "Members list contains duplicate member(s).";

    private final List<JsonAdaptedMember> members = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEzFoodie} with the given members.
     */
    @JsonCreator
    public JsonSerializableEzFoodie(@JsonProperty("members") List<JsonAdaptedMember> members) {
        this.members.addAll(members);
    }

    /**
     * Converts a given {@code ReadOnlyEzFoodie} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEzFoodie}.
     */
    public JsonSerializableEzFoodie(ReadOnlyEzFoodie source) {
        members.addAll(source.getMemberList().stream().map(JsonAdaptedMember::new).collect(Collectors.toList()));
    }

    /**
     * Converts this ezFoodie into the model's {@code EzFoodie} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EzFoodie toModelType() throws IllegalValueException {
        EzFoodie ezFoodie = new EzFoodie();
        for (JsonAdaptedMember jsonAdaptedMember : members) {
            Member member = jsonAdaptedMember.toModelType();
            if (ezFoodie.hasMember(member)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEMBER);
            }
            ezFoodie.addMember(member);
        }
        return ezFoodie;
    }

}
