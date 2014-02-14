package redgear.securecraft;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redgear.core.tile.TileEntitySmart;
import redgear.core.util.ItemStackUtil;

public class BlockSmartDoors extends BlockAddedDoors implements ITileEntityProvider {
	public BlockSmartDoors(Material par2Material, int doorIndex) {
		super(par2Material, doorIndex);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g,
			float t) {
		ItemStack heldItem = player.getHeldItem();
		String owner = getOwner(world, x, y, z);

		if (heldItem != null && Securecraft.ChiselTool == heldItem.getItem()) {
			if (player.getDisplayName().equals(owner)) {
				ItemStackUtil.dropItemStack(world, x, y, z,
						new ItemStack(Securecraft.AddedDoorItem, 1, doorTypeForIcon));

				if ((world.getBlockMetadata(x, y, z) & 8) != 0)
					world.setBlockToAir(x, y - 1, z);

				//else
				//eworld.setBlockToAir(x, y + 1, z);
				world.setBlockToAir(x, y, z);
				return true;
			}

			//else
			//	player.addChatMessage("This block is owned by: " + owner);
		} else if (player.getDisplayName().equals(owner))
			return super.onBlockActivated(world, x, y, z, player, i, f, g, t);

		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySmart();
	}

	protected String getOwner(World world, int x, int y, int z) {
		try {
			TileEntitySmart myTile = (TileEntitySmart) world.getTileEntity(x, y, z);
			return myTile.ownerName;
		} catch (Exception e)  //is something goes wrong, just return an empty string
		{
			return "";
		}
	}

	protected void toggleDoor(World world, int x, int y, int z) {
		int i1 = getFullMetadata(world, x, y, z);
		int j1 = i1 & 7;
		j1 ^= 4;

		if ((i1 & 8) == 0) {
			world.setBlockMetadataWithNotify(x, y, z, j1, 2);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
		} else {
			world.setBlockMetadataWithNotify(x, y - 1, z, j1, 2);
			world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
		}

		world.playAuxSFXAtEntity((EntityPlayer) null, 1003, x, y, z, 0);
	}

	private int isSmartPlateNearby(World world, int x, int y, int z) {
		final int Map[][] = { {0, 1, 0, -1 }, {1, 0, -1, 0 } };

		for (int i = 0; i < 4; i++) {
			Block block = world.getBlock(x + Map[0][i], y + Map[1][i], z);
			if (block == Securecraft.DiamondPPBlock || block == Securecraft.SmartObsidianPPBLock)
				if (((TileEntitySmart) world.getTileEntity(x, y, z)).ownerName.equals(world.getTileEntity(
						x + Map[0][i], y + Map[1][i], z)))
					return world.getBlockMetadata(x + Map[0][i], y + Map[1][i], z);
		}
		return -1;
	}

	/**
	 * A function to open a door.
	 */
	@Override//onPoweredBlockChange
	public void func_150014_a(World par1World, int par2, int par3, int par4, boolean par5) {
		/*
		 * int l = this.getFullMetadata(par1World, par2, par3, par4);
		 * boolean flag1 = (l & 4) != 0;
		 * int plateState = isSmartPlateNearby(par1World, par2, par3, par4);
		 * 
		 * if (flag1 != par5 && plateState > -1)
		 * {
		 * int i1 = l & 7;
		 * i1 ^= 4;
		 * 
		 * if ((l & 8) == 0)
		 * {
		 * par1World.setBlockMetadataWithNotify(par2, par3, par4, i1, 2);
		 * par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3,
		 * par4);
		 * }
		 * else
		 * {
		 * par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, i1, 2);
		 * par1World.markBlockRangeForRenderUpdate(par2, par3 - 1, par4, par2,
		 * par3, par4);
		 * }
		 * 
		 * par1World.playAuxSFXAtEntity((EntityPlayer)null, 1003, par2, par3,
		 * par4, 0);
		 * }
		 */
	}
}
