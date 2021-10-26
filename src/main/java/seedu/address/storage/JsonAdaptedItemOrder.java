package seedu.address.storage;

import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.item.Item;
import seedu.address.model.item.Name;

public class JsonAdaptedItemOrder {

    private final String name;
    private final Integer id;
    private final Integer count;

    /**
     * Constructor for JsonAdaptedItemOrder that supports json reading/writing
     * @param name name of item.
     * @param id id of item.
     * @param count count of item.
     */
    @JsonCreator
    public JsonAdaptedItemOrder(@JsonProperty("name") String name,
                           @JsonProperty("id") String id,
                           @JsonProperty("count") String count) {
        this.name = name;
        this.id = Integer.parseInt(id);
        this.count = Integer.parseInt(count);
    }

    /**
     * Change an Item to the JsonAdapted version.
     * @param source the Item to be converted.
     */
    public JsonAdaptedItemOrder(Item source) {
        this.name = source.getName().toString();
        this.id = source.getId();
        this.count = source.getCount();
    }

    public Item toModelType() {
        return new Item(new Name(name), id, count, Collections.emptySet(), 1.0, 2.0);
    }
}
