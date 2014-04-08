/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 * 
 * File created @ [Jan 30, 2014, 5:19:11 PM]
 */
package common.steamcraft.common.core.handler;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import common.steamcraft.common.block.ModBlocks;
import common.steamcraft.common.block.ModOres;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * @author MrArcane111
 *
 */
public class WorldGenerator implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider generator, IChunkProvider chunk) {
		int blockChunkX = chunkX * 16;
		int blockChunkZ = chunkZ * 16;
		
		switch (world.provider.dimensionId) {
		case -1:
			if(ConfigHandler.netherGenerationEnabled)
			generateNether(world, random, blockChunkX, blockChunkZ);
			break;
		case 0:
			if(ConfigHandler.overworldGenerationEnabled)
			generateSurface(world, random, blockChunkX, blockChunkZ);
			break;
		case 1:
			if(ConfigHandler.endGenerationEnabled)
			generateEnd(world, random, blockChunkX, blockChunkZ);
			break;
		}
	}

	private void generateSurface(World world, Random random, int chunkX, int chunkZ) {
		if(ConfigHandler.etheriumOreGenEnabled)
		{
		for (int chance = 0; chance < ConfigHandler.etheriumChance; chance++) {
			int etheriumXCoord = chunkX + random.nextInt(16);
			int etheriumYCoord = 8 + random.nextInt(ConfigHandler.etheriumHeight);
			int etheriumZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.oreVolucite.blockID, random.nextInt(2)).generate(world, random, etheriumXCoord, etheriumYCoord, etheriumZCoord);
		}
		}
		if(ConfigHandler.uraniumOreGenEnabled)
		{
		for (int chance = 0; chance < ConfigHandler.uraniumChance; chance++) {
			int uraniteXCoord = chunkX + random.nextInt(16);
			int uraniteYCoord = random.nextInt(ConfigHandler.uraniumHeight);
			int uraniteZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.oreUranite.blockID, random.nextInt(5)).generate(world, random, uraniteXCoord, uraniteYCoord, uraniteZCoord);
		}
		}
		if(ConfigHandler.brimstoneOreGenEnabled)
		{
		for (int chance = 0; chance < ConfigHandler.brimstoneChance; chance++) {
			int brimstoneXCoord = chunkX + random.nextInt(16);
			int brimstoneYCoord = random.nextInt(ConfigHandler.brimstoneHeight);
			int brimstoneZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.oreBrimstone.blockID, random.nextInt(8)).generate(world, random, brimstoneXCoord, brimstoneYCoord, brimstoneZCoord);
		}
		}
		if(ConfigHandler.borniteOreGenEnabled)
		{
		for (int chance = 0; chance < ConfigHandler.borniteChance; chance++) {
			int borniteXCoord = chunkX + random.nextInt(16);
			int borniteYCoord = random.nextInt(ConfigHandler.borniteHeight);
			int borniteZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.oreBornite.blockID, random.nextInt(12)).generate(world, random, borniteXCoord, borniteYCoord, borniteZCoord);
		}
		}
		if(ConfigHandler.phosphateOreGenEnabled)
		{
		for (int chance = 0; chance < ConfigHandler.phosphateChance; chance++) {
			int zincXCoord = chunkX + random.nextInt(16);
			int zincYCoord = random.nextInt(ConfigHandler.phosphateHeight);
			int zincZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.orePhosphate.blockID, random.nextInt(6)).generate(world, random, zincXCoord, zincYCoord, zincZCoord);
		}
		}
		if(ConfigHandler.aluminumOreGenEnabled)
		{
		for (int chance = 0; chance < ConfigHandler.aluminumChance; chance++) {
			int aluminumXCoord = chunkX + random.nextInt(16);
			int aluminumYCoord = random.nextInt(ConfigHandler.aluminumHeight);
			int aluminumZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.oreAluminum.blockID, random.nextInt(6)).generate(world, random, aluminumXCoord, aluminumYCoord, aluminumZCoord);
		}
		}
		if(ConfigHandler.copperOreGenEnabled)
		{
		for (int chance = 0; chance < ConfigHandler.copperChance; chance++) {
			int copperXCoord = chunkX + random.nextInt(16);
			int copperYCoord = random.nextInt(ConfigHandler.copperHeight);
			int copperZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.oreCopper.blockID, random.nextInt(6)).generate(world, random, copperXCoord, copperYCoord, copperZCoord);
		}
		}
		if(ConfigHandler.tinOreGenEnabled)
		{
		for (int chance = 0; chance < ConfigHandler.tinChance; chance++) {
			int tinXCoord = chunkX + random.nextInt(16);
			int tinYCoord = random.nextInt(ConfigHandler.tinHeight);
			int tinZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.oreTin.blockID, random.nextInt(6)).generate(world, random, tinXCoord, tinYCoord, tinZCoord);
		}
		}
		if(ConfigHandler.zincOreGenEnabled)
		{
		for (int chance = 0; chance < ConfigHandler.zincChance; chance++) {
			int zincXCoord = chunkX + random.nextInt(16);
			int zincYCoord = random.nextInt(ConfigHandler.zincHeight);
			int zincZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.oreZinc.blockID, random.nextInt(6)).generate(world, random, zincXCoord, zincYCoord, zincZCoord);
		}
		}
	}

	private void generateEnd(World world, Random random, int chunkX, int chunkZ) {
		if(ConfigHandler.netherEtheriumCrystalGenEnabled)
		{
		for (int chance = 0; chance < ConfigHandler.netherEtheriumCrystalChance; chance++) {
			int etheriumXCoord = chunkX + random.nextInt(16);
			int etheriumYCoord = random.nextInt(ConfigHandler.netherEtheriumCrystalHeight);
			int etheriumZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModBlocks.etheriumCrystal.blockID, random.nextInt(6)).generate(world, random, etheriumXCoord, etheriumYCoord, etheriumZCoord);
		}
		}
	}

	private void generateNether(World world, Random random, int chunkX, int chunkZ) {
		if(ConfigHandler.endEtheriumCrystalGenEnabled)
		{
		for (int chance = 0; chance < ConfigHandler.endEtheriumCrystalChance; chance++) {
			int phosphateXCoord = chunkX + random.nextInt(16);
			int phosphateYCoord = random.nextInt(ConfigHandler.endEtheriumCrystalHeight);
			int phosphateZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModBlocks.etheriumCrystal.blockID, random.nextInt(6)).generate(world, random, phosphateXCoord, phosphateYCoord, phosphateZCoord);
		}
		}
	}
}