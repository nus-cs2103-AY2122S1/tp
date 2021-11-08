package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.skill.Language;

/**
 * Jackson-friendly version of {@link Language}.
 */
class JsonAdaptedLanguage {

    private final String languageName;

    /**
     * Constructs a {@code JsonAdaptedLanguage} with the given {@code languageName}.
     */
    @JsonCreator
    public JsonAdaptedLanguage(String languageName) {
        this.languageName = languageName;
    }

    /**
     * Converts a given {@code Language} into this class for Jackson use.
     */
    public JsonAdaptedLanguage(Language source) {
        languageName = source.languageName;
    }

    @JsonValue
    public String getLanguageName() {
        return languageName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Language} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted language.
     */
    public Language toModelType() throws IllegalValueException {
        if (!Language.isValidLanguageName(languageName)) {
            throw new IllegalValueException(Language.MESSAGE_CONSTRAINTS);
        }
        return new Language(languageName);
    }

}
