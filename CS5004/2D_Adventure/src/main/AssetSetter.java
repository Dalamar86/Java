package main;

import java.util.ArrayList;

import gameobject.GameObject;
import components.MonsterTest;
import components.NPCTest;
import components.ObjectTest;

/**
 * This class is responsible for setting up all the objects in each level.
 * 
 * @author Robert Wilson
 *
 */
public class AssetSetter {
	// Current Game Panel
	private GamePanel gp;
	// lists of the gameObjects to add to the map
	private ArrayList<GameObject> obj;
	private ArrayList<GameObject> npc;
	private ArrayList<GameObject> monster;
	
	/**
	 * Creates a new instance of the asset setter
	 * 
	 * @param gp Current Game Panel
	 */
	public AssetSetter (GamePanel gp) {
		this.gp = gp;
		this.setObj(new ArrayList<>());
		this.setNpc(new ArrayList<>());
		this.setMonster(new ArrayList<>());
	}
	
	/**
	 * Uses the predicate tests to determine which list to add the gameObject to.  
	 * The location of the object is given by the x and y parameters.
	 * 
	 * @param gameObject (GameObject) the object to add to the list
	 * @param x (int) map column position 
	 * @param y (int) map row position
	 */
	public void addItem(GameObject gameObject, int x, int y) {
		// Set the gameObject location
		int scale = gp.tileSize;
		gameObject.setWorldX(x*scale);
		gameObject.setWorldY(y*scale);
		
		// Decide which list to add to depending on the object type
		if(new ObjectTest<GameObject>().test(gameObject)) {
			getObj().add(gameObject);
		} else if(new NPCTest<GameObject>().test(gameObject)) {
			getNpc().add(gameObject);
		} else if(new MonsterTest<GameObject>().test(gameObject)) {
			getMonster().add(gameObject);
		}
	}
	
	//#####################################################################
	// 								Helpers
	//#####################################################################
	
	/**
	 * Resets the lists
	 */
	public void reset() {
		setObj(new ArrayList<>());
		setNpc(new ArrayList<>());
		setMonster(new ArrayList<>());
	}
	
	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	/**
	 * Getter of the asset setter object list
	 * 
	 * @return obj (ArrayList) list of objects to be added to the map
	 */
	public ArrayList<GameObject> getObj() {
		return obj;
	}

	/**
	 * Sets the object list of the Asset Setter
	 * 
	 * @param obj (ArrayList)
	 */
	private void setObj(ArrayList<GameObject> obj) {
		this.obj = obj;
	}

	/**
	 * Getter of the asset setter NPC list
	 * 
	 * @return npc (ArrayList) list of npcs to be added to the map
	 */
	public ArrayList<GameObject> getNpc() {
		return npc;
	}

	/**
	 * Sets the npc list of the Asset Setter
	 * 
	 * @param npc (ArrayList)
	 */
	private void setNpc(ArrayList<GameObject> npc) {
		this.npc = npc;
	}

	/**
	 * Getter of the asset setter monster list
	 * 
	 * @return monster (ArrayList) list of monsters to be added to the map
	 */
	public ArrayList<GameObject> getMonster() {
		return monster;
	}

	/**
	 * Sets the monster list of the Asset Setter
	 * 
	 * @param monster (ArrayList)
	 */
	public void setMonster(ArrayList<GameObject> monster) {
		this.monster = monster;
	}
}
