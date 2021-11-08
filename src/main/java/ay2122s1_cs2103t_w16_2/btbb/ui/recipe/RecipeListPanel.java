package ay2122s1_cs2103t_w16_2.btbb.ui.recipe;


import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiPart;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing a list of recipes
 */
public class RecipeListPanel extends UiPart<Region> {
    private static final String FXML = "RecipeListPanel.fxml";

    @FXML
    private ListView<Recipe> recipeListView;

    /**
     * Creates a RecipeListPanel with the given {@code ObservableList}.
     *
     * @param recipeList The recipe list to display.
     */
    public RecipeListPanel(ObservableList<Recipe> recipeList) {
        super(FXML);
        recipeListView.setItems(recipeList);
        recipeListView.setCellFactory(listView -> new RecipeListPanel.RecipeListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Recipe} using a {@code RecipeCard}.
     */
    class RecipeListViewCell extends ListCell<Recipe> {
        @Override
        protected void updateItem(Recipe recipe, boolean empty) {
            super.updateItem(recipe, empty);

            if (empty || recipe == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RecipeCard(recipe, getIndex() + 1).getRoot());
            }
        }
    }
}
