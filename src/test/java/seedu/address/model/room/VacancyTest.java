package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class VacancyTest {

    public static final Vacancy VACANT_FACTORY = Vacancy.of(true);
    public static final Vacancy OCCUPIED_FACTORY = Vacancy.of(false);
    public static final Vacancy VACANT_CONSTRUCTOR = Vacancy.VACANT;
    public static final Vacancy OCCUPIED_CONSTRUCTOR = Vacancy.OCCUPIED;

    @Test
    public void isValidVacancy() {
        assertEquals(VACANT_FACTORY, VACANT_CONSTRUCTOR);
        assertEquals(OCCUPIED_FACTORY, OCCUPIED_CONSTRUCTOR);

        assertNotEquals(VACANT_FACTORY, OCCUPIED_FACTORY);
        assertNotEquals(VACANT_CONSTRUCTOR, OCCUPIED_CONSTRUCTOR);
        assertNotEquals(VACANT_FACTORY, OCCUPIED_CONSTRUCTOR);
        assertNotEquals(OCCUPIED_FACTORY, VACANT_CONSTRUCTOR);
    }
}
