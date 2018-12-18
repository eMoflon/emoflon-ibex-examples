package testsuite.ibex.cc;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.OperationalStrategy;
import org.junit.Assert;
import org.junit.Test;

import testsuite.ibex.util.CCTestCase;

public class TestSimplePostive extends CCTestCase {
	public TestSimplePostive(Supplier<IbexOptions> createOptions,
			BiConsumer<ResourceSet, OperationalStrategy> registerMetamodels, String name) {
		super(createOptions, registerMetamodels, name);
	}

	@Test
	public void testForSingleNetwork() throws IOException {
		createChecker("in/OneFacebookNetwork", "in/OneInstagramNetwork");
		runCC();
		checker.saveModels();
		Assert.assertTrue(checker.modelsAreConsistent());
	}
	
	
	
	@Test
	public void testForTwoNetwork() throws IOException
	{ 
		createChecker("in/TwoFacebookNetwork", "in/TwoInstagramNetwork");
		runCC();
		checker.saveModels();
		Assert.assertTrue(checker.modelsAreConsistent());
	}
	@Test
	public void testForThreeNetwork() throws IOException
	{ 
		createChecker("in/TwoFacebookNetwork", "in/ThreeInstagramNetwork");
		runCC();
		checker.saveModels();
		Assert.assertTrue(checker.modelsAreConsistent());
	}
	
	
}
