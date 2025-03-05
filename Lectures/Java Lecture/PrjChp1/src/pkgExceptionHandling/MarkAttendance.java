package pkgExceptionHandling;

import java.time.LocalTime;

public class MarkAttendance {
	
	public void scanCard(int lecNbr)throws LateComerException
	{
		LocalTime currentTime = LocalTime.now();
		LocalTime tmpLocalTime = null;
		if (lecNbr==1) 
		{
			tmpLocalTime = LocalTime.of(9, 20);
		}
		else if (lecNbr==2) 
		{
			tmpLocalTime = LocalTime.of(10, 20);
		}
		else if(lecNbr==3) 
		{
			tmpLocalTime = LocalTime.of(11, 20);
		}
		if((lecNbr==1 && currentTime.isAfter(tmpLocalTime)) || 
		  (lecNbr==2 && currentTime.isAfter(tmpLocalTime)) || 
		  (lecNbr==3 && currentTime.isAfter(tmpLocalTime)))
		{
			throw new LateComerException(tmpLocalTime.toString());
		}
		else if((lecNbr==1 && currentTime.isBefore(tmpLocalTime)) || 
				  (lecNbr==2 && currentTime.isBefore(tmpLocalTime)) || 
				  (lecNbr==3 && currentTime.isBefore(tmpLocalTime)))
		{
			System.out.println("Student is in Time Attendance Marked");
		}
	}
	
}
