package de.canitzp.genericdust.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * @author canitzp
 */
public class ItemNugget extends ColoredItem {

    public ItemNugget(String name, int color) {
        super(name, color);
    }

    @Override
    public String getCategoryName() {
        return "nugget";
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        ItemStack ingot = getIngot();
        if(ingot != null){
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(this, 9), ingot));
            GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "XXX", "XXX", "XXX", 'X', this.getOreDictName()));
        }
    }
}
