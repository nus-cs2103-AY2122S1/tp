package seedu.address.logic;

import seedu.address.model.LastUpdatedDate;
import seedu.address.model.Model;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Homework;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonRates;
import seedu.address.model.lesson.MakeUpLesson;
import seedu.address.model.lesson.OutstandingFees;
import seedu.address.model.lesson.RecurringLesson;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.TimeRange;
import seedu.address.model.person.Person;
import seedu.address.model.util.PersonUtil;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Responsible for the automated updates and calculation of each lesson's fees.
 * Many lessons to 1 FeeCalculator.
 */
public class FeesCalculator {

    private static final float numberOfMinutesInAnHour = 60.00F;
    private static final LocalDate currentDate = LocalDate.now();
    private final LastUpdatedDate lastUpdated;



    public FeesCalculator(LastUpdatedDate lastUpdatedDate) {
        lastUpdated = lastUpdatedDate;
    }

    public Model updateAllLessonOutstandingFees(Model model) {
        // if last updated day is today, no need to update lessons again.
        if (lastUpdated.date.equals(LocalDate.now())) {
            return model;
        }

        List<Person> personList = model.getFilteredPersonList();

        for (int i = 0; i < personList.size(); i++) {
            Person targetPerson = personList.get(i);
            model.setPerson(targetPerson, createEditedPerson(targetPerson));
        }

        model.setLastUpdatedDate();


        return model;
    }

    public Person createEditedPerson(Person personToEdit) {
        assert personToEdit != null;

       List<Lesson> lessonList = personToEdit.getLessons()
               .stream()
               .collect(Collectors.toList());

        for (int i = 0; i < lessonList.size(); i++) {
            lessonList.set(i, updateLessonOutstandingFeesField(lessonList.get(i)));
        }

        return PersonUtil.createdEditedPerson(personToEdit, new TreeSet<>(lessonList));
    }

    /**
     * Automated updates the specific lesson's outstanding fees.
     * @param lesson the specific lesson to be updated.
     * @return Updated lesson with the correct outstanding fees.
     */
    public Lesson updateLessonOutstandingFeesField(Lesson lesson) {

        // make a copy of untouched fields
        Date copiedDate = new Date(lesson.getDate().value);
        TimeRange copiedTimRange = new TimeRange(lesson.getTimeRange().value);
        Subject copiedSubject = new Subject(lesson.getSubject().subject);
        LessonRates copiedLessonRates = new LessonRates(lesson.getLessonRates().value);
        Set<Homework> copiedHomework = lesson.getHomework() == null
                ? null
                : new HashSet<>(lesson.getHomework());

        // update outstanding fees after calculation
        OutstandingFees updatedOutstandingFees = lesson.hasStarted()
                ? getUpdatedOutstandingFees(copiedDate.getUpdateFeesDay(), copiedTimRange, copiedLessonRates)
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
     * @param updateDay Day of Week for lesson fees to be updated.
     * @param timeRange Duration per lesson.
     * @param lessonRates Cost per hour for the lesson.
     * @return Updated Outstanding Fees object.
     */
    public OutstandingFees getUpdatedOutstandingFees(DayOfWeek updateDay, TimeRange timeRange,
                                                     LessonRates lessonRates) {

        // updated fee values
        int numberOfLessons = numOfLessonsSinceLastUpdate(updateDay);
        float costPerLesson = getCostPerLesson(timeRange, lessonRates);
        double updatedFees = costPerLesson * numberOfLessons;

        return new OutstandingFees(Double.toString(updatedFees));
    }

    public static float getCostPerLesson(TimeRange timeRange, LessonRates lessonRates) {
        Duration duration = timeRange.getDuration();
        float durationInHour = duration.toMinutes() / numberOfMinutesInAnHour;
        float costPerLesson = durationInHour * lessonRates.getLessonRatesFloat();
        return costPerLesson;
    }

    public int numOfLessonsSinceLastUpdate(DayOfWeek updateDay) {
        // get the number of weeks past since lastAdded date
        LocalDate startDate = lastUpdated.getLastUpdatedDate().date;
        int numberOfWeeksBetween = (int) ChronoUnit.WEEKS.between(startDate, currentDate);

        int updateDayOfWeek = updateDay.getValue();
        int todayDayOfWeek = currentDate.getDayOfWeek().getValue();

        // If today is after the update day or if today is the update day, update the fees for this week's lesson.
        if (todayDayOfWeek >= updateDayOfWeek) {
            numberOfWeeksBetween += 1;
        }

        // isCancelled deduction logic goes here

        return numberOfWeeksBetween;
    }

    /**
     * For reminding when the fees are due. In cycles of 4 lessons.
     */
    public static float getThreshold(float costPerLesson) {
        float threshold = costPerLesson * 4;
        return threshold;
    }
}
