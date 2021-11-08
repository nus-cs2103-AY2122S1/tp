package safeforhall.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.find.FindPersonCommand;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.Name;
import safeforhall.model.person.VaccStatus;

public class FindCompositePredicateTest {

    @Test
    public void equals() {
        FindPersonCommand.FindCompositePredicate firstPredicate = new FindPersonCommand.FindCompositePredicate();
        firstPredicate.setName(new Name("Alice"));
        firstPredicate.setRoom("A100");
        firstPredicate.setFaculty(new Faculty("SoC"));
        firstPredicate.setVaccStatus(new VaccStatus("T"));

        FindPersonCommand.FindCompositePredicate secondPredicate = new FindPersonCommand.FindCompositePredicate();

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindPersonCommand.FindCompositePredicate firstPredicateCopy = new FindPersonCommand.FindCompositePredicate();
        firstPredicateCopy.setName(new Name("Alice"));
        firstPredicateCopy.setRoom("A100");
        firstPredicateCopy.setFaculty(new Faculty("SoC"));
        firstPredicateCopy.setVaccStatus(new VaccStatus("T"));

        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
