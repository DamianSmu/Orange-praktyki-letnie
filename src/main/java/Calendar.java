import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class Calendar {

    @JsonProperty("working_hours")
    private TimeInterval workingHours;
    @JsonProperty("planned_meeting")
    private List<TimeInterval> plannedMeeting;

    public Calendar() {
    }

    public Calendar(TimeInterval workingHours, List<TimeInterval> plannedMeeting) {
        this.workingHours = workingHours;
        this.plannedMeeting = plannedMeeting;
    }

    public List<TimeInterval> getPlannedMeeting() {
        return plannedMeeting;
    }

    public void setPlannedMeeting(List<TimeInterval> plannedMeeting) {
        this.plannedMeeting = plannedMeeting;
    }

    public TimeInterval getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(TimeInterval workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return Objects.equals(workingHours, calendar.workingHours) && Objects.equals(plannedMeeting, calendar.plannedMeeting);
    }
}
