package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor
{

	private int mainPc = -1;
	public int getMainPc()
	{
		return mainPc;
	}

	public void visit(PrintStmt printStmt)
	{
		Obj expr = printStmt.getExpr().obj;
		Struct exprStruct = expr.getType().getElemType() == null
				? expr.getType()
				: expr.getType().getElemType();
		if (printStmt.getSingStmCommaIntOpt() instanceof SinglStmComaNo)
		{
			if (exprStruct.equals(MyTable.intType))
			{
				Code.loadConst(5); // sirina
				Code.put(Code.print);

			} else if (exprStruct.equals(MyTable.charType))
			{
				Code.loadConst(1);
				Code.put(Code.bprint);
			} else
			{
				Code.loadConst(5);
				Code.put(Code.print);
			}
		} else
		{
			Code.loadConst(((SinglStmComaYes) printStmt.getSingStmCommaIntOpt()).getValue());
			if (exprStruct.equals(MyTable.intType))
			{
				Code.put(Code.print);

			} else if (exprStruct.equals(MyTable.charType))
			{
				Code.put(Code.bprint);
			} else
			{
				Code.put(Code.print);
			}
		}
	}

	public void visit(FactorTerm factorTerm)
	{

		int nesto = 3;
		return;

	}

	public void visit(ReadStmt readStmt)
	{

		if (readStmt.getDesignator().obj.getType() == Tab.charType)
		{
			Code.put(Code.bread);
		} else
		{
			Code.put(Code.read);
		}

		Code.store(readStmt.getDesignator().obj);

	}

	Obj obj;
	public void visit(IntFactor intFactor)
	{
		obj = MyTable.insert(Obj.Con, "$", intFactor.struct);
		obj.setLevel(0);
		obj.setAdr(intFactor.getValue());
		System.out.println("intFactor: " + intFactor.getValue() + " Const");
		Code.load(obj);
	}

	public void visit(CharFactor charFactor)
	{

		obj = MyTable.insert(Obj.Con, "$", charFactor.struct);
		obj.setLevel(0);
		obj.setAdr(charFactor.getValue());
		Code.load(obj);
	}
	public void visit(BoolFactor boolFactor)
	{
		obj = MyTable.insert(Obj.Con, "$", boolFactor.struct);
		obj.setLevel(0);
		obj.setAdr((boolFactor.getValue() ? 1 : 0));
		Code.load(obj);

	}

	public void visit(MethodName methodName)
	{

		methodName.obj.setAdr(Code.pc);
		if ("main".equalsIgnoreCase(methodName.getMethodname()))
		{
			mainPc = Code.pc;

		}
		SyntaxNode methodNode = methodName.getParent();
		VarCounter vc = new VarCounter();

		methodNode.traverseTopDown(vc);

		FormParamCounter fpc = new FormParamCounter();
		methodNode.traverseTopDown(fpc);

		Code.put(Code.enter);
		Code.put(fpc.getCount());
		Code.put(fpc.getCount() + vc.getCount());

	}

	public void visit(ReturnNoExpr returnNoExpr)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);

	}

	public void visit(ReturnExpr returnExpr)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(MethodDecl methodDecl)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(FakeIdent fakeIdent)
	{
		if (((DesignatorWithOptList) fakeIdent.getParent()).getListaDesi() instanceof EmptyDesiList)
		{

		} else
		{
			Code.load(fakeIdent.obj);
		}
	}

	public void visit(DesignatorWithOptList designatorWithOptList)
	{
		if (designatorWithOptList.getListaDesi() instanceof EmptyDesiList)
		{

		} else
		{

			if (designatorWithOptList.getListaDesi() instanceof NonEmptyListaDesi)
			{
				if (((NonEmptyListaDesi) designatorWithOptList.getListaDesi())
						.getListaDesi() instanceof NonEmptyListaDesi)
				{
					if (((NonEmptyListaDesi) ((NonEmptyListaDesi) designatorWithOptList
							.getListaDesi()).getListaDesi())
									.getListaDesi() instanceof EmptyDesiList)
					{
						// detektovali matrixu
						/*
						 * ListaDesi / \ ListaDesi ElemListeDesi / ElemListeDesi
						 */
						// stack:.. MatAdrr, row, col
						Code.put(Code.dup_x2);
						// stack:.. col, MatAdrr, row, col
						Code.put(Code.pop);
						if(designatorWithOptList.obj.getType().getKind() == Struct.Char) 
						{
							// stack:.. col, MatAdrr, row
							Code.put(Code.baload);
						}else
						{
							// stack:.. col, MatAdrr, row
							Code.put(Code.aload);
						}
				
						// stack:.. col, matAdr_row
						Code.put(Code.dup_x1);
						// stack:.. matAdr_row, col, matAdr_row
						Code.put(Code.pop);
						// stack:.. matAdr_row, col
					}

				}

			}
		}

	}

	public void visit(FactorDesignator factorDesignator)
	{	
		Code.load(factorDesignator.getDesignator().obj);
	}

	public void visit(DesiStmtINC desiStmtINC)
	{
		if (desiStmtINC.getDesignator().obj.getKind() == Obj.Elem)
		{
			Code.put(Code.dup2); // &array, index moraju se duplirati zbog store
			Code.load(desiStmtINC.getDesignator().obj);
		}
		if (desiStmtINC.getDesignator().obj.getKind() == Obj.Var)
		{
			Code.load(desiStmtINC.getDesignator().obj);
		}

		Code.loadConst(1); // ucitaj 1
		Code.put(Code.add); // dodaj jedan na ucitan podatak
		Code.store(desiStmtINC.getDesignator().obj); // upisi nesto = nesto+1
														// kao nesto++

	}

	public void visit(DesiStmtDEC desiStmtDEC)
	{
		if (desiStmtDEC.getDesignator().obj.getKind() == Obj.Elem)
		{
			Code.put(Code.dup2); // &array, index moraju se duplirati zbog store
			Code.load(desiStmtDEC.getDesignator().obj);
		}
		if (desiStmtDEC.getDesignator().obj.getKind() == Obj.Var)
		{
			Code.load(desiStmtDEC.getDesignator().obj);
		}

		Code.loadConst(1); // ucitaj 1
		Code.put(Code.sub); // dodaj jedan na ucitan podatak
		Code.store(desiStmtDEC.getDesignator().obj); // upisi nesto = nesto+1
														// kao nesto++

	}

	public void visit(DesiStmtAssignop desiStmtAssignop)
	{
		if (desiStmtAssignop.getDesignator().obj.getType().getKind() != MyStruct.Matrix
				&& desiStmtAssignop.getDesignator().obj.getType().getKind() != Struct.Array)
		{
			// ako nije matrix
			Code.store(desiStmtAssignop.getDesignator().obj);
		} else
		{

			// stack:.. matAdr_row, col, val
			
			Code.store(desiStmtAssignop.getDesignator().obj);

		}

	}

	public void visit(NewVectorExprFactor newVectorExprFactor)
	{
		Code.put(Code.newarray);
		Struct temp = newVectorExprFactor.struct.getElemType();
		if (temp == MyTable.charType)
		{
			Code.put(0);
		} else
		{
			Code.put(1);
		}

	}

	public void visit(NewMatrixFactor newMatrixFactor)
	{
		Code.put(Code.dup_x1); // stack:..row,col-> ..col, row, col
		Code.put(Code.pop); // skinemo col da nam ostane col, row
		Code.put(Code.newarray);
		Struct type = newMatrixFactor.struct.getElemType();
		if (type == MyTable.charType)
		{
			Code.put(0);
		} else
		{
			Code.put(1);
		} // ovo ce zajedno sa new_array da napravi int arr or char arr

		// stack:col, adr
		/*
		 * adr: _______________ | | | | | |___|___|___|___|
		 */

		Code.put(Code.dup);
		Code.put(Code.arraylength); // stack: col,adrM, lenAdrM
		
		Code.loadConst(-1); // stack: col,adrM, lenAdrM, -1
		
		
		int loop = Code.pc;
		Code.put(Code.const_1); // stack: col,adrM, lenAdrM, -1, 1
		Code.put(Code.add); // stack: col,adrM, lenAdrM, 0
		
		Code.put(Code.dup2); //// stack: col,adrM, lenAdrM, 0, lenAdrM, 0
		int temp = Code.pc + 1; 
		Code.putFalseJump(Code.gt, 0);// stack: col,adrM, lenAdrM, 0
		
		Code.put(Code.dup_x1);// stack: col,adrM,0,lenAdrM, 0
		Code.put(Code.pop);
		Code.put(Code.pop);// stack: col,adrM,0
		
		Code.put(Code.dup_x2);// stack: index, col, adrM,index
		Code.put(Code.dup_x2);// stack: index, index, col, adrM,index
		Code.put(Code.pop);	// stack: index, index, col, adrM
		
		Code.put(Code.dup_x2);// stack: index, adrM, index, col, adrM
		Code.put(Code.dup_x2);// stack: index, adrM, adrM, index, col, adrM
		Code.put(Code.pop);// stack: index, adrM, adrM, index, col
		
		Code.put(Code.dup_x2);// stack: index, adrM, col, adrM, index, col
		Code.put(Code.dup_x2);// stack: index, adrM, col, col,adrM, index, col
		Code.put(Code.pop);// stack: index, adrM, col, col, adrM, index
		
		Code.put(Code.dup_x2); // stack: index, adrM, col,index, col, adrM, index
		Code.put(Code.pop);  // stack: index, adrM, col,  index, col, adrM
		Code.put(Code.dup_x2); // stack: index, adrM, col, adrM, index, col, adrM
		Code.put(Code.pop); // stack: index, adrM, col, adrM, index, col
		
		Code.put(Code.newarray);
		if (type == MyTable.charType)
		{
			Code.put(0);
		} else
		{
			Code.put(1);
		} // stack: index, adrM, col, adrM, index, adrNizKolone
		
		if (type == MyTable.charType)
		{
			Code.put(Code.bastore); // cuvanje u adrMatrix[i]= adrNizKolone
		} else
		{
			Code.put(Code.astore);
		} // stack: index, adrM, col
		
		Code.put(Code.dup_x2); // stack:col, index, adrM, col
		Code.put(Code.pop);// stack:col, index, adrM
		
		Code.put(Code.dup_x1);// stack:col, adrM, index, adrM
		Code.put(Code.arraylength);// stack:col, adrM, index, lenAdrM
		Code.put(Code.dup_x1);// stack:col, adrM, lenAdrM, index, lenAdrM
		Code.put(Code.pop);// stack:col, adrM, lenAdrM, index
		
		Code.putJump(loop);
		
		Code.fixup(temp);
		
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.pop);

	}

	public void visit(MulopTerm mulopTerm)
	{
		Mulop mulop = mulopTerm.getMulop();
		if (mulop instanceof MulMulop)
		{
			Code.put(Code.mul);
		} else if (mulop instanceof DivMulop)
		{
			Code.put(Code.div);
		} else
		{
			Code.put(Code.rem);
		}
	}

	public void visit(MinusTermExpr minusTermExpr)
	{
		Code.put(Code.neg);
	}

	public void visit(AddopExpr addopExpr)
	{
		Addop addop = addopExpr.getAddop();

		if (addop instanceof PlusAddop)
		{
			Code.put(Code.add);
		} else
		{
			Code.put(Code.sub);
		}
	}

}