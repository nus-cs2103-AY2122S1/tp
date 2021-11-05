package seedu.address.commons.util;

import javafx.scene.image.Image;
import seedu.address.MainApp;

/**
 * Retrieves Image file
 */
public class ImageUtil {

    private static final String IMAGE_RESOURCE_PATH = "/images/";

    private static Image getImage(String imagePath) {
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    /**
     * Returns a social platform icon given a platform.
     */
    public static Image getSocialIcon(String platform) {

        String imageFile;
        switch (platform) {
        case "ig":
            imageFile = "instagram.png";
            break;
        case "tl":
            //Fallthrough
        case "tg":
            imageFile = "telegram.png";
            break;
        case "fb":
            imageFile = "facebook.png";
            break;
        case "gh":
            imageFile = "github.png";
            break;
        case "dc":
            imageFile = "discord.png";
            break;
        case "kk":
            imageFile = "kakao.png";
            break;
        case "ln": //temporary
            //Fallthrough
        case "in":
            imageFile = "linkedin.png";
            break;
        case "sc":
            imageFile = "snapchat.png";
            break;
        case "tw":
            imageFile = "twitter.png";
            break;
        case "wc":
            imageFile = "wechat.png";
            break;
        default:
            return null;
        }
        return getImage(IMAGE_RESOURCE_PATH + "social/" + imageFile);
    }

    /**
     * Returns a gender icon given a gender.
     */
    public static Image getGenderIcon(String gender) {

        String imageFile;
        switch (gender) {
        case "M":
            imageFile = "male.png";
            break;
        case "F":
            imageFile = "female.png";
            break;
        case "O":
            imageFile = "other.png";
            break;
        default:
            return null;
        }
        return getImage(IMAGE_RESOURCE_PATH + "gender/" + imageFile);
    }

}
