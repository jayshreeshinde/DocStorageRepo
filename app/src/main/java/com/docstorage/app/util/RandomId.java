package com.docstorage.app.util;

import java.util.Random;

public final class RandomId {
	public static String generateDocumentId() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int length=20;
		String randomId;
		Random rand = new Random();
		char[] text = new char[length];
		for(int i=0; i< length; i++) {
			text[i] = characters.charAt(rand.nextInt(characters.length()));
		}
		randomId = new String(text);
		return randomId;
	}

}

