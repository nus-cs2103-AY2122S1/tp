package seedu.address;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import javafx.application.Application;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FileUtil;

/**
 * Represents the parsed command-line parameters given to the application.
 */
public class AppParameters {
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);

    private Path configPath;
    private Path accountPath;

    public Path getConfigPath() {
        return configPath;
    }

    public void setConfigPath(Path configPath) {
        this.configPath = configPath;
    }

    public Path getAccountPath() {
        return accountPath;
    }

    public void setAccountPath(Path accountPath) {
        this.accountPath = accountPath;
    }

    /**
     * Parses the application command-line parameters.
     */
    public static AppParameters parse(Application.Parameters parameters) {
        AppParameters appParameters = new AppParameters();
        Map<String, String> namedParameters = parameters.getNamed();

        String configPathParameter = namedParameters.get("config");
        if (configPathParameter != null && !FileUtil.isValidPath(configPathParameter)) {
            logger.warning("Invalid config path " + configPathParameter + ". Using default config path.");
            configPathParameter = null;
        }
        appParameters.setConfigPath(configPathParameter != null ? Paths.get(configPathParameter) : null);

        String accountPathParameter = namedParameters.get("account");
        if (accountPathParameter != null && !FileUtil.isValidPath(accountPathParameter)) {
            logger.warning("Invalid account path " + accountPathParameter + ". Using default account path.");
            accountPathParameter = null;
        }
        appParameters.setAccountPath(accountPathParameter != null ? Paths.get(accountPathParameter) : null);

        return appParameters;
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppParameters)) {
            return false;
        }

        AppParameters otherAppParameters = (AppParameters) other;
        return Objects.equals(getConfigPath(), otherAppParameters.getConfigPath())
                && Objects.equals(getAccountPath(), otherAppParameters.getAccountPath());
    }

    /**
     * Overrides the hashCode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(configPath, accountPath);
    }
}
