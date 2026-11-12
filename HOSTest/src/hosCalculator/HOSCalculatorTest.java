package hosCalculator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class HOSCalculatorTest {
	
	@Test
	public void test1() { //should run with no issues. 2 hours = 120 minutes
		Driver d = new Driver();
		LocalDateTime date1 = LocalDateTime.of(2026, 11, 10, 8, 0);
		LocalDateTime date2 = LocalDateTime.of(2026, 11, 10, 10, 0);
		DrivingSession s1 = new DrivingSession(date1, date2);
		d.addSessions(s1);
		HOSCalculator calc = new HOSCalculator(660);
		long HOS = calc.calculateDrivingMinutes(d);
		assertEquals(120, HOS, "Here is the test: ");
	}
	
	@Test
	public void test2() { //should run with no issues. 2 hrs + 1hr30 = 210 minutes
		Driver d = new Driver();
		LocalDateTime date1 = LocalDateTime.of(2026, 11, 10, 8, 0);
		LocalDateTime date2 = LocalDateTime.of(2026, 11, 10, 10, 0);
		LocalDateTime date3 = LocalDateTime.of(2026, 11, 10, 11, 0);
		LocalDateTime date4 = LocalDateTime.of(2026, 11, 10, 12, 30);
		
		DrivingSession s1 = new DrivingSession(date1, date2);
		DrivingSession s2 = new DrivingSession(date3, date4);
		
		d.addSessions(s1);
		d.addSessions(s2);
		HOSCalculator calc = new HOSCalculator(660);
		long HOS = calc.calculateDrivingMinutes(d);
		assertEquals(210, HOS, "Here is the test: ");
	}
	@Test
	public void test3() { //deliberate failure, as the time does not sum to 150.
		Driver d = new Driver();
		LocalDateTime date1 = LocalDateTime.of(2026, 11, 10, 8, 0);
		LocalDateTime date2 = LocalDateTime.of(2026, 11, 10, 10, 0);
		LocalDateTime date3 = LocalDateTime.of(2026, 11, 10, 11, 0);
		LocalDateTime date4 = LocalDateTime.of(2026, 11, 10, 12, 30);
		
		DrivingSession s1 = new DrivingSession(date1, date2);
		DrivingSession s2 = new DrivingSession(date3, date4);
		
		d.addSessions(s1);
		d.addSessions(s2);
		HOSCalculator calc = new HOSCalculator(660);
		long HOS = calc.calculateDrivingMinutes(d);
		assertEquals(150, HOS, "Here is the test: ");
	}
	
	@Test
	public void test4() { //should show success as the driving time is only 210 minutes and does not exceed 660 minutes.
		Driver d = new Driver();
		LocalDateTime date1 = LocalDateTime.of(2026, 11, 10, 8, 0);
		LocalDateTime date2 = LocalDateTime.of(2026, 11, 10, 10, 0);
		LocalDateTime date3 = LocalDateTime.of(2026, 11, 10, 11, 0);
		LocalDateTime date4 = LocalDateTime.of(2026, 11, 10, 12, 30);
		
		DrivingSession s1 = new DrivingSession(date1, date2);
		DrivingSession s2 = new DrivingSession(date3, date4);
		
		d.addSessions(s1);
		d.addSessions(s2);
		HOSCalculator calc = new HOSCalculator(660);
		assertEquals(false, calc.exceedsDrivingLimit(d), "Here is the test: ");
	}
	
	@Test
	public void test5() { //deliberate failure as the driving time is only 210 minutes and does not exceed 660 minutes.
		Driver d = new Driver();
		LocalDateTime date1 = LocalDateTime.of(2026, 11, 10, 8, 0);
		LocalDateTime date2 = LocalDateTime.of(2026, 11, 10, 10, 0);
		LocalDateTime date3 = LocalDateTime.of(2026, 11, 10, 11, 0);
		LocalDateTime date4 = LocalDateTime.of(2026, 11, 10, 12, 30);
		
		DrivingSession s1 = new DrivingSession(date1, date2);
		DrivingSession s2 = new DrivingSession(date3, date4);
		
		d.addSessions(s1);
		d.addSessions(s2);
		HOSCalculator calc = new HOSCalculator(660);
		assertEquals(true, calc.exceedsDrivingLimit(d), "Here is the test: ");
	}
	
	@Test
	public void test6() { //should show success as the driving time is exactly 660 minutes and does not exceed 660 minutes.
		Driver d = new Driver();
		LocalDateTime date1 = LocalDateTime.of(2026, 11, 10, 0, 0);
		LocalDateTime date2 = LocalDateTime.of(2026, 11, 10, 11, 0);
		
		DrivingSession s1 = new DrivingSession(date1, date2);
		
		d.addSessions(s1);
		HOSCalculator calc = new HOSCalculator(660);
		assertEquals(false, calc.exceedsDrivingLimit(d), "Here is the test: ");
	}
	
	@Test
	public void test7() { //deliberate failure as the driving time is exactly 660 minutes and does not exceed 660 minutes.
		Driver d = new Driver();
		LocalDateTime date1 = LocalDateTime.of(2026, 11, 10, 0, 0);
		LocalDateTime date2 = LocalDateTime.of(2026, 11, 10, 11, 0);
		
		DrivingSession s1 = new DrivingSession(date1, date2);
		
		d.addSessions(s1);
		HOSCalculator calc = new HOSCalculator(660);
		assertEquals(true, calc.exceedsDrivingLimit(d), "Here is the test: ");
	}
	
	@Test
	public void test8() { //should show success as the driving time is exactly 661 minutes and does exceed 660 minutes.
		Driver d = new Driver();
		LocalDateTime date1 = LocalDateTime.of(2026, 11, 10, 0, 0);
		LocalDateTime date2 = LocalDateTime.of(2026, 11, 10, 11, 1);
		
		DrivingSession s1 = new DrivingSession(date1, date2);
		
		d.addSessions(s1);
		HOSCalculator calc = new HOSCalculator(660);
		assertEquals(true, calc.exceedsDrivingLimit(d), "Here is the test: ");
	}
	
	@Test
	public void test9() { //deliberate failure as the driving time is exactly 661 minutes and does exceed 660 minutes.
		Driver d = new Driver();
		LocalDateTime date1 = LocalDateTime.of(2026, 11, 10, 0, 0);
		LocalDateTime date2 = LocalDateTime.of(2026, 11, 10, 11, 1);
		
		DrivingSession s1 = new DrivingSession(date1, date2);
		
		d.addSessions(s1);
		HOSCalculator calc = new HOSCalculator(660);
		assertEquals(false, calc.exceedsDrivingLimit(d), "Here is the test: ");
	}
	
}
