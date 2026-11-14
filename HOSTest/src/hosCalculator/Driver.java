package hosCalculator;

import java.util.ArrayList;

public class Driver {
	private ArrayList<DrivingSession> sessions;
	
	public Driver() {
		sessions = new ArrayList<>();
	}
	
	public void addSessions(DrivingSession session) {
		this.sessions.add(session);
	}
	
	public ArrayList<DrivingSession> getSessions() {
		return this.sessions;
	}
}
