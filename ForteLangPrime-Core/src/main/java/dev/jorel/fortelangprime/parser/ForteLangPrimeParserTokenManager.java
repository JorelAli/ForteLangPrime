/* ForteLangPrimeParserTokenManager.java */
/* Generated by: ParserGeneratorCC: Do not edit this line. ForteLangPrimeParserTokenManager.java */
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
import dev.jorel.fortelangprime.ast.operation.*;
import dev.jorel.fortelangprime.compiler.*;
import dev.jorel.fortelangprime.util.*;

/** Token Manager. */
@SuppressWarnings ("unused")
public class ForteLangPrimeParserTokenManager implements ForteLangPrimeParserConstants {
 static int commentNesting = 0;
private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1){
   switch (pos)
   {
      case 0:
         if ((active0 & 0xffffe00000000L) != 0x0L)
         {
            jjmatchedKind = 52;
            return 1;
         }
         if ((active0 & 0x13ff3fa00L) != 0x0L)
            return 7;
         if ((active0 & 0xc00c0000L) != 0x0L)
         {
            jjmatchedKind = 56;
            return 7;
         }
         if ((active0 & 0x8000000000000000L) != 0x0L)
         {
            jjmatchedKind = 56;
            return 8;
         }
         return -1;
      case 1:
         if ((active0 & 0x7ffbe00000000L) != 0x0L)
         {
            jjmatchedKind = 52;
            jjmatchedPos = 1;
            return 1;
         }
         if ((active0 & 0x1f8101a00L) != 0x0L)
            return 7;
         if ((active0 & 0x8004000000000L) != 0x0L)
            return 1;
         if ((active0 & 0xc0000L) != 0x0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 56;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 2:
         if ((active0 & 0x200000000L) != 0x0L)
            return 1;
         if ((active0 & 0xc0000L) != 0x0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 56;
               jjmatchedPos = 0;
            }
            return -1;
         }
         if ((active0 & 0x7ffbc00000000L) != 0x0L)
         {
            jjmatchedKind = 52;
            jjmatchedPos = 2;
            return 1;
         }
         return -1;
      case 3:
         if ((active0 & 0x7ee1800000000L) != 0x0L)
         {
            jjmatchedKind = 52;
            jjmatchedPos = 3;
            return 1;
         }
         if ((active0 & 0x11a400000000L) != 0x0L)
            return 1;
         if ((active0 & 0xc0000L) != 0x0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 56;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 4:
         if ((active0 & 0x7801800000000L) != 0x0L)
         {
            if (jjmatchedPos != 4)
            {
               jjmatchedKind = 52;
               jjmatchedPos = 4;
            }
            return 1;
         }
         if ((active0 & 0x6e0000000000L) != 0x0L)
            return 1;
         if ((active0 & 0xc0000L) != 0x0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 56;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 5:
         if ((active0 & 0x800000000000L) != 0x0L)
         {
            jjmatchedKind = 52;
            jjmatchedPos = 5;
            return 1;
         }
         if ((active0 & 0x70c1800000000L) != 0x0L)
            return 1;
         if ((active0 & 0xc0000L) != 0x0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 56;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 6:
         if ((active0 & 0x800000000000L) != 0x0L)
            return 1;
         if ((active0 & 0xc0000L) != 0x0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 56;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 7:
         if ((active0 & 0xc0000L) != 0x0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 56;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 8:
         if ((active0 & 0xc0000L) != 0x0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 56;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0, long active1){
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0, active1), pos + 1);
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
      case '!':
         return jjMoveStringLiteralDfa1_0(0x40000000L);
      case '#':
         return jjMoveStringLiteralDfa1_0(0x8000000000000000L);
      case '%':
         return jjStartNfaWithStates_0(0, 25, 7);
      case '&':
         return jjMoveStringLiteralDfa1_0(0x80000000L);
      case '(':
         return jjStopAtPos(0, 1);
      case ')':
         return jjStopAtPos(0, 2);
      case '*':
         return jjStartNfaWithStates_0(0, 17, 7);
      case '+':
         jjmatchedKind = 21;
         return jjMoveStringLiteralDfa1_0(0x100000L);
      case ',':
         return jjStopAtPos(0, 10);
      case '-':
         jjmatchedKind = 22;
         return jjMoveStringLiteralDfa1_0(0x200L);
      case '.':
         return jjStartNfaWithStates_0(0, 26, 7);
      case '/':
         return jjStartNfaWithStates_0(0, 23, 7);
      case ':':
         return jjStopAtPos(0, 7);
      case ';':
         return jjStopAtPos(0, 8);
      case '<':
         jjmatchedKind = 15;
         return jjMoveStringLiteralDfa1_0(0x8001000L);
      case '=':
         jjmatchedKind = 14;
         return jjMoveStringLiteralDfa1_0(0x20000000L);
      case '>':
         jjmatchedKind = 16;
         return jjMoveStringLiteralDfa1_0(0x10000000L);
      case '@':
         return jjMoveStringLiteralDfa1_0(0xc0000L);
      case 'B':
         return jjMoveStringLiteralDfa1_0(0x400000000L);
      case 'D':
         return jjMoveStringLiteralDfa1_0(0x800000000L);
      case 'I':
         return jjMoveStringLiteralDfa1_0(0x200000000L);
      case 'L':
         return jjMoveStringLiteralDfa1_0(0x800000000000L);
      case 'S':
         return jjMoveStringLiteralDfa1_0(0x1001000000000L);
      case '[':
         return jjStopAtPos(0, 5);
      case ']':
         return jjStopAtPos(0, 6);
      case '^':
         return jjStartNfaWithStates_0(0, 24, 7);
      case 'a':
         return jjMoveStringLiteralDfa1_0(0x8000000000000L);
      case 'e':
         return jjMoveStringLiteralDfa1_0(0x2010000000000L);
      case 'f':
         return jjMoveStringLiteralDfa1_0(0x200000000000L);
      case 'i':
         return jjMoveStringLiteralDfa1_0(0x40e4000000000L);
      case 'p':
         return jjMoveStringLiteralDfa1_0(0x400000000000L);
      case 't':
         return jjMoveStringLiteralDfa1_0(0x10a000000000L);
      case '{':
         return jjStopAtPos(0, 3);
      case '|':
         jjmatchedKind = 13;
         return jjMoveStringLiteralDfa1_0(0x100000800L);
      case '}':
         return jjStopAtPos(0, 4);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0, 0L);
      return 1;
   }
   switch(curChar)
   {
      case '&':
         if ((active0 & 0x80000000L) != 0x0L)
            return jjStartNfaWithStates_0(1, 31, 7);
         break;
      case '+':
         if ((active0 & 0x100000L) != 0x0L)
            return jjStartNfaWithStates_0(1, 20, 7);
         break;
      case '=':
         if ((active0 & 0x8000000L) != 0x0L)
            return jjStartNfaWithStates_0(1, 27, 7);
         else if ((active0 & 0x10000000L) != 0x0L)
            return jjStartNfaWithStates_0(1, 28, 7);
         else if ((active0 & 0x20000000L) != 0x0L)
            return jjStartNfaWithStates_0(1, 29, 7);
         else if ((active0 & 0x40000000L) != 0x0L)
            return jjStartNfaWithStates_0(1, 30, 7);
         break;
      case '>':
         if ((active0 & 0x200L) != 0x0L)
            return jjStartNfaWithStates_0(1, 9, 7);
         else if ((active0 & 0x800L) != 0x0L)
            return jjStartNfaWithStates_0(1, 11, 7);
         break;
      case 'E':
         return jjMoveStringLiteralDfa2_0(active0, 0x80000L);
      case 'P':
         return jjMoveStringLiteralDfa2_0(active0, 0x40000L);
      case '[':
         if ((active0 & 0x8000000000000000L) != 0x0L)
            return jjStopAtPos(1, 63);
         break;
      case 'a':
         return jjMoveStringLiteralDfa2_0(active0, 0x600000000000L);
      case 'c':
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000000000L);
      case 'f':
         if ((active0 & 0x4000000000L) != 0x0L)
            return jjStartNfaWithStates_0(1, 38, 1);
         break;
      case 'h':
         return jjMoveStringLiteralDfa2_0(active0, 0x8000000000L);
      case 'i':
         return jjMoveStringLiteralDfa2_0(active0, 0x800000000000L);
      case 'l':
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000000L);
      case 'm':
         return jjMoveStringLiteralDfa2_0(active0, 0x4000000000000L);
      case 'n':
         return jjMoveStringLiteralDfa2_0(active0, 0xe0200000000L);
      case 'o':
         return jjMoveStringLiteralDfa2_0(active0, 0xc00000000L);
      case 'r':
         return jjMoveStringLiteralDfa2_0(active0, 0x100000000000L);
      case 's':
         if ((active0 & 0x8000000000000L) != 0x0L)
            return jjStartNfaWithStates_0(1, 51, 1);
         break;
      case 't':
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000000L);
      case 'x':
         return jjMoveStringLiteralDfa2_0(active0, 0x2000000000000L);
      case 'y':
         return jjMoveStringLiteralDfa2_0(active0, 0x2000000000L);
      case '|':
         if ((active0 & 0x1000L) != 0x0L)
            return jjStartNfaWithStates_0(1, 12, 7);
         else if ((active0 & 0x100000000L) != 0x0L)
            return jjStartNfaWithStates_0(1, 32, 7);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0, 0L);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0, 0L);
      return 2;
   }
   switch(curChar)
   {
      case 'b':
         return jjMoveStringLiteralDfa3_0(active0, 0x800000000000L);
      case 'e':
         return jjMoveStringLiteralDfa3_0(active0, 0x8000000000L);
      case 'f':
         return jjMoveStringLiteralDfa3_0(active0, 0xe0000000000L);
      case 'l':
         return jjMoveStringLiteralDfa3_0(active0, 0x200000000000L);
      case 'n':
         return jjMoveStringLiteralDfa3_0(active0, 0x400000000000L);
      case 'o':
         return jjMoveStringLiteralDfa3_0(active0, 0x400000000L);
      case 'p':
         return jjMoveStringLiteralDfa3_0(active0, 0x6002000000000L);
      case 'q':
         return jjMoveStringLiteralDfa3_0(active0, 0x80000L);
      case 'r':
         return jjMoveStringLiteralDfa3_0(active0, 0x1001000040000L);
      case 's':
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000000L);
      case 't':
         if ((active0 & 0x200000000L) != 0x0L)
            return jjStartNfaWithStates_0(2, 33, 1);
         break;
      case 'u':
         return jjMoveStringLiteralDfa3_0(active0, 0x100800000000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0, 0L);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0, 0L);
      return 3;
   }
   switch(curChar)
   {
      case 'b':
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000L);
      case 'e':
         if ((active0 & 0x2000000000L) != 0x0L)
            return jjStartNfaWithStates_0(3, 37, 1);
         else if ((active0 & 0x10000000000L) != 0x0L)
            return jjStartNfaWithStates_0(3, 40, 1);
         else if ((active0 & 0x100000000000L) != 0x0L)
            return jjStartNfaWithStates_0(3, 44, 1);
         break;
      case 'i':
         return jjMoveStringLiteralDfa4_0(active0, 0x14e1000040000L);
      case 'l':
         if ((active0 & 0x400000000L) != 0x0L)
            return jjStartNfaWithStates_0(3, 34, 1);
         break;
      case 'n':
         if ((active0 & 0x8000000000L) != 0x0L)
            return jjStartNfaWithStates_0(3, 39, 1);
         break;
      case 'o':
         return jjMoveStringLiteralDfa4_0(active0, 0x6000000000000L);
      case 'r':
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000000L);
      case 's':
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000000L);
      case 'u':
         return jjMoveStringLiteralDfa4_0(active0, 0x80000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0, 0L);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0, 0L);
      return 4;
   }
   switch(curChar)
   {
      case 'a':
         return jjMoveStringLiteralDfa5_0(active0, 0x800000080000L);
      case 'c':
         if ((active0 & 0x400000000000L) != 0x0L)
            return jjStartNfaWithStates_0(4, 46, 1);
         break;
      case 'e':
         if ((active0 & 0x200000000000L) != 0x0L)
            return jjStartNfaWithStates_0(4, 45, 1);
         break;
      case 'l':
         return jjMoveStringLiteralDfa5_0(active0, 0x800000000L);
      case 'n':
         return jjMoveStringLiteralDfa5_0(active0, 0x1000040000L);
      case 'p':
         return jjMoveStringLiteralDfa5_0(active0, 0x1000000000000L);
      case 'r':
         return jjMoveStringLiteralDfa5_0(active0, 0x6000000000000L);
      case 'x':
         if ((active0 & 0x20000000000L) != 0x0L)
         {
            jjmatchedKind = 41;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0xc0000000000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0, 0L);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0, 0L);
      return 5;
   }
   switch(curChar)
   {
      case 'e':
         if ((active0 & 0x800000000L) != 0x0L)
            return jjStartNfaWithStates_0(5, 35, 1);
         break;
      case 'g':
         if ((active0 & 0x1000000000L) != 0x0L)
            return jjStartNfaWithStates_0(5, 36, 1);
         break;
      case 'l':
         if ((active0 & 0x40000000000L) != 0x0L)
            return jjStartNfaWithStates_0(5, 42, 1);
         break;
      case 'r':
         if ((active0 & 0x80000000000L) != 0x0L)
            return jjStartNfaWithStates_0(5, 43, 1);
         return jjMoveStringLiteralDfa6_0(active0, 0x800000000000L);
      case 't':
         if ((active0 & 0x1000000000000L) != 0x0L)
            return jjStartNfaWithStates_0(5, 48, 1);
         else if ((active0 & 0x2000000000000L) != 0x0L)
            return jjStartNfaWithStates_0(5, 49, 1);
         else if ((active0 & 0x4000000000000L) != 0x0L)
            return jjStartNfaWithStates_0(5, 50, 1);
         return jjMoveStringLiteralDfa6_0(active0, 0xc0000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0, 0L);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0, 0L);
      return 6;
   }
   switch(curChar)
   {
      case 'a':
         return jjMoveStringLiteralDfa7_0(active0, 0xc0000L);
      case 'y':
         if ((active0 & 0x800000000000L) != 0x0L)
            return jjStartNfaWithStates_0(6, 47, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0, 0L);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0, 0L);
      return 7;
   }
   switch(curChar)
   {
      case 'b':
         return jjMoveStringLiteralDfa8_0(active0, 0xc0000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0, 0L);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0, 0L);
      return 8;
   }
   switch(curChar)
   {
      case 'l':
         return jjMoveStringLiteralDfa9_0(active0, 0xc0000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0, 0L);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0, 0L);
      return 9;
   }
   switch(curChar)
   {
      case 'e':
         if ((active0 & 0x40000L) != 0x0L)
            return jjStopAtPos(9, 18);
         else if ((active0 & 0x80000L) != 0x0L)
            return jjStopAtPos(9, 19);
         break;
      default :
         break;
   }
   return jjStartNfa_0(8, active0, 0L);
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
   jjnewStateCnt = 19;
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
                  if ((0xf000ec7a00000000L & l) != 0x0L)
                  {
                     if (kind > 56)
                        kind = 56;
                     { jjCheckNAdd(7); }
                  }
                  else if ((0x3ff000000000000L & l) != 0x0L)
                  {
                     if (kind > 53)
                        kind = 53;
                     { jjCheckNAddStates(0, 2); }
                  }
                  else if (curChar == 34)
                     { jjCheckNAddStates(3, 5); }
                  if (curChar == 35)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 8:
                  if ((0xf000ec7a00000000L & l) != 0x0L)
                  {
                     if (kind > 56)
                        kind = 56;
                     { jjCheckNAdd(7); }
                  }
                  if (curChar == 35)
                     { jjCheckNAddStates(6, 8); }
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 52)
                     kind = 52;
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
                  if ((0xfffffffbffffffffL & l) != 0x0L)
                     { jjCheckNAddStates(3, 5); }
                  break;
               case 6:
                  if (curChar == 34 && kind > 55)
                     kind = 55;
                  break;
               case 7:
                  if ((0xf000ec7a00000000L & l) == 0x0L)
                     break;
                  if (kind > 56)
                     kind = 56;
                  { jjCheckNAdd(7); }
                  break;
               case 9:
                  if ((0xffffffffffffdbffL & l) != 0x0L)
                     { jjCheckNAddStates(6, 8); }
                  break;
               case 10:
                  if ((0x2400L & l) != 0x0L && kind > 62)
                     kind = 62;
                  break;
               case 11:
                  if (curChar == 10 && kind > 62)
                     kind = 62;
                  break;
               case 12:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 13:
                  if (curChar == 35)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 14:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 53)
                     kind = 53;
                  { jjCheckNAddStates(0, 2); }
                  break;
               case 15:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 53)
                     kind = 53;
                  { jjCheckNAdd(15); }
                  break;
               case 16:
                  if (curChar != 46)
                     break;
                  if (kind > 54)
                     kind = 54;
                  { jjCheckNAdd(17); }
                  break;
               case 17:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 54)
                     kind = 54;
                  { jjCheckNAdd(17); }
                  break;
               case 18:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 54)
                     kind = 54;
                  { jjCheckNAddStates(9, 11); }
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
                  if ((0x7fffffe87fffffeL & l) != 0x0L)
                  {
                     if (kind > 52)
                        kind = 52;
                     { jjCheckNAdd(1); }
                  }
                  else if ((0x5000000050000001L & l) != 0x0L)
                  {
                     if (kind > 56)
                        kind = 56;
                     { jjCheckNAdd(7); }
                  }
                  break;
               case 8:
               case 7:
                  if ((0x5000000050000001L & l) == 0x0L)
                     break;
                  if (kind > 56)
                     kind = 56;
                  { jjCheckNAdd(7); }
                  break;
               case 1:
                  if ((0x7fffffe87fffffeL & l) == 0x0L)
                     break;
                  if (kind > 52)
                     kind = 52;
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
                  if ((0xffffffffefffffffL & l) != 0x0L)
                     { jjCheckNAddStates(3, 5); }
                  break;
               case 9:
                  { jjAddStates(6, 8); }
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
               case 9:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     { jjAddStates(6, 8); }
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
      i = jjnewStateCnt;
      jjnewStateCnt = startsAt;
      startsAt = 19 - jjnewStateCnt;
      if (i == startsAt)
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_1(){
   switch(curChar)
   {
      case '#':
         return jjMoveStringLiteralDfa1_1(0x1L);
      case ']':
         return jjMoveStringLiteralDfa1_1(0x2L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_1(long active1){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case '#':
         if ((active1 & 0x2L) != 0x0L)
            return jjStopAtPos(1, 65);
         break;
      case '[':
         if ((active1 & 0x1L) != 0x0L)
            return jjStopAtPos(1, 64);
         break;
      default :
         return 2;
   }
   return 2;
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", "\50", "\51", "\173", "\175", "\133", "\135", "\72", "\73", "\55\76", 
"\54", "\174\76", "\74\174", "\174", "\75", "\74", "\76", "\52", 
"\100\120\162\151\156\164\141\142\154\145", "\100\105\161\165\141\164\141\142\154\145", "\53\53", "\53", "\55", "\57", 
"\136", "\45", "\56", "\74\75", "\76\75", "\75\75", "\41\75", "\46\46", "\174\174", 
"\111\156\164", "\102\157\157\154", "\104\157\165\142\154\145", "\123\164\162\151\156\147", 
"\164\171\160\145", "\151\146", "\164\150\145\156", "\145\154\163\145", "\151\156\146\151\170", 
"\151\156\146\151\170\154", "\151\156\146\151\170\162", "\164\162\165\145", "\146\141\154\163\145", 
"\160\141\156\151\143", "\114\151\142\162\141\162\171", "\123\143\162\151\160\164", 
"\145\170\160\157\162\164", "\151\155\160\157\162\164", "\141\163", null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = im == null ? input_stream.getImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind);
   t.kind = jjmatchedKind;
   t.image = curTokenImage;

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}
static final int[] jjnextStates = {
   15, 16, 18, 3, 5, 6, 9, 10, 12, 16, 17, 18, 
};

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

  EOFLoop:
  for (;;)
  {
   try
   {
      curChar = input_stream.beginToken();
   }
   catch(final Exception e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      return matchedToken;
   }
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   for (;;)
   {
     switch(curLexState)
     {
       case 0:
         try {
           input_stream.backup(0);
            while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0x0L)
               curChar = input_stream.beginToken();
         }
         catch (final java.io.IOException e1) {
           continue EOFLoop;
         }
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_0();
         break;
       case 1:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_1();
         if (jjmatchedPos == 0 && jjmatchedKind > 66)
         {
            jjmatchedKind = 66;
         }
         break;
     }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else if ((jjtoSkip[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           SkipLexicalActions(null);
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
        jjimageLen += jjmatchedPos + 1;
      if (jjnewLexState[jjmatchedKind] != -1)
        curLexState = jjnewLexState[jjmatchedKind];
        curPos = 0;
        jjmatchedKind = 0x7fffffff;
        try {
           curChar = input_stream.readChar();
           continue;
        }
        catch (final java.io.IOException e1) { }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try {
       input_stream.readChar();
       input_stream.backup(1);
     }
     catch (final java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.getImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.getImage();
     }
     throw new TokenMgrException(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrException.LEXICAL_ERROR);
   }
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      case 63 :
         image.append(input_stream.getSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
   commentNesting++;
         break;
      case 64 :
         image.append(input_stream.getSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
  commentNesting++;
         break;
      case 65 :
         image.append(input_stream.getSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
  commentNesting--;
  if (commentNesting == 0)
  {
   SwitchTo(DEFAULT);
  }
         break;
      default :
         break;
   }
}
void MoreLexicalActions()
{
   jjimageLen += (lengthOfMatch = jjmatchedPos + 1);
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void TokenLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
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


    jjmatchedPos =
    jjnewStateCnt =
    0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 19; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream, int lexState)
  {
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(int lexState)
  {
    if (lexState >= 2 || lexState < 0)
      throw new TokenMgrException("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrException.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }


/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "InsideMLC",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0x1ffffffffffffffL, 0x0L, 
};
static final long[] jjtoSkip = {
   0xfe00000000000000L, 0x3L, 
};
static final long[] jjtoSpecial = {
   0x0L, 0x0L, 
};
static final long[] jjtoMore = {
   0x0L, 0x4L, 
};
    protected SimpleCharStream  input_stream;

    private final int[] jjrounds = new int[19];
    private final int[] jjstateSet = new int[2 * 19];
    private final StringBuilder jjimage = new StringBuilder();
    private StringBuilder image = jjimage;
    private int jjimageLen;
    private int lengthOfMatch;
    protected int curChar;
}
