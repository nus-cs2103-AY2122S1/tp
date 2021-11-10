package seedu.address.model.util;

import java.time.LocalDate;

import seedu.address.model.Countdown;
import seedu.address.model.ReadOnlyCountdown;

/**
 * Contains utility methods for populating {@code Countdown} with sample data.
 */
public class SampleCountdownUtil {
    public static LocalDate getSampleDate() {
        return LocalDate.now();
    }

    public static ReadOnlyCountdown getSampleCountdown() {
        Countdown sampleCd = new Countdown();
        sampleCd.setDate(getSampleDate());
        return sampleCd;
    }

}
