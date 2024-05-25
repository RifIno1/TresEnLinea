package log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

public class LogClass {
    private static final Logger logger = Logger.getLogger(LogClass.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("src\\main\\java\\log\\exceptions.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This message is used to log the messages with the level of error or warning messages : Level.INFO, Level.WARNING, Level.SEVERE 
    public static void log(Level level, String message) {
        logger.log(level, message);
    }

    // This message is used to log the exceptions
    public static void logException(Exception e) {
        logger.log(Level.SEVERE, "An exception occurred", e);
    }
}
