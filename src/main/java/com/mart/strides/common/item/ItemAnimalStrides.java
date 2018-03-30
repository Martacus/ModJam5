package com.mart.strides.common.item;

import com.mart.strides.common.ai.EntityAITemptBoots;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class ItemAnimalStrides extends ItemArmorBase {

    public ItemAnimalStrides(String registryName) {
        super(registryName);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        List<EntityAnimal> animalList = player.getEntityWorld().getEntitiesWithinAABB(EntityAnimal.class,
                new AxisAlignedBB(player.getPosition().getX() - 10, player.getPosition().getY(), player.getPosition().getZ() - 10,
                        player.getPosition().getX() + 10, player.getPosition().getY() + 5, player.getPosition().getZ() + 10));

        for(EntityAnimal animal : animalList){
            boolean shouldSkip = false;
            for(EntityAITasks.EntityAITaskEntry task : animal.tasks.taskEntries){
                if(task.action instanceof EntityAITemptBoots){
                    shouldSkip = true;
                }
            }

            if (shouldSkip){
                continue;
            }

            animal.tasks.addTask(1, new EntityAITemptBoots(animal, 1.25D, false));
        }
        super.onArmorTick(world, player, itemStack);
    }
}
