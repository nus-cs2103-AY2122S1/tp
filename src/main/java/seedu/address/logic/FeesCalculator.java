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
    private static final float numberOfDaysInAWeek = 7;
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

        for (Person targetPerson : personList) {
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
        Set<Date> copiedCancelledDates = lesson.getCancelledDates();

        OutstandingFees currentOutstanding = lesson.getOutstandingFees();

        // update outstanding fees after calculation
        OutstandingFees updatedOutstandingFees = lesson.hasStarted() && !lesson.hasEnded()
                ? getUpdatedOutstandingFees(currentOutstanding, lesson.getDayOfWeek(),
                        copiedTimeRange, copiedLessonRates, copiedCancelledDates)
                : new OutstandingFees(lesson.getOutstandingFees().value);

        return lesson.isRecurring()
                ? new RecurringLesson(copiedDate, copiedTimeRange, copiedSubject,
                        copiedHomework, copiedLessonRates, updatedOutstandingFees, copiedCancelledDates)
                : new MakeUpLesson(copiedDate, copiedTimeRange, copiedSubject,
                        copiedHomework, copiedLessonRates, updatedOutstandingFees, copiedCancelledDates);
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
                                                     LessonRates lessonRates, Set<Date> cancelledDates) {

        // updated fee values
        int numberOfLessons = getNumOfLessonsSinceLastUpdate(updateDay, timeRange.getEnd(), cancelledDates);
        float costPerLesson = getCostPerLesson(timeRange, lessonRates);
        float updatedFees = costPerLesson * (float) numberOfLessons + original.getMonetaryValueInFloat();

        return new OutstandingFees(Double.toString(updatedFees));
    }

    private float getCostPerLesson(TimeRange timeRange, LessonRates lessonRates) {
        Duration duration = timeRange.getDuration();
        float durationInHour = duration.toMinutes() / numberOfMinutesInAnHour;
        return durationInHour * lessonRates.getMonetaryValueInFloat();
    }

    public int getNumOfLessonsSinceLastUpdate(DayOfWeek updateDay, LocalTime endTime, Set<Date> cancelledDates) {

        int lastUpdatedDay = lastUpdated.getLastUpdatedLocalDate().getDayOfWeek().getValue();
        int currentUpdatedDay = currentDateTime.getDayOfWeek().getValue();

        boolean isUpdatedToday = lastUpdated.getLastUpdatedLocalDate().isEqual(currentDateTime.toLocalDate());
        boolean isUpdatedBeforeLessonOver = lastUpdated.getLastUpdatedLocalTime().isBefore(endTime);

        // if app launched today and lastUpdated is before this lesson ended
        if (isUpdatedToday && isUpdatedBeforeLessonOver) {
            return 1;
        }

        // Number of Days between last updated and current date excluding these both days
        long numOfDays = ChronoUnit.DAYS
                .between(lastUpdated.getLastUpdatedLocalDate()
                        .plus(1, ChronoUnit.DAYS), currentDateTime.toLocalDate());
        int lessonCount = (int) Math.floor((int) numOfDays / numberOfDaysInAWeek);
        // Represents the number of days from nearest update day to current day
        int remainder = (int) (numOfDays % numberOfDaysInAWeek);

        // Number of days since last updated day
        int sinceLastUpdateDay = (int)(currentUpdatedDay - updateDay.getValue());

        if (sinceLastUpdateDay < 0) {
            sinceLastUpdateDay += numberOfDaysInAWeek;
        }

        if (remainder >= sinceLastUpdateDay) { // if number of days from nearest update day is
            lessonCount += 1;
        }

        // if the lesson on the same day as the last updated happens before last update. i.e. not recorded
        if (lastUpdatedDay == updateDay.getValue() && lastUpdated.getLastUpdatedLocalTime().isBefore(endTime)) {
            lessonCount += 1;
        }

        if (currentUpdatedDay == updateDay.getValue() && currentDateTime.toLocalTime().isAfter(endTime)) {
            lessonCount += 1;
        }

        for (Date cancelledDate : cancelledDates) {
            if (cancelledDate.isDateBetween(lastUpdated.getLastUpdatedLocalDate(), currentDateTime.toLocalDate())) {
                lessonCount -= 1;
            }
        }

        return lessonCount;
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
