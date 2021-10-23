package seedu.fast.model.tag;

import seedu.fast.logic.parser.ParserUtil;

/**
 * An InvestmentPlanTag is a subset of a Tag, having a fixed name uneditable by the user.
 * Guarantees: immutable; name is fixed and valid as declared in {@link #isValidTagName(String)}
 */
public class InvestmentPlanTag extends Tag {

    public static final String INVESTMENT_PLAN_TAG_PREFIX = "ip/";

    public static final String MESSAGE_USAGE = "Investment Plan tag: label a client with their Investment plan "
        + "purchased. \n"
        + "The investment plans include insurance plans: \n"
        + " * Health, Life, Motor, Property and Travel\n"
        + "as well as Investment plan, and Savings plan.\n"
        + "Similar to tag, to apply an Investment Plan Tag, you have to use Add, Edit, or Tag command.\n\n"
        + "Parameters (using Edit): \n"
        + "edit INDEX t/ ip/INVESTMENT_PLAN\n\n"
        + "Example: \n"
        + "edit 3 t/ ip/motor\n\n"
        + "Parameters (using Tag): \n"
        + "tag INDEX t/ a/ ip/INVESTMENT_PLAN d/ ip/INVESTMENT_PLAN\n\n"
        + "Example: \n"
        + "t/ a/ ip/save d/ ip/property";
    public static final String INVESTMENT_PLAN_VALIDATION_REGEX = LifeInsurance.COMMAND + "|"
        + MotorInsurance.COMMAND + "|"
        + HealthInsurance.COMMAND + "|"
        + TravelInsurance.COMMAND + "|"
        + PropertyInsurance.COMMAND + "|"
        + Investment.COMMAND + "|"
        + Savings.COMMAND;

    /**
     * Constructs a {@code PriorityTag}.
     *
     * @param tagName A valid tag name.
     */
    public InvestmentPlanTag(String tagName) {
        super(ParserUtil.parseInvestmentPlanTag(tagName));
    }

    /**
     * Class containing relevant fields for a LifeInsurance Tag.
     */
    public class LifeInsurance {
        public static final String NAME = "LifeInsurance";

        public static final String TERM = "life";

        public static final String COMMAND = INVESTMENT_PLAN_TAG_PREFIX + TERM;

        //prevent instantiation of this class
        private LifeInsurance() {
        }
    }

    /**
     * Class containing relevant fields for a MotorInsurance Tag.
     */
    public class MotorInsurance {
        public static final String NAME = "MotorInsurance";

        public static final String TERM = "motor";

        public static final String COMMAND = INVESTMENT_PLAN_TAG_PREFIX + TERM;

        //prevent instantiation of this class
        private MotorInsurance() {
        }
    }

    /**
     * Class containing relevant fields for a HealthInsurance Tag.
     */
    public class HealthInsurance {
        public static final String NAME = "HealthInsurance";

        public static final String TERM = "health";

        public static final String COMMAND = INVESTMENT_PLAN_TAG_PREFIX + TERM;

        //prevent instantiation of this class
        private HealthInsurance() {
        }
    }

    /**
     * Class containing relevant fields for a TravelInsurance Tag.
     */
    public class TravelInsurance {
        public static final String NAME = "TravelInsurance";

        public static final String TERM = "travel";

        public static final String COMMAND = INVESTMENT_PLAN_TAG_PREFIX + TERM;

        //prevent instantiation of this class
        private TravelInsurance() {
        }
    }

    /**
     * Class containing relevant fields for a PropertyInsurance Tag.
     */
    public class PropertyInsurance {
        public static final String NAME = "PropertyInsurance";

        public static final String TERM = "property";

        public static final String COMMAND = INVESTMENT_PLAN_TAG_PREFIX + TERM;

        //prevent instantiation of this class
        private PropertyInsurance() {
        }
    }

    /**
     * Class containing relevant fields for a Investment Tag.
     */
    public class Investment {
        public static final String NAME = "investment";

        public static final String TERM = "invest";

        public static final String COMMAND = INVESTMENT_PLAN_TAG_PREFIX + TERM;

        //prevent instantiation of this class
        private Investment() {
        }
    }

    /**
     * Class containing relevant fields for a Savings Tag.
     */
    public class Savings {
        public static final String NAME = "Savings";

        public static final String TERM = "save";

        public static final String COMMAND = INVESTMENT_PLAN_TAG_PREFIX + TERM;

        //prevent instantiation of this class
        private Savings() {
        }
    }

}
