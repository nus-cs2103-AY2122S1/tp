package seedu.anilist.model.genre;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.HashSet;

/**
 * List of all genres currently supported.
 */
public class GenreList {
    private static final HashSet<String> LIST_OF_GENRES;

    static {
        LIST_OF_GENRES = new HashSet<>();
        LIST_OF_GENRES.add("action");
        LIST_OF_GENRES.add("adventure");
        LIST_OF_GENRES.add("comedy");
        LIST_OF_GENRES.add("drama");
        LIST_OF_GENRES.add("fantasy");
        LIST_OF_GENRES.add("horror");
        LIST_OF_GENRES.add("magic");
        LIST_OF_GENRES.add("mystery");
        LIST_OF_GENRES.add("psychological");
        LIST_OF_GENRES.add("romance");
        LIST_OF_GENRES.add("sci fi");
        LIST_OF_GENRES.add("slice of life");
        LIST_OF_GENRES.add("sports");
        LIST_OF_GENRES.add("supernatural");
    }

    /**
     * Creates a copy of the LIST_OF_GENRES array and return it.
     *
     * @return the copied array
     */
    public static String[] getListOfGenres() {
        return LIST_OF_GENRES.toArray(new String[0]);
    }

    /**
     * Converts the list of commands into a String, with each command separated by a comma and space.
     *
     * @return string of the list of all supported commands
     */
    public static String getListOfGenresAsString() {
        StringBuilder listOfGenresAsString = new StringBuilder();
        String separator = "";
        String[] arrayOfGenre = getListOfGenres();
        Arrays.sort(arrayOfGenre);
        for (String genre: arrayOfGenre) {
            listOfGenresAsString.append(separator);
            listOfGenresAsString.append("    - ").append(genre);
            separator = "\n";
        }

        return listOfGenresAsString.toString();
    }

    /**
     * Checks if the given string is a genre inside the list
     *
     * @return string of the list of all supported commands
     */
    public static boolean contains(String genreString) {
        requireNonNull(genreString);
        return LIST_OF_GENRES.contains(genreString);
    }
}
