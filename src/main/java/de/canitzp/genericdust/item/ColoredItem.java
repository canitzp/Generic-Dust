package de.canitzp.genericdust.item;

import de.canitzp.genericdust.GenericDust;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author canitzp
 */
public abstract class ColoredItem extends Item{

    public static final List<ColoredItem> registeredItems = new ArrayList<>();

    protected int color;
    public String name;

    public boolean isActive = true;

    public ColoredItem(String name, int color){
        this.setRegistryName(new ResourceLocation(GenericDust.MODID, this.getCategoryName() + "_" + name));
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setCreativeTab(GenericDust.tab);
        this.color = color;
        this.name = name;

        registeredItems.add(this);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
        if(!this.isActive){
            tooltip.add("Disabled");
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format("genericdust.resource." + this.name) + " " + I18n.format("genericdust.category." + this.getCategoryName());
    }

    public void preInit(FMLPreInitializationEvent event){
        if(event.getSide().isClient()){
            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(new ResourceLocation(GenericDust.MODID, this.getCategoryName()), "inventory"));
        }
    }

    public void init(FMLInitializationEvent event){
        if(event.getSide().isClient()){
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == getColorLayer() ? color : -1, this);
        }
    }

    public void postInit(FMLPostInitializationEvent event){}

    public abstract String getCategoryName();

    public String getOreDictName(){
        return this.getCategoryName() + StringUtils.capitalize(this.name);
    }

    public int getColorLayer(){
        return 0;
    }

    public ItemStack getOre(){
        return get("ore");
    }

    public ItemStack getIngot(){
        ItemStack ingot = get("ingot");
        if(ingot != null){
            return ingot;
        }
        return get("gem");
    }

    public ItemStack get(String category){
        return getDirect(category + StringUtils.capitalize(this.name));
    }

    public ItemStack getDirect(String name){
        for(String entry : OreDictionary.getOreNames()){
            if(name.equalsIgnoreCase(entry)){
                List<ItemStack> stacks = OreDictionary.getOres(entry);
                if(!stacks.isEmpty()){
                    return stacks.get(0);
                }
            }
        }
        return null;
    }

}
