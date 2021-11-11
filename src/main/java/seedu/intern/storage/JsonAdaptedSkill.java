package seedu.intern.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.intern.commons.exceptions.IllegalValueException;
import seedu.intern.model.skills.Skill;

/**
 * Jackson-friendly version of {@link Skill}.
 */
class JsonAdaptedSkill {

    private final String skillName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code skillName}.
     */
    @JsonCreator
    public JsonAdaptedSkill(String skillName) {
        this.skillName = skillName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedSkill(Skill source) {
        skillName = source.skillName;
    }

    @JsonValue
    public String getSkillName() {
        return skillName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Skill} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted skill.
     */
    public Skill toModelType() throws IllegalValueException {
        if (!Skill.isValidSkillName(skillName)) {
            throw new IllegalValueException(Skill.MESSAGE_CONSTRAINTS);
        }
        return new Skill(skillName);
    }

}
