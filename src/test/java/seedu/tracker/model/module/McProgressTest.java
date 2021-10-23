package seedu.tracker.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class McProgressTest {
    private final McProgress mcProgress = new McProgress(new Mc(12), new Mc(McProgressList.GE_REQUIREMENT),
            McProgressList.GE_TAG_TITLE);
    @Test
    public void getTarget_success() {
        Mc expectedTarget = new Mc(McProgressList.GE_REQUIREMENT);
        assertTrue(mcProgress.getTarget().equals(expectedTarget));
    }

    @Test
    public void getCompletionRatioTest() {
        // return ratio of completed / target if completed < target
        double expectedRatio = (double) 12 / 16;
        assertTrue(mcProgress.getCompletionRatio() == expectedRatio);

        // return 1 if completed = target
        McProgress completedProgress = new McProgress(mcProgress);
        completedProgress.setCompleted(new Mc(16));
        assertTrue(completedProgress.getCompletionRatio() == 1);

        //return 1 if completed > target
        McProgress overcompletedProgress = new McProgress(mcProgress);
        overcompletedProgress.setCompleted(new Mc(17));
        assertTrue(overcompletedProgress.getCompletionRatio() == 1);
    }

    @Test
    public void isCompletedTest() {
        //completed < target -> false
        assertFalse(mcProgress.isCompleted());

        //completed == target -> true
        McProgress completedProgress = new McProgress(mcProgress);
        completedProgress.setCompleted(new Mc(16));
        assertTrue(completedProgress.isCompleted());

        //completed > target -> true
        McProgress overcompletedProgress = new McProgress(mcProgress);
        overcompletedProgress.setCompleted(new Mc(17));
        assertTrue(overcompletedProgress.isCompleted());
    }
}
