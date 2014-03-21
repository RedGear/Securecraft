package redgear.securecraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redgear.core.block.BlockGeneric;
import redgear.core.item.ItemGeneric;
import redgear.core.mod.ModUtils;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "redgear_securecraft", name = "Securecraft", version = "@ModVersion@", dependencies = "required-after:redgear_core;")
public class Securecraft extends ModUtils {

	@Instance("redgear_securecraft")
	public static ModUtils inst;

	public static Block RenforcedObsidian;
	public static Block SmartObsidian;

	public static Block ObsidianPPBlock;
	public static Block DiamondPPBlock;
	public static Block RenforcedObsidianPPBlock;
	public static Block SmartObsidianPPBLock;
	public static Block MossyPPBlock;
	public static Block BrickPPBlock;
	public static Block EmeraldPPBlock;
	public static Block RenforcedEmeraldPPBlock;

	public static Block DiamondDoorBlock;
	public static Block ObsidianDoorBlock;
	public static Block RenforcedObsidianDoorBlock;
	public static Block SmartObsidianDoorBlock;

	public static Item ChiselTool;
	public static Item AddedDoorItem;

	@Override
	protected void PreInit(FMLPreInitializationEvent event) {
		RenforcedObsidian = new BlockGeneric(Material.rock, "renforcedObsidian").setHardness(100)
				.setResistance(2000.0F).setStepSound(Block.soundTypeStone).setCreativeTab(CreativeTabs.tabDecorations)
				.setBlockName("RenforcedObsidian");
		SmartObsidian = new BlockSmartObsidian(Material.rock).setBlockUnbreakable().setStepSound(Block.soundTypeStone)
				.setCreativeTab(CreativeTabs.tabDecorations).setBlockName("SmartObsidian");
		ObsidianPPBlock = new BlockAddedPressurePlates("obsidian", Material.rock, EntityPlayer.class).setHardness(50)
				.setCreativeTab(CreativeTabs.tabDecorations).setBlockName("ObsidianPP");
		DiamondPPBlock = new BlockSmartPressurePlates("diamond_block", Material.rock, EntityPlayer.class)
				.setHardness(5).setCreativeTab(CreativeTabs.tabDecorations).setBlockName("DiamondPP");
		RenforcedObsidianPPBlock = new BlockAddedPressurePlates("redgear_securecraft:renforcedObsidian", Material.rock,
				EntityPlayer.class).setHardness(100).setCreativeTab(CreativeTabs.tabDecorations)
				.setBlockName("RenforcedObsidianPP");
		SmartObsidianPPBLock = new BlockSmartPressurePlates("redgear_securecraft:smartObsidian", Material.rock,
				EntityPlayer.class).setBlockUnbreakable().setCreativeTab(CreativeTabs.tabDecorations)
				.setBlockName("SmartObsidianPP");
		MossyPPBlock = new BlockAddedPressurePlates("cobblestone_mossy", Material.rock, IMob.class).setHardness(2.0F)
				.setCreativeTab(CreativeTabs.tabDecorations).setBlockName("MossyPP");
		BrickPPBlock = new BlockAddedPressurePlates("brick", Material.rock, EntityAnimal.class).setHardness(2.0F)
				.setCreativeTab(CreativeTabs.tabDecorations).setBlockName("BrickPP");
		DiamondDoorBlock = new BlockSmartDoors(Material.rock, 0).setHardness(5).setBlockName("DiamondDoor");
		ObsidianDoorBlock = new BlockAddedDoors(Material.rock, 1).setHardness(50).setBlockName("ObsidianDoor");
		RenforcedObsidianDoorBlock = new BlockAddedDoors(Material.rock, 2).setHardness(100).setBlockName(
				"RenforcedObsidianDoor");
		SmartObsidianDoorBlock = new BlockSmartDoors(Material.rock, 3).setBlockUnbreakable().setBlockName(
				"SmartObsidianDoor");
		EmeraldPPBlock = new BlockSmartPressurePlates("emerald_block", Material.rock, EntityLiving.class, true)
				.setHardness(5).setCreativeTab(CreativeTabs.tabDecorations).setBlockName("EmeraldPP");
		RenforcedEmeraldPPBlock = new BlockSmartPressurePlates("redgear_securecraft:smartObsidianAnti", Material.rock,
				EntityLiving.class, true).setBlockUnbreakable().setCreativeTab(CreativeTabs.tabDecorations)
				.setBlockName("RenforcedEmeraldPP");
		ChiselTool = new ItemGeneric("ObsidianChisel").setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName(
				"ObsidianChisel");
		AddedDoorItem = new ItemAddedDoor().setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName(
				"AddedDoorItem");
		
		GameRegistry.registerBlock(ObsidianPPBlock, "ObsidianPPBlock");
		GameRegistry.registerBlock(DiamondPPBlock, "DiamondPPBlock");
		GameRegistry.registerBlock(RenforcedObsidianPPBlock, "RenforcedObsidianPPBlock");
		GameRegistry.registerBlock(SmartObsidianPPBLock, "SmartObsidianPPBLock");
		GameRegistry.registerBlock(MossyPPBlock, "MossyPPBlock");
		GameRegistry.registerBlock(BrickPPBlock, "BrickPPBlock");
		GameRegistry.registerBlock(EmeraldPPBlock, "EmeraldPPBlock");
		GameRegistry.registerBlock(RenforcedEmeraldPPBlock, "SmartObsidianAntiPPBlock");
		GameRegistry.registerBlock(DiamondDoorBlock, "DiamondDoorBlock");
		GameRegistry.registerBlock(ObsidianDoorBlock, "ObsidianDoorBlock");
		GameRegistry.registerBlock(RenforcedObsidianDoorBlock, "RenforcedObsidianDoorBlock");
		GameRegistry.registerBlock(SmartObsidianDoorBlock, "SmartObsidianDoorBlock");

		GameRegistry.registerItem(AddedDoorItem, "AddedDoorItem");

		GameRegistry.addShapedRecipe(new ItemStack(RenforcedObsidian, 4, 0), new Object[] {"OIO", "III", "OIO", 'O',
				Blocks.obsidian, 'I', Items.iron_ingot });
		GameRegistry.addShapedRecipe(new ItemStack(SmartObsidian, 8, 0), new Object[] {"OOO", "ODO", "OOO", 'O',
				RenforcedObsidian, 'D', Items.diamond });
		GameRegistry.addShapedRecipe(new ItemStack(ChiselTool, 1, 0), new Object[] {"R", "S", "S", 'R',
				RenforcedObsidian, 'S', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(RenforcedObsidianPPBlock, 1, 0), new Object[] {"XX", 'X',
				RenforcedObsidian });
		GameRegistry.addShapedRecipe(new ItemStack(SmartObsidianPPBLock, 1, 0),
				new Object[] {"XX", 'X', SmartObsidian });
		GameRegistry.addShapedRecipe(new ItemStack(ObsidianPPBlock, 1, 0), new Object[] {"XX", 'X', Blocks.obsidian });
		GameRegistry.addShapedRecipe(new ItemStack(DiamondPPBlock, 1, 0), new Object[] {"XX", 'X', Items.diamond });
		GameRegistry.addShapedRecipe(new ItemStack(MossyPPBlock, 1, 0), new Object[] {"XX", 'X',
				Blocks.mossy_cobblestone });
		GameRegistry.addShapedRecipe(new ItemStack(BrickPPBlock, 1, 0), new Object[] {"XX", 'X', Blocks.brick_block });
		GameRegistry.addShapedRecipe(new ItemStack(EmeraldPPBlock, 1, 0), new Object[] {"XX", 'X', Items.emerald });
		GameRegistry.addShapedRecipe(new ItemStack(RenforcedEmeraldPPBlock, 1, 0), new Object[] {"XXX", "XPX", "XXX",
				'X', RenforcedObsidian, 'P', EmeraldPPBlock });
		GameRegistry.addShapedRecipe(new ItemStack(AddedDoorItem, 1, 0), new Object[] {"XX", "XX", "XX", 'X',
				Items.diamond });
		GameRegistry.addShapedRecipe(new ItemStack(AddedDoorItem, 1, 1), new Object[] {"XX", "XX", "XX", 'X',
				Blocks.obsidian });
		GameRegistry.addShapedRecipe(new ItemStack(AddedDoorItem, 1, 2), new Object[] {"XX", "XX", "XX", 'X',
				RenforcedObsidian });
		GameRegistry.addShapedRecipe(new ItemStack(AddedDoorItem, 1, 3), new Object[] {"XX", "XX", "XX", 'X',
				SmartObsidian });
	}

	@Override
	public void Init(FMLInitializationEvent event) {

	}

	@Override
	protected void PostInit(FMLPostInitializationEvent event) {

	}

	@Override
	@EventHandler
	public void PreInitialization(FMLPreInitializationEvent event) {
		super.PreInitialization(event);
	}

	@Override
	@EventHandler
	public void Initialization(FMLInitializationEvent event) {
		super.Initialization(event);
	}

	@Override
	@EventHandler
	public void PostInitialization(FMLPostInitializationEvent event) {
		super.PostInitialization(event);
	}
}
