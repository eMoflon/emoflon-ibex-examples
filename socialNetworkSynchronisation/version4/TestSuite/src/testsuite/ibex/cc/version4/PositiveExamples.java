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
	
	//usability
		private void testForFWD(String source) throws IOException{
			runFWD_OPT();
			runCC();
			Assert.assertTrue(checker.modelsAreConsistent());
		}
		
		private void testForBWD(String target) throws IOException{
			runBWD_OPT();
			runCC();
			Assert.assertTrue(checker.modelsAreConsistent());
		}
		
		//adding forward n backward testcase
			//usability
			@Test
			public void testForForward() throws IOException{
				testForFWD("version4/users/FacebookNACFriendship2");
			}
			
			@Test
			public void testForBackward() throws IOException{
				testForBWD("version4/users/OneInstagramUser4");
			}
	
	@Test
	public void testForUserToUserIslandRuleOneUser() throws IOException {
		testForPairOfModels("version4/users/OneFacebookUser", "version4/users/OneInstagramUser");
	}
	@Test
	public void testForUserToUserIslandRuleTwoUser() throws IOException {
		testForPairOfModels("version4/users/TwoFacebookUsers", "version4/users/TwoInstagramUsers");
	}
	@Test
	public void testForMultipleNetworksWithUsersBridgeRule() throws IOException {
		testForPairOfModels("version4/users/FacebookNetworkOfUsers", "version4/users/InstagramNetworkOfUsers");
	}
	@Test
	public void testForIgnoreInterNetworkFollowers() throws IOException {
		testForPairOfModels("version4/networks/ThreeFacebookNetworks", "version4/networks/ThreeInstagramNetworks");
	}
	@Test
	public void testForIgnoreIntraNetworkFollowers1() throws IOException {
		testForPairOfModels("version4/users/FacebookIntraUser", "version4/users/OneInstagramUser5");
	}
	@Test
	public void testForHandleIntraNetworkFollowers() throws IOException {
		testForPairOfModels("version4/networks/OneFacebookNetwork", "version4/networks/OneInstagramNetwork");
	}
	@Test
	public void testForHandleIntraNetworkFollowers1() throws IOException {
		testForPairOfModels("version4/networks/TwoFacebookNetwork", "version4/networks/TwoInstagramNetwork");
	}
	@Test
	public void testForHandleIntraNetworkFollowersNAC() throws IOException {
		testForPairOfModels("version4/users/FacebookNACFriendship2", "version4/users/OneInstagramUser4");
	}
	@Test
	public void testForHandleIntraNetworkFollowersNAC1() throws IOException {
		testForPairOfModels("version4/users/FacebookIntraUser1", "version4/users/OneInstagramUser2");
	}
	@Test
	public void testForHandleIntraNetworkFollowersNAC11() throws IOException {
		testForPairOfModels("version4/users/FacebookNACFriendship", "version4/users/InstgramFollowershipUsers");
	}
	/*-----------------------------------------Test cases for Ignore Extra Relation-------------------------------*/
	@Test
	public void testForIgnoreParentRelations() throws IOException {
		testForPairOfModels("version4/users/TwoFacebbookOtherUsers", "version4/users/TwoInstagramOtherUsers");
	}
	@Test
	public void testForIgnoreSiblingRelations() throws IOException {
		testForPairOfModels("version4/users/FacebookSiblingRelationUsers", "version4/users/TwoInstagramOtherUsers2");
	}
	@Test
	public void testForIgnoreSpouseRelations() throws IOException {
		testForPairOfModels("version4/users/FacebookSpouseRelationUsers", "version4/users/TwoInstagramOtherUsers3");
	}
	@Test
	public void testForIgnoreSpouseBidirectionRelations() throws IOException {
		testForPairOfModels("version4/users/FacebookSpouseRelationBidirectionRelation", "version4/users/TwoInstagramUsers31");
	}

}
