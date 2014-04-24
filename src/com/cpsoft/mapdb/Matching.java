package com.cpsoft.mapdb;

public class Matching {
	User[] users = new User[2];
	int[] hit = new int[2];
	int[] hp = new int[2];
	
	public Matching(User u1, User u2) {
		this.users[0] = u1;
		this.users[1] = u2;
	}
}
