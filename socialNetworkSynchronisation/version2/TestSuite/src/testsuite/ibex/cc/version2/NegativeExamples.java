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

public class NegativeExamples  extends CCTestCase {
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
		testForPairOfModels("networks/OneFacebookNetwork", "networks/TwoInstagramNetwork");
	}

	@Test
	public void testForTwoNetworks() throws IOException {
		testForPairOfModels("networks/TwoFacebookNetwork", "networks/ThreeInstagramNetworks");
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
	public void testForSingleUserWithWrongNames() throws IOException {
		testForPairOfModels("users/OneFacebookUser", "users/OneInstagramOtherUser");
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
		testForPairOfModels("networks/FacebookNetworkOfNetwork", "networks/InstagramOtherNetworkOfNetworks");
	}
	@Test
	public void testForIgnoreParentRelations() throws IOException {
		testForPairOfModels("users/TwoFacebbookOtherUsers", "users/OneInstagramOtherUser");
	}
	@Test
	public void testForIgnoreSiblingWithFriendsRelations() throws IOException {
		testForPairOfModels("users/FacebookSiblingRelationOtherUsers", "users/ThreeInstagramUsers");
	}
	@Test
	public void testForIgnoreSpouseRelations() throws IOException {
		testForPairOfModels("users/FacebookSpouseRelationUsers", "users/ThreeInstagramUsers");
	}
	@Test
	public void testForIgnoreSpouseBidirectionRelations() throws IOException {
		testForPairOfModels("users/FacebookSpouseRelationBidirectionRelation", "users/ThreeInstagramUsers");
	}
	@Test
	public void testForIgnoreInterNetworkFollowership() throws IOException {
		testForPairOfModels("networks/FacebookFriendsNetworkOfNetwork", "networks/InstagramOtherNetworkOfNetworks");
	}
	@Test
	public void testForWrongIgnoreRelations() throws IOException {
		testForPairOfModels("users/FacebookWithParentRelation", "users/OneInstagramUser");
	}
	@Test
	public void testForWrongInterNetworkFollowshipRelations() throws IOException {
		testForPairOfModels("networks/OneFacebookMultipleNetwork", "networks/OneInstagramMultipleUserNetwork");
	}
	
}
