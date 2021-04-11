import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class CalendarReader {
    public static Calendar readJsonCalendar(String filename) throws IOException {
        JavaTimeModule module = new JavaTimeModule();
        LocalTimeDeserializer localDateTimeDeserializer = new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm"));
        module.addDeserializer(LocalTime.class, localDateTimeDeserializer);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.readValue(Paths.get(filename).toFile(), Calendar.class);
    }
}
