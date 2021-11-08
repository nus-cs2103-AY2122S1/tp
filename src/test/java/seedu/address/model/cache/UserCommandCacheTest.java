package seedu.address.model.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserCommandCacheTest {
    private final UserCommandCache userCommandCache = UserCommandCache.getInstance();
    private final UserCommandCache userCommandCache2 = UserCommandCache.getInstance();

    @Test
    public void initialEqual() {
        assertEquals(userCommandCache2, userCommandCache);
    }

    @Test
    public void getBeforeAndAfter_add1command() {
        userCommandCache.reset();
        userCommandCache.addCommand("1");
        assertEquals(userCommandCache.getBefore(), "1");

        //Test get before oldest command
        assertEquals(userCommandCache.getBefore(), "1");

        //Test get after most recent command
        assertEquals(userCommandCache.getAfter(), "");
    }

    @Test
    public void indexReset() {
        userCommandCache.reset();
        userCommandCache.addCommand("1");
        userCommandCache.addCommand("2");
        userCommandCache.getBefore();
        userCommandCache.getBefore();

        userCommandCache.addCommand("3");
        assertEquals(userCommandCache.getBefore(), "3");
    }

    @Test
    public void getBeforeAndAfter_add25command() {
        userCommandCache.reset();
        for (int i = 1; i < 26; i++) {
            String currCommand = String.valueOf(i);
            userCommandCache.addCommand(currCommand);
        }

        //Test get before 25 times
        for (int j = 25; j > 0; j--) {
            assertEquals(userCommandCache.getBefore(), String.valueOf(j));
        }

        for (int k = 2; k < 26; k++) {
            assertEquals(userCommandCache.getAfter(), String.valueOf(k));
        }
    }

    @Test
    public void getBeforeAndAfter_add50command() {
        userCommandCache.reset();
        for (int i = 1; i < 51; i++) {
            String currCommand = String.valueOf(i);
            userCommandCache.addCommand(currCommand);
        }

        //Test get before 25 times
        for (int j = 50; j > 0; j--) {
            assertEquals(userCommandCache.getBefore(), String.valueOf(j));
        }

        for (int k = 2; k < 51; k++) {
            assertEquals(userCommandCache.getAfter(), String.valueOf(k));
        }
    }

    @Test
    public void trimming() {
        userCommandCache.reset();
        for (int i = 1; i < 51; i++) {
            String currCommand = String.valueOf(i);
            userCommandCache.addCommand(currCommand);
        }

        //Test get before 25 times
        for (int j = 50; j > 24; j--) {
            assertEquals(userCommandCache.getBefore(), String.valueOf(j));
        }

        for (int k = 26; k < 51; k++) {
            assertEquals(userCommandCache.getAfter(), String.valueOf(k));
        }
    }
}
