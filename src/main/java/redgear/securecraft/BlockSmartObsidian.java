package redgear.securecraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import redgear.core.block.BlockSmart;
import redgear.core.util.ItemStackUtil;

public class BlockSmartObsidian extends BlockSmart {
	boolean glass = false;
	
	public BlockSmartObsidian(Material material, String name) {
		super(material, name);
	}
	
	public Block setGlass(){
		glass = true;
		return this;
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
	
	
	/**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
	@Override
    public boolean renderAsNormalBlock(){
        return !glass;
    }
	
	/**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return !glass;
    }
    
    /**
     * Checks if the block is a solid face on the given side, used by placement logic.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y position
     * @param z Z position
     * @param side The side to check
     * @return True if the block is solid on the specified side.
     */
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side){
    	return true;
    }
}