package de.canitzp.genericdust;

import de.canitzp.genericdust.item.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author canitzp
 */
public class OreInspection {

    private static final HashMap<String, Integer> oreColor = new HashMap<String, Integer>(){{
        put("coal", 0x302E32);
        put("iron", 0xA34833);
        put("gold", 0xE5BD4C);
        put("lapis", 0x566FCF);
        put("diamond", 0x6797A1);
        put("emerald", 0x4Ca784);
        put("quartz", 0xC2CDDF);
        put("copper", 0xA7573D);
        put("tin", 0xCDC4B3);
        put("lead", 0x737172);
        put("silver", 0xC2C2C2);
        put("uranium", 0x6A8D2F);
        put("quartzblack", 0x000000);
    }};

    public static List<String> findAllOres(){
        List<String> list = new ArrayList<>();
        for(String oreName : OreDictionary.getOreNames()){
            if(oreName.startsWith("ore")){
                list.add(oreName);
            }
        }
        return list;
    }

    public static boolean existsOre(String name){
        return !OreDictionary.getOres("ore" + StringUtils.capitalize(name)).isEmpty();
    }

    public static boolean existsIngotForOre(String oreName){
        return !OreDictionary.getOres(oreName.replace("ore", "ingot")).isEmpty() || !OreDictionary.getOres(oreName.replace("ore", "gem")).isEmpty();
    }

    public static void createDusts(){
        for(String oreName : oreColor.keySet()){
            String name = oreName.replace("ore", "").toLowerCase(Locale.ENGLISH);
            if (oreColor.containsKey(name)){
                int color = oreColor.get(name);
                GameRegistry.register(new ItemDust(name, color));
                GameRegistry.register(new ItemCrushed(name, color));
                if(!Objects.equals(name, "gold")){
                    GameRegistry.register(new ItemNugget(name, color));
                }
                GameRegistry.register(new ItemPurified(name, color));
                GameRegistry.register(new ItemPile(name, color));
            }
        }
    }

}
