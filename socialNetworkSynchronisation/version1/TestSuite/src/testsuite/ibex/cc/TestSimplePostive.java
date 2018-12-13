package testsuite.ibex.cc;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import testsuite.ibex.util.CCTestCase;
import testsuite.ibex.util.CC_App_ForTesting;

public class TestSimplePostive extends CCTestCase {

	public void createChecker(String srcInstance, String trgInstance) throws IOException {
		checker = new CC_App_ForTesting(srcInstance, trgInstance);
	}

	@Test
	public void testForSingleNetwork() throws IOException {
		createChecker("in/OneFacebookNetwork", "in/OneInstagramNetwork");
		runCC();
		checker.saveModels();
		Assert.assertTrue(checker.modelsAreConsistent());
	}
}
