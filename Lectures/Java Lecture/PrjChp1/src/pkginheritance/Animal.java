package pkginheritance;

public class Animal {
	private int age;
	private String gender;
	private double weight;
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void eat() {
		
	}
	
	public void sleep() {
		
	}
	
	public void details() {
		System.out.println("Age of animal "+this.getAge());
	}
	
	public Animal() {
		System.out.println("Non parametrised constructor of animal class");
	}
	
	public Animal(int ag, double wt, String gn) {
		this.age = ag;
		this.weight = wt;
		this.gender = gn;
		System.out.println("Parameterised constructor of animal class");
	}
}
