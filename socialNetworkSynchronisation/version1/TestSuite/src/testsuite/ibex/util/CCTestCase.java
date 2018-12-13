package testsuite.ibex.util;

import java.io.IOException;

import org.emoflon.ibex.tgg.operational.strategies.opt.cc.CC;


public abstract class CCTestCase extends TestCase {
	protected CC checker;
	
	protected void runCC() throws IOException {
		checker.run();
		checker.terminate();
		checker.saveModels();
	}
}
