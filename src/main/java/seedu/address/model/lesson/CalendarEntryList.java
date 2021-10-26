package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;

import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.ClashingLessonException;
import seedu.address.model.person.exceptions.LessonNotFoundException;

/**
 * A list of calendar entries of lessons that enforces no overlapping time ranges between its lessons,
 * and does not allow nulls.
 * A lesson is considered overlapping by comparing using {@code Lesson#isClashing(Lesson)}.
 * As such, adding and updating of entries uses Lesson#isClashing(Lesson) for checking to ensure that the lesson
 * being added or updated does not clash with other lessons in the CalendarEntryList.
 * However, the removal of a lesson uses Person#equals(Object) to ensure that the person with exactly the same fields
 * will be removed.
 *
 * @author Chesterwongz
 * @see Lesson#isClashing(Lesson)
 */
public class CalendarEntryList {
    private final Calendar calendar = new Calendar();
    private final List<Entry<Lesson>> entryList = new ArrayList<>();

    public Calendar getCalendar() {
        return calendar;
    }

    private void add(Entry<Lesson> calendarEntry) {
        calendar.addEntry(calendarEntry);
        entryList.add(calendarEntry);
    }

    private void remove(Entry<Lesson> calendarEntry) {
        calendar.removeEntry(calendarEntry);
        entryList.remove(calendarEntry);
    }

    private void clear() {
        calendar.clear();
        entryList.clear();
    }

    /**
     * Retrieves the {@code Entry} that contains the {@code Lesson} we want to remove.
     * The Entry must exist within the {@code entryList}.
     *
     * @param toFind The Lesson whose Entry we want to find
     * @return The Entry that has the Lesson.
     * @throws LessonNotFoundException If the Entry does not exist.
     */
    private List<Entry<Lesson>> getEntries(Lesson toFind) {
        List<Entry<Lesson>> entries = entryList.stream().filter(entry -> entry.getUserObject().equals(toFind))
                .collect(Collectors.toList());
        if (entries.isEmpty()) {
            throw new LessonNotFoundException();
        }
        return entries;
    }

    /**
     * Returns true if the specified lesson clashes with existing lessons.
     *
     * @param toCheck The lesson to check.
     * @return True if there is a clash in lesson timing, false otherwise.
     */
    public boolean hasClashes(Lesson toCheck) {
        requireNonNull(toCheck);
        return entryList.stream().anyMatch(entry-> entry.getUserObject().isClashing(toCheck));
    }

    /**
     * Returns true if the specified lesson clashes with existing lessons.
     *
     * @param toCheck The lesson to check.
     * @param toIgnore The lesson to ignore.
     * @return True if there is a clash in lesson timing, false otherwise.
     */
    public boolean hasClashes(Lesson toCheck, Lesson toIgnore) {
        requireNonNull(toCheck);
        requireNonNull(toIgnore);
        return entryList.stream().anyMatch(entry-> entry.getUserObject()
                .equals(toIgnore) ? false : entry.getUserObject().isClashing(toCheck));
    }

