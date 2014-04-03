package redgear.securecraft;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import redgear.core.tile.TileEntitySmart;
import redgear.core.util.ItemStackUtil;
import redgear.core.world.WorldLocation;

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

	/*protected void toggleDoor(World world, int x, int y, int z, boolean active) {
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
	}*/
	
	 /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
	@Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block other){
		if(other instanceof BlockSmartDoors){
			super.onNeighborBlockChange(world, x, y, z, other);
		}
		
    	if(other instanceof BlockSmartPressurePlates){
    		WorldLocation here = new WorldLocation(x, y, z, world);
    		WorldLocation there;
    		
    		for(int i = 2; i < 6; i++){
    			there = here.translate(i, 1);
    			
    			if(there.getBlock() instanceof BlockSmartPressurePlates){
    				TileEntitySmart otherTile = (TileEntitySmart)there.getTile();
    				String otherOwner = otherTile.ownerName;
    				if(this.getOwner(world, x, y, z).equals(otherOwner)){
    					super.onNeighborBlockChange(world, x, y, z, other);
    					return;
    				}
    			}
    			
    			
    		}
    	}
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
