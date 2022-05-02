package main;

import gameobject.GameObject;

import java.util.ArrayList;

import components.MonsterTest;
import components.NPCTest;
import components.ObjectTest;

public class AssetSetter {

	private GamePanel gp;
	private ArrayList<GameObject> obj;
	private ArrayList<GameObject> npc;
	private ArrayList<GameObject> monster;
	
	public AssetSetter (GamePanel gp) {
		this.gp = gp;
		this.setObj(new ArrayList<>());
		this.setNpc(new ArrayList<>());
		this.setMonster(new ArrayList<>());
	}
	
	public void addItem(GameObject gameObject, int x, int y) {
		int scale = gp.tileSize;
		gameObject.setWorldX(x*scale);
		gameObject.setWorldY(y*scale);
		if(new ObjectTest<GameObject>().test(gameObject)) {
			getObj().add(gameObject);
		} else if(new NPCTest<GameObject>().test(gameObject)) {
			getNpc().add(gameObject);
		} else if(new MonsterTest<GameObject>().test(gameObject)) {
			getMonster().add(gameObject);
			System.out.println("monster added");
		}
	}
	
	//#####################################################################
	// 								Helpers
	//#####################################################################
	
	public void reset() {
		setObj(new ArrayList<>());
		setNpc(new ArrayList<>());
		setMonster(new ArrayList<>());
	}
	
	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	public ArrayList<GameObject> getObj() {
		return obj;
	}

	private void setObj(ArrayList<GameObject> obj) {
		this.obj = obj;
	}

	public ArrayList<GameObject> getNpc() {
		return npc;
	}

	private void setNpc(ArrayList<GameObject> npc) {
		this.npc = npc;
	}

	public ArrayList<GameObject> getMonster() {
		return monster;
	}

	public void setMonster(ArrayList<GameObject> monster) {
		this.monster = monster;
	}
}
