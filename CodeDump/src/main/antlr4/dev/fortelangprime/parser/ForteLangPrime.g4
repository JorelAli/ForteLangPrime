grammar ForteLangPrime;

// -----------------------------
// Parser rules
// -----------------------------

library: 'Library' IDENTIFIER '{' metadata* '}' '{' declaration* '}' ;
metadata: (exportStmt | importStmt) ;

declaration
    : functionDecl
    | typeDecl
    | operatorDecl
    | annotatedTypeDecl
    ;

functionDecl
    : IDENTIFIER genericTypeParams? paramList '->' type '=' expression ';'
    | IDENTIFIER ':' functionType '=' expression ';'
    ;

functionType: type ('->' type)* ;

paramList: (param+)? ;
param: IDENTIFIER type ;

genericTypeParams: '<' typeIdentifier (',' typeIdentifier)* '>' ;

exportStmt: 'export' ('*' | IDENTIFIER) ';' ;
importStmt: 'import' STRING_LITERAL 'as' IDENTIFIER ';' ;

// -----------------------------
// Types
// -----------------------------

typeDecl
    : 'type' genericTypeParams? IDENTIFIER '=' typeExpr ('|' '|' typeExpr)* ';'?
    ;

annotatedTypeDecl: annotation+ typeDecl ;

annotation: '@' IDENTIFIER ('(' operatorSymbol ')')? ;

typeExpr
    : recordType
    | IDENTIFIER
    | IDENTIFIER '<' typeExpr (',' typeExpr)* '>'
    ;

recordType: '{' recordField+ '}' ;
recordField: IDENTIFIER type ';' ;

type: '<' IDENTIFIER '>' ;
typeIdentifier: IDENTIFIER ;

// -----------------------------
// Operators
// -----------------------------

operatorDecl
    : ('infix' | 'infixl' | 'infixr') INT_LITERAL IDENTIFIER '(' operatorSymbol ')' functionType '=' expression ';'
    ;

operatorSymbol: SYMBOL ;

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
    | '(' expression ')'                      # groupedExpr
    ;

postfixExpression
    : recordAccess                            # recordAccessExpr
    ;

// expression
//     : literal                              # literalExpr
//     | IDENTIFIER                           # identifierExpr
//     | functionCall                         # functionCallExpr
//     | recordLiteral                        # recordLiteralExpr
//     | recordAccess                         # recordAccessExpr
//     | recordUpdate                         # recordUpdateExpr
//     | ifExpr                               # ifExprExpr
//     | switchExpr                           # switchExprExpr
//     | guardExpr                            # guardExprExpr
//     | expression operatorSymbol expression # binaryOperation
//     ;

functionCall: IDENTIFIER expression+ ;

recordLiteral: '{' recordAssignment+ '}' ;
recordAssignment: IDENTIFIER '=' expression ';' ;

recordAccess: '.' IDENTIFIER ;
recordUpdate: '{' IDENTIFIER '|' recordAssignment+ '}' ;

ifExpr: 'if' expression 'then' expression 'else' expression ;

switchExpr: 'switch' expression ('|' pattern '=>' expression)+ ;
guardExpr: '?:' ('|' expression '=>' expression)+ ;

pattern: IDENTIFIER | '[]' | pattern operatorSymbol pattern ;

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
SYMBOL: (~[a-zA-Z0-9 \t\r\n{}();:=<>"'[\]|])+ ;
STRING_LITERAL: '"' (~["\\\n\r])* '"' ;
INT_LITERAL: [0-9]+ ;
DOUBLE_LITERAL: [0-9]+ '.' [0-9]+ ;

COMMENT: '##' ~[\n\r]* -> skip ;
MULTILINE_COMMENT: '##' .*? '##' -> skip ;
WS: [ \t\r\n]+ -> skip ;