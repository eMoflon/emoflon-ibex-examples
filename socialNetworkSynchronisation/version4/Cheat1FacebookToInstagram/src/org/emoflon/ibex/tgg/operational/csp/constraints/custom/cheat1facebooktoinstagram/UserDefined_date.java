package org.emoflon.ibex.tgg.operational.csp.constraints.custom.cheat1facebooktoinstagram;

import java.util.Date;

import org.emoflon.ibex.tgg.operational.csp.RuntimeTGGAttributeConstraint;
import org.emoflon.ibex.tgg.operational.csp.RuntimeTGGAttributeConstraintVariable;

public class UserDefined_date extends RuntimeTGGAttributeConstraint
{

   /**
    * Constraint date(v0)
    * 
    * @see TGGLanguage.csp.impl.ConstraintImpl#solve()
    */
	@Override
	public void solve() {
		if (variables.size() != 1)
			throw new RuntimeException("The CSP -DATE- needs exactly 1 variables");

		RuntimeTGGAttributeConstraintVariable v0 = variables.get(0);
		String bindingStates = getBindingStates(v0);

		if (bindingStates.equals("F")) {
	  		String type = v0.getType();
	  		Date value = (Date) generateValue(type);
	  		v0.bindToValue(value);
	  		setSatisfied(true);
	  		return;
	  	} 
		else{
			throw new UnsupportedOperationException("This case in the constraint has not been implemented yet: " + bindingStates);
		}
	}
}

