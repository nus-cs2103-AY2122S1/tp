package seedu.address.testutil;

import java.util.Map;

import seedu.address.model.student.Assessment;

/**
 * A utility class containing a list of {@code Assessment} objects to be used in tests.
 */
public class TypicalAssessments {

    public static final Assessment PATH_1 = new AssessmentBuilder()
            .withValue("P01")
            .withScores(
                    Map.of("E0543948", "100",
                            "E0473477", "100")
            ).build();
}
