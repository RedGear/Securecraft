package redgear.securecraft;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import redgear.core.block.BlockGeneric;

public class BlockStrongGlass extends BlockGeneric{

	public BlockStrongGlass(Material material, String name) {
		super(material, name);
	}
	
	 /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
	@Override
    public boolean renderAsNormalBlock(){
        return false;
    }
	
	/**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
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
