package framework.utils;

import pf.ProjectSettings;
import org.apache.log4j.PropertyConfigurator;

public class Logger {
    private static org.apache.log4j.Logger logger_ = org.apache.log4j.Logger.getLogger("Logger");

    public static void debug(String message) {
        logger_.debug("PrjectName : " + ProjectSettings.getName() + "\r\n" + message);
    }

    public static void info(String message) {
        logger_.info("PrjectName : " + ProjectSettings.getName() + "\r\n" + message);
    }

    public static void warn(String message) {
        logger_.warn("PrjectName : " + ProjectSettings.getName() + "\r\n" + message);
    }

    public static void error(String message) {
        logger_.error("PrjectName : " + ProjectSettings.getName() + "\r\n" + message);
    }

    static {
        PropertyConfigurator.configureAndWatch(Logger.class.getResource("/").getPath().substring(1).replaceAll("%20", " ") + "log4j.properties", 60000);
    }
}
