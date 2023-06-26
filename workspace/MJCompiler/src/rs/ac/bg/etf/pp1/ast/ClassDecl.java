// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:58


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl extends Decl {

    private ClassName ClassName;
    private ExtendOpt ExtendOpt;
    private VarDeclListFild VarDeclListFild;
    private OptMethodDecList OptMethodDecList;

    public ClassDecl (ClassName ClassName, ExtendOpt ExtendOpt, VarDeclListFild VarDeclListFild, OptMethodDecList OptMethodDecList) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.ExtendOpt=ExtendOpt;
        if(ExtendOpt!=null) ExtendOpt.setParent(this);
        this.VarDeclListFild=VarDeclListFild;
        if(VarDeclListFild!=null) VarDeclListFild.setParent(this);
        this.OptMethodDecList=OptMethodDecList;
        if(OptMethodDecList!=null) OptMethodDecList.setParent(this);
    }

    public ClassName getClassName() {
        return ClassName;
    }

    public void setClassName(ClassName ClassName) {
        this.ClassName=ClassName;
    }

    public ExtendOpt getExtendOpt() {
        return ExtendOpt;
    }

    public void setExtendOpt(ExtendOpt ExtendOpt) {
        this.ExtendOpt=ExtendOpt;
    }

    public VarDeclListFild getVarDeclListFild() {
        return VarDeclListFild;
    }

    public void setVarDeclListFild(VarDeclListFild VarDeclListFild) {
        this.VarDeclListFild=VarDeclListFild;
    }

    public OptMethodDecList getOptMethodDecList() {
        return OptMethodDecList;
    }

    public void setOptMethodDecList(OptMethodDecList OptMethodDecList) {
        this.OptMethodDecList=OptMethodDecList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassName!=null) ClassName.accept(visitor);
        if(ExtendOpt!=null) ExtendOpt.accept(visitor);
        if(VarDeclListFild!=null) VarDeclListFild.accept(visitor);
        if(OptMethodDecList!=null) OptMethodDecList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(ExtendOpt!=null) ExtendOpt.traverseTopDown(visitor);
        if(VarDeclListFild!=null) VarDeclListFild.traverseTopDown(visitor);
        if(OptMethodDecList!=null) OptMethodDecList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(ExtendOpt!=null) ExtendOpt.traverseBottomUp(visitor);
        if(VarDeclListFild!=null) VarDeclListFild.traverseBottomUp(visitor);
        if(OptMethodDecList!=null) OptMethodDecList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExtendOpt!=null)
            buffer.append(ExtendOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclListFild!=null)
            buffer.append(VarDeclListFild.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptMethodDecList!=null)
            buffer.append(OptMethodDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
