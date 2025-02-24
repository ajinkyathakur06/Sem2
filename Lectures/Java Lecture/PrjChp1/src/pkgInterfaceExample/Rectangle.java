package pkgInterfaceExample;

public class Rectangle implements Shape {

	@Override
	public void drawShape(int no_of_sides) {

		System.out.println("This is implemented from reactangle class and it has "+no_of_sides+" sides for the shape");
	}
	
	@Override
	public void printShape() {
		System.out.println("From rectangle class");
	}
}
