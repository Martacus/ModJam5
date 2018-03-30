package com.mart.strides.common.events;

import com.mart.strides.common.registration.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonEvents {

    @SubscribeEvent
    public static void onHit(LivingHurtEvent event){
        if(event.getEntityLiving() instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            Iterable<ItemStack> armor = player.getArmorInventoryList();
            for (ItemStack arm : armor) {
                if (arm.getItem() == ModItems.TRIGGER_STRIDES) {
                    BlockPos pos = player.getPosition();
                    player.getEntityWorld().newExplosion(player, pos.getX(), pos.getY(), pos.getZ(), 5, false, false);
                }
            }
        }
    }

}
