package seedu.smartnus.model.question;

import static seedu.smartnus.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.statistic.Statistic;
import seedu.smartnus.model.tag.Tag;

/**
 * Represents a Question in SmartNus.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Question {

    // Integer representation of question types
    public static final int MCQ_QUESTION_TYPE = 0;
    public static final int TF_QUESTION_TYPE = 1;
    public static final int SAQ_QUESTION_TYPE = 2;

    // message on condition for validity of Question
    public static final String MESSAGE_DUPLICATE_CHOICES = "Choices (both incorrect and correct)"
            + " should not have duplicate titles.";

    // Identity fields
    protected final ArrayList<Choice> orderedChoices = new ArrayList<>();
    private final Name name;
    private final Importance importance;

    private final Set<Tag> tags = new HashSet<>();
    private final Set<Choice> choices = new HashSet<>();

    // Question statistic
    private final Statistic statistic;

    /**
     * Every field must be present and not null.
     */
    public Question(Name name, Importance importance, Set<Tag> tags, Set<Choice> choices) {
        requireAllNonNull(name, importance, tags);
        this.name = name;
        this.importance = importance;
        this.tags.addAll(tags);
        this.choices.addAll(choices);
        this.orderedChoices.addAll(choices);
        this.statistic = new Statistic();
        Collections.shuffle(orderedChoices);
    }

    /**
     * Constructor for question with statistic counter.
     * @param name The question
     * @param importance The importance
     * @param tags The tag
     * @param choices The choice of answers
     * @param statistic The statistic
     */
    public Question(Name name, Importance importance, Set<Tag> tags, Set<Choice> choices, Statistic statistic) {
        requireAllNonNull(name, importance, tags, statistic);
        this.name = name;
        this.importance = importance;
        this.tags.addAll(tags);
        this.choices.addAll(choices);
        this.orderedChoices.addAll(choices);
        this.statistic = statistic;
        Collections.shuffle(orderedChoices);
    }

    /**
     * Checks if a {@code Question} is valid. Conditions for validity depend on the type of {@code Question}.
     *
     * @return True if Question is valid, else false.
     */
    public abstract boolean isValidQuestion();

    public Name getName() {
        return name;
    }

    public Importance getImportance() {
        return importance;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable choice set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Choice> getChoices() {
        return Collections.unmodifiableSet(choices);
    }

    /**
     * Returns an immutable wrong choice set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Choice> getWrongChoices() {
        Set<Choice> wrongChoices = new HashSet<>();
        for (Choice choice : choices) {
            if (!choice.getIsCorrect()) {
                wrongChoices.add(choice);
            }
        }
        return Collections.unmodifiableSet(wrongChoices);
    }

    /**
     * Returns a randomised ArrayList of choices.
     */
    public ArrayList<Choice> getOrderedChoices() {
        return orderedChoices;
    }

    /**
     * Shuffles the order of the choices for the question.
     */
    public void shuffleChoices() {
        Collections.shuffle(orderedChoices);
    }

    public Choice getCorrectChoice() {
        for (Choice choice : choices) {
            if (choice.getIsCorrect()) {
                return choice;
            }
        }
        return null;
    }

    public String getCorrectChoiceTitle() {
        return getCorrectChoice() == null ? "" : getCorrectChoice().getTitle();
    }

    /**
     * Returns true if both questions have the same name.
     * This defines a weaker notion of equality between two questions.
     */
    public boolean isSameQuestion(Question otherQuestion) {
        if (otherQuestion == this) {
            return true;
        }

        return otherQuestion != null
                && otherQuestion.getName().equals(getName());
    }

    /**
     * Updates the question statistic and returns True if the correct choice is given.
     * @param choice The choice to be checked
     * @return True if the choice is correct, false otherwise.
     */
    public boolean attemptAndCheckAnswer(Choice choice) {
        statistic.addAttempt();
        if (this.getCorrectChoice().equals(choice)) {
            statistic.addCorrect();
            return true;
        }
        return false;
    }

    /**
     * Returns this question's statistics.
     * @return Statistic for this question.
     */
    public Statistic getStatistic() {
        return statistic;
    }

    /**
     * Find a {@code Choice} by its title
     * @param title A String representation of title of the choice
     * @return The {@code Choice} whose title equals {@code title} or null if that choice does not exist
     */
    public Choice findChoiceByTitle(String title) {
        return orderedChoices.stream().filter(x -> x.getTitle().equals(title)).findFirst().orElse(null);
    }

    /**
     * Returns true if both questions have the same identity and data fields.
     * This defines a stronger notion of equality between two questions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Question)) {
            return false;
        }

        Question otherQuestion = (Question) other;
        return otherQuestion.getName().equals(getName())
                && otherQuestion.getImportance().equals(getImportance())
                && otherQuestion.getTags().equals(getTags())
                && otherQuestion.getChoices().equals(getChoices());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, importance, tags, choices);
    }

    public abstract int getQuestionType();

    /**
     * Returns the conditions that make a Question valid in the form of a user-friendly message.
     *
     * @return The conditions that make a Question valid in the form of a user-friendly message.
     */
    public abstract String getValidConditions();

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        appendImportance(builder);
        appendTags(builder);
        appendChoices(builder);

        return builder.toString();
    }

    private StringBuilder appendImportance(StringBuilder builder) {
        builder.append(getName())
                .append("; Importance: ")
                .append(getImportance());
        return builder;
    }

    private StringBuilder appendTags(StringBuilder builder) {
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder;
    }

    private StringBuilder appendChoices(StringBuilder builder) {
        Set<Choice> choices = getChoices();
        if (!choices.isEmpty()) {
            builder.append("; Choices: ");
            for (Choice choice: choices) {
                builder.append(choice).append(", ");
            }
            builder.setLength(builder.length() - 2); //remove ", " after last choice
        }
        return builder;
    }

    public boolean isCorrectAnswer(Choice userAnswer) {
        return userAnswer.equals(getCorrectChoice());
    }
}
