package com.dista.cours.validation.visitor;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodImplementorVisitor extends VoidVisitorAdapter<ClassOrInterfaceDeclaration> {


    @Override
    public void visit(ClassOrInterfaceDeclaration n, ClassOrInterfaceDeclaration destination) {
        MethodDeclaration method = destination.addMethod("validate", Modifier.PUBLIC);//  boolean validate(T objectToValidate);
        method.setType("boolean");
        Parameter parameter = new Parameter();
        String name = n.getNameAsString();
        parameter.setType(name);
        parameter.setName("obj");
        method.addParameter(parameter);
        method.addAnnotation(new MarkerAnnotationExpr("Override"));
        method.accept(new StatementsVisitor(), n);
        super.visit(n, destination);
    }


}
