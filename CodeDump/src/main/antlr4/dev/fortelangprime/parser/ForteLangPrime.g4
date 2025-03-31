grammar ForteLangPrime;

// -----------------------------
// Parser rules
// -----------------------------

library: 'Library' IDENTIFIER '{' metadata* '}' '{' declaration* '}' ;
metadata: (exportStmt | importStmt) ;

declaration
    : annotation* coreDeclaration
    ;

coreDeclaration
    : functionDecl
    | typeDecl
    | operatorDecl
    ;

functionDecl
    : IDENTIFIER genericDecl? ':' paramList? typeArrow? '=' expression ';' // id(<T>) a <T> -> <T> = a;
    // | IDENTIFIER type '=' expression ';' // justTwo <Int> = 2;
    ;

functionType: type ('->' type)* ;

typeArrow
    : '->' type
    ;

paramList: param ( '->' param )* ;
param
    : type IDENTIFIER? 
    | '(' functionType ')' IDENTIFIER?
    ;

genericDecl: ('<' genericTypeParams '>') ;
genericTypeParams: typeIdentifier (',' typeIdentifier)* ;

exportStmt: 'export' ('*' | IDENTIFIER) ';' ;
importStmt: 'import' STRING_LITERAL 'as' IDENTIFIER ';' ;

// -----------------------------
// Types
// -----------------------------

typeDecl
    : 'type' IDENTIFIER genericDecl? '=' typeExpr ('|' typeExpr)* ';'?
    ;

annotation: '@' IDENTIFIER ('(' literal (',' literal)* ')')? ;

typeExpr
    : recordType
    | IDENTIFIER typeExpr?
    | IDENTIFIER '<' typeExpr (',' typeExpr)* '>'
    ;

recordType: '{' recordField+ '}' ;
recordField: IDENTIFIER type ';' ;

type
    : typeIdentifier
    | typeIdentifier genericDecl
    ;

typeIdentifier: IDENTIFIER ;

// -----------------------------
// Operators
// -----------------------------

operatorDecl
    : IDENTIFIER '(' operatorSymbol ')' genericDecl? ':' paramList? typeArrow? '=' expression ';'
    ;

operatorSymbol
    : EQ
    | NEQ
    | LTE
    | GTE
    | LT
    | GT
    | ADD
    | SUB
    | MUL
    | DIV
    | MOD
    | POW
    | SYMBOL ;

// -----------------------------
// Expressions (Simplified for now)
// -----------------------------

expression
    : primaryExpression (postfixExpression)*  # extendedExpr
    | primaryExpression                       # simpleExpr
    | expression operatorSymbol expression    # binaryOperation
    ;

primaryExpression
    : literal                                 # literalExpr
    | IDENTIFIER                              # identifierExpr
    | functionCall                            # functionCallExpr
    | recordLiteral                           # recordLiteralExpr
    | recordUpdate                            # recordUpdateExpr
    | ifExpr                                  # ifExprExpr
    | switchExpr                              # switchExprExpr
    | guardExpr                               # guardExprExpr
    | listExpr                                # listExprExpr
    | '(' expression ')'                      # groupedExpr
    ;

postfixExpression
    : recordAccess                            # recordAccessExpr
    ;

functionCall
    : callableFunction expression+
    ;

callableFunction
    : IDENTIFIER ('.' IDENTIFIER)* // blah, or List.Cons
    ;

recordLiteral: '{' recordAssignment+ '}' ;
recordAssignment: IDENTIFIER '=' expression ';' ;

recordAccess: '.' IDENTIFIER ;
recordUpdate: '{' IDENTIFIER '|' recordAssignment+ '}' ;

ifExpr: 'if' expression 'then' expression 'else' expression ;
switchExpr: 'match' expression ('|' pattern '=>' expression)+ ;
guardExpr: '?:' ('|' expression '=>' expression)+ ;
listExpr: '[' (expression (',' expression)*)? ']' ;

pattern
    : expression
    ;

literal
    : INT_LITERAL
    | DOUBLE_LITERAL
    | STRING_LITERAL
    | 'true'
    | 'false'
    | '???'
    ;

// -----------------------------
// Lexer rules
// -----------------------------


IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]* ;

// Custom operators, have to be length >= 2
fragment SYMBOL_CHAR: [!%&*+\-./<>:=?$@^|~];
SYMBOL
    : SYMBOL_CHAR SYMBOL_CHAR+       { !getText().equals(">>") && !getText().equals("<<") }?
    ;

// Literals
STRING_LITERAL: '"' (~["\\\n\r])* '"' ;
INT_LITERAL: [0-9]+ ;
DOUBLE_LITERAL: [0-9]+ '.' [0-9]+ ;

// Relational operators
EQ: '==';
NEQ: '!=';
LTE: '<=';
GTE: '>=';
LT: '<';
GT: '>';

// Mathematical operators
ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
MOD: '%';
POW: '^';

// Comments
COMMENT: '##' ~[\n\r]* -> skip ;
// MULTILINE_COMMENT: '##' .*? '##' -> skip ;
WS: [ \t\r\n]+ -> skip ;