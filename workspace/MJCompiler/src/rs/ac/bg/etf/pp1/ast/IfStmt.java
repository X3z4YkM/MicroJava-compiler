// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class IfStmt extends SingleStatement {

    private ConditionEP ConditionEP;
    private Statement Statement;

    public IfStmt (ConditionEP ConditionEP, Statement Statement) {
        this.ConditionEP=ConditionEP;
        if(ConditionEP!=null) ConditionEP.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ConditionEP getConditionEP() {
        return ConditionEP;
    }

    public void setConditionEP(ConditionEP ConditionEP) {
        this.ConditionEP=ConditionEP;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionEP!=null) ConditionEP.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionEP!=null) ConditionEP.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionEP!=null) ConditionEP.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStmt(\n");

        if(ConditionEP!=null)
            buffer.append(ConditionEP.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStmt]");
        return buffer.toString();
    }
}
