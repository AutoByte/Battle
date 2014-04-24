package com.cpsoft.mapdb;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class Battle {
	Vector	rounds	= null;
	Random	rand	= null;
	User[]	users	= null;

	public Battle(User[] users) {
		this.users = users;
		rounds = new Vector();
		rand = new Random(new Date().getTime());
	}

	private void fire(Matching matching) {
		int hit = rand.nextInt(100);
		if (hit > matching.users[0].getHitChance()) {
			int hp = rand.nextInt(users[0].getAp());
			System.out.println(users[0].getName() + " hit: " + hit + ", hitChance: " + matching.users[0].getHitChance() + ", hp: " + hp );
			users[1].setSh(users[1].getSh()-hp);
		}
		else
			System.out.println(users[0].getName() + " missed.");
		hit = rand.nextInt(100);
		if (hit > matching.users[1].getHitChance()) {
			int hp = rand.nextInt(users[1].getAp());
			System.out.println(users[1].getName() + " hit: " + hit + ", hitChance: " + matching.users[1].getHitChance() + ", hp: " + hp );
			users[0].setSh(users[0].getSh()-hp);
		}
		else
			System.out.println(users[1].getName() + " missed.");
	}

	public User[] fight() {

		while (gameOn()) {
			runRound();
		}
		return users;
	}

	private void runRound() {
		for (int i = 0; i < users.length; i++) {
			users[i].setSpd(rand.nextInt(100));
		}
		Object[] matchings = getMatching(users);
		for (int i = 0; i < matchings.length; i++) {
			fire((Matching)matchings[i]);
		}
		for (int i = 0; i < users.length; i++) {
			users[i].setSpd(0);
		}
	}

	private Object[] getMatching(User[] users) {
		shuffleArray(users);
		ArrayList result = new ArrayList();
		for (int i = 0; i < users.length; i=i+2) {
			result.add(new Matching(users[i], users[i+1]));
		}
		return result.toArray();
	}

	private void shuffleArray(User[] ar) {
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rand.nextInt(i + 1);
			// Simple swap
			User a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	private boolean gameOn() {
		int alive = 0;
		User winner = null;
		for (int i = 0; i < users.length; i++) {
			if(users[i].getSh() > 0){ // || users[i].getHp() > 0)
				alive++;
				winner = users[i];
			}
		}
		System.out.println("Alive: " + alive);
		if(alive > 1)
			return true;
		if(alive == 1)
			System.out.println("The winner is: " + winner.getName());
		return false;
	}
	
	public static void main(String[] args) {
		User[] users = new User[4];
		users[0] = new User("user1", "pass1");
		users[1] = new User("user2", "pass2");
		users[2] = new User("user3", "pass3");
		users[3] = new User("user4", "pass4");
		new Battle(users).fight();
	}
}
