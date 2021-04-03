package iart.t4g11;

import java.io.Serializable;

public class Pair implements Serializable {
    private int a;
    private int b;

    public Pair(int a, int b){
        this.a = a;
        this.b = b;
    }

    public int getA(){ return a; }
    public int getB(){ return b; }
    public void setA(int a){ this.a = a; }
    public void setB(int b){ this.b = b; }
    @Override
    public String toString() { return "(" + a + ", " + b + ")"; }
    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) return true;
        if(!(o instanceof Pair)) return false;
        Pair p = (Pair) o;
        return p.getA() == this.getA() && p.getB() == this.getB();
    }
}
