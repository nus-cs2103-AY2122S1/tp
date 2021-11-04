package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.BookKeeping;
import seedu.address.model.ReadOnlyBookKeeping;

@JsonRootName(value = "bookKeeping")
public class JsonSerializableBookKeeping {
    private Double revenue;
    private Double cost;
    private Double profit;

    /**
     * Constructor that supports json.
     *
     * @param revenue current revenue.
     * @param cost current cost.
     * @param profit current profit
     */
    @JsonCreator
    public JsonSerializableBookKeeping(@JsonProperty("revenue") Double revenue,
                                       @JsonProperty("cost") Double cost,
                                       @JsonProperty("profit") Double profit) {
        this.revenue = revenue;
        this.cost = cost;
        this.profit = profit;
    }

    /**
     * Constructor for JsonSerializableBookKeeping.
     *
     * @param bookKeeping current bookKeeping.
     */
    public JsonSerializableBookKeeping(ReadOnlyBookKeeping bookKeeping) {
        this.revenue = bookKeeping.getRevenue();
        this.cost = bookKeeping.getCost();
        this.profit = bookKeeping.getProfit();
    }

    public BookKeeping toModelType() throws IllegalValueException {
        return new BookKeeping(revenue, cost, profit);
    }
}
