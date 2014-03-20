package redgear.securecraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redgear.core.block.BlockGeneric;
import redgear.core.mod.ModUtils;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "RedGear|Securecraft", name = "Securecraft", version = "@ModVersion@", dependencies = "required-after:RedGear|Core;")
public class Securecraft extends ModUtils {

	@Instance("RedGear|Securecraft")
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

	public Securecraft() {
		super(2550, 23150);
	}

	@Override
	protected void PreInit(FMLPreInitializationEvent event) {
		RenforcedObsidian = new BlockGeneric(getBlockId("RenforcedObsidian"), Material.rock, "renforcedObsidian")
				.setHardness(100).setResistance(2000.0F).setStepSound(Block.soundStoneFootstep)
				.setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("RenforcedObsidian");
		SmartObsidian = new BlockSmartObsidian(getBlockId("SmartObsidian"), Material.rock).setBlockUnbreakable()
				.setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("SmartObsidian").setResistance(6000000.0F);
		ObsidianPPBlock = new BlockAddedPressurePlates(getBlockId("ObsidianPP"), "obsidian", Material.rock,
				EntityPlayer.class).setHardness(50).setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("ObsidianPP");
		DiamondPPBlock = new BlockSmartPressurePlates(getBlockId("DiamondPP"), "diamond_block", Material.rock,
				EntityPlayer.class).setHardness(5).setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("DiamondPP");
		RenforcedObsidianPPBlock = new BlockAddedPressurePlates(getBlockId("RenforcedObsidianPP"),
				"redgear_securecraft:renforcedObsidian", Material.rock, EntityPlayer.class).setHardness(100)
				.setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("RenforcedObsidianPP");
		SmartObsidianPPBLock = new BlockSmartPressurePlates(getBlockId("SmartObsidianPP"),
				"redgear_securecraft:smartObsidian", Material.rock, EntityPlayer.class).setBlockUnbreakable()
				.setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("SmartObsidianPP").setResistance(6000000.0F);
		MossyPPBlock = new BlockAddedPressurePlates(getBlockId("MossyPP"), "cobblestone_mossy", Material.rock,
				IMob.class).setHardness(2.0F).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("MossyPP");
		BrickPPBlock = new BlockAddedPressurePlates(getBlockId("BrickPP"), "brick", Material.rock, EntityAnimal.class)
				.setHardness(2.0F).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("BrickPP");
		DiamondDoorBlock = new BlockSmartDoors(getBlockId("DiamondDoor"), Material.rock, 0).setHardness(5)
				.setUnlocalizedName("DiamondDoor");
		ObsidianDoorBlock = new BlockAddedDoors(getBlockId("ObsidianDoor"), Material.rock, 1).setHardness(50)
				.setUnlocalizedName("ObsidianDoor");
		RenforcedObsidianDoorBlock = new BlockAddedDoors(getBlockId("RenforcedObsidianDoor"), Material.rock, 2)
				.setHardness(100).setUnlocalizedName("RenforcedObsidianDoor");
		SmartObsidianDoorBlock = new BlockSmartDoors(getBlockId("SmartDoor"), Material.rock, 3).setBlockUnbreakable()
				.setUnlocalizedName("SmartObsidianDoor").setResistance(6000000.0F);
		EmeraldPPBlock = new BlockSmartPressurePlates(getBlockId("EmeraldPP"), "emerald_block", Material.rock,
				EntityLiving.class, true).setHardness(5).setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("EmeraldPP");
		RenforcedEmeraldPPBlock = new BlockSmartPressurePlates(getBlockId("RenforcedEmeraldPP"),
				"redgear_securecraft:smartObsidianAnti", Material.rock, EntityLiving.class, true).setBlockUnbreakable()
				.setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("RenforcedEmeraldPP").setResistance(6000000.0F);
		ChiselTool = new ItemToolChisel(getItemId("ObsidianChisel")).setCreativeTab(CreativeTabs.tabTools)
				.setUnlocalizedName("ObsidianChisel");
		AddedDoorItem = new ItemAddedDoor(getItemId("DoorItem")).setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("AddedDoorItem");
		GameRegistry.registerBlock(RenforcedObsidian, "RenforcedObsidian");
		GameRegistry.registerBlock(SmartObsidian, "SmartObsidian");
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

		GameRegistry.registerItem(ChiselTool, "ChiselTool");
		GameRegistry.registerItem(AddedDoorItem, "AddedDoorItem");

		GameRegistry.addShapedRecipe(new ItemStack(RenforcedObsidian, 4, 0), new Object[] {"OIO", "III", "OIO", 'O',
				Block.obsidian, 'I', Item.ingotIron });
		GameRegistry.addShapedRecipe(new ItemStack(SmartObsidian, 8, 0), new Object[] {"OOO", "ODO", "OOO", 'O',
				RenforcedObsidian, 'D', Item.diamond });
		GameRegistry.addShapedRecipe(new ItemStack(ChiselTool, 1, 0), new Object[] {"R", "S", "S", 'R',
				RenforcedObsidian, 'S', Item.stick });
		GameRegistry.addShapedRecipe(new ItemStack(RenforcedObsidianPPBlock, 1, 0), new Object[] {"XX", 'X',
				RenforcedObsidian });
		GameRegistry.addShapedRecipe(new ItemStack(SmartObsidianPPBLock, 1, 0),
				new Object[] {"XX", 'X', SmartObsidian });
		GameRegistry.addShapedRecipe(new ItemStack(ObsidianPPBlock, 1, 0), new Object[] {"XX", 'X', Block.obsidian });
		GameRegistry.addShapedRecipe(new ItemStack(DiamondPPBlock, 1, 0), new Object[] {"XX", 'X', Item.diamond });
		GameRegistry.addShapedRecipe(new ItemStack(MossyPPBlock, 1, 0),
				new Object[] {"XX", 'X', Block.cobblestoneMossy });
		GameRegistry.addShapedRecipe(new ItemStack(BrickPPBlock, 1, 0), new Object[] {"XX", 'X', Block.brick });
		GameRegistry.addShapedRecipe(new ItemStack(EmeraldPPBlock, 1, 0), new Object[] {"XX", 'X', Item.emerald });
		GameRegistry.addShapedRecipe(new ItemStack(RenforcedEmeraldPPBlock, 1, 0), new Object[] {"XXX", "XPX", "XXX",
				'X', RenforcedObsidian, 'P', EmeraldPPBlock });
		GameRegistry.addShapedRecipe(new ItemStack(AddedDoorItem, 1, 0), new Object[] {"XX", "XX", "XX", 'X',
				Item.diamond });
		GameRegistry.addShapedRecipe(new ItemStack(AddedDoorItem, 1, 1), new Object[] {"XX", "XX", "XX", 'X',
				Block.obsidian });
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
