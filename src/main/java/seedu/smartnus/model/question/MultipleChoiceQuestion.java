package seedu.smartnus.model.question;

import java.util.Set;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.statistic.Statistic;
import seedu.smartnus.model.tag.Tag;

public class MultipleChoiceQuestion extends Question {
    public static final int NUMBER_OF_INCORRECT_CHOICES = 3;
    public static final String MESSAGE_INCORRECT_NUMBER_OF_CHOICES = "Must have only 3 incorrect options!";
    public static final String MESSAGE_DUPLICATE_CHOICES = "Options should not be duplicates!";
    public static final String MESSAGE_VALID_MCQ = "Multiple Choice Questions should have one correct answer and"
            + " three wrong options.";

    public MultipleChoiceQuestion(Name name, Importance importance, Set<Tag> tags,
                                  Set<Choice> choices) {
        super(name, importance, tags, choices);
    }

    public MultipleChoiceQuestion(Name name, Importance importance, Set<Tag> tags,
                                  Set<Choice> choices, Statistic statistic) {
        super(name, importance, tags, choices, statistic);
    }

    /**
     * Returns True if {@code MultipleChoiceQuestion} is valid, false otherwise.A {@code MultipleChoiceQuestion} is
     * valid if it has four choices and exactly one of the choices is correct.
     *
     * @return True if MultipleChoiceQuestion is valid, false otherwise.
     */
    @Override
    public boolean isValidQuestion() {
        int choiceCount = getChoices().size();
        int correctChoiceCount = 0;
        for (Choice choice : getChoices()) {
            if (choice.getIsCorrect()) {
                correctChoiceCount += 1;
            }
        }
        return choiceCount == (NUMBER_OF_INCORRECT_CHOICES + 1)
                && correctChoiceCount == 1
                && this.getStatistic().isValidStatistic();
    }

    @Override
    public int getQuestionType() {
        return MCQ_QUESTION_TYPE;
    }

    @Override
    public String getValidConditions() {
        return MESSAGE_VALID_MCQ;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MultipleChoiceQuestion)) {
            return false;
        }

        return super.equals(other);
    }
}
