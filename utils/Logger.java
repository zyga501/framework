package framework.utils;

import org.apache.log4j.PropertyConfigurator;

public class Logger {
    private static org.apache.log4j.Logger logger_ = org.apache.log4j.Logger.getLogger("Logger");

    public static void debug(String prefix, String message) {
        logger_.debug(prefix + "\r\n" + message);
    }

    public static void info(String prefix, String message) {
        logger_.info(prefix + "\r\n" + message);
    }

    public static void warn(String prefix, String message) {
        logger_.warn(prefix + "\r\n" + message);
    }

    public static void error(String prefix, String message) {
        logger_.error(prefix + "\r\n" + message);
    }

    static {
        PropertyConfigurator.configureAndWatch(Logger.class.getResource("/").getPath().substring(1).replaceAll("%20", " ") + "log4j.properties", 60000);
    }
}
