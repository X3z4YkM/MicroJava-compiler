// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class PrintStmt extends SingleStatement {

    private Expr Expr;
    private SingStmCommaIntOpt SingStmCommaIntOpt;

    public PrintStmt (Expr Expr, SingStmCommaIntOpt SingStmCommaIntOpt) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.SingStmCommaIntOpt=SingStmCommaIntOpt;
        if(SingStmCommaIntOpt!=null) SingStmCommaIntOpt.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public SingStmCommaIntOpt getSingStmCommaIntOpt() {
        return SingStmCommaIntOpt;
    }

    public void setSingStmCommaIntOpt(SingStmCommaIntOpt SingStmCommaIntOpt) {
        this.SingStmCommaIntOpt=SingStmCommaIntOpt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(SingStmCommaIntOpt!=null) SingStmCommaIntOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(SingStmCommaIntOpt!=null) SingStmCommaIntOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(SingStmCommaIntOpt!=null) SingStmCommaIntOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStmt(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingStmCommaIntOpt!=null)
            buffer.append(SingStmCommaIntOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStmt]");
        return buffer.toString();
    }
}
