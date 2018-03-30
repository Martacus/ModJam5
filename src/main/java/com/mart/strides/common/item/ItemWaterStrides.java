package com.mart.strides.common.item;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWaterStrides extends ItemArmorBase {

    public ItemWaterStrides(String registryName) {
        super(registryName);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if(player.isInWater()){
            if(player.moveForward > 0){
                float f1 = 0.08f;
                f1 += (0.54600006F - f1) * 6.8f / 3.0F;
                player.motionX *= (double)f1;
                player.motionZ *= (double)f1;
            }
        }
        super.onArmorTick(world, player, itemStack);
    }
}
