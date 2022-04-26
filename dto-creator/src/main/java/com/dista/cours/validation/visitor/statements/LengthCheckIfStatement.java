package com.dista.cours.validation.visitor.statements;


import com.dista.cours.validation.visitor.FieldWrapper;
import com.dista.cours.validation.visitor.expressions.LengthMethodCallExpr;
import com.dista.cours.validation.visitor.expressions.ObjMethodCallExpr;
import com.dista.cours.validation.visitor.lookups.LengthWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;


public class LengthCheckIfStatement extends Statement {
    private final IfStmt ifStmt;

    public LengthCheckIfStatement(Parameter parameter, LengthWrapper lengthWrapper, MethodDeclaration methodDeclaration) {
        final ObjMethodCallExpr callExpr = new ObjMethodCallExpr(parameter, methodDeclaration);
        final Expression condition = createCondition(lengthWrapper, callExpr);
        ifStmt = new IfStmt()
                .setCondition(condition)
                .setThenStmt(new ReturnFalseBlock());

    }

    public LengthCheckIfStatement(FieldWrapper wrapper, MethodDeclaration methodDeclaration) {

        final ObjMethodCallExpr callExpr = new ObjMethodCallExpr(wrapper.getParameter(), methodDeclaration);
        final Expression condition = createCondition(null, callExpr);
        if (condition == null) {
            ifStmt = null;
        } else {
            ifStmt = new IfStmt()
                    .setCondition(condition)
                    .setThenStmt(new ReturnFalseBlock());
        }
    }

    private Expression createCondition(LengthWrapper todo, ObjMethodCallExpr callExpr) {
        Expression equalsMethodCallExpr = new LengthMethodCallExpr(callExpr);
        Expression lessThanMin = new BinaryExpr(equalsMethodCallExpr, new NameExpr(String.valueOf(todo.getMin())), BinaryExpr.Operator.LESS);
        Expression greaterThanMax = new BinaryExpr(equalsMethodCallExpr, new NameExpr(String.valueOf(todo.getMax())), BinaryExpr.Operator.GREATER);
        return new BinaryExpr(lessThanMin, greaterThanMax, BinaryExpr.Operator.OR);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return ifStmt.accept(v, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        if (ifStmt != null)
            ifStmt.accept(v, arg);
    }
}
