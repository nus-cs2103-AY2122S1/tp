package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class LogsCenterTest {

    @Test
    public void getLogger_validName_success() {
        Config defaultConfig = new Config();
        LogsCenter.init(defaultConfig);
        String defaultLoggerName = "DefaultLogger";
        assertNotNull(LogsCenter.getLogger(defaultLoggerName));
    }

    @Test
    public void getLogger_nullName_failure() {
        Config defaultConfig = new Config();
        LogsCenter.init(defaultConfig);
        String defaultLoggerName = null;
        assertThrows(NullPointerException.class, () -> LogsCenter.getLogger(defaultLoggerName));
    }

    @Test
    public void equals_sameLogger() {
        Config defaultConfig = new Config();
        LogsCenter.init(defaultConfig);
        String defaultLoggerName = "DefaultLogger";
        assertEquals(LogsCenter.getLogger(defaultLoggerName), LogsCenter.getLogger(defaultLoggerName));
    }

    @Test
    public void equals_twoDifferentLoggers() {
        Config defaultConfig = new Config();
        LogsCenter.init(defaultConfig);
        String defaultLoggerName = "DefaultLogger";
        String defaultLoggerName2 = "DefaultLogger2";
        assertNotEquals(LogsCenter.getLogger(defaultLoggerName), LogsCenter.getLogger(defaultLoggerName2));
    }

    @Test
    public void hashCode_sameLogger() {
        Config defaultConfig = new Config();
        LogsCenter.init(defaultConfig);
        String defaultLoggerName = "DefaultLogger";
        assertEquals(LogsCenter.getLogger(defaultLoggerName).hashCode(),
                LogsCenter.getLogger(defaultLoggerName).hashCode());
    }

    @Test
    public void hashCode_twoDifferentLoggers() {
        Config defaultConfig = new Config();
        LogsCenter.init(defaultConfig);
        String defaultLoggerName = "DefaultLogger";
        String defaultLoggerName2 = "DefaultLogger2";
        assertNotEquals(LogsCenter.getLogger(defaultLoggerName).hashCode(),
                LogsCenter.getLogger(defaultLoggerName2).hashCode());
    }
}
