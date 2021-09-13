package seedu.address.model.person;

/**
 * Represents an insurance policy that a person can own.
 */
public class Insurance {
    /** The suffix that is added to the end of each insurance type */
    public static final String INSURANCE_SUFFIX = " insurance";
    private InsuranceType type;

    /**
     * Class constructor
     * @param type The type of this insurance
     */
    public Insurance(InsuranceType type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return type.getTypeName() + INSURANCE_SUFFIX;
    }
}
