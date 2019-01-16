package artifacts;

import artifacts.common.item.*;
import baubles.api.BaubleType;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    public static BaubleBase baubleBalloon = new BaublePotionEffect("bauble_balloon", BaubleType.RING, MobEffects.JUMP_BOOST, 1).setEquipSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.5F);
    public static BaubleBase baubleSnorkel = new BaubleSnorkel();
    public static BaubleBase baubleLightningAmulet = new BaubleAmulet("bauble_lightning_amulet", new ResourceLocation(Artifacts.MODID,"textures/entity/lightning_amulet.png"));
    public static BaubleBase baubleFireAmulet = new BaubleAmulet("bauble_fire_amulet", new ResourceLocation(Artifacts.MODID,"textures/entity/fire_amulet.png"));
    public static BaubleBase baubleThornsAmulet = new BaubleAmulet("bauble_thorns_amulet", new ResourceLocation(Artifacts.MODID,"textures/entity/thorns_amulet.png"));
    public static BaubleBase baubleHorseshoe = new BaubleHorseshoe();
    public static BaubleBase baubleShield = new BaubleShield();
    public static BaubleBase baubleObsidianSkull = new BaubleObsidianSkull();


    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.registerAll(
                baubleBalloon,
                baubleObsidianSkull,
                baubleSnorkel,
                baubleLightningAmulet,
                baubleFireAmulet,
                baubleThornsAmulet,
                baubleHorseshoe,
                baubleShield
        );
    }

    public static void registerModels() {
        Artifacts.proxy.registerItemRenderer(baubleBalloon, 0, baubleBalloon.name);
        Artifacts.proxy.registerItemRenderer(baubleObsidianSkull, 0, baubleObsidianSkull.name);
        Artifacts.proxy.registerItemRenderer(baubleSnorkel, 0, baubleSnorkel.name);
        Artifacts.proxy.registerItemRenderer(baubleLightningAmulet, 0, baubleLightningAmulet.name);
        Artifacts.proxy.registerItemRenderer(baubleFireAmulet, 0, baubleFireAmulet.name);
        Artifacts.proxy.registerItemRenderer(baubleThornsAmulet, 0, baubleThornsAmulet.name);
        Artifacts.proxy.registerItemRenderer(baubleHorseshoe, 0, baubleHorseshoe.name);
        Artifacts.proxy.registerItemRenderer(baubleShield, 0, baubleShield.name);
    }
}
