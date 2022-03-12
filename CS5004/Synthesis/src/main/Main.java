package main;
import entities.*;
import items.*;

public class Main {

	public static void main(String[] args) {
		Entities player = new Player();
		Entity skel1 = new Skeleton();
		Entity skel2 = new Skeleton();
		Item weapon = new MeleeWeapon();
		
		System.out.println(player.getHealth());
		System.out.println(skel1.getHealth()+ "\n");
		
		System.out.println(player.getSpeech());
		System.out.println(skel1.getSpeech() + "\n");
		
		player.addWeapon((Weapons) weapon);
		System.out.println(player.getWeapon());
		
		System.out.println(player);
		System.out.println(skel1);
		
		System.out.println(Skeleton.getSkeletonCount());
		System.out.println(Player.getPlayerCount());
	}

}
