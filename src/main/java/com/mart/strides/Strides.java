package com.mart.strides;

import com.mart.strides.common.CommonProxy;
import com.mart.strides.common.registration.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Strides.MODID)
@Mod.EventBusSubscriber(modid = Strides.MODID)
public class Strides {

    public final static String MODID = "strides";
    public final static String VERSION = "@VERSION@";

    @SidedProxy(serverSide = "com.mart.strides.common.CommonProxy", clientSide = "com.mart.strides.client.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static Strides strides;

    public static final CreativeTabs STRIDE_TAB = new CreativeTabs(MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.WATER_STRIDES);
        }
    };

    public Strides(){

    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

    }

    @Mod.EventHandler
    public void preInit(FMLInitializationEvent event){

    }

    @Mod.EventHandler
    public void preInit(FMLPostInitializationEvent event){

    }


}
