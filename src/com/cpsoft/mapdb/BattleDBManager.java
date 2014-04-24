package com.cpsoft.mapdb;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import junit.framework.TestCase;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class BattleDBManager extends TestCase {
	DB db;
	ConcurrentNavigableMap<String,User> battles;
	
	public BattleDBManager() {
		db = DBMaker.newFileDB(new File("GameDB"))
	            .closeOnJvmShutdown()
	            .encryptionEnable("password")
	            .make();
		battles = db.getTreeMap("battles");
	}

	//Add
	public void addUser(User u) {
		battles.put(u.getName(), u);
		db.commit();
	}
	//Delete
	public void deleteUser(String name) {
		battles.remove(name);
		db.commit();
	}
	//Update
	public void updateUser(User u) {
		addUser(u);
	}
	//Get
	public User getUser(String name) {
		return battles.get(name);
	}
	
	//loginCheck
	public boolean checkLogin(String name, String pass) {
		if(name != null && pass != null) {
			User u = battles.get(name);
			if(u != null) {
				if(pass.equals(u.getPass())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		BattleDBManager bdbm = new BattleDBManager();
		assertNotNull(bdbm);
		
		
	}
}
