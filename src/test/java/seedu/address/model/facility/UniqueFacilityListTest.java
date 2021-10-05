package seedu.address.model.facility;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Assert.assertThrows;

public class UniqueFacilityListTest {
    public final UniqueFacilityList uniqueFacilityList = new UniqueFacilityList();

    @Test
    public void add_null_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> uniqueFacilityList.add(null));
    }
}
