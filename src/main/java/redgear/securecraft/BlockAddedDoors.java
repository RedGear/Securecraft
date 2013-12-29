package redgear.securecraft;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAddedDoors extends BlockDoor
{
    private static final String[][] doorIconNames = new String[][] {{"doorDiamond_lower", "doorDiamond_upper"}, {"doorObsidian_lower", "doorObsidian_upper"}, {"doorRenforcedObsidian_lower", "doorRenforcedObsidian_upper"}, {"doorSmartObsidian_lower", "doorSmartObsidian_upper"}};
    private static Icon[][] doorIcons = new Icon[4][4];

    /** Used for pointing at icon names. */
    protected int doorTypeForIcon;

    public BlockAddedDoors(int par1, Material par2Material, int doorIndex)
    {
        super(par1, par2Material);
        doorTypeForIcon = doorIndex;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        int i1 = this.getFullMetadata(par1World, par2, par3, par4);
        int j1 = i1 & 7;
        j1 ^= 4;

        if ((i1 & 8) == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, j1, 2);
            par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
        }
        else
        {
            par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, j1, 2);
            par1World.markBlockRangeForRenderUpdate(par2, par3 - 1, par4, par2, par3, par4);
        }

        par1World.playAuxSFXAtEntity(par5EntityPlayer, 1003, par2, par3, par4, 0);
        return true;
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return this.doorIcons[this.doorTypeForIcon][0];
    }

    @SideOnly(Side.CLIENT)

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par5 != 1 && par5 != 0)
        {
            int i1 = this.getFullMetadata(par1IBlockAccess, par2, par3, par4);
            int j1 = i1 & 3;
            boolean flag = (i1 & 4) != 0;
            boolean flag1 = false;
            boolean flag2 = (i1 & 8) != 0;

            if (flag)
            {
                if (j1 == 0 && par5 == 2)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 1 && par5 == 5)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 2 && par5 == 3)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 3 && par5 == 4)
                {
                    flag1 = !flag1;
                }
            }
            else
            {
                if (j1 == 0 && par5 == 5)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 1 && par5 == 3)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 2 && par5 == 4)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 3 && par5 == 2)
                {
                    flag1 = !flag1;
                }

                if ((i1 & 16) != 0)
                {
                    flag1 = !flag1;
                }
            }

            return this.doorIcons[this.doorTypeForIcon][(flag1 ? 1 : 0) + (flag2 ? 2 : 0)];
        }
        else
        {
            return this.doorIcons[this.doorTypeForIcon][0];
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        for (int i = 0; i < 4; ++i)
        {
            this.doorIcons[i][0] = par1IconRegister.registerIcon("redgear_securecraft:" + doorIconNames[i][0]);
            this.doorIcons[i][1] = new IconFlipped(this.doorIcons[i][0], true, false);
            this.doorIcons[i][2] = par1IconRegister.registerIcon("redgear_securecraft:" + doorIconNames[i][1]);
            this.doorIcons[i][3] = new IconFlipped(this.doorIcons[i][2], true, false);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return (par1 & 8) != 0 ? 0 : Securecraft.AddedDoorItem.itemID;
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Securecraft.AddedDoorItem.itemID;
    }

    @Override
    public int damageDropped(int metadata)
    {
        return doorTypeForIcon;
    }
}
