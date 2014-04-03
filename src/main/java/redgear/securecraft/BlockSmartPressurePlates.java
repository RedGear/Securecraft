package redgear.securecraft;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import redgear.core.tile.TileEntitySmart;
import redgear.core.util.ItemStackUtil;
import redgear.core.world.WorldLocation;

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

	/*
	@Override
	//setStateIfMobInteractsWithPlate
	protected void func_150062_a(World world, int x, int y, int z, int meta) {
		super.func_150062_a(world, x, y, z, meta);
		
		WorldLocation loc = new WorldLocation(x, y, z, world);
		boolean active = loc.getBlockMeta() > 0;
		String owner = getOwner(loc);
		
		checkDoor(loc.translate(ForgeDirection.NORTH, 1), owner, active);
		checkDoor(loc.translate(ForgeDirection.SOUTH, 1), owner, active);
		checkDoor(loc.translate(ForgeDirection.EAST, 1), owner, active);
		checkDoor(loc.translate(ForgeDirection.WEST, 1), owner, active);
	}
	
	protected void checkDoor(WorldLocation loc, String owner, boolean active){
		Block block = loc.getBlock();

			if (block == Securecraft.DiamondDoorBlock || block == Securecraft.SmartObsidianDoorBlock)
				if (owner.equals(getOwner(loc))) {
					BlockSmartDoors door = (BlockSmartDoors) block;

					door.toggleDoor(loc.world, loc.getX(), loc.getY(), loc.getZ(), active);
				}
	}*/
	
	protected String getOwner(WorldLocation loc) {
		try {
			TileEntitySmart myTile = (TileEntitySmart) loc.getTile();
			return myTile.ownerName;
		} catch (Exception e)  //is something goes wrong, just return an empty string
		{
			return "";
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySmart();
	}
	
	/**
     * Determines if this block is can be destroyed by the specified entities normal behavior.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z position
     * @return True to allow the ender dragon to destroy this block
     */
	@Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity){
        return false;
    }
}
