package me.hyperboid.testmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ExampleMod implements ModInitializer {
	public static final String MOD_ID = "hyperstest";

	public static final Item TEST_ITEM = new Item(
		new Item.Settings()
		.group(ItemGroup.MISC)
	);
	
	public static final Item OBAMIUM_INGOT = new Item(
		new Item.Settings()
		.group(ItemGroup.MISC)
	);

	public static final Block OBAMIUM_BLOCK = new Block(
		FabricBlockSettings
		.of(Material.STONE)
		.strength(0.9f, 3000f)
		.sounds(BlockSoundGroup.STONE)
		.breakByTool(FabricToolTags.PICKAXES)
	);

	private static ConfiguredFeature<?,?> OBAMIUM_ORE_OVERWORLD = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, OBAMIUM_BLOCK.getDefaultState(), 4))
	// TODO: Change to -64 for 1.17
	.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 64)))
	.spreadHorizontally()
	.repeat(100)
	;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
		Registry.register(
			Registry.ITEM,
			new Identifier(
				MOD_ID,"test_item"
			), TEST_ITEM
		);
		Registry.register(
			Registry.ITEM,
			new Identifier(
				MOD_ID,"obamium_ingot"
			), OBAMIUM_INGOT
		);
		Registry.register(
			Registry.BLOCK,
			new Identifier(
				MOD_ID,"obamium_block"
			), OBAMIUM_BLOCK
		);
		Registry.register(
			Registry.ITEM,
			new Identifier(
				MOD_ID,"obamium_block"
			),
			new BlockItem(
				OBAMIUM_BLOCK,
				new Item.Settings()
				.group(ItemGroup.BUILDING_BLOCKS)
			)
		);
		RegistryKey<ConfiguredFeature<?,?>> obamiumOreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MOD_ID, "obamium_block"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, obamiumOreOverworld.getValue(), OBAMIUM_ORE_OVERWORLD);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, obamiumOreOverworld);
	}
}
