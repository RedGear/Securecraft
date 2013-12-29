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

public class BlockSmartPressurePlates extends BlockAddedPressurePlates implements ITileEntityProvider
{
    public BlockSmartPressurePlates(int par1, String par2Str, Material par3Material, Class creatureClass){
        super(par1, par2Str, par3Material, creatureClass, true);
    }
    
    public BlockSmartPressurePlates(int par1, String par2Str, Material par3Material, Class creatureClass, boolean isAnti){
        super(par1, par2Str, par3Material, creatureClass, true, isAnti);
    }   

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
    {
        try
        {
            TileEntitySmart myTile = (TileEntitySmart) world.getBlockTileEntity(x, y, z);
            myTile.ownerName = ((EntityPlayer) entity).username;
        }
        catch (Exception e) {} //if the tile or entity don't cast right, just give up.
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t)
    {
        ItemStack heldItem = player.getHeldItem();
        String owner = getOwner(world, x, y, z);

        if (heldItem != null && ItemToolChisel.class.isAssignableFrom(heldItem.getItem().getClass()))
        {
            if (player.username.equals(owner))
            {
                ItemStackUtil.dropItemStack(world, x, y, z, new ItemStack(this, 1, 0));
                world.setBlockToAir(x, y, z);
                return true;
            }

            //else
            //	player.addChatMessage("This block is owned by: " + owner);
        }

        return false;
    }
    
    @Override
    protected void setStateIfMobInteractsWithPlate(World world, int x, int y, int z, int par5){
    	super.setStateIfMobInteractsWithPlate(world, x, y, z, par5);
    	
    	final int Map[][] = {{0, 1, 0, -1}, {1, 0, -1, 0}};
    	int meta = world.getBlockMetadata(x, y, z);
    	
    	for(int i = 0; i < 4; i++){
    		int blockId = world.getBlockId(x + Map[0][i], y + Map[1][i], z);
    		
    		
    		if(blockId == Securecraft.DiamondDoorBlock.blockID || blockId == Securecraft.SmartObsidianDoorBlock.blockID){
    			if(((TileEntitySmart)world.getBlockTileEntity(x, y, z)).ownerName.equals(((TileEntitySmart)world.getBlockTileEntity(x + Map[0][i], y + Map[1][i], z)))){
    				BlockSmartDoors block = (BlockSmartDoors)Block.blocksList[blockId];
    				
    				block.toggleDoor(world, x + Map[0][i], y + Map[1][i], z);
    			}
    				
    		}
    	}
    	
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntitySmart();
    }
}
