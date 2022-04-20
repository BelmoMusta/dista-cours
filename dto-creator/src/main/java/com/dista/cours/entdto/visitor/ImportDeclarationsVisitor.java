package com.dista.cours.entdto.visitor;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ImportDeclarationsVisitor extends VoidVisitorAdapter<CompilationUnit> {

    @Override
    public void visit(ImportDeclaration importDeclaration, CompilationUnit destination) {
        destination.getImports().add(importDeclaration);
        super.visit(importDeclaration, destination);
    }

}
