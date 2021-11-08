package seedu.edrecord.testutil;

import seedu.edrecord.model.assignment.Assignment;

public class TypicalAssignments {

    public static final Assignment IP = new AssignmentBuilder()
            .withName("iP").withWeightage("15").withMaxScore("15").withId(1).build();
    public static final Assignment TP = new AssignmentBuilder()
            .withName("tP").withWeightage("50").withMaxScore("50").withId(2).build();
    public static final Assignment TUTORIAL = new AssignmentBuilder()
            .withName("tutorial").withWeightage("0.25").withMaxScore("10").withId(3).build();
    public static final Assignment LAB = new AssignmentBuilder()
            .withName("lab").withWeightage("2.5").withMaxScore("10").withId(4).build();
    public static final Assignment QUIZ = new AssignmentBuilder()
            .withName("quiz").withWeightage("1.25").withMaxScore("15").withId(5).build();
    public static final Assignment PE = new AssignmentBuilder()
            .withName("practical exam").withWeightage("15").withMaxScore("25").withId(6).build();
    public static final Assignment MIDTERM = new AssignmentBuilder()
            .withName("midterm").withWeightage("15").withMaxScore("55").withId(7).build();
    public static final Assignment FINAL = new AssignmentBuilder()
            .withName("final").withWeightage("40").withMaxScore("100").withId(8).build();


    private TypicalAssignments() {} // prevents instantiation

}
