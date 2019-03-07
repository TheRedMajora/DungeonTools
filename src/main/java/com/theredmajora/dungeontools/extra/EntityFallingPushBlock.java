package com.theredmajora.dungeontools.extra;

import java.util.List;

import com.google.common.collect.Lists;
import com.theredmajora.dungeontools.DungeonItems;
import com.theredmajora.dungeontools.blocks.BlockPush;
import com.theredmajora.dungeontools.tileentity.TileEntityPushBlock;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFallingPushBlock extends Entity
{
	public boolean heavy;
    public int fallTime;
    private int fallHurtMax = 40;
    private float fallHurtAmount = 2.0F;
    protected static final DataParameter<BlockPos> ORIGIN = EntityDataManager.<BlockPos>createKey(EntityFallingPushBlock.class, DataSerializers.BLOCK_POS);
    public BlockPos pushReturnPos;

    public EntityFallingPushBlock(World worldIn)
    {
        super(worldIn);
    }

    public EntityFallingPushBlock(World worldIn, double x, double y, double z, boolean heavy)
    {
        super(worldIn);
        this.heavy = heavy;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.setPosition(x, y + (double)((1.0F - this.height) / 2.0F), z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.setOrigin(new BlockPos(this));
    }

    @Override
    public boolean canBeAttackedWithItem()
    {
        return false;
    }

    public void setOrigin(BlockPos p_184530_1_)
    {
        this.dataManager.set(ORIGIN, p_184530_1_);
    }

    @SideOnly(Side.CLIENT)
    public BlockPos getOrigin()
    {
        return (BlockPos)this.dataManager.get(ORIGIN);
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    protected void entityInit()
    {
        this.dataManager.register(ORIGIN, BlockPos.ORIGIN);
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    @Override
    public void onUpdate()
    {this.prevPosX = this.posX;
    this.prevPosY = this.posY;
    this.prevPosZ = this.posZ;

    if (this.fallTime++ == 0)
    {
        BlockPos blockpos = new BlockPos(this);
        
        if (this.world.getBlockState(blockpos).getBlock() instanceof BlockPush)
        {
        	pushReturnPos = ((TileEntityPushBlock) world.getTileEntity(blockpos)).getReturnPos();
			this.world.removeTileEntity(blockpos);
            this.world.setBlockToAir(blockpos);
        }
        else if (!this.world.isRemote)
        {
            this.setDead();
            return;
        }
    }

    if (!this.hasNoGravity())
    {
        this.motionY -= 0.03999999910593033D;
    }

    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

    if (!this.world.isRemote)
    {
        BlockPos blockpos1 = new BlockPos(this);

        if (!this.onGround)
        {
            if (this.fallTime > 100 && !this.world.isRemote && (blockpos1.getY() < 1 || blockpos1.getY() > 256) || this.fallTime > 600)
            {
                this.setDead();
            }
        }
        else
        {
            IBlockState iblockstate = this.world.getBlockState(blockpos1);

            if (this.world.isAirBlock(new BlockPos(this.posX, this.posY - 0.009999999776482582D, this.posZ))) //Forge: Don't indent below.
            if (BlockFalling.canFallThrough(this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.009999999776482582D, this.posZ))))
            {
                this.onGround = false;
                return;
            }

            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;

            if (iblockstate.getBlock() != Blocks.PISTON_EXTENSION)
            {
                this.setDead();

                if (this.world.mayPlace(DungeonItems.push_block, blockpos1, true, EnumFacing.UP, (Entity)null) && (!BlockFalling.canFallThrough(this.world.getBlockState(blockpos1.down()))) && this.world.setBlockState(blockpos1, getState(), 11))
                {
        			((TileEntityPushBlock) world.getTileEntity(blockpos1)).setReturnPos(pushReturnPos);
                }
            }
        }
    }

    this.motionX *= 0.9800000190734863D;
    this.motionY *= 0.9800000190734863D;
    this.motionZ *= 0.9800000190734863D;
    }

    public IBlockState getState() {
		return heavy ? DungeonItems.push_block_heavy.getDefaultState() : DungeonItems.push_block.getDefaultState();
	}

	public void fall(float distance, float damageMultiplier)
    {
    	int i = MathHelper.ceil(distance - 1.0F);

        if (i > 0)
        {
            List<Entity> list = Lists.newArrayList(this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()));
            DamageSource damagesource = DamageSource.FALLING_BLOCK;

            for (Entity entity : list)
            { entity.attackEntityFrom(damagesource, (float)Math.min(MathHelper.floor((float)i * this.fallHurtAmount), this.fallHurtMax)); }
        }
    }

    protected void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setBoolean("Heavy", this.heavy);
        compound.setInteger("Time", this.fallTime);
        compound.setFloat("FallHurtAmount", this.fallHurtAmount);
        compound.setInteger("FallHurtMax", this.fallHurtMax);
		compound.setTag("pushReturnCoords", NBTUtil.createPosTag(pushReturnPos));
    }
    
	protected void readEntityFromNBT(NBTTagCompound compound)
    { 
        this.heavy = compound.getBoolean("Heavy");
        this.fallTime = compound.getInteger("Time");
        this.fallHurtAmount = compound.getFloat("FallHurtAmount");
        this.fallHurtMax = compound.getInteger("FallHurtMax");
        this.pushReturnPos = NBTUtil.getPosFromTag(compound.getCompoundTag("pushReturnCoords"));
    }

    @SideOnly(Side.CLIENT)
    public World getWorldObj()
    {
        return this.world;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canRenderOnFire()
    {
        return false;
    }

    @Override
    public boolean ignoreItemEntityData()
    {
        return true;
    }
}