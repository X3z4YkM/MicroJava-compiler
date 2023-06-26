// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class NonEmptyDesignatorsList extends DesignatorsList {

    private DesignatorsList DesignatorsList;
    private DesignatorElem DesignatorElem;

    public NonEmptyDesignatorsList (DesignatorsList DesignatorsList, DesignatorElem DesignatorElem) {
        this.DesignatorsList=DesignatorsList;
        if(DesignatorsList!=null) DesignatorsList.setParent(this);
        this.DesignatorElem=DesignatorElem;
        if(DesignatorElem!=null) DesignatorElem.setParent(this);
    }

    public DesignatorsList getDesignatorsList() {
        return DesignatorsList;
    }

    public void setDesignatorsList(DesignatorsList DesignatorsList) {
        this.DesignatorsList=DesignatorsList;
    }

    public DesignatorElem getDesignatorElem() {
        return DesignatorElem;
    }

    public void setDesignatorElem(DesignatorElem DesignatorElem) {
        this.DesignatorElem=DesignatorElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorsList!=null) DesignatorsList.accept(visitor);
        if(DesignatorElem!=null) DesignatorElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorsList!=null) DesignatorsList.traverseTopDown(visitor);
        if(DesignatorElem!=null) DesignatorElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorsList!=null) DesignatorsList.traverseBottomUp(visitor);
        if(DesignatorElem!=null) DesignatorElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NonEmptyDesignatorsList(\n");

        if(DesignatorsList!=null)
            buffer.append(DesignatorsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorElem!=null)
            buffer.append(DesignatorElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NonEmptyDesignatorsList]");
        return buffer.toString();
    }
}
