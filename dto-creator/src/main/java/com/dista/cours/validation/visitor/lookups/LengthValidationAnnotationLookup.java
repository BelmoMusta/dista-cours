package com.dista.cours.validation.visitor.lookups;

import com.dista.cours.validation.visitor.FieldWrapper;
import com.dista.cours.validation.visitor.statements.LengthCheckIfStatement;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.Map;

public class LengthValidationAnnotationLookup extends VoidVisitorAdapter<FieldWrapper> {

    @Override
    public void visit(FieldDeclaration fieldDeclaration, FieldWrapper wrapper) {
        Map<String, MethodDeclaration> map = wrapper.getMap();
        for (AnnotationExpr annotation : fieldDeclaration.getAnnotations()) {
            if ("Length".equals(annotation.getNameAsString())) {
                LengthWrapper lengthWrapper = getLengtWrapper(annotation);
                MethodDeclaration methodDeclaration = map.get(fieldDeclaration.getVariables().get(0).getNameAsString());
                if (methodDeclaration != null) {
                    wrapper.getBody().addStatement(new LengthCheckIfStatement(wrapper.getParameter(), lengthWrapper, methodDeclaration));
                }
            }
        }
        super.visit(fieldDeclaration, wrapper);
    }

    private LengthWrapper getLengtWrapper(AnnotationExpr annotation) {
        int min = 0;
        int max = 0;
        List<MemberValuePair> all = annotation.findAll(MemberValuePair.class);
        for (MemberValuePair memberValuePair : all) {
            if (memberValuePair.getNameAsString().equals("min")) {
                min = Integer.parseInt(memberValuePair.getValue().toString());
            }
            if (memberValuePair.getNameAsString().equals("max")) {
                max = Integer.parseInt(memberValuePair.getValue().toString());
            }
        }
        return new LengthWrapper(min, max);
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, FieldWrapper wrapper) {
        Type type = methodDeclaration.getType();
        if (!type.isVoidType()) {
            for (AnnotationExpr annotation : methodDeclaration.getAnnotations()) {
                if (annotation.getNameAsString().equals("Length")) {
                    wrapper.getBody().addStatement(new LengthCheckIfStatement(wrapper, methodDeclaration));
                }
            }
        }
        super.visit(methodDeclaration, wrapper);
    }

}

