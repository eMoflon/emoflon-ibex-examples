package testsuite.ibex.cc.version2;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.OperationalStrategy;
import org.junit.Assert;
import org.junit.Test;

import testsuite.ibex.util.CCTestCase;

public class PositiveExamples extends CCTestCase {
	public PositiveExamples(Supplier<IbexOptions> createOptions,
			BiConsumer<ResourceSet, OperationalStrategy> registerMetamodels, String name) {
		super(createOptions, registerMetamodels, name);
	}

	private void testForPairOfModels(String source, String target) throws IOException {
		createChecker(source, target);
		runCC();
		Assert.assertTrue(checker.modelsAreConsistent());
	}

	@Test
	public void testForIgnoreParentRelations() throws IOException {
		testForPairOfModels("version2/users/TwoFacebbookOtherUsers", "version2/users/TwoInstagramOtherUsers");
	}

	@Test
	public void testForIgnoreSiblingRelations() throws IOException {
		testForPairOfModels("version2/users/FacebookSiblingRelationUsers", "version2/users/TwoInstagramOtherUsers1");
	}

	@Test
	public void testForIgnoreSpouseRelations() throws IOException {
		testForPairOfModels("version2/users/FacebookSpouseRelationUsers", "version2/users/TwoInstagramOtherUsers2");
	}

	@Test
	public void testForIgnoreSpouseBidirectionRelations() throws IOException {
		testForPairOfModels("version2/users/FacebookSpouseRelationBidirectionRelation",
				"version2/users/TwoInstagramUsers");
	}

	@Test
	public void testForIgnoreInterNetworkFollowership() throws IOException {
		testForPairOfModels("version2/networks/FacebookNetworkOfNetwork",
				"version2/networks/InstagramNetworkOfNetworks");
	}

	@Test
	public void testForHandleIntraNetworkFollowers() throws IOException {
		testForPairOfModels("version2/users/TwoFacebookUsers", "version2/users/TwoInstagramUsers1");
	}

}
