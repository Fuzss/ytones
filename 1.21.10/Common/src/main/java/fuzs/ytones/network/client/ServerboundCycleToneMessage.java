package fuzs.ytones.network.client;

import fuzs.puzzleslib.api.network.v4.codec.ExtraStreamCodecs;
import fuzs.puzzleslib.api.network.v4.message.MessageListener;
import fuzs.puzzleslib.api.network.v4.message.play.ServerboundPlayMessage;
import fuzs.ytones.world.level.block.ToneProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public record ServerboundCycleToneMessage(int carriedIndex,
                                          InteractionHand interactionHand,
                                          int value) implements ServerboundPlayMessage {
    public static final StreamCodec<ByteBuf, ServerboundCycleToneMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.SHORT.map(Short::intValue, Integer::shortValue),
            ServerboundCycleToneMessage::carriedIndex,
            ExtraStreamCodecs.fromEnum(InteractionHand.class),
            ServerboundCycleToneMessage::interactionHand,
            ByteBufCodecs.BYTE.map(Byte::intValue, Integer::byteValue),
            ServerboundCycleToneMessage::value,
            ServerboundCycleToneMessage::new);

    @Override
    public MessageListener<Context> getListener() {
        return new MessageListener<Context>() {
            @Override
            public void accept(Context context) {
                context.packetListener()
                        .handleSetCarriedItem(new ServerboundSetCarriedItemPacket(ServerboundCycleToneMessage.this.carriedIndex));
                ItemStack itemInHand = context.player().getItemInHand(ServerboundCycleToneMessage.this.interactionHand);
                context.player()
                        .setItemInHand(ServerboundCycleToneMessage.this.interactionHand,
                                ToneProvider.cycle(itemInHand, ServerboundCycleToneMessage.this.value));
            }
        };
    }
}
