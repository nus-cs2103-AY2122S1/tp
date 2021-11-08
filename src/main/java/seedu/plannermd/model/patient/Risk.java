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

    public static final String UNCLASSIFIED_RISK = RiskLevel.UNCLASSIFIED.name();

    public static final String MESSAGE_CONSTRAINTS =
            "Risks should only be either HIGH, MEDIUM OR LOW";

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
     * Returns true if a given string is a valid risk
     * including unclassified risk.
     */
    public static boolean isValidUnclassifiableRisk(String test) {
        return isValidRisk(test) || test.equals(RiskLevel.UNCLASSIFIED.name());
    }

    /**
     * Factory method to return an unclassified risk.
     */
    public static Risk getUnclassifiedRisk() {
        return new Risk();
    }

    /**
     * Factory method to return a risk that can be unclassified.
     */
    public static Risk getUnclassifiableRisk(String risk) {
        if (risk.equals(RiskLevel.UNCLASSIFIED.name())) {
            return getUnclassifiedRisk();
        }
        return new Risk(risk);
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
