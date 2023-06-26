// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class VectorForParsList extends FormParsList {

    private FormParsList FormParsList;
    private EPFormPar EPFormPar;

    public VectorForParsList (FormParsList FormParsList, EPFormPar EPFormPar) {
        this.FormParsList=FormParsList;
        if(FormParsList!=null) FormParsList.setParent(this);
        this.EPFormPar=EPFormPar;
        if(EPFormPar!=null) EPFormPar.setParent(this);
    }

    public FormParsList getFormParsList() {
        return FormParsList;
    }

    public void setFormParsList(FormParsList FormParsList) {
        this.FormParsList=FormParsList;
    }

    public EPFormPar getEPFormPar() {
        return EPFormPar;
    }

    public void setEPFormPar(EPFormPar EPFormPar) {
        this.EPFormPar=EPFormPar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParsList!=null) FormParsList.accept(visitor);
        if(EPFormPar!=null) EPFormPar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsList!=null) FormParsList.traverseTopDown(visitor);
        if(EPFormPar!=null) EPFormPar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsList!=null) FormParsList.traverseBottomUp(visitor);
        if(EPFormPar!=null) EPFormPar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VectorForParsList(\n");

        if(FormParsList!=null)
            buffer.append(FormParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EPFormPar!=null)
            buffer.append(EPFormPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VectorForParsList]");
        return buffer.toString();
    }
}
