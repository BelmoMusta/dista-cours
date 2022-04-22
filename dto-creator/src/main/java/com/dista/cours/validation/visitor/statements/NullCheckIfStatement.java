package com.dista.cours.validation.visitor.statements;


import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class NullCheckIfStatement extends Statement {
    final IfStmt ifStmt;

    public NullCheckIfStatement(String parameter) {
        final Expression condition = new BinaryExpr(new NameExpr(parameter), new NullLiteralExpr(), BinaryExpr.Operator.EQUALS);
        ifStmt = new IfStmt().setCondition(condition).setThenStmt(new ReturnFalseBlock());
    }

    public NullCheckIfStatement(Parameter parameter) {
        this(parameter.getNameAsString());
    }

    public NullCheckIfStatement(Parameter parameter, MethodDeclaration methodDeclaration) {
        // creates if(parameter.methodDeclaration() == null) return false;
        MethodCallExpr methodCallExpr = new MethodCallExpr(parameter.getNameAsExpression(), methodDeclaration.getNameAsString());
        BinaryExpr condition = new BinaryExpr(methodCallExpr, new NullLiteralExpr(), BinaryExpr.Operator.EQUALS);

        ifStmt = new IfStmt()
                .setCondition(condition)
                .setThenStmt(new ReturnFalseBlock());

    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return ifStmt.accept(v, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        ifStmt.accept(v, arg);
    }
}
