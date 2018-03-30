package com.mart.strides.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemIceStrides extends ItemArmorBase {

    public ItemIceStrides(String registryName) {
        super(registryName);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if(player.getEntityWorld().getBlockState(player.getPosition().add(0, -1, 0)).getBlock() == Blocks.WATER){
            player.getEntityWorld().setBlockState(player.getPosition().add(0, -1, 0), Blocks.ICE.getDefaultState());
        }
    }
}
