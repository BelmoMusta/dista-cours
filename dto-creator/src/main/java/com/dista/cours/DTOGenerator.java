package com.dista.cours;

import com.dista.cours.entdto.ClassToDTO;
import com.dista.cours.validation.impl.ValidatorGenerator;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;

/**
 * Goal which touches a timestamp file.
 *
 * @goal touch
 *
 * @phase process-sources
 */
@Mojo(name = "dto-generator", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class DTOGenerator extends AbstractMojo {
	
	@Parameter(defaultValue = "${project.build.directory}", required = true, readonly = false)
	private File outputDirectory;
	
	@Parameter(defaultValue = "${project.basedir}", required = true, readonly = false)
	private File src;
	
	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	MavenProject project;
	
	public void execute()
			throws MojoExecutionException {
		getLog().info("BEGIN CREATING DTOS");
		File f = outputDirectory;
		
		if (!f.exists()) {
			f.mkdirs();
		}
		try {

            ValidatorGenerator.generateDTOSinDirectory(src.getAbsolutePath(), "DTO", new File(new File(outputDirectory,"generated-sources"),"dto").getAbsolutePath() );
			ClassToDTO.generateDTOSinDirectory(src.getAbsolutePath(), "DTO", new File(new File(outputDirectory,"generated-sources"),"dto").getAbsolutePath() );
		} catch (Exception e) {
			throw new MojoExecutionException("Error generating dtos ", e);
		}
	}
}
