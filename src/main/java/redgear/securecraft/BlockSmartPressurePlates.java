package redgear.securecraft;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redgear.core.tile.TileEntitySmart;
import redgear.core.util.ItemStackUtil;

public class BlockSmartPressurePlates extends BlockAddedPressurePlates implements ITileEntityProvider {
	public BlockSmartPressurePlates(String par2Str, Material par3Material,
			Class<? extends EntityLivingBase> creatureClass) {
		super(par2Str, par3Material, creatureClass, true);
	}

	public BlockSmartPressurePlates(String par2Str, Material par3Material,
			Class<? extends EntityLivingBase> creatureClass, boolean isAnti) {
		super(par2Str, par3Material, creatureClass, true, isAnti);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		try {
			TileEntitySmart myTile = (TileEntitySmart) world.getTileEntity(x, y, z);
			myTile.ownerName = ((EntityPlayer) entity).getDisplayName();
		} catch (Exception e) {
		} //if the tile or entity don't cast right, just give up.
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g,
			float t) {
		ItemStack heldItem = player.getHeldItem();
		String owner = getOwner(world, x, y, z);

		if (heldItem != null && Securecraft.ChiselTool == heldItem.getItem())
			if (player.getDisplayName().equals(owner)) {
				ItemStackUtil.dropItemStack(world, x, y, z, new ItemStack(this, 1, 0));
				world.setBlockToAir(x, y, z);
				return true;
			}

		return false;
	}

	@Override
	//setStateIfMobInteractsWithPlate
	protected void func_150062_a(World world, int x, int y, int z, int par5) {
		super.func_150062_a(world, x, y, z, par5);

		final int Map[][] = { {0, 1, 0, -1 }, {1, 0, -1, 0 } };

		for (int i = 0; i < 4; i++) {
			Block block = world.getBlock(x + Map[0][i], y + Map[1][i], z);

			if (block == Securecraft.DiamondDoorBlock || block == Securecraft.SmartObsidianDoorBlock)
				if (((TileEntitySmart) world.getTileEntity(x, y, z)).ownerName.equals(world.getTileEntity(
						x + Map[0][i], y + Map[1][i], z))) {
					BlockSmartDoors door = (BlockSmartDoors) block;

					door.toggleDoor(world, x + Map[0][i], y + Map[1][i], z);
				}
		}

	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySmart();
	}
}
