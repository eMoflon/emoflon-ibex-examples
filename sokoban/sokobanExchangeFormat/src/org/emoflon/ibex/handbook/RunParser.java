package org.emoflon.ibex.handbook;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Injector;

public class RunParser {
	public static void main(String[] args) throws IOException {
		// do this only once per application
		Injector injector = new SokobanExchangeFormatStandaloneSetup().createInjectorAndDoEMFRegistration();

		// obtain a resourceset from the injector
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);

		// load a resource by URI, in this case from the file system
		Resource resource = resourceSet.getResource(URI.createFileURI("./boards/simplest.sok"), true);

		// Validation
		IResourceValidator validator = ((XtextResource) resource).getResourceServiceProvider().getResourceValidator();
		List<Issue> issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		for (Issue issue : issues) {
			System.out.println(issue.getMessage());
		}

		if (issues.isEmpty()) {
			ResourceSet rs = new ResourceSetImpl();
			rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,
					new XMIResourceFactoryImpl());
			Resource r = rs.createResource(resource.getURI().appendFileExtension("xmi"));
			r.getContents().add(resource.getContents().get(0));
			r.save(null);
		}
	}
}
