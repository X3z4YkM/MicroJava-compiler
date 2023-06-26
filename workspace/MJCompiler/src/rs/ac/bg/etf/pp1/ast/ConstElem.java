// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:58


package rs.ac.bg.etf.pp1.ast;

public class ConstElem implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private String ident;
    private Literal Literal;

    public ConstElem (String ident, Literal Literal) {
        this.ident=ident;
        this.Literal=Literal;
        if(Literal!=null) Literal.setParent(this);
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident=ident;
    }

    public Literal getLiteral() {
        return Literal;
    }

    public void setLiteral(Literal Literal) {
        this.Literal=Literal;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Literal!=null) Literal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Literal!=null) Literal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Literal!=null) Literal.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstElem(\n");

        buffer.append(" "+tab+ident);
        buffer.append("\n");

        if(Literal!=null)
            buffer.append(Literal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstElem]");
        return buffer.toString();
    }
}
