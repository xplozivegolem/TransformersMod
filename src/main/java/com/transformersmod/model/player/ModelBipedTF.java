package com.transformersmod.model.player;

import com.transformersmod.item.armor.ITransformerArmor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelBipedTF extends ModelBiped
{
	public ModelBipedTF()
	{
		this(0.0F);
	}

	public ModelBipedTF(float modelScale)
	{
		this(modelScale, 0.0F, 64, 32);
	}

	public ModelBipedTF(float scaleFactor, float p_i1149_2_, int textureWidth, int textureHeight)
	{
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scaleFactor);
		this.bipedHead.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
		this.bipedHeadwear = new ModelRenderer(this, 32, 0);
		this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scaleFactor + 0.5F);
		this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
		this.bipedBody = new ModelRenderer(this, 16, 16);
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scaleFactor);
		this.bipedBody.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
		this.bipedRightArm = new ModelRenderer(this, 40, 16);
		this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, scaleFactor);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + p_i1149_2_, 0.0F);
		this.bipedLeftArm = new ModelRenderer(this, 40, 16);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, scaleFactor);
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + p_i1149_2_, 0.0F);
		this.bipedRightLeg = new ModelRenderer(this, 0, 16);
		this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor);
		this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + p_i1149_2_, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor);
		this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + p_i1149_2_, 0.0F);
	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
	{
		this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, entity);

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;

			ItemStack helm = player.getCurrentArmor(3);
			boolean wearingTransformerHelm = helm != null ? helm.getItem() instanceof ITransformerArmor : false;
			ItemStack chest = player.getCurrentArmor(2);
			boolean wearingTransformerChest = chest != null ? chest.getItem() instanceof ITransformerArmor : false;
			ItemStack pants = player.getCurrentArmor(1);
			boolean wearingTransformerPants = pants != null ? pants.getItem() instanceof ITransformerArmor : false;

			if (this.isChild)
			{
				float f6 = 2.0F;
				GL11.glPushMatrix();
				GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
				GL11.glTranslatef(0.0F, 16.0F * p_78088_7_, 0.0F);
				
				if (!wearingTransformerHelm)
				{
					this.bipedHead.render(p_78088_7_);
				}

				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
				GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
				if (!wearingTransformerChest)
				{
					this.bipedBody.render(p_78088_7_);
					this.bipedRightArm.render(p_78088_7_);
					this.bipedLeftArm.render(p_78088_7_);
				}

				if (!wearingTransformerPants)
				{
					this.bipedRightLeg.render(p_78088_7_);
					this.bipedLeftLeg.render(p_78088_7_);
				}

				if (!wearingTransformerHelm)
				{
					this.bipedHeadwear.render(p_78088_7_);
				}
				GL11.glPopMatrix();
			}
			else
			{
				if (!wearingTransformerHelm)
				{
					this.bipedHead.render(p_78088_7_);
				}

				if (!wearingTransformerChest)
				{
					this.bipedBody.render(p_78088_7_);
					this.bipedRightArm.render(p_78088_7_);
					this.bipedLeftArm.render(p_78088_7_);
				}

				if (!wearingTransformerPants)
				{
					this.bipedRightLeg.render(p_78088_7_);
					this.bipedLeftLeg.render(p_78088_7_);
				}

				if (!wearingTransformerHelm)
				{
					this.bipedHeadwear.render(p_78088_7_);
				}
			}
		}
		else
		{
			if (this.isChild)
			{
				float f6 = 2.0F;
				GL11.glPushMatrix();
				GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
				GL11.glTranslatef(0.0F, 16.0F * p_78088_7_, 0.0F);
				this.bipedHead.render(p_78088_7_);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
				GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
				this.bipedBody.render(p_78088_7_);
				this.bipedRightArm.render(p_78088_7_);
				this.bipedLeftArm.render(p_78088_7_);
				this.bipedRightLeg.render(p_78088_7_);
				this.bipedLeftLeg.render(p_78088_7_);
				this.bipedHeadwear.render(p_78088_7_);
				GL11.glPopMatrix();
			}
			else
			{
				this.bipedHead.render(p_78088_7_);
				this.bipedBody.render(p_78088_7_);
				this.bipedRightArm.render(p_78088_7_);
				this.bipedLeftArm.render(p_78088_7_);
				this.bipedRightLeg.render(p_78088_7_);
				this.bipedLeftLeg.render(p_78088_7_);
				this.bipedHeadwear.render(p_78088_7_);
			}
		}
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
	 * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
	 * "far" arms and legs can swing at most.
	 */
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
	{
        this.bipedHead.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.bipedHead.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.bipedRightArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 2.0F * p_78087_2_ * 0.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 2.0F * p_78087_2_ * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;

        if (this.isRiding)
        {
            this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
        }

        if (this.heldItemLeft != 0)
        {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedRightArm.rotateAngleZ = 0.0F;

        switch (this.heldItemRight)
        {
            case 0:
            case 2:
            default:
                break;
            case 1:
                this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
                break;
            case 3:
                this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
                this.bipedRightArm.rotateAngleY = -0.5235988F;
        }

        this.bipedLeftArm.rotateAngleY = 0.0F;
        float f6;
        float f7;

        if (this.swingProgress > -9990.0F)
        {
            f6 = this.swingProgress;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f6 = 1.0F - this.swingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * (float)Math.PI);
            float f8 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            this.bipedRightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
        }

        if (this.isSneak)
        {
            this.bipedBody.rotateAngleX = 0.5F;
            this.bipedRightArm.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX += 0.4F;
            this.bipedRightLeg.rotationPointZ = 4.0F;
            this.bipedLeftLeg.rotationPointZ = 4.0F;
            this.bipedRightLeg.rotationPointY = 9.0F;
            this.bipedLeftLeg.rotationPointY = 9.0F;
            this.bipedHead.rotationPointY = 1.0F;
        }
        else
        {
            this.bipedBody.rotateAngleX = 0.0F;
            this.bipedRightLeg.rotationPointZ = 0.1F;
            this.bipedLeftLeg.rotationPointZ = 0.1F;
            this.bipedRightLeg.rotationPointY = 12.0F;
            this.bipedLeftLeg.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
        }

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;

        if (this.aimedBow)
        {
            f6 = 0.0F;
            f7 = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
            this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        }

        copyModelAngles(this.bipedHead, this.bipedHeadwear);
	}
}