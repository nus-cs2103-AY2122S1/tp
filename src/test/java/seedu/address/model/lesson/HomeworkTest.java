package seedu.address.model.lesson;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HomeworkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Homework(null));
    }

    @Test
    public void constructor_invalidHomeworkDescription_throwsIllegalArgumentException() {
        String invalidHomeworkDescription = "1234567890 this is more than 50 characters including"
            + "spaces";
        assertThrows(IllegalArgumentException.class, () -> new Homework(invalidHomeworkDescription));
    }

    @Test
    public void isValidHomeworkDescription() {
        // null homework description
        assertThrows(NullPointerException.class, () -> Homework.isValidDescription(null));
    }

}
