// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:58


package rs.ac.bg.etf.pp1.ast;

public class Program implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ProgramName ProgramName;
    private DeclList DeclList;
    private MethodDecList MethodDecList;
    private ProgramEnd ProgramEnd;

    public Program (ProgramName ProgramName, DeclList DeclList, MethodDecList MethodDecList, ProgramEnd ProgramEnd) {
        this.ProgramName=ProgramName;
        if(ProgramName!=null) ProgramName.setParent(this);
        this.DeclList=DeclList;
        if(DeclList!=null) DeclList.setParent(this);
        this.MethodDecList=MethodDecList;
        if(MethodDecList!=null) MethodDecList.setParent(this);
        this.ProgramEnd=ProgramEnd;
        if(ProgramEnd!=null) ProgramEnd.setParent(this);
    }

    public ProgramName getProgramName() {
        return ProgramName;
    }

    public void setProgramName(ProgramName ProgramName) {
        this.ProgramName=ProgramName;
    }

    public DeclList getDeclList() {
        return DeclList;
    }

    public void setDeclList(DeclList DeclList) {
        this.DeclList=DeclList;
    }

    public MethodDecList getMethodDecList() {
        return MethodDecList;
    }

    public void setMethodDecList(MethodDecList MethodDecList) {
        this.MethodDecList=MethodDecList;
    }

    public ProgramEnd getProgramEnd() {
        return ProgramEnd;
    }

    public void setProgramEnd(ProgramEnd ProgramEnd) {
        this.ProgramEnd=ProgramEnd;
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
        if(ProgramName!=null) ProgramName.accept(visitor);
        if(DeclList!=null) DeclList.accept(visitor);
        if(MethodDecList!=null) MethodDecList.accept(visitor);
        if(ProgramEnd!=null) ProgramEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgramName!=null) ProgramName.traverseTopDown(visitor);
        if(DeclList!=null) DeclList.traverseTopDown(visitor);
        if(MethodDecList!=null) MethodDecList.traverseTopDown(visitor);
        if(ProgramEnd!=null) ProgramEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgramName!=null) ProgramName.traverseBottomUp(visitor);
        if(DeclList!=null) DeclList.traverseBottomUp(visitor);
        if(MethodDecList!=null) MethodDecList.traverseBottomUp(visitor);
        if(ProgramEnd!=null) ProgramEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program(\n");

        if(ProgramName!=null)
            buffer.append(ProgramName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DeclList!=null)
            buffer.append(DeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecList!=null)
            buffer.append(MethodDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ProgramEnd!=null)
            buffer.append(ProgramEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program]");
        return buffer.toString();
    }
}
