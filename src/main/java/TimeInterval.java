import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;


public class TimeInterval implements Comparable<TimeInterval>{
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime start;
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime end;

    public TimeInterval() {
    }

    public TimeInterval(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "[\"" + start + "\", \"" + end + "\"]";
    }

    @Override
    public int compareTo(TimeInterval o) {
        if(this.start.equals(o.getStart())){
            if(this.end.isAfter(o.getEnd())){
                return 1;
            } else if(this.end.isBefore(o.getEnd())){
                return -1;
            } else {
                return 0;
            }
        }
        if(this.start.isBefore(o.getStart())){
            return -1;
        } else if(this.start.isAfter(o.getStart())){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeInterval that = (TimeInterval) o;
        return start.equals(that.start) && end.equals(that.end);
    }
}
