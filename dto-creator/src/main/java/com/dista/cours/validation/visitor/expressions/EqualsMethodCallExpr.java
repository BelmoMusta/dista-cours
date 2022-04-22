package com.dista.cours.validation.visitor.expressions;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;


public class EqualsMethodCallExpr extends Expression {
    final MethodCallExpr methodCallExpr;//= new MethodCallExpr();

    public EqualsMethodCallExpr(Expression leftHand, Expression rightHand) {
        methodCallExpr = new MethodCallExpr("equals", rightHand);
        methodCallExpr.setScope(leftHand);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return methodCallExpr.accept(v, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        methodCallExpr.accept(v, arg);
    }
}
