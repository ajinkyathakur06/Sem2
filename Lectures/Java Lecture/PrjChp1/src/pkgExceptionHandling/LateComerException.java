package pkgExceptionHandling;

/*This is a custom exception class.
Its object is thrown whan a student is late for the lecture */

public class LateComerException extends Exception{
	private String tmstp;
	public LateComerException(String tm) {
		tmstp = tm;
	}
	
	public String toString() {
		return "This is LateComer Exception.\n"+tmstp+" is the last time to  mark attendance";
	}
}
