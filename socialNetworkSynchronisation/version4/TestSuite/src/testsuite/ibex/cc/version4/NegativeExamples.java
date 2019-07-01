package testsuite.ibex.cc.version4;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.OperationalStrategy;
import org.junit.Assert;
import org.junit.Test;

import testsuite.ibex.util.CCTestCase;

public class NegativeExamples extends CCTestCase {
	public NegativeExamples(Supplier<IbexOptions> createOptions,
			BiConsumer<ResourceSet, OperationalStrategy> registerMetamodels, String name) {
		super(createOptions, registerMetamodels, name);
	}

	private void testForPairOfModels(String source, String target) throws IOException {
		createChecker(source, target);
		runCC();
		Assert.assertFalse(checker.modelsAreConsistent());
	}
	//adding friendship in users in source model
	@Test
	public void testForUserToUserIslandRuleOneUser() throws IOException {
		testForPairOfModels("version4/users/TwoFacebookUsersWithFriendship", "version4/users/TwoInstagramUsers2");
	}
	//adding friendship for one user
	@Test
	public void testForUserToUserIslandRuleOneUser1() throws IOException {
		testForPairOfModels("version4/users/OneFacebookUser3", "version4/users/OneInstagramUser3");
	}
	//adding more users as friends to the friendship node
	@Test
	public void testForHandleIntraNetworkFollowersWithMultipleUser() throws IOException {
		testForPairOfModels("version4/users/FacebookThreeUsers", "version4/users/InstagramThreeUsers");
	}
	
	//mapping from one to many networks.
	@Test
	public void testForHandleIntraNetworkFollowers1() throws IOException {
		testForPairOfModels("version4/networks/TwoFacebookNetwork", "version4/networks/OneInstagramNetwork");
	}
	//mutation in source model (adding friendship)
	@Test
	public void testForIgnoreInterNetworkfollowership() throws IOException {
		testForPairOfModels("version4/users/ThreeFacebookNetworksWithUsers", "version4/users/ThreeInstagramNetworksWithUsers");
	}
	//mutation in target model (no followers relation in one network). 
	@Test
	public void testForHandleIntraNetworkFollowers11() throws IOException {
		testForPairOfModels("version4/networks/OneFacebookNetwork", "version4/networks/TwoInstagramNetwork2");
	}
	//Extra friendship node in source but no followership for the same in target
	@Test
	public void testForHandleIntraNetworkFollowersNAC11() throws IOException {
		testForPairOfModels("version4/users/FacebookNACFriendship", "version4/users/InstgramFollowershipUsers");
	}
	//Followership bidirectional in target but no friendship node for the same in source
	@Test
	public void testForHandleIntraNetworkFollowersNAC1() throws IOException {
		testForPairOfModels("version4/users/TwoFacebookUsers", "version4/users/OneInstagramUser2");
	}
	//adding extra friendship node to the source for bidirectional followership
	@Test
	public void testForIgnoreIntraNetworkFollowership() throws IOException {
		testForPairOfModels("version4/users/FacebookNACFriendship", "version4/users/TwoInstagramUsers2");
	}
	/*--------------------------------Test case for extra relation----------------------------------*/
	//mapping from many to one user 
	@Test
	public void testForIgnoreParentRelations() throws IOException {
		testForPairOfModels("version4/users/TwoFacebbookOtherUsers", "version4/users/OneInstagramOtherUser");
	}
	
	@Test
	public void testForIgnoreSpouseRelations() throws IOException {
		testForPairOfModels("version4/users/FacebookSpouseRelationUsers", "version4/users/ThreeInstagramUsers");
	}
	@Test
	public void testForIgnoreSpouseBidirectionRelations() throws IOException {
		testForPairOfModels("version4/users/FacebookSpouseRelationBidirectionRelation","version4/users/ThreeInstagramUsers");
	}

}
