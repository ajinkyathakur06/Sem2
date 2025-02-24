package pkgInterfaceExample;

public class OuterB implements OuterClass.innerInterface {
    public int n, m;

    public OuterB(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public void printValues() {
        System.out.println("n is : " + n);
        System.out.println("m is : " + m);
    }

    public void innerM1() {
        System.out.println("This message comes from implemented innerM1 in OuterB class");
    }
}
