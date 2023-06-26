// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class FormParsListElem extends FormParsList {

    private EPFormPar EPFormPar;

    public FormParsListElem (EPFormPar EPFormPar) {
        this.EPFormPar=EPFormPar;
        if(EPFormPar!=null) EPFormPar.setParent(this);
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
        if(EPFormPar!=null) EPFormPar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EPFormPar!=null) EPFormPar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EPFormPar!=null) EPFormPar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParsListElem(\n");

        if(EPFormPar!=null)
            buffer.append(EPFormPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParsListElem]");
        return buffer.toString();
    }
}
