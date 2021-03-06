package fiskfille.tf.client.model.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.model.transformer.ModelChildBase;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.helper.TFModelHelper;

@SideOnly(Side.CLIENT)
public class ModelPlayerTF extends ModelChildBase.Biped
{
	public ModelPlayerTF()
	{
		super();
	}

	public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float f5)
	{
		TFModelHelper.modelBipedMain = this;
		
		this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, f5, entity);

		this.bipedHead.rotationPointY = TFModelHelper.headOffsetY;
		this.bipedHeadwear.rotationPointY = TFModelHelper.headOffsetY;
		this.bipedHead.rotationPointX = TFModelHelper.headOffsetX;
		this.bipedHeadwear.rotationPointX = TFModelHelper.headOffsetX;
		this.bipedHead.rotationPointZ = TFModelHelper.headOffsetZ;
		this.bipedHeadwear.rotationPointZ = TFModelHelper.headOffsetZ;
		
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;

			ItemStack helm = player.getCurrentArmor(3);
			boolean wearingTransformerHelm = helm != null ? helm.getItem() instanceof ItemTransformerArmor : false;
			ItemStack chest = player.getCurrentArmor(2);
			boolean wearingTransformerChest = chest != null ? chest.getItem() instanceof ItemTransformerArmor : false;
			ItemStack pants = player.getCurrentArmor(1);
			boolean wearingTransformerPants = pants != null ? pants.getItem() instanceof ItemTransformerArmor : false;

			if (this.isChild)
			{
				float f6 = 2.0F;
				GL11.glPushMatrix();
				GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
				GL11.glTranslatef(0.0F, 16.0F * f5, 0.0F);
				if (!wearingTransformerHelm) {this.bipedHead.render(f5);}
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
				GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
				if (!wearingTransformerChest) {this.bipedRightArm.render(f5);}
				if (!wearingTransformerChest) {this.bipedLeftArm.render(f5);}
				if (!wearingTransformerPants) {this.bipedRightLeg.render(f5);}
				if (!wearingTransformerPants) {this.bipedLeftLeg.render(f5);}
				if (!wearingTransformerHelm) {this.bipedHeadwear.render(f5);}
				GL11.glPopMatrix();
			}
			else
			{
				if (!wearingTransformerHelm) {this.bipedHead.render(f5);}
				if (!wearingTransformerChest) {this.bipedBody.render(f5);}
				if (!wearingTransformerChest) {this.bipedRightArm.render(f5);}
				if (!wearingTransformerChest) {this.bipedLeftArm.render(f5);}
				if (!wearingTransformerPants) {this.bipedRightLeg.render(f5);}
				if (!wearingTransformerPants) {this.bipedLeftLeg.render(f5);}
				if (!wearingTransformerHelm) {this.bipedHeadwear.render(f5);}
			}
		}

		TFModelHelper.headOffsetX = 0;
		TFModelHelper.headOffsetY = 0;
		TFModelHelper.headOffsetZ = 0;
	}
}