package seedu.plannermd.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemarkTest {

    @Test
    void isEmpty_EMPTY_REMARK_trueReturned() {
        assertEquals(Remark.getEmptyRemark().isEmpty(), true);
    }

    @Test
    void isEmpty_emptyString_trueReturned() {
        assertEquals(new Remark("").isEmpty(), true);
    }

    @Test
    void isEmpty_sampleRemark_trueReturned() {
        assertEquals(new Remark("SampleRemark").isEmpty(), false);
    }
}