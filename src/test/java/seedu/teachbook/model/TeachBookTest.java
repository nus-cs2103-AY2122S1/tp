package seedu.teachbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.teachbook.testutil.Assert.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class TeachBookTest {

    private final TeachBook teachBook = new TeachBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), teachBook.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teachBook.resetData(null));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> teachBook.getStudentList().remove(0));
    }

}

