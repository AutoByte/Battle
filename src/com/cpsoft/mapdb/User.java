package com.cpsoft.mapdb;

import java.io.Serializable;

public class User implements Serializable, Cloneable {

	private String name;
	private String pass;
	private int level;
	private int exp;
	private int hp;
	private int sh;
	private int ap;
	private int hitChance;
	private int spd;
	private int wins;
	private int losses;
	

	public User(String name, String pass, int level, int exp, int hp, int sh, int ap, int hc) {
		super();
		this.name = name;
		this.pass = pass;
		this.level = level;
		this.exp = exp;
		this.hp = hp;
		this.sh = sh;
		this.ap = ap;
		this.hitChance = hc;
	}
	
	public User(String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
		this.level = 1;
		this.exp = 0;
		this.hp = 5;
		this.sh = 5;
		this.ap = 5;
		this.hitChance = 50;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getSh() {
		return sh;
	}
	public void setSh(int sh) {
		this.sh = sh;
	}
	public int getAp() {
		return ap;
	}
	public void setAp(int ap) {
		this.ap = ap;
	}
	
	public int getHitChance() {
		return hitChance;
	}

	public void setHitChance(int hitChance) {
		this.hitChance = hitChance;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	//Defined clone method in Department class.
	@Override
	protected Object clone() throws CloneNotSupportedException {
		User cloned = (User)super.clone();
		return cloned;
	}
}
