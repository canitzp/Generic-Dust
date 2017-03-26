package de.canitzp.genericdust.item;

import de.canitzp.genericdust.GenericDust;
import de.ellpeck.actuallyadditions.api.ActuallyAdditionsAPI;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author canitzp
 */
public class ItemDust extends ColoredItem{

    public ItemDust(String name, int color) {
        super(name, color);
    }

    @Override
    public String getCategoryName() {
        return "dust";
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        ItemStack ore = getOre();
        ItemStack ingot = getIngot();
        if(GenericDust.ACTADD && ore != null){
            ActuallyAdditionsAPI.addCrusherRecipe(ore, new ItemStack(this), null, 0);
        }
        if(ingot != null){
            GameRegistry.addSmelting(this, ingot, 0);
        }
    }
}
