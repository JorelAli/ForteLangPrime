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

public class ForteLangPrimeParser implements ForteLangPrimeParserConstants {

        private static TypingContext typingContext;
        private static String currentFunctionName;

        public static FLPLibrary parse(String input) throws ParseException, IOException {
                typingContext = new TypingContext();
                InputStream inputStream = new ByteArrayInputStream(input.getBytes(Charset.forName("UTF-8")));
                return new ForteLangPrimeParser(new StreamProvider(inputStream)).input();
        }

        public static FLPLibrary parse(File input) throws ParseException, FileNotFoundException, IOException {
                typingContext = new TypingContext();
                return new ForteLangPrimeParser(new StreamProvider(new FileInputStream(input))).input();
        }

        public static TypingContext getTypingContext() {
                return ForteLangPrimeParser.typingContext;
        }

/** Main endpoint */
  final public FLPLibrary input() throws ParseException {FLPLibrary lib;
    lib = program();
    jj_consume_token(0);
return lib;
  }

  final public FLPLibrary program() throws ParseException {Token name; List<String> exports;
        List<TypeDeclaration> typeDeclarations;
        List<FLPFunction> functions;
    jj_consume_token(LIBRARY);
    name = jj_consume_token(VAR_NAME);
    jj_consume_token(OPENCBRACE);
    exports = exports();
    jj_consume_token(CLOSECBRACE);
    jj_consume_token(OPENCBRACE);
    //TODO: Implement type declarations
            //TODO: Make it so you can put type declarations anywhere, not just at the start of the file
            typeDeclarations = typeDeclarations();
    functions = functions();
    jj_consume_token(CLOSECBRACE);
return new FLPLibrary(name.image, exports, functions);
  }

  final public List<String> exports() throws ParseException {List<String> exports = new ArrayList<String>(); Token t;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EXPORT:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(EXPORT);
      t = jj_consume_token(VAR_NAME);
      jj_consume_token(SEMICOLON);
exports.add(t.image);
    }
return exports;
  }

  final public void epsilon() throws ParseException {
{ }
  }

  final public List<TypeDeclaration> typeDeclarations() throws ParseException {List<TypeDeclaration> list = new ArrayList<TypeDeclaration>();
    jj_consume_token(TYPE);
    jj_consume_token(VAR_NAME);
    jj_consume_token(EQUALS);
    recordType();
return list;
  }

  final public List<FLPFunction> functions() throws ParseException {List<FLPFunction> functions = new ArrayList<FLPFunction>();
        FLPFunction f;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LCHEVRON:
      case VAR_NAME:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      f = functionDeclaration();
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
      jj_la1[2] = jj_gen;
      ;
    }
    name = jj_consume_token(VAR_NAME);
ForteLangPrimeParser.currentFunctionName = name.image;
    functionTypes = functionTypes();
tf = Converter.functionTypesToTypeFunction(functionTypes);
                typingContext.addFunction(name.image, tf);
    jj_consume_token(EQUALS);
    expr = expression();
    jj_consume_token(SEMICOLON);
return new FLPFunction(name.image, tf, genericTypeDeclaration, expr);
  }

  final public List<TypeNamedGeneric> genericTypeDeclaration() throws ParseException {Token t;
        List<TypeNamedGeneric> genericNames = new ArrayList<TypeNamedGeneric>();
    jj_consume_token(LCHEVRON);
    t = jj_consume_token(VAR_NAME);
genericNames.add(new TypeNamedGeneric(t.image));
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_3;
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
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return expr;
  }

  final public ExprVariable variable() throws ParseException {Token t;
    t = jj_consume_token(VAR_NAME);
return new ExprVariable(t.beginLine, t.image, ForteLangPrimeParser.currentFunctionName);
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
      jj_la1[5] = jj_gen;
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
      jj_la1[6] = jj_gen;
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
      jj_la1[7] = jj_gen;
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
        jj_la1[8] = jj_gen;
        ;
      }
type = new TypeNamedGeneric(t.image);
      break;
      }
    case OPENCBRACE:{
      type = recordType();
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return type;
  }

  final public Type recordType() throws ParseException {
    jj_consume_token(OPENCBRACE);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case VAR_NAME:{
        ;
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        break label_4;
      }
      jj_consume_token(VAR_NAME);
      jj_consume_token(LCHEVRON);
      type();
      jj_consume_token(RCHEVRON);
      jj_consume_token(SEMICOLON);
    }
    jj_consume_token(CLOSECBRACE);
return null;
  }

  /** Generated Token Manager. */
  public ForteLangPrimeParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[11];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x40000,0x40000,0x400,0xc8000000,0xc0000000,0x0,0x200,0x40000,0x3800008,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x8,0x40,0x0,0x0,0x2c1,0x0,0x40,0x0,0x0,0x40,0x40,};
   }

  /** Constructor. */
  public ForteLangPrimeParser(Provider stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ForteLangPrimeParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public ForteLangPrimeParser(ForteLangPrimeParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ForteLangPrimeParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
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

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[52];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 11; i++) {
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
    for (int i = 0; i < 52; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
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

}
