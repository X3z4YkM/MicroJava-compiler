// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:31:0


package rs.ac.bg.etf.pp1.ast;

public class DesignatorWithOptList extends Designator {

    private FakeIdent FakeIdent;
    private ListaDesi ListaDesi;

    public DesignatorWithOptList (FakeIdent FakeIdent, ListaDesi ListaDesi) {
        this.FakeIdent=FakeIdent;
        if(FakeIdent!=null) FakeIdent.setParent(this);
        this.ListaDesi=ListaDesi;
        if(ListaDesi!=null) ListaDesi.setParent(this);
    }

    public FakeIdent getFakeIdent() {
        return FakeIdent;
    }

    public void setFakeIdent(FakeIdent FakeIdent) {
        this.FakeIdent=FakeIdent;
    }

    public ListaDesi getListaDesi() {
        return ListaDesi;
    }

    public void setListaDesi(ListaDesi ListaDesi) {
        this.ListaDesi=ListaDesi;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FakeIdent!=null) FakeIdent.accept(visitor);
        if(ListaDesi!=null) ListaDesi.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FakeIdent!=null) FakeIdent.traverseTopDown(visitor);
        if(ListaDesi!=null) ListaDesi.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FakeIdent!=null) FakeIdent.traverseBottomUp(visitor);
        if(ListaDesi!=null) ListaDesi.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorWithOptList(\n");

        if(FakeIdent!=null)
            buffer.append(FakeIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ListaDesi!=null)
            buffer.append(ListaDesi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorWithOptList]");
        return buffer.toString();
    }
}
