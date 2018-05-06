package org.emoflon.ibex.handbook.preprocessing;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.URI;
import org.emoflon.ibex.handbook.preprocessing.api.PreprocessingDemoclesApp;
import org.junit.jupiter.api.Test;

class TestPreprocessor {

	@Test
	void test() {
		PreprocessingDemoclesApp p = new PreprocessingDemoclesApp();
		p.loadModel(URI.createURI("./instances/src.xmi", true));
		assertEquals(1, p.initAPI().init().countMatches());
	}

}
