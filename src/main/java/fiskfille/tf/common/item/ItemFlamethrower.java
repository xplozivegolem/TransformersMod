package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.entity.EntityFlamethrowerFire;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.helper.TFHelper;

public class ItemFlamethrower extends ItemSword
{
	public ItemFlamethrower(ToolMaterial material)
	{
		super(material);
		this.setCreativeTab(TransformersMod.tabTransformers);
		this.setMaxDamage(1500);
	}

	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int time)
	{
		if (TFHelper.isPlayerCloudtrap(player) && !world.isRemote && (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode))
		{
			stack.damageItem(5, player);

			if (!player.capabilities.isCreativeMode)
			{
				player.inventory.consumeInventoryItem(TFItems.energonCrystalPiece);
			}
		}
	}

	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		int duration = this.getMaxItemUseDuration(stack) - count;

		if (duration < 100)
		{
			if (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode)
			{			
				World world = player.worldObj;
				
				if (duration % 5 == 0)
				{
					Vec3 backCoords = TFMotionManager.getFrontCoords(player, -0.3F, true);
					player.motionX = (backCoords.xCoord - player.posX);
					player.motionZ = (backCoords.zCoord - player.posZ);

					world.playAuxSFX(1009, (int)player.posX, (int)player.posY, (int)player.posZ, 0);
				}

				if (!world.isRemote)
				{
					EntityFlamethrowerFire entity = new EntityFlamethrowerFire(world, player);
					world.spawnEntityInWorld(entity);
				}


				//				float f = 1.0F;
				//	            float f11 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
				//				float f21 = (player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f) + 90;
				//				double d01 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
				//				double d11 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
				//				double d21 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
				//				Vec3 vec32 = Vec3.createVectorHelper(d01, d11, d21);
				//				float f31 = MathHelper.cos(-f21 * 0.017453292F - (float)Math.PI);
				//				float f41 = MathHelper.sin(-f21 * 0.017453292F - (float)Math.PI);
				//				float f51 = -MathHelper.cos(-f11 * 0.017453292F);
				//				float f61 = MathHelper.sin(-f11 * 0.017453292F);
				//				float f71 = f41 * f51;
				//				float f81 = f31 * f51;
				//				double d31 = 0.5D;
				//				Vec3 vec33 = vec32.addVector(f71 * d31, f61 * d31, f81 * d31);
				//				
				//				for (int i = 0; i < 7; ++i)
				//				{
				//		            float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
				//					float f2 = (player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f);
				//					double d0 = vec33.xCoord * (double)f;
				//					double d1 = vec33.yCoord * (double)f;
				//					double d2 = vec33.zCoord * (double)f;
				//					Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
				//					float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
				//					float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
				//					float f5 = -MathHelper.cos(-f1 * 0.017453292F);
				//					float f6 = MathHelper.sin(-f1 * 0.017453292F);
				//					float f7 = f4 * f5;
				//					float f8 = f3 * f5;
				//					double d3 = i + 1;
				//					Vec3 hurtVec = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
				//					
				//					for (int i1 = 0; i1 < 5; ++i1)
				//					{
				//						Random rand = new Random();
				//						Block block = player.worldObj.getBlock((int)hurtVec.xCoord, (int)hurtVec.yCoord, (int)hurtVec.zCoord);
				////						player.worldObj.spawnParticle("flame", hurtVec.xCoord + rand.nextFloat() - 0.5F, hurtVec.yCoord + rand.nextFloat() - 1.0F, hurtVec.zCoord + rand.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
				//						
				//						if (!player.worldObj.isRemote)
				//						{
				//							if (block == Blocks.air && player.worldObj.getBlock((int)hurtVec.xCoord, (int)hurtVec.yCoord - 1, (int)hurtVec.zCoord).isOpaqueCube())
				//							{
				//								player.worldObj.setBlock((int)hurtVec.xCoord, (int)hurtVec.yCoord, (int)hurtVec.zCoord, Blocks.fire);
				//							}
				//						}
				//						else
				//						{
				//							TFParticles.spawnParticle(TFParticleType.FLAMETHROWER_FLAME, hurtVec.xCoord, hurtVec.yCoord, hurtVec.zCoord, rand.nextFloat() / 5, rand.nextFloat() / 5, rand.nextFloat() / 5);
				//						}
				//					}
				//					
				//					List<Entity> entities = getEntitiesNear(player.worldObj,  hurtVec.xCoord, hurtVec.yCoord, hurtVec.zCoord, 1.0F);
				//					
				//					for (Entity entity : entities)
				//					{
				//						if (!entity.getUniqueID().equals(player.getUniqueID()))
				//						{										
				//							if (entity instanceof EntityLivingBase)
				//							{
				//								((EntityLivingBase)entity).setFire(20);
				//								((EntityLivingBase)entity).attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
				//							}
				//						}
				//					}
				//				}
			}
		}
	}

	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
	{
		return stack;
	}

	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.bow;
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (TFHelper.isPlayerCloudtrap(player) && (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode))
		{
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		}

		return stack;
	}

	public List<Entity> getEntitiesNear(World world, double x, double y, double z, float par4)
	{
		List<Entity> list = world.selectEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x - par4, y - par4, z - par4, x + par4, y + par4, z + par4), IEntitySelector.selectAnything);

		return list;
	}

	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(TransformersMod.modid + ":" + iconString);
	}
}