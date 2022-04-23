package com.dista.cours.validation.visitor.expressions;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;


public class ObjMethodCallExpr extends Expression {
    private final MethodCallExpr methodCallExpr;

    public ObjMethodCallExpr(Parameter caller, MethodDeclaration  methodDeclaration) {
        methodCallExpr = new MethodCallExpr();
        methodCallExpr.setScope(caller.getNameAsExpression());
        methodCallExpr.setName(methodDeclaration.getName());
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
