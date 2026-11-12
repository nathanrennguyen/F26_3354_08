package hosCalculator;
import java.time.LocalDateTime;
import java.time.Duration;

public class DrivingSession {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public DrivingSession(LocalDateTime startTime, LocalDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public long getDurationMinutes() {
		return Duration.between(startTime, endTime).toMinutes();
	}
}
