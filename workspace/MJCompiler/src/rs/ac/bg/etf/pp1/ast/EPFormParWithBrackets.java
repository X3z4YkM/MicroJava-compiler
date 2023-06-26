// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class EPFormParWithBrackets extends EPFormPar {

    private Type Type;
    private String ident;

    public EPFormParWithBrackets (Type Type, String ident) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ident=ident;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident=ident;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EPFormParWithBrackets(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+ident);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EPFormParWithBrackets]");
        return buffer.toString();
    }
}