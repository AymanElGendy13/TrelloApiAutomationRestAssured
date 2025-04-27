package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.File;

public class Logs {

    public static final String LOGS_PATH = "test-outputs/logs/";

    private Logs() {
        super();
    }

    private static Logger createLoggerForClass(String className) {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();

        String logFileName = LOGS_PATH + className + "_" + System.currentTimeMillis() + ".log";

        PatternLayout layout = PatternLayout.newBuilder()
                .withPattern("[%p] - [%d{dd-MM-yyyy HH:mm:ss}] [%t] [%c] - %msg%n")
                .build();

        FileAppender appender = FileAppender.newBuilder()
                .setName(className + "Appender")
                .withFileName(logFileName)
                .setLayout(layout)
                .build();

        appender.start();
        config.addAppender(appender);

        AppenderRef ref = AppenderRef.createAppenderRef(className + "Appender", null, null);
        AppenderRef[] refs = new AppenderRef[]{ref};

        LoggerConfig loggerConfig = LoggerConfig.createLogger(false, org.apache.logging.log4j.Level.DEBUG,
                className, "true", refs, null, config, null);
        loggerConfig.addAppender(appender, null, null);

        config.addLogger(className, loggerConfig);
        context.updateLoggers();

        return LogManager.getLogger(className);
    }

    public static Logger logger() {
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        return createLoggerForClass(className);
    }

    public static void trace(String... message) {
        logger().trace(String.join(" ", message));
    }

    public static void debug(String... message) {
        logger().debug(String.join(" ", message));
    }

    public static void info(String... message) {
        logger().info(String.join(" ", message));
    }

    public static void warn(String... message) {
        logger().warn(String.join(" ", message));
    }

    public static void error(String... message) {
        logger().error(String.join(" ", message));
    }

    public static void fatal(String... message) {
        logger().fatal(String.join(" ", message));
    }
}
