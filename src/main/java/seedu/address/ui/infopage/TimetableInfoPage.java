package seedu.address.ui.infopage;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.ui.ResultDisplay;

/**
 * Timetable class which outputs a timetable according to tuition list.
 */
public class TimetableInfoPage extends InfoPage {
    private static final String FXML = "TimetableInfoPage.fxml";
    private static final int DEFAULT_FONT_SIZE = 8;
    private static final String COLOR_ODD = "-fx-background-color: #3e7589; -fx-text-fill:WHITE; -fx-font-size:%1$spt;";
    private static final String COLOR_EVEN = "-fx-background-color: #515658; -fx-text-fill:WHITE;"
            + " -fx-font-size:%1$spt;";
    private static final String PANE_BORDER = "-fx-border-width:0.3px;-fx-border-color:black";
    private static final String PANE_NO_BORDER = "-fx-border-width:0px;";
    private static final String NOT_SHOWN = "The following class details are not shown due to space limit: ";
    private static final Logger logger = LogsCenter.getLogger(TimetableInfoPage.class);
    private static final String[] days = new String[]{"Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday", "Sunday"};
    private static final HashMap<String, Integer> dates = Timeslot.getDays();
    private final ObservableList<TuitionClass> tuitionClasses;
    private final int numCols = 8;
    private int start;
    private int end;
    private int totalRows;
    private ArrayList<LocalTime> timeStart = new ArrayList<>();
    private ArrayList<LocalTime> timeEnd = new ArrayList<>();
    private ArrayList<String> notShown = new ArrayList<>();

    @FXML
    private GridPane timetable;

    @FXML
    private ScrollPane scrollPane;

    /**
     * Displays a timetable on screen to user.
     * @param tuitionClasses tuition classes to be shown.
     * @param resultDisplay the ResultDisplay panel.
     *                      Used if information of certain classes are not shown due to space limit.
     * @throws CommandException if the tuition class list is empty.
     */
    public TimetableInfoPage(ObservableList<TuitionClass> tuitionClasses, ResultDisplay resultDisplay)
            throws CommandException {
        super(FXML);
        scrollPane.setStyle(" -fx-background:dark; -fx-border-color:dark;");
        scrollPane.setPadding(new Insets(0, 0, 0, 20));
        this.tuitionClasses = tuitionClasses;
        setDay();
        if (tuitionClasses.size() == 0) {
            throw new CommandException("No class found.");
        } else {
            parseTime();
            setTableTime(start, end);
            insertSlot();
        }
        if (notShown.size() > 0) {
            String notShownClasses = "";
            for (String s: notShown) {
                notShownClasses += "\n" + s;
            }
            resultDisplay.setFeedbackToUser(NOT_SHOWN + notShownClasses);
        }
    }

    /**
     * Parses timeslot of tuition class list to determine the size of timetable.
     */
    private void parseTime() {
        LocalTime earliest = null;
        LocalTime latest = null;
        for (TuitionClass tc: tuitionClasses) {
            LocalTime[] times = tc.getTimeslot().parseTime();
            LocalTime localStart = times[0];
            LocalTime localEnd = times[1];
            timeStart.add(localStart);
            timeEnd.add(localEnd);
            if (earliest == null) {
                earliest = localStart;
            }
            if (latest == null) {
                latest = localEnd;
            }
            if (localStart.compareTo(earliest) < 0) {
                earliest = localStart;
            }
            if (localEnd.getHour() == 0) {
                latest = LocalTime.parse("00:00");
                if (localEnd.getMinute() > 0) {
                    earliest = LocalTime.parse("00:00");
                }
            }
            if (localEnd.compareTo(latest) > 0 && !(latest.getHour() == 0)) {
                latest = localEnd;
            }
        }
        this.start = Integer.parseInt(earliest.toString().substring(0, 2));
        int end = Integer.parseInt(latest.toString().substring(0, 2));
        this.end = end == 0 ? 24 : end + 1;
        this.totalRows = (this.end - this.start + 1) * 6;
    }

    /**
     * Inserts tuition classes into the timetable.
     */
    private void insertSlot() {
        if (tuitionClasses.size() == 0) {
            return;
        }
        LocalTime startTime;
        if ((this.start + "").length() == 1) {
            startTime = LocalTime.parse("0" + this.start + ":00");
        } else {
            startTime = LocalTime.parse(this.start + ":00");
        }
        for (int i = 0; i < tuitionClasses.size(); i++) {
            insertATuitionClass(tuitionClasses.get(i), startTime, i);
        }
    }

