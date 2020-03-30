/* ForteLangPrimeParser.java */
/* Generated By:JavaCC: Do not edit this line. ForteLangPrimeParser.java */
package dev.jorel.fortelangprime.parser;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import dev.jorel.fortelangprime.ast.*;
import dev.jorel.fortelangprime.ast.expressions.*;
import dev.jorel.fortelangprime.ast.types.*;
import dev.jorel.fortelangprime.parser.util.*;
import dev.jorel.fortelangprime.compiler.*;

public class ForteLangPrimeParser implements ForteLangPrimeParserConstants {

        private static UniversalContext universalContext;
        private static String currentFunctionName;

        public static FLPLibrary parse(String input) throws ParseException, IOException {
                universalContext = new UniversalContext();
                InputStream inputStream = new ByteArrayInputStream(input.getBytes(Charset.forName("UTF-8")));
                return new ForteLangPrimeParser(new StreamProvider(inputStream)).input();
        }

        public static FLPLibrary parse(File input) throws ParseException, FileNotFoundException, IOException {
                universalContext = new UniversalContext();
                return new ForteLangPrimeParser(new StreamProvider(new FileInputStream(input))).input();
        }

        public static UniversalContext getUniversalContext() {
                return ForteLangPrimeParser.universalContext;
        }

/** Main endpoint */
  final public FLPLibrary input() throws ParseException {FLPLibrary lib;
    lib = program();
    jj_consume_token(0);
return lib;
  }

  final public FLPLibrary program() throws ParseException {Token name; List<String> exports;
        List<RecordTypeDeclaration> typeDeclarations;
        List<FLPFunction> functions;
    jj_consume_token(LIBRARY);
    name = jj_consume_token(VAR_NAME);
    jj_consume_token(OPENCBRACE);
    exports = exports();
    jj_consume_token(CLOSECBRACE);
    jj_consume_token(OPENCBRACE);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INFIX:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      customOperator();
    }
    typeDeclarations = typeDeclarations();
    functions = functions();
    jj_consume_token(CLOSECBRACE);
return new FLPLibrary(name.image, exports, functions, typeDeclarations);
  }

  final public List<String> exports() throws ParseException {List<String> exports = new ArrayList<String>(); Token t;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EXPORT:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(EXPORT);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case VAR_NAME:{
        t = jj_consume_token(VAR_NAME);
exports.add(t.image);
        break;
        }
      case STAR:{
        jj_consume_token(STAR);
universalContext.exportAll();
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(SEMICOLON);
    }
return exports;
  }

  final public void epsilon() throws ParseException {
{ }
  }

  final public void customOperator() throws ParseException {
    jj_consume_token(INFIX);
    jj_consume_token(VAR_NAME);
    jj_consume_token(OPENBRACKET);
    jj_consume_token(CUSTOM_OPERATOR);
    jj_consume_token(CLOSEBRACKET);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case VAR_NAME:{
      jj_consume_token(VAR_NAME);
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    jj_consume_token(LCHEVRON);
    type();
    jj_consume_token(RCHEVRON);
    jj_consume_token(ARROW);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case VAR_NAME:{
      jj_consume_token(VAR_NAME);
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    jj_consume_token(LCHEVRON);
    type();
    jj_consume_token(RCHEVRON);
    jj_consume_token(ARROW);
    jj_consume_token(LCHEVRON);
    type();
    jj_consume_token(RCHEVRON);
    jj_consume_token(EQUALS);
    expression();
    jj_consume_token(SEMICOLON);
  }

  final public List<RecordTypeDeclaration> typeDeclarations() throws ParseException {List<RecordTypeDeclaration> list = new ArrayList<RecordTypeDeclaration>();
        Token name;
        TypeRecord type;
        boolean printable = false;
        boolean equatable = false;
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PRINTABLE:
      case EQUATABLE:
      case TYPE:{
        ;
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case PRINTABLE:
        case EQUATABLE:{
          ;
          break;
          }
        default:
          jj_la1[6] = jj_gen;
          break label_4;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case PRINTABLE:{
          jj_consume_token(PRINTABLE);
printable = true;
          break;
          }
        case EQUATABLE:{
          jj_consume_token(EQUATABLE);
equatable = true;
          break;
          }
        default:
          jj_la1[7] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      jj_consume_token(TYPE);
      name = jj_consume_token(VAR_NAME);
      jj_consume_token(EQUALS);
      type = recordType(name.image);
universalContext.addRecordType(name.image, type);
                        list.add(new RecordTypeDeclaration(name.image, type, printable, equatable));
    }
return list;
  }

  final public List<FLPFunction> functions() throws ParseException {List<FLPFunction> functions = new ArrayList<FLPFunction>();
        FLPFunction f;
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LCHEVRON:
      case VAR_NAME:{
        ;
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        break label_5;
      }
      f = functionDeclaration();
for(FLPFunction function : functions) {
                                if(function.getName().equals(f.getName())) {
                                        {if (true) throw new ParseException("Tried to declare function " + f.getName() + " on line " + f.getLineNumber() + " but it has already been declared on line " + function.getLineNumber());}
                                }
                        }
                        functions.add(f);
    }
return functions;
  }

  final public FLPFunction functionDeclaration() throws ParseException {List<TypeNamedGeneric> genericTypeDeclaration = new ArrayList<TypeNamedGeneric>();
        Token name;
        Expr expr;
        List<Pair<String, Type>> functionTypes;
        TypeFunction tf;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LCHEVRON:{
      genericTypeDeclaration = genericTypeDeclaration();
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      ;
    }
    name = jj_consume_token(VAR_NAME);
ForteLangPrimeParser.currentFunctionName = name.image;
    functionTypes = functionTypes();
tf = Converter.functionTypesToTypeFunction(functionTypes);
                universalContext.addFunction(name.image, tf);
    jj_consume_token(EQUALS);
    expr = expression();
    jj_consume_token(SEMICOLON);
return new FLPFunction(name.beginLine, name.image, tf, genericTypeDeclaration, expr);
  }

  final public List<TypeNamedGeneric> genericTypeDeclaration() throws ParseException {Token t;
        List<TypeNamedGeneric> genericNames = new ArrayList<TypeNamedGeneric>();
    jj_consume_token(LCHEVRON);
    t = jj_consume_token(VAR_NAME);
genericNames.add(new TypeNamedGeneric(t.image));
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        ;
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        break label_6;
      }
      jj_consume_token(COMMA);
      t = jj_consume_token(VAR_NAME);
genericNames.add(new TypeNamedGeneric(t.image));
    }
    jj_consume_token(RCHEVRON);
