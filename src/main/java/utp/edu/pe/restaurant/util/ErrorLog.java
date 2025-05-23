package utp.edu.pe.restaurant.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLog {

    public enum Level {INFO, WARN, ERROR};

    public static String log(String msg, Level level) throws IOException {

        String errorLogDir =AppConfig.getErrorLogDir();
        String filename = errorLogDir;

        // FORMATO: FECHA HORA - LEVEL - MENSAJE
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String time = ldt.format(fmt);

        String event_fmt = "%s - %s - %s\r\n";
        String event = String.format(event_fmt, time, level, msg);

        TextUTP.append(event, filename);

        System.out.println(event);

        return event;

    }

}
