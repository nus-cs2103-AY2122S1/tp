package seedu.address.storage;

import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.item.Item;
import seedu.address.model.item.Name;

/**
 * Jackson-friendly version of {@link Item} in an order. Unimportant details (e.g. tag and cost) are omitted.
 */
public class JsonAdaptedItemOrder {

    private final String name;
    private final Integer id;
    private final Integer count;
    private final Double sp;

    /**
     * Constructor for JsonAdaptedItemOrder that supports json reading/writing
     * @param name name of item.
     * @param id id of item.
     * @param count count of item.
     */
    @JsonCreator
    public JsonAdaptedItemOrder(@JsonProperty("name") String name,
                            @JsonProperty("id") String id,
                            @JsonProperty("count") String count,
                            @JsonProperty("sp") Double sp) {
        this.name = name;
        this.id = Integer.parseInt(id);
        this.count = Integer.parseInt(count);
        this.sp = sp;
    }

    /**
     * Change an Item to the JsonAdapted version.
     * @param source the Item to be converted.
     */
    public JsonAdaptedItemOrder(Item source) {
        this.name = source.getName().toString();
        this.id = source.getId();
        this.count = source.getCount();
        this.sp = source.getSalesPrice();
    }

    public Item toModelType() {
        return new Item(new Name(name), id, count, Collections.emptySet(), 1.0, sp);
    }
}