return genericNames;
  }

  final public Expr expression() throws ParseException {Expr expr;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case OPENCBRACE:
    case IF:
    case TRUE:
    case FALSE:
    case PANIC:
    case VAR_NAME:
    case INT_LITERAL:
    case STRING:{
      expr = innerExpression(false);
      break;
      }
    case OPENBRACKET:{
      jj_consume_token(OPENBRACKET);
      expr = innerExpression(true);
      jj_consume_token(CLOSEBRACKET);
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return expr;
  }

  final public Expr innerExpression(boolean withBrackets) throws ParseException {Expr expr;
        Expr secondaryExpr;
        Token op;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT_LITERAL:{
      expr = integer();
      break;
      }
    case TRUE:
    case FALSE:{
      expr = bool();
      break;
      }
    case STRING:{
      expr = string();
      break;
      }
    case PANIC:{
      expr = panic();
      break;
      }
    case VAR_NAME:{
      expr = variable();
      break;
      }
    case IF:{
      expr = ifStatement();
      break;
      }
    case OPENCBRACE:{
      expr = recordExpr();
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    if (jj_2_1(2)) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQUALS_EQUALS:{
        op = jj_consume_token(EQUALS_EQUALS);
        break;
        }
      case CUSTOM_OPERATOR:{
        op = jj_consume_token(CUSTOM_OPERATOR);
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      secondaryExpr = expression();
expr = new ExprBinaryOp(op.beginLine, expr, secondaryExpr, Operation.from(op.kind), withBrackets);
    } else {
      ;
    }
return expr;
  }

  final public ExprRecordConstruction recordExpr() throws ParseException {List<Pair<String, Expr>> values = new ArrayList<Pair<String, Expr>>();
        Token startingToken;
        Expr base = null;
        Token t;
        Expr expr;
    startingToken = jj_consume_token(OPENCBRACE);
    if (jj_2_2(2)) {
      base = variable();
      jj_consume_token(PIPE);
    } else {
      ;
    }
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case VAR_NAME:{
        ;
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        break label_7;
      }
      t = jj_consume_token(VAR_NAME);
      jj_consume_token(EQUALS);
      expr = expression();
      jj_consume_token(SEMICOLON);
values.add(Pair.of(t.image, expr));
    }
    jj_consume_token(CLOSECBRACE);
return new ExprRecordConstruction(startingToken.beginLine, base, values);
  }

  final public ExprVariable variable() throws ParseException {Token t;
        List<Expr> expressions = new ArrayList<Expr>();
        Expr e;
    t = jj_consume_token(VAR_NAME);
    label_8:
    while (true) {
      if (jj_2_3(2)) {
        ;
      } else {
        break label_8;
      }
      e = expression();
expressions.add(e);
    }
return new ExprVariable(t.beginLine, t.image, ForteLangPrimeParser.currentFunctionName, expressions);
  }

  final public ExprIfStatement ifStatement() throws ParseException {Token t;
        Expr a;
        Expr b;
        Expr c;
    t = jj_consume_token(IF);
    a = expression();
    jj_consume_token(THEN);
    b = expression();
    jj_consume_token(ELSE);
    c = expression();
return new ExprIfStatement(t.beginLine, a, b, c);
  }

  final public ExprIntLit integer() throws ParseException {Token t;
    t = jj_consume_token(INT_LITERAL);
return new ExprIntLit(t.beginLine, Integer.parseInt(t.image));
  }

  final public ExprBoolLit bool() throws ParseException {Token t;
        boolean value;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case TRUE:{
      t = jj_consume_token(TRUE);
value = true;
      break;
      }
    case FALSE:{
      t = jj_consume_token(FALSE);
value = false;
      break;
      }
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return new ExprBoolLit(t.beginLine, value);
  }

  final public ExprStringLit string() throws ParseException {Token t;
    t = jj_consume_token(STRING);
return new ExprStringLit(t.beginLine, t.image.substring(1, t.image.length() - 1));
  }

  final public ExprPanic panic() throws ParseException {Token t;
    t = jj_consume_token(PANIC);
return new ExprPanic(t.beginLine);
  }

  final public List<Pair<String, Type>> functionTypes() throws ParseException {List<Pair<String, Type>> types = new ArrayList<Pair<String, Type>>();
        List<Pair<String, Type>> otherTypes = new ArrayList<Pair<String, Type>>();
        Type t;
        Token varName;
varName = null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case VAR_NAME:{
      varName = jj_consume_token(VAR_NAME);
      break;
      }
    default:
      jj_la1[16] = jj_gen;
      ;
    }
    jj_consume_token(LCHEVRON);
    t = type();
if(varName == null) {
                        types.add(Pair.of(null, t));
                } else {
                        types.add(Pair.of(varName.image, t));
                }
    jj_consume_token(RCHEVRON);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ARROW:{
      jj_consume_token(ARROW);
      otherTypes = functionTypes();
types.addAll(otherTypes);
      break;
      }
    default:
      jj_la1[17] = jj_gen;
      ;
    }
