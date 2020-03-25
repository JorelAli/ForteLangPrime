/* ForteLangPrimeParserTokenManager.java */
/* Generated By:JavaCC: Do not edit this line. ForteLangPrimeParserTokenManager.java */
package dev.jorel.fortelangprime.parser;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import dev.jorel.fortelangprime.ast.FLPFunction;
import dev.jorel.fortelangprime.ast.FLPLibrary;
import dev.jorel.fortelangprime.ast.expressions.Expr;
import dev.jorel.fortelangprime.ast.expressions.ExprBoolLit;
import dev.jorel.fortelangprime.ast.expressions.ExprIntLit;
import dev.jorel.fortelangprime.ast.expressions.ExprStringLit;
import dev.jorel.fortelangprime.ast.expressions.ExprPanic;
import dev.jorel.fortelangprime.ast.types.Type;

/** Token Manager. */
@SuppressWarnings("unused")public class ForteLangPrimeParserTokenManager implements ForteLangPrimeParserConstants {

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0){
   switch (pos)
   {
      case 0:
         if ((active0 & 0x7ff800000L) != 0L)
         {
            jjmatchedKind = 35;
            return 1;
         }
         return -1;
      case 1:
         if ((active0 & 0x3ef800000L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 35;
               jjmatchedPos = 1;
            }
            return 1;
         }
         if ((active0 & 0x410000000L) != 0L)
            return 1;
         return -1;
      case 2:
         if ((active0 & 0x3ef000000L) != 0L)
         {
            jjmatchedKind = 35;
            jjmatchedPos = 2;
            return 1;
         }
         if ((active0 & 0x800000L) != 0L)
            return 1;
         return -1;
      case 3:
         if ((active0 & 0x38a000000L) != 0L)
         {
            jjmatchedKind = 35;
            jjmatchedPos = 3;
            return 1;
         }
         if ((active0 & 0x65000000L) != 0L)
            return 1;
         return -1;
      case 4:
         if ((active0 & 0x88000000L) != 0L)
            return 1;
         if ((active0 & 0x302000000L) != 0L)
         {
            jjmatchedKind = 35;
            jjmatchedPos = 4;
            return 1;
         }
         return -1;
      case 5:
         if ((active0 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 35;
            jjmatchedPos = 5;
            return 1;
         }
         if ((active0 & 0x202000000L) != 0L)
            return 1;
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0){
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0(){
   switch(curChar)
   {
      case 40:
         return jjStopAtPos(0, 1);
      case 41:
         return jjStopAtPos(0, 2);
      case 43:
         jjmatchedKind = 21;
         return jjMoveStringLiteralDfa1_0(0x100000L);
      case 44:
         return jjStopAtPos(0, 10);
      case 45:
         return jjMoveStringLiteralDfa1_0(0x1200L);
      case 58:
         return jjStopAtPos(0, 7);
      case 59:
         return jjStopAtPos(0, 8);
      case 60:
         return jjStopAtPos(0, 18);
      case 61:
         jjmatchedKind = 17;
         return jjMoveStringLiteralDfa1_0(0x800L);
      case 62:
         return jjStopAtPos(0, 19);
      case 63:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 64:
         return jjStopAtPos(0, 14);
      case 66:
         return jjMoveStringLiteralDfa1_0(0x1000000L);
      case 73:
         return jjMoveStringLiteralDfa1_0(0x800000L);
      case 76:
         return jjMoveStringLiteralDfa1_0(0x100000000L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x2000000L);
      case 91:
         return jjStopAtPos(0, 5);
      case 93:
         return jjStopAtPos(0, 6);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x400000000L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x240000000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x8000000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x10000000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x80000000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x24000000L);
      case 123:
         return jjStopAtPos(0, 3);
      case 124:
         jjmatchedKind = 16;
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 125:
         return jjStopAtPos(0, 4);
      case 126:
         return jjMoveStringLiteralDfa1_0(0x400000L);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 43:
         if ((active0 & 0x100000L) != 0L)
            return jjStopAtPos(1, 20);
         break;
      case 58:
         if ((active0 & 0x8000L) != 0L)
            return jjStopAtPos(1, 15);
         break;
      case 61:
         if ((active0 & 0x400000L) != 0L)
            return jjStopAtPos(1, 22);
         break;
      case 62:
         if ((active0 & 0x200L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 1;
         }
         else if ((active0 & 0x800L) != 0L)
            return jjStopAtPos(1, 11);
         else if ((active0 & 0x2000L) != 0L)
            return jjStopAtPos(1, 13);
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x88000000L);
      case 102:
         if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(1, 28, 1);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000000L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x100000000L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000000L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000000L);
      case 115:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(1, 34, 1);
         break;
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000000L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x200000000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 62:
         if ((active0 & 0x1000L) != 0L)
            return jjStopAtPos(2, 12);
         break;
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x100000000L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x20000000L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000000L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000000L);
      case 116:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(2, 23, 1);
         break;
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(3, 26, 1);
         else if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(3, 30, 1);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x82000000L);
      case 108:
         if ((active0 & 0x1000000L) != 0L)
            return jjStartNfaWithStates_0(3, 24, 1);
         break;
      case 110:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(3, 29, 1);
         break;
      case 111:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000L);
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x100000000L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x100000000L);
      case 99:
         if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(4, 31, 1);
         break;
      case 101:
         if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(4, 27, 1);
         break;
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x2000000L);
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x200000000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 103:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(5, 25, 1);
         break;
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x100000000L);
      case 116:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(5, 33, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 121:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(6, 32, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 14;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 36)
                        kind = 36;
                     { jjCheckNAddStates(0, 2); }
                  }
                  else if (curChar == 35)
                  {
                     if (kind > 43)
                        kind = 43;
                     { jjCheckNAdd(8); }
                  }
                  else if (curChar == 34)
                     { jjCheckNAddStates(3, 5); }
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 35)
                     kind = 35;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 2:
                  if (curChar == 34)
                     { jjCheckNAddStates(3, 5); }
                  break;
               case 4:
                  { jjCheckNAddStates(3, 5); }
                  break;
               case 5:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     { jjCheckNAddStates(3, 5); }
                  break;
               case 6:
                  if (curChar == 34 && kind > 38)
                     kind = 38;
                  break;
               case 7:
                  if (curChar != 35)
                     break;
                  if (kind > 43)
                     kind = 43;
                  { jjCheckNAdd(8); }
                  break;
               case 8:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  if (kind > 43)
                     kind = 43;
                  { jjCheckNAdd(8); }
                  break;
               case 9:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 36)
                     kind = 36;
                  { jjCheckNAddStates(0, 2); }
                  break;
               case 10:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 36)
                     kind = 36;
                  { jjCheckNAdd(10); }
                  break;
               case 11:
                  if (curChar != 46)
                     break;
                  if (kind > 37)
                     kind = 37;
                  { jjCheckNAdd(12); }
                  break;
               case 12:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 37)
                     kind = 37;
                  { jjCheckNAdd(12); }
                  break;
               case 13:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 37)
                     kind = 37;
                  { jjCheckNAddStates(6, 8); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 1:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 35)
                     kind = 35;
                  { jjCheckNAdd(1); }
                  break;
               case 3:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 4:
                  { jjCheckNAddStates(3, 5); }
                  break;
               case 5:
                  if ((0xffffffffefffffffL & l) != 0L)
                     { jjCheckNAddStates(3, 5); }
                  break;
               case 8:
                  if (kind > 43)
                     kind = 43;
                  jjstateSet[jjnewStateCnt++] = 8;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
               case 5:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     { jjCheckNAddStates(3, 5); }
                  break;
               case 8:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 43)
                     kind = 43;
                  jjstateSet[jjnewStateCnt++] = 8;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 14 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   10, 11, 13, 3, 5, 6, 11, 12, 13, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", "\50", "\51", "\173", "\175", "\133", "\135", "\72", "\73", "\55\76", 
