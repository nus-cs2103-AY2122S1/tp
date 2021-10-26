package seedu.address.logic;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.LastUpdatedDate;
import seedu.address.model.Model;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Homework;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonRates;
import seedu.address.model.lesson.MakeUpLesson;
import seedu.address.model.lesson.Money;
import seedu.address.model.lesson.OutstandingFees;
import seedu.address.model.lesson.RecurringLesson;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.TimeRange;
import seedu.address.model.person.Person;
import seedu.address.model.util.PersonUtil;

/**
 * Responsible for the automated updates and calculation of each lesson's fees.
 * Many lessons to 1 FeeCalculator.
 */
public class FeesCalculator implements Calculator {
    public static final String MESSAGE_PAY_TOO_MUCH = "Payment amount exceeds current "
            + "outstanding fees. Invalid transaction.";

    private static final float numberOfMinutesInAnHour = 60.00F;
    private final LocalDateTime currentDateTime;
    private final LastUpdatedDate lastUpdated;

    /**
     * Constructs a {@code FeesCalculator}
     *
     * @param lastUpdatedDate Last Updated Date. Equivalent to last date and time user launched TAB.
     * @param currentDateTime Current Date and Time.
     */
    public FeesCalculator(LastUpdatedDate lastUpdatedDate, LocalDateTime currentDateTime) {
        lastUpdated = lastUpdatedDate;
        this.currentDateTime = currentDateTime.truncatedTo(ChronoUnit.MINUTES);
    }

    @Override
    public Model updateAllLessonOutstandingFees(Model model) {
        List<Person> personList = model.getFilteredPersonList();

        for (int i = 0; i < personList.size(); i++) {
            Person targetPerson = personList.get(i);
            model.setPerson(targetPerson, createEditedPerson(targetPerson));
        }

        model.setLastUpdatedDate();


        return model;
    }

    private Person createEditedPerson(Person personToEdit) {
        assert personToEdit != null;

        List<Lesson> lessonList = new ArrayList<>(personToEdit.getLessons());

        for (int i = 0; i < lessonList.size(); i++) {
            lessonList.set(i, updateLessonOutstandingFeesField(lessonList.get(i)));
        }

        return PersonUtil.createdEditedPerson(personToEdit, new TreeSet<>(lessonList));
    }

    /**
     * Automatically updates the specific lesson's outstanding fees.
     *
     * @param lesson The specific lesson to be updated.
     * @return Updated lesson with the correct outstanding fees.
     */
    public Lesson updateLessonOutstandingFeesField(Lesson lesson) {

        // make a copy of untouched fields
        Date copiedDate = new Date(lesson.getStartDate().value);
        TimeRange copiedTimeRange = new TimeRange(lesson.getTimeRange().value);
        Subject copiedSubject = new Subject(lesson.getSubject().subject);
        LessonRates copiedLessonRates = new LessonRates(lesson.getLessonRates().value);
        Set<Homework> copiedHomework = lesson.getHomework() == null
                ? null
                : new HashSet<>(lesson.getHomework());

        OutstandingFees currentOutstanding = lesson.getOutstandingFees();

        // update outstanding fees after calculation
        OutstandingFees updatedOutstandingFees = lesson.hasStarted() && !lesson.hasEnded()
                ? getUpdatedOutstandingFees(currentOutstanding, lesson.getDayOfWeek(),
                        copiedTimeRange, copiedLessonRates)
                : new OutstandingFees(lesson.getOutstandingFees().value);

        return lesson.isRecurring()
                ? new RecurringLesson(copiedDate, copiedTimeRange, copiedSubject,
                        copiedHomework, copiedLessonRates, updatedOutstandingFees)
                : new MakeUpLesson(copiedDate, copiedTimeRange, copiedSubject,
                        copiedHomework, copiedLessonRates, updatedOutstandingFees);
    }

