package fiskfille.tf.common.entity;

import net.minecraft.entity.Entity;
import cpw.mods.fml.common.registry.EntityRegistry;
import fiskfille.tf.TransformersMod;

public class TFEntities 
{
	public static void registerEntities()
	{
		registerEntity(EntityTankShell.class, "tf_tank_shell", 20, 10, true);
		registerEntity(EntityMissile.class, "tf_missile", 20, 10, true);
		registerEntity(EntityMiniMissile.class, "tf_mini_missile", 20, 10, true);
		registerEntity(EntityTransformiumSeed.class, "tf_transformium_seed", 20, 10, true);
		registerEntity(EntityFlamethrowerFire.class, "tf_flamethrower_fire", 20, 10, true);
	}
	
	private static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange, int updateFrequency, boolean sendVelocityUpdates)
	{
		int id = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, name, id);
		EntityRegistry.registerModEntity(entityClass, name, id, TransformersMod.instance, trackingRange, updateFrequency, sendVelocityUpdates);
	}
}
