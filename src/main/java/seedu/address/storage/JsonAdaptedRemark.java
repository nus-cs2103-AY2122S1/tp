package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.remark.Remark;

/**
 * Jackson-friendly version of {@link Remark}.
 */
class JsonAdaptedRemark {

    private final String remarkDetail;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedRemark(String remarkDetails) {
        this.remarkDetail = remarkDetails;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedRemark(Remark source) {
        remarkDetail = source.remarkDetail;
    }

    @JsonValue
    public String getRemarkDetail() {
        return remarkDetail;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Remark toModelType() {
        return new Remark(remarkDetail);
    }

}
