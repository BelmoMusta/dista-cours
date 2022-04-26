package com.dista.cours.validation.visitor.lookups;

import com.dista.cours.validation.visitor.FieldWrapper;
import com.dista.cours.validation.visitor.statements.MustEqualCheckIfStatement;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Map;

public class NotNullValidationAnnotationLookup extends VoidVisitorAdapter<FieldWrapper> {

    @Override
    public void visit(FieldDeclaration fieldDeclaration, FieldWrapper wrapper) {
        if (!wrapper.isDone()) {
            Map<String, MethodDeclaration> map = wrapper.getMap();
            for (AnnotationExpr annotation : fieldDeclaration.getAnnotations()) {
                if (annotation.getNameAsString().equals("NotNull")) {
                    MethodDeclaration methodDeclaration = map.get(fieldDeclaration.getVariables().get(0).getNameAsString());
                    if (methodDeclaration != null && !methodDeclaration.getType().isPrimitiveType()) {
                        MustEqualCheckIfStatement statement = new MustEqualCheckIfStatement(wrapper.getParameter(), methodDeclaration);
                        wrapper.getBody().addStatement(statement);
                    }
                }
            }
            wrapper.setDone(true);
        }
        super.visit(fieldDeclaration, wrapper);
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, FieldWrapper wrapper) {
        Type type = methodDeclaration.getType();
        if (!type.isVoidType() && !type.isPrimitiveType()) {
            for (AnnotationExpr annotation : methodDeclaration.getAnnotations()) {
                if (annotation.getNameAsString().equals("NotNull")) {
                    MustEqualCheckIfStatement statement = new MustEqualCheckIfStatement(wrapper.getParameter(), methodDeclaration);
                    wrapper.getBody().addStatement(statement);
                }
            }
        }
        super.visit(methodDeclaration, wrapper);
    }
}
