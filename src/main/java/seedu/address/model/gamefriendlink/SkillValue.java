package seedu.address.model.gamefriendlink;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.exceptions.ParseException;

public class SkillValue {
    public static final String MESSAGE_CONSTRAINTS = "Skill value assigned must be an integer "
            + "from 0 to 10 (inclusive).";
    private static final Integer SKILL_LEVEL_MIN = 0;
    private static final Integer SKILL_LEVEL_MAX = 10;

    public final Integer skillVal;

    /**
     * Constructs a {@code UserName}.
     *
     * @param skillVal A valid skill level.
     */
    public SkillValue(Integer skillVal) {
        requireNonNull(skillVal);
        checkArgument(validateSkillValue(skillVal), MESSAGE_CONSTRAINTS);
        this.skillVal = skillVal;
    }

    /**
     * Returns true if the given skill value provided is within the range 0 to 10 with bounds inclusive.
     */
    public static boolean validateSkillValue(Integer skillVal) {
        requireNonNull(skillVal);
        return skillVal >= SKILL_LEVEL_MIN && skillVal <= SKILL_LEVEL_MAX;
    }

    /**
     * Returns true if the given skill value string represents a valid integer and is within the range of [0, 10].
     *
     * @param skillValString string representing skill value to validate.
     * @return whether skill value string is valid integer and within range of [0, 10].
     * @throws ParseException thrown when
     */
    public static boolean validateSkillValueString(String skillValString) {
        requireNonNull(skillValString);
        try {
            int skillVal = Integer.parseInt(skillValString);
            return skillVal >= SKILL_LEVEL_MIN && skillVal <= SKILL_LEVEL_MAX;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public Integer getSkillVal() {
        return this.skillVal;
    }

    @Override
    public String toString() {
        return this.skillVal.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SkillValue // instanceof handles nulls
                && this.skillVal.equals(((SkillValue) other).skillVal)); // state check
    }

    @Override
    public int hashCode() {
        return skillVal.hashCode();
    }

}
