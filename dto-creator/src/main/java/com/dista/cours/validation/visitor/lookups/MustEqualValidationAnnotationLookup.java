package com.dista.cours.validation.visitor.lookups;

import com.dista.cours.validation.visitor.FieldWrapper;
import com.dista.cours.validation.visitor.statements.MustEqualCheckIfStatement;
import com.dista.cours.validation.visitor.statements.NullCheckIfStatement;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MustEqualValidationAnnotationLookup extends VoidVisitorAdapter<FieldWrapper> {
    static Map<String, Function> annotationNameFunctions = new LinkedHashMap<>();

    static {
        annotationNameFunctions.put("MustEqual", null);
    }

    @Override
    public void visit(FieldDeclaration fieldDeclaration, FieldWrapper wrapper) {
        Map<String, MethodDeclaration> map = wrapper.getMap();
        for (AnnotationExpr annotation : fieldDeclaration.getAnnotations()) {
            if (annotation.getNameAsString().equals("MustEqual")) {
                List<StringLiteralExpr> value = annotation.findAll(StringLiteralExpr.class);
                MethodDeclaration methodDeclaration = map.get(fieldDeclaration.getVariables().get(0).getNameAsString());
                if (methodDeclaration != null) {
                    wrapper.getBody().addStatement(new MustEqualCheckIfStatement(wrapper.getParameter(), value.get(0).getValue(), methodDeclaration));
                }
            }
        }
        super.visit(fieldDeclaration, wrapper);
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, FieldWrapper wrapper) {
        Type type = methodDeclaration.getType();
        if (!type.isVoidType()) {
            for (AnnotationExpr annotation : methodDeclaration.getAnnotations()) {
                if (annotation.getNameAsString().equals("MustEqual")) {
                    wrapper.getBody().addStatement(new NullCheckIfStatement(wrapper.getParameter(), methodDeclaration));
                }
            }
        }
        super.visit(methodDeclaration, wrapper);
    }
}
