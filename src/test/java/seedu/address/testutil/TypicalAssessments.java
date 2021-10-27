package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTUAL_SCORE_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTUAL_SCORE_QUIZ1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_NAME_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_NAME_QUIZ1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TOTAL_SCORE_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TOTAL_SCORE_QUIZ1;

import seedu.address.model.assessment.Assessment;

public class TypicalAssessments {

    public static final Assessment MIDTERMS = new AssessmentBuilder().withAssesmentName("Midterms")
            .withScore(20, 100).build();
    public static final Assessment FINALS = new AssessmentBuilder().withAssesmentName("Finals")
            .withScore(65, 75).build();

    // Manually added
    public static final Assessment FIELDWORK = new AssessmentBuilder().withAssesmentName("Fieldwork")
            .withScore(1, 5).build();
    public static final Assessment PRESENTATION = new AssessmentBuilder().withAssesmentName("Presentation")
            .withScore(90, 100).build();

    // Manually added - Assessment's details found in {@code CommandTestUtil}
    public static final Assessment QUIZ1 = new AssessmentBuilder().withAssesmentName(VALID_ASSESSMENT_NAME_QUIZ1)
            .withScore(VALID_ACTUAL_SCORE_QUIZ1, VALID_TOTAL_SCORE_QUIZ1).build();
    public static final Assessment LAB5 = new AssessmentBuilder().withAssesmentName(VALID_ASSESSMENT_NAME_LAB5)
            .withScore(VALID_ACTUAL_SCORE_LAB5, VALID_TOTAL_SCORE_LAB5).build();
}
