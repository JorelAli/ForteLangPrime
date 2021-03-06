/* Generated by: ParserGeneratorCC: Do not edit this line. ForteLangPrimeParserConstants.java */
package dev.jorel.fortelangprime.parser;


/**
 * Token literal values and constants.
 * Generated by com.helger.pgcc.output.java.OtherFilesGenJava#start()
 */
public interface ForteLangPrimeParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int OPENBRACKET = 1;
  /** RegularExpression Id. */
  int CLOSEBRACKET = 2;
  /** RegularExpression Id. */
  int OPENCBRACE = 3;
  /** RegularExpression Id. */
  int CLOSECBRACE = 4;
  /** RegularExpression Id. */
  int OPENSBRACKET = 5;
  /** RegularExpression Id. */
  int CLOSESBRACKET = 6;
  /** RegularExpression Id. */
  int COLON = 7;
  /** RegularExpression Id. */
  int SEMICOLON = 8;
  /** RegularExpression Id. */
  int ARROW = 9;
  /** RegularExpression Id. */
  int COMMA = 10;
  /** RegularExpression Id. */
  int PLAY_BUTTON = 11;
  /** RegularExpression Id. */
  int REVERSE_PLAY_BUTTON = 12;
  /** RegularExpression Id. */
  int PIPE = 13;
  /** RegularExpression Id. */
  int EQUALS = 14;
  /** RegularExpression Id. */
  int LCHEVRON = 15;
  /** RegularExpression Id. */
  int RCHEVRON = 16;
  /** RegularExpression Id. */
  int STAR = 17;
  /** RegularExpression Id. */
  int PRINTABLE = 18;
  /** RegularExpression Id. */
  int EQUATABLE = 19;
  /** RegularExpression Id. */
  int CONCAT = 20;
  /** RegularExpression Id. */
  int PLUS = 21;
  /** RegularExpression Id. */
  int MINUS = 22;
  /** RegularExpression Id. */
  int SLASH = 23;
  /** RegularExpression Id. */
  int HAT = 24;
  /** RegularExpression Id. */
  int MOD = 25;
  /** RegularExpression Id. */
  int FULL_STOP = 26;
  /** RegularExpression Id. */
  int LESS_THAN_OR_EQUAL = 27;
  /** RegularExpression Id. */
  int GREATER_THAN_OR_EQUAL = 28;
  /** RegularExpression Id. */
  int EQUALS_EQUALS = 29;
  /** RegularExpression Id. */
  int NOT_EQUALS = 30;
  /** RegularExpression Id. */
  int AND_AND = 31;
  /** RegularExpression Id. */
  int OR_OR = 32;
  /** RegularExpression Id. */
  int TYPE_INT = 33;
  /** RegularExpression Id. */
  int TYPE_BOOL = 34;
  /** RegularExpression Id. */
  int TYPE_DOUBLE = 35;
  /** RegularExpression Id. */
  int TYPE_STRING = 36;
  /** RegularExpression Id. */
  int TYPE = 37;
  /** RegularExpression Id. */
  int IF = 38;
  /** RegularExpression Id. */
  int THEN = 39;
  /** RegularExpression Id. */
  int ELSE = 40;
  /** RegularExpression Id. */
  int INFIX = 41;
  /** RegularExpression Id. */
  int INFIXL = 42;
  /** RegularExpression Id. */
  int INFIXR = 43;
  /** RegularExpression Id. */
  int TRUE = 44;
  /** RegularExpression Id. */
  int FALSE = 45;
  /** RegularExpression Id. */
  int PANIC = 46;
  /** RegularExpression Id. */
  int LIBRARY = 47;
  /** RegularExpression Id. */
  int SCRIPT = 48;
  /** RegularExpression Id. */
  int EXPORT = 49;
  /** RegularExpression Id. */
  int IMPORT = 50;
  /** RegularExpression Id. */
  int AS = 51;
  /** RegularExpression Id. */
  int VAR_NAME = 52;
  /** RegularExpression Id. */
  int INT_LITERAL = 53;
  /** RegularExpression Id. */
  int NUMBER = 54;
  /** RegularExpression Id. */
  int STRING = 55;
  /** RegularExpression Id. */
  int CUSTOM_OPERATOR = 56;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int InsideMLC = 1;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\"(\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "\":\"",
    "\";\"",
    "\"->\"",
    "\",\"",
    "\"|>\"",
    "\"<|\"",
    "\"|\"",
    "\"=\"",
    "\"<\"",
    "\">\"",
    "\"*\"",
    "\"@Printable\"",
    "\"@Equatable\"",
    "\"++\"",
    "\"+\"",
    "\"-\"",
    "\"/\"",
    "\"^\"",
    "\"%\"",
    "\".\"",
    "\"<=\"",
    "\">=\"",
    "\"==\"",
    "\"!=\"",
    "\"&&\"",
    "\"||\"",
    "\"Int\"",
    "\"Bool\"",
    "\"Double\"",
    "\"String\"",
    "\"type\"",
    "\"if\"",
    "\"then\"",
    "\"else\"",
    "\"infix\"",
    "\"infixl\"",
    "\"infixr\"",
    "\"true\"",
    "\"false\"",
    "\"???\"",
    "\"Library\"",
    "\"Script\"",
    "\"export\"",
    "\"import\"",
    "\"as\"",
    "<VAR_NAME>",
    "<INT_LITERAL>",
    "<NUMBER>",
    "<STRING>",
    "<CUSTOM_OPERATOR>",
    "\"\\n\"",
    "\"\\r\"",
    "\" \"",
    "\"\\t\"",
    "\"\\f\"",
    "<token of kind 62>",
    "\"#[\"",
    "\"#[\"",
    "\"]#\"",
    "<token of kind 66>",
  };

}
