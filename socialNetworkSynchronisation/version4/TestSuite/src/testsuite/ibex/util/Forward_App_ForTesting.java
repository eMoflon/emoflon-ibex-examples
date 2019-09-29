package testsuite.ibex.util;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.OperationalStrategy;
import org.emoflon.ibex.tgg.operational.strategies.opt.FWD_OPT;
import org.emoflon.ibex.tgg.runtime.engine.DemoclesTGGEngine;

public class Forward_App_ForTesting extends FWD_OPT{
	
	private String src;
	
	private BiConsumer<ResourceSet, OperationalStrategy> registerMetamodels;

	public Forward_App_ForTesting(String srcInstance, Supplier<IbexOptions> createOptions,
			BiConsumer<ResourceSet, OperationalStrategy> registerMetamodels) throws IOException {
		super(createOptions.get());
		src = srcInstance;
		
		this.registerMetamodels = registerMetamodels;
		registerBlackInterpreter(new DemoclesTGGEngine()); 
	}

	@Override
	public void loadModels() throws IOException {
		s = loadResource("Testsuite/resources/" + src + ".xmi");
		t = createResource("Testsuite/resources/CO/trg.xmi");
		c = createResource("Testsuite/resources/CO/corr.xmi");
		p = createResource("Testsuite/resources/CO/protocol.xmi");

		EcoreUtil.resolveAll(rs);
	}
	
	@Override
	protected void registerUserMetamodels() throws IOException {
		registerMetamodels.accept(rs, this);

		// Register correspondence metamodel last
		loadAndRegisterCorrMetamodel(options.projectPath() + "/model/" + options.projectName() + ".ecore");
		
	}

}
