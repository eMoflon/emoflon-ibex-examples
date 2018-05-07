package org.emoflon.ibex.tgg.run.sokobanimportexport;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emoflon.ibex.handbook.preprocessing.Preprocessor;
import org.emoflon.ibex.tgg.compiler.patterns.PatternSuffixes;
import org.emoflon.ibex.tgg.operational.csp.constraints.factories.UserDefinedRuntimeTGGAttrConstraintFactory;
import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.sync.SYNC;
import org.emoflon.ibex.tgg.runtime.engine.DemoclesTGGEngine;

import SokobanLanguage.Board;

public class INITIAL_FWD extends SYNC {

	public INITIAL_FWD() throws IOException {
		super(createIbexOptions());
		registerBlackInterpreter(new DemoclesTGGEngine());
	}

	public static void main(String[] args) throws IOException {
		Logger.getRootLogger().setLevel(Level.DEBUG);

		INITIAL_FWD sync = new INITIAL_FWD();

		logger.info("Starting SYNC");
		long tic = System.currentTimeMillis();
		sync.forward();
		long toc = System.currentTimeMillis();
		logger.info("Completed SYNC in: " + (toc - tic) + " ms");

		sync.saveModels();
		sync.terminate();
	}

	protected void registerUserMetamodels() throws IOException {
		_RegistrationHelper.registerMetamodels(rs, this);

		// Register correspondence metamodel last
		loadAndRegisterMetamodel(projectPath + "/model/" + projectPath + ".ecore");
	}

	@Override
	public void saveModels() throws IOException {
		determineWidthAndHeight();
		t.save(null);
		c.save(null);
		p.save(null);
	}

	private void determineWidthAndHeight() {
		Board b = (Board) t.getContents().get(0);
		
		b.getFields().stream().max((f1,f2) -> f1.getRow() - f2.getRow())
			.ifPresent(f -> b.setHeight(f.getRow() + 1));
		
		b.getFields().stream().max((f1,f2) -> f1.getCol() - f2.getCol())
		.ifPresent(f -> b.setWidth(f.getCol() + 1));
	}

	@Override
	public void loadModels() throws IOException {
		s = loadResource(projectPath + "/instances/src.xmi");
		preprocess();
		t = createResource(projectPath + "/instances/trg.xmi");
		c = createResource(projectPath + "/instances/corr.xmi");
		p = createResource(projectPath + "/instances/protocol.xmi");

		EcoreUtil.resolveAll(rs);
	}

	private void preprocess() {
		Preprocessor p = new Preprocessor(getResourceSet());
		p.preprocess();
	}

	@Override
	public boolean isPatternRelevantForCompiler(String patternName) {
		return patternName.endsWith(PatternSuffixes.FWD);
	}

	private static IbexOptions createIbexOptions() {
		IbexOptions options = new IbexOptions();
		options.projectName("SokobanImportExport");
		options.projectPath("SokobanImportExport");
		options.debug(true);
		options.userDefinedConstraints(new UserDefinedRuntimeTGGAttrConstraintFactory());
		return options;
	}
}
