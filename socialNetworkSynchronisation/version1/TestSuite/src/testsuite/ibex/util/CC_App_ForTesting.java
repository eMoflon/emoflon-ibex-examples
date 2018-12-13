package testsuite.ibex.util;

import java.io.IOException;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emoflon.ibex.tgg.operational.strategies.opt.cc.CC;
import org.emoflon.ibex.tgg.run.facebooktoinstagram._RegistrationHelper;
import org.emoflon.ibex.tgg.runtime.engine.DemoclesTGGEngine;

public class CC_App_ForTesting extends CC {
	private String src;
	private String trg;

	public CC_App_ForTesting(String srcInstance, String trgInstance) throws IOException {
		super(_RegistrationHelper.createIbexOptions());
		src = srcInstance;
		trg = trgInstance;
		registerBlackInterpreter(new DemoclesTGGEngine());
	}

	@Override
	public void loadModels() throws IOException {
		s = loadResource("Testsuite/resources/" + src + ".xmi");
		t = loadResource("Testsuite/resources/" + trg + ".xmi");
		c = createResource("Testsuite/instances/corr.xmi");
		p = createResource("Testsuite/instances/protocol.xmi");

		EcoreUtil.resolveAll(rs);
	}

	@Override
	protected void registerUserMetamodels() throws IOException {
		_RegistrationHelper.registerMetamodels(rs, this);

		// Register correspondence metamodel last
		loadAndRegisterCorrMetamodel(options.projectPath() + "/model/" + options.projectName() + ".ecore");
	}
}
