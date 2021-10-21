package seedu.address.commons.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.image.Image;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Github;

/**
 * Helps in obtaining data from the GitHub API.
 */
public class GitHubUtil {
    public static final Image DEFAULT_USER_PROFILE_PICTURE = new Image(
            GitHubUtil.class.getResourceAsStream("/images/profile.png"));

    private static final Logger logger = LogsCenter.getLogger(GitHubUtil.class);

    private static final String GITHUB_URL_PREFIX = "https://github.com/";
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    /**
     * Establishes a connection to the server, and
     * updates the response code accordingly.
     *
     * @param extension The extension of the url to go to.
     */
    private static String establishConnectionForWebsite(String extension) {
        try {
            String url = GITHUB_URL_PREFIX + extension;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() != 200) {
                logger.severe("Server responded with error code " + httpResponse.statusCode());
                return null;
            }

            return StringUtil.clean(httpResponse.body());
        } catch (MalformedURLException e) {
            logger.severe("Improper URL Format.");
        } catch (IOException | InterruptedException e) {
            logger.severe("A Connection with the server could not be established.");
        }
        return null;
    }

    /**
     * Returns the profile picture of the requested
     * user on GitHub.
     *
     * @param userName The name of the user.
     * @return An {@code Image} object with the users profile picture in it.
     */
    public static Image getProfilePicture(String userName) {
        assert userName != null && !userName.equals("") : "No UserName Found";

        if (!Github.isValidGithub(userName)) {
            throw new RuntimeException("Invalid GitHib Account");
        }

        String htmlString = establishConnectionForWebsite(userName);
        if (htmlString == null) {
            return DEFAULT_USER_PROFILE_PICTURE;
        } else {
            Pattern p = Pattern.compile(
                    "(?<=class=avatar avatar-user)(.*?)(?=\\s*/>)");
            Matcher m = p.matcher(htmlString);
            String target = "";
            while (m.find()) {
                target = m.group();
            }
            target = target.split("src=")[1];
            return new Image(target);
        }
    }


    /**
     * Returns the list of the most recently contributed repositories
     * on a person's Github
     * @param userName The name of the user.
     * @return a list of repository names
     */
    public static ArrayList<String> getRepoNames(String userName) {

        if (!Github.isValidGithub(userName)) {
            throw new RuntimeException("Invalid GitHib Account");
        }

        String htmlString = establishConnectionForWebsite(userName + "?tab=repositories");

        if (htmlString == null) {
            throw new RuntimeException("Data could not be obtained.");
        } else {
            Pattern p = Pattern.compile("(?<=codeRepository)(.*?)(?=</a>)");
            Matcher m = p.matcher(htmlString);
            ArrayList<String> repos = new ArrayList<>();

            while (m.find()) {
                repos.add(StringUtil.clean(m.group(), ">"));
            }

            return repos;
        }
    }

    private static HashMap<String, Double> getRepoLanguages(String userName, String repoName) {
        if (!Github.isValidGithub(userName)) {
            throw new RuntimeException("Invalid GitHib Account");
        }

        String htmlString = establishConnectionForWebsite(userName + "/" + repoName);
        if (htmlString == null) {
            logger.severe("Data could not be obtained.");
            return null;
        } else {
            Pattern p = Pattern.compile("(?<=color-text-primary text-bold mr-1)(.*?)(?=</a>)");
            Matcher m = p.matcher(htmlString);
            HashMap<String, Double> repoLanguages = new HashMap<>();
            while (m.find()) {
                String[] texts = StringUtil.clean(m.group(), ">").split("</span");
                String language = texts[0];
                String value = texts[1].replace(" <span", "").replace("%", "");
                repoLanguages.put(language, Double.parseDouble(value) / 100);
            }
            return repoLanguages;
        }
    }

    private static void normalize(HashMap<String, Double> features) {
        double sum = features.values().stream().parallel().mapToDouble(k -> k).sum();
        features.keySet().stream().parallel().forEach(k -> {
            features.replace(k, features.get(k) / sum);
        });
    }

    private static void updateLanguageStats(HashMap<String, Double> total, HashMap<String, Double> stats) {
        stats.keySet().stream().parallel().forEach(k -> {
            if (total.containsKey(k)) {
                total.replace(k, total.get(k) + stats.get(k));
            } else {
                total.put(k, stats.get(k));
            }
        });
    }

    /**
     * Returns the % of contribution of every language that a person
     * has made on Github to date
     *
     * @param userName The name of the user.
     * @return An {@code HashMap} object mapping Languages to their % found on GitHub
     */
    public static HashMap<String, Double> getLanguageStats(String userName) {
        if (!Github.isValidGithub(userName)) {
            throw new RuntimeException("Invalid GitHib Account");
        }

        ArrayList<String> repoNames = getRepoNames(userName);
        HashMap<String, Double> languageStats = new HashMap<>();
        repoNames.stream().parallel().forEach(repo -> {
            HashMap<String, Double> tempStats = getRepoLanguages(userName, repo);
            if (tempStats != null) {
                updateLanguageStats(languageStats, tempStats);
            }
        });
        normalize(languageStats);
        return languageStats;
    }

    /**
     * Returns the total number of contributions a person on GitHub
     * has made to date.
     *
     * @param userName The name of the user.
     * @return The total contributions found on GitHub else -1, if any error occurred.
     * @throws RuntimeException If the server did not respond well.
     */
    public static int getContributionsCount(String userName) throws RuntimeException {
        if (!Github.isValidGithub(userName)) {
            throw new RuntimeException("Invalid GitHib Account");
        }
        String htmlString = establishConnectionForWebsite(userName);

        if (!Github.isValidGithub(userName)) {
            throw new RuntimeException("Invalid GitHib Account");
        }

        if (htmlString == null) {
            throw new RuntimeException("Data could not be obtained.");
        } else {
            Pattern p = Pattern.compile("(?<=<h2 class=f4 text-normal mb-2>)(.*?)(?=</h2>)");
            Matcher m = p.matcher(htmlString);
            String target = "";

            while (m.find()) {
                target = m.group();
            }

            return Integer.parseInt(target.strip().split("\\s+")[0]);
        }
    }

    /**
     * Returns the total number of repos a person on GitHub
     * has made to date.
     *
     * @param userName The name of the user.
     * @return The total number of repos found on GitHub else -1, if any error occurred.
     * @throws RuntimeException If the server did not respond well.
     */
    public static int getRepoCount(String userName) throws RuntimeException {
        if (!Github.isValidGithub(userName)) {
            throw new RuntimeException("Invalid GitHib Account");
        }
        String htmlString = establishConnectionForWebsite(userName);

        if (!Github.isValidGithub(userName)) {
            throw new RuntimeException("Invalid GitHib Account");
        }

        if (htmlString == null) {
            throw new RuntimeException("Data could not be obtained.");
        } else {
            Pattern p = Pattern.compile("(?<=Repositories)(.*?)(?=</span>)");
            Matcher m = p.matcher(htmlString);
            String target = "";

            while (m.find()) {
                target = m.group();
            }
            if (target.isBlank()) {
                return 0;
            }
            return Integer.parseInt(target.split("class=Counter>")[1]);
        }
    }
}
