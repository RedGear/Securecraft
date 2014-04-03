package redgear.securecraft;

import java.util.ArrayList;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAddedDoors extends BlockDoor {
	private static final String[][] doorIconNames = new String[][] { {"doorDiamond_lower", "doorDiamond_upper" },
			{"doorObsidian_lower", "doorObsidian_upper" },
			{"doorReinforcedObsidian_lower", "doorReinforcedObsidian_upper" },
			{"doorSmartObsidian_lower", "doorSmartObsidian_upper" } };
	private static IIcon[][] doorIcons = new IIcon[4][4];

	/** Used for pointing at icon names. */
	protected int doorTypeForIcon;

	public BlockAddedDoors(Material par2Material, int doorIndex) {
		super(par2Material);
		doorTypeForIcon = doorIndex;
	}

	/**
	 * Redirects to vanilla method until MCP fixes name.
	 */
	public int getFullMetadata(IBlockAccess world, int x, int y, int z) {
		return func_150012_g(world, x, y, z);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		int i1 = getFullMetadata(par1World, par2, par3, par4);
		int j1 = i1 & 7;
		j1 ^= 4;

		if ((i1 & 8) == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, j1, 2);
			par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
		} else {
			par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, j1, 2);
			par1World.markBlockRangeForRenderUpdate(par2, par3 - 1, par4, par2, par3, par4);
		}

		par1World.playAuxSFXAtEntity(par5EntityPlayer, 1003, par2, par3, par4, 0);
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public IIcon getIcon(int par1, int par2) {
		return doorIcons[doorTypeForIcon][0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
	 */
	public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		if (par5 != 1 && par5 != 0) {
			int i1 = getFullMetadata(par1IBlockAccess, par2, par3, par4);
			int j1 = i1 & 3;
			boolean flag = (i1 & 4) != 0;
			boolean flag1 = false;
			boolean flag2 = (i1 & 8) != 0;

			if (flag) {
				if (j1 == 0 && par5 == 2)
					flag1 = !flag1;
				else if (j1 == 1 && par5 == 5)
					flag1 = !flag1;
				else if (j1 == 2 && par5 == 3)
					flag1 = !flag1;
				else if (j1 == 3 && par5 == 4)
					flag1 = !flag1;
			} else {
				if (j1 == 0 && par5 == 5)
					flag1 = !flag1;
				else if (j1 == 1 && par5 == 3)
					flag1 = !flag1;
				else if (j1 == 2 && par5 == 4)
					flag1 = !flag1;
				else if (j1 == 3 && par5 == 2)
					flag1 = !flag1;

				if ((i1 & 16) != 0)
					flag1 = !flag1;
			}

			return doorIcons[doorTypeForIcon][(flag1 ? 1 : 0) + (flag2 ? 2 : 0)];
		} else
			return doorIcons[doorTypeForIcon][0];
	}

	@SideOnly(Side.CLIENT)
	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		for (int i = 0; i < 4; ++i) {
			doorIcons[i][0] = par1IconRegister.registerIcon("redgear_securecraft:" + doorIconNames[i][0]);
			doorIcons[i][1] = new IconFlipped(doorIcons[i][0], true, false);
			doorIcons[i][2] = par1IconRegister.registerIcon("redgear_securecraft:" + doorIconNames[i][1]);
			doorIcons[i][3] = new IconFlipped(doorIcons[i][2], true, false);
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		if ((metadata & 8) != 0)
			list.add(new ItemStack(Securecraft.AddedDoorItem, 1, doorTypeForIcon));

		return list;
	}

	@Override
	public int damageDropped(int metadata) {
		return doorTypeForIcon;
	}
}
