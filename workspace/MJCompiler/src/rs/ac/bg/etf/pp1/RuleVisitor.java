package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;

public class RuleVisitor extends VisitorAdaptor{

	int printCallCount = 0;
	int globalDeclCount = 0;
	
	Logger log = Logger.getLogger(getClass());

	public void visit(GlobalDecl globalDecl){
		globalDeclCount++;
	}
	
    public void visit(PrintStmt print) {
		printCallCount++;
	}

}
