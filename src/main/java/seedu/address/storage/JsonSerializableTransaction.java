package seedu.address.storage;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TransactionList;
import seedu.address.model.order.Order;
import seedu.address.model.order.TransactionRecord;

/**
 * Jackson-friendly version of {@link TransactionRecord}.
 */
@JsonRootName(value = "transaction")
class JsonSerializableTransaction {

    private final ArrayList<JsonAdaptedOrder> orders = new ArrayList<>();

    @JsonCreator
    public JsonSerializableTransaction(@JsonProperty("orders") ArrayList<JsonAdaptedOrder> orders) {
        this.orders.addAll(orders);
    }

    public JsonSerializableTransaction(Stream<TransactionRecord> source) {
        orders.addAll(source.map(JsonAdaptedOrder::new).collect(Collectors.toList()));
    }

    public TransactionList toModelType() throws IllegalValueException {
        ArrayList<TransactionRecord> transactionRecordList = new ArrayList<>();
        for (JsonAdaptedOrder o : orders) {
            TransactionRecord addition = o.toModelType();
            transactionRecordList.add(addition);
        }
        return new TransactionList(transactionRecordList);
    }
}
