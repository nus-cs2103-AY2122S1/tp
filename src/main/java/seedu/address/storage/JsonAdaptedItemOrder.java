package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;
import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.Set;

public class JsonAdaptedItemOrder {

    private final String name;
    private final Integer id;
    private final Integer count;

    @JsonCreator
    public JsonAdaptedItemOrder(@JsonProperty("name") String name,
                           @JsonProperty("id") String id,
                           @JsonProperty("count") String count) {
        this.name = name;
        this.id = Integer.parseInt(id);
        this.count = Integer.parseInt(count);
    }

    public JsonAdaptedItemOrder(Item source) {
        this.name = source.getName().toString();
        this.id = source.getId();
        this.count = source.getCount();
    }

    public Item toModelType() {
        return new Item(new Name(name), id, count, Collections.emptySet(), 1.0, 2.0);
    }
}
