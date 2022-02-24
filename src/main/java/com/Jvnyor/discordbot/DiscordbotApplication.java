package com.Jvnyor.discordbot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

@SpringBootApplication
public class DiscordbotApplication {

	@Component
    class BotConnection implements ApplicationRunner {
		
        @Override
        public void run(ApplicationArguments args) throws Exception {
        	
        	final String token = "";
    		final DiscordClient client = DiscordClient.create(token);
        	
        	client.login().flatMapMany(gateway -> gateway.on(MessageCreateEvent.class))
	    	.map(MessageCreateEvent::getMessage)
	    	.filter(message -> "!password".equals(message.getContent()))
	    	.flatMap(Message::getChannel)
	    	.flatMap(channel -> channel.createMessage(PasswordGenerator.generateSecureRandomPassword()))
	    	.blockLast();
        }
    }
	
	public static void main(String[] args) {
		SpringApplication.run(DiscordbotApplication.class, args);
	}

}
