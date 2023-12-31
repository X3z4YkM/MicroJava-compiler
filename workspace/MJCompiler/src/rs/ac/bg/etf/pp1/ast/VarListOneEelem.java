// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class VarListOneEelem extends VarList {

    private VarElem VarElem;

    public VarListOneEelem (VarElem VarElem) {
        this.VarElem=VarElem;
        if(VarElem!=null) VarElem.setParent(this);
    }

    public VarElem getVarElem() {
        return VarElem;
    }

    public void setVarElem(VarElem VarElem) {
        this.VarElem=VarElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarElem!=null) VarElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarElem!=null) VarElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarElem!=null) VarElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarListOneEelem(\n");

        if(VarElem!=null)
            buffer.append(VarElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarListOneEelem]");
        return buffer.toString();
    }
}
