package com.Jvnyor.discordbot;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PasswordGenerator {

static Random random = new SecureRandom();
	
	public static Stream<Character> getRandomSpecialChars(int count) {
	    Random random = new SecureRandom();
	    IntStream specialChars = random.ints(count, 33, 45);
	    return specialChars.mapToObj(data -> (char) data);
	}
	
	public static String generateSecureRandomPassword() {
	    Stream<Character> pwdStream = Stream.concat(getRandomNumbers(10), 
	      Stream.concat(getRandomSpecialChars(10), 
	      Stream.concat(getRandomAlphabets(10, true), getRandomAlphabets(10, false))));
	    List<Character> charList = pwdStream.collect(Collectors.toList());
	    Collections.shuffle(charList);
	    String password = charList.stream()
	        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
	        .toString();
	    return password;
	}

	public static Stream<Character> getRandomAlphabets(int count, boolean upperCase) {
        IntStream characters = null;
        if (upperCase) {
            characters = random.ints(count, 65, 90);
        } else {
            characters = random.ints(count, 97, 122);
        }
        return characters.mapToObj(data -> (char) data);
    }

	public static Stream<Character> getRandomNumbers(int count) {
        IntStream numbers = random.ints(count, 48, 57);
        return numbers.mapToObj(data -> (char) data);
    }
	
}
