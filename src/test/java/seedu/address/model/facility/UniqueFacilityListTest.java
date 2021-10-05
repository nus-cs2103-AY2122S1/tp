package seedu.address.model.facility;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UniqueFacilityListTest {
    public final UniqueFacilityList uniqueFacilityList = new UniqueFacilityList();

    @Test
    public void add_null_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> uniqueFacilityList.add(null));
    }
}
