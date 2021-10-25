package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.DateTimeUtil;
import seedu.address.testutil.PersonBuilder;

public class SummaryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Summary(null));
    }

    @Test
    public void setStatistics_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Summary(null));
    }

    @Test
    public void setStatistics_allStatsIncluded_success() {
        Person personWithOverdueVisit =
            new PersonBuilder().withName("Amy")
                .withVisit(DateTimeUtil.getInputStringSevenDaysBeforeNow()).withLastVisit("").build();
        Person personWithVisitNextSevenDays =
            new PersonBuilder().withName("Ben")
                .withVisit(DateTimeUtil.getInputStringSevenDaysFromNow()).withLastVisit("").build();
        Person personWithVisitNextEightDays =
            new PersonBuilder().withName("Charlie")
                .withVisit(DateTimeUtil.getInputStringEightDaysFromNow()).withLastVisit("").build();
        Person personWithVisitNextThirtyDays =
            new PersonBuilder().withName("David")
                .withVisit(DateTimeUtil.getInputStringThirtyDaysFromNow()).withLastVisit("").build();
        Person personWithLastVisitPastSevenDays =
            new PersonBuilder().withName("Eugene")
                .withVisit("").withLastVisit(DateTimeUtil.getInputStringSevenDaysBeforeNow()).build();
        Person personWithLastVisitPastEightDays =
            new PersonBuilder().withName("Farah")
                .withVisit("").withLastVisit(DateTimeUtil.getInputStringEightDaysBeforeNow()).build();
        Person personWithLastVisitPastThirtyDays =
            new PersonBuilder().withName("Genevieve")
                .withVisit("").withLastVisit(DateTimeUtil.getInputStringThirtyDaysBeforeNow()).build();
        AddressBook addressBook =
            new AddressBookBuilder().withPerson(personWithOverdueVisit).withPerson(personWithVisitNextSevenDays)
                .withPerson(personWithVisitNextEightDays).withPerson(personWithVisitNextThirtyDays)
                .withPerson(personWithLastVisitPastSevenDays).withPerson(personWithLastVisitPastEightDays)
                .withPerson(personWithLastVisitPastThirtyDays).build();
        Summary summary = new Summary(addressBook);

        assertEquals(summary.getTotalElderly(), addressBook.getPersonList().size());
        assertEquals(1, summary.getOverdueVisits());
        assertEquals(1, summary.getVisitsLastWeek());
        assertEquals(3, summary.getVisitsLastMonth());
        assertEquals(1, summary.getVisitsNextWeek());
        assertEquals(3, summary.getVisitsNextMonth());
    }

    @Test
    public void setStatistics_statsNotIncluded_success() {
        Person personWithVisitAfterThirtyDays =
            new PersonBuilder().withName("Amy")
                .withVisit(DateTimeUtil.getInputStringOverThirtyDaysFromNow()).withLastVisit("").build();
        Person personWithLastVisitBeforeThirtyDays =
            new PersonBuilder().withName("Ben")
                .withVisit("").withLastVisit(DateTimeUtil.getInputStringOverThirtyDaysBeforeNow()).build();
        AddressBook addressBook =
            new AddressBookBuilder().withPerson(personWithVisitAfterThirtyDays)
                .withPerson(personWithLastVisitBeforeThirtyDays).build();
        Summary summary = new Summary(addressBook);

        assertEquals(summary.getTotalElderly(), addressBook.getPersonList().size());
        assertEquals(0, summary.getOverdueVisits());
        assertEquals(0, summary.getVisitsLastWeek());
        assertEquals(0, summary.getVisitsLastMonth());
        assertEquals(0, summary.getVisitsNextWeek());
        assertEquals(0, summary.getVisitsNextMonth());
    }
}
