package nustracker.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import nustracker.commons.core.LogsCenter;

/**
 * A class to access the images stored in the profile-pictures folder.
 */
public class ImageStorage {

    private static final Path pathOfProfilePictureFolder = Paths.get(System.getProperty("user.dir"),
            "profile-pictures");
    private final Logger logger = LogsCenter.getLogger(StorageManager.class);

    /**
     * Instantiates a new ImageStorage instance and creates the profile-pictures folder (if not created previously).
     */
    public ImageStorage() {
        createImageFolder();
    }

    /**
     * Reads an image from the images stored in the profile-pictures folder.
     *
     * @param studentId The name of the student.
     * @return Profile image of the student
     */
    public Image readImage(String studentId) {
        Image studentImage;

        File jpg = studentIdToImageFile(studentId, ".jpg");
        File png = studentIdToImageFile(studentId, ".png");

        //Checks if an image with the student's name exists
        if (jpg.isFile()) {
            studentImage = new Image(jpg.toURI().toString(), 120, 120, true,
                    true, false);
        } else if (png.isFile()) {
            studentImage = new Image(png.toURI().toString(), 120, 120, true,
                    true, false);
        } else {
            studentImage = new Image(Objects.requireNonNull(this.getClass()
                            .getResourceAsStream("/images/default.png")));
        }
        Objects.requireNonNull(studentImage);

        return studentImage;
    }

    /**
     * Takes the studentId, and returns a File that potentially contains a profile picture belonging to a
     * student with that studentId.
     *
     * @param studentId The studentId to be used.
     * @param fileExtension The file extension of the file (.png or .jpg).
     * @return A file that potentially contains the student's image.
     */
    private File studentIdToImageFile(String studentId, String fileExtension) {
        Path pathOfImageFile = Paths.get(pathOfProfilePictureFolder.toString(), studentId + fileExtension);
        return pathOfImageFile.toFile();
    }

    /**
     * Creates a folder called profile-pictures, that stores the profile pictures of the students.
     */
    private void createImageFolder() {
        try {
            if (Files.exists(pathOfProfilePictureFolder)) {
                return;
            }
            Files.createDirectories(pathOfProfilePictureFolder);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "profile-pictures folder could not be created.");
        }
    }
}
