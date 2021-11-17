package socialite.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DatesTest {

    @Test
    public void check_equal_dateLists() {
        Dates dateList1 = new Dates();

        Date d1 = new Date("date:2011-01-01");
        Date d2 = new Date("date:2011-01-01");
        dateList1.addDate(d1);
        dateList1.addDate(d2);

        Dates dateList2 = new Dates(dateList1);

        assertTrue(dateList1.equals(dateList2));


    }
}
