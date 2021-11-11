package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.testutil.Assert;

public class JsonAdaptedRequirementTest {
    @Test
    public void toModelType_invalidRequirement_throwsException() {
        JsonAdaptedRequirement invalidRequirement = new JsonAdaptedRequirement("");
        Assert.assertThrows(IllegalValueException.class, invalidRequirement::toModelType);
    }

    @Test
    public void getRequirementName_success() {
        String validRequirement = "cv";
        assertEquals(validRequirement, new JsonAdaptedRequirement(validRequirement).getRequirementName());
    }
}
