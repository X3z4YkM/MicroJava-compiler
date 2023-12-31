// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class VarListMultipleElem extends VarList {

    private VarList VarList;
    private VarElem VarElem;

    public VarListMultipleElem (VarList VarList, VarElem VarElem) {
        this.VarList=VarList;
        if(VarList!=null) VarList.setParent(this);
        this.VarElem=VarElem;
        if(VarElem!=null) VarElem.setParent(this);
    }

    public VarList getVarList() {
        return VarList;
    }

    public void setVarList(VarList VarList) {
        this.VarList=VarList;
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
        if(VarList!=null) VarList.accept(visitor);
        if(VarElem!=null) VarElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarList!=null) VarList.traverseTopDown(visitor);
        if(VarElem!=null) VarElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarList!=null) VarList.traverseBottomUp(visitor);
        if(VarElem!=null) VarElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarListMultipleElem(\n");

        if(VarList!=null)
            buffer.append(VarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarElem!=null)
            buffer.append(VarElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarListMultipleElem]");
        return buffer.toString();
    }
}
