package tutoraid.model.student;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.Assert;

public class ProgressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Progress(null));
    }

}