    /**
     * Returns true if the entryList contains a Lesson that clashes with the specified Lessons.
     *
     * @param toCheck The lesson to check.
     * @return True if there is a clash in lesson timing, false otherwise.
     */
    public boolean hasClashes(Iterable<Lesson> toCheck) {
        requireAllNonNull(toCheck);
        for (Lesson lesson : toCheck) {
            if (hasClashes(lesson)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a lesson to the list.
     * The lesson must not clash with any in the list.
     *
     * @param editedPerson the person we added the lesson to.
     * @param toAdd The lesson to add
     */
    private void addLesson(Person editedPerson, Lesson toAdd) {
        requireAllNonNull(editedPerson, toAdd);
        if (hasClashes(toAdd)) {
            throw new ClashingLessonException();
        }
        if (!toAdd.isRecurring()) {
            // makeup lesson is not cancelled
            if (!toAdd.isCancelled()) {
                Entry<Lesson> entry = convertToEntry(editedPerson, toAdd);
                add(entry);
            }
            return;
        }

        List<Entry<Lesson>> entriesToAdd = convertRecurringLessonToEntries(editedPerson, toAdd);
        for (Entry<Lesson> entry : entriesToAdd) {
            add(entry);
        }
    }

    /**
     * Removes the equivalent Lesson from the Calendar.
     * The lesson must exist in the calendar.
     *
     * @param toRemove The lesson to remove.
     */
    private void removeLesson(Lesson toRemove) {
        requireNonNull(toRemove);
        if (toRemove.isCancelled()) {
            // fully cancelled lessons have no associated calendar entries
            return;
        }

        List<Entry<Lesson>> entriesToRemove = getEntries(toRemove);
        for (Entry<Lesson> entry : entriesToRemove) {
            remove(entry);
        }
    }

    /**
     * Adds all lessons of the specified person into the calendar.
     *
     * @param person The person whose lesson we want to add.
     */
    public void addLessons(Person person) {
        requireAllNonNull(person);
        calendar.startBatchUpdates();
        for (Lesson lesson : person.getLessons()) {
            addLesson(person, lesson);
        }
        calendar.stopBatchUpdates();
    }

    /**
     * Removes all specified lessons from the calendar.
     *
     * @param person The person whose lessons are to be removed.
     */
    public void removeLessons(Person person) {
        requireAllNonNull(person);
        calendar.startBatchUpdates();
        for (Lesson lesson : person.getLessons()) {
            removeLesson(lesson);
        }
        calendar.stopBatchUpdates();
    }

    /**
     * Sets the {@code target} person's lessons to the {@code editedPerson} lessons.
     *
     * @param target The person whose lessons we want to remove.
     * @param editedPerson THe person whose lessons we want to add.
     */
    public void setLessons(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        removeLessons(target);
        addLessons(editedPerson);
    }

    /**
     * Replaces the entries of the calendar with {@code persons}.
     * {@code persons} must not contain clashing lessons.
     *
     * @param persons The list of persons we will use to build the {@code CalendarEntryList}.
     */
    public void resetLessons(List<Person> persons) {
        requireAllNonNull(persons);
        clear();
        for (Person person : persons) {
            addLessons(person);
        }
    }

    /**
     * Converts a lesson to a {@code List<Entry<Lesson>>} split by cancelled dates.
     *
     * @param owner  The person with the lesson.
     * @param lesson The
     * @return A list of entries representing the lesson.
     */
    public List<Entry<Lesson>> convertRecurringLessonToEntries(Person owner, Lesson lesson) {
        List<Entry<Lesson>> entryList = new ArrayList<>();
        List<Date> cancelledDates = lesson.getCancelledDates().stream().sorted().collect(Collectors.toList());

        Interval interval = new Interval(lesson.getStartDateTime(), lesson.getEndDateTime());
        LocalDate startDate = lesson.getLocalDate(); // Start date of recurring entry

        // Split lesson by cancelled dates
        while (cancelledDates.size() > 0) {
            Date cancelledDate = cancelledDates.remove(0);
            if (cancelledDate.getLocalDate().isAfter(startDate)) {
                // end date of this recurring entry is 1 week before cancelledDate
                LocalDate endDate = cancelledDate.getLocalDate().minusWeeks(1);
                Entry<Lesson> entry = convertToEntry(owner, lesson, interval, Optional.of(endDate));
                entryList.add(entry);
            }
            // update start date of next recurring entry to be 1 week after cancelledDate
            startDate = cancelledDate.getLocalDate().plusWeeks(1);
            interval = interval.withDates(startDate, startDate);

        }
        entryList.add(convertToEntry(owner, lesson, interval, Optional.empty()));

        return entryList;
    }

    /**
     * Converts a {@code Lesson} to a calendar {@code Entry} for CalendarFX.
     *
     * @param owner  The person with the lesson.
     * @param lesson The lesson to be converted to a calendar entry.
     * @return The calendar entry that also contains this lesson.
     */
    private Entry<Lesson> convertToEntry(Person owner, Lesson lesson) {
        return convertToEntry(owner, lesson, new Interval(lesson.getStartDateTime(), lesson.getEndDateTime()),
                Optional.empty());
    }

    /**
     * Converts a {@code Lesson} to a calendar {@code Entry} for CalendarFX.
     * Adapted from CalendarFX API example: https://dlsc.com/wp-content/html/calendarfx/apidocs/index.html
     *
     * @param owner         The person with the lesson.
     * @param lesson        The lesson to be converted to a calendar entry.
     * @param entryInterval The interval of this entry.
     * @param untilDate     An optional end date of the entry if it is recurring.
     * @return Then calendar entry of the lesson.
     */
    private Entry<Lesson> convertToEntry(Person owner, Lesson lesson, Interval entryInterval,
                                         Optional<LocalDate> untilDate) {
        requireNonNull(lesson);
        Entry<Lesson> entry = new Entry<>();
        entry.setUserObject(lesson);
        entry.setInterval(entryInterval);
        StringBuilder entryTitle = new StringBuilder(owner.getName().toString());
        entryTitle.append(" (").append(lesson.getSubject().toString()).append(")");

        // Non recurring
        if (!lesson.isRecurring()) {
            entry.setTitle(entryTitle.toString());
            return entry;
        }

        // Recurring
        entryTitle.append("(Recurring)");
        entry.setTitle(entryTitle.toString());

        String recurrenceRule = "RRULE:FREQ=WEEKLY";
        if (untilDate.isPresent()) {
            recurrenceRule += ";UNTIL=" + untilDate.get().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
        entry.setRecurrenceRule(recurrenceRule);
        return entry;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof CalendarEntryList)) {
            return false;
        }

        // state check
        CalendarEntryList other = (CalendarEntryList) obj;

        if (entryList.size() != other.entryList.size()) {
            return false;
        }

        for (int i = 0; i < entryList.size(); i++) {
            boolean equalPair = entryList.get(i).getUserObject().equals(other.entryList.get(i).getUserObject());
            if (!equalPair) {
                return false;
            }
        }
        return true;
        // Note that we don't use ArrayList#equals(Object) as we want to check if lessons are equal, not entries.
        // CalendarFX Entry#equals(Object) method does not check equality of the user object, and only checks Entry.id,
        // which we do not set, so we should not use it.
    }

    @Override
    public int hashCode() {
        return calendar.hashCode();
    }
}
