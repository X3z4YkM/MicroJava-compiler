// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:30:59


package rs.ac.bg.etf.pp1.ast;

public class DesignatorElemCommaDesi extends DesignatorElem {

    private DesignatorOPT DesignatorOPT;

    public DesignatorElemCommaDesi (DesignatorOPT DesignatorOPT) {
        this.DesignatorOPT=DesignatorOPT;
        if(DesignatorOPT!=null) DesignatorOPT.setParent(this);
    }

    public DesignatorOPT getDesignatorOPT() {
        return DesignatorOPT;
    }

    public void setDesignatorOPT(DesignatorOPT DesignatorOPT) {
        this.DesignatorOPT=DesignatorOPT;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorOPT!=null) DesignatorOPT.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorOPT!=null) DesignatorOPT.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorOPT!=null) DesignatorOPT.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorElemCommaDesi(\n");

        if(DesignatorOPT!=null)
            buffer.append(DesignatorOPT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorElemCommaDesi]");
        return buffer.toString();
    }
}
