package seedu.address.model.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;

public class SampleDataUtilTest {

    @Test
    public void getSampleAddressBook_success() {
        ReadOnlyAddressBook addressBook = SampleDataUtil.getSampleAddressBook();
        ArrayList<String> names = new ArrayList<>(List.of("Adam Oh", "Aishwarya Radhakrishnan Nair",
                "Cheong Yee Ming", "Damith C. Rajapakse", "Jai Lulla"));
        Assertions.assertTrue(addressBook.getPersonList().stream().allMatch(p -> names.contains(p.getName().fullName)));
    }
}
