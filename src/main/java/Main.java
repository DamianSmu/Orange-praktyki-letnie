import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

public class Main {

    private static final Duration duration = Duration.ofMinutes(30);

    public static void main(String[] args) throws IOException {

        String calendarPath1 = args.length > 0 ? args[0] : "calendar1.json";
        String calendarPath2 = args.length > 1 ? args[1] : "calendar2.json";


        Calendar calendar1 = CalendarReader.readJsonCalendar(calendarPath1);
        Calendar calendar2 = CalendarReader.readJsonCalendar(calendarPath2);


        System.out.println(Arrays.toString(MeetingPlanner.planMeetings(calendar1, calendar2, duration).toArray()));
    }
}
