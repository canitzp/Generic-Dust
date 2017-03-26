package de.canitzp.genericdust.item;

import de.canitzp.genericdust.GenericDust;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.Recipes;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author canitzp
 */
public class ItemCrushed extends ColoredItem {

    public ItemCrushed(String name, int color) {
        super(name, color);
    }

    @Override
    public String getCategoryName() {
        return "crushed";
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format("genericdust.category." + this.getCategoryName()) + " " + I18n.format("genericdust.resource." + this.name);
    }

    @Override
    public int getColorLayer() {
        return 1;
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        ItemStack ore = getOre();
        ItemStack ingot = getIngot();
        ItemStack dust = get("dust");
        if(GenericDust.IC2){
            if(ore != null){
                Recipes.macerator.addRecipe(new RecipeInputItemStack(ore), null, false, new ItemStack(this, 2));
            }
            if(dust != null){
                ItemStack stoneDust = getDirect("dustStone");
                ItemStack pile = get("pile");
                pile.stackSize = 2;
                Recipes.centrifuge.addRecipe(new RecipeInputOreDict(this.getOreDictName()), null, false, dust, stoneDust, pile);
                ItemStack purified = get("purified");
                Recipes.oreWashing.addRecipe(new RecipeInputOreDict(this.getOreDictName()), null, false, purified, pile, stoneDust);
            }
        }
        if(ingot != null){
            GameRegistry.addSmelting(this, ingot, 0);
        }
    }
}
