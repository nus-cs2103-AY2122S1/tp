package seedu.address.testutil;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * A utility class containing a list of {@code Interview} objects to be used in tests.
 */
public class TypicalInterviews {
    public static final Interview ASSISTANT_INTERVIEW = new InterviewBuilder()
            .withPosition(TypicalPositions.ADMIN_ASSISTANT)
            .build();

    public static final Interview BOOKKEEPER_INTERVIEW = new InterviewBuilder()
            .withPosition(TypicalPositions.BOOKKEEPER)
            .build();
    public static final Interview HR_MANAGER_INTERVIEW = new InterviewBuilder()
            .withPosition(TypicalPositions.HR_MANAGER)
            .build();

    public static final Interview ACCOUNTANT_INTERVIEW = new InterviewBuilder().withPosition(new Position(new Title(
            "Accountant")))
            .withCandidates(new HashSet<>()).withDate(LocalDate.of(2021, 10, 15))
            .withStartTime(LocalTime.of(14, 0)).withDuration(Duration.ofMinutes(120))
            .withStatus(Interview.InterviewStatus.PENDING).build();

    public static final Interview BOOKKEEPER_INTERVIEW_2 = new InterviewBuilder().withPosition(new Position(new Title(
            "Bookkeeper")))
            .withCandidates(new HashSet<>()).withDate(LocalDate.of(2021, 12, 15))
            .withStartTime(LocalTime.of(14, 0)).withDuration(Duration.ofMinutes(120))
            .withStatus(Interview.InterviewStatus.PENDING).build();

    public static List<Interview> getTypicalInterviews() {
        return new ArrayList<>(Arrays.asList(ASSISTANT_INTERVIEW, BOOKKEEPER_INTERVIEW, HR_MANAGER_INTERVIEW));
    }
}
