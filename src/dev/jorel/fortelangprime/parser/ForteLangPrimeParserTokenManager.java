/* ForteLangPrimeParserTokenManager.java */
/* Generated By:JavaCC: Do not edit this line. ForteLangPrimeParserTokenManager.java */
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
@SuppressWarnings("unused")public class ForteLangPrimeParserTokenManager implements ForteLangPrimeParserConstants {
    static int commentNesting = 0;

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1){
   switch (pos)
   {
      case 0:
         if ((active0 & 0x7ffff000000000L) != 0L)
         {
            jjmatchedKind = 55;
            return 1;
         }
         if ((active1 & 0x4L) != 0L)
         {
            jjmatchedKind = 59;
            return 8;
         }
         if ((active0 & 0x600608000L) != 0L)
         {
            jjmatchedKind = 59;
            return 7;
         }
         if ((active0 & 0x9ff9f7a00L) != 0L)
            return 7;
         return -1;
      case 1:
         if ((active0 & 0x3ffdf000000000L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 55;
               jjmatchedPos = 1;
            }
            return 1;
         }
         if ((active0 & 0x600000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 59;
               jjmatchedPos = 0;
            }
            return -1;
         }
         if ((active0 & 0x40020000000000L) != 0L)
            return 1;
         if ((active0 & 0xfc0807a00L) != 0L)
            return 7;
         return -1;
      case 2:
         if ((active0 & 0x3ffde000000000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 2;
            return 1;
         }
         if ((active0 & 0x600000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 59;
               jjmatchedPos = 0;
            }
            return -1;
         }
         if ((active0 & 0x1000000000L) != 0L)
            return 1;
         if ((active0 & 0x1000L) != 0L)
            return 7;
         return -1;
      case 3:
         if ((active0 & 0x600000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 59;
               jjmatchedPos = 0;
            }
            return -1;
         }
         if ((active0 & 0x3f70c000000000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 3;
            return 1;
         }
         if ((active0 & 0x8d2000000000L) != 0L)
            return 1;
         return -1;
      case 4:
         if ((active0 & 0x600000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 59;
               jjmatchedPos = 0;
            }
            return -1;
         }
         if ((active0 & 0x3c00c000000000L) != 0L)
         {
            if (jjmatchedPos != 4)
            {
               jjmatchedKind = 55;
               jjmatchedPos = 4;
            }
            return 1;
         }
         if ((active0 & 0x3700000000000L) != 0L)
            return 1;
         return -1;
      case 5:
         if ((active0 & 0x600000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 59;
               jjmatchedPos = 0;
            }
            return -1;
         }
         if ((active0 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 5;
            return 1;
         }
         if ((active0 & 0x3860c000000000L) != 0L)
            return 1;
         return -1;
      case 6:
         if ((active0 & 0x600000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 59;
               jjmatchedPos = 0;
            }
            return -1;
         }
         if ((active0 & 0x4000000000000L) != 0L)
            return 1;
         return -1;
      case 7:
         if ((active0 & 0x600000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 59;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 8:
         if ((active0 & 0x600000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 59;
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
      case 33:
         return jjMoveStringLiteralDfa1_0(0x200000000L, 0x0L);
      case 35:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x4L);
      case 37:
         return jjStartNfaWithStates_0(0, 28, 7);
      case 38:
         return jjMoveStringLiteralDfa1_0(0x400000000L, 0x0L);
      case 40:
         return jjStopAtPos(0, 1);
      case 41:
         return jjStopAtPos(0, 2);
      case 42:
         return jjStartNfaWithStates_0(0, 20, 7);
      case 43:
         jjmatchedKind = 24;
         return jjMoveStringLiteralDfa1_0(0x800000L, 0x0L);
      case 44:
         return jjStopAtPos(0, 10);
      case 45:
         jjmatchedKind = 25;
         return jjMoveStringLiteralDfa1_0(0x1200L, 0x0L);
      case 46:
         return jjStartNfaWithStates_0(0, 29, 7);
      case 47:
         return jjStartNfaWithStates_0(0, 26, 7);
      case 58:
         return jjStopAtPos(0, 7);
      case 59:
         return jjStopAtPos(0, 8);
      case 60:
         jjmatchedKind = 18;
         return jjMoveStringLiteralDfa1_0(0x40004000L, 0x0L);
      case 61:
         jjmatchedKind = 17;
         return jjMoveStringLiteralDfa1_0(0x100000800L, 0x0L);
      case 62:
         jjmatchedKind = 19;
         return jjMoveStringLiteralDfa1_0(0x80000000L, 0x0L);
      case 63:
         return jjMoveStringLiteralDfa1_0(0x8000L, 0x0L);
      case 64:
         return jjMoveStringLiteralDfa1_0(0x600000L, 0x0L);
      case 66:
         return jjMoveStringLiteralDfa1_0(0x2000000000L, 0x0L);
      case 68:
         return jjMoveStringLiteralDfa1_0(0x4000000000L, 0x0L);
      case 73:
         return jjMoveStringLiteralDfa1_0(0x1000000000L, 0x0L);
      case 76:
         return jjMoveStringLiteralDfa1_0(0x4000000000000L, 0x0L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x8008000000000L, 0x0L);
      case 91:
         return jjStopAtPos(0, 5);
      case 93:
         return jjStopAtPos(0, 6);
      case 94:
         return jjStartNfaWithStates_0(0, 27, 7);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x40000000000000L, 0x0L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x10080000000000L, 0x0L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x1000000000000L, 0x0L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x20720000000000L, 0x0L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x2000000000000L, 0x0L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x850000000000L, 0x0L);
      case 123:
         return jjStopAtPos(0, 3);
      case 124:
         jjmatchedKind = 16;
         return jjMoveStringLiteralDfa1_0(0x800002000L, 0x0L);
      case 125:
         return jjStopAtPos(0, 4);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0, long active1){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0, active1);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(1, 34, 7);
         break;
      case 43:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(1, 23, 7);
         break;
      case 58:
         if ((active0 & 0x8000L) != 0L)
            return jjStopAtPos(1, 15);
         break;
      case 61:
         if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(1, 30, 7);
         else if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(1, 31, 7);
         else if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(1, 32, 7);
         else if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(1, 33, 7);
         break;
      case 62:
         if ((active0 & 0x200L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 1;
         }
         else if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(1, 11, 7);
         else if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(1, 13, 7);
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L, active1, 0L);
      case 69:
         return jjMoveStringLiteralDfa2_0(active0, 0x400000L, active1, 0L);
      case 80:
         return jjMoveStringLiteralDfa2_0(active0, 0x200000L, active1, 0L);
      case 91:
         if ((active1 & 0x4L) != 0L)
            return jjStopAtPos(1, 66);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x3000000000000L, active1, 0L);
      case 99:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000000000000L, active1, 0L);
      case 102:
         if ((active0 & 0x20000000000L) != 0L)
            return jjStartNfaWithStates_0(1, 41, 1);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000000000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000000000000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x80000000000L, active1, 0L);
      case 109:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000000000000L, active1, 0L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x701000000000L, active1, 0L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x6000000000L, active1, 0L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000000000L, active1, 0L);
      case 115:
         if ((active0 & 0x40000000000000L) != 0L)
            return jjStartNfaWithStates_0(1, 54, 1);
         break;
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000000000L, active1, 0L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000000000L, active1, 0L);
      case 121:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000000L, active1, 0L);
      case 124:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(1, 14, 7);
         else if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(1, 35, 7);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0, active1);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1){
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(0, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0, 0L);
      return 2;
   }
   switch(curChar)
   {
      case 62:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(2, 12, 7);
         break;
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000000000000L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000000000L);
      case 102:
         return jjMoveStringLiteralDfa3_0(active0, 0x700000000000L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000000000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000000L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x30010000000000L);
      case 113:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x8008000200000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000000000L);
      case 116:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 36, 1);
         break;
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x804000000000L);
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
      case 98:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000000000L);
      case 101:
         if ((active0 & 0x10000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 40, 1);
         else if ((active0 & 0x80000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 43, 1);
         else if ((active0 & 0x800000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 47, 1);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0xa708000200000L);
      case 108:
         if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 37, 1);
         break;
      case 110:
         if ((active0 & 0x40000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 42, 1);
         break;
      case 111:
         return jjMoveStringLiteralDfa4_0(active0, 0x30000000000000L);
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000000000000L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000000000000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x400000L);
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
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000000400000L);
      case 99:
         if ((active0 & 0x2000000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 49, 1);
         break;
      case 101:
         if ((active0 & 0x1000000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 48, 1);
         break;
      case 108:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000000000L);
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000200000L);
      case 112:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000000000000L);
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x30000000000000L);
      case 120:
         if ((active0 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x600000000000L);
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
      case 101:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 38, 1);
         break;
      case 103:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 39, 1);
         break;
      case 108:
         if ((active0 & 0x200000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 45, 1);
         break;
      case 114:
         if ((active0 & 0x400000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 46, 1);
         return jjMoveStringLiteralDfa6_0(active0, 0x4000000000000L);
      case 116:
         if ((active0 & 0x8000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 51, 1);
         else if ((active0 & 0x10000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 52, 1);
         else if ((active0 & 0x20000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 53, 1);
         return jjMoveStringLiteralDfa6_0(active0, 0x600000L);
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
      case 97:
         return jjMoveStringLiteralDfa7_0(active0, 0x600000L);
      case 121:
         if ((active0 & 0x4000000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 50, 1);
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
      case 98:
         return jjMoveStringLiteralDfa8_0(active0, 0x600000L);
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
      case 108:
         return jjMoveStringLiteralDfa9_0(active0, 0x600000L);
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
      case 101:
         if ((active0 & 0x200000L) != 0L)
            return jjStopAtPos(9, 21);
         else if ((active0 & 0x400000L) != 0L)
            return jjStopAtPos(9, 22);
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
               case 8:
                  if ((0xf000ec7a00000000L & l) != 0L)
                  {
                     if (kind > 59)
                        kind = 59;
                     { jjCheckNAdd(7); }
                  }
                  if (curChar == 35)
                     { jjCheckNAddStates(0, 2); }
                  break;
               case 0:
                  if ((0xf000ec7a00000000L & l) != 0L)
                  {
                     if (kind > 59)
                        kind = 59;
                     { jjCheckNAdd(7); }
                  }
                  else if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 56)
                        kind = 56;
                     { jjCheckNAddStates(3, 5); }
                  }
                  else if (curChar == 34)
                     { jjCheckNAddStates(6, 8); }
                  if (curChar == 35)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 55)
                     kind = 55;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 2:
                  if (curChar == 34)
                     { jjCheckNAddStates(6, 8); }
                  break;
               case 4:
                  { jjCheckNAddStates(6, 8); }
                  break;
               case 5:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     { jjCheckNAddStates(6, 8); }
                  break;
               case 6:
                  if (curChar == 34 && kind > 58)
                     kind = 58;
                  break;
               case 7:
                  if ((0xf000ec7a00000000L & l) == 0L)
                     break;
                  if (kind > 59)
                     kind = 59;
                  { jjCheckNAdd(7); }
                  break;
               case 9:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     { jjCheckNAddStates(0, 2); }
                  break;
               case 10:
                  if ((0x2400L & l) != 0L && kind > 65)
                     kind = 65;
                  break;
               case 11:
                  if (curChar == 10 && kind > 65)
                     kind = 65;
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
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 56)
                     kind = 56;
                  { jjCheckNAddStates(3, 5); }
                  break;
               case 15:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 56)
                     kind = 56;
                  { jjCheckNAdd(15); }
                  break;
               case 16:
                  if (curChar != 46)
                     break;
                  if (kind > 57)
                     kind = 57;
                  { jjCheckNAdd(17); }
                  break;
               case 17:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 57)
                     kind = 57;
                  { jjCheckNAdd(17); }
                  break;
               case 18:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 57)
                     kind = 57;
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
               case 8:
               case 7:
                  if ((0x5000000050000001L & l) == 0L)
                     break;
                  if (kind > 59)
                     kind = 59;
                  { jjCheckNAdd(7); }
                  break;
               case 0:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 55)
                        kind = 55;
                     { jjCheckNAdd(1); }
                  }
                  else if ((0x5000000050000001L & l) != 0L)
                  {
                     if (kind > 59)
                        kind = 59;
                     { jjCheckNAdd(7); }
                  }
                  break;
               case 1:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 55)
                     kind = 55;
                  { jjCheckNAdd(1); }
                  break;
               case 3:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 4:
                  { jjCheckNAddStates(6, 8); }
                  break;
               case 5:
                  if ((0xffffffffefffffffL & l) != 0L)
                     { jjCheckNAddStates(6, 8); }
                  break;
               case 9:
                  { jjAddStates(0, 2); }
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
                     { jjCheckNAddStates(6, 8); }
                  break;
               case 9:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     { jjAddStates(0, 2); }
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
      if ((i = jjnewStateCnt) == (startsAt = 19 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_1(){
   switch(curChar)
   {
      case 35:
         return jjMoveStringLiteralDfa1_1(0x8L);
      case 93:
         return jjMoveStringLiteralDfa1_1(0x10L);
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
      case 35:
         if ((active1 & 0x10L) != 0L)
            return jjStopAtPos(1, 68);
         break;
      case 91:
         if ((active1 & 0x8L) != 0L)
            return jjStopAtPos(1, 67);
         break;
      default :
         return 2;
   }
   return 2;
}
static final int[] jjnextStates = {
   9, 10, 12, 15, 16, 18, 3, 5, 6, 16, 17, 18, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", "\50", "\51", "\173", "\175", "\133", "\135", "\72", "\73", "\55\76", 
"\54", "\75\76", "\55\76\76", "\174\76", "\74\174", "\77\72", "\174", "\75", "\74", 
"\76", "\52", "\100\120\162\151\156\164\141\142\154\145", 
"\100\105\161\165\141\164\141\142\154\145", "\53\53", "\53", "\55", "\57", "\136", "\45", "\56", "\74\75", "\76\75", 
"\75\75", "\41\75", "\46\46", "\174\174", "\111\156\164", "\102\157\157\154", 
"\104\157\165\142\154\145", "\123\164\162\151\156\147", "\164\171\160\145", "\151\146", 
"\164\150\145\156", "\145\154\163\145", "\151\156\146\151\170", "\151\156\146\151\170\154", 
"\151\156\146\151\170\162", "\164\162\165\145", "\146\141\154\163\145", "\160\141\156\151\143", 
"\114\151\142\162\141\162\171", "\123\143\162\151\160\164", "\145\170\160\157\162\164", 
"\151\155\160\157\162\164", "\141\163", null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, };
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
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   for (;;)
   {
     switch(curLexState)
     {
       case 0:
         try { input_stream.backup(0);
            while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
               curChar = input_stream.BeginToken();
         }
         catch (java.io.IOException e1) { continue EOFLoop; }
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_0();
         break;
       case 1:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_1();
         if (jjmatchedPos == 0 && jjmatchedKind > 69)
         {
            jjmatchedKind = 69;
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
        catch (java.io.IOException e1) { }
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
     throw new TokenMgrException(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrException.LEXICAL_ERROR);
   }
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      case 66 :
         image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
                       commentNesting++;
         break;
      case 67 :
         image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
                       commentNesting++;
         break;
      case 68 :
         image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
                                commentNesting--;
                                if (commentNesting == 0) {
                                        SwitchTo(DEFAULT);
                                }
         break;
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

      if (SimpleCharStream.staticFlag)
            throw new RuntimeException("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");

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
    for (i = 19; i-- > 0;)
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
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0xfffffffffffffffL, 0x0L, 
};
static final long[] jjtoSkip = {
   0xf000000000000000L, 0x1fL, 
};
static final long[] jjtoMore = {
   0x0L, 0x20L, 
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
