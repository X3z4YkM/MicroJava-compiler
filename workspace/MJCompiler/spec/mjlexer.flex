
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}



%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }



"program"  	{ return new_symbol(sym.PROG, yytext());}
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"if"		{ return new_symbol(sym.IF, yytext()); }
"else" 		{ return new_symbol(sym.ELSE, yytext()); }
"const"		{ return new_symbol(sym.CONST, yytext()); }
"while"		{ return new_symbol(sym.WHILE, yytext()); }
"do"		{ return new_symbol(sym.DO, yytext()); }
"break"		{ return new_symbol(sym.BREAK, yytext()); }
"continue"	{ return new_symbol(sym.CONTINUE, yytext()); }
"foreach"	{ return new_symbol(sym.FOREACH, yytext()); }
"class"		{ return new_symbol(sym.CLASS, yytext()); }
"new"		{ return new_symbol(sym.NEW, yytext()); }
"extends"	{ return new_symbol(sym.EXTENDS, yytext()); }
"read"		{ return new_symbol(sym.READ, yytext()); }

"true"|"false" 		{return new_symbol (sym.BOOL, Boolean.valueOf(yytext()));}
[0-9]+  			{ return new_symbol(sym.INT, Integer.valueOf(yytext())); }
([a-z]|[A-Z])[a-zA-Z0-9_]* 	{return new_symbol (sym.IDENT, yytext()); }
"'"."'"			{return new_symbol (sym.CHAR, Character.valueOf(yytext().charAt(1)));}


<YYINITIAL> "+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-"			{ return new_symbol(sym.MINUS, yytext()); }
"*"			{ return new_symbol(sym.MUL, yytext()); }
"/"			{ return new_symbol(sym.DIV, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
">"			{ return new_symbol(sym.GT, yytext()); }
">="		{ return new_symbol(sym.GEQ, yytext()); }
"<"			{ return new_symbol(sym.LT, yytext()); }
"<="		{ return new_symbol(sym.LEQ, yytext()); }
"=="		{ return new_symbol(sym.LOGEQ, yytext()); }
"!="		{ return new_symbol(sym.NEQ, yytext()); }
"%"		    { return new_symbol(sym.MOD, yytext()); }
"++"		{ return new_symbol(sym.INC, yytext()); }
"--"		{ return new_symbol(sym.DEC, yytext()); }
"&&"		{ return new_symbol(sym.AND, yytext()); }
"||"		{ return new_symbol(sym.OR, yytext()); }
"=="		{ return new_symbol(sym.LOGEQ, yytext()); }
"=>"		{ return new_symbol(sym.ASSIGN, yytext()); }

";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"."         { return new_symbol(sym.DOT, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"["			{ return new_symbol(sym.LBRACKET, yytext()); }
"]"			{ return new_symbol(sym.RBRACKET, yytext()); }


<YYINITIAL> "//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }










