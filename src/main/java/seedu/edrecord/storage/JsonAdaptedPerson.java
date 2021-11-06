package seedu.edrecord.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.edrecord.commons.exceptions.IllegalValueException;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleSet;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.AssignmentGradeMap;
import seedu.edrecord.model.person.Email;
import seedu.edrecord.model.person.Info;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.model.person.Phone;
import seedu.edrecord.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String info;
    private final String mods;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String info,
                             @JsonProperty("modules") String mods,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.info = info;
        this.mods = mods;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().name;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        info = source.getInfo().value;
        mods = source.getModules().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
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

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (info == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Info.class.getSimpleName()));
        }
        if (!Info.isValidInfo(info)) {
            throw new IllegalValueException(Info.MESSAGE_CONSTRAINTS);
        }
        final Info modelInfo = new Info(info);

        if (mods == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleSet.class.getSimpleName()));
        }

        ModuleSet moduleSet = new ModuleSet();
        String[] modGroupPairs = mods.split(" ");
        for (String modGroupPair : modGroupPairs) {
            String[] modGroupArray = modGroupPair.split(":");
            if (modGroupArray.length != 2) {
                throw new IllegalValueException(ModuleSet.MESSAGE_CONSTRAINTS);
            }
            String mod = modGroupArray[0];
            if (!Module.isValidModuleCode(mod)) {
                throw new IllegalValueException(Module.MESSAGE_CONSTRAINTS);
            }
            if (!Module.MODULE_SYSTEM.hasModule(mod)) {
                throw new IllegalValueException(String.format(Module.MESSAGE_DOES_NOT_EXIST, mod));
            }
            final Module modelModule = Module.MODULE_SYSTEM.getModule(mod);

            String groups = modGroupArray[1];
            if (groups.length() <= 1 || groups.charAt(0) != '[' || groups.charAt(groups.length() - 1) != ']') {
                throw new IllegalValueException(ModuleSet.MESSAGE_GROUP_CONSTRAINTS);
            }
            groups = ModuleSet.parseGroups(groups);
            String[] groupArray = groups.split(",");

            for (String group : groupArray) {
                if (!Group.isValidGroup(group)) {
                    throw new IllegalValueException(Group.MESSAGE_CONSTRAINTS);
                }
                if (!modelModule.getGroupSystem().hasGroup(group)) {
                    throw new IllegalValueException(Group.MESSAGE_DOES_NOT_EXIST);
                }
                final Group modelGroup = modelModule.getGroup(group);
                if (!modelModule.getGroupSystem().hasGroup(modelGroup)) {
                    modelModule.getGroupSystem().addGroup(modelGroup);
                }
                moduleSet.addToSet(modelModule);
            }
        }
        final Set<Tag> modelTags = new HashSet<>(personTags);

        // TODO change `new AssignmentGradeMap()` to save grades
        return new Person(modelName, modelPhone, modelEmail, modelInfo, moduleSet, modelTags,
                new AssignmentGradeMap());
    }

}
