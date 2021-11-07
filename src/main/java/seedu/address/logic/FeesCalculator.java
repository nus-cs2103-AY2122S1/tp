package seedu.address.logic;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
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

    /**
     * Updates all lessons in model to the updated outstanding fees.
     *
     * @param model Model to be updated.
     * @return Updated model with correct outstanding fees.
     */
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
        Date copiedEndDate = new Date(lesson.getEndDate().value);
        TimeRange copiedTimeRange = new TimeRange(lesson.getTimeRange().value);
        Subject copiedSubject = new Subject(lesson.getSubject().value);
        LessonRates copiedLessonRates = new LessonRates(lesson.getLessonRates().value);
        Set<Homework> copiedHomework = lesson.getHomework() == null
                ? null
                : new HashSet<>(lesson.getHomework());
        Set<Date> copiedCancelledDates = lesson.getCancelledDates();

        OutstandingFees currentOutstanding = lesson.getOutstandingFees();

        if (lesson.isRecurring()) {
            OutstandingFees updatedOutstandingFees = lesson.hasStarted()
                    ? getUpdatedOutstandingFeesRecurring(currentOutstanding, lesson.getStartDate(), lesson.getEndDate(),
                    lesson.getDayOfWeek(), copiedTimeRange, copiedLessonRates, copiedCancelledDates)
                    : new OutstandingFees(lesson.getOutstandingFees().value);
            return new RecurringLesson(copiedDate, copiedEndDate, copiedTimeRange, copiedSubject,
                    copiedHomework, copiedLessonRates, updatedOutstandingFees, copiedCancelledDates);
        } else {
            OutstandingFees updatedOutstandingFees = getUpdatedOutstandingFeesMakeup(currentOutstanding,
                    copiedDate, copiedTimeRange, copiedLessonRates);
            return new MakeUpLesson(copiedDate, copiedTimeRange, copiedSubject,
                    copiedHomework, copiedLessonRates, updatedOutstandingFees, copiedCancelledDates);
        }
    }

    public OutstandingFees getUpdatedOutstandingFeesMakeup(OutstandingFees original, Date date, TimeRange timeRange,
                                                           LessonRates lessonRates) {
        BigDecimal costPerLesson = getCostPerLesson(timeRange, lessonRates);
        BigDecimal updatedFees = original.getMonetaryValue();

        LocalDateTime makeUpLessonEnd = LocalDateTime.of(date.getLocalDate(), timeRange.getEnd());

        boolean isBetween = makeUpLessonEnd.isAfter(lastUpdated.dateTime) && makeUpLessonEnd.isBefore(currentDateTime);

        if (isBetween) {
            updatedFees = updatedFees.add(costPerLesson);
        }

        return new OutstandingFees(DECIMAL_FORMAT.format(updatedFees));
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
    public OutstandingFees getUpdatedOutstandingFeesRecurring(OutstandingFees original, Date startDate, Date endDate,
                                                              DayOfWeek updateDay,
            TimeRange timeRange, LessonRates lessonRates, Set<Date> cancelledDates) {
        // updated fee values
        int numberOfLessons = getNumOfLessonsSinceLastUpdate(updateDay, startDate.getLocalDate(),
                endDate.getLocalDate(), timeRange.getEnd(), cancelledDates);
        assert numberOfLessons >= 0;
        BigDecimal costPerLesson = getCostPerLesson(timeRange, lessonRates);

        // updatedFees = costPerLesson * numOfLessons + originalAmount
        BigDecimal updatedFees = costPerLesson
                .multiply(BigDecimal.valueOf(numberOfLessons))
                .add(original.getMonetaryValue());

        return new OutstandingFees(DECIMAL_FORMAT.format(updatedFees));
    }

    private BigDecimal getCostPerLesson(TimeRange timeRange, LessonRates lessonRates) {
        Duration duration = timeRange.getDuration();
        BigDecimal durationInHour = BigDecimal.valueOf(duration.toMinutes() / numberOfMinutesInAnHour);
        return durationInHour.multiply(lessonRates.getMonetaryValue());
    }

    /**
     * Calculates the number of lessons that have passed since last updated date and time.
     *
     * @param updateDay The day of the week which the lesson falls on.
     * @param startDate The start date of the lesson.
     * @param endDate The end date of the lesson.
     * @param endTime The end time of the lesson.
     * @param cancelledDates The set of cancelled dates.
     * @return The number of lessons since that have passed since last update.
     */
    public int getNumOfLessonsSinceLastUpdate(DayOfWeek updateDay, LocalDate startDate,
                                               LocalDate endDate, LocalTime endTime, Set<Date> cancelledDates) {
        int lastUpdatedDay = lastUpdated.getLastUpdatedLocalDate().getDayOfWeek().getValue();
        int currentUpdatedDay = currentDateTime.getDayOfWeek().getValue();

        if (lastUpdated.dateTime.isEqual(currentDateTime)) {
            return 0;
        }

        // Number of Days between last updated and current date excluding these both days
        LocalDate laterStart = Collections.max(Arrays.asList(startDate, lastUpdated.getLastUpdatedLocalDate()));
        LocalDate earlierEnd = Collections.min(Arrays.asList(endDate, currentDateTime.toLocalDate()));

        if (laterStart.isAfter(earlierEnd)) {
            return 0;
        }

        LocalDate start = laterStart.with(TemporalAdjusters.previous(updateDay));
        LocalDate end = earlierEnd.with(TemporalAdjusters.next(updateDay));

        // get lessons in between
        int numLessons = (int) ChronoUnit.WEEKS.between(start, end) - 1;
        assert numLessons >= 0;

        for (Date cancelledDate : cancelledDates) {
            if (cancelledDate.isDateBetween(lastUpdated.getLastUpdatedLocalDate(), currentDateTime.toLocalDate())) {
                numLessons -= 1;
            }
        }

        assert numLessons >= 0;

        // all lessons between this range is cancelled
        if (numLessons == 0) {
            return numLessons;
        }

        // Check the case for lesson falls on lastUpdatedDay or currentDay
        boolean endBeforeLastUpdatedOnSameDay = laterStart.equals(lastUpdated.getLastUpdatedLocalDate())
            && lastUpdatedDay == updateDay.getValue()
            && lastUpdated.getLastUpdatedLocalTime().isAfter(endTime);

        boolean endAfterCurrentDateTimeOnSameDay = earlierEnd.equals(currentDateTime.toLocalDate())
            && currentUpdatedDay == updateDay.getValue()
            && currentDateTime.toLocalTime().isBefore(endTime);

        // Only minus once if start and end dates of lesson on the same date
        if (endBeforeLastUpdatedOnSameDay || endAfterCurrentDateTimeOnSameDay) {
            numLessons -= 1;
        }

        return numLessons;
    }

    /**
     * Returns the outstanding fees after payment.
     *
     * @param payment Amount paid.
     * @return Updated OutstandingFees.
     */
    public static String pay(OutstandingFees toPay, Money payment) throws IllegalValueException {
        BigDecimal newOutstandingFees = toPay.getMonetaryValue().subtract(payment.getMonetaryValue());

        if (newOutstandingFees.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalValueException(MESSAGE_PAY_TOO_MUCH);
        }
        String parseValueToString = DECIMAL_FORMAT.format(newOutstandingFees);
        return parseValueToString;
    }
}
