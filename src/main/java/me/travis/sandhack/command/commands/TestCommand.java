package me.travis.sandhack.command.commands;

import me.travis.sandhack.command.Command;
import me.travis.sandhack.util.ClientMessage;

public class TestCommand extends Command {

    public TestCommand() {
        super("Test");
    }

    @Override
    public void execute(String[] message) {
        if (message.length == 0) {
            ClientMessage.sendErrorMessage("empty message");
            return;
        }
        for (String s : message) {
            ClientMessage.sendMessage(s);
        }
    }

}
