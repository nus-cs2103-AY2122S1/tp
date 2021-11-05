package seedu.programmer.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.programmer.testutil.TypicalClasses.CLASS_1;
import static seedu.programmer.testutil.TypicalClasses.CLASS_10;

import org.junit.jupiter.api.Test;

public class ClassIdTest {

    @Test
    public void getClassNum_validClass_returnsCorrectly() {
        assertEquals(CLASS_1.getClassNum(), 1);
        assertEquals(CLASS_10.getClassNum(), 10);
    }
}
