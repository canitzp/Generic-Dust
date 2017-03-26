package de.canitzp.genericdust.item;

import de.canitzp.genericdust.GenericDust;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.Recipes;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

/**
 * @author canitzp
 */
public class ItemPurified extends ColoredItem {

    public ItemPurified(String name, int color) {
        super(name, color);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format("genericdust.category." + this.getCategoryName()) + " " + I18n.format("genericdust.resource." + this.name);
    }

    @Override
    public String getCategoryName() {
        return "purified";
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        if(GenericDust.IC2){
            ItemStack dust = get("dust");
            if(dust != null){
                ItemStack pile = get("pile");
                Recipes.centrifuge.addRecipe(new RecipeInputOreDict(this.getOreDictName()), null, false, dust, pile);
            }
        }
    }
}
