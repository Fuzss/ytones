package fuzs.ytones.client.handler;

import fuzs.puzzlesapi.api.client.slotcycling.v1.ItemCyclingProvider;
import fuzs.ytones.Ytones;
import fuzs.ytones.network.client.ServerboundCycleToneMessage;
import fuzs.ytones.world.level.block.ToneProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public record ToneCyclingProvider(ItemStack itemInHand, InteractionHand interactionHand, ItemStack forwardStack, ItemStack backwardStack) implements ItemCyclingProvider {

    public ToneCyclingProvider(ItemStack itemInHand, InteractionHand interactionHand) {
        this(itemInHand.copyWithCount(1), interactionHand, ToneProvider.cycle(itemInHand, 1), ToneProvider.cycle(itemInHand, -1));
        this.forwardStack.setCount(1);
        this.backwardStack.setCount(1);
    }

    @Override
    public ItemStack getSelectedStack() {
        return this.itemInHand;
    }

    @Override
    public ItemStack getForwardStack() {
        return this.forwardStack;
    }

    @Override
    public ItemStack getBackwardStack() {
        return this.backwardStack;
    }

    @Override
    public int getForwardSlot() {
        return 0;
    }

    @Override
    public int getBackwardSlot() {
        return 0;
    }

    @Override
    public boolean cycleSlotForward() {
        return this.cycleSlot(1);
    }

    @Override
    public boolean cycleSlotBackward() {
        return this.cycleSlot(-1);
    }

    private boolean cycleSlot(int value) {
        Minecraft minecraft = Minecraft.getInstance();
        int selected = minecraft.player.getInventory().selected;
        Ytones.NETWORK.sendToServer(new ServerboundCycleToneMessage(selected, this.interactionHand, value));
        return true;
    }
}
