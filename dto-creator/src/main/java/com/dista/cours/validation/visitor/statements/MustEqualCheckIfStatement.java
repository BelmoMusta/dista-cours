package com.dista.cours.validation.visitor.statements;


import com.dista.cours.validation.visitor.expressions.EqualsMethodCallExpr;
import com.dista.cours.validation.visitor.expressions.ObjMethodCallExpr;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class MustEqualCheckIfStatement extends Statement {
    final IfStmt ifStmt;

    public MustEqualCheckIfStatement(Parameter parameter, String literal, MethodDeclaration methodDeclaration) {
        ObjMethodCallExpr callExpr = new ObjMethodCallExpr(parameter, methodDeclaration);
        EqualsMethodCallExpr equalsMethodCallExpr = new EqualsMethodCallExpr(new StringLiteralExpr(literal), callExpr);
        Expression negated = new UnaryExpr(equalsMethodCallExpr, UnaryExpr.Operator.LOGICAL_COMPLEMENT);
        ifStmt = new IfStmt()
                .setCondition(negated)
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
