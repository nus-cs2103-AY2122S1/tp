package seedu.smartnus.storage;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.smartnus.commons.exceptions.IllegalValueException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;

/**
 * Jackson-friendly version of {@link Question}.
 */
class JsonAdaptedChoice {

    private final String title;
    private final boolean isCorrect;
    private final Set<String> keywords = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedChoice} with the given choice details.
     */
    @JsonCreator
    public JsonAdaptedChoice(@JsonProperty("title") String title, @JsonProperty("isCorrect") boolean isCorrect,
                             @JsonProperty("keywords") Set<String> keywords) {
        this.title = title;
        this.isCorrect = isCorrect;
        if (keywords != null) {
            this.keywords.addAll(keywords);
        }
    }

    /**
     * Converts a given {@code Choice} into this class for Jackson use.
     */
    public JsonAdaptedChoice(Choice source) {
        title = source.getTitle();
        isCorrect = source.getIsCorrect();
        keywords.addAll(source.getKeywords());
    }

    /**
     * Converts this Jackson-friendly adapted question object into the model's {@code Question} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted question.
     */
    public Choice toModelType() throws IllegalValueException {
        if (!Choice.isValidChoiceTitle(title)) {
            throw new IllegalValueException(Choice.MESSAGE_CONSTRAINTS);
        }
        for (String keyword : keywords) {
            if (!Choice.isValidKeyword(keyword)) {
                throw new IllegalValueException(Choice.MESSAGE_KEYWORD_CONSTRAINTS);
            }
        }
        return new Choice(title, isCorrect, keywords);
    }

}
