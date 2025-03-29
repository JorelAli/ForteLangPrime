package dev.fortelangprime.ast;

public class LiteralNode extends ExpressionNode {
    public final Object value;

    public LiteralNode(Object value) {
        this.value = value;
    }
}