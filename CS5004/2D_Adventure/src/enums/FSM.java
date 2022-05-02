package enums;

import gameobject.GameObject;

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
	
	public abstract void update(GameObject gameObject);
}
