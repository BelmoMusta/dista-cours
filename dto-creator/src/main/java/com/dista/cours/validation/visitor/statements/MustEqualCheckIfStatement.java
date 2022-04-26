package com.dista.cours.validation.visitor.statements;


import com.dista.cours.validation.visitor.expressions.EqualsMethodCallExpr;
import com.dista.cours.validation.visitor.expressions.ObjMethodCallExpr;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;

import java.util.Arrays;
import java.util.List;


public class MustEqualCheckIfStatement extends Statement {
    private final IfStmt ifStmt;

    public MustEqualCheckIfStatement(Parameter parameter, String literal, MethodDeclaration methodDeclaration) {
        final Expression callExpr = new ObjMethodCallExpr(parameter, methodDeclaration);
        final Expression condition = createCondition(literal, methodDeclaration, callExpr);
        ifStmt = new IfStmt()
                .setCondition(condition)
                .setThenStmt(new ReturnFalseBlock());
    }

    public MustEqualCheckIfStatement(Parameter parameter, MethodDeclaration methodDeclaration) {
        final Expression methodCallExpr = new ObjMethodCallExpr(parameter, methodDeclaration);
        final Expression condition = createCondition(new NullLiteralExpr(), methodCallExpr);
        ifStmt = new IfStmt()
                .setCondition(condition)
                .setThenStmt(new ReturnFalseBlock());
    }

    public MustEqualCheckIfStatement(Parameter parameter) {
        final Expression paramAsExpression = parameter.getNameAsExpression();
        final Expression condition = new EqualsMethodCallExpr(new NullLiteralExpr(), paramAsExpression);
        ifStmt = new IfStmt()
                .setCondition(condition)
                .setThenStmt(new ReturnFalseBlock());
    }

    private Expression createCondition(String literal, MethodDeclaration methodDeclaration, Expression callExpr) {

        final Expression leftHand = getStringLiteralExpr(literal, methodDeclaration.getType());
        final Expression equalsMethodCallExpr = new EqualsMethodCallExpr(leftHand, callExpr);
        final Expression condition = new UnaryExpr(equalsMethodCallExpr, UnaryExpr.Operator.LOGICAL_COMPLEMENT);
        return condition;
    }

    private Expression createCondition(Expression literal, Expression callExpr) {
        final Expression equalsMethodCallExpr = new EqualsMethodCallExpr(literal, callExpr);
        final Expression condition = new UnaryExpr(equalsMethodCallExpr, UnaryExpr.Operator.LOGICAL_COMPLEMENT);
        return condition;
    }

    static List<String> simplePrimitiveTypes = Arrays.asList("int", "boolean", "short");

    protected Expression getStringLiteralExpr(String literal, Type type) {

        final Expression leftHand;
        if (type.isPrimitiveType()) {
            if (simplePrimitiveTypes.contains(type.asString())) {
                leftHand = new NameExpr(literal);
            } else if ("long".equals(type.asString())) {
                leftHand = new NameExpr(literal + "L");
            } else if ("double".equals(type.asString())) {
                leftHand = new NameExpr(literal + "D");
            } else if ("float".equals(type.asString())) {
                leftHand = new NameExpr(literal + "F");
            } else {
                leftHand = new NameExpr();
            }
        } else {
            leftHand = new StringLiteralExpr(literal);
        }

        return leftHand;
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return ifStmt.accept(v, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        ifStmt.accept(v, arg);
    }

    @Override
    public IfStmt asIfStmt() {
        return ifStmt;
    }
}
