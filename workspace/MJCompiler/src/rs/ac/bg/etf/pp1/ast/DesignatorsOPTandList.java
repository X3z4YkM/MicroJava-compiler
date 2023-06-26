// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class DesignatorsOPTandList extends Designators {

    private DesignatorOPT DesignatorOPT;
    private DesignatorsList DesignatorsList;

    public DesignatorsOPTandList (DesignatorOPT DesignatorOPT, DesignatorsList DesignatorsList) {
        this.DesignatorOPT=DesignatorOPT;
        if(DesignatorOPT!=null) DesignatorOPT.setParent(this);
        this.DesignatorsList=DesignatorsList;
        if(DesignatorsList!=null) DesignatorsList.setParent(this);
    }

    public DesignatorOPT getDesignatorOPT() {
        return DesignatorOPT;
    }

    public void setDesignatorOPT(DesignatorOPT DesignatorOPT) {
        this.DesignatorOPT=DesignatorOPT;
    }

    public DesignatorsList getDesignatorsList() {
        return DesignatorsList;
    }

    public void setDesignatorsList(DesignatorsList DesignatorsList) {
        this.DesignatorsList=DesignatorsList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorOPT!=null) DesignatorOPT.accept(visitor);
        if(DesignatorsList!=null) DesignatorsList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorOPT!=null) DesignatorOPT.traverseTopDown(visitor);
        if(DesignatorsList!=null) DesignatorsList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorOPT!=null) DesignatorOPT.traverseBottomUp(visitor);
        if(DesignatorsList!=null) DesignatorsList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorsOPTandList(\n");

        if(DesignatorOPT!=null)
            buffer.append(DesignatorOPT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorsList!=null)
            buffer.append(DesignatorsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorsOPTandList]");
        return buffer.toString();
    }
}
