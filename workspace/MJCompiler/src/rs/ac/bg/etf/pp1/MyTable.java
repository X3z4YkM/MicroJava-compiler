package rs.ac.bg.etf.pp1;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.concepts.Obj;

public class MyTable extends Tab{

	public static final Struct boolType = new Struct(Struct.Bool);
	public static final Struct matrixType = new Struct(MyStruct.Matrix);
	
	public static void init() {
		Tab.init();
		Tab.currentScope.addToLocals(new Obj(Obj.Type,"bool",boolType));
		Tab.currentScope.addToLocals(new Obj(Obj.Type,"matrix",matrixType));
	}
	
}
