package org.emoflon.ibex.tgg.run.sokobanimportexport;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.handbook.sokobanExchangeFormat.SokobanExchangeFormatPackage;
import org.emoflon.ibex.handbook.sokobanExchangeFormat.impl.SokobanExchangeFormatPackageImpl;
import org.emoflon.ibex.tgg.operational.strategies.OperationalStrategy;

import SokobanExchangeFormatPreprocessor.impl.SokobanExchangeFormatPreprocessorPackageImpl;
import SokobanLanguage.impl.SokobanLanguagePackageImpl;


public class _RegistrationHelper {

	/** Load and register source and target metamodels */
	public static void registerMetamodels(ResourceSet rs, OperationalStrategy strategy)  throws IOException {
		
		// For both source and target metamodels (and any other dependencies you might require)
		
		// Option 1 (recommended): If you have generated code for your metamodel <Foo> and use eMoflon projects and defaults,
		//                         just add the project Foo as a plugin dependency and simply use:
		// FooPackageImpl.init();
		SokobanLanguagePackageImpl.init();
		SokobanExchangeFormatPreprocessorPackageImpl.init();
		
		SokobanExchangeFormatPackageImpl.init();
		rs.getPackageRegistry().put("platform:/resource/sokobanExchangeFormat/model/generated/SokobanExchangeFormat.ecore", SokobanExchangeFormatPackage.eINSTANCE);

		// Option 2:  If you wish to use the .ecore file directly without generating code
		// strategy.loadAndRegisterMetamodel("<pathToEcoreFile>");
		
		// Option 3 (advanced): If you have an .ecore file with an arbitrary URI "<URIOfPackage>"
		// String pathToEcoreFile = "<pathToEcoreFile>";
		// URI key = URI.createURI("<URIOfPackage>");
		// URI value = URI.createURI(pathToEcoreFile);
		// strategy.loadAndRegisterMetamodel(pathToEcoreFile);
		// rs.getURIConverter().getURIMap().put(key, value);
	}
}
