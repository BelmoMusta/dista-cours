package com.dista.cours.validation.visitor;

import com.dista.cours.validation.visitor.lookups.LengthValidationAnnotationLookup;
import com.dista.cours.validation.visitor.lookups.MustEqualValidationAnnotationLookup;
import com.dista.cours.validation.visitor.lookups.NotNullValidationAnnotationLookup;
import com.dista.cours.validation.visitor.statements.NotNullCheckIfStatement;
import com.dista.cours.validation.visitor.statements.ReturnTrueStatement;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class StatementsVisitor extends VoidVisitorAdapter<ClassOrInterfaceDeclaration> {
    @Override
    public void visit(MethodDeclaration validationMethod, ClassOrInterfaceDeclaration classToValidate) {
        validationMethod.getBody().ifPresent(body -> { // always present
            final Parameter parameter = validationMethod.getParameters().get(0);// check not necessary
            final Statement nullCheckIfStatement = new NotNullCheckIfStatement(parameter);
            body.addStatement(nullCheckIfStatement);
            FieldMethodMapper fieldMethodMapper = new FieldMethodMapper();
            classToValidate.accept(fieldMethodMapper, classToValidate);
            FieldWrapper fieldWrapper = new FieldWrapper(parameter, body, fieldMethodMapper.getMap());
            classToValidate.accept(new NotNullValidationAnnotationLookup(), fieldWrapper);
            classToValidate.accept(new MustEqualValidationAnnotationLookup(), fieldWrapper);
            classToValidate.accept(new LengthValidationAnnotationLookup(), fieldWrapper);
            body.addStatement(new ReturnTrueStatement());

        });
        super.visit(validationMethod, classToValidate);
    }


}

