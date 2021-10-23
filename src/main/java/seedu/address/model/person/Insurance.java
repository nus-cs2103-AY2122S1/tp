package seedu.address.model.person;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents an insurance policy that a person can own.
 */
public class Insurance {
    /** The suffix that is added to the end of each insurance type */
    public static final String INSURANCE_SUFFIX = " insurance";
    public static final String INVALID_ARG_SUFFIX = " is not an insurance type";
    public static final String MESSAGE_CONSTRAINTS =
            "Insurance type must be one of the following: "
            + String.join(", ", Arrays.stream(InsuranceType.values())
                    .map(InsuranceType::getTypeName)
                    .collect(Collectors.toList()));

    private InsuranceType type;
    private String name;

    /**
     * Class constructor
     * @param type The type of this insurance
     */
    public Insurance(InsuranceType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * Returns an Insurance with the given name
     * @param insuranceName The name of the Insurance to return
     * @return The Insurance with the supplied name
     */
    public static Insurance of(String insuranceName, String name) throws IllegalValueException {
        for (InsuranceType type : InsuranceType.values()) {
            if (type.getTypeName().equalsIgnoreCase(insuranceName)) {
                return new Insurance(type, name);
            }
        }
        throw new IllegalValueException(insuranceName + INVALID_ARG_SUFFIX);
    }

    @Deprecated
    public static Insurance of(String insuranceName) throws IllegalValueException {
        for (InsuranceType type : InsuranceType.values()) {
            if (type.getTypeName().equalsIgnoreCase(insuranceName)) {
                return new Insurance(type, "");
            }
        }
        throw new IllegalValueException(insuranceName + INVALID_ARG_SUFFIX);
    }

    /**
     * Gets the type of this Insurance
     * @return @code{type}
     */
    public InsuranceType getType() {
        return type;
    }

    /**
     * Returns a String representing the type this Insurance is
     * @return @code{type.getTypeName()}
     */
    public String getTypeName() {
        return type.getTypeName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Insurance)) {
            return false;
        } else {
            Insurance insuranceObj = (Insurance) obj;
            return this.type.equals(insuranceObj.type)
                    && this.name.equals(insuranceObj.name);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }

    @Override
    public String toString() {
        return type.getTypeName() + INSURANCE_SUFFIX + ": " + name;
    }
}
