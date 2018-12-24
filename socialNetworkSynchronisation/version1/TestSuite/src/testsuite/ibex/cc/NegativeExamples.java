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

public class NegativeExamples  extends CCTestCase{
	public NegativeExamples(Supplier<IbexOptions> createOptions,
			BiConsumer<ResourceSet, OperationalStrategy> registerMetamodels, String name) {
		super(createOptions, registerMetamodels, name);
	}
	
	private void testForPairOfModels(String source, String target) throws IOException {
		createChecker(source, target);
		runCC();
		Assert.assertFalse(checker.modelsAreConsistent());
	}

	@Test
	public void testForSingleNetwork() throws IOException {
		testForPairOfModels("networks/OneFacebookNetwork", "networks/TwoInstagramNetworks");
	}

	@Test
	public void testForTwoNetworks() throws IOException {
		testForPairOfModels("networks/TwoFacebookNetworks", "networks/ThreeInstagramNetworks");
	}

	@Test
	public void testForThreeNetwork() throws IOException {
		testForPairOfModels("networks/ThreeFacebookNetworks", "networks/InstagramNetworkOfNetworks");
	}

	@Test
	public void testForSingleUser() throws IOException {
		testForPairOfModels("users/OneFacebookUser", "users/TwoInstagramUsers");
	}

	@Test
	public void testForTwoUsers() throws IOException {
		testForPairOfModels("users/TwoFacebookUsers", "users/ThreeInstagramUsers");
	}
	
	@Test
	public void testForThreeUsers() throws IOException {
		testForPairOfModels("users/ThreeFacebookUsers", "users/OneInstagramUser");
	}
	
	@Test
	public void testForMultipleNetworksWithUsers() throws IOException {
		testForPairOfModels("networks/FacebookNetworkOfNetworks", "networks/InstagramOtherNetworkOfNetworks");
	}
}
