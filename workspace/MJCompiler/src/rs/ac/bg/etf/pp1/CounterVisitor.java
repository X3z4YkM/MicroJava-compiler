package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {

	protected int count;
	
	public int getCount()
	{
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor
	{
		public void visit(VectorGlobalVar vectorGlobalVar)
		{
			count++;
		}
		public void visit(ScalGlobalVar scalGlobalVar)
		{
			count++;
		}
		
	}
	
	public static class VarCounter extends CounterVisitor
	{
		public void visit(VarElem1 varElem1)
		{
			count++;
		}
		public void visit(VarElem2 varElem2)
		{
			count++;
		}
	}
}
