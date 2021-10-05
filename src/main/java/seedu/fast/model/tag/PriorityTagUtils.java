package seedu.fast.model.tag;

public class PriorityTagUtils {

    public static final String lowPriorityName = "Low Priority";
    public static final String mediumPriorityName = "Medium Priority";
    public static final String highPriorityName = "High Priority";

    public static final String lowPriorityCommand = "low";
    public static final String mediumPriorityCommand = "medium";
    public static final String highPriorityCommand = "high";

    public static final String PRIORITY_VALIDATION_REGEX = "p/(" + lowPriorityCommand + "|"
                                                                + mediumPriorityCommand + "|"
                                                                + highPriorityCommand + ")";

}
