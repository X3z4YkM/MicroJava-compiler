// generated with ast extension for cup
// version 0.8
// 24/5/2023 16:31:1


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(FormParsOpt FormParsOpt);
    public void visit(Literal Literal);
    public void visit(VarDeclElem VarDeclElem);
    public void visit(ErrFild ErrFild);
    public void visit(Relop Relop);
    public void visit(Assignop Assignop);
    public void visit(ActParsRBR ActParsRBR);
    public void visit(MethodType MethodType);
    public void visit(DesignatorElem DesignatorElem);
    public void visit(ExprEP ExprEP);
    public void visit(SingStmCommaIntOpt SingStmCommaIntOpt);
    public void visit(ExtendOpt ExtendOpt);
    public void visit(StatementList StatementList);
    public void visit(MethodDecList MethodDecList);
    public void visit(OptMethodDecList OptMethodDecList);
    public void visit(Addop Addop);
    public void visit(Fild Fild);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(VarList VarList);
    public void visit(ConstList ConstList);
    public void visit(DeclList DeclList);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(FormParsList FormParsList);
    public void visit(Condition Condition);
    public void visit(ErrorGlobaVar ErrorGlobaVar);
    public void visit(Statements Statements);
    public void visit(EPFormPar EPFormPar);
    public void visit(DesignatorsList DesignatorsList);
    public void visit(ExprList ExprList);
    public void visit(VarDeclList VarDeclList);
    public void visit(Expr Expr);
    public void visit(VarDeclListFild VarDeclListFild);
    public void visit(ActPars ActPars);
    public void visit(Designators Designators);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Decl Decl);
    public void visit(VarElem VarElem);
    public void visit(ListaDesi ListaDesi);
    public void visit(FildElem FildElem);
    public void visit(Statement Statement);
    public void visit(ActParsLBR ActParsLBR);
    public void visit(DesignatorExtendLBR DesignatorExtendLBR);
    public void visit(ConditionEP ConditionEP);
    public void visit(CondFact CondFact);
    public void visit(ElemEGlobalVar ElemEGlobalVar);
    public void visit(GlobalVarList GlobalVarList);
    public void visit(VarDeclListFildElem VarDeclListFildElem);
    public void visit(SingleStatement SingleStatement);
    public void visit(FormPars FormPars);
    public void visit(EPGlobalVarDecl EPGlobalVarDecl);
    public void visit(ElemListeDesi ElemListeDesi);
    public void visit(DesignatorOPT DesignatorOPT);
    public void visit(ProgramEnd ProgramEnd);
    public void visit(ModMulop ModMulop);
    public void visit(DivMulop DivMulop);
    public void visit(MulMulop MulMulop);
    public void visit(MinusAddop MinusAddop);
    public void visit(PlusAddop PlusAddop);
    public void visit(LeqRelop LeqRelop);
    public void visit(LtRelop LtRelop);
    public void visit(GeqRelop GeqRelop);
    public void visit(GtRelop GtRelop);
    public void visit(NeqRelop NeqRelop);
    public void visit(LogeqRelop LogeqRelop);
    public void visit(EqualAssignop EqualAssignop);
    public void visit(Label Label);
    public void visit(Type Type);
    public void visit(AEADLBracket AEADLBracket);
    public void visit(DesignatorLExprR DesignatorLExprR);
    public void visit(DesignatorDotIdent DesignatorDotIdent);
    public void visit(EmptyDesiList EmptyDesiList);
    public void visit(NonEmptyListaDesi NonEmptyListaDesi);
    public void visit(FakeIdent FakeIdent);
    public void visit(DesignatorWithOptList DesignatorWithOptList);
    public void visit(ActParsEnd ActParsEnd);
    public void visit(ActParsStart ActParsStart);
    public void visit(DelimitedFactor DelimitedFactor);
    public void visit(NewVectorActParsFactor NewVectorActParsFactor);
    public void visit(NewMatrixFactor NewMatrixFactor);
    public void visit(NewVectorExprFactor NewVectorExprFactor);
    public void visit(NewScalarFactor NewScalarFactor);
    public void visit(BoolFactor BoolFactor);
    public void visit(CharFactor CharFactor);
    public void visit(IntFactor IntFactor);
    public void visit(FactorDesignatorWithActPars FactorDesignatorWithActPars);
    public void visit(FactorDesignator FactorDesignator);
    public void visit(MulopTerm MulopTerm);
    public void visit(FactorTerm FactorTerm);
    public void visit(AddopExpr AddopExpr);
    public void visit(MinusTermExpr MinusTermExpr);
    public void visit(TermExpr TermExpr);
    public void visit(ErrorExprEP ErrorExprEP);
    public void visit(ExprEPExpr ExprEPExpr);
    public void visit(ExprElem ExprElem);
    public void visit(ExprListVector ExprListVector);
    public void visit(EmptyActParsList EmptyActParsList);
    public void visit(AcrParsWithExprList AcrParsWithExprList);
    public void visit(ExprRelopExprCondFact ExprRelopExprCondFact);
    public void visit(ExprCondFact ExprCondFact);
    public void visit(CondTermAndCondFact CondTermAndCondFact);
    public void visit(CondFactCondTerm CondFactCondTerm);
    public void visit(ConditionOrConTerm ConditionOrConTerm);
    public void visit(ConditionTermCondition ConditionTermCondition);
    public void visit(ErrorConditioEP ErrorConditioEP);
    public void visit(ConditionEpCondition ConditionEpCondition);
    public void visit(DesignatorOPTNo DesignatorOPTNo);
    public void visit(DesignatorOPTYes DesignatorOPTYes);
    public void visit(DesignatorElemCommaDesi DesignatorElemCommaDesi);
    public void visit(EmptyDesignatorsList EmptyDesignatorsList);
    public void visit(NonEmptyDesignatorsList NonEmptyDesignatorsList);
    public void visit(DesignatorsOPTandList DesignatorsOPTandList);
    public void visit(DesiStmtDEC DesiStmtDEC);
    public void visit(DesiStmtINC DesiStmtINC);
    public void visit(DesiStmtActPars DesiStmtActPars);
    public void visit(DesiStmtAssignop DesiStmtAssignop);
    public void visit(DesiStmtDesignator DesiStmtDesignator);
    public void visit(SinglStmComaNo SinglStmComaNo);
    public void visit(SinglStmComaYes SinglStmComaYes);
    public void visit(DelimitedStatement DelimitedStatement);
    public void visit(PrintStmt PrintStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(ReturnExpr ReturnExpr);
    public void visit(ReturnNoExpr ReturnNoExpr);
    public void visit(ContinueStmt ContinueStmt);
    public void visit(BreakStmt BreakStmt);
    public void visit(WhileStmt WhileStmt);
    public void visit(IfElseStmt IfElseStmt);
    public void visit(IfStmt IfStmt);
    public void visit(DesignatorSingleStatement DesignatorSingleStatement);
    public void visit(EmptyStatementList EmptyStatementList);
    public void visit(NonEmptyStatementList NonEmptyStatementList);
    public void visit(MultipleStatemet MultipleStatemet);
    public void visit(OneSingleStatement OneSingleStatement);
    public void visit(ErrorFormPar ErrorFormPar);
    public void visit(EPFormParMatrix EPFormParMatrix);
    public void visit(EPFormParNoBrackets EPFormParNoBrackets);
    public void visit(EPFormParWithBrackets EPFormParWithBrackets);
    public void visit(FormParsListElem FormParsListElem);
    public void visit(VectorForParsList VectorForParsList);
    public void visit(NoFormPars NoFormPars);
    public void visit(YesFormPars YesFormPars);
    public void visit(NoFormParsOpt NoFormParsOpt);
    public void visit(YesFormParsOpt YesFormParsOpt);
    public void visit(VarElem3 VarElem3);
    public void visit(VarElem2 VarElem2);
    public void visit(VarElem1 VarElem1);
    public void visit(VarListOneEelem VarListOneEelem);
    public void visit(VarListMultipleElem VarListMultipleElem);
    public void visit(VarDeclElemWithVarList VarDeclElemWithVarList);
    public void visit(EmptyVarDeclList EmptyVarDeclList);
    public void visit(NonEmptyVarDeclList NonEmptyVarDeclList);
    public void visit(VoidReturnType VoidReturnType);
    public void visit(NonVoidReturnType NonVoidReturnType);
    public void visit(MethodEnd MethodEnd);
    public void visit(MethodStart MethodStart);
    public void visit(MethodName MethodName);
    public void visit(MethodDecl MethodDecl);
    public void visit(VoidMethods VoidMethods);
    public void visit(NonVoidMethods NonVoidMethods);
    public void visit(NoMethodDecList NoMethodDecList);
    public void visit(YesMethodDecList YesMethodDecList);
    public void visit(ClassName ClassName);
    public void visit(ScalarField ScalarField);
    public void visit(VectorField VectorField);
    public void visit(SingleFieldFieldList SingleFieldFieldList);
    public void visit(MultipleFieldFieldList MultipleFieldFieldList);
    public void visit(ErrorOnlySemi ErrorOnlySemi);
    public void visit(ErrorVarDecList ErrorVarDecList);
    public void visit(ErrorFielDeclSemi ErrorFielDeclSemi);
    public void visit(CorFieldDecl CorFieldDecl);
    public void visit(EmptyVarDecListFild EmptyVarDecListFild);
    public void visit(NonEmptyFildDeclList NonEmptyFildDeclList);
    public void visit(ErrorGlobalVar ErrorGlobalVar);
    public void visit(MatrixGlobalVar MatrixGlobalVar);
    public void visit(ScalGlobalVar ScalGlobalVar);
    public void visit(VectorGlobalVar VectorGlobalVar);
    public void visit(GlobalVectorElem GlobalVectorElem);
    public void visit(GlobalVectorList GlobalVectorList);
    public void visit(ErrorGlobalVarOnlySemi ErrorGlobalVarOnlySemi);
    public void visit(ErrorGlobalVarDecl ErrorGlobalVarDecl);
    public void visit(VarDecl VarDecl);
    public void visit(CharLiteral CharLiteral);
    public void visit(BoolLiteral BoolLiteral);
    public void visit(IntLiteral IntLiteral);
    public void visit(ConstElem ConstElem);
    public void visit(SingleElemConstList SingleElemConstList);
    public void visit(MultipleElemConstList MultipleElemConstList);
    public void visit(NoExtend NoExtend);
    public void visit(ClasExtend ClasExtend);
    public void visit(ConstDecl ConstDecl);
    public void visit(GlobalDecl GlobalDecl);
    public void visit(ClassDecl ClassDecl);
    public void visit(EmptyDeclList EmptyDeclList);
    public void visit(NonEmptyDeclList NonEmptyDeclList);
    public void visit(ProgramName ProgramName);
    public void visit(Program Program);

}