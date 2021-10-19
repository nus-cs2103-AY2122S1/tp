package seedu.address.ui.timetable;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.ClassTiming;
import seedu.address.model.person.Student;
import seedu.address.ui.UiPart;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TimetablePanel extends UiPart<Region> {
    private static final String FXML = "timetable/TimetablePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TimetablePanel.class);

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane timetable;

    /**
     * Creates a {@code TimetablePanel} with the given {@code ObservableList}.
     */
    public TimetablePanel(ObservableList<Student> studentList) {
        super(FXML);
        build(studentList);
    }

    public void build(ObservableList<Student> studentList) {
        clearAll();
        if (studentList.isEmpty()) {
            Label label = new Label("You have no classes.");
            timetable.add(label, 0, 0);
        } else {
            buildDays();
            buildClasses(studentList);
        }
    }

    public void buildClasses(ObservableList<Student> studentList) {
        LocalTime earliestTime = getEarliestTime(studentList);
        //earliest time is after the date
        ArrayList<Student> sortedList = new ArrayList<Student>(studentList);
        sortedList.sort((student1, student2) -> student1.getClassTiming().compareTo(student2.getClassTiming()));
        List<ClassTiming> sortedClassTimings = sortedList
                .stream()
                .map(Student::getClassTiming)
                .collect(Collectors.toList());
        ArrayList<ClassTiming> uniqueClassTiming = removeDuplicateClassTimings(sortedClassTimings);

        // build the timetable
        LocalTime previousTime = earliestTime;
        for (int i = 0; i < uniqueClassTiming.size(); i++) {
            ClassTiming currentClassTiming = uniqueClassTiming.get(i);
            if (currentClassTiming.isAfter(previousTime)) {
                addEmptySlot(previousTime, currentClassTiming.getStartTime(),
                        currentClassTiming.getDayToInt() - 1,
                        getColumnIndex(earliestTime, previousTime));
            }

            addTuitionClassSlot(currentClassTiming, earliestTime);

            if (i != uniqueClassTiming.size() - 1 &&
                    currentClassTiming.getDayToInt() < uniqueClassTiming.get(i + 1).getDayToInt()) {
                previousTime = earliestTime;
            } else {
                previousTime = currentClassTiming.getEndTime();
            }
        }
    }

    public void addTuitionClassSlot(ClassTiming classTiming, LocalTime earliestTime) {
        TimetableTuitionClassSlot timetableTuitionClassSlot =
                new TimetableTuitionClassSlot("test", classTiming);
        int duration = getTimeDifference(classTiming.getStartTime(), classTiming.getEndTime());
        int columnIndex = getColumnIndex(earliestTime, classTiming.getStartTime());
        timetable.add(timetableTuitionClassSlot.getRoot(), columnIndex, classTiming.getDayToInt() - 1,
                duration, 1);
    }

    public int getColumnIndex(LocalTime earliestTime, LocalTime timeToIndex) {
        return TimetableDay.getWidth() + getTimeDifference(earliestTime, timeToIndex);
    }

    private void addEmptySlot(LocalTime startTime, LocalTime endTime, int row, int column) {
        TimetableEmptySlot emptySlot = new TimetableEmptySlot(startTime, endTime);
        int duration = getTimeDifference(startTime, endTime);
        timetable.add(emptySlot.getRoot(), column , row, duration, 1);
    }

    private int getTimeDifference(LocalTime startTime, LocalTime endTime) {
        return (int) startTime.until(endTime, ChronoUnit.MINUTES);
    }

    private ArrayList<ClassTiming> removeDuplicateClassTimings(List<ClassTiming> classTimings) {
        ArrayList<ClassTiming> uniqueClassTimings = new ArrayList<>();

        for (int i = 0; i < classTimings.size(); i++) {
            ClassTiming currentClassTiming = classTimings.get(i);
            if (uniqueClassTimings.contains(currentClassTiming)) {
                continue;
            }
            uniqueClassTimings.add(currentClassTiming);
        }

        return uniqueClassTimings;
    }

    private LocalTime getEarliestTime(ObservableList<Student> studentList) {
        assert studentList.size() > 0;
        return studentList.stream()
                .map(student -> student.getClassTiming().getStartTime())
                .reduce((classTime1, classTime2) -> classTime1.isBefore(classTime2) ? classTime1 : classTime2)
                .get();
    }

    public void buildDays() {
//        timetable.add(new timetableDay("Time").getRoot(), 0, 0, 60, 1);
        timetable.add(new TimetableDay("Mon").getRoot(), 0, 0, 50, 1);
        timetable.add(new TimetableDay("Tue").getRoot(), 0, 1, 50, 1);
        timetable.add(new TimetableDay("Wed").getRoot(), 0, 2, 50, 1);
        timetable.add(new TimetableDay("Thu").getRoot(), 0, 3, 50, 1);
        timetable.add(new TimetableDay("Fri").getRoot(), 0, 4, 50, 1);
        timetable.add(new TimetableDay("Sat").getRoot(), 0, 5, 50, 1);
        timetable.add(new TimetableDay("Sun").getRoot(), 0, 6, 50, 1);
    }

    /**
     * Clears the previously constructed timetable.
     */
    private void clearAll() {
        timetable.getChildren().clear();
        timetable.getRowConstraints().clear();
        timetable.getColumnConstraints().clear();
    }
}
