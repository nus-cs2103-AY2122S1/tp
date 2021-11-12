package seedu.address.model.gamefriendlink;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class SkillValue {
    public static final String MESSAGE_CONSTRAINTS = "Skill value assigned must be an integer "
            + "from 0 to 10 (inclusive).";
    private static final int SKILL_LEVEL_MIN = 0;
    private static final int SKILL_LEVEL_MAX = 10;

    public final int skillVal;

    /**
     * Constructs a {@code UserName}.
     *
     * @param skillVal A valid skill level.
     */
    public SkillValue(int skillVal) {
        checkArgument(validateSkillValue(skillVal), MESSAGE_CONSTRAINTS);
        this.skillVal = skillVal;
    }

    /**
     * Returns true if the given skill value provided is within the range 0 to 10 with bounds inclusive.
     */
    public static boolean validateSkillValue(int skillVal) {
        return skillVal >= SKILL_LEVEL_MIN && skillVal <= SKILL_LEVEL_MAX;
    }

    /**
     * Returns true if the given skill value string represents a valid integer and is within the range of [0, 10].
     *
     * @param skillValString string representing skill value to validate.
     * @return whether skill value string is valid integer and within range of [0, 10].
     */
    public static boolean isValidSkillValueString(String skillValString) {
        requireNonNull(skillValString);
        try {
            int skillVal = Integer.parseInt(skillValString);
            return validateSkillValue(skillVal);
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns the integer skill value of the {@Code SkillValue} object.
     *
     * @return Integer skill value.
     */
    public Integer getSkillVal() {
        return this.skillVal;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SkillValue // instanceof handles nulls
                && this.skillVal == (((SkillValue) other).skillVal)); // state check
    }

    @Override
    public String toString() {
        return String.valueOf(skillVal);
    }
}
