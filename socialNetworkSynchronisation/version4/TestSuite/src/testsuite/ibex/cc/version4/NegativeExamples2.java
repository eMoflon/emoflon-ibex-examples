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

public class NegativeExamples2 extends CCTestCase {
	public NegativeExamples2(Supplier<IbexOptions> createOptions,
			BiConsumer<ResourceSet, OperationalStrategy> registerMetamodels, String name) {
		super(createOptions, registerMetamodels, name);
	}

private void testForPairOfModels(String source, String target) throws IOException {
		createChecker(source, target);
		runCC();
		Assert.assertFalse(checker.modelsAreConsistent());
	}
	
	//usability
	private void testForFWD(String source) throws IOException{
			createForward(source);
			runFWD_OPT();
			//Assert.assertFalse(checker.modelsAreConsistent());
		}
		
	private void testForBWD(String target) throws IOException{
			createBackward(target);
			runBWD_OPT();
			//Assert.assertFalse(checker.modelsAreConsistent());
		}
		
		
		//adding forward n backward testcase
		//usability
	@Test
	public void testForForward() throws IOException{
			testForFWD("version4/users/FacebookNACFriendship");
		}
		
		@Test
		public void testForBackward() throws IOException{
			testForBWD("version4/users/InstgramFollowershipUsers");
		}
		@Test
		public void testForSorceNAC() throws IOException {
			testForPairOfModels("version4/users/FacebookNACFriendship", "CO/trg");
		}
		@Test
		public void testForTargetNAC() throws IOException {
			testForPairOfModels("CO/src","version4/users/InstgramFollowershipUsers");
		}
}
