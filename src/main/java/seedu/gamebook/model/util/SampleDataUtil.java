package seedu.gamebook.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.gamebook.model.GameBook;
import seedu.gamebook.model.ReadOnlyGameBook;
import seedu.gamebook.model.gameentry.GameEntry;
import seedu.gamebook.model.tag.Tag;

/**
 * Contains utility methods for populating {@code GameBook} with sample data.
 */
public class SampleDataUtil {
    public static GameEntry[] getSampleGameEntries() {
        return new GameEntry[] {
            new GameEntry("Poker", "10.96", "0.23", "2021-09-21 11:40", "10",
                    "genting casino", "drunk"),
            new GameEntry("Roulette", "31.01", "1.12", "2021-09-23",
                        "30", "Marina Bay Sands", "drunk"),
            new GameEntry("Poker", "110", "23.64", "2021-09-22 12:40", "12",
                        "sentosa casino", ""),
            new GameEntry("Blackjack", "10.20", "12.94", "2021-10-21 12:40", "10",
                        "genting casino", "hungry, angry, friends"),
            new GameEntry("Baccarat", "21.12", "0.26", "2020-12-30 13:40",
                        "12", "sentosa casino", "smoking, late-night"),
        };
    }

    public static ReadOnlyGameBook getSampleGameBook() {
        GameBook sampleGb = new GameBook();
        for (GameEntry sampleGameEntry : getSampleGameEntries()) {
            sampleGb.addGameEntry(sampleGameEntry);
        }
        return sampleGb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