return types;
  }

  final public Type type() throws ParseException {Token t;
        Type type;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case TYPE_INT:{
      jj_consume_token(TYPE_INT);
type = new TypeInt();
      break;
      }
    case TYPE_STRING:{
      jj_consume_token(TYPE_STRING);
type = new TypeString();
      break;
      }
    case TYPE_BOOL:{
      jj_consume_token(TYPE_BOOL);
type = new TypeBool();
      break;
      }
    case VAR_NAME:{
      t = jj_consume_token(VAR_NAME);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LCHEVRON:{
        jj_consume_token(LCHEVRON);
        type();
        jj_consume_token(RCHEVRON);
        break;
        }
      default:
        jj_la1[18] = jj_gen;
        ;
      }
type = new TypeNamedGeneric(t.image);
      break;
      }
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return type;
  }

  final public TypeRecord recordType(String name) throws ParseException {List<Pair<String, Type>> types = new ArrayList<Pair<String, Type>>();
        Token t;
        Type type;
    jj_consume_token(OPENCBRACE);
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case VAR_NAME:{
        ;
        break;
        }
      default:
        jj_la1[20] = jj_gen;
        break label_9;
      }
      t = jj_consume_token(VAR_NAME);
      jj_consume_token(LCHEVRON);
      type = type();
      jj_consume_token(RCHEVRON);
      jj_consume_token(SEMICOLON);
types.add(Pair.of(t.image, type));
    }
    jj_consume_token(CLOSECBRACE);
