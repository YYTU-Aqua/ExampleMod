package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ExampleMod implements ModInitializer {

	public static final CustomItem CUSTOM_ITEM = new CustomItem(new FabricItemSettings());
	public static final Block EXAMPLE_BLOCK  = new Block(FabricBlockSettings.create().strength(4.0f));
	public static ToolItem CUSO_TOOL = new PickaxeItem(CusoTool.INSTANCE, 1, -2.8F, new FabricItemSettings());

    public static final VerticalSlabBlock POLISHED_ANDESITE_VERTICAL_SLAB = Registry.register(
        Registries.BLOCK,
        new Identifier("tutorial", "polished_andesite_vertical_slab"),
        new VerticalSlabBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
	
	private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
	.icon(() -> new ItemStack(CUSTOM_ITEM))
	.displayName(Text.translatable("itemGroup.tutorial.test_group"))
        .entries((context, entries) -> {
		entries.add(CUSTOM_ITEM);
		entries.add(EXAMPLE_BLOCK);
		entries.add(CUSO_TOOL);
		entries.add(ChargeableBlock.CHARGEABLE_BLOCK);
		entries.add(POLISHED_ANDESITE_VERTICAL_SLAB);
	})
	.build();

	@Override
	public void onInitialize() {
		//CREATIVE_TAB
		Registry.register(Registries.ITEM_GROUP, new Identifier("tutorial", "test_group"), ITEM_GROUP);
		//ITEM
		Registry.register(Registries.ITEM, new Identifier("tutorial", "custom_item"), CUSTOM_ITEM);
		FuelRegistry.INSTANCE.add(CUSTOM_ITEM, 1);
		Registry.register(Registries.ITEM, new Identifier("tutorial", "cuso_tool"), CUSO_TOOL);
		//BLOCK
		Registry.register(Registries.BLOCK, new Identifier("tutorial", "example_block"), EXAMPLE_BLOCK);
        Registry.register(Registries.ITEM, new Identifier("tutorial", "example_block"), new BlockItem(EXAMPLE_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, new Identifier("tutorial", "chargeable_block"), ChargeableBlock.CHARGEABLE_BLOCK);
        Registry.register(Registries.ITEM, new Identifier("tutorial", "chargeable_block"), new BlockItem(ChargeableBlock.CHARGEABLE_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier("tutorial", "polished_andesite_vertical_slab"), new BlockItem(POLISHED_ANDESITE_VERTICAL_SLAB, new FabricItemSettings()));
	}
}