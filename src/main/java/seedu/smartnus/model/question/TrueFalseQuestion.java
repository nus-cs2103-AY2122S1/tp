package seedu.smartnus.model.question;

import static seedu.smartnus.model.choice.Choice.FALSE_CHOICE_TITLE;
import static seedu.smartnus.model.choice.Choice.TRUE_CHOICE_TITLE;

import java.util.Collections;
import java.util.Set;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.statistic.Statistic;
import seedu.smartnus.model.tag.Tag;

public class TrueFalseQuestion extends Question {

    public static final String MESSAGE_ANSWER_INVALID = "Only T or F is allowed as an answer!";
    public static final String MESSAGE_OPTIONS_INVALID = "Do not specify incorrect options (/opt) for T/F questions.";
    public static final String MESSAGE_VALID_TFQ = "True/False Questions must have exactly 1 correct choice"
            + "(either True or False) and 1 incorrect choice (the opposite of the answer)";
    private static final int NUMBER_OF_CHOICES = 2;

    public TrueFalseQuestion(Name name, Importance importance, Set<Tag> tags,
                             Set<Choice> choices, Statistic statistic) {
        super(name, importance, tags, choices, statistic);
    }

    public TrueFalseQuestion(Name name, Importance importance, Set<Tag> tags, Set<Choice> choices) {
        super(name, importance, tags, choices);
    }

    /**
     * Returns True if {@code TrueFalseQuestion} is valid, false otherwise.A {@code TrueFalseQuestion} is
     * valid if it has two choices (True and False) and only one of them is correct.
     *
     * @return True if TrueFalseQuestion is valid, false otherwise.
     */
    @Override
    public boolean isValidQuestion() {
        int validChoices = 0;
        int correctChoices = 0;
        for (Choice choice : getChoices()) {
            if (choice.getIsCorrect()) {
                correctChoices += 1;
            }
            if (isValidTrueFalseChoice(choice)) {
                validChoices += 1;
            }
        }
        return validChoices == NUMBER_OF_CHOICES
                && correctChoices == 1
                && this.getStatistic().isValidStatistic();
    }

    public static boolean isValidTrueFalseChoice(Choice choice) {
        return choice.getTitle().equals(TRUE_CHOICE_TITLE) || choice.getTitle().equals(FALSE_CHOICE_TITLE);
    }

    /**
     * For True/False questions, instead of random order, we want True to be first and False to be second.
     **/
    @Override
    public void shuffleChoices() {
        if (orderedChoices.get(0).getTitle().equals("False")) {
            Collections.reverse(orderedChoices);
        }
    }

    @Override
    public int getQuestionType() {
        return TF_QUESTION_TYPE;
    }

    @Override
    public String getValidConditions() {
        return MESSAGE_VALID_TFQ;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TrueFalseQuestion)) {
            return false;
        }

        return super.equals(other);
    }
}
