package com.dista.cours.validation.visitor;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

import java.util.Optional;

public class ValidatorClassVisitor extends GenericVisitorAdapter<ClassOrInterfaceDeclaration, CompilationUnit> {
    final String className;

    public ValidatorClassVisitor(String className) {
        this.className = className;
    }

    @Override
    public ClassOrInterfaceDeclaration visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, CompilationUnit destination) {
        if (classOrInterfaceDeclaration.isAbstract() ||
                !classOrInterfaceDeclaration.getAnnotationByName("Validation").isPresent()) {
            return null;
        }


        final ClassOrInterfaceDeclaration aClass = destination.addClass(className);
        ClassOrInterfaceType classOrInterfaceType = JavaParser.parseClassOrInterfaceType(getTypeToValidate(classOrInterfaceDeclaration));
        aClass.addImplementedType(classOrInterfaceType);
        super.visit(classOrInterfaceDeclaration, destination);
        return aClass;
    }

    private String getTypeToValidate(ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {


        String name = classOrInterfaceDeclaration.getNameAsString();

        return "com.dista.cours.validation.api.Validator<" + name + ">";
    }

}
