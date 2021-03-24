package iart.t4g11;

public class Position<R, C> {
    private R r;
    private C c;

    public Position(R r, C c){
        this.r = r;
        this.c = c;
    }

    public R getRow(){ return r; }
    public C getCol(){ return c; }
    public void setRow(R r){ this.r = r; }
    public void setCol(C c){ this.c = c; }
}
