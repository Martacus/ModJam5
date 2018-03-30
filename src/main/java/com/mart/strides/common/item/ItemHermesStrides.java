package com.mart.strides.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHermesStrides extends ItemArmorBase {

    public ItemHermesStrides(String registryName) {
        super(registryName);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {

        if(!player.onGround && player.motionY < 0){
            player.motionY = -0.1;
            player.fallDistance = 0;
        }

        if(!player.isInWater() && player.onGround){
            player.motionX = player.motionX * 1.5;
            player.motionZ = player.motionZ * 1.5;
        }


        super.onArmorTick(world, player, itemStack);
    }
}
