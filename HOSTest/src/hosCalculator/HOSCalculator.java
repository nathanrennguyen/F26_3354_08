package hosCalculator;



public class HOSCalculator {
	private long limit;
	public HOSCalculator(long limit) {
		this.limit = limit;
	}
	
	public long calculateDrivingMinutes(Driver driver) {
        long total = 0;

        for (DrivingSession session : driver.getSessions()) {
            total += session.getDurationMinutes();
        }

        return total;
    }

    public boolean exceedsDrivingLimit(Driver driver) { //limit should be in minute format
        return calculateDrivingMinutes(driver) > this.limit;
    }
}
