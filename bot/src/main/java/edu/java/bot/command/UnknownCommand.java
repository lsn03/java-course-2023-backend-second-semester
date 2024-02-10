package edu.java.bot.command;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnknownCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(
        this.getClass()
    );

    @Override
    public String command() {
        return "/";
    }

    @Override
    public String description() {
        return "Неизвестная комманда.";
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        logger.warn(
            "User @{} entered \"{}\" user_id={}",
            update.message().chat().username(),
            update.message().text(),
            chatId
        );

        return new SendMessage(
            update.message().chat().id(),
            "Неизвестная команда. Используйте /help для списка команд."
        );
    }

    @Override
    public boolean supports(Update update) {

        return Command.super.supports(update);
    }

    @Override
    public BotCommand toApiCommand() {
        return Command.super.toApiCommand();
    }
}
