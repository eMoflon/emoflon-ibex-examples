package org.emoflon.ibex.tgg.operational.csp.constraints.custom.cheat1facebooktoinstagram;

import java.sql.Date;

import org.emoflon.ibex.tgg.operational.csp.RuntimeTGGAttributeConstraint;
import org.emoflon.ibex.tgg.operational.csp.RuntimeTGGAttributeConstraintVariable;

public class UserDefined_FriendshipDate extends RuntimeTGGAttributeConstraint
{

   /**
    * Constraint FriendshipDate(v0)
    * 
    * @see TGGLanguage.csp.impl.ConstraintImpl#solve()
    */
	@Override
	public void solve() {
		if (variables.size() != 1)
			throw new RuntimeException("The CSP -FRIENDSHIPDATE- needs exactly 1 variables");

		RuntimeTGGAttributeConstraintVariable v0 = variables.get(0);
		String bindingStates = getBindingStates(v0);

		
		if (bindingStates.equals("B")) {
			setSatisfied(true);
			
	} else if (bindingStates.equals("F")) {
		v0.bindToValue(generateValue(v0.getType()));
		setSatisfied(true);
	}
	 else {
			throw new UnsupportedOperationException(
					"This case in the constraint has not been implemented yet: " + bindingStates);
		}
}
}

