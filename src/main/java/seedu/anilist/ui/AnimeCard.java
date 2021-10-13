package seedu.anilist.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.anilist.model.anime.Anime;

/**
 * An UI component that displays information of a {@code Anime}.
 */
public class AnimeCard extends UiPart<Region> {

    private static final String FXML = "AnimeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Anime anime;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane genres;

    /**
     * Creates a {@code AnimeCard} with the given {@code Anime} and index to display.
     */
    public AnimeCard(Anime anime, int displayedIndex) {
        super(FXML);
        this.anime = anime;
        id.setText(displayedIndex + ". ");
        name.setText(anime.getName().fullName);
        anime.getGenres().stream()
                .sorted(Comparator.comparing(genre -> genre.genreName))
                .forEach(genre -> genres.getChildren().add(new Label(genre.genreName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AnimeCard)) {
            return false;
        }

        // state check
        AnimeCard card = (AnimeCard) other;
        return id.getText().equals(card.id.getText())
                && anime.equals(card.anime);
    }
}