return new TypeRecord(name, types);
  }

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_3R_24()
 {
    if (jj_scan_token(STRING)) return true;
    return false;
  }

  private boolean jj_3R_13()
 {
    if (jj_scan_token(OPENBRACKET)) return true;
    if (jj_3R_14()) return true;
    return false;
  }

  private boolean jj_3R_12()
 {
    if (jj_3R_14()) return true;
    return false;
  }

  private boolean jj_3R_30()
 {
    if (jj_scan_token(VAR_NAME)) return true;
    return false;
  }

  private boolean jj_3R_10()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_12()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) return true;
    }
    return false;
  }

  private boolean jj_3R_29()
 {
    if (jj_scan_token(FALSE)) return true;
    return false;
  }

  private boolean jj_3R_28()
 {
    if (jj_scan_token(TRUE)) return true;
    return false;
  }

  private boolean jj_3R_23()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_28()) {
    jj_scanpos = xsp;
    if (jj_3R_29()) return true;
    }
    return false;
  }

  private boolean jj_3_2()
 {
    if (jj_3R_11()) return true;
    if (jj_scan_token(PIPE)) return true;
    return false;
  }

  private boolean jj_3R_27()
 {
    if (jj_scan_token(OPENCBRACE)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_2()) jj_scanpos = xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_30()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(CLOSECBRACE)) return true;
    return false;
  }

  private boolean jj_3R_22()
 {
    if (jj_scan_token(INT_LITERAL)) return true;
    return false;
  }

  private boolean jj_3R_26()
 {
    if (jj_scan_token(IF)) return true;
    if (jj_3R_10()) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(25)) {
    jj_scanpos = xsp;
    if (jj_scan_token(46)) return true;
    }
    if (jj_3R_10()) return true;
    return false;
  }

  private boolean jj_3R_21()
 {
    if (jj_3R_27()) return true;
    return false;
  }

  private boolean jj_3R_20()
 {
    if (jj_3R_26()) return true;
    return false;
  }

  private boolean jj_3R_19()
 {
    if (jj_3R_11()) return true;
    return false;
  }

  private boolean jj_3R_18()
 {
    if (jj_3R_25()) return true;
    return false;
  }

  private boolean jj_3R_17()
 {
    if (jj_3R_24()) return true;
    return false;
  }

  private boolean jj_3R_16()
 {
    if (jj_3R_23()) return true;
    return false;
  }

  private boolean jj_3R_15()
 {
    if (jj_3R_22()) return true;
    return false;
  }

  private boolean jj_3R_25()
 {
    if (jj_scan_token(PANIC)) return true;
    return false;
  }

  private boolean jj_3R_14()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_15()) {
    jj_scanpos = xsp;
    if (jj_3R_16()) {
    jj_scanpos = xsp;
    if (jj_3R_17()) {
    jj_scanpos = xsp;
    if (jj_3R_18()) {
    jj_scanpos = xsp;
    if (jj_3R_19()) {
    jj_scanpos = xsp;
    if (jj_3R_20()) {
    jj_scanpos = xsp;
    if (jj_3R_21()) return true;
    }
    }
    }
    }
    }
    }
    xsp = jj_scanpos;
    if (jj_3_1()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3_3()
 {
    if (jj_3R_10()) return true;
    return false;
  }

  private boolean jj_3R_11()
 {
    if (jj_scan_token(VAR_NAME)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_3()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /** Generated Token Manager. */
  public ForteLangPrimeParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[21];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x0,0x80000,0x0,0x0,0x20300000,0x300000,0x300000,0x20000,0x20000,0x400,0x4000000a,0x40000008,0x2000000,0x0,0x0,0x0,0x200,0x20000,0x1c000000,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x2,0x80,0x400,0x400,0x400,0x0,0x0,0x0,0x400,0x0,0x0,0x2c1c,0x2c1c,0x4000,0x400,0xc,0x400,0x0,0x0,0x400,0x400,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[3];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor. */
  public ForteLangPrimeParser(Provider stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ForteLangPrimeParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public ForteLangPrimeParser(String dsl) throws ParseException, TokenMgrException {
      this(new StringProvider(dsl));
  }

  public void ReInit(String s) {
     ReInit(new StringProvider(s));
  }
  /** Reinitialise. */
  public void ReInit(Provider stream) {
	if (jj_input_stream == null) {
      jj_input_stream = new SimpleCharStream(stream, 1, 1);
   } else {
      jj_input_stream.ReInit(stream, 1, 1);
   }
   if (token_source == null) {
      token_source = new ForteLangPrimeParserTokenManager(jj_input_stream);
   }

    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public ForteLangPrimeParser(ForteLangPrimeParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ForteLangPrimeParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.RuntimeException { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) {
       return;
    }

    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];

      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }

      for (int[] oldentry : jj_expentries) {
        if (oldentry.length == jj_expentry.length) {
          boolean isMatched = true;

          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              isMatched = false;
              break;
            }

          }
          if (isMatched) {
            jj_expentries.add(jj_expentry);
            break;
          }
        }
      }

      if (pos != 0) {
        jj_lasttokens[(jj_endpos = pos) - 1] = kind;
      }
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[57];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 21; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 57; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage, token_source == null ? null : ForteLangPrimeParserTokenManager.lexStateNames[token_source.curLexState]);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 3; i++) {
      try {
        JJCalls p = jj_2_rtns[i];

        do {
          if (p.gen > jj_gen) {
            jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
            switch (i) {
              case 0: jj_3_1(); break;
              case 1: jj_3_2(); break;
              case 2: jj_3_3(); break;
            }
          }
          p = p.next;
        } while (p != null);

        } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }

    p.gen = jj_gen + xla - jj_la; 
    p.first = token;
    p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
