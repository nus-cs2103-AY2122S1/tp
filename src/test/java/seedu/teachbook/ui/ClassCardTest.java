package seedu.teachbook.ui;

import static seedu.teachbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;

public class ClassCardTest {
    private static final ClassName TEST_CLASS_NAME = new ClassName("test");
    private static final Class TEST_CLASS = new Class(TEST_CLASS_NAME);

    @Test
    public void constructor_nullClassObj_throwsExceptionInInitializerError() {
        assertThrows(ExceptionInInitializerError.class, () -> new ClassCardTest.TestClassCardPart(null,
                1));
    }

    private static class TestClassCardPart extends ClassCard {
        TestClassCardPart(Class classObj, int displayedIndex) {
            super(classObj, displayedIndex);
        }

    }

}

