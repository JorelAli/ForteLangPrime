// -----------------------------
// File: src/main/java/dev/fortelangprime/TreePrinter.java
// -----------------------------
package dev.fortelangprime;

import dev.fortelangprime.parser.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class TreePrinter {

    public static void printTree(ParseTree tree, ForteLangPrimeParser parser) {
        print(tree, parser, 0);
    }

    private static void print(ParseTree tree, ForteLangPrimeParser parser, int indent) {
        String indentation = "  ".repeat(indent);
        if (tree instanceof TerminalNode terminal) {
            System.out.println(indentation + terminal.getText());
        } else {
            String ruleName = parser.getRuleNames()[((org.antlr.v4.runtime.RuleContext) tree).getRuleIndex()];
            System.out.println(indentation + ruleName);
            for (int i = 0; i < tree.getChildCount(); i++) {
                print(tree.getChild(i), parser, indent + 1);
            }
        }
    }
}