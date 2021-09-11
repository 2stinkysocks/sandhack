package me.travis.sandhack.networking.handler;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.networking.Packet;
import me.travis.sandhack.networking.packets.client.NewClientPacket;
import me.travis.sandhack.networking.packets.ping.PingUpPacket;
import net.minecraft.client.Minecraft;

import java.io.IOException;

/**
 * @author Madmegsox1
 * @since 30/05/2021
 */

public class ClientHandling {
    public String token;
    private final Minecraft mc = Minecraft.getMinecraft();

    public ClientHandling(){
        token = "";
    }

    public void newClient(){
        try {
            Packet packetClient = new NewClientPacket(); // TODO fix null
            String[] data = packetClient.run();
            if(data[0].equals("server") && data[1].equals("newclient")){
               if(data[2].equals("false")){ // loads token
                   this.token = Sandhack.CONFIG_MANAGER.loadIRCtoken();
               }
               else if(data[2].equals("true")){ // saves token
                   this.token = data[3];
                   Sandhack.CONFIG_MANAGER.saveIRCtoken(this.token);
               }
            }
            if(!token.isEmpty()){
                Packet packetUp = new PingUpPacket();
                data = packetUp.run(token);
                if(data[0].equals("server") && data[1].equals("pingup")){
                    Sandhack.LOGGER.info("IRC chat init complete");
                }else {
                    Sandhack.LOGGER.error("IRC chat not didnt init");
                }
            }
        }catch (IOException e){
            Sandhack.LOGGER.error("Exception in loading new client for IRC " + e);
        }
    }
}
