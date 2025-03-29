// -----------------------------
// File: src/main/java/dev/fortelangprime/Main.java
// -----------------------------
package dev.fortelangprime;

import dev.fortelangprime.ast.LibraryNode;
import dev.fortelangprime.parser.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = Files.readString(Path.of("src/test/resources/sample.flp"));
        ForteLangPrimeLexer lexer = new ForteLangPrimeLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ForteLangPrimeParser parser = new ForteLangPrimeParser(tokens);
        ParseTree tree = parser.library();

        // System.out.println("Parse tree:");
        // TreePrinter.printTree(tree, parser);

        // ParseTreeToAstVisitor visitor = new ParseTreeToAstVisitor();
        // LibraryNode ast = (LibraryNode) visitor.visit(tree);
        // System.out.println("Library name: " + ast.name);
    }
}