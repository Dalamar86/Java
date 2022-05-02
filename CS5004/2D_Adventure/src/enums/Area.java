package enums;

import object.*;

import java.util.ArrayList;

import main.GamePanel;
import monster.*;
import npc.*;

public enum Area {
	DEBUG {
		@Override
		public void setup(GamePanel gp) {
			// Add Objects
			gp.aSetter.addItem(new OBJ_Key(gp), 27, 16);
			gp.aSetter.addItem(new OBJ_Key(gp), 23, 40);
			
			gp.aSetter.addItem(new OBJ_Shield_Blue(gp), 38, 8);
			
			gp.aSetter.addItem( new OBJ_Door(gp), 10, 12);
			gp.aSetter.addItem( new OBJ_Door(gp), 12, 23);
			gp.aSetter.addItem( new OBJ_Door(gp), 8, 28);
			
			gp.aSetter.addItem(new OBJ_Chest(gp), 10, 9);
			
			gp.aSetter.addItem(new OBJ_Boots(gp),37, 42);
			
			// Add NPCs
			gp.aSetter.addItem(new NPC_OldMan(gp), 21, 21);
			gp.aSetter.addItem(new NPC_OldMan(gp), 37, 21);
			
			// Add Monsters
			gp.aSetter.addItem(new MON_GreenSlime(gp), 21, 40);
			gp.aSetter.addItem(new MON_GreenSlime(gp), 23, 36);
			gp.aSetter.addItem(new MON_GreenSlime(gp), 37, 10);
			gp.aSetter.addItem(new MON_GreenSlime_Boss(gp), 11, 32);
		}
		
		@Override
		public void resetMonster(GamePanel gp) {
			
			gp.aSetter.setMonster(new ArrayList<>());
			// Add Monsters
			gp.aSetter.addItem(new MON_GreenSlime(gp), 21, 40);
			gp.aSetter.addItem(new MON_GreenSlime(gp), 23, 36);
			gp.aSetter.addItem(new MON_GreenSlime(gp), 37, 10);
		}
	},
	MAIN {
		@Override
		public void setup(GamePanel gp) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resetMonster(GamePanel gp) {
			// TODO Auto-generated method stub
			
		}
	},
	LEFT {
		@Override
		public void setup(GamePanel gp) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resetMonster(GamePanel gp) {
			// TODO Auto-generated method stub
			
		}
	},
	RIGHT {
		@Override
		public void setup(GamePanel gp) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resetMonster(GamePanel gp) {
			// TODO Auto-generated method stub
			
		}
	},
	TOP {
		@Override
		public void setup(GamePanel gp) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resetMonster(GamePanel gp) {
			// TODO Auto-generated method stub
			
		}
	},
	BOTTOM {
		@Override
		public void setup(GamePanel gp) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resetMonster(GamePanel gp) {
			// TODO Auto-generated method stub
			
		}
	},
	TEMPLE {
		@Override
		public void setup(GamePanel gp) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resetMonster(GamePanel gp) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	public abstract void setup(GamePanel gp);
	public abstract void resetMonster(GamePanel gp);
	
}
