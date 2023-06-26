// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class GlobalVectorList extends GlobalVarList {

    private GlobalVarList GlobalVarList;
    private ElemEGlobalVar ElemEGlobalVar;

    public GlobalVectorList (GlobalVarList GlobalVarList, ElemEGlobalVar ElemEGlobalVar) {
        this.GlobalVarList=GlobalVarList;
        if(GlobalVarList!=null) GlobalVarList.setParent(this);
        this.ElemEGlobalVar=ElemEGlobalVar;
        if(ElemEGlobalVar!=null) ElemEGlobalVar.setParent(this);
    }

    public GlobalVarList getGlobalVarList() {
        return GlobalVarList;
    }

    public void setGlobalVarList(GlobalVarList GlobalVarList) {
        this.GlobalVarList=GlobalVarList;
    }

    public ElemEGlobalVar getElemEGlobalVar() {
        return ElemEGlobalVar;
    }

    public void setElemEGlobalVar(ElemEGlobalVar ElemEGlobalVar) {
        this.ElemEGlobalVar=ElemEGlobalVar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobalVarList!=null) GlobalVarList.accept(visitor);
        if(ElemEGlobalVar!=null) ElemEGlobalVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalVarList!=null) GlobalVarList.traverseTopDown(visitor);
        if(ElemEGlobalVar!=null) ElemEGlobalVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalVarList!=null) GlobalVarList.traverseBottomUp(visitor);
        if(ElemEGlobalVar!=null) ElemEGlobalVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalVectorList(\n");

        if(GlobalVarList!=null)
            buffer.append(GlobalVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElemEGlobalVar!=null)
            buffer.append(ElemEGlobalVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalVectorList]");
        return buffer.toString();
    }
}
