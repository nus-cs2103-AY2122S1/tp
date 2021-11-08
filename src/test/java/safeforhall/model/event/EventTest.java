package safeforhall.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.commands.CommandTestUtil.VALID_CAPACITY_BASKETBALL;
import static safeforhall.logic.commands.CommandTestUtil.VALID_DATE_VOLLEYBALL;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_VOLLEYBALL;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VENUE_BASKETBALL;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VENUE_FOOTBALL_TRAINING;
import static safeforhall.testutil.TypicalEvents.BASKETBALL;
import static safeforhall.testutil.TypicalEvents.DANCE;
import static safeforhall.testutil.TypicalEvents.SWIM;
import static safeforhall.testutil.TypicalEvents.VOLLEYBALL;

import org.junit.jupiter.api.Test;

import safeforhall.testutil.EventBuilder;
import safeforhall.testutil.TypicalPersons;

public class EventTest {
    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(BASKETBALL.isSameEvent(BASKETBALL));

        // null -> returns false
        assertFalse(BASKETBALL.isSameEvent(null));

        // same name, all other attributes different -> returns true
        Event editedBasketball = new EventBuilder(BASKETBALL).withCapacity(VALID_CAPACITY_BASKETBALL)
                .withVenue(VALID_VENUE_BASKETBALL)
                .build();
        assertTrue(BASKETBALL.isSameEvent(editedBasketball));

        // name differs in case, all other attributes same -> returns true
        Event editedVolleyball = new EventBuilder(VOLLEYBALL)
                .withEventName(VALID_NAME_VOLLEYBALL.toLowerCase()).build();
        assertTrue(VOLLEYBALL.isSameEvent(editedVolleyball));

        // different name, all other attributes same -> returns false
        editedBasketball = new EventBuilder(BASKETBALL).withEventName(VALID_NAME_VOLLEYBALL).build();
        assertFalse(BASKETBALL.isSameEvent(editedBasketball));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_VOLLEYBALL + " ";
        editedVolleyball = new EventBuilder(VOLLEYBALL).withEventName(nameWithTrailingSpaces).build();
        assertFalse(VOLLEYBALL.isSameEvent(editedVolleyball));
    }

    @Test
    public void hasUnvaccinatedResident() {
        // vaccinated
        assertFalse(BASKETBALL.hasUnvaccinatedResident());

        // non-vaccinated
        assertTrue(SWIM.hasUnvaccinatedResident());
    }

    @Test
    public void numOfUnvaccinatedResidents() {
        assertEquals(0, BASKETBALL.numOfUnvaccinatedResidents());

        assertEquals(1, SWIM.numOfUnvaccinatedResidents());

        assertEquals(2, DANCE.numOfUnvaccinatedResidents());
    }

    @Test
    public void isOver() {
        assertTrue(BASKETBALL.isOver());

        assertTrue(SWIM.isOver());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event basketballCopy = new EventBuilder(BASKETBALL).build();
        assertTrue(BASKETBALL.equals(basketballCopy));

        // same object -> returns true
        assertTrue(BASKETBALL.equals(BASKETBALL));

        // null -> returns false
        assertFalse(BASKETBALL.equals(null));

        // different type -> returns false
        assertFalse(BASKETBALL.equals(5));

        // different person -> returns false
        assertFalse(BASKETBALL.equals(VOLLEYBALL));

        // different name -> returns false
        Event editedBasketball = new EventBuilder(BASKETBALL).withEventName(VALID_NAME_VOLLEYBALL).build();
        assertFalse(BASKETBALL.equals(editedBasketball));

        // different phone -> returns false
        editedBasketball = new EventBuilder(BASKETBALL).withEventDate(VALID_DATE_VOLLEYBALL).build();
        assertFalse(BASKETBALL.equals(editedBasketball));

        // different email -> returns false
        editedBasketball = new EventBuilder(BASKETBALL).withVenue(VALID_VENUE_FOOTBALL_TRAINING).build();
        assertFalse(BASKETBALL.equals(editedBasketball));
    }

    @Test
    public void test_hasNoResidents_success() {
        Event editedBasketball = new EventBuilder(BASKETBALL).withCapacity(VALID_CAPACITY_BASKETBALL)
                .withVenue(VALID_VENUE_BASKETBALL)
                .withResidentList(TypicalPersons.GEORGE.getName().toString(), TypicalPersons.GEORGE.toString())
                .build();
        assertFalse(editedBasketball.hasNoResidents());

        editedBasketball = new EventBuilder(BASKETBALL).withCapacity(VALID_CAPACITY_BASKETBALL)
                .withVenue(VALID_VENUE_BASKETBALL)
                .withResidentList(ResidentList.DEFAULT_LIST, ResidentList.DEFAULT_LIST)
                .build();
        assertTrue(editedBasketball.hasNoResidents());
    }

    @Test
    public void checkHashCode() throws Exception {
        try {
            Event editedBasketball = new EventBuilder(BASKETBALL).withEventDate(VALID_DATE_VOLLEYBALL).build();
            editedBasketball.hashCode();
        } catch (NoSuchMethodError e) {
            throw new NoSuchMethodException("hashCode not overridden");
        }
    }
}
