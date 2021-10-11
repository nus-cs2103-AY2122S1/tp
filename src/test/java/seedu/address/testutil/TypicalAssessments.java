package seedu.address.testutil;

import java.util.Map;

import seedu.address.model.student.Assessment;
import seedu.address.model.student.ID;
import seedu.address.model.student.Score;

/**
 * A utility class containing a list of {@code Assessment} objects to be used in tests.
 */
public class TypicalAssessments {

    public static final Assessment PATH_1 = new AssessmentBuilder()
            .withValue("P01")
            .withScores(
                    Map.of(new ID("E0543948"), new Score("100"),
                            new ID("E0473477"), new Score("100"))
            ).build();
}
