package fuzs.ytones.network;

import fuzs.puzzleslib.api.network.v3.ClientMessageListener;
import fuzs.puzzleslib.api.network.v3.ClientboundMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;

public record ClientboundPopHeldItemMessage(InteractionHand interactionHand) implements ClientboundMessage<ClientboundPopHeldItemMessage> {

    @Override
    public ClientMessageListener<ClientboundPopHeldItemMessage> getHandler() {
        return new ClientMessageListener<>() {

            @Override
            public void handle(ClientboundPopHeldItemMessage message, Minecraft client, ClientPacketListener handler, LocalPlayer player, ClientLevel level) {
//                player.getItemInHand(message.interactionHand).setPopTime(5);
            }
        };
    }
}
