package name.lavafishing;

import name.lavafishing.effect.EffectBlessed;
import name.lavafishing.effect.EffectEndlessFlame;
import name.lavafishing.effect.EffectLavaWalker;
import name.lavafishing.item.ItemObsidianFishingRod;
import name.lavafishing.item.fishs.ItemAgniFish;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LavaFishing implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("lavafishing");
	//注册火神鱼物品
	public static final ItemAgniFish AGNI_FISH =
			Registry.register(Registries.ITEM, new Identifier("lavafishing", "agni_fish"),
					new ItemAgniFish(new FabricItemSettings().rarity(Rarity.RARE).food(new FoodComponent.Builder()
							.meat()
							.alwaysEdible()
							.hunger(1)
							.saturationModifier(0.5f)
							.build())));

	//添加祝福效果
	public static final EffectBlessed EFFECT_BLESSED =
			Registry.register(Registries.STATUS_EFFECT, new Identifier("lavafishing", "effect_blessed"),
			new EffectBlessed());

	//无尽之火
	public static final EffectEndlessFlame EFFECT_ENDLESS_FLAME =
			Registry.register(Registries.STATUS_EFFECT, new Identifier("lavafishing", "effect_endless_flame"),
					new EffectEndlessFlame());

	//岩浆行者
	public static final EffectLavaWalker EFFECT_LAVA_WALKER =
			Registry.register(Registries.STATUS_EFFECT, new Identifier("lavafishing", "effect_lava_walker"),
					new EffectLavaWalker());

	//岩浆钓竿
	public static final ItemObsidianFishingRod OBSIDIAN_FISHING_ROD =
			Registry.register(Registries.ITEM, new Identifier("lavafishing", "obsidian_fishing_rod"),
					new ItemObsidianFishingRod(new FabricItemSettings()));


	//添加物品组
	public static final ItemGroup LAVAFISHING_GROUP =
			Registry.register(Registries.ITEM_GROUP, new Identifier("lavafishing", "lavafishing_group"),
					FabricItemGroup.builder()
							.icon(()->new ItemStack(OBSIDIAN_FISHING_ROD))
							.displayName(Text.translatable("itemGroup.lavafishing.lavafishing_group"))
							.entries((content, entries) -> {
								entries.add(AGNI_FISH);
								entries.add(OBSIDIAN_FISHING_ROD);
							})
							.build());

	@Override
	public void onInitialize() {
		LOGGER.info("Hello lavafishing world!");
	}
}