package com.mart.strides.common.item;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockYellowFlower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ItemFlowerStrides extends ItemArmorBase {

    public ItemFlowerStrides(String registryName) {
        super(registryName);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        BlockPos pos = player.getPosition();

        if(player.getEntityWorld().getBlockState(pos).getBlock() == Blocks.RED_FLOWER){
            return;
        }

        Random rand = new Random();
        int flowerMeta = rand.nextInt(9);

        player.getEntityWorld().setBlockState(pos, Blocks.RED_FLOWER.getStateFromMeta(flowerMeta));

        super.onArmorTick(world, player, itemStack);
    }
}
