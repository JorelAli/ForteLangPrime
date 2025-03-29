package dev.fortelangprime.ast;

import java.util.List;

public class LibraryNode implements AstNode {
    public final String name;
    public final List<AstNode> declarations;

    public LibraryNode(String name, List<AstNode> declarations) {
        this.name = name;
        this.declarations = declarations;
    }
}