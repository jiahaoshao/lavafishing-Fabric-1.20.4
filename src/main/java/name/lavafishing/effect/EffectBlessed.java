package name.lavafishing.effect;


import jdk.jfr.Category;
import jdk.jfr.Event;

import name.lavafishing.LavaFishing;
import net.minecraft.client.particle.FireworksSparkParticle;
import net.minecraft.command.argument.ArgumentTypes;
import net.minecraft.command.argument.ParticleEffectArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.WorldEventS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.ParticleCommand;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.logging.ILogger;

import java.util.logging.Logger;

import static name.lavafishing.LavaFishing.*;

public class EffectBlessed extends StatusEffect {
    public EffectBlessed(){
        super(StatusEffectCategory.NEUTRAL, 0xCC3300);//药水效果是有益的还是有害的, 显示的颜色
    }

    // 这个方法在每个 tick 都会调用，以检查是否应应用药水效果
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // 在我们的例子中，为了确保每一 tick 药水效果都会被应用，我们只要这个方法返回 true 就行了。
        return duration % 10 == 0;
    }

    // 给自己身上附加无尽之火效果，但是不掉血，同时攻击能给实体挂上无尽之火效果
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        //下面实现无尽之火效果
        entity.heal(2.0f);
        entity.setFireTicks(20);
        entity.setOnFire(true);

        if (entity.isTouchingWaterOrRain()) {
            entity.setFireTicks(20);
            entity.setOnFire(true);
            entity.damage(entity.getDamageSources().onFire(), 0.5f);
        }
        entity.damage(entity.getDamageSources().onFire(), 0.5f);

        //下面实现攻击后给被攻击者挂上无尽之火效果
        LivingEntity target = entity.getAttacking();//被攻击者
        if(target != null && target != entity && entity.hasStatusEffect(EFFECT_BLESSED))
        {
            //LavaFishing.LOGGER.info(target.toString());
            spawnHitParticle(target.getWorld(), target.getPos());
            LavaFishing.LOGGER.info(String.valueOf(target.getHealth()));
            target.addStatusEffect(new StatusEffectInstance(EFFECT_ENDLESS_FLAME, 20 * 60), entity);
            entity.onAttacking(entity);
        }

        super.applyUpdateEffect(entity,amplifier);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        entity.onAttacking(entity);
        //LavaFishing.LOGGER.info(entity.getAttacking().toString());
        super.onApplied(entity, amplifier);
    }

    private static void spawnHitParticle(@NotNull World world, @NotNull Vec3d pos) {
        world.addParticle(ParticleTypes.LAVA, true, pos.x, pos.y + 1.3f,
                pos.z, 0.3, 0.3, 0.3);
        //world.addParticle(ParticleTypes.LAVA, true, 0.3, 0.3, 0.3, pos.x, pos.y + 1.3f, pos.z);
    }
}
