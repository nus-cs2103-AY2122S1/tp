package seedu.address.model.person.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_ALMONDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_GRAPEFRUITS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_ALICE;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CustomerBuilder;



public class AllergyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Allergy(null));
    }

    @Test
    public void constructor_invalidAllergyName_throwsIllegalArgumentException() {
        String invalidAllergyName = "";
        assertThrows(IllegalArgumentException.class, () -> new Allergy(invalidAllergyName));
    }

    @Test
    public void isValidAllergyName() {
        // null allergy name
        assertThrows(NullPointerException.class, () -> Allergy.isValidAllergyName(null));
    }

    @Test
    public void equals() {
        Allergy allergy = new Allergy(VALID_ALLERGY_ALMONDS);

        // same values -> returns true
        Allergy toCopy = new Allergy(VALID_ALLERGY_ALMONDS);
        assertTrue(allergy.equals(toCopy));

        // same object -> returns true
        assertTrue(allergy.equals(allergy));

        // null -> returns false
        assertFalse(allergy.equals(null));

        // different type -> returns false
        assertFalse(allergy.equals(5));

        // different Allergy -> returns false
        Allergy different = new Allergy(VALID_ALLERGY_GRAPEFRUITS);
        assertFalse(allergy.equals(different));

    }

}
