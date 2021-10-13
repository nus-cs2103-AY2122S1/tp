package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_DESCRIPTOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_DESCRIPTOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddScoreCommand.ScoreDescriptor;
import seedu.address.testutil.ScoreDescriptorBuilder;

public class ScoreDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        ScoreDescriptor descriptorWithSameValues = new ScoreDescriptor(SCORE_DESCRIPTOR_AMY);
        assertEquals(SCORE_DESCRIPTOR_AMY, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(SCORE_DESCRIPTOR_AMY, SCORE_DESCRIPTOR_AMY);

        // null -> returns false
        assertNotEquals(SCORE_DESCRIPTOR_AMY, null);

        // different types -> returns false
        assertNotEquals(SCORE_DESCRIPTOR_AMY, 5);

        // different values -> returns false
        assertNotEquals(SCORE_DESCRIPTOR_AMY, SCORE_DESCRIPTOR_BOB);

        // different assessment -> returns false
        ScoreDescriptor editedAmy = new ScoreDescriptorBuilder(SCORE_DESCRIPTOR_AMY)
                .withAssessment(VALID_ASSESSMENT_BOB).build();
        assertNotEquals(SCORE_DESCRIPTOR_AMY, editedAmy);

        // different name -> returns false
        editedAmy = new ScoreDescriptorBuilder(SCORE_DESCRIPTOR_AMY)
                .withName(VALID_NAME_BOB).build();
        assertNotEquals(SCORE_DESCRIPTOR_AMY, editedAmy);

        // different ID -> returns false
        editedAmy = new ScoreDescriptorBuilder(SCORE_DESCRIPTOR_AMY)
                .withId(VALID_ID_BOB).build();
        assertNotEquals(SCORE_DESCRIPTOR_AMY, editedAmy);

        // different score -> returns false
        editedAmy = new ScoreDescriptorBuilder(SCORE_DESCRIPTOR_AMY)
                .withScore(VALID_SCORE_BOB).build();
        assertNotEquals(SCORE_DESCRIPTOR_AMY, editedAmy);
    }
}
