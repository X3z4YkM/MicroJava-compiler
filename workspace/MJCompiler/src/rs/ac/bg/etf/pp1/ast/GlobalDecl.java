// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:58


package rs.ac.bg.etf.pp1.ast;

public class GlobalDecl extends Decl {

    private EPGlobalVarDecl EPGlobalVarDecl;

    public GlobalDecl (EPGlobalVarDecl EPGlobalVarDecl) {
        this.EPGlobalVarDecl=EPGlobalVarDecl;
        if(EPGlobalVarDecl!=null) EPGlobalVarDecl.setParent(this);
    }

    public EPGlobalVarDecl getEPGlobalVarDecl() {
        return EPGlobalVarDecl;
    }

    public void setEPGlobalVarDecl(EPGlobalVarDecl EPGlobalVarDecl) {
        this.EPGlobalVarDecl=EPGlobalVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EPGlobalVarDecl!=null) EPGlobalVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EPGlobalVarDecl!=null) EPGlobalVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EPGlobalVarDecl!=null) EPGlobalVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalDecl(\n");

        if(EPGlobalVarDecl!=null)
            buffer.append(EPGlobalVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalDecl]");
        return buffer.toString();
    }
}
