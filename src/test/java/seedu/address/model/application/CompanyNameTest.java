package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.getTypicalApplications;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Contains tests for the CompanyName entity.
 */
public class CompanyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidCompanyName_throwsIllegalArgumentException() {
        // company name is blank
        String invalidCompanyName = "";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalidCompanyName));

        // company name too long
        String tooLongCompanyName = "verylongcompanynamethatislongerthan40characters";
        assertThrows(IllegalArgumentException.class, () -> new Company(tooLongCompanyName));
    }

    @Test
    public void isValidCompanyName() {
        // null name
        assertThrows(NullPointerException.class, () -> Company.isValidCompanyName(null));

        // invalid name
        assertFalse(Company.isValidCompanyName("")); // empty string
        assertFalse(Company.isValidCompanyName(" ")); // spaces only
        assertFalse(Company.isValidCompanyName("^")); // only non-alphanumeric characters
        assertFalse(Company.isValidCompanyName("peter*")); // contains non-alphanumeric characters
        assertFalse(Company.isValidCompanyName("verylongcompanynamethatislongerthan40characters")); // too long

        // valid name
        assertTrue(Company.isValidCompanyName("shopee")); // alphabets only
        assertTrue(Company.isValidCompanyName("12345")); // numbers only
        assertTrue(Company.isValidCompanyName("Shopee123")); // alphanumeric characters
        assertTrue(Company.isValidCompanyName("Shopee")); // with capital letters
        assertTrue(Company.isValidCompanyName("The Best Company In the World")); // long names
    }

    @Test
    public void equals() {
        Company name = new Company("Grab");

        // same object -> return true
        assertTrue(name.equals(name));

        // null -> return false
        assertFalse(name.equals(null));

        // different name -> return false
        assertFalse(name.equals(new Company("Shopee")));

        // same name -> return true
        assertTrue(name.equals(new Company("Grab")));
    }

    @Test
    public void toHashCode_success() {
        String amazonName = "Amazon";
        assertEquals(amazonName.hashCode(), AMAZON.getCompany().hashCode());
    }

    @Test
    public void getComparator_success() {
        List<Application> applicationList = getTypicalApplications();
        applicationList.sort(Company.getComparator());
    }

}
