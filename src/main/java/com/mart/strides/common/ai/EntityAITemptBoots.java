package com.mart.strides.common.ai;

import com.mart.strides.common.registration.ModItems;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAITemptBoots extends EntityAIBase {
    /**
     * The entity using this AI that is tempted by the player.
     */
    private final EntityCreature temptedEntity;
    private final double speed;
    /**
     * X position of player tempting this mob
     */
    private double targetX;
    /**
     * Y position of player tempting this mob
     */
    private double targetY;
    /**
     * Z position of player tempting this mob
     */
    private double targetZ;
    /**
     * Tempting player's pitch
     */
    private double pitch;
    /**
     * Tempting player's yaw
     */
    private double yaw;
    /**
     * The player that is tempting the entity that is using this AI.
     */
    private EntityPlayer temptingPlayer;

    /**
     * True if this EntityAITempt task is running
     */
    private boolean isRunning;
    /**
     * Whether the entity using this AI will be scared by the tempter's sudden movement.
     */
    private final boolean scaredByPlayerMovement;

    public EntityAITemptBoots(EntityCreature temptedEntityIn, double speedIn, boolean scaredByPlayerMovementIn) {
        this.temptedEntity = temptedEntityIn;
        this.speed = speedIn;
        this.scaredByPlayerMovement = scaredByPlayerMovementIn;
        this.setMutexBits(3);

        if (!(temptedEntityIn.getNavigator() instanceof PathNavigateGround)) {
            throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        this.temptingPlayer = this.temptedEntity.world.getClosestPlayerToEntity(this.temptedEntity, 10.0D);

        if (this.temptingPlayer == null) {
            return false;
        } else {
            Iterable<ItemStack> armor = this.temptingPlayer.getArmorInventoryList();
            for (ItemStack arm : armor) {
                if (arm.getItem() == ModItems.ANIMAL_STRIDES) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting() {
        if (this.scaredByPlayerMovement) {
            if (this.temptedEntity.getDistanceSq(this.temptingPlayer) < 36.0D) {
                if (this.temptingPlayer.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002D) {
                    return false;
                }

                if (Math.abs((double) this.temptingPlayer.rotationPitch - this.pitch) > 5.0D || Math.abs((double) this.temptingPlayer.rotationYaw - this.yaw) > 5.0D) {
                    return false;
                }
            } else {
                this.targetX = this.temptingPlayer.posX;
                this.targetY = this.temptingPlayer.posY;
                this.targetZ = this.temptingPlayer.posZ;
            }

            this.pitch = (double) this.temptingPlayer.rotationPitch;
            this.yaw = (double) this.temptingPlayer.rotationYaw;
        }

        return this.shouldExecute();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.targetX = this.temptingPlayer.posX;
        this.targetY = this.temptingPlayer.posY;
        this.targetZ = this.temptingPlayer.posZ;
        this.isRunning = true;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    @Override
    public void resetTask() {
        this.temptingPlayer = null;
        this.temptedEntity.getNavigator().clearPath();
        this.isRunning = false;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void updateTask() {
        this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, (float) (this.temptedEntity.getHorizontalFaceSpeed() + 20), (float) this.temptedEntity.getVerticalFaceSpeed());

        if (this.temptedEntity.getDistanceSq(this.temptingPlayer) < 6.25D) {
            this.temptedEntity.getNavigator().clearPath();
        } else {
            this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.speed);
        }
    }

    /**
     * @see #isRunning
     */
    public boolean isRunning() {
        return this.isRunning;
    }
}
