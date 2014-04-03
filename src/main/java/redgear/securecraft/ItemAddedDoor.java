package redgear.securecraft;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import redgear.core.tile.TileEntitySmart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAddedDoor extends ItemDoor {
	private final static String[] subNames = {"DiamondDoorItem", "ObsidianDoorItem", "RenforcedObsidianDoorItem",
			"SmartObsidianDoorItem" };
	IIcon[] icons;

	public ItemAddedDoor() {
		super(null);
		setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		icons = new IIcon[subNames.length];

		for (int i = 0; i < subNames.length; i++)
			icons[i] = register.registerIcon("redgear_securecraft:" + subNames[i]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not
	 * BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
			int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par7 != 1)
			return false;
		else {
			++par5;
			Block block;

			switch (par1ItemStack.getItemDamage()) {
			case 0:
				block = Securecraft.DiamondDoorBlock;
				break;

			case 1:
				block = Securecraft.ObsidianDoorBlock;
				break;

			case 2:
				block = Securecraft.ReinforcedObsidianDoorBlock;
				break;

			case 3:
				block = Securecraft.SmartObsidianDoorBlock;
				break;

			default:
				return false;
			}

			if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)
					&& par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack)) {
				if (!block.canPlaceBlockAt(par3World, par4, par5, par6))
					return false;
				else {
					int i1 = MathHelper.floor_double((par2EntityPlayer.rotationYaw + 180.0F) * 4.0F / 360.0F - 0.5D) & 3;
					placeDoorBlock(par3World, par4, par5, par6, i1, block);

					if (par1ItemStack.getItemDamage() == 0 || par1ItemStack.getItemDamage() == 3)
						try {
							TileEntitySmart myTile = (TileEntitySmart) par3World.getTileEntity(par4, par5, par6);
							myTile.ownerName = par2EntityPlayer.getDisplayName();
							myTile = (TileEntitySmart) par3World.getTileEntity(par4, par5 + 1, par6);
							myTile.ownerName = par2EntityPlayer.getDisplayName();
						} catch (Exception e) {
						} //if the tile dosen't cast right, just give up.

					--par1ItemStack.stackSize;
					return true;
				}
			} else
				return false;
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems) {
		for (int ix = 0; ix < 4; ix++)
			subItems.add(new ItemStack(par1, 1, ix));
	}
}
