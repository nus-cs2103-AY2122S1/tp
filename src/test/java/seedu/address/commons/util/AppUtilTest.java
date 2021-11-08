package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests functionalities of AppUtil.
 */
public class AppUtilTest {

    /**
     * Checks whether image can be fetched from local successfully.
     */
    @Test
    public void getImage_exitingImage() {
        assertNotNull(AppUtil.getImage("/images/ezFoodie_icon.png"));
    }

    /**
     * Checks whether fetching a null image will throw an exception successfully.
     */
    @Test
    public void getImage_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AppUtil.getImage(null));
    }

    /**
     * Checks whether true will display no error message.
     */
    @Test
    public void checkArgument_true_nothingHappens() {
        AppUtil.checkArgument(true);
        AppUtil.checkArgument(true, "");
    }

    /**
     * Checks whether false without error message will throw an exception correctly.
     */
    @Test
    public void checkArgument_falseWithoutErrorMessage_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> AppUtil.checkArgument(false));
    }

    /**
     * Checks whether false with error message will throw an exception along with an error message correctly.
     */
    @Test
    public void checkArgument_falseWithErrorMessage_throwsIllegalArgumentException() {
        String errorMessage = "error message";
        assertThrows(IllegalArgumentException.class, errorMessage, () -> AppUtil.checkArgument(false, errorMessage));
    }
}
