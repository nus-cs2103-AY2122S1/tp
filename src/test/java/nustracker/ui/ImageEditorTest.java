package nustracker.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ImageEditorTest {

    private Circle testCircle;
    private String testColorHexCode;
    private ImageEditor imageEditor;


    //EP: Valid glow color (Green)
    @Test
    public void validColor_greenHexCode_true() {
        assertTrue(ImageEditor.isValidColorHexCode("#00FF00"));
    }

    //EP: Valid glow color (Neon Pink)
    @Test
    public void validColor_pinkHexCode_true() {
        assertTrue(ImageEditor.isValidColorHexCode("#FF10F0"));
    }

    //EP: Invalid glow color (Too long hex code)
    @Test
    public void invalidColor_tooLong_false() {
        assertFalse(ImageEditor.isValidColorHexCode("#FF10F01823109"));
    }

    //EP: Invalid glow color (Empty hex code)
    @Test
    public void invalidColor_emptyHexCode_false() {
        assertFalse(ImageEditor.isValidColorHexCode(""));
    }

    //EP: Valid glow color (Green)
    @Test
    public void correctGlowColor_greenHexCode_greenGlow() {
        testCircle = new Circle();
        testColorHexCode = "#00FF00";

        imageEditor = new ImageEditor(testCircle);

        imageEditor.setUpHolder(testColorHexCode);

        DropShadow dropShadow = (DropShadow) testCircle.getEffect();
        assertEquals(Color.web(testColorHexCode), dropShadow.getColor());
    }

    //EP: Valid glow color (Neon pink)
    @Test
    public void correctGlowColor_pinkHexCode_pinkGlow() {
        testCircle = new Circle();
        testColorHexCode = "#FF10F0";

        imageEditor = new ImageEditor(testCircle);

        imageEditor.setUpHolder(testColorHexCode);

        DropShadow dropShadow = (DropShadow) testCircle.getEffect();
        assertEquals(Color.web(testColorHexCode), dropShadow.getColor());
    }


    //EP: Invalid glow color (Too long hex code)
    @Test
    public void incorrectGlowColor_randomHexCode_whiteGlow() {
        testCircle = new Circle();
        testColorHexCode = "#FF10F01823109";

        imageEditor = new ImageEditor(testCircle);

        imageEditor.setUpHolder(testColorHexCode);

        DropShadow dropShadow = (DropShadow) testCircle.getEffect();
        assertEquals(ImageEditor.DEFAULT_COLOR, dropShadow.getColor());
    }

    //EP: Invalid glow color (Empty hex code)
    @Test
    public void incorrectGlowColor_emptyHexCode_whiteGlow() {
        testCircle = new Circle();
        testColorHexCode = "";

        imageEditor = new ImageEditor(testCircle);

        imageEditor.setUpHolder(testColorHexCode);

        DropShadow dropShadow = (DropShadow) testCircle.getEffect();
        assertEquals(ImageEditor.DEFAULT_COLOR, dropShadow.getColor());
    }

    //EP: Invalid glow color (Null)
    @Test
    public void incorrectGlowColor_null_whiteGlow() {
        testCircle = new Circle();
        testColorHexCode = null;

        imageEditor = new ImageEditor(testCircle);

        imageEditor.setUpHolder(null);

        DropShadow dropShadow = (DropShadow) testCircle.getEffect();
        assertEquals(ImageEditor.DEFAULT_COLOR, dropShadow.getColor());
    }

    //EP: Valid stroke color (Green)
    @Test
    public void correctStrokeColor_green_greenStroke() {
        testCircle = new Circle();
        testColorHexCode = "#00FF00";

        imageEditor = new ImageEditor(testCircle);

        imageEditor.formattingHolder(testColorHexCode);

        assertEquals(Color.web(testColorHexCode), testCircle.getStroke());
    }

    //EP: Valid stroke color (Neon pink)
    @Test
    public void correctStrokeColor_pink_pinkStroke() {
        testCircle = new Circle();
        testColorHexCode = "#FF10F0";

        imageEditor = new ImageEditor(testCircle);

        imageEditor.formattingHolder(testColorHexCode);

        assertEquals(Color.web(testColorHexCode), testCircle.getStroke());
    }

    //EP: Invalid stroke color (Random hex code)
    @Test
    public void incorrectStrokeColor_random_whiteStroke() {
        testCircle = new Circle();
        testColorHexCode = "#FF10F01823109";

        imageEditor = new ImageEditor(testCircle);

        imageEditor.formattingHolder(testColorHexCode);

        assertEquals(ImageEditor.DEFAULT_COLOR, testCircle.getStroke());
    }

    //EP: Invalid stroke color (Null)
    @Test
    public void incorrectStrokeColor_null_whiteStroke() {
        testCircle = new Circle();
        testColorHexCode = null;

        imageEditor = new ImageEditor(testCircle);

        imageEditor.formattingHolder(null);

        assertEquals(ImageEditor.DEFAULT_COLOR, testCircle.getStroke());
    }

    @Test
    public void validImage_testImage_noException() {
        testCircle = new Circle();
        imageEditor = new ImageEditor(testCircle);
        Image testImage = new Image(Objects.requireNonNull(this.getClass()
                .getResourceAsStream("/images/default.png")));

        imageEditor.fillImage(testImage);
    }

}
