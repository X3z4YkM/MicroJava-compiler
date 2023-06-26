// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class CorFieldDecl extends VarDeclListFildElem {

    private Type Type;
    private FildElem FildElem;

    public CorFieldDecl (Type Type, FildElem FildElem) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.FildElem=FildElem;
        if(FildElem!=null) FildElem.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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
        if(Type!=null) Type.accept(visitor);
        if(FildElem!=null) FildElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FildElem!=null) FildElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FildElem!=null) FildElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CorFieldDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FildElem!=null)
            buffer.append(FildElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CorFieldDecl]");
        return buffer.toString();
    }
}
