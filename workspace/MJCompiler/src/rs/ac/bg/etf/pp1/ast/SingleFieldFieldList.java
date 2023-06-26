// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class SingleFieldFieldList extends FildElem {

    private Fild Fild;

    public SingleFieldFieldList (Fild Fild) {
        this.Fild=Fild;
        if(Fild!=null) Fild.setParent(this);
    }

    public Fild getFild() {
        return Fild;
    }

    public void setFild(Fild Fild) {
        this.Fild=Fild;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Fild!=null) Fild.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Fild!=null) Fild.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Fild!=null) Fild.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleFieldFieldList(\n");

        if(Fild!=null)
            buffer.append(Fild.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleFieldFieldList]");
        return buffer.toString();
    }
}
