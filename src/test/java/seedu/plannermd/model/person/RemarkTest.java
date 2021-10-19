package seedu.plannermd.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RemarkTest {

    @Test
    void isEmpty_emptyRemark_trueReturned() {
        assertEquals(Remark.getEmptyRemark().isEmpty(), true);
    }

    @Test
    void isEmpty_emptyString_trueReturned() {
        assertEquals(new Remark("").isEmpty(), true);
    }

    @Test
    void isEmpty_sampleRemark_trueReturned() {
        assertEquals(new Remark("Sample Remark").isEmpty(), false);
    }
}
