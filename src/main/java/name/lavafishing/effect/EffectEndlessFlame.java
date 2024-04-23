package name.lavafishing.effect;

import name.lavafishing.LavaFishing;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EffectEndlessFlame extends StatusEffect {
    public EffectEndlessFlame() {
        super(StatusEffectCategory.HARMFUL, 0xCC3300);
    }


    // 这个方法在每个 tick 都会调用，以检查是否应应用药水效果
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // 在我们的例子中，为了确保每一 tick 药水效果都会被应用，我们只要这个方法返回 true 就行了。
        return duration % 10 == 0;
    }

    //被有祝福效果的生物空手攻击后获得。（因为对僵尸猪灵和烈焰人都有效未实现，所以改为不需要空手也可以）
    //使生物着火，无法被水、细雪等熄灭，会持续受伤。
    //甚至对僵尸猪灵和烈焰人都有效。（未实现）
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
//        LavaFishing.LOGGER.info(entity.toString());
//        LavaFishing.LOGGER.info(String.valueOf(entity.getHealth()));
//        if(entity.isFireImmune() && entity instanceof PlayerEntity)
//        {
//            LavaFishing.LOGGER.info(String.valueOf(entity.isFireImmune()));
//            entity.damage(entity.getDamageSources().inFire(), 0.5f);
//        }
            entity.setFireTicks(20);
            entity.setOnFire(true);

        if (entity.isTouchingWaterOrRain()) {
            entity.setFireTicks(20);
            entity.setOnFire(true);
            entity.damage(entity.getDamageSources().onFire(), 0.5f);
        }
        entity.damage(entity.getDamageSources().onFire(), 0.5f);
        super.applyUpdateEffect(entity,amplifier);
    }
}
