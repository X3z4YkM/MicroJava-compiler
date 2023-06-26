// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:31:0


package rs.ac.bg.etf.pp1.ast;

public class DesignatorLExprR extends ElemListeDesi {

    private DesignatorExtendLBR DesignatorExtendLBR;
    private Expr Expr;

    public DesignatorLExprR (DesignatorExtendLBR DesignatorExtendLBR, Expr Expr) {
        this.DesignatorExtendLBR=DesignatorExtendLBR;
        if(DesignatorExtendLBR!=null) DesignatorExtendLBR.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorExtendLBR getDesignatorExtendLBR() {
        return DesignatorExtendLBR;
    }

    public void setDesignatorExtendLBR(DesignatorExtendLBR DesignatorExtendLBR) {
        this.DesignatorExtendLBR=DesignatorExtendLBR;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorExtendLBR!=null) DesignatorExtendLBR.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorExtendLBR!=null) DesignatorExtendLBR.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorExtendLBR!=null) DesignatorExtendLBR.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorLExprR(\n");

        if(DesignatorExtendLBR!=null)
            buffer.append(DesignatorExtendLBR.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorLExprR]");
        return buffer.toString();
    }
}
