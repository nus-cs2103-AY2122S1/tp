package socialite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import javafx.application.Application;

public class AppParametersTest {

    private final ParametersStub parametersStub = new ParametersStub();
    private final AppParameters expected = new AppParameters();

    @Test
    public void parse_validConfigPath_success() {
        parametersStub.namedParameters.put("config", "config.json");
        expected.setConfigPath(Paths.get("config.json"));
        assertEquals(expected, AppParameters.parse(parametersStub));
    }

    @Test
    public void parse_nullConfigPath_success() {
        parametersStub.namedParameters.put("config", null);
        assertEquals(expected, AppParameters.parse(parametersStub));
    }

    @Test
    public void parse_invalidConfigPath_success() {
        parametersStub.namedParameters.put("config", "a\0");
        expected.setConfigPath(null);
        assertEquals(expected, AppParameters.parse(parametersStub));
    }

    @Test
    public void testEquals() {

        // Same app parameter -> returns true
        assertEquals(expected, expected);

        // null -> returns false
        assertNotEquals(null, expected);

        // different class -> returns false
        assertNotEquals(expected, Path.of("data/"));

        // Two identical app parameters -> returns true
        assertEquals(new AppParameters(), expected);
    }

    @Test
    public void testHashCode() {
        AppParameters ap1 = new AppParameters();
        ap1.setConfigPath(Path.of("data/"));
        AppParameters ap2 = new AppParameters();
        ap2.setConfigPath(Path.of("data/"));
        AppParameters ap3 = new AppParameters();
        ap3.setConfigPath(Path.of("data/images/"));

        // Same app parameter -> same hash code
        assertEquals(ap1.hashCode(), ap1.hashCode());

        // Identical config path -> same hash code
        assertEquals(ap1.hashCode(), ap2.hashCode());

        // Different config path -> different hash code
        assertNotEquals(ap1.hashCode(), ap3.hashCode());
    }

    private static class ParametersStub extends Application.Parameters {
        private Map<String, String> namedParameters = new HashMap<>();

        @Override
        public List<String> getRaw() {
            throw new AssertionError("should not be called");
        }

        @Override
        public List<String> getUnnamed() {
            throw new AssertionError("should not be called");
        }

        @Override
        public Map<String, String> getNamed() {
            return Collections.unmodifiableMap(namedParameters);
        }
    }
}
