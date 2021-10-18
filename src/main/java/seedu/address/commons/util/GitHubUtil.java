package seedu.address.commons.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.image.Image;
import seedu.address.commons.core.LogsCenter;

/**
 * Helps in obtaining data from the GitHub API.
 */
public class GitHubUtil {
    private static final Logger logger = LogsCenter.getLogger(GitHubUtil.class);

    private static final String URL_PREFIX = "https://api.github.com/users/";
    private static int responseCode;
    private static URL url;
    private final Image defaultUserProfilePicture = new Image(
            this.getClass().getResourceAsStream("/images/profile.png"));

    /**
     * Initializes a GitHubUtil Object.
     */
    public GitHubUtil() {
    }

    /**
     * Establishes a connection to the server, and
     * updates the response code accordingly.
     *
     * @param userName The name of the user.
     */
    public void establishConnection(String userName) {
        String userUrl = URL_PREFIX + userName;

        try {
            url = new URL(userUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            responseCode = conn.getResponseCode();
        } catch (MalformedURLException e) {
            logger.severe("Improper URL Format.");
        } catch (IOException e) {
            logger.severe("A Connection with the server could not be established.");
        }
    }

    /**
     * Returns a {@code JSONObject} consisting of the user data
     * obtained from the GitHub API.
     *
     * @param userName The name of the user.
     * @return A {@code JSONObject} consisting of the user data.
     * @throws RuntimeException If the server did not respond well.
     */
    public JSONObject getProfile(String userName) throws RuntimeException {
        assert userName != null && !userName.equals("") : "No UserName Found";

        establishConnection(userName);
        JSONObject data = null;

        if (responseCode != 200) {
            logger.severe("Server responded with error code " + responseCode);
            throw new RuntimeException("Data could not be obtained.");
        } else {
            String inline = "";

            try {
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                scanner.close();
            } catch (IOException e) {
                logger.severe("Data could not be obtained from the URL.");
            }

            try {
                JSONParser jsonParser = new JSONParser();
                data = (JSONObject) jsonParser.parse(inline);
            } catch (ParseException e) {
                logger.severe("Data obtained could not be parsed into JSON Format.");
            }
        }
        return data;
    }

    /**
     * Returns the profile picture of the requested
     * user on GitHub.
     *
     * @param userName The name of the user.
     * @return An {@code Image} object with the users profile picture in it.
     */
    public Image getProfilePicture(String userName) {
        assert userName != null && !userName.equals("") : "No UserName Found";
        JSONObject jsonObject = null;

        try {
            jsonObject = getProfile(userName);
        } catch (RuntimeException e) {
            logger.severe("Profile Picture Could not be obtained. Using default.");
            return defaultUserProfilePicture;
        }

        assert jsonObject != null : "No Data Found";

        String userProfileUrl = (String) jsonObject.get("avatar_url");
        Image image = new Image(userProfileUrl);

        return image;
    }

    /**
     * Returns the total number of commits a person on GitHub
     * has made to date.
     *
     * @param userName The name of the user.
     * @return The total number of commits.
     */
    public int getCommits(String userName) {
        return 0;
    }

    /**
     * Returns the total number of repos a person on GitHub
     * has made to date.
     *
     * @param userName The name of the user.
     * @return The total number of repos.
     */
    public int getRepoCount(String userName) {
        assert userName != null && !userName.equals("") : "No UserName Found";
        JSONObject jsonObject = null;

        try {
            jsonObject = getProfile(userName);
        } catch (RuntimeException e) {
            logger.severe("Repo Count Could not be obtained.");
            return 0;
        }

        assert jsonObject != null : "No Data Found";

        int repoCount = (Integer) jsonObject.get("public_repos");

        return repoCount;
    }
}
