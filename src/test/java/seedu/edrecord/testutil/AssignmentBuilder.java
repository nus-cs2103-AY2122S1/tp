package seedu.edrecord.testutil;

import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.assignment.Score;
import seedu.edrecord.model.assignment.Weightage;
import seedu.edrecord.model.name.Name;

/**
 * A utility class to help with building Assignment objects.
 */
public class AssignmentBuilder {

    public static final String DEFAULT_NAME = "Assignment 1";
    public static final String DEFAULT_WEIGHTAGE = "2.5";
    public static final String DEFAULT_MAX_SCORE = "20";
    public static final int DEFAULT_ID = 0;

    private Name name;
    private Weightage weightage;
    private Score score;
    private int id;

    /**
     * Creates an {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder() {
        name = new Name(DEFAULT_NAME);
        weightage = new Weightage(DEFAULT_WEIGHTAGE);
        score = new Score(DEFAULT_MAX_SCORE);
        id = DEFAULT_ID;
    }

    /**
     * Initializes the AssignmentBuilder with the data from {@code toCopy}.
     */
    public AssignmentBuilder(Assignment toCopy) {
        name = toCopy.getName();
        weightage = toCopy.getWeightage();
        score = toCopy.getMaxScore();
        id = toCopy.getId();
    }

    /**
     * Sets the {@code Name} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Weightage} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withWeightage(String weightage) {
        this.weightage = new Weightage(weightage);
        return this;
    }

    /**
     * Sets the {@code MaxScore} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withMaxScore(String maxScore) {
        this.score = new Score(maxScore);
        return this;
    }

    /**
     * Sets the {@code ID} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Builds and returns the Assignment.
     */
    public Assignment build() {
        return new Assignment(name, weightage, score, id);
    }
}
