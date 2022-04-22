package com.dista.cours.validation.visitor;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class FieldMethodMapper extends VoidVisitorAdapter<ClassOrInterfaceDeclaration> {
    Map<String, MethodDeclaration> map = new LinkedHashMap<>();

    @Override
    public void visit(FieldDeclaration fieldDeclaration, ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {
        for (VariableDeclarator variable : fieldDeclaration.getVariables()) {
            final String getterName = "get" + StringUtils.capitalize(variable.getNameAsString());
            final String isName = "is" + StringUtils.capitalize(variable.getNameAsString());
            final MethodDeclaration methodAssociatedToField = classOrInterfaceDeclaration.findAll(MethodDeclaration.class)
                    .stream()
                    .filter(methodDeclaration -> methodDeclaration.getNameAsString().equals(getterName)
                            || methodDeclaration.getNameAsString().equals(isName))
                    .filter(methodDeclaration -> methodDeclaration.getParameters().isEmpty())
                    .findFirst().orElse(null);
            if (methodAssociatedToField != null) {
                map.put(variable.getNameAsString(), methodAssociatedToField);
            }

        }
        super.visit(fieldDeclaration, classOrInterfaceDeclaration);
    }

    public Map<String, MethodDeclaration> getMap() {
        return map;
    }
}
