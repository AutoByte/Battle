package com.cpsoft.mapdb;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import junit.framework.TestCase;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class UserDBManager extends TestCase {
	DB db;
	ConcurrentNavigableMap<String,User> users;
	
	public UserDBManager() {
		db = DBMaker.newFileDB(new File("GameDB"))
	            .closeOnJvmShutdown()
	            .encryptionEnable("password")
	            .make();
		users = db.getTreeMap("users");
	}

	//Add
	public void addUser(User u) {
		users.put(u.getName(), u);
		db.commit();
	}
	//Delete
	public void deleteUser(String name) {
		users.remove(name);
		db.commit();
	}
	//Update
	public void updateUser(User u) {
		addUser(u);
	}
	//Get
	public User getUser(String name) {
		return users.get(name);
	}
	
	//loginCheck
	public boolean checkLogin(String name, String pass) {
		if(name != null && pass != null) {
			User u = users.get(name);
			if(u != null) {
				if(pass.equals(u.getPass())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		UserDBManager udbm = new UserDBManager();
		//udbm.addUser(new User("pfort", "ppass1959"));
		//udbm.addUser(new User("cfort", "cpass1960"));
		udbm.addUser(new User("rambo", "rpass2010"));
		
		assertFalse(udbm.checkLogin("pfort", "ppass"));
		assertFalse(udbm.checkLogin("rambo", "ppass"));
		assertTrue(udbm.checkLogin("cfort", "cpass1960"));
		assertTrue(udbm.checkLogin("rambo", "rpass2010"));
		udbm.deleteUser("rambo");
		assertFalse(udbm.checkLogin("rambo", "rpass2010"));
		
		User u1 = udbm.getUser("pfort");
		User u2 = (User) u1.clone();
		u1.setLevel(99);
		udbm.updateUser(u1);
		assertNotSame(u1.getLevel(), u2.getLevel());
		assertTrue(udbm.checkLogin("pfort", "ppass1959"));
		assertEquals(udbm.getUser("pfort").getLevel(), 99);
		u1.setLevel(1);
		udbm.updateUser(u1);
	}
}
