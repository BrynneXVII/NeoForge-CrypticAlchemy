package com.brynne.crypticalchemy.block.custom;

import com.brynne.crypticalchemy.block.CrypticAlchemyBlocks;
import com.brynne.crypticalchemy.item.CrypticAlchemyItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

public class MistGrassBush extends VegetationBlock implements BonemealableBlock {
    public static final int MAX_AGE = 2;
    public static final IntegerProperty AGE;
    private static final VoxelShape SHAPE_SEEDLING;
    private static final VoxelShape SHAPE_GROWING;

    public MistGrassBush(Properties properties) {
        super(properties);
        this.registerDefaultState((this.stateDefinition.any()).setValue(AGE, 0));
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        return new ItemStack(CrypticAlchemyItems.MIST_GRASS_SEED.get());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape;
        switch (state.getValue(AGE)) {
            case 0 -> shape = SHAPE_SEEDLING;
            case 2 -> shape = Shapes.block();
            default -> shape = SHAPE_GROWING;
        }

        return shape;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);
        if (age < MAX_AGE && level.getRawBrightness(pos.above(), 0) >= 9 && CommonHooks.canCropGrow(level, pos, state, random.nextInt(5) == 0)) {
            BlockState newState = state.setValue(AGE, age + 1);
            level.setBlock(pos, newState, 2);
            CommonHooks.fireCropGrowPost(level, pos, state);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
        }

    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.getMaxLocalRawBrightness(pos) <= 13 && random.nextDouble() <= 0.7) {
            double fireflyX = (double)pos.getX() + random.nextDouble() * (double)10.0F - (double)5.0F;
            double fireflyY = (double)pos.getY() + random.nextDouble() * (double)5.0F;
            double fireflyZ = (double)pos.getZ() + random.nextDouble() * (double)10.0F - (double)5.0F;
            level.addParticle(ParticleTypes.FIREFLY, fireflyX, fireflyY, fireflyZ, (double)0.0F, (double)0.0F, (double)0.0F);
        }

    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        return;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        int age = state.getValue(AGE);
        boolean isMaxAge = age == MAX_AGE;
        return (!isMaxAge && itemStack.is(Items.BONE_MEAL) ? InteractionResult.PASS : super.useItemOn(itemStack, state, level, pos, player, hand, hitResult));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int age = state.getValue(AGE);
        if (age > 0) {
            if (level instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)level;
                switch (age) {
                    case 1:
                        Block.popResource(level, pos, new ItemStack(CrypticAlchemyItems.MIST_GRASS.get(), 1));
                        break;
                    case 2:
                        Block.popResource(level, pos, new ItemStack(CrypticAlchemyItems.MIST_GRASS.get(), 2));
                        int count = Math.random() > 0.8 ? 1 : 0;
                        Block.popResource(level, pos, new ItemStack(CrypticAlchemyItems.MIST_GRASS_SEED.get(), count));
                        break;
                    default:
                        Block.popResource(level, pos, new ItemStack(CrypticAlchemyBlocks.MIST_GRASS_BLOCK.get().asItem(), 1));
                }
                serverLevel.playSound(null, pos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + serverLevel.getRandom().nextFloat() * 0.4F);
                BlockState newState = state.setValue(AGE, 0);
                serverLevel.setBlock(pos, newState, 2);
                serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, BonemealSource source) {
        return state.getValue(AGE) < MAX_AGE;
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
        return Math.random() > 0.3;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
        int newAge = Math.min(MAX_AGE, state.getValue(AGE) + 1);
        level.setBlock(pos, state.setValue(AGE, newAge), 2);
    }

    static {
        AGE = BlockStateProperties.AGE_2;
        SHAPE_SEEDLING = Block.column(6.0F, 0.0F, 4.0F);
        SHAPE_GROWING = Block.column(10.0F, 0.0F, 9.0F);
    }
}
