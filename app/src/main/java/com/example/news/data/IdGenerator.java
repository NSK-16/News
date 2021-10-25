package com.example.news.data;

import java.util.UUID;

public  class IdGenerator {

	public static int generateUniqueId() {
		UUID idOne = UUID.randomUUID();
		String str=""+idOne;
		int uid=str.hashCode();
		String filterStr=""+uid;
		str=filterStr.replaceAll("-", "");
		int id = Integer.parseInt(str);
		return id;
	}
}
