package fuzs.ytones.network.client;

import fuzs.puzzleslib.api.network.v3.ServerMessageListener;
import fuzs.puzzleslib.api.network.v3.ServerboundMessage;
import fuzs.ytones.Ytones;
import fuzs.ytones.mixin.accessor.LivingEntityAccessor;
import fuzs.ytones.network.ClientboundPopHeldItemMessage;
import fuzs.ytones.world.level.block.ToneProvider;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public record ServerboundCycleToneMessage(int carriedIndex, InteractionHand interactionHand, int value) implements ServerboundMessage<ServerboundCycleToneMessage> {
    @Override
    public ServerMessageListener<ServerboundCycleToneMessage> getHandler() {
        return new ServerMessageListener<>() {

            @Override
            public void handle(ServerboundCycleToneMessage message, MinecraftServer server, ServerGamePacketListenerImpl handler, ServerPlayer player, ServerLevel level) {
                handler.handleSetCarriedItem(new ServerboundSetCarriedItemPacket(message.carriedIndex));
                ItemStack itemInHand = player.getItemInHand(message.interactionHand);
                player.setItemInHand(message.interactionHand, ToneProvider.cycle(itemInHand, message.value));
                ((LivingEntityAccessor) player).ytones$callDetectEquipmentUpdates();
                Ytones.NETWORK.sendTo(player, new ClientboundPopHeldItemMessage(message.interactionHand));
            }
        };
    }
}
