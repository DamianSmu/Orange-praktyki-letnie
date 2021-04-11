import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Calendar calendar = CalendarReader.readJsonCalendar("calendar1.json");
    }
}
