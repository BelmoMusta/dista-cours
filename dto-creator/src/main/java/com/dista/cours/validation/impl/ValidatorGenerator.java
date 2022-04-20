package com.dista.cours.validation.impl;

import com.dista.cours.entdto.visitor.PackageDeclarationVisitor;
import com.dista.cours.validation.visitor.ValidatorClassVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.github.javaparser.utils.SourceRoot;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

public class ValidatorGenerator {


    public static CompilationUnit generateDTO(File file, String prefix) throws Exception {
        final CompilationUnit compilationUnit = JavaParser.parse(file);
        final CompilationUnit clone = new CompilationUnit();
        compilationUnit.accept(new PackageDeclarationVisitor(compilationUnit), clone);
        String className = "ValidatorGeneratedClass";
        final ClassOrInterfaceDeclaration aClass = compilationUnit.accept(new ValidatorClassVisitor(className), clone);
        if (aClass == null) {
            return null;
        }

        String s = compilationUnit.getPackageDeclaration()
                .map(PackageDeclaration::getNameAsString)
                .orElse("");

        return clone;
    }

    public static void generateDTOSinDirectory(String srcDirectory, String prefix, String outputDirectory) throws Exception {
        Collection<File> files = FileUtils.listFiles(new File(srcDirectory), new String[]{"java"}, true);
        for (File file : files) {
            final CompilationUnit generatedDtoContent = generateDTO(file, prefix);
            if (generatedDtoContent == null) {
                continue;
            }
            String child = file.getName().replaceAll("\\.java", prefix + ".java");

            File truePackage = getTruePackage(outputDirectory, generatedDtoContent);
            File destinationFolder = null;
            if (truePackage != null) {
                truePackage.mkdirs();
                destinationFolder = truePackage;
            }
            if (destinationFolder != null && !destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }
            FileUtils.write(new File(destinationFolder, "ValidatorGeneratedClass.java"), generatedDtoContent.toString(), StandardCharsets.UTF_8);

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
