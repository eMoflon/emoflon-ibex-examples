package testsuite.ibex.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.OperationalStrategy;
import org.emoflon.ibex.tgg.operational.strategies.opt.BWD_OPT;
import org.emoflon.ibex.tgg.operational.strategies.opt.FWD_OPT;
import org.emoflon.ibex.tgg.operational.strategies.opt.cc.CC;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public abstract class CCTestCase extends TestCase {
	protected CC checker;
	protected FWD_OPT forward;
	protected BWD_OPT backward;
	
	private Supplier<IbexOptions> createOptions;
	private BiConsumer<ResourceSet, OperationalStrategy> registerMetamodels;

	public void createChecker(String srcInstance, String trgInstance) throws IOException {
		checker = new CC_App_ForTesting(srcInstance, trgInstance, createOptions, registerMetamodels);
	}
	
	public void createForward(String srcInstance) throws IOException{
		forward = new Forward_App_ForTesting(srcInstance, createOptions, registerMetamodels);
		}
	
	public void createBackward(String trgInstance) throws IOException{
		backward = new Backward_App_ForTesting(trgInstance, createOptions, registerMetamodels);
		}

	public CCTestCase(Supplier<IbexOptions> createOptions,
			BiConsumer<ResourceSet, OperationalStrategy> registerMetamodels, String name) {
		this.createOptions = createOptions;
		this.registerMetamodels = registerMetamodels;
	}

	@Parameters(name = "{2}")
	public static Collection<?> solutions() {
		Supplier<IbexOptions> options1 = org.emoflon.ibex.tgg.run.cheat1facebooktoinstagram._RegistrationHelper::createIbexOptions;
		BiConsumer<ResourceSet, OperationalStrategy> reg1 = (t, u) -> {
			try {
				org.emoflon.ibex.tgg.run.cheat1facebooktoinstagram._RegistrationHelper.registerMetamodels(t, u);
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		Supplier<IbexOptions> options2 = org.emoflon.ibex.tgg.run.facebooktoinstagram._RegistrationHelper::createIbexOptions;
		BiConsumer<ResourceSet, OperationalStrategy> reg2 = (t, u) -> {
			try {
				org.emoflon.ibex.tgg.run.facebooktoinstagram._RegistrationHelper.registerMetamodels(t, u);
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		
		return Arrays.asList(new Object[][] { 
			{ options1, reg1, "Cheat 1"}, 
			{ options2, reg2, "Your Solution"}
		});
	}
	
	protected void runCC() throws IOException {
		checker.run();
		checker.terminate();
		checker.saveModels(); //usability
	}
	
	protected void runFWD_OPT() throws IOException { //usability
		forward.forward();
		forward.terminate();
		forward.saveModels();
	}
	
	protected void runBWD_OPT() throws IOException { //usability
		backward.backward();
		backward.terminate();
		backward.saveModels();
	}
}