    /**
     * Updates the Outstanding Fees field to most recent value and modify the lastAdded date.
     *
     * @param original Outstanding Fees of current amount.
     * @param updateDay Day of the lesson.
     * @param timeRange Duration per lesson.
     * @param lessonRates Cost per hour for the lesson.
     * @return Updated Outstanding Fees object.
     */
    public OutstandingFees getUpdatedOutstandingFees(OutstandingFees original, DayOfWeek updateDay, TimeRange timeRange,
                                                     LessonRates lessonRates) {

        // updated fee values
        int numberOfLessons = getNumOfLessonsSinceLastUpdate(updateDay, timeRange.getStart());
        float costPerLesson = getCostPerLesson(timeRange, lessonRates);
        float updatedFees = costPerLesson * (float) numberOfLessons + original.getMonetaryValueInFloat();

        return new OutstandingFees(Double.toString(updatedFees));
    }

    private float getCostPerLesson(TimeRange timeRange, LessonRates lessonRates) {
        Duration duration = timeRange.getDuration();
        float durationInHour = duration.toMinutes() / numberOfMinutesInAnHour;
        float costPerLesson = durationInHour * lessonRates.getMonetaryValueInFloat();
        return costPerLesson;
    }

    public int getNumOfLessonsSinceLastUpdate(DayOfWeek updateDay, LocalTime endTime) {
        int numOfLessons = 0;

        int lastUpdatedDay = lastUpdated.dateTime.getDayOfWeek().getValue();
        int currentUpdatedDay = currentDateTime.getDayOfWeek().getValue();

        // Get the nearest Monday
        LocalDate nearestNextMondayToLastUpdated =
                lastUpdated.dateTime.toLocalDate().with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        long numOfDaysBeforeMonday = lastUpdated.dateTime
                .toLocalDate()
                .until(nearestNextMondayToLastUpdated, ChronoUnit.DAYS);

        // if lesson is same day of week as last updated, check if last updated is after lesson
        boolean isSameDayLessonBeforeUpdate = updateDay.getValue() != lastUpdatedDay
                || lastUpdated.dateTime.toLocalTime().isBefore(endTime);

        // If lesson does not fall on a Monday itself
        // check if the lastUpdated day is it on the lesson's update day or after the updated day
        // if it is on update day check if the last update was before lesson endTime
        // if all true
        boolean isLessonBetweenLastUpdateAndMonday = numOfDaysBeforeMonday > 0
                && updateDay.getValue() >= lastUpdatedDay
                && isSameDayLessonBeforeUpdate;

        if (isLessonBetweenLastUpdateAndMonday) {
            numOfLessons += 1;
        }

        LocalDate nearestPreviousMondayToCurrent =
                currentDateTime.toLocalDate().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        numOfLessons += ChronoUnit.WEEKS.between(nearestNextMondayToLastUpdated, nearestPreviousMondayToCurrent);

        long numOfDaysAfterMonday = nearestPreviousMondayToCurrent
                .until(currentDateTime.toLocalDate(), ChronoUnit.DAYS);

        boolean isSameDayLessonAfterCurrent = updateDay.getValue() != currentUpdatedDay
                || lastUpdated.dateTime.toLocalTime().isAfter(endTime);

        boolean isLessonBetweenMondayAndToday = numOfDaysAfterMonday > 0
                && updateDay.getValue() <= currentUpdatedDay
                && isSameDayLessonAfterCurrent;

        if (isLessonBetweenMondayAndToday) {
            numOfLessons += 1;
        }

        // isCancelled deduction logic goes here

        return numOfLessons;
    }

    /**
     * Returns the outstanding fees after payment.
     *F
     * @param payment Amount paid.
     * @return Updated OutstandingFees.
     */
    public static String pay(OutstandingFees toPay, Money payment) throws IllegalValueException {
        float newOutstandingFees = toPay.getMonetaryValueInFloat() - payment.getMonetaryValueInFloat();

        if (newOutstandingFees < 0) {
            throw new IllegalValueException(MESSAGE_PAY_TOO_MUCH);
        }
        String parseValueToString = Float.toString(newOutstandingFees);
        return parseValueToString;
    }
}
