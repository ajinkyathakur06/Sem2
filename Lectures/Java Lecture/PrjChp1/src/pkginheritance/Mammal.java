package pkginheritance;
public class Mammal extends Animal{
	private int litterSize;
	private int gestationPeriod;
	 
	public int getLitterSize() {
		return litterSize;
	}
	 
	public void setLitterSize(int litterSize) {
		this.litterSize = litterSize;
	}
	
	public int getGestationPeriod() {
		return gestationPeriod;
	}
	
	public void setGestationPeriod(int gestationPeriod) {
		this.gestationPeriod = gestationPeriod;
	}
	
	public void details() {
		System.out.println("Age of mammal "+super.getAge());
	}
	
	public Mammal() {
		super(20,10.5,"male");
		System.out.println("Mammal class obj created");
		
	}
}
