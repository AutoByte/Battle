package com.cpsoft.mapdb;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.*;

public class QuickStart {

	public QuickStart() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//configure and open database using builder pattern.
		//all options are available with code auto-completion.
		DB db = DBMaker.newFileDB(new File("gamedb"))
		            .closeOnJvmShutdown()
		            .encryptionEnable("password")
		            .make();

		//open existing an collection (or create new)
		ConcurrentNavigableMap<Integer,String> map = db.getTreeMap("collectionName");

		map.put(1, "one");
		map.put(2, "two");
		//map.keySet() is now [1,2]

		db.commit();  //persist changes into disk

		map.put(3, "three");
		//map.keySet() is now [1,2,3]
		db.rollback(); //revert recent changes
		//map.keySet() is now [1,2]

		db.close();
	}
}


