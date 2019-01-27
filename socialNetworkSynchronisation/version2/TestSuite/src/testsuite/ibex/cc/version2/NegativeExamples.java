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
  //mutation in target model for number of users.
	@Test
	public void testForIgnoreParentRelations() throws IOException {
		testForPairOfModels("version2/users/TwoFacebbookOtherUsers", "version2/users/OneInstagramOtherUser");
	}
	//adding friends relation with parents
	@Test
	public void testForIgnoreSiblingWithFriendsRelations() throws IOException {
		testForPairOfModels("version2/users/FacebookSiblingRelationOtherUsers", "version2/users/ThreeInstagramUsers");
	}
	//Mapping for Facebook user to instagram user with no relation
	@Test
	public void testForIgnoreSpouseRelations() throws IOException {
		testForPairOfModels("version2/users/FacebookSpouseRelationUsers", "version2/users/ThreeInstagramUsers");
	}
	//adding friends relation in source model
	@Test
	public void testForIgnoreInterNetworkFollowership() throws IOException {
		testForPairOfModels("version2/networks/FacebookFriendsNetworkOfNetwork","version2/networks/InstagramOtherNetworkOfNetworks");
	}
	//adding followership in target model.
  @Test
	public void testForWrongIgnoreRelations() throws IOException {
		testForPairOfModels("version2/users/FacebookWithParentRelation", "version2/users/OneInstagramUser");
  }
	// adding intranetwork followership
  @Test
	public void testForWrongInterNetworkFollowshipRelations() throws IOException {
		testForPairOfModels("version2/networks/OneFacebookMultipleNetwork","version2/networks/OneInstagramMultipleUserNetwork");
	}
}
