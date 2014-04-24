package com.cpsoft.mapdb;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class QuickStart2 {

	public static void main(String[] args) {
		//configure and open database using builder pattern.
		//all options are available with code auto-completion.
		DB db = DBMaker.newFileDB(new File("gamedb"))
		            .closeOnJvmShutdown()
		            .encryptionEnable("password")
		            .make();

		//open existing an collection (or create new)
		ConcurrentNavigableMap<Integer,String> map = db.getTreeMap("collectionName");

		String a = map.get(1);
		String b = map.get(2);
		String c = map.get(3);
		if(c == null) {
			c = "I was not saved";
		}
		System.out.println(a + "," + b + "," + c);

		db.close();
		
	}

}
