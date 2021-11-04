package safeforhall.model.util;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import safeforhall.model.ReadOnlyAddressBook;

/**
 * Class to ensure SampleDataUtil exists and has a method to return a ReadOnlyAddressBook.
 * The contents of this object is not verified to allow free editing of sample data without
 * failing the test case.
 */
public class SampleDataUtilTest {

    @Test
    public void testSampleData() {
        try {
            ReadOnlyAddressBook addressBook = SampleDataUtil.getSampleAddressBook();
        } catch (Exception e) {
            fail();
        }
    }
}
