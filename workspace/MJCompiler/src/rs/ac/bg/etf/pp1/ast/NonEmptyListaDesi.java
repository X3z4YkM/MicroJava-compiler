// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:31:0


package rs.ac.bg.etf.pp1.ast;

public class NonEmptyListaDesi extends ListaDesi {

    private ListaDesi ListaDesi;
    private ElemListeDesi ElemListeDesi;

    public NonEmptyListaDesi (ListaDesi ListaDesi, ElemListeDesi ElemListeDesi) {
        this.ListaDesi=ListaDesi;
        if(ListaDesi!=null) ListaDesi.setParent(this);
        this.ElemListeDesi=ElemListeDesi;
        if(ElemListeDesi!=null) ElemListeDesi.setParent(this);
    }

    public ListaDesi getListaDesi() {
        return ListaDesi;
    }

    public void setListaDesi(ListaDesi ListaDesi) {
        this.ListaDesi=ListaDesi;
    }

    public ElemListeDesi getElemListeDesi() {
        return ElemListeDesi;
    }

    public void setElemListeDesi(ElemListeDesi ElemListeDesi) {
        this.ElemListeDesi=ElemListeDesi;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ListaDesi!=null) ListaDesi.accept(visitor);
        if(ElemListeDesi!=null) ElemListeDesi.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListaDesi!=null) ListaDesi.traverseTopDown(visitor);
        if(ElemListeDesi!=null) ElemListeDesi.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListaDesi!=null) ListaDesi.traverseBottomUp(visitor);
        if(ElemListeDesi!=null) ElemListeDesi.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NonEmptyListaDesi(\n");

        if(ListaDesi!=null)
            buffer.append(ListaDesi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElemListeDesi!=null)
            buffer.append(ElemListeDesi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NonEmptyListaDesi]");
        return buffer.toString();
    }
}
