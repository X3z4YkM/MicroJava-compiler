package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class MyStruct extends Struct
{
	public static final int Matrix = 8;
	public MyStruct(int kind)
	{
		super(kind);
	}

	public MyStruct(int kind, Struct elemType)
	{
		super(kind);

		if (kind == Matrix || kind == Array)
		{
			super.setElementType(elemType);
		}
	}
	
	public MyStruct(int kind, SymbolDataStructure members) {
		super(kind);
		setMembers(members);
	}
	
}
