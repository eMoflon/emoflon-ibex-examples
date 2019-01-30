package testsuite.ibex.cc.version3;

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
	public void testForUserToUserIslandRuleOneUser() throws IOException {
		testForPairOfModels("version3/users/OneFacebookUser", "version3/users/OneInstagramUser");
	}
	@Test
	public void testForUserToUserIslandRuleTwoUser() throws IOException {
		testForPairOfModels("version3/users/OneFacebookUser", "version3/users/OneInstagramUser");
	}
	@Test
	public void testForMultipleNetworksWithUsersBridgeRule() throws IOException {
		testForPairOfModels("version3/users/FacebookNetworkOfUsers", "version3/users/InstagramNetworkOfUsers");
	}
	@Test
	public void testForIgnoreInterNetworkFollowers() throws IOException {
		testForPairOfModels("version3/networks/ThreeFacebookNetworks", "version3/networks/ThreeInstagramNetworks");
	}
	@Test
	public void testForHandleIntraNetworkFollowers() throws IOException {
		testForPairOfModels("version3/networks/OneFacebookNetwork", "version3/networks/OneInstagramNetwork");
	}
	@Test
	public void testForHandleIntraNetworkFollowers1() throws IOException {
		testForPairOfModels("version3/networks/TwoFacebookNetwork", "version3/networks/TwoInstagramNetwork");
	}
	/*-----------------------------------------Test cases for Ignore Extra Relation-------------------------------*/
	@Test
	public void testForIgnoreParentRelations() throws IOException {
		testForPairOfModels("version3/users/TwoFacebbookOtherUsers", "version3/users/TwoInstagramOtherUsers");
	}
	@Test
	public void testForIgnoreSiblingRelations() throws IOException {
		testForPairOfModels("version3/users/FacebookSiblingRelationUsers", "version3/users/TwoInstagramOtherUsers");
	}
	@Test
	public void testForIgnoreSpouseRelations() throws IOException {
		testForPairOfModels("version3/users/FacebookSpouseRelationUsers", "version3/users/TwoInstagramOtherUsers");
	}
	@Test
	public void testForIgnoreSpouseBidirectionRelations() throws IOException {
		testForPairOfModels("version3/users/FacebookSpouseRelationBidirectionRelation", "version3/users/TwoInstagramUsers");
	}

}
