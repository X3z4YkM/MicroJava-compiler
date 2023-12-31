package rs.ac.bg.etf.pp1;

import java.util.*;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor
{

	int printCallCount = 0;
	int readCallCount = 0;
	int nVars = 0;
	Obj currentMethod = null;
	Obj returnExprObj = null;
	boolean pozvanRetur = false;
	boolean voidMethod = false;
	int level = 0;
	int globalCount = 0;
	public boolean GlobalErrorDetetcted = false;
	public List<Obj> ConstsList = new ArrayList<>();
	public List<Obj> GlobalList = new ArrayList<>();
	public List<Obj> FormParslList = new ArrayList<>();
	public List<Obj> VarDeclList = new ArrayList<>();

	Logger log = Logger.getLogger(getClass());

	public boolean passed()
	{
		return !GlobalErrorDetetcted;
	}

	public void report_error(String message, SyntaxNode info)
	{
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());

	}

	public void report_info(String message, SyntaxNode info)
	{
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	public void visit(ProgramName programName)
	{
		programName.obj = MyTable.insert(Obj.Prog, programName.getIdent(), Tab.noType);
		MyTable.openScope();
		globalCount = 0;

	}

	public void visit(Program program)
	{
		nVars = MyTable.currentScope.getnVars();
		MyTable.chainLocalSymbols(program.getProgramName().obj);
		MyTable.closeScope();

	}
	// **************************Consts

	public int loadledConstIntVal;
	public char loadledConstCharVal;
	public int loadledConstBoolVal;
	public boolean firstConst = true;
	public Struct firstStruct;

	Obj toadd;

	public void visit(ConstElem conselem)
	{
		conselem.struct = conselem.getLiteral().struct;

		if (MyTable.find(conselem.getIdent()) != MyTable.noObj)
		{
			if (level == MyTable.find(conselem.getIdent()).getLevel())
			{
				report_error("[ERROR]: konstanta sa imenom [" + conselem.getIdent()
						+ "] vec definisana!!", conselem);
				return;
			}

		}

		if (firstConst == true)
		{
			firstStruct = conselem.struct;
			firstConst = false;
		}

		if (conselem.struct == MyTable.intType)
		{

			toadd = new Obj(Obj.Con, conselem.getIdent(), conselem.getLiteral().struct);
			toadd.setAdr(loadledConstIntVal);
			toadd.setLevel(level);
			ConstsList.add(toadd);

		} else if (conselem.struct == MyTable.charType)
		{
			toadd = new Obj(Obj.Con, conselem.getIdent(), conselem.getLiteral().struct);
			toadd.setAdr(loadledConstCharVal);
			toadd.setLevel(level);
			ConstsList.add(toadd);
		} else if (conselem.struct == MyTable.boolType)
		{
			toadd = new Obj(Obj.Con, conselem.getIdent(), conselem.getLiteral().struct);
			toadd.setAdr(loadledConstBoolVal);
			toadd.setLevel(level);
			ConstsList.add(toadd);
		}

	}

	public void visit(ConstDecl constdecl)
	{
		constdecl.struct = constdecl.getType().struct;

		Obj constNode;
		if (constdecl.struct == null)
			return;
		if (ConstsList.size() > 0)
		{
			for (Obj obj : ConstsList)
			{

				if (obj.getType().getKind() != constdecl.struct.getKind())
				{
					report_error("[ERROR]: miss match tip konstante i vrednost konstante ["
							+ obj.getName() + "-" + obj.getAdr() + "]!!!", constdecl);
					break;
				}

				constNode = MyTable.insert(Obj.Con, obj.getName(), obj.getType());
				constNode.setAdr(obj.getAdr());
				// globalCount++;
				constNode.setLevel(obj.getLevel());
				report_info(" [" + obj.getName() + "], Found const " + obj.getName() + ": "
						+ ispisCvora(obj.getType()) + ", " + constNode.getAdr() + ", "
						+ constNode.getLevel(), constdecl);
			}

			while (!ConstsList.isEmpty())
			{
				ConstsList.remove(0);
			}

		}
		firstConst = true;
	}

	public void visit(IntLiteral initLiteral)
	{
		if (savedStruct != MyTable.intType)
		{
			report_error("[ERROR]: ne poklapaju se tipovi!!", initLiteral);
			return;
		}

		initLiteral.struct = MyTable.intType;
		loadledConstIntVal = initLiteral.getValue();

	}

	public void visit(CharLiteral charLiteral)
	{
		if (savedStruct != MyTable.charType)
		{
			report_error("[ERROR]: ne poklapaju se tipovi!!", charLiteral);
			return;
		}
		charLiteral.struct = MyTable.charType;
		loadledConstCharVal = charLiteral.getValue();

	}

	public void visit(BoolLiteral boolLiteral)
	{
		if (savedStruct != MyTable.boolType)
		{
			report_error("[ERROR]: ne poklapaju se tipovi!!", boolLiteral);
			return;
		}
		boolLiteral.struct = MyTable.boolType;
		loadledConstBoolVal = boolLiteral.getValue() ? 1 : 0;

	}

	// ********************GlobalDecl

	public void visit(VarDecl varDecl)
	{

		if (GlobalList.size() > 0)
		{
			Obj VarNode;
			for (Obj obj : GlobalList)
			{

				if (obj.getType().getKind() == Struct.Array)
				{
					obj.getType().setElementType(varDecl.getType().struct);
				}

				if (obj.getType().getKind() == MyStruct.Matrix)
				{
					obj.getType().setElementType(varDecl.getType().struct);
				}

				VarNode = MyTable.insert(Obj.Var, obj.getName(), obj.getType());
				VarNode.setAdr(globalCount++);
				VarNode.setLevel(level);
				if (obj.getType().getKind() == Struct.Array)
				{
					report_info(" [" + VarNode.getName() + "], Found Var " + VarNode.getName()
							+ ": " + ispisCvora(VarNode.getType()) + ", " + VarNode.getAdr() + ", "
							+ VarNode.getLevel(), varDecl);
				} else
				{
					report_info(" [" + VarNode.getName() + "], Found Var " + VarNode.getName()
							+ ": " + ispisCvora(VarNode.getType()) + ", Adr:" + VarNode.getAdr()
							+ ", Level:" + VarNode.getLevel(), varDecl);

				}

			}

			while (!GlobalList.isEmpty())
			{
				GlobalList.remove(0);
			}
		}
	}

	public boolean globalVarChechk(String name, int level)
	{
		for (Obj obj : GlobalList)
		{
			if (obj.getName().equals(name) && obj.getLevel() == level)
				return true;
			if (MyTable.find(name) != MyTable.noObj)
			{
				if (level == MyTable.find(name).getLevel())
				{
					return true;
				}
			}
		}

		return false;
	}

	public void visit(VectorGlobalVar vectorGlobalVar)
	{

		if (globalVarChechk(vectorGlobalVar.getIdent(), level))
		{
			report_error("[ERROR]: konstanta sa imenom [" + vectorGlobalVar.getIdent()
					+ "] vec definisana!!", vectorGlobalVar);
			return;
		}

		GlobalList.add(new Obj(Obj.Var, vectorGlobalVar.getIdent(),
				new Struct(Struct.Array, savedStruct), 0, level));
	}

	public void visit(MatrixGlobalVar matrixGlobalVar)
	{

		if (globalVarChechk(matrixGlobalVar.getIdent(), level))
		{
			report_error("[ERROR]: konstanta sa imenom [" + matrixGlobalVar.getIdent()
					+ "] vec definisana!!", matrixGlobalVar);
			return;
		}

		GlobalList.add(new Obj(Obj.Var, matrixGlobalVar.getIdent(),
				new Struct(MyStruct.Matrix, savedStruct), 0, level));
	}
	public void visit(ScalGlobalVar scalGlobalVar)
	{

		if (globalVarChechk(scalGlobalVar.getIdent(), level))
		{
			report_error("[ERROR]: konstanta sa imenom [" + scalGlobalVar.getIdent()
					+ "] vec definisana!!", scalGlobalVar);
			return;
		}
		GlobalList.add(new Obj(Obj.Var, scalGlobalVar.getIdent(), savedStruct, 0, level));
	}

	// ********************Methods

	public boolean isVoid = false;
	public boolean isFormPars = false;
	public Obj currentMeth;
	public int counterFormPar = 0;
	public boolean setReturn = false;
	public Struct methodReturnType;
	public int locakVarCount;
	public boolean ErrorDetected = false;

	public void visit(YesMethodDecList yesMethodDecList)
	{

	}

	public void visit(MethodName methodName)
	{

		if (MyTable.find(methodName.getMethodname()) != MyTable.noObj)
		{
			if (MyTable.find(methodName.getMethodname()).getLevel() == level)
			{
				report_error("[ERROR]: metod with the name[" + methodName.getMethodname()
						+ "] exists!!!", methodName);
				return;
			}
		}

		methodName.obj = MyTable.insert(Obj.Meth, methodName.getMethodname(), methodReturnType);
		methodName.obj = new Obj(Obj.Meth, methodName.getMethodname(), methodReturnType);
		currentMeth = methodName.obj;
		MyTable.openScope();
		level++;
		counterFormPar = 0;
		locakVarCount = 0;
		setReturn = false;
		pozvanRetur = false;
	}

	public void visit(MethodStart methodStart)
	{
		MyTable.chainLocalSymbols(currentMeth);

	}

	public void visit(MethodEnd methodEnd)
	{
		currentMeth.setLevel(MyTable.currentScope.getnVars());
		if (pozvanRetur == false && !isVoid)
		{
			report_error("[ERROR]: return nije postavljen!!", methodEnd);
			GlobalErrorDetetcted = true;
			return;
		}
		if (returnExprObj == null && !currentMeth.getType().equals(MyTable.noType))
		{
			GlobalErrorDetetcted = true;
			report_error("[ERROR]: return mora da vrati vrednost!!", methodEnd);
			return;
		}
		if (returnExprObj != null && currentMeth.getType().equals(MyTable.noType))
		{
			GlobalErrorDetetcted = true;
			report_error("[ERROR]: return ne tereba expr!!!", methodEnd);
			return;
		}
		if (!currentMeth.getType().equals(MyTable.noType)
				&& currentMeth.getType().getKind() != returnExprObj.getType().getKind())
		{
			GlobalErrorDetetcted = true;
			report_error("[ERROR]: tip return i Type se ne poklapaju !!!", methodEnd);
			return;
		}

	}

	public void visit(MethodDecl methodDecl)
	{

		String mName = methodDecl.getMethodName().getMethodname();

		if (!mName.equals("main"))
		{
			report_error("[ERROR]: method [" + mName + "] should be called 'main'", methodDecl);
			return;
		}
		report_info(currentMeth.getName() + " kind " + currentMeth.getKind() + " typ "
				+ currentMeth.getType().getKind() + " adr " + currentMeth.getAdr() + " lev "
				+ currentMeth.getLevel(), methodDecl);

		// currentMeth = MyTable.noObj;
		MyTable.closeScope();
		level--;

	}

	public void visit(ReturnNoExpr returnNoExpr)
	{
		returnExprObj = null;
		pozvanRetur = true;
		setReturn = false;
	}

	public void visit(ReturnExpr returnExpr)
	{
		returnExprObj = returnExpr.getExpr().obj;
		pozvanRetur = true;
		setReturn = true;

	}

	public void visit(EPFormParWithBrackets ePFormParWithBrackets)
	{
		Type type = ePFormParWithBrackets.getType();
		String name = ePFormParWithBrackets.getIdent();

		if (MyTable.find(name) != MyTable.noObj)
		{
			if (MyTable.find(name).getLevel() == level)
			{
				report_error("[ERROR]: parameters in form have same name [" + name + "]",
						ePFormParWithBrackets);
				return;
			}
		}

		Obj formParsObj;

		Struct inerstruct = new Struct(Struct.Array, type.struct);
		Struct outer = new Struct(type.struct.getKind());
		outer.setElementType(inerstruct);

		formParsObj = MyTable.insert(Obj.Var, name, inerstruct);
		formParsObj.setLevel(level);
		formParsObj.setAdr(counterFormPar++);
		FormParslList.add(formParsObj);
		++locakVarCount;
		report_info(
				" [" + formParsObj.getName() + "], Found Var  " + formParsObj.getName() + ": "
						+ ispisCvora(formParsObj.getType()) + ", Adr: " + formParsObj.getAdr()
						+ ", Level: " + formParsObj.getLevel() + " in method [main]",
				ePFormParWithBrackets);

	}

	public void visit(EPFormParMatrix ePFormParMatrix)
	{
		Type type = ePFormParMatrix.getType();
		String name = ePFormParMatrix.getIdent();

		if (MyTable.find(name) != MyTable.noObj)
		{
			if (MyTable.find(name).getLevel() == level)
			{
				report_error("[ERROR]: parameters in form have same name [" + name + "]",
						ePFormParMatrix);
				return;
			}
		}

		Obj formParsObj;

		Struct inerstruct = new Struct(MyStruct.Matrix, type.struct);
		Struct outer = new Struct(type.struct.getKind());
		outer.setElementType(inerstruct);

		formParsObj = MyTable.insert(Obj.Var, name, inerstruct);
		formParsObj.setLevel(level);
		formParsObj.setAdr(counterFormPar++);
		FormParslList.add(formParsObj);
		++locakVarCount;
		report_info(
				" [" + formParsObj.getName() + "], Found Var  " + formParsObj.getName() + ": "
						+ ispisCvora(formParsObj.getType()) + ", Adr: " + formParsObj.getAdr()
						+ ", Level: " + formParsObj.getLevel() + " in method [main]",
				ePFormParMatrix);

	}

	public void visit(EPFormParNoBrackets ePFormParNoBrackets)
	{
		Type type = ePFormParNoBrackets.getType();
		String name = ePFormParNoBrackets.getIdent();

		if (MyTable.find(name) != MyTable.noObj)
		{
			if (MyTable.find(name).getLevel() == level)
			{
				report_error("[ERROR]: parameters in form have same name [" + name + "]",
						ePFormParNoBrackets);
				return;
			}
		}

		Obj formParsObj;

		formParsObj = MyTable.insert(Obj.Var, name, type.struct);
		formParsObj.setLevel(level);
		formParsObj.setAdr(counterFormPar++);
		FormParslList.add(formParsObj);
		++locakVarCount;
		report_info(
				" [" + formParsObj.getName() + "], Found Var  " + formParsObj.getName() + ": "
						+ ispisCvora(formParsObj.getType()) + ", Adrr: " + formParsObj.getAdr()
						+ ", Level: " + formParsObj.getLevel() + " in method [main]",
				ePFormParNoBrackets);
	}

	public void visit(YesFormPars yesFormPars)
	{
		isFormPars = true;

	}

	public void visit(NoFormPars noFormPars)
	{
		isFormPars = false;

	}

	public void visit(NonVoidReturnType nonVoidReturnType)
	{
		methodReturnType = nonVoidReturnType.getType().struct;
		isVoid = false;
	}

	public void visit(VoidReturnType voidReturnType)
	{
		methodReturnType = MyTable.noType;
		isVoid = true;
	}

	public void visit(VarDeclElemWithVarList varDeclElemWithVarList)
	{

		if (VarDeclList.size() > 0)
		{
			Obj VarNode;
			for (Obj obj : VarDeclList)
			{

				if (obj.getType().getKind() == Struct.Array)
				{
					obj.getType().setElementType(varDeclElemWithVarList.getType().struct);
				}
				if (obj.getType().getKind() == MyStruct.Matrix)
				{
					obj.getType().setElementType(varDeclElemWithVarList.getType().struct);
				}

				VarNode = MyTable.insert(Obj.Var, obj.getName(), obj.getType());
				VarNode.setAdr(counterFormPar++);
				VarNode.setLevel(level);
				++locakVarCount;
				if (obj.getType().getKind() == Struct.Array)
				{
					report_info(
							" [" + VarNode.getName() + "], Found Var  " + VarNode.getName() + ": "
									+ ispisCvora(VarNode.getType()) + ", Adr: " + VarNode.getAdr()
									+ ", Level: " + VarNode.getLevel() + " in method [main]",
							varDeclElemWithVarList);
				} else
				{
					report_info(" [" + VarNode.getName() + "], Found Var " + VarNode.getName()
							+ ": " + ispisCvora(VarNode.getType()) + ", Adr: " + VarNode.getAdr()
							+ ", Level: " + VarNode.getLevel(), varDeclElemWithVarList);

				}

			}

			while (!VarDeclList.isEmpty())
			{
				VarDeclList.remove(0);
			}

		}

	}

	public void visit(VarElem1 varElem1)
	{
		if (VarDeclChechk(varElem1.getIdent(), level))
		{
			report_error(
					"[ERROR]: lokalna sa imenom [" + varElem1.getIdent() + "] vec definisana!!",
					varElem1);
			return;
		}

		VarDeclList.add(new Obj(Obj.Var, varElem1.getIdent(), new Struct(Struct.Array, savedStruct),
				0, level));
	}

	public void visit(VarElem2 varElem2)
	{

		if (VarDeclChechk(varElem2.getIdent(), level))
		{
			report_error(
					"[ERROR]: lokalna sa imenom [" + varElem2.getIdent() + "] vec definisana!!",
					varElem2);
			return;
		}
		VarDeclList.add(new Obj(Obj.Var, varElem2.getIdent(), savedStruct, 0, level));

	}

	public void visit(VarElem3 varElem3)
	{
		if (VarDeclChechk(varElem3.getIdent(), level))
		{
			report_error(
					"[ERROR]: lokalna sa imenom [" + varElem3.getIdent() + "] vec definisana!!",
					varElem3);
			return;
		}

		VarDeclList.add(new Obj(Obj.Var, varElem3.getIdent(),
				new Struct(MyStruct.Matrix, savedStruct), 0, level));
	}

	public boolean VarDeclChechk(String name, int level)
	{
		for (Obj obj : GlobalList)
		{
			if (obj.getName().equals(name) && obj.getLevel() == level)
				return true;
			if (MyTable.find(name) != MyTable.noObj)
			{
				if (level == MyTable.find(name).getLevel())
				{
					return true;
				}
			}
		}

		for (Obj obj : VarDeclList)
		{
			if (obj.getName().equals(name) && obj.getLevel() == level)
				return true;
			if (MyTable.find(name) != MyTable.noObj)
			{
				if (level == MyTable.find(name).getLevel())
				{
					return true;
				}
			}
		}

		return false;
	}

	// **************DesiStmt
	public void visit(DesiStmtINC desiStmtINC)
	{
		if (desierrordetect)
		{

			desierrordetect = false;
			return;
		}

		Struct desiT = desiStmtINC.getDesignator().obj.getType().getElemType() == null
				? desiStmtINC.getDesignator().obj.getType()
				: desiStmtINC.getDesignator().obj.getType().getElemType();
		if (!desiT.equals(MyTable.intType))
		{

			if (!desiT.equals(MyTable.noType))
			{
				if (!(desiT.equals(MyTable.intType)) || !(desiT.getKind() == Struct.Array
						&& desiT.getElemType().equals(MyTable.intType)))
				{
					report_error("[ERROR]:" + "__[" + desiStmtINC.getDesignator().obj.getName()
							+ "]__" + " Pre operatora ++ se mora nalaziti tip int ", desiStmtINC);
				}

				if (!(desiStmtINC.getDesignator().obj.getKind() == Obj.Var))
				{
					report_error(" [ERROR] :" + "__[" + desiStmtINC.getDesignator().obj.getName()
							+ "]__"
							+ " Sa leve strane dodele kod operatora ++ se mora naci promenljiva ili element niza",
							desiStmtINC);
				}
			}
		}

	}

	public void visit(DesiStmtDEC desiStmtDEC)
	{
		if (desierrordetect)
		{

			desierrordetect = false;
			return;
		}

		Struct desiT = desiStmtDEC.getDesignator().obj.getType().getElemType() == null
				? desiStmtDEC.getDesignator().obj.getType()
				: desiStmtDEC.getDesignator().obj.getType().getElemType();
		if (!desiT.equals(MyTable.intType))
		{

			if (!desiT.equals(MyTable.noType))
			{
				if (!(desiT.equals(MyTable.intType)) || !(desiT.getKind() == Struct.Array
						&& desiT.getElemType().equals(MyTable.intType)))
				{
					report_error("[ERROR]:" + "__[" + desiStmtDEC.getDesignator().obj.getName()
							+ "]__" + " Pre operatora ++ se mora nalaziti tip int ", desiStmtDEC);
				}

				if (!(desiStmtDEC.getDesignator().obj.getKind() == Obj.Var))
				{
					report_error(" [ERROR] :" + "__[" + desiStmtDEC.getDesignator().obj.getName()
							+ "]__"
							+ " Sa leve strane dodele kod operatora ++ se mora naci promenljiva ili element niza",
							desiStmtDEC);
				}
			}
		}

	}

	// **************************Designator

	public void visit(PrintStmt printStmt)
	{
		Obj expr = printStmt.getExpr().obj;
		Struct exprStruct = expr.getType().getElemType() == null
				? expr.getType()
				: expr.getType().getElemType();

		if (!exprStruct.equals(MyTable.intType) && !exprStruct.equals(MyTable.charType)
				&& !exprStruct.equals(MyTable.boolType))
		{
			report_error("[ERROR]: expr u printu nije odg tipa!!", printStmt);
			GlobalErrorDetetcted = true;
			printStmt.obj = MyTable.noObj;
			return;
		} else
		{
			printStmt.obj = expr;
			report_info("[INFO STATEMENT]: printing!!!", printStmt);
			printCallCount++;
		}

	}

	public void visit(ReadStmt readStmt)
	{
		Obj desi = readStmt.getDesignator().obj;
		if (desi.getKind() != Obj.Elem && desi.getKind() != Obj.Var && desi.getKind() != Obj.Fld)
		{
			report_error("[ERROR]: Designator mora biti prom, elem niza ili polje objekta!!",
					readStmt);
			GlobalErrorDetetcted = true;
			readStmt.obj = MyTable.noObj;
			return;
		}
		Struct structT = desi.getType().getElemType() == null
				? desi.getType()
				: desi.getType().getElemType();
		if (!structT.equals(MyTable.intType) && !structT.equals(MyTable.charType)
				&& !structT.equals(MyTable.boolType))
		{
			report_error("[ERROR]: Designator mora biti ili int ili char ili bool !!", readStmt);
			GlobalErrorDetetcted = true;
			readStmt.obj = MyTable.noObj;
			return;
		}
		readStmt.obj = desi;
		report_info("[INFO STATEMENT]: reading!!!", readStmt);
		readCallCount++;
	}

	public void visit(SinglStmComaYes singlStmComaYes)
	{

	}

	public void visit(SinglStmComaNo ninglStmComaNo)
	{

	}

	public List<Obj> ElemDesiRek = new ArrayList<>();
	public boolean desierrordetect = false;
	Obj identO;

	public void visit(OneSingleStatement oneSingleStatement)
	{
		desierrordetect = false;
	}

	public void visit(DesiStmtAssignop desiStmtAssignop)
	{

		if (desiStmtAssignop.getDesignator().obj.getKind() == Obj.NO_VALUE
				|| desiStmtAssignop.getExprEP().obj.getKind() == Obj.NO_VALUE)
		{
			return;
		}

		Obj desiObj = desiStmtAssignop.getDesignator().obj;
		Obj expreObj = desiStmtAssignop.getExprEP().obj;
		Struct desiStruct = desiObj.getType();
		Struct expreStruct = expreObj.getType();

		Obj ident = MyTable.find(desiObj.getName());
		if (ident.equals(MyTable.noObj))
		{
			report_error("[ERROR]: unresolved variable [" + desiObj.getName() + "]",
					desiStmtAssignop);
			desierrordetect = true;
			GlobalErrorDetetcted = true;
			return;
		}
		if (ident.getKind() == Obj.Con)
		{
			report_error("[ERROR]: Cons variable [" + desiObj.getName() + "]", desiStmtAssignop);
			desierrordetect = true;
			GlobalErrorDetetcted = true;
			return;
		} else if (ident.getType().getKind() != Struct.Array
				&& ident.getType().getKind() != MyStruct.Matrix && newTypeLER == true)
		{
			report_error("[ERROR]: __" + ident.getName() + "__ nije tip niz[]/mat[][] !!",
					desiStmtAssignop);
			desierrordetect = true;
			GlobalErrorDetetcted = true;
			return;

		} else if (desiStruct.getKind() == Struct.Array && expreStruct.getKind() == Struct.Array)
		{
			// niz = niz
			if (desiStruct.getElemType().getKind() == expreStruct.getElemType().getKind())
			{
				// niz[int,char,bool] = niz[int,char,bool]
				report_info("[STATEMENT INFO]: pronadjena dodela  [niz: " + desiObj.getName()
						+ " , niz: " + expreObj.getName() + "]!!", desiStmtAssignop);
			} else
			{
				// greska tipovi se ne poklapaju
				report_error("[ERROR]: tipovi ne suglasni [niz: " + desiObj.getName() + " , niz: "
						+ expreObj.getName() + "]!!", desiStmtAssignop);
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			}
		} else// niz = int ili niz = char
		if (desiStruct.getKind() == Struct.Array && expreStruct.getKind() == MyStruct.Matrix)
		{
			if (desiStruct.getElemType().getKind() == expreStruct.getElemType().getKind())
			{
				report_info("[STATEMENT INFO]: pronadjena dodela  [niz: " + desiObj.getName()
						+ " , matrix: " + expreObj.getName() + "]!!", desiStmtAssignop);
			} else
			{
				report_error("[ERROR]: tipovi ne suglasni [niz: " + desiObj.getName()
						+ " , matrix: " + expreObj.getName() + "]!!", desiStmtAssignop);
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			}

		} else if (desiStruct.getKind() == Struct.Array && expreStruct.getKind() != MyStruct.Matrix
				&& expreStruct.getKind() != Struct.Array)
		{
			if (desiStruct.getElemType().getKind() == expreStruct.getKind())
			{
				report_info("[STATEMENT INFO]: pronadjena dodela  [niz: " + desiObj.getName()
						+ " , prom: " + expreObj.getName() + "]!!", desiStmtAssignop);
			} else
			{
				report_error("[ERROR]: tipovi ne suglasni [niz: " + desiObj.getName() + " , prom: "
						+ expreObj.getName() + "]!!", desiStmtAssignop);
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			}

		} else if (desiStruct.getKind() == MyStruct.Matrix
				&& expreStruct.getKind() == MyStruct.Matrix)
		{
			if (desiStruct.getElemType().getKind() == expreStruct.getElemType().getKind())
			{
				report_info("[STATEMENT INFO]: pronadjena dodela  [matrix: " + desiObj.getName()
						+ " , matrix: " + expreObj.getName() + "]!!", desiStmtAssignop);
			} else
			{
				report_error("[ERROR]: tipovi ne suglasni [matrix: " + desiObj.getName()
						+ " , matrix: " + expreObj.getName() + "]!!", desiStmtAssignop);
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			}

		} else if (desiStruct.getKind() == MyStruct.Matrix && expreStruct.getKind() == Struct.Array)
		{
			if (desiStruct.getElemType().getKind() == expreStruct.getElemType().getKind())
			{
				report_info(
						"[STATEMENT INFO]: pronadjena dodela  [matrix: " + desiObj.getName()
								+ "[][]" + " , niz: " + expreObj.getName() + "]!!",
						desiStmtAssignop);
			} else
			{
				report_error("[ERROR]: tipovi ne suglasni [matrix: " + desiObj.getName()
						+ " , niz: " + expreObj.getName() + "]!!", desiStmtAssignop);
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			}

		} else if (desiStruct.getKind() == MyStruct.Matrix && expreStruct.getKind() != Struct.Array
				&& expreStruct.getKind() != MyStruct.Matrix)
		{
			if (desiStruct.getElemType().getKind() == expreStruct.getKind())
			{
				report_info("[STATEMENT INFO]: pronadjena dodela  [matrix: " + desiObj.getName()
						+ "["
						+ ((NonEmptyListaDesi) ((DesignatorWithOptList) desiStmtAssignop
								.getDesignator()).getListaDesi()).getListaDesi().obj.getName()
						+ "]["
						+ ((DesignatorWithOptList) desiStmtAssignop.getDesignator())
								.getListaDesi().obj.getName()
						+ "] , prom: " + expreObj.getName() + "]!!", desiStmtAssignop);
			} else
			{
				report_error("[ERROR]: tipovi ne suglasni [matrix: " + desiObj.getName()
						+ " , prom: " + expreObj.getName() + "]!!", desiStmtAssignop);
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			}
		} else if (desiStruct.getKind() != MyStruct.Matrix && desiStruct.getKind() != Struct.Array
				&& expreStruct.getKind() == Struct.Array)
		{
			if (desiStruct.getKind() == expreStruct.getElemType().getKind())
			{
				report_info("[STATEMENT INFO]: pronadjena dodela  [prom: " + desiObj.getName()
						+ " , niz: " + expreObj.getName() + "]!!", desiStmtAssignop);
			} else
			{
				report_error("[ERROR]: tipovi ne suglasni [prom: " + desiObj.getName() + " , niz: "
						+ expreObj.getName() + "]!!", desiStmtAssignop);
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			}
		} else if (desiStruct.getKind() != MyStruct.Matrix && desiStruct.getKind() != Struct.Array
				&& expreStruct.getKind() == MyStruct.Matrix)
		{
			if (desiStruct.getKind() == expreStruct.getElemType().getKind())
			{
				report_info("[STATEMENT INFO]: pronadjena dodela  [prom: " + desiObj.getName()
						+ " , matrix: " + expreObj.getName() + "[][]]!!", desiStmtAssignop);
			} else
			{
				report_error("[ERROR]: tipovi ne suglasni [prom: " + desiObj.getName()
						+ " , matrix: " + expreObj.getName() + "]!!", desiStmtAssignop);
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			}
		} else if (desiStruct.getKind() != MyStruct.Matrix && desiStruct.getKind() != Struct.Array
				&& expreStruct.getKind() != MyStruct.Matrix
				&& expreStruct.getKind() != Struct.Array)
		{
			if (expreStruct != null && (desiStruct.getKind() == expreStruct.getKind()))
			{
				report_info("[STATEMENT INFO]: pronadjena drodela  [prom: " + desiObj.getName()
						+ " , prom: " + expreObj.getName() + "]!!", desiStmtAssignop);
			} else
			{
				report_error("[ERROR]: tipovi ne suglasni [prom: " + desiObj.getName() + " , prom: "
						+ expreObj.getName() + "]!!", desiStmtAssignop);
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			}
		} else
		{
			report_error("[ERROR]: tipovi ne suglasni [?: " + desiObj.getName() + " , ?: "
					+ expreObj.getName() + "]!!", desiStmtAssignop);
			desierrordetect = true;
			GlobalErrorDetetcted = true;
			return;
		}
		newTypeLER = false;
	}

	public void visit(ExprEPExpr exprEPExpr)
	{
		exprEPExpr.obj = exprEPExpr.getExpr().obj;
	}

	public boolean canBeelemSingle = false;

	public void visi(DesiStmtAssignop desiStmtAssignop)
	{
		canBeelemSingle = true;
	}

	public void visit(FakeIdent fakeIdent)
	{
		fakeIdent.obj = MyTable.find(fakeIdent.getIdent());
	}

	public void visit(DesignatorWithOptList designatorWithOptList)
	{
		String name = designatorWithOptList.getFakeIdent().getIdent();

		if (designatorWithOptList.getListaDesi() instanceof EmptyDesiList)
		{
			SyntaxNode parent = designatorWithOptList.getParent();
			identO = MyTable.find(name);

			if (identO == MyTable.noObj || identO.getKind() == Obj.Type
					|| identO.getKind() == Obj.Prog)
			{
				designatorWithOptList.obj = new Obj(Obj.NO_VALUE, name, MyTable.noType);
				report_error("[ERROR]: unresolved variable [" + name + "]", designatorWithOptList);
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			} else
			// if(identO.getType().getKind() == Struct.Array &&
			// identO.getType().getElemType()!=null && !(parent instanceof
			// DesiStmtAssignop))
			// {
			// designatorWithOptList.obj = new Obj(Obj.NO_VALUE ,
			// designatorWithOptList.getIdent(), MyTable.noType);
			// report_error("[ERROR]: unresolved variable
			// ["+designatorWithOptList.getIdent()+"]", designatorWithOptList);
			// desierrordetect= true;
			// GlobalErrorDetetcted = true;
			// return
			//
			// }else
			{
				designatorWithOptList.obj = identO;

				if (identO.getKind() == Obj.Var)
				{
					report_info("[STATEMENT INFO]: Found Var " + name + ": "
							+ ispisCvora(designatorWithOptList.obj.getType()) + ", Adr: "
							+ designatorWithOptList.obj.getAdr() + ", Level: "
							+ designatorWithOptList.obj.getLevel(), designatorWithOptList);
				} else if (identO.getKind() == Obj.Con)
				{
					report_info("[STATEMENT INFO]: Found Con " + name + ": "
							+ ispisCvora(designatorWithOptList.obj.getType()) + ", Adr: "
							+ designatorWithOptList.obj.getAdr() + ", Level: "
							+ designatorWithOptList.obj.getLevel(), designatorWithOptList);
				}
			}
			canBeelemSingle = false;
		} else if (designatorWithOptList.getListaDesi() instanceof NonEmptyListaDesi)
		{
			identO = MyTable.find(name);
			if (identO == MyTable.noObj || identO.getKind() == Obj.Type
					|| identO.getKind() == Obj.Prog)
			{
				designatorWithOptList.obj = new Obj(Obj.NO_VALUE, name, MyTable.noType);
				report_error("[ERROR]: unresolved variable [" + name + "]", designatorWithOptList);
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			}
			Struct expr = designatorWithOptList.getListaDesi().obj.getType().getElemType() == null
					? designatorWithOptList.getListaDesi().obj.getType()
					: designatorWithOptList.getListaDesi().obj.getType().getElemType();

			if (!expr.equals(MyTable.intType))
			{
				report_error("[ERROR] vrednost u " + identO.getName() + "[] mora biti int tipa",
						designatorWithOptList);
				designatorWithOptList.obj = MyTable.noObj;
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			}
			if (identO.getType().getElemType() == null)
			{
				report_error("[ERROR] [" + identO.getName() + "] nije tipa niz!!",
						designatorWithOptList);
				designatorWithOptList.obj = MyTable.noObj;
				desierrordetect = true;
				GlobalErrorDetetcted = true;
				return;
			}

			if (identO.getType().getElemType().getKind() == expr.getKind()
					&& expr.equals(MyTable.intType) && (identO.getType().getKind() == Struct.Array
							|| identO.getType().getKind() == MyStruct.Matrix))
			{

				report_info("[STATEMENT INFO]: Found Elem [" + name + "]", designatorWithOptList);
				designatorWithOptList.obj = new Obj(Obj.Elem, name, identO.getType());
			} else if (identO.getType().getKind() == expr.getKind() && expr.equals(MyTable.intType)
					&& (identO.getType().getKind() != Struct.Array
							|| identO.getType().getKind() != MyStruct.Matrix))
			{
				report_info("[STATEMENT INFO]: Found Elem " + name + "", designatorWithOptList);
				designatorWithOptList.obj = new Obj(Obj.Elem, name, MyTable.intType);

			} else if (identO.getType().getElemType().getKind() != expr.getKind()
					&& expr.equals(MyTable.intType) && (identO.getType().getKind() == Struct.Array
							|| identO.getType().getKind() == MyStruct.Matrix))
			{
				report_info("[STATEMENT INFO]: Found Elem [" + name + "]", designatorWithOptList);
				designatorWithOptList.obj = new Obj(Obj.Elem, name, identO.getType().getElemType());

			} else
			{

				report_error("[ERROR] __" + identO.getName() + "__ nije tipa int[]!!!",
						designatorWithOptList);

				GlobalErrorDetetcted = true;
				desierrordetect = true;
				designatorWithOptList.obj = MyTable.noObj;
			}

		}

		// report_info(""+designatorWithOptList.getIdent()+"
		// "+ispisCvora(designatorWithOptList.obj.getType()),
		// designatorWithOptList);

	}

	public void visit(NonEmptyListaDesi nonEmptyListaDesi)
	{
		nonEmptyListaDesi.obj = nonEmptyListaDesi.getElemListeDesi().obj;
	}

	public void visit(DesignatorLExprR designatorLExprR)
	{

		designatorLExprR.obj = designatorLExprR.getExpr().obj;
	}

	public List<Obj> nonEmptDesiList = new ArrayList<Obj>();
	public boolean conInAssList = false;

	public void visit(DesignatorsOPTandList designatorsOPTandList)
	{

	}

	public void visit(DesignatorElemCommaDesi designatorElemCommaDesi)
	{
		if (designatorElemCommaDesi.getDesignatorOPT() instanceof DesignatorOPTYes)
		{
			if (designatorElemCommaDesi.getDesignatorOPT().obj.getKind() == Obj.Con)
			{
				report_error("[ERROR]: Constu: ["
						+ designatorElemCommaDesi.getDesignatorOPT().obj.getName()
						+ "] se ne moze pridodaati vrednost", designatorElemCommaDesi);
				conInAssList = true;
				GlobalErrorDetetcted = true;
				return;
			}
			nonEmptDesiList.add(designatorElemCommaDesi.getDesignatorOPT().obj);
		}
	}

	public void visit(DesignatorOPTYes designatorOPTYes)
	{
		designatorOPTYes.obj = designatorOPTYes.getDesignator().obj;
	}

	// *******************Expr

	public boolean Addition = false;

	public void visit(TermExpr termExpr)
	{
		termExpr.obj = termExpr.getTerm().obj;
	}

	public void visit(MinusTermExpr minusTermExpr)
	{

		if (!minusTermExpr.getTerm().obj.getType().equals(MyTable.intType))
		{

			minusTermExpr.obj = MyTable.noObj;
			if (ErrorDetected)
			{
				report_error("[ERROR] tip nakon - mora biti int!!", minusTermExpr);
			}
			ErrorDetected = false;
		} else
		{
			minusTermExpr.obj = minusTermExpr.getTerm().obj;
		}

	}

	public void visit(AddopExpr addopExpr)
	{
		Struct expr = addopExpr.getExpr().obj.getType().getElemType() == null
				? addopExpr.getExpr().obj.getType()
				: addopExpr.getExpr().obj.getType().getElemType();
		Struct term = addopExpr.getTerm().obj.getType().getElemType() == null
				? addopExpr.getTerm().obj.getType()
				: addopExpr.getTerm().obj.getType().getElemType();

		if (expr.getKind() == Struct.Int && term.getKind() == Struct.Int)
		{
			addopExpr.obj = new Obj(Obj.Var, "", MyTable.intType);

		} else if ((expr.getKind() == Struct.Array) && expr.getElemType().equals(MyTable.intType)
				&& (term.getKind() == Struct.Array) && term.getElemType().equals(MyTable.intType))
		{
			addopExpr.obj = new Obj(Obj.Var, "", new Struct(Struct.Array, new Struct(Struct.Int)));

		} else
		{
			Addop addop = addopExpr.getAddop();
			if (addop instanceof PlusAddop)
			{
				report_error("[ERROR]: Semnatic error kod operator + mora biti nad tipom int",
						addopExpr);
				addopExpr.obj = MyTable.noObj;
			}
			if (addop instanceof MinusAddop)
			{
				report_error("[ERROR]: Semnatic error kod operator - mora biti nad tipom int",
						addopExpr);
				addopExpr.obj = MyTable.noObj;
			}
			addopExpr.obj = MyTable.noObj;
		}

		ErrorDetected = false;

	}

	// **************Term

	public void visit(FactorTerm factorTerm)
	{

		if (factorTerm.getFactor() instanceof FactorDesignator)
		{
			factoVal = nameFD;
		}

		Struct factorT = factorTerm.getFactor().struct;

		if (factorT == null)
		{
			factorTerm.obj = MyTable.noObj;
			ErrorDetected = true;
			GlobalErrorDetetcted = true;
			return;
		}

		if (factorT == MyTable.intType || factorT == MyTable.charType
				|| factorT == MyTable.boolType)
		{
			factorTerm.obj = new Obj(Obj.Con, factoVal, factorT);
			if (factorT.getKind() == Struct.Int)
			{
				factorTerm.obj.setAdr(loadedConstInt);
			} else if (factorT.getKind() == Struct.Char)
			{
				factorTerm.obj.setAdr(loadedConstChr);
			} else
			{
				factorTerm.obj.setAdr(loadedConstBool);
			}
		} else if (factorT.getKind() == Struct.Array)
			factorTerm.obj = new Obj(Obj.Var, factoVal, factorT);
		else if (factorT.getKind() == MyStruct.Matrix)
			factorTerm.obj = new Obj(Obj.Var, factoVal, factorT);
		if (factorTerm.obj == null)
		{
			System.out.println(factorTerm);
		}
		if (factorTerm.obj.getType() == MyTable.noType)
		{
			GlobalErrorDetetcted = true;
			ErrorDetected = false;
		}
		factoVal = "";
	}

	public void visit(MulopTerm mulopTerm)
	{

		Obj term = mulopTerm.getTerm().obj;
		Struct termT = term.getType();
		Struct factT = mulopTerm.getFactor().struct;

		if (termT.equals(MyTable.intType) && factT.equals(MyTable.intType))
		{
			mulopTerm.obj = new Obj(Obj.Var, "", MyTable.intType);

		} else if (termT.getKind() == Struct.Array && termT.getElemType().getKind() == Struct.Int
				&& factT.getKind() == Struct.Array && factT.getElemType().getKind() == Struct.Int)
		{
			mulopTerm.obj = new Obj(Obj.Var, "", MyTable.intType);
		} else if (termT.getKind() == Struct.Array && termT.getElemType().getKind() == Struct.Int
				&& factT.getKind() == Struct.Int)
		{
			mulopTerm.obj = new Obj(Obj.Var, "", new Struct(Struct.Array, new Struct(Struct.Int)));
		} else if (termT.getKind() == MyStruct.Matrix && termT.getElemType().getKind() == Struct.Int
				&& factT.getKind() == MyStruct.Matrix
				&& factT.getElemType().getKind() == Struct.Int)
		{
			mulopTerm.obj = new Obj(Obj.Var, "", MyTable.intType);
		} else if (termT.getKind() == MyStruct.Matrix && termT.getElemType().getKind() == Struct.Int
				&& factT.getKind() == Struct.Int)
		{
			mulopTerm.obj = new Obj(Obj.Var, "", new Struct(Struct.Array, new Struct(Struct.Int)));
		}

		else
		{
			if (factT != MyTable.noType)
			{

				if (mulopTerm.getMulop() instanceof MulMulop)
					report_error("[ERROR]: Semnatic error kod operator * mora biti nad tipom int",
							mulopTerm);

				if (mulopTerm.getMulop() instanceof DivMulop)
					report_error("[ERROR]: Semnatic error kod operator / mora biti nad tipom int",
							mulopTerm);

				if (mulopTerm.getMulop() instanceof ModMulop)
					report_error("[ERROR]: Semnatic error kod operator % mora biti nad tipom int",
							mulopTerm);
			}

			mulopTerm.obj = MyTable.noObj;
			ErrorDetected = false;
			factoVal = "";

		}

	}
	// **************Factor

	public boolean newTypeLER = false;
	String nameFD;

	public void visit(FactorDesignator factorDesignator)
	{
		if (factorDesignator.getDesignator().obj.equals(MyTable.noObj))
		{
			return;
		}
		Struct s = factorDesignator.getDesignator().obj.getType();
		nameFD = factorDesignator.getDesignator().obj.getName();
		factorDesignator.struct = factorDesignator.getDesignator().obj.getType();
	}

	public String factoVal = "";
	public int loadedConstInt;
	public int loadedConstChr;
	public int loadedConstBool;

	public void visit(IntFactor intFactor)
	{
		intFactor.struct = MyTable.intType;
		factoVal = "" + intFactor.getValue();
		loadedConstInt = intFactor.getValue();
	}

	public void visit(CharFactor charFactor)
	{
		charFactor.struct = MyTable.charType;
		factoVal = "" + charFactor.getValue();
		loadedConstChr = charFactor.getValue();
	}

	public void visit(BoolFactor boolFactor)
	{
		boolFactor.struct = MyTable.boolType;
		factoVal = "" + (boolFactor.getValue() ? 1 : 0);
		loadedConstBool = (boolFactor.getValue() ? 1 : 0);
	}

	String temp = "";

	public void visit(NewVectorExprFactor newVectorExprFactor)
	{
		/*
		 * struct ce imati: kind=Array elemType = Type.struct sto ce biti Int
		 * 
		 */
		if (newVectorExprFactor.getExpr().obj.getType().getKind() != Struct.Int)
		{
			report_error("[ERROR]: expresion must be and int type", newVectorExprFactor);
			ErrorDetected = true;
			GlobalErrorDetetcted = true;
			return;
		} else if (!newVectorExprFactor.getType().struct.equals(MyTable.intType)
				&& !newVectorExprFactor.getType().struct.equals(MyTable.charType))
		{
			report_error("[ERROR]: Type must be int or char", newVectorExprFactor);
			ErrorDetected = true;
			GlobalErrorDetetcted = true;
			return;
		}
		temp = factoVal;
		factoVal = "new Type[" + temp + "]";
		newVectorExprFactor.struct = new Struct(Struct.Array, newVectorExprFactor.getType().struct);
		newTypeLER = true;
	}

	public void visit(DelimitedFactor delimitedFactor)
	{
		delimitedFactor.struct = delimitedFactor.getExpr().obj.getType();

	}

	public void visit(NewMatrixFactor newMatrixFactor)
	{
		/*
		 * struct ce imati: kind=Array elemType = Type.struct sto ce biti Int
		 * 
		 */
		if (newMatrixFactor.getExpr().obj.getType().getKind() != Struct.Int
				|| newMatrixFactor.getExpr1().obj.getType().getKind() != Struct.Int)
		{
			report_error("[ERROR]: expresion must be and int type", newMatrixFactor);
			ErrorDetected = true;
			GlobalErrorDetetcted = true;
			return;
		} else if (!newMatrixFactor.getType().struct.equals(MyTable.intType)
				&& !newMatrixFactor.getType().struct.equals(MyTable.charType))
		{
			report_error("[ERROR]: Type must be int or char", newMatrixFactor);
			ErrorDetected = true;
			GlobalErrorDetetcted = true;
			return;
		}
		temp = factoVal;
		factoVal = "new Type[" + temp + "][" + temp + "]";
		newMatrixFactor.struct = new MyStruct(MyStruct.Matrix, newMatrixFactor.getType().struct);
		newTypeLER = true;
	}

	// **************************Type

	Struct savedStruct = null;

	public void visit(Type type)
	{
		Obj typeNode = MyTable.find(type.getIdent());

		if (typeNode == MyTable.noObj)
		{
			report_error("[ERROR]: nepostoji tip sa imenom " + type.getIdent() + " u tabeli!!",
					type);
			type.struct = MyTable.noType;
		} else
		{
			type.struct = typeNode.getType();
			savedStruct = typeNode.getType();
		}
	}

	// ********* za testiranje samo obrisati
	String ispisCvora(Struct structToVisit)
	{

		StringBuilder msg = new StringBuilder();

		switch (structToVisit.getKind())
		{
			case Struct.None :
				msg.append("notype");
				break;
			case Struct.Int :
				msg.append("int");
				break;
			case Struct.Bool :
				msg.append("bool");
				break;
			case Struct.Char :
				msg.append("char");
				break;
			case Struct.Array :
				msg.append("Arr of ");
				switch (structToVisit.getElemType().getKind())
				{
					case Struct.None :
						msg.append("notype");
						break;
					case Struct.Int :
						msg.append("int");
						break;
					case Struct.Bool :
						msg.append("bool");
						break;
					case Struct.Char :
						msg.append("char");
						break;
					case Struct.Class :
						msg.append("Class");
						break;
				}
				break;
			case Struct.Class :
				msg.append("Class [");
				for (Obj obj : structToVisit.getMembers())
				{
					obj.accept(null);
				}
				msg.append("]");
				break;

			case MyStruct.Matrix :
				msg.append("Matrix of ");

				switch (structToVisit.getElemType().getKind())
				{
					case Struct.None :
						msg.append("notype");
						break;
					case Struct.Int :
						msg.append("int");
						break;
					case Struct.Bool :
						msg.append("bool");
						break;
					case Struct.Char :
						msg.append("char");
						break;
					case Struct.Class :
						msg.append("Class");
						break;
				}
		}

		return msg.toString();
	}

}
