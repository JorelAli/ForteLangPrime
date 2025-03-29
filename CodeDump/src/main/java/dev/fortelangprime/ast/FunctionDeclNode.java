package dev.fortelangprime.ast;

import java.util.List;

public class FunctionDeclNode implements AstNode {
    public final String name;
    public final List<String> genericParams;
    public final List<Param> parameters;
    public final String returnType;
    public final ExpressionNode body;

    public FunctionDeclNode(String name, List<String> genericParams, List<Param> parameters, String returnType, ExpressionNode body) {
        this.name = name;
        this.genericParams = genericParams;
        this.parameters = parameters;
        this.returnType = returnType;
        this.body = body;
    }

    public static class Param {
        public final String name;
        public final String type;

        public Param(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }
}