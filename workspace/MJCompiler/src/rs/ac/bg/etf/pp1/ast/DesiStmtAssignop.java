// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class DesiStmtAssignop extends DesignatorStatement {

    private Designator Designator;
    private Assignop Assignop;
    private ExprEP ExprEP;

    public DesiStmtAssignop (Designator Designator, Assignop Assignop, ExprEP ExprEP) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.ExprEP=ExprEP;
        if(ExprEP!=null) ExprEP.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public ExprEP getExprEP() {
        return ExprEP;
    }

    public void setExprEP(ExprEP ExprEP) {
        this.ExprEP=ExprEP;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(Assignop!=null) Assignop.accept(visitor);
        if(ExprEP!=null) ExprEP.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
        if(ExprEP!=null) ExprEP.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        if(ExprEP!=null) ExprEP.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesiStmtAssignop(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprEP!=null)
            buffer.append(ExprEP.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesiStmtAssignop]");
        return buffer.toString();
    }
}
