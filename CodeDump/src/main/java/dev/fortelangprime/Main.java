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

		if (args.length == 1 && args[0].equals("-lsp")) {

		} else {
			String input = Files.readString(Path.of("src/test/resources/MoreTests.flp"));
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
}