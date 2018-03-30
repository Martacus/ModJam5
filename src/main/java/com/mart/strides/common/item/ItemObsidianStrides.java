package com.mart.strides.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemObsidianStrides extends ItemArmorBase {

    public ItemObsidianStrides(String registryName) {
        super(registryName);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if(!player.onGround && player.motionY < 0){
            player.motionY = - 3;
            player.fallDistance = 0;
        }
    }
}
