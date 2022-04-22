package com.dista.cours.validation.visitor;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

public class PackageDeclarationVisitor extends GenericVisitorAdapter<String, CompilationUnit> {


    @Override
    public String visit(PackageDeclaration packageDeclaration, CompilationUnit destination) {
        String name = packageDeclaration.getNameAsString();
        super.visit(packageDeclaration, destination);
        destination.setPackageDeclaration(packageDeclaration);
        return name;
    }


}
