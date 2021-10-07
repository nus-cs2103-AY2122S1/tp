package seedu.siasa.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_COMMISSION_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_PRICE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_TITLE_CRITICAL;
import static seedu.siasa.testutil.TypicalPersons.ALICE;
import static seedu.siasa.testutil.TypicalPersons.BOB;
import static seedu.siasa.testutil.TypicalPolicies.CRITICAL_ILLNESS;
import static seedu.siasa.testutil.TypicalPolicies.FULL_LIFE;

import org.junit.jupiter.api.Test;

import seedu.siasa.model.person.Person;
import seedu.siasa.testutil.PersonBuilder;
import seedu.siasa.testutil.PolicyBuilder;

class PolicyTest {

    @Test
    public void isSamePolicy() {
        // same object -> returns true
        assertTrue(FULL_LIFE.isSamePolicy(FULL_LIFE));

        // null -> returns false
        assertFalse(FULL_LIFE.isSamePolicy(null));

        // same title and owner, all other attributes different -> returns true
        Policy editedFullLife = new PolicyBuilder(FULL_LIFE)
                .withPrice(VALID_POLICY_PRICE_CRITICAL)
                .withCommission(VALID_POLICY_COMMISSION_CRITICAL)
                .withExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL)
                .build();
        assertTrue(FULL_LIFE.isSamePolicy(editedFullLife));

        // different title, all other attributes same -> returns false
        editedFullLife = new PolicyBuilder(FULL_LIFE).withTitle(VALID_POLICY_TITLE_CRITICAL).build();
        assertFalse(FULL_LIFE.isSamePolicy(editedFullLife));

        // different owner, all other attributes same -> returns false
        editedFullLife = new PolicyBuilder(FULL_LIFE).withOwner(BOB).build();
        assertFalse(FULL_LIFE.isSamePolicy(editedFullLife));

        // title differs in case, all other attributes same -> returns false
        Policy editedCriticalIllness = new PolicyBuilder(CRITICAL_ILLNESS)
                .withTitle(VALID_POLICY_TITLE_CRITICAL + " extended")
                .build();
        assertFalse(CRITICAL_ILLNESS.isSamePolicy(editedCriticalIllness));

        // owner differs in name, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(ALICE).withName(ALICE.getName().fullName + " Sr").build();
        editedCriticalIllness = new PolicyBuilder(CRITICAL_ILLNESS).withOwner(editedBob).build();
        assertFalse(CRITICAL_ILLNESS.isSamePolicy(editedCriticalIllness));

        // owner differs in non identity fields -> returns true
        editedBob = new PersonBuilder(ALICE).withAddress("Simpson Road").build();
        editedCriticalIllness = new PolicyBuilder(CRITICAL_ILLNESS).withOwner(editedBob).build();
        assertTrue(CRITICAL_ILLNESS.isSamePolicy(editedCriticalIllness));

        // title has trailing spaces, all other attributes same -> returns false
        String titleWithTrailingSpaces = VALID_POLICY_TITLE_CRITICAL + " ";
        editedCriticalIllness = new PolicyBuilder(CRITICAL_ILLNESS).withTitle(titleWithTrailingSpaces).build();
        assertFalse(CRITICAL_ILLNESS.isSamePolicy(editedCriticalIllness));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Policy aliceCopy = new PolicyBuilder(FULL_LIFE).build();
        assertTrue(FULL_LIFE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(FULL_LIFE.equals(FULL_LIFE));

        // null -> returns false
        assertFalse(FULL_LIFE.equals(null));

        // different type -> returns false
        assertFalse(FULL_LIFE.equals(5));

        // different policy -> returns false
        assertFalse(FULL_LIFE.equals(CRITICAL_ILLNESS));

        // different title -> returns false
        Policy editedFullLife = new PolicyBuilder(FULL_LIFE).withTitle(VALID_POLICY_TITLE_CRITICAL).build();
        assertFalse(FULL_LIFE.equals(editedFullLife));

        // different price -> returns false
        editedFullLife = new PolicyBuilder(FULL_LIFE).withPrice(VALID_POLICY_PRICE_CRITICAL).build();
        assertFalse(FULL_LIFE.equals(editedFullLife));

        // different commission -> returns false
        editedFullLife = new PolicyBuilder(FULL_LIFE).withCommission(VALID_POLICY_COMMISSION_CRITICAL).build();
        assertFalse(FULL_LIFE.equals(editedFullLife));

        // different expiry date -> returns false
        editedFullLife = new PolicyBuilder(FULL_LIFE).withExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL).build();
        assertFalse(FULL_LIFE.equals(editedFullLife));

        // different owner -> returns false
        editedFullLife = new PolicyBuilder(FULL_LIFE).withOwner(BOB).build();
        assertFalse(FULL_LIFE.equals(editedFullLife));
    }
}
