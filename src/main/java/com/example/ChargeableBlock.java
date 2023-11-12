package com.example;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ChargeableBlock extends Block {
    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");
 
    // The block instance. You can place it anywhere. Make the class is initialized.
    public static final ChargeableBlock CHARGEABLE_BLOCK = new ChargeableBlock(FabricBlockSettings.copyOf(Blocks.STONE));

    public ChargeableBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(CHARGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
    }
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 1);
        world.setBlockState(pos, state.with(CHARGED, true));
        return ActionResult.SUCCESS;
    }
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        while (world.getBlockState(pos).get(CHARGED)){
            // Summoning the Lighting Bolt at the block
            
            CreeperEntity creeperEntity = (CreeperEntity) EntityType.CREEPER.create(world);
            creeperEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos));
            world.spawnEntity(creeperEntity);
        }
 
        world.setBlockState(pos, state.with(CHARGED, false));
        super.onSteppedOn(world, pos, state, entity);
    }
}
