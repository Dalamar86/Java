package enums;

import gameobject.GameObject;

/**
 * Enum which houses the Finite-State Machine which the monsters 
 * use to determine their actions.
 * 
 * 
 * @author Robert Wilson
 *
 */
public enum FSM {
	IDLE {
		@Override
		public void update(GameObject gameObject) {
			gameObject.setAction();
		}
	},
	ATTACKING {
		@Override
		public void update(GameObject gameObject) {
			gameObject.attack();
		}
	},
	EVADE {
		@Override
		public void update(GameObject gameObject) {
			gameObject.damageReaction();
		}
	},
	AIDE {
		@Override
		public void update(GameObject gameObject) {
			gameObject.findAide();
		}
	};
	
	/**
	 * the update method of the FSM
	 * 
	 * @param gameObject The gameObject who owns this particular FSM 
	 */
	public abstract void update(GameObject gameObject);
}
