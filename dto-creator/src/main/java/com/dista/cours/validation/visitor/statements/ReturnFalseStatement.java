package com.dista.cours.validation.visitor.statements;


import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class ReturnFalseStatement extends Statement {
    final ReturnStmt returnStmt;

    public ReturnFalseStatement() {
        returnStmt = new ReturnStmt("false");
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return returnStmt.accept(v, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        returnStmt.accept(v, arg);
    }
}
