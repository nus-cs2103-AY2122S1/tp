package seedu.plannermd.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.util.AppUtil.checkArgument;

import java.util.Map;

public class Risk {

    public enum RiskLevel {
        HIGH,
        MEDIUM,
        LOW,
        UNCLASSIFIED
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Risks should only be either HIGH, MEDIUM OR LOW, and it should not be blank";

    private static final Map<String, RiskLevel> riskMap = Map.of(
            "HIGH", RiskLevel.HIGH,
            "MEDIUM", RiskLevel.MEDIUM,
            "LOW", RiskLevel.LOW
    );

    public final RiskLevel riskLevel;

    private Risk() {
        riskLevel = RiskLevel.UNCLASSIFIED;
    }

    /**
     * Constructs a {@code Risk}.
     *
     * @param risk A valid risk.
     */
    public Risk(String risk) {
        requireNonNull(risk);
        checkArgument(isValidRisk(risk), MESSAGE_CONSTRAINTS);
        riskLevel = riskMap.get(risk);
    }

    /**
     * Returns true if a given string is a valid risk.
     */
    public static boolean isValidRisk(String test) {
        return riskMap.containsKey(test);
    }

    /**
     * Factory method to return an unclassified risk.
     */
    public static Risk getUnclassifiedRisk() {
        return new Risk();
    }

    /**
     * Returns true if risk level is unclassified.
     */
    public boolean isUnclassified() {
        return riskLevel.equals(RiskLevel.UNCLASSIFIED);
    }

    @Override
    public String toString() {
        return riskLevel.name();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Risk // instanceof handles nulls
                && riskLevel.equals(((Risk) other).riskLevel)); // state check
    }

    @Override
    public int hashCode() {
        return riskLevel.hashCode();
    }
}
