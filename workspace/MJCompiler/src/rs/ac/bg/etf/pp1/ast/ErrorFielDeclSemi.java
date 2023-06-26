// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class ErrorFielDeclSemi extends VarDeclListFildElem {

    private ErrFild ErrFild;

    public ErrorFielDeclSemi (ErrFild ErrFild) {
        this.ErrFild=ErrFild;
        if(ErrFild!=null) ErrFild.setParent(this);
    }

    public ErrFild getErrFild() {
        return ErrFild;
    }

    public void setErrFild(ErrFild ErrFild) {
        this.ErrFild=ErrFild;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ErrFild!=null) ErrFild.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ErrFild!=null) ErrFild.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ErrFild!=null) ErrFild.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ErrorFielDeclSemi(\n");

        if(ErrFild!=null)
            buffer.append(ErrFild.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ErrorFielDeclSemi]");
        return buffer.toString();
    }
}
