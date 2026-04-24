import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ExperimentLogger {
    private static final String LOG_FILE = "experiment_metrics.csv";
    private static final String HEADER = "timestamp,algorithm,input_size,execution_time_ms,operations,feasibility,details";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private ExperimentLogger() {
        // Utility class
    }

    public static synchronized void log(
            String algorithm,
            int inputSize,
            double executionTimeMs,
            long operations,
            String feasibility,
            String details) {
        Path logPath = Paths.get(LOG_FILE);
        boolean writeHeader = Files.notExists(logPath);

        try (BufferedWriter writer = Files.newBufferedWriter(
                logPath,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            if (writeHeader) {
                writer.write(HEADER);
                writer.newLine();
            }

            String timestamp = LocalDateTime.now().format(FORMATTER);
            String row = String.join(",",
                    escapeCsv(timestamp),
                    escapeCsv(algorithm),
                    Integer.toString(inputSize),
                    String.format("%.3f", executionTimeMs),
                    Long.toString(operations),
                    escapeCsv(feasibility),
                    escapeCsv(details));

            writer.write(row);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Warning: unable to write metrics log - " + e.getMessage());
        }
    }

    private static String escapeCsv(String value) {
        if (value == null) {
            return "\"\"";
        }
        String escaped = value.replace("\"", "\"\"");
        return "\"" + escaped + "\"";
    }
}
