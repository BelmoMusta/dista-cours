package com.dista.cours.validation.impl;

import com.dista.cours.MapperProcessor;
import com.dista.cours.validation.visitor.MethodImplementorVisitor;
import com.dista.cours.validation.visitor.PackageDeclarationVisitor;
import com.dista.cours.validation.visitor.ValidatorClassVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class ValidatorGenerator {


    public static CompilationUnit generateDTO(File file, String generatedClass) throws Exception {
        final CompilationUnit unitToValidate = JavaParser.parse(file);
        final CompilationUnit validationGeneratedClass = new CompilationUnit();
        final ClassOrInterfaceDeclaration aClass = unitToValidate.accept(new ValidatorClassVisitor(generatedClass), validationGeneratedClass);
        if (aClass == null) {
            return null;
        }
        unitToValidate.accept(new PackageDeclarationVisitor(), validationGeneratedClass);
        unitToValidate.accept(new MethodImplementorVisitor(), aClass);

        return validationGeneratedClass;
    }

    public static void generateDTOSinDirectory(String srcDirectory, String outputDirectory) throws Exception {
        Collection<File> files = FileUtils.listFiles(new File(srcDirectory), new String[]{"java"}, true);

        for (File file : files) {
            String child = file.getName().replaceAll("\\.java", "");
            String generatedClass = child + "GeneratedValidatorClass";

            final CompilationUnit generatedDtoContent = generateDTO(file, generatedClass);
            if (generatedDtoContent == null) {
                continue;
            }

            File truePackage = getTruePackage(outputDirectory, generatedDtoContent);
            File destinationFolder = null;
            if (truePackage != null) {
                truePackage.mkdirs();
                destinationFolder = truePackage;
            }
            if (destinationFolder != null && !destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }
            FileUtils.write(new File(destinationFolder, generatedClass + ".java"), generatedDtoContent.toString(), StandardCharsets.UTF_8);

        }
    }

    private static File getTruePackage(String outDirectory, CompilationUnit generatedDtoContent) {
        return generatedDtoContent.getPackageDeclaration().map(pkg -> {
            String nameAsString = pkg.getNameAsString();
            String[] split = nameAsString.split("\\.");
            File truePackage = new File(outDirectory);
            for (int i = 0; i < split.length; i++) {
                truePackage = new File(truePackage, split[i]);
            }
            return truePackage;
        }).orElse(null);
    }
}