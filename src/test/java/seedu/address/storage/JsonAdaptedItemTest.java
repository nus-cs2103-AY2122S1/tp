package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.BANANA_MUFFIN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Name;

public class JsonAdaptedItemTest {
    private static final String INVALID_NAME = "Chicken!";
    private static final String INVALID_TAG = "#tasty";
    private static final String INVALID_COUNT = "a0";

    private static final String VALID_NAME = BANANA_MUFFIN.getName().toString();
    private static final String VALID_ID = BANANA_MUFFIN.getId().toString();
    private static final String VALID_COUNT = BANANA_MUFFIN.getCount().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BANANA_MUFFIN.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validItemDetails_returnsItem() throws Exception {
        JsonAdaptedItem item = new JsonAdaptedItem(BANANA_MUFFIN);
        assertEquals(BANANA_MUFFIN, item.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedItem item =
                new JsonAdaptedItem(INVALID_NAME, VALID_ID, VALID_COUNT, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(null, VALID_ID, VALID_COUNT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedItem item =
                new JsonAdaptedItem(VALID_NAME, VALID_ID, VALID_COUNT, invalidTags);
        assertThrows(IllegalValueException.class, item::toModelType);
    }

}
