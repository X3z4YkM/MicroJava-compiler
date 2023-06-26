// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:31:0


package rs.ac.bg.etf.pp1.ast;

public class FactorDesignatorWithActPars extends Factor {

    private Designator Designator;
    private ActParsLBR ActParsLBR;
    private ActPars ActPars;
    private ActParsRBR ActParsRBR;

    public FactorDesignatorWithActPars (Designator Designator, ActParsLBR ActParsLBR, ActPars ActPars, ActParsRBR ActParsRBR) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.ActParsLBR=ActParsLBR;
        if(ActParsLBR!=null) ActParsLBR.setParent(this);
        this.ActPars=ActPars;
        if(ActPars!=null) ActPars.setParent(this);
        this.ActParsRBR=ActParsRBR;
        if(ActParsRBR!=null) ActParsRBR.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public ActParsLBR getActParsLBR() {
        return ActParsLBR;
    }

    public void setActParsLBR(ActParsLBR ActParsLBR) {
        this.ActParsLBR=ActParsLBR;
    }

    public ActPars getActPars() {
        return ActPars;
    }

    public void setActPars(ActPars ActPars) {
        this.ActPars=ActPars;
    }

    public ActParsRBR getActParsRBR() {
        return ActParsRBR;
    }

    public void setActParsRBR(ActParsRBR ActParsRBR) {
        this.ActParsRBR=ActParsRBR;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(ActParsLBR!=null) ActParsLBR.accept(visitor);
        if(ActPars!=null) ActPars.accept(visitor);
        if(ActParsRBR!=null) ActParsRBR.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(ActParsLBR!=null) ActParsLBR.traverseTopDown(visitor);
        if(ActPars!=null) ActPars.traverseTopDown(visitor);
        if(ActParsRBR!=null) ActParsRBR.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(ActParsLBR!=null) ActParsLBR.traverseBottomUp(visitor);
        if(ActPars!=null) ActPars.traverseBottomUp(visitor);
        if(ActParsRBR!=null) ActParsRBR.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorDesignatorWithActPars(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsLBR!=null)
            buffer.append(ActParsLBR.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActPars!=null)
            buffer.append(ActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsRBR!=null)
            buffer.append(ActParsRBR.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorDesignatorWithActPars]");
        return buffer.toString();
    }
}
