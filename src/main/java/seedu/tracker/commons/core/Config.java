package seedu.tracker.commons.core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

/**
 * Config values used by the app
 */
public class Config {

    public static final Path DEFAULT_CONFIG_FILE = Paths.get("config.json");

    // Config values customizable through config file
    private Level logLevel = Level.INFO;
    private Path userPrefsFilePath = Paths.get("preferences.json");
    private Path userInfoFilePath = Paths.get("userInformation.json");

    public Level getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    public Path getUserPrefsFilePath() {
        return userPrefsFilePath;
    }

    public void setUserPrefsFilePath(Path userPrefsFilePath) {
        this.userPrefsFilePath = userPrefsFilePath;
    }

    public Path getUserInfoFilePath() {
        return userInfoFilePath;
    }

    public void setUserInfoFilePath(Path userInfoFilePath) {
        this.userPrefsFilePath = userInfoFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Config)) { //this handles null as well.
            return false;
        }

        Config o = (Config) other;

        return Objects.equals(logLevel, o.logLevel)
                && Objects.equals(userPrefsFilePath, o.userPrefsFilePath)
                && Objects.equals(userInfoFilePath, o.userInfoFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logLevel, userPrefsFilePath, userInfoFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current log level : " + logLevel);
        sb.append("\nPreference file Location : " + userPrefsFilePath);
        sb.append("\nUser Information file Location : " + userInfoFilePath);
        return sb.toString();
    }

}
