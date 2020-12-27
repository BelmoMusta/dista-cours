package com.dista.cours.entdto;

import com.dista.cours.entdto.visitor.ClassDTOVisitor;
import com.dista.cours.entdto.visitor.FieldDTOVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.Name;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class ClassToDTO {
	
	
	public static CompilationUnit generateDTO(File file, String prefix) throws Exception {
		final CompilationUnit compilationUnit = JavaParser.parse(file);
		final CompilationUnit clone = new CompilationUnit();
		
		final ClassOrInterfaceDeclaration aClass = compilationUnit.accept(new ClassDTOVisitor(), clone);
		if (aClass == null) {
			return null;
		}
		
		final PackageDeclaration packageDeclaration = compilationUnit.getPackageDeclaration()
				.map(pkg -> pkg.setName(new Name(pkg.getName().asString() + "." + prefix.toLowerCase())))
				.orElse(new PackageDeclaration(new Name(prefix.toLowerCase())));
		clone.setPackageDeclaration(packageDeclaration);
		aClass.addField("Long", "id", Modifier.PRIVATE);
		compilationUnit.accept(new FieldDTOVisitor(), aClass);
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
			if(truePackage != null){
				truePackage.mkdirs();
				destinationFolder = truePackage;
			}
			if (destinationFolder != null && !destinationFolder.exists()) {
				destinationFolder.mkdirs();
			}
			FileUtils.write(new File(destinationFolder, child), generatedDtoContent.toString(), StandardCharsets.UTF_8);
			
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
