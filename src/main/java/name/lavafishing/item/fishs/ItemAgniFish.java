package name.lavafishing.item.fishs;

import name.lavafishing.LavaFishing;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static name.lavafishing.LavaFishing.EFFECT_BLESSED;

public class ItemAgniFish extends Item {
    public ItemAgniFish(Settings settings) {
        super(settings);
    }


    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(user instanceof PlayerEntity player)
        {
            player.addStatusEffect(new StatusEffectInstance(EFFECT_BLESSED, 20 * 30, 1));
            //player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE));
        }
        return super.finishUsing(stack, world, user);
    }
}
