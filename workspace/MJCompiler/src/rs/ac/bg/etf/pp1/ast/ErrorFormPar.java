// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class ErrorFormPar extends EPFormPar {

    public ErrorFormPar () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ErrorFormPar(\n");

        buffer.append(tab);
        buffer.append(") [ErrorFormPar]");
        return buffer.toString();
    }
}