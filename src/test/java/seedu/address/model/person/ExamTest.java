package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.lesson.Subject;
import seedu.address.testutil.TestUtil;

class ExamTest {

    private Subject math = new Subject("Math");
    private Subject biology = new Subject("Biology");

    private LocalDateTime january1Late = TestUtil.createLocalDateTime("2021-01-01 23:59");
    private LocalDateTime january1Early = TestUtil.createLocalDateTime("2021-01-01 00:01");
    private LocalDateTime january2Late = TestUtil.createLocalDateTime("2021-01-02 23:59");

    @Test
    void testEquals_sameDetailsDifferentObjects_returnsTrue() {
        Subject math2 = new Subject("Math");
        LocalDateTime january1Late2 = TestUtil.createLocalDateTime("2021-01-01 23:59");
        assertEquals(new Exam(math, january1Late), new Exam(math2, january1Late2));
    }

    @Test
    void testEquals_differentDetails_returnsFalse() {
        // same time different subject
        assertNotEquals(new Exam(math, january1Late), new Exam(biology, january1Late));
        // same subject different time
        assertNotEquals(new Exam(math, january1Late), new Exam(math, january2Late));
    }

    @Test
    void compareTo() {
        Subject math = new Subject("Math");
        Exam jan1Late = new Exam(math, january1Late);
        Exam jan1Early = new Exam(math, january1Early);
        Exam jan2Late = new Exam(math, january2Late);

        // different time, same day
        assertEquals(jan1Late.compareTo(jan1Early), 1);
        assertEquals(jan1Early.compareTo(jan1Late), -1);

        // different day, same time
        assertEquals(jan2Late.compareTo(jan1Late), 1);
        assertEquals(jan1Late.compareTo(jan2Late), -1);

        // exactly the same, should be 0.
        assertEquals(jan1Early.compareTo(jan1Early), 0);
    }
}
