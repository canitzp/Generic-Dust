package de.canitzp.genericdust.item;

import de.canitzp.genericdust.GenericDust;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.Recipes;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * @author canitzp
 */
public class ItemPile extends ColoredItem {

    public ItemPile(String name, int color) {
        super(name, color);
    }

    @Override
    public String getCategoryName() {
        return "pile";
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format("genericdust.category." + this.getCategoryName()) + " " + I18n.format("genericdust.resource." + this.name);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        ItemStack dust = get("dust");
        if(GenericDust.IC2) {
            ItemStack plate = get("plate");
            if(plate != null){
                Recipes.macerator.addRecipe(new RecipeInputItemStack(plate), null, false, new ItemStack(this, 8));
            }
            Recipes.compressor.addRecipe(new RecipeInputOreDict(this.getOreDictName(), 9), null, false, dust);
        }
        GameRegistry.addRecipe(new ShapedOreRecipe(dust, "XXX", "XXX", "XXX", 'X', this.getOreDictName()));
    }
}
