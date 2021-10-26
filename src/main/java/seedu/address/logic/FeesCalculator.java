package seedu.address.logic;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
            + "Outstanding Fees. Invalid transcation.";

    private static final float numberOfMinutesInAnHour = 60.00F;
    private static final LocalDateTime currentDateTime = LocalDateTime.now();
    private final LastUpdatedDate lastUpdated;

    public FeesCalculator(LastUpdatedDate lastUpdatedDate) {
        lastUpdated = lastUpdatedDate;
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

        // update outstanding fees after calculation
        OutstandingFees updatedOutstandingFees = lesson.hasStarted() && !lesson.hasEnded()
                ? getUpdatedOutstandingFees(lesson.getEndDateTime(), copiedTimRange, copiedLessonRates)
                : new OutstandingFees(lesson.getOutstandingFees().value);

        return lesson.isRecurring()
                ? new RecurringLesson(copiedDate, copiedTimRange, copiedSubject,
                        copiedHomework, copiedLessonRates, updatedOutstandingFees)
                : new MakeUpLesson(copiedDate, copiedTimRange, copiedSubject,
                        copiedHomework, copiedLessonRates, updatedOutstandingFees);
    }

    /**
     * Updates the Outstanding Fees field to most recent value and modify the lastAdded date.
     *
     * @param updateDateTime Date and Time when lesson ends.
     * @param timeRange Duration per lesson.
     * @param lessonRates Cost per hour for the lesson.
     * @return Updated Outstanding Fees object.
     */
    public OutstandingFees getUpdatedOutstandingFees(LocalDateTime updateDateTime, TimeRange timeRange,
                                                     LessonRates lessonRates) {

        // updated fee values
        int numberOfLessons = numOfLessonsSinceLastUpdate(updateDateTime);
        float costPerLesson = getCostPerLesson(timeRange, lessonRates);
        double updatedFees = costPerLesson * numberOfLessons;

        return new OutstandingFees(Double.toString(updatedFees));
    }

    private float getCostPerLesson(TimeRange timeRange, LessonRates lessonRates) {
        Duration duration = timeRange.getDuration();
        float durationInHour = duration.toMinutes() / numberOfMinutesInAnHour;
        float costPerLesson = durationInHour * lessonRates.getMonetaryValueInFloat();
        return costPerLesson;
    }

    private int numOfLessonsSinceLastUpdate(LocalDateTime updateDateTime) {
        // get the number of weeks passed since lastAdded date
        LocalDateTime startDate = lastUpdated.dateTime;
        int numberOfWeeksBetween = (int) ChronoUnit.WEEKS.between(startDate, currentDateTime);

        // If today is after the update day or if today is the update day, update the fees for this week's lesson.
        if (currentDateTime.isAfter(updateDateTime)) {
            numberOfWeeksBetween += 1;
        }

        // isCancelled deduction logic goes here

        return numberOfWeeksBetween;
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
