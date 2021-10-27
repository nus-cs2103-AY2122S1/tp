package seedu.address.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class ImageStorage {

    private static final String IMAGE_RESOURCE_PATH = "src/main/resources/images/";

    /**
     * Returns a social platform icon given a platform.
     */
    public static Image getSocialIcon(String platform) {

        String imageFile;
        switch (platform) {
        case "ig":
            imageFile = "instagram.png";
            break;
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
        try {
            FileInputStream inputStream = new FileInputStream(IMAGE_RESOURCE_PATH + "social/" + imageFile);
            return new Image(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Image file not found for social platform: " + platform);
        }
        return null;
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
        default:
            return null;
        }
        try {
            FileInputStream inputStream = new FileInputStream(IMAGE_RESOURCE_PATH + "gender/" + imageFile);
            return new Image(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Image file not found for gender: " + gender);
        }
        return null;
    }

}


