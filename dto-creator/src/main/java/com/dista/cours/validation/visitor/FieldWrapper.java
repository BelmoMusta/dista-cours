package com.dista.cours.validation.visitor;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.BlockStmt;

import java.util.Map;

public class FieldWrapper {
    final Parameter parameter;
    final BlockStmt body;
    final Map<String, MethodDeclaration> map;
    private boolean done;

    FieldWrapper(Parameter parameter, BlockStmt body, Map<String, MethodDeclaration> map) {
        this.parameter = parameter;
        this.body = body;
        this.map = map;
    }

    public BlockStmt getBody() {
        return body;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public Map<String, MethodDeclaration> getMap() {
        return map;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }
}
