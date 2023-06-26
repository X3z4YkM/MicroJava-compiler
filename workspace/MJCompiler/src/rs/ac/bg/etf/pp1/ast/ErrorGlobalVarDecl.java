// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:58


package rs.ac.bg.etf.pp1.ast;

public class ErrorGlobalVarDecl extends EPGlobalVarDecl {

    private ErrorGlobaVar ErrorGlobaVar;

    public ErrorGlobalVarDecl (ErrorGlobaVar ErrorGlobaVar) {
        this.ErrorGlobaVar=ErrorGlobaVar;
        if(ErrorGlobaVar!=null) ErrorGlobaVar.setParent(this);
    }

    public ErrorGlobaVar getErrorGlobaVar() {
        return ErrorGlobaVar;
    }

    public void setErrorGlobaVar(ErrorGlobaVar ErrorGlobaVar) {
        this.ErrorGlobaVar=ErrorGlobaVar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ErrorGlobaVar!=null) ErrorGlobaVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ErrorGlobaVar!=null) ErrorGlobaVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ErrorGlobaVar!=null) ErrorGlobaVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ErrorGlobalVarDecl(\n");

        if(ErrorGlobaVar!=null)
            buffer.append(ErrorGlobaVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ErrorGlobalVarDecl]");
        return buffer.toString();
    }
}
