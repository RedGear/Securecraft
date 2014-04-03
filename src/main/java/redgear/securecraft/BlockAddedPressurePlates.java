package redgear.securecraft;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import redgear.core.tile.TileEntitySmart;

public class BlockAddedPressurePlates extends BlockPressurePlate {
	protected Class<?> acceptableEntityClass;
	private final boolean isSmart;
	private final boolean isAnti;

	public BlockAddedPressurePlates(String par2Str, Material par3Material, Class<?> creatureClass, boolean isSmart,
			boolean isAnti) {
		super(par2Str, par3Material,  Sensitivity.mobs);
		acceptableEntityClass = creatureClass;
		this.isSmart = isSmart;
		this.isAnti = isAnti;
	}

	public BlockAddedPressurePlates(String par2Str, Material par3Material, Class<?> creatureClass, boolean isSmart) {
		this(par2Str, par3Material, creatureClass, false, false);
	}

	public BlockAddedPressurePlates(String par2Str, Material par3Material, Class<?> creatureClass) {
		this(par2Str, par3Material, creatureClass, false);
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

	/**
	 * Returns the current state of the pressure plate. Returns a value between
	 * 0 and 15 based on the number of items on
	 * it.
	 */
	@Override
	//getPlateState
	protected int func_150065_e(World world, int x, int y, int z) {
		List<?> list = world.getEntitiesWithinAABB(EntityLivingBase.class, this.func_150061_a(x, y, z));

		if (!list.isEmpty()) {
			Iterator<?> iterator = list.iterator();

			while (iterator.hasNext()) {
				EntityLivingBase entity = (EntityLivingBase) iterator.next();

				if (!entity.doesEntityNotTriggerPressurePlate()
						&& acceptableEntityClass.isAssignableFrom(entity.getClass()))
					if (isSmart) {
						if (EntityPlayer.class.isAssignableFrom(entity.getClass())) {
							boolean test = ((EntityPlayer) entity).getDisplayName().equals(getOwner(world, x, y, z));
							if (isAnti ? !test : test)
								return 15;
						} else if (isAnti)
							return 15;
					} else
						return 15;
			}
		}

		return 0;
	}
}
