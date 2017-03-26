package de.canitzp.genericdust;

import de.canitzp.genericdust.item.ColoredItem;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Random;

/**
 * @author canitzp
 */
@Mod(modid = GenericDust.MODID, name = GenericDust.MODNAME, version = GenericDust.VERSION, dependencies = GenericDust.DEPENDENCIES)
public class GenericDust {

    public static final String MODID = "genericdust";
    public static final String MODNAME = "Generic Dust";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "after:actuallyadditions;after:ic2";

    public static CreativeTabs tab = new CreativeTabs(MODID) {
        @Override
        public Item getTabIconItem() {
            return ColoredItem.registeredItems.get(MathHelper.getInt(new Random(), 0, ColoredItem.registeredItems.size()-1));
        }
    };

    public static final boolean ACTADD = Loader.isModLoaded("actuallyadditions");
    public static final boolean IC2 = Loader.isModLoaded("IC2");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        OreInspection.createDusts();

        for(ColoredItem item : ColoredItem.registeredItems){
            item.preInit(event);
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        OreDictionary.registerOre("gemCoal", new ItemStack(Items.COAL));
        OreDictionary.registerOre("gemCoal", new ItemStack(Items.COAL, 1, 1));
        if(IC2){
            OreDictionary.registerOre("gemUranium", new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ic2", "nuclear")), 1, 2));
        }

        for(ColoredItem item : ColoredItem.registeredItems){
            item.init(event);
            if(OreDictionary.getOres(item.getOreDictName()).isEmpty() && OreInspection.existsOre(item.name)){
                OreDictionary.registerOre(item.getOreDictName(), item);
            } else {
                item.isActive = false;
            }
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        for(ColoredItem item : ColoredItem.registeredItems){
            if(item.isActive){
                item.postInit(event);
            }
        }
    }

}