    /**
     * Inserts a tuition class into timetable.
     * @param tuitionClass the tuition class to be inserted.
     * @param startTime the starting time of the timetable.
     * @param i the position of the tuition class in the tuition class list
     */
    private void insertATuitionClass(TuitionClass tuitionClass, LocalTime startTime, int i) {
        String date = tuitionClass.getTimeslot().getDay();
        int colInsert = dates.get(date);
        int rowStartInsert = 6 + Math.round(startTime.until(timeStart.get(i), ChronoUnit.MINUTES) / (float) 10.0);
        int rowFinishInsert;
        String message = tuitionClass.getNameString() + "\n" + tuitionClass.getTimeslot().getTime().substring(4);
        if (timeEnd.get(i).getHour() == 0) {
            rowFinishInsert = totalRows;
            insertAcrossAnotherDay(colInsert + 1,
                    Math.round(timeEnd.get(i).getMinute() / (float) 10.0), message, tuitionClass);
        } else {
            rowFinishInsert = 6 + Math.round(startTime.until(timeEnd.get(i), ChronoUnit.MINUTES) / (float) 10.0);
        }
        int rowSpan = rowFinishInsert - rowStartInsert;
        Label lesson = getLabel(message, getFontSize(rowSpan),
                colInsert, tuitionClass);
        timetable.add(lesson, colInsert, rowStartInsert, 1,
                rowSpan == 0 ? 1 : rowSpan);
        GridPane.setHalignment(lesson, HPos.CENTER);
        GridPane.setFillWidth(lesson, true);
    }

    /**
     * Produces a label of tuition class to be inserted into the timetable.
     * @param message message shown on the label.
     * @param fontSize size of text of label.
     * @param col column of timetable the label should be shown at.
     * @param tuitionClass the tuition class to be shown.
     * @return a label with information of tuition class on it.
     */
    private Label getLabel(String message, int fontSize, int col, TuitionClass tuitionClass) {
        Label lesson;
        if (fontSize == 1) {
            lesson = new Label();
            notShown.add(tuitionClass.getNameString() + " " + tuitionClass.getTimeslot());
        } else if (fontSize == 2) {
            lesson = new Label(tuitionClass.getNameString());
            fontSize = 8;
            notShown.add(tuitionClass.getNameString() + " " + tuitionClass.getTimeslot());
        } else {
            lesson = new Label(message);
        }
        lesson.setStyle(String.format(getColor(col), fontSize));
        lesson.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lesson.setAlignment(Pos.CENTER);
        return lesson;
    }

    private int getFontSize(int span) {
        if (span < 3) {
            return 1;
        }
        if (span <= 4) {
            return 2;
        }
        if (span < 6) {
            return 6;
        }
        if (span < 9) {
            return 7;
        }
        return DEFAULT_FONT_SIZE;
    }

    /**
     * Inserts a label across 00:00.
     * @param col the column the label should be shown at.
     * @param rows number of rows the label should occupy.
     * @param message the message to be shown on the label.
     * @param tuitionClass the tuition class to be shown.
     */
    private void insertAcrossAnotherDay(int col, int rows, String message, TuitionClass tuitionClass) {
        if (rows == 0) {
            return;
        }
        if (col == 9) {
            col = 1;
        }
        int fontSize = getFontSize(rows);
        Label lesson = getLabel(message, fontSize, col, tuitionClass);
        timetable.add(lesson, col, 0 + 6, 1,
                rows);
        GridPane.setHalignment(lesson, HPos.CENTER);
    }

    private String getColor(int start) {
        String color = start % 2 == 0 ? COLOR_EVEN : COLOR_ODD;
        return color;
    }

    private void setDay() {
        for (int j = 0; j < numCols; j++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            timetable.getColumnConstraints().add(colConst);
        }
    }

    /**
     * Sets vertical size of the table.
     * @param start starting time of timetable.
     * @param end end time of timetable.
     */
    private void setTableTime(int start, int end) {
        for (int i = 0; i < totalRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / totalRows);
            timetable.getRowConstraints().add(rowConst);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < totalRows; j++) {
                StackPane pane;
                if (j == 0 && i != 0) {
                    pane = getDayPane(i);
                } else if (j % 6 == 0 && i == 0 && j != 0) {
                    pane = getTimePane(start - 1 + j / 6);
                } else {
                    pane = new StackPane();
                }

                if (j % 6 == 0) {
                    pane.setStyle(PANE_BORDER);
                } else {
                    pane.setStyle(PANE_NO_BORDER);
                }
                int rowSpan = j % 6 == 0 ? 6 : i == 0 ? 6 : 1;
                timetable.add(pane, i, j, 1, rowSpan);
            }
        }
    }

    private StackPane getDayPane(int i) {
        StackPane pane = new StackPane();
        Label label = new Label(days[i - 1]);
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        pane.getChildren().add(label);
        return pane;
    }

    private StackPane getTimePane(int i) {
        StackPane pane = new StackPane();
        Label label = new Label(i + ":00");
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        pane.getChildren().add(label);
        return pane;
    }
}
