package seedu.address.testutil;

import java.util.List;

import seedu.address.model.table.Table;

public class TypicalTables {
    public static final Table DUMMY_TABLE_1 = new Table(1, 1);
    public static final Table DUMMY_TABLE_2 = new Table(2, 2);
    public static final Table DUMMY_TABLE_3 = new Table(3, 3);
    public static final Table DUMMY_TABLE_4 = new Table(4, 4);
    public static final Table DUMMY_TABLE_5 = new Table(5, 5);

    public static final List<Table> DUMMY_LIST_OF_TABLES =
            List.of(DUMMY_TABLE_1, DUMMY_TABLE_2, DUMMY_TABLE_3, DUMMY_TABLE_4, DUMMY_TABLE_5);

    public static final List<Integer> DUMMY_LIST_OF_TABLE_SIZES = List.of(1, 2, 3, 4, 5);
}
