package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.GamesList;
import seedu.address.model.game.Game;

/**
 * A utility class containing a list of {@code Game} objects to be used in tests.
 */
public class TypicalGames {

    public static final String VALID_GAME_ID_CSGO = "CSGO";
    public static final String VALID_GAME_ID_APEX_LEGENDS = "ApexLegends";

    public static final Game MINECRAFT = new GameBuilder().withGameId("Minecraft").build();
    public static final Game LEAGUE_OF_LEGENDS = new GameBuilder().withGameId("LeagueOfLegends").build();
    public static final Game FORTNITE = new GameBuilder().withGameId("Fortnite").build();
    public static final Game GENSHIN_IMPACT = new GameBuilder().withGameId("GenshinImpact").build();
    public static final Game GTA_V = new GameBuilder().withGameId("GTAV").build();
    public static final Game ANIMAL_CROSSING = new GameBuilder().withGameId("AnimalCrossing").build();
    public static final Game VALORANT = new GameBuilder().withGameId("Valorant").build();

    // Manually added
    public static final Game FIFA22 = new GameBuilder().withGameId("Fifa22").build();
    public static final Game ROCKET_LEAGUE = new GameBuilder().withGameId("RocketLeague").build();

    // Manually added - Game's details found in {@code CommandTestUtil}
    public static final Game CSGO = new GameBuilder().withGameId(VALID_GAME_ID_CSGO).build();
    public static final Game APEX_LEGENDS = new GameBuilder().withGameId(VALID_GAME_ID_APEX_LEGENDS).build();

    public static final String KEYWORD_MATCHING_MEIER = "Legends"; // A keyword that matches MEIER

    private TypicalGames() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static GamesList getTypicalGamesList() {
        GamesList ab = new GamesList();
        for (Game game : getTypicalGames()) {
            ab.addGame(game);
        }
        return ab;
    }

    public static List<Game> getTypicalGames() {
        return new ArrayList<>(Arrays.asList(MINECRAFT, LEAGUE_OF_LEGENDS, FORTNITE, GENSHIN_IMPACT, GTA_V,
                ANIMAL_CROSSING, VALORANT));
    }
}
