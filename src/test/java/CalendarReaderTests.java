import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class CalendarReaderTests {

    @Test
    public void shouldReadCalendarWhenProperFileIsGiven() throws IOException {
        String path = "calendar1.json";
        Calendar calendar = CalendarReader.readJsonCalendar(path);
        Calendar expected = new Calendar();
        expected.setWorkingHours(new TimeInterval(LocalTime.parse("09:00"), LocalTime.parse("19:55")));
        ArrayList<TimeInterval> plannedMeetings = new ArrayList<>();
        plannedMeetings.add(new TimeInterval(LocalTime.parse("09:00"), LocalTime.parse("10:30")));
        plannedMeetings.add(new TimeInterval(LocalTime.parse("12:00"), LocalTime.parse("13:00")));
        plannedMeetings.add(new TimeInterval(LocalTime.parse("16:00"), LocalTime.parse("18:00")));
        expected.setPlannedMeeting(plannedMeetings);
        Assertions.assertEquals(expected, calendar);
    }

    @Test
    public void shouldThrowIOExceptionWhenCannotOpenFile() {
        String path = "ThisFileDoesNotExist";
        Assertions.assertThrows(IOException.class, () -> CalendarReader.readJsonCalendar(path));
    }
}
