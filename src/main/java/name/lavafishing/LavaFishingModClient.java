package name.lavafishing;

import name.lavafishing.client.FishingBobberEntityRendererClient;
import name.lavafishing.registry.LavafishingItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class LavaFishingModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        EntityRendererRegistry.register(EntityType.FISHING_BOBBER, FishingBobberEntityRendererClient::new);
        registerFishingRodPredicates(LavafishingItems.OBSIDIAN_FISHING_ROD);
    }

    public void registerFishingRodPredicates(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("cast"), (itemStack, clientWorld, livingEntity, i) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                boolean bl = livingEntity.getMainHandStack() == itemStack;
                boolean bl2 = livingEntity.getOffHandStack() == itemStack;
                if (livingEntity.getMainHandStack().getItem() instanceof FishingRodItem) {
                    bl2 = false;
                }
                return (bl || bl2) && livingEntity instanceof PlayerEntity && ((PlayerEntity)livingEntity).fishHook != null ? 1.0F : 0.0F;
            }
        });
    }
}
