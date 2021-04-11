import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class MeetingPlannerTests {

    private static final Duration duration = Duration.ofMinutes(30);

    private Calendar createExampleCalendar1(){
        Calendar calendar = new Calendar();
        calendar.setWorkingHours(new TimeInterval(LocalTime.parse("09:00"), LocalTime.parse("19:55")));
        ArrayList<TimeInterval> plannedMeetings = new ArrayList<>();
        plannedMeetings.add(new TimeInterval(LocalTime.parse("09:00"), LocalTime.parse("10:30")));
        plannedMeetings.add(new TimeInterval(LocalTime.parse("12:00"), LocalTime.parse("13:00")));
        plannedMeetings.add(new TimeInterval(LocalTime.parse("16:00"), LocalTime.parse("18:00")));
        calendar.setPlannedMeeting(plannedMeetings);
        return calendar;
    }

    private Calendar createExampleCalendar2(){
        Calendar calendar = new Calendar();
        calendar.setWorkingHours(new TimeInterval(LocalTime.parse("10:00"), LocalTime.parse("18:30")));
        ArrayList<TimeInterval> plannedMeetings = new ArrayList<>();
        plannedMeetings.add(new TimeInterval(LocalTime.parse("10:00"), LocalTime.parse("11:30")));
        plannedMeetings.add(new TimeInterval(LocalTime.parse("12:30"), LocalTime.parse("14:30")));
        plannedMeetings.add(new TimeInterval(LocalTime.parse("14:30"), LocalTime.parse("15:00")));
        plannedMeetings.add(new TimeInterval(LocalTime.parse("16:00"), LocalTime.parse("17:00")));
        calendar.setPlannedMeeting(plannedMeetings);
        return calendar;
    }

    private Calendar createCalendarWithZeroWorkingHours(){
        Calendar calendar = new Calendar();
        calendar.setWorkingHours(new TimeInterval(LocalTime.parse("10:00"), LocalTime.parse("10:00")));
        ArrayList<TimeInterval> plannedMeetings = new ArrayList<>();
        calendar.setPlannedMeeting(plannedMeetings);
        return calendar;
    }

    private Calendar createCalendarFilledWithMeeting(){
        Calendar calendar = new Calendar();
        calendar.setWorkingHours(new TimeInterval(LocalTime.parse("10:00"), LocalTime.parse("18:00")));
        ArrayList<TimeInterval> plannedMeetings = new ArrayList<>();
        plannedMeetings.add(new TimeInterval(LocalTime.parse("10:00"), LocalTime.parse("18:00")));
        calendar.setPlannedMeeting(plannedMeetings);
        return calendar;
    }

    private Calendar createCalendarWithNoMeetings(){
        Calendar calendar = new Calendar();
        calendar.setWorkingHours(new TimeInterval(LocalTime.parse("10:00"), LocalTime.parse("18:00")));
        ArrayList<TimeInterval> plannedMeetings = new ArrayList<>();
        calendar.setPlannedMeeting(plannedMeetings);
        return calendar;
    }

    @Test
    public void shouldReturnEmptyListWhenCannotSetAnyMeeting(){
        Calendar calendar1 = createCalendarWithZeroWorkingHours();
        ArrayList<TimeInterval> results1 = MeetingPlanner.planMeetings(calendar1, calendar1, duration);
        Assertions.assertEquals(0, results1.size());

        Calendar calendar2 = createCalendarFilledWithMeeting();
        ArrayList<TimeInterval> results2 = MeetingPlanner.planMeetings(calendar2, calendar2, duration);
        Assertions.assertEquals(0, results2.size());
    }

    @Test
    public void shouldReturnMeetingsWhenCalendarsAreValid(){
        Calendar calendar1 = createExampleCalendar1();
        Calendar calendar2 = createExampleCalendar2();
        ArrayList<TimeInterval> results = MeetingPlanner.planMeetings(calendar1, calendar2, duration);
        String resultsString = Arrays.toString(results.toArray());
        Assertions.assertEquals("[[\"11:30\", \"12:00\"], [\"15:00\", \"16:00\"], [\"18:00\", \"18:30\"]]", resultsString);
    }

    @Test
    public void shouldReturnOneIntervalWhenCalendarHasNoMeetings(){
        Calendar calendar1 = createCalendarWithNoMeetings();
        ArrayList<TimeInterval> results1 = MeetingPlanner.planMeetings(calendar1, calendar1, duration);
        String resultsString = Arrays.toString(results1.toArray());
        Assertions.assertEquals("[[\"10:00\", \"18:00\"]]", resultsString);
    }
}
