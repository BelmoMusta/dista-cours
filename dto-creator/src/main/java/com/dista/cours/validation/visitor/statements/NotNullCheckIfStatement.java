package com.dista.cours.validation.visitor.statements;


import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.IfStmt;


public class NotNullCheckIfStatement extends MustEqualCheckIfStatement {
    private final IfStmt ifStmt;

    public NotNullCheckIfStatement(Parameter parameter) {
        super(parameter);
        ifStmt = super.asIfStmt();
    }
}
