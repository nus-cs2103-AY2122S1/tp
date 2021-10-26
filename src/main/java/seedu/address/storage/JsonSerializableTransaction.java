package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.TransactionRecord;

@JsonRootName(value = "transaction")
class JsonSerializableTransaction {

    private final List<JsonAdaptedOrder> orders = new ArrayList<>();

    @JsonCreator
    public JsonSerializableTransaction(@JsonProperty("orders") List<JsonAdaptedOrder> orders) {
        this.orders.addAll(orders);
    }

    public JsonSerializableTransaction(Stream<TransactionRecord> source) {
        orders.addAll(source.map(JsonAdaptedOrder::new).collect(Collectors.toList()));
    }

    public List<TransactionRecord> toModelType() throws IllegalValueException {
        List<TransactionRecord> transactionRecordList = new ArrayList<>();
        for (JsonAdaptedOrder o : orders) {
            TransactionRecord addition = o.toModelType();
            transactionRecordList.add(addition);
        }
        return transactionRecordList;
    }
}
