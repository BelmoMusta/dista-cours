package com.dista.cours.entdto.visitor;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

public class ClassDTOVisitor extends GenericVisitorAdapter<ClassOrInterfaceDeclaration, CompilationUnit> {
    @Override
    public ClassOrInterfaceDeclaration visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, CompilationUnit destination) {
        if (classOrInterfaceDeclaration.isAbstract() || !classOrInterfaceDeclaration.getAnnotationByName("DTO").isPresent()) {
            return null;
        }
        final String className = classOrInterfaceDeclaration.getNameAsString();
        final String destDTOClassName = className + "DTO";
        final ClassOrInterfaceDeclaration aClass = destination.addClass(destDTOClassName);
        super.visit(classOrInterfaceDeclaration, destination);
        return aClass;
    }
}
