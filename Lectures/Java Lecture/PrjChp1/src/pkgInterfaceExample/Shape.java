package pkgInterfaceExample;

public interface Shape {
	public void drawShape(int no_of_sides);
	
	public default void printShape() {
		System.out.println("This is a shape");
	}
	
	public static void aboutShape() {
		System.out.println("This feature comes from Shape interface");
	}
	
}