"\54", "\75\76", "\55\76\76", "\174\76", "\100", "\77\72", "\174", "\75", "\74", 
"\76", "\53\53", "\53", "\176\75", "\111\156\164", "\102\157\157\154", 
"\123\164\162\151\156\147", "\164\162\165\145", "\146\141\154\163\145", "\151\146", "\164\150\145\156", 
"\145\154\163\145", "\160\141\156\151\143", "\114\151\142\162\141\162\171", 
"\145\170\160\157\162\164", "\141\163", null, null, null, null, null, null, null, null, null, };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(Exception e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public ForteLangPrimeParserTokenManager(SimpleCharStream stream){

      if (SimpleCharStream.staticFlag)
            throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");

    input_stream = stream;
  }

  /** Constructor. */
  public ForteLangPrimeParserTokenManager (SimpleCharStream stream, int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream)
  {
	
    jjmatchedPos = jjnewStateCnt = 0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 14; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit( SimpleCharStream stream, int lexState)
  {
  
    ReInit( stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(int lexState)
  {
    if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x7fffffffffL, 
};
static final long[] jjtoSkip = {
   0xf8000000000L, 
};
    protected SimpleCharStream  input_stream;

    private final int[] jjrounds = new int[14];
    private final int[] jjstateSet = new int[2 * 14];

    
    protected int curChar;
}
