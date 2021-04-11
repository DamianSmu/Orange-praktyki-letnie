import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class MeetingPlanner {

    public static ArrayList<TimeInterval> planMeetings(Calendar calendar1, Calendar calendar2, Duration duration) {
        TimeInterval workingHours = calculateWorkingHours(calendar1, calendar2);
        if (workingHours == null) {
            return new ArrayList<>();
        }

        SortedSet<TimeInterval> plannedMeetings = new TreeSet<>();
        plannedMeetings.addAll(calendar1.getPlannedMeeting());
        plannedMeetings.addAll(calendar2.getPlannedMeeting());

        ArrayList<TimeInterval> availableMeetings = new ArrayList<>();
        if (plannedMeetings.size() > 0) {
            TimeInterval firstInterval = new TimeInterval(workingHours.getStart(), plannedMeetings.first().getStart());
            availableMeetings.addAll(getMeetingsBetween(firstInterval, duration));
            Iterator<TimeInterval> iterator = plannedMeetings.iterator();
            TimeInterval meeting = iterator.next();

            while (iterator.hasNext()) {
                TimeInterval nextMeeting = iterator.next();
                TimeInterval between = new TimeInterval(meeting.getEnd(), nextMeeting.getStart());
                availableMeetings.addAll(getMeetingsBetween(between, duration));
                meeting = nextMeeting;
            }

            TimeInterval lastMeeting = plannedMeetings.last();
            TimeInterval lastInterval = new TimeInterval(lastMeeting.getEnd(), workingHours.getEnd());
            availableMeetings.addAll(getMeetingsBetween(lastInterval, duration));
        } else {
            TimeInterval workingHoursInterval = new TimeInterval(workingHours.getStart(), workingHours.getEnd());
            availableMeetings.addAll(getMeetingsBetween(workingHoursInterval, duration));
        }
        return availableMeetings;
    }

    private static TimeInterval calculateWorkingHours(Calendar calendar1, Calendar calendar2) {
        TimeInterval workingHours1 = calendar1.getWorkingHours();
        TimeInterval workingHours2 = calendar2.getWorkingHours();

        LocalTime start = workingHours1.getStart().isBefore(workingHours2.getStart()) ? workingHours2.getStart() : workingHours1.getStart();
        LocalTime end = workingHours1.getEnd().isAfter(workingHours2.getStart()) ? workingHours2.getEnd() : workingHours1.getEnd();

        if (start.isAfter(end)) {
            return null;
        } else {
            return new TimeInterval(start, end);
        }
    }

    private static ArrayList<TimeInterval> getMeetingsBetween(TimeInterval time, Duration duration) {
        LocalTime meetingStart = time.getStart();
        if (meetingStart.plus(duration).isBefore(time.getEnd()) || meetingStart.plus(duration).equals(time.getEnd())) {
            return new ArrayList<>(Collections.singleton(new TimeInterval(meetingStart, time.getEnd())));
        } else {
            return new ArrayList<>();
        }
    }
}
