package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.BookKeeping;

@JsonRootName(value = "bookKeeping")
public class JsonSerializableBookKeeping {
    Double revenue;
    Double cost;
    Double profit;

    @JsonCreator
    public JsonSerializableBookKeeping(@JsonProperty("revenue") Double revenue,
                                       @JsonProperty("cost") Double cost,
                                       @JsonProperty("profit") Double profit) {
        this.revenue = revenue;
        this.cost = cost;
        this.profit = profit;
    }

    public JsonSerializableBookKeeping(BookKeeping bookKeeping) {
        this.revenue = bookKeeping.getRevenue();
        this.cost = bookKeeping.getCost();
        this.profit = bookKeeping.getProfit();
    }

    public BookKeeping toModelType() throws IllegalValueException {
        return new BookKeeping(revenue, cost, profit);
    }
}
