package com.dista.cours.validation.visitor.lookups;

import com.dista.cours.validation.visitor.FieldWrapper;
import com.dista.cours.validation.visitor.statements.MustEqualCheckIfStatement;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.Map;

public class MustEqualValidationAnnotationLookup extends VoidVisitorAdapter<FieldWrapper> {


    @Override
    public void visit(FieldDeclaration fieldDeclaration, FieldWrapper wrapper) {
        Map<String, MethodDeclaration> map = wrapper.getMap();
        for (AnnotationExpr annotation : fieldDeclaration.getAnnotations()) {
            if ("MustEqual".equals(annotation.getNameAsString())) {
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
            NodeList<AnnotationExpr> annotations = methodDeclaration.getAnnotations();
            for (AnnotationExpr annotation : annotations) {
                if (annotation.getNameAsString().equals("MustEqual")) {
                    List<StringLiteralExpr> value = annotation.findAll(StringLiteralExpr.class);
                    wrapper.getBody().addStatement(new MustEqualCheckIfStatement(wrapper.getParameter(),value.get(0).getValue(), methodDeclaration));
                }
            }
        }
        super.visit(methodDeclaration, wrapper);
    }
}
