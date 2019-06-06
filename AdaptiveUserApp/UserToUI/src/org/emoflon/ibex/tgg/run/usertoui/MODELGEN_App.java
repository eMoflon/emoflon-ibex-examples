package org.emoflon.ibex.tgg.run.usertoui;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.gen.MODELGEN;
import org.emoflon.ibex.tgg.operational.strategies.gen.MODELGENStopCriterion;
import org.emoflon.ibex.tgg.runtime.engine.DemoclesTGGEngine;

public class MODELGEN_App extends MODELGEN {

	public MODELGEN_App() throws IOException {
		super(createIbexOptions());
		registerBlackInterpreter(new DemoclesTGGEngine());
	}

	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);

		logger.info("Starting MODELGEN");
		long tic = System.currentTimeMillis();
		MODELGEN_App generator = new MODELGEN_App();
		long toc = System.currentTimeMillis();
		logger.info("Completed init for MODELGEN in: " + (toc - tic) + " ms");
		
		MODELGENStopCriterion stop = new MODELGENStopCriterion(generator.getTGG());
		//stop.setTimeOutInMS(2000);
//		stop.setMaxRuleCount("R4MinimalisticUserInterface", 1);
//		stop.setMaxRuleCount("R5MinimizedNavigationInformation", 1);
//		stop.setMaxRuleCount("R6DisplayExtraInfo", 1);
//		stop.setMaxRuleCount("R1BadMood", 1);
//		stop.setMaxRuleCount("R2NeutralMood", 1);
//		stop.setMaxRuleCount("R3GoodMood", 1);
//		stop.setMaxRuleCount("R7LowLight", 1);
//		stop.setMaxRuleCount("R8NormalLight", 1);
//		stop.setMaxRuleCount("R9HighLight", 1);
//		stop.setMaxRuleCount("R23LowBattery", 1);
//		stop.setMaxRuleCount("R24Charging", 1);
//		stop.setMaxRuleCount("R19Smartphone", 1);
//		stop.setMaxRuleCount("R17InexperiencedUser", 1);
//		stop.setMaxRuleCount("R18ExperiencedUser", 1);
//		stop.setMaxRuleCount("R26FastMobileNetConnectionRule", 1);
//		stop.setMaxRuleCount("R27SlowMobileNetConnectionRule", 1);
//		stop.setMaxRuleCount("R10DayRule", 1);
//		stop.setMaxRuleCount("R11NightModeRule", 1);
//		stop.setMaxRuleCount("R12VocalUI", 1);
//		stop.setMaxRuleCount("R13ImpairedVision", 1);
//		stop.setMaxRuleCount("R14MiddleAgedUser", 1);
//		stop.setMaxRuleCount("R15OlderUser", 1);
//		stop.setMaxRuleCount("R16YoungerUser", 1);
//		stop.setMaxRuleCount("R21DrivingMode", 1);
//		stop.setMaxRuleCount("R22DeactivateDrivingMode", 1);
//		stop.setMaxRuleCount("R25WiFi", 1);
//		stop.setMaxRuleCount("R28DisableVocalUI", 1);
//		stop.setMaxRuleCount("R20Tablet", 1);
	//	stop.setMaxRuleCount("BasicRule", 4);
		
		generator.setStopCriterion(stop);
		
		tic = System.currentTimeMillis();
		generator.run();
		toc = System.currentTimeMillis();
		logger.info("Completed MODELGEN in: " + (toc - tic) + " ms");
		
		generator.saveModels();
		generator.terminate();
	}
	
	
	@Override
	protected void registerUserMetamodels() throws IOException {
		_RegistrationHelper.registerMetamodels(rs, this);
			
		// Register correspondence metamodel last
		loadAndRegisterCorrMetamodel(options.projectPath() + "/model/" + options.projectName() + ".ecore");
	}
	
	private static IbexOptions createIbexOptions() {
		return _RegistrationHelper.createIbexOptions();
	}
}
