package seedu.address.storage;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.ui.ThemeType;

/**
 * A class wrapping the different {@code ThemeType} that can be switched to.
 */
public class ThemeList {
    public static final ThemeType DEFAULT_THEME = new ThemeType("BookTheme");
    // New Themes Name to be added to this list
    private static final List<String> THEMES = List.of("BookTheme", "ColoredTheme", "RandomTheme", "DarkTheme");
    public static final List<ThemeType> THEME_LIST = THEMES
            .stream()
            .map(ThemeType::new)
            .collect(Collectors.toList());

    private final FilteredList<ThemeType> currentTheme;

    /**
     * Returns a {@code ThemeList} with the current {@code Theme} set to {@code theme}.
     */
    public ThemeList(ThemeType theme) {
        ObservableList<ThemeType> temp = FXCollections.observableArrayList(THEME_LIST);
        currentTheme = new FilteredList<>(temp);
        setTheme(theme);
    }

    public void setTheme(ThemeType theme) {
        currentTheme.setPredicate(x -> x.equals(theme));
    }

    public ObservableList<ThemeType> getThemeList() {
        return this.currentTheme;
    }

    public ThemeType getTheme() {
        return this.currentTheme.get(0);
    }
}
