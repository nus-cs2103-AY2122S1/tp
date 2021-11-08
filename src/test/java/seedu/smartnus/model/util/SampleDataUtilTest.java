package seedu.smartnus.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.model.util.SampleDataUtil.getSampleSmartNus;

import org.junit.jupiter.api.Test;

import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.question.Question;


public class SampleDataUtilTest {
    @Test
    public void getSampleSmartNusTest() {
        ReadOnlySmartNus sampleSmartNus = getSampleSmartNus();
        for (Question question : sampleSmartNus.getQuestionList()) {
            assertTrue(question.isValidQuestion());
        }
    }
}
