package seedu.programmer.model.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.programmer.testutil.TypicalClasses.CLASS_1;

public class ClassIdTest {

    @Test
    public void getClassNum_validClass_returnsCorrectly() {
        assertEquals(CLASS_1.getClassNum(), 1);
    }
}
