package com.dista.cours.entdto.visitor;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class PackageDeclarationVisitor extends VoidVisitorAdapter<CompilationUnit> {
    final CompilationUnit source;

    public PackageDeclarationVisitor(CompilationUnit source) {
        this.source = source;
    }

    @Override
    public void visit(PackageDeclaration packageDeclaration_, CompilationUnit destination) {
        final PackageDeclaration packageDeclaration = source.getPackageDeclaration()
                .map(pkg -> pkg.setName(new Name(pkg.getName().asString() + "." + "dto".toLowerCase())))
                .orElse(new PackageDeclaration(new Name("dto".toLowerCase())));
        destination.setPackageDeclaration(packageDeclaration);
        super.visit(packageDeclaration, destination);
    }


}
