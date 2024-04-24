package name.lavafishing.item;

import name.lavafishing.LavaFishing;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.ArrayList;
import java.util.Random;

public class ItemObsidianFishingRod extends FishingRodItem {
    public ItemObsidianFishingRod(Settings settings){
        super(settings);
    }

//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//        ItemStack itemStack = user.getStackInHand(hand);
//        int i;
//        if (user.fishHook != null) {
//            if (!world.isClient) {
//                i = user.fishHook.use(itemStack);
//                itemStack.damage(i, user, (p) -> {
//                    p.sendToolBreakStatus(hand);
//                });
//            }
//
//            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
//            user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
//        } else {
//            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
//            if (!world.isClient) {
//                i = EnchantmentHelper.getLure(itemStack);
//                int j = EnchantmentHelper.getLuckOfTheSea(itemStack);
//                world.spawnEntity(new FishingBobberEntity(user, world, j, i));
//            }
//
//            user.incrementStat(Stats.USED.getOrCreateStat(this));
//            user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
//        }
//
//        return TypedActionResult.success(itemStack, world.isClient());
//    }
//
//

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack heldStack = user.getStackInHand(hand);


        if(user.fishHook != null) {
            // Retrieve fishing bobber and damage Fishing Rod
            if(!world.isClient) {
                int damage = user.fishHook.use(heldStack);
                heldStack.damage(damage, user, player -> player.sendToolBreakStatus(hand));
            }

            world.playSound(null, user.getX(), user.getY(), user.getZ(),SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        } else {
            world.playSound(null, user.getX(), user.getY(), user.getZ(),SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

            // Summon new fishing bobber
            if(!world.isClient) {
                int bonusExperience = 0;

                // Calculate lure and luck
                int lure = Math.min((EnchantmentHelper.getLure(heldStack)),5);
                int lots = EnchantmentHelper.getLuckOfTheSea(heldStack);

                // Summon bobber with stats
                FishingBobberEntity bobber = new FishingBobberEntity(user, world, lots, lure);
                world.spawnEntity(bobber);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        return TypedActionResult.success(heldStack, world.isClient());
    }


    @Override
    public int getEnchantability() {
        return 1;
    }


    public static class Builder {

        private Item.Settings settings = new Item.Settings().maxDamage(100);
        public Builder() {

        }

        public Builder withSettings(Item.Settings settings) {
            this.settings = settings;
            return this;
        }

        public ItemObsidianFishingRod build() {
            return new ItemObsidianFishingRod(settings);
        }
    }
}
