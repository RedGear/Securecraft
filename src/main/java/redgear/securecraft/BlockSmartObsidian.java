package redgear.securecraft;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import redgear.core.block.BlockSmart;
import redgear.core.util.ItemStackUtil;

public class BlockSmartObsidian extends BlockSmart {
	public BlockSmartObsidian(Material material) {
		super(material, "smartObsidian");
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
}