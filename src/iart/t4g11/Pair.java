package iart.t4g11;

public class Pair<A, B> {
    private A a;
    private B b;

    public Pair(A a, B b){
        this.a = a;
        this.b = b;
    }

    public A getA(){ return a; }
    public B getB(){ return b; }
    public void setRow(A a){ this.a = a; }
    public void setCol(B b){ this.b = b; }
    @Override
    public String toString() { return "(" + a + ", " + b + ")"; }
}
