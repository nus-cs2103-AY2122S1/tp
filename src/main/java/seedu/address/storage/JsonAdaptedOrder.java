package seedu.address.storage;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.item.Item;
import seedu.address.model.order.TransactionRecord;

/**
 * Jackson-friendly version of {@link TransactionRecord}.
 */
class JsonAdaptedOrder {

    private final List<JsonAdaptedItemOrder> itemOrders = new ArrayList<>();
    private final String id;
    private final Instant timeStamp;

    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("itemOrders") List<JsonAdaptedItemOrder> itemOrders,
                            @JsonProperty("id") String id,
                            @JsonProperty("timeStamp") Instant timeStamp) {
        this.itemOrders.addAll(itemOrders);
        this.id = id;
        this.timeStamp = timeStamp;
    }

    public JsonAdaptedOrder(TransactionRecord source) {
        this.id = source.getId();
        this.timeStamp = source.getTimestamp();

        itemOrders.addAll(source.getOrderItems().stream().map(JsonAdaptedItemOrder::new).collect(Collectors.toList()));
    }

    public TransactionRecord toModelType() {
        List<Item> itemList = new ArrayList<>();
        for (JsonAdaptedItemOrder o : itemOrders) {
            Item addition = o.toModelType();
            itemList.add(addition);
        }
        return new TransactionRecord(itemList, id, timeStamp);
    }
}
