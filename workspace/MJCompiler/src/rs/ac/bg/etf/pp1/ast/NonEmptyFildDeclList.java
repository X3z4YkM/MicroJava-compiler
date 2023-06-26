// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class NonEmptyFildDeclList extends VarDeclListFild {

    private VarDeclListFild VarDeclListFild;
    private VarDeclListFildElem VarDeclListFildElem;

    public NonEmptyFildDeclList (VarDeclListFild VarDeclListFild, VarDeclListFildElem VarDeclListFildElem) {
        this.VarDeclListFild=VarDeclListFild;
        if(VarDeclListFild!=null) VarDeclListFild.setParent(this);
        this.VarDeclListFildElem=VarDeclListFildElem;
        if(VarDeclListFildElem!=null) VarDeclListFildElem.setParent(this);
    }

    public VarDeclListFild getVarDeclListFild() {
        return VarDeclListFild;
    }

    public void setVarDeclListFild(VarDeclListFild VarDeclListFild) {
        this.VarDeclListFild=VarDeclListFild;
    }

    public VarDeclListFildElem getVarDeclListFildElem() {
        return VarDeclListFildElem;
    }

    public void setVarDeclListFildElem(VarDeclListFildElem VarDeclListFildElem) {
        this.VarDeclListFildElem=VarDeclListFildElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclListFild!=null) VarDeclListFild.accept(visitor);
        if(VarDeclListFildElem!=null) VarDeclListFildElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclListFild!=null) VarDeclListFild.traverseTopDown(visitor);
        if(VarDeclListFildElem!=null) VarDeclListFildElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclListFild!=null) VarDeclListFild.traverseBottomUp(visitor);
        if(VarDeclListFildElem!=null) VarDeclListFildElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NonEmptyFildDeclList(\n");

        if(VarDeclListFild!=null)
            buffer.append(VarDeclListFild.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclListFildElem!=null)
            buffer.append(VarDeclListFildElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NonEmptyFildDeclList]");
        return buffer.toString();
    }
}
