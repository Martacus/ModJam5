package com.mart.strides.common.item;

import com.mart.strides.Strides;
import com.mart.strides.common.util.IModelProvider;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemArmorBase extends ItemArmor implements IModelProvider{

    private String registryName;


    public ItemArmorBase(String registryName) {
        super(ArmorMaterial.IRON, 0, EntityEquipmentSlot.FEET);
        this.registryName = registryName;
        setRegistryName(registryName);
        setUnlocalizedName(Strides.MODID + "." + this.registryName);
        setCreativeTab(Strides.STRIDE_TAB);
    }


}
