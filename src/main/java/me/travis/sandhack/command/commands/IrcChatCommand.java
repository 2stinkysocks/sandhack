package me.travis.sandhack.command.commands;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.command.Command;
import me.travis.sandhack.util.ClientMessage;

/**
 * @author Madmegsox1
 * @since 30/05/2021
 */

public class IrcChatCommand extends Command {

    public IrcChatCommand(){
        super("irc");
    }
    @Override
    public void execute(String[] message) {
        if(message[0].equals("set")){
            switch (message[1]) {
                case "global":
                    Sandhack.CLIENT_HANDLING.newClient();
                    Sandhack.CHAT_HANDLING.setRunning(true);
                    Sandhack.CHAT_HANDLING.setToGlobal();
                    Sandhack.CHAT_HANDLING.start();
                    ClientMessage.sendIRCMessage("");
                    break;
                case "direct":
                case "dm":
                    if (message.length > 2) {
                        Sandhack.CLIENT_HANDLING.newClient();
                        Sandhack.CHAT_HANDLING.setRunning(true);
                        Sandhack.CHAT_HANDLING.setToDirect(message[2]);
                        Sandhack.CHAT_HANDLING.start();
                        ClientMessage.sendIRCMessage("");
                    }
                    break;
                case "server":
                    Sandhack.CHAT_HANDLING.setRunning(false);
                    Sandhack.CHAT_HANDLING.setToServer();
                    ClientMessage.sendIRCMessage("");
                    break;
            }
        }
    }
}
