package seedu.anilist.testutil;

import seedu.anilist.model.AnimeList;
import seedu.anilist.model.anime.Anime;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AnimeList animeList;

    public AddressBookBuilder() {
        animeList = new AnimeList();
    }

    public AddressBookBuilder(AnimeList animeList) {
        this.animeList = animeList;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Anime anime) {
        animeList.addAnime(anime);
        return this;
    }

    public AnimeList build() {
        return animeList;
    }
}
