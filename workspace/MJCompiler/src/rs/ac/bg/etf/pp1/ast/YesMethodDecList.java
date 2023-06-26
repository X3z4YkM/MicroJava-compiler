// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class YesMethodDecList extends OptMethodDecList {

    private MethodDecList MethodDecList;

    public YesMethodDecList (MethodDecList MethodDecList) {
        this.MethodDecList=MethodDecList;
        if(MethodDecList!=null) MethodDecList.setParent(this);
    }

    public MethodDecList getMethodDecList() {
        return MethodDecList;
    }

    public void setMethodDecList(MethodDecList MethodDecList) {
        this.MethodDecList=MethodDecList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDecList!=null) MethodDecList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDecList!=null) MethodDecList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDecList!=null) MethodDecList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesMethodDecList(\n");

        if(MethodDecList!=null)
            buffer.append(MethodDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [YesMethodDecList]");
        return buffer.toString();
    }
}
