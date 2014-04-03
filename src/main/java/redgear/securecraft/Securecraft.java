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

	public static Block ReinforcedObsidian;
	public static Block SmartObsidian;

	public static Block ObsidianPPBlock;
	public static Block DiamondPPBlock;
	public static Block ReinforcedObsidianPPBlock;
	public static Block SmartObsidianPPBLock;
	public static Block MossyPPBlock;
	public static Block BrickPPBlock;
	public static Block EmeraldPPBlock;
	public static Block ReinforcedEmeraldPPBlock;

	public static Block DiamondDoorBlock;
	public static Block ObsidianDoorBlock;
	public static Block ReinforcedObsidianDoorBlock;
	public static Block SmartObsidianDoorBlock;

	public static Block ReinforcedGlass;
	public static Block ReinforcedObsidianGlass;
	public static Block SmartGlass;
	
	public static Item ChiselTool;
	public static Item AddedDoorItem;
	
	public static CreativeTabs tab = new CreativeTabs("securecraft"){

		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(ReinforcedObsidian);
		}
		
	};

	@Override
	protected void PreInit(FMLPreInitializationEvent event) {
		ReinforcedObsidian = new BlockGeneric(Material.rock, "reinforcedObsidian").setHardness(100)
				.setResistance(4000.0F).setStepSound(Block.soundTypeStone).setCreativeTab(tab)
				.setBlockName("ReinforcedObsidian");
		SmartObsidian = new BlockSmartObsidian(Material.rock, "smartObsidian").setBlockUnbreakable().setResistance(6000000.0F).setStepSound(Block.soundTypeStone)
				.setCreativeTab(tab).setBlockName("SmartObsidian");
		ObsidianPPBlock = new BlockAddedPressurePlates("obsidian", Material.rock, EntityPlayer.class).setHardness(50)
				.setCreativeTab(tab).setBlockName("ObsidianPP");
		DiamondPPBlock = new BlockSmartPressurePlates("diamond_block", Material.rock, EntityPlayer.class)
				.setHardness(5).setCreativeTab(tab).setBlockName("DiamondPP");
		ReinforcedObsidianPPBlock = new BlockAddedPressurePlates("redgear_securecraft:reinforcedObsidian", Material.rock,
				EntityPlayer.class).setHardness(100).setResistance(4000.0F).setCreativeTab(tab)
				.setBlockName("ReinforcedObsidianPP");
		SmartObsidianPPBLock = new BlockSmartPressurePlates("redgear_securecraft:smartObsidian", Material.rock,
				EntityPlayer.class).setBlockUnbreakable().setResistance(6000000.0F).setCreativeTab(tab)
				.setBlockName("SmartObsidianPP");
		MossyPPBlock = new BlockAddedPressurePlates("cobblestone_mossy", Material.rock, IMob.class).setHardness(2.0F)
				.setCreativeTab(tab).setBlockName("MossyPP");
		BrickPPBlock = new BlockAddedPressurePlates("brick", Material.rock, EntityAnimal.class).setHardness(2.0F)
				.setCreativeTab(tab).setBlockName("BrickPP");
		DiamondDoorBlock = new BlockSmartDoors(Material.rock, 0).setHardness(5).setBlockName("DiamondDoor");
		ObsidianDoorBlock = new BlockAddedDoors(Material.rock, 1).setHardness(50).setResistance(2000.0F).setBlockName("ObsidianDoor");
		ReinforcedObsidianDoorBlock = new BlockAddedDoors(Material.rock, 2).setHardness(100).setResistance(4000.0F).setBlockName(
				"ReinforcedObsidianDoor");
		SmartObsidianDoorBlock = new BlockSmartDoors(Material.rock, 3).setBlockUnbreakable().setResistance(6000000.0F).setBlockName(
				"SmartObsidianDoor");
		EmeraldPPBlock = new BlockSmartPressurePlates("emerald_block", Material.rock, EntityLiving.class, true)
				.setHardness(5).setCreativeTab(tab).setBlockName("EmeraldPP");
		ReinforcedEmeraldPPBlock = new BlockSmartPressurePlates("redgear_securecraft:smartObsidianAnti", Material.rock,
				EntityLiving.class, true).setBlockUnbreakable().setResistance(6000000.0F).setCreativeTab(tab)
				.setBlockName("ReinforcedEmeraldPP");
		
		ReinforcedGlass = new BlockStrongGlass(Material.glass, "reinforcedGlass").setHardness(5.0F).setResistance(10.0F)
				.setStepSound(Block.soundTypeGlass).setCreativeTab(tab).setBlockName("ReinforcedGlass");
		ReinforcedObsidianGlass = new BlockStrongGlass(Material.glass, "reinforcedObsidianGlass").setHardness(50.0F)
				.setResistance(2000.0F).setStepSound(Block.soundTypeGlass).setCreativeTab(tab).setBlockName("ReinforcedObsidianGlass");
		SmartGlass = new BlockSmartObsidian(Material.glass, "smartGlass").setGlass().setBlockUnbreakable().setResistance(6000000.0F).setStepSound(Block.soundTypeGlass)
				.setCreativeTab(tab).setBlockName("SmartGlass");
		
		ChiselTool = new ItemGeneric("ObsidianChisel").setCreativeTab(tab).setUnlocalizedName(
				"ObsidianChisel");
		AddedDoorItem = new ItemAddedDoor().setCreativeTab(tab).setUnlocalizedName(
				"AddedDoorItem"); 
		
		GameRegistry.registerBlock(ObsidianPPBlock, "ObsidianPPBlock");
		GameRegistry.registerBlock(DiamondPPBlock, "DiamondPPBlock");
		GameRegistry.registerBlock(ReinforcedObsidianPPBlock, "ReinforcedObsidianPPBlock");
		GameRegistry.registerBlock(SmartObsidianPPBLock, "SmartObsidianPPBLock");
		GameRegistry.registerBlock(MossyPPBlock, "MossyPPBlock");
		GameRegistry.registerBlock(BrickPPBlock, "BrickPPBlock");
		GameRegistry.registerBlock(EmeraldPPBlock, "EmeraldPPBlock");
		GameRegistry.registerBlock(ReinforcedEmeraldPPBlock, "SmartObsidianAntiPPBlock");
		
		GameRegistry.registerBlock(DiamondDoorBlock, "DiamondDoorBlock");
		GameRegistry.registerBlock(ObsidianDoorBlock, "ObsidianDoorBlock");
		GameRegistry.registerBlock(ReinforcedObsidianDoorBlock, "ReinforcedObsidianDoorBlock");
		GameRegistry.registerBlock(SmartObsidianDoorBlock, "SmartObsidianDoorBlock");

		GameRegistry.registerItem(AddedDoorItem, "AddedDoorItem");

		GameRegistry.addShapedRecipe(new ItemStack(ReinforcedObsidian, 4, 0), new Object[] {"OIO", "III", "OIO", 'O',
				Blocks.obsidian, 'I', Items.iron_ingot });
		GameRegistry.addShapedRecipe(new ItemStack(SmartObsidian, 8, 0), new Object[] {"OOO", "ODO", "OOO", 'O',
				ReinforcedObsidian, 'D', Items.diamond });
		GameRegistry.addShapedRecipe(new ItemStack(ChiselTool, 1, 0), new Object[] {"R", "S", "S", 'R',
				ReinforcedObsidian, 'S', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(ReinforcedObsidianPPBlock, 1, 0), new Object[] {"XX", 'X',
				ReinforcedObsidian });
		GameRegistry.addShapedRecipe(new ItemStack(SmartObsidianPPBLock, 1, 0),
				new Object[] {"XX", 'X', SmartObsidian });
		GameRegistry.addShapedRecipe(new ItemStack(ObsidianPPBlock, 1, 0), new Object[] {"XX", 'X', Blocks.obsidian });
		GameRegistry.addShapedRecipe(new ItemStack(DiamondPPBlock, 1, 0), new Object[] {"XX", 'X', Items.diamond });
		GameRegistry.addShapedRecipe(new ItemStack(MossyPPBlock, 1, 0), new Object[] {"XX", 'X',
				Blocks.mossy_cobblestone });
		GameRegistry.addShapedRecipe(new ItemStack(BrickPPBlock, 1, 0), new Object[] {"XX", 'X', Blocks.brick_block });
		GameRegistry.addShapedRecipe(new ItemStack(EmeraldPPBlock, 1, 0), new Object[] {"XX", 'X', Items.emerald });
		GameRegistry.addShapedRecipe(new ItemStack(ReinforcedEmeraldPPBlock, 1, 0), new Object[] {"XXX", "XPX", "XXX",
				'X', ReinforcedObsidian, 'P', EmeraldPPBlock });
		
		
		GameRegistry.addShapedRecipe(new ItemStack(ReinforcedGlass, 4, 0), new Object[] {"OIO", "III", "OIO", 'O', Blocks.glass, 'I', Items.iron_ingot });
		GameRegistry.addShapedRecipe(new ItemStack(ReinforcedObsidianGlass, 4, 0), new Object[] {"OIO", "III", "OIO", 'O', Blocks.glass, 'I', Blocks.obsidian });
		GameRegistry.addShapedRecipe(new ItemStack(SmartGlass, 8, 0), new Object[] {"OOO", "ODO", "OOO", 'O', ReinforcedObsidianGlass, 'D', Items.diamond });
		
		
		GameRegistry.addShapedRecipe(new ItemStack(AddedDoorItem, 1, 0), new Object[] {"XX", "XX", "XX", 'X',
				Items.diamond });
		GameRegistry.addShapedRecipe(new ItemStack(AddedDoorItem, 1, 1), new Object[] {"XX", "XX", "XX", 'X',
				Blocks.obsidian });
		GameRegistry.addShapedRecipe(new ItemStack(AddedDoorItem, 1, 2), new Object[] {"XX", "XX", "XX", 'X',
				ReinforcedObsidian });
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
