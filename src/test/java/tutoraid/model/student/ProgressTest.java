package tutoraid.model.student;

//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import tutoraid.testutil.Assert;

public class ProgressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Progress(null));
    }

}
