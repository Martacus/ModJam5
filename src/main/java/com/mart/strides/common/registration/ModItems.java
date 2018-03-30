package com.mart.strides.common.registration;

import com.google.common.collect.Lists;
import com.mart.strides.Strides;
import com.mart.strides.common.item.*;
import com.mart.strides.common.util.IModelProvider;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@GameRegistry.ObjectHolder(Strides.MODID)
@Mod.EventBusSubscriber(modid = Strides.MODID)
public class ModItems {

    public static final Item WATER_STRIDES = Items.AIR;
    public static final Item FLOWER_STRIDES = Items.AIR;
    public static final Item ANIMAL_STRIDES = Items.AIR;
    public static final Item TRIGGER_STRIDES = Items.AIR;
    public static final Item ICE_STRIDES = Items.AIR;
    public static final Item HERMES_STRIDES = Items.AIR;
    public static final Item OBSIDIAN_STRIDES = Items.AIR;

    static List<Item> items;

    @SubscribeEvent
    public static void init(RegistryEvent.Register<Item> event){
        items = Lists.newArrayList(
                new ItemWaterStrides("water_strides"),
                new ItemFlowerStrides("flower_strides"),
                new ItemAnimalStrides("animal_strides"),
                new ItemTriggerStrides("trigger_strides"),
                new ItemIceStrides("ice_strides"),
                new ItemHermesStrides("hermes_strides"),
                new ItemObsidianStrides("obsidian_strides")
        );

        event.getRegistry().registerAll(items.toArray(new Item[0]));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event){
        items.stream().filter(item -> item instanceof IModelProvider).forEach(item ->{
            Int2ObjectArrayMap<String> variants = new Int2ObjectArrayMap<>();
            ((IModelProvider) item).gatherVariants(variants);
            for(Int2ObjectArrayMap.Entry<String> entry : variants.int2ObjectEntrySet()){
                ModelLoader.setCustomModelResourceLocation(item, entry.getIntKey(), new ModelResourceLocation(item.getRegistryName(), entry.getValue()));
            }
        });
    }

}
