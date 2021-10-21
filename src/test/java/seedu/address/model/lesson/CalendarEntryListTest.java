package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.MAKEUP_LESSON;
import static seedu.address.testutil.TypicalLessons.RECURRING_LESSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.ClashingLessonException;
import seedu.address.model.person.exceptions.LessonNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class CalendarEntryListTest {

    private final CalendarEntryList calendarEntryList = new CalendarEntryList();

    private final Person ALICE_WITH_LESSON = new PersonBuilder(ALICE).withLessons(RECURRING_LESSON).build();
    private final Person BOB_WITH_LESSON = new PersonBuilder(BOB).withLessons(MAKEUP_LESSON).build();

    @Test
    public void hasClashes_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarEntryList.hasClashes((Lesson) null));
    }

    @Test
    public void hasClashes_nullIterableLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarEntryList.hasClashes((Iterable<Lesson>) null));
    }

    @Test
    public void hasClashes_noLessonInList_returnsFalse() {
        assertFalse(calendarEntryList.hasClashes(RECURRING_LESSON));
    }

    @Test
    public void hasClashes_LessonInList_returnsTrue() {
        calendarEntryList.addLessons(ALICE_WITH_LESSON);
        assertTrue(calendarEntryList.hasClashes(RECURRING_LESSON));
    }

    @Test
    public void hasClashes_lessonWithClashingTimeRangeInList_returnsTrue() {
        calendarEntryList.addLessons(ALICE_WITH_LESSON);
        assertTrue(calendarEntryList.hasClashes(MAKEUP_LESSON));
    }

    @Test
    public void addLessons_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarEntryList.addLessons(null));
    }

    @Test
    public void addLessons_clashingLesson_throwsClashingLessonException() {
        calendarEntryList.addLessons(ALICE_WITH_LESSON);
        assertThrows(ClashingLessonException.class, () -> calendarEntryList.addLessons(BOB_WITH_LESSON));
    }

    @Test
    public void setLessons_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarEntryList.setLessons(null, ALICE));
    }

    @Test
    public void setLessons_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarEntryList.setLessons(ALICE, null));
    }

    @Test
    public void setLessons_targetPersonHasLessonNotInList_throwsLessonNotFoundException() {
        assertThrows(LessonNotFoundException.class,
                () -> calendarEntryList.setLessons(ALICE_WITH_LESSON, ALICE_WITH_LESSON));
    }

    @Test
    public void setLessons_editedPersonHasSameLessons_success() {
        calendarEntryList.addLessons(ALICE_WITH_LESSON);
        calendarEntryList.setLessons(ALICE_WITH_LESSON, ALICE_WITH_LESSON);
        CalendarEntryList expectedCalendarEntryList = new CalendarEntryList();
        expectedCalendarEntryList.addLessons(ALICE_WITH_LESSON);
        assertEquals(expectedCalendarEntryList, calendarEntryList);
    }

    @Test
    public void setPerson_editedPersonHasClashingLesson_success() {
        calendarEntryList.addLessons(ALICE_WITH_LESSON);
        Person editedAlice = new PersonBuilder(ALICE).withLessons(MAKEUP_LESSON).build();
        calendarEntryList.setLessons(ALICE_WITH_LESSON, editedAlice);
        CalendarEntryList expectedCalendarEntryList = new CalendarEntryList();
        expectedCalendarEntryList.addLessons(editedAlice);
        assertEquals(expectedCalendarEntryList, calendarEntryList);
    }

    @Test
    public void setPerson_editedPersonHasLessonsClashWithOthers_throwsClashingLessonException() {
        calendarEntryList.addLessons(ALICE_WITH_LESSON);
        calendarEntryList.addLessons(BOB);
        assertThrows(ClashingLessonException.class, () -> calendarEntryList.setLessons(BOB, BOB_WITH_LESSON));
    }

    @Test
    public void removeLessons_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarEntryList.removeLessons((Person) null));
    }

    @Test
    public void removeLessons_lessonDoesNotExist_throwsLessonNotFoundException() {
        assertThrows(LessonNotFoundException.class, () -> calendarEntryList.removeLessons(ALICE_WITH_LESSON));
    }

    @Test
    public void removeLessons_existingLessons_removesLessons() {
        calendarEntryList.addLessons(ALICE_WITH_LESSON);
        calendarEntryList.removeLessons(ALICE_WITH_LESSON);
        CalendarEntryList expectedCalendarEntryList = new CalendarEntryList();
        assertEquals(expectedCalendarEntryList, calendarEntryList);
    }

    @Test
    public void resetLessons_nullPersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarEntryList.resetLessons((List<Person>) null));
    }

    @Test
    public void resetLessons_personList_replacesEntryListWithProvidedLessons() {
        calendarEntryList.addLessons(ALICE_WITH_LESSON);
        CalendarEntryList expectedCalendarEntryList = new CalendarEntryList();
        expectedCalendarEntryList.addLessons(BOB_WITH_LESSON);
        List<Person> replacementPersonList = new ArrayList<>();
        replacementPersonList.add(BOB_WITH_LESSON);
        calendarEntryList.resetLessons(replacementPersonList);
        assertEquals(calendarEntryList, calendarEntryList);
    }

    @Test
    public void resetLessons_listWithPersonsWithClashingLessons_throwsClashingLessonException() {
        List<Person> listWithPersonsWithClashingLessons = Arrays.asList(ALICE_WITH_LESSON, BOB_WITH_LESSON);
        assertThrows(ClashingLessonException.class,
                () -> calendarEntryList.resetLessons(listWithPersonsWithClashingLessons));
    }

}
