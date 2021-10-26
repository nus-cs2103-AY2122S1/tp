package safeforhall.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import safeforhall.testutil.PersonBuilder;

public class RoomValidCheckPredicateTest {

    private static final String INVALID_ROOM_FOR_FIND1 = "AA";
    private static final String INVALID_ROOM_FOR_FIND2 = "A12";
    private static final String INVALID_ROOM_FOR_FIND3 = "12";

    private static final String VALID_ROOM_FOR_FIND1 = "A";
    private static final String VALID_ROOM_FOR_FIND2 = "A1";
    private static final String VALID_ROOM_FOR_FIND3 = "E200";
    private static final String VALID_ROOM_FOR_FIND4 = "2";

    @Test
    public void equals() {
        String input1 = "A100";
        String input2 = "A1";

        RoomValidCheckPredicate firstPredicate = new RoomValidCheckPredicate(input1);
        RoomValidCheckPredicate secondPredicate = new RoomValidCheckPredicate(input2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RoomValidCheckPredicate firstPredicateCopy = new RoomValidCheckPredicate(input1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_validRoomForFind_returnsTrue() {
        RoomValidCheckPredicate predicate = new RoomValidCheckPredicate(VALID_ROOM_FOR_FIND1);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withRoom("A200").build()));

        predicate = new RoomValidCheckPredicate(VALID_ROOM_FOR_FIND2);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withRoom("A100").build()));

        predicate = new RoomValidCheckPredicate(VALID_ROOM_FOR_FIND3);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").withRoom("e200").build()));

        predicate = new RoomValidCheckPredicate(VALID_ROOM_FOR_FIND4);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").withRoom("e200").build()));
    }

    @Test
    public void test_invalidRoomForFind_throwsException() {
        try {
            RoomValidCheckPredicate predicate = new RoomValidCheckPredicate(INVALID_ROOM_FOR_FIND1);
            assertFalse(predicate.test(new PersonBuilder().withName("Alice").withRoom("A100").build()));
        } catch (IllegalArgumentException e) {
            assertEquals(1, 1);
        }

        try {
            RoomValidCheckPredicate predicate = new RoomValidCheckPredicate(INVALID_ROOM_FOR_FIND2);
            assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withRoom("A100").build()));
        } catch (IllegalArgumentException e) {
            assertEquals(1, 1);
        }

        try {
            RoomValidCheckPredicate predicate = new RoomValidCheckPredicate(INVALID_ROOM_FOR_FIND3);
            assertFalse(predicate.test(new PersonBuilder().withName("Alice").withRoom("A100").withPhone("12345")
                    .withEmail("alice@email.com").build()));
        } catch (IllegalArgumentException e) {
            assertEquals(1, 1);
        }
    }
}

