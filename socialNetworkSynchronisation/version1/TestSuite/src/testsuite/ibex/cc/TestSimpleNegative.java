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

public class TestSimpleNegative  extends CCTestCase{
	public TestSimpleNegative(Supplier<IbexOptions> createOptions,
			BiConsumer<ResourceSet, OperationalStrategy> registerMetamodels, String name) {
		super(createOptions, registerMetamodels, name);
	}
	
	@Test
	public void testForSingleNetwork() throws IOException {
		createChecker("in/OneFacebookNetwork", "in/OneFacebookNetwork");
		runCC();
		checker.saveModels();
		Assert.assertTrue(checker.modelsAreConsistent());
	}
	@Test
	public void testForsameNetwork() throws IOException {
		createChecker("in/TwoFacebookNetwork", "in/FiveFacebookNetwork");
		runCC();
		checker.saveModels();
		Assert.assertTrue(checker.modelsAreConsistent());
	}
	@Test
	public void testForSingleUser() throws IOException {
		createChecker("in/OneFacebookUser", "in/TwoInstagramUser");
		runCC();
		checker.saveModels();
		Assert.assertTrue(checker.modelsAreConsistent());
	}
	
	@Test
	public void testForMultipleUser() throws IOException {
		createChecker("in/OneFacebookUser", "in/OneFacebookUser");
		runCC();
		checker.saveModels();
		Assert.assertTrue(checker.modelsAreConsistent());
	}
}
