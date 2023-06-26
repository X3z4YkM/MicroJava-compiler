// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class MultipleFieldFieldList extends FildElem {

    private Fild Fild;
    private FildElem FildElem;

    public MultipleFieldFieldList (Fild Fild, FildElem FildElem) {
        this.Fild=Fild;
        if(Fild!=null) Fild.setParent(this);
        this.FildElem=FildElem;
        if(FildElem!=null) FildElem.setParent(this);
    }

    public Fild getFild() {
        return Fild;
    }

    public void setFild(Fild Fild) {
        this.Fild=Fild;
    }

    public FildElem getFildElem() {
        return FildElem;
    }

    public void setFildElem(FildElem FildElem) {
        this.FildElem=FildElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Fild!=null) Fild.accept(visitor);
        if(FildElem!=null) FildElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Fild!=null) Fild.traverseTopDown(visitor);
        if(FildElem!=null) FildElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Fild!=null) Fild.traverseBottomUp(visitor);
        if(FildElem!=null) FildElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleFieldFieldList(\n");

        if(Fild!=null)
            buffer.append(Fild.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FildElem!=null)
            buffer.append(FildElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleFieldFieldList]");
        return buffer.toString();
    }
}
