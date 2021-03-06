
package steamcraft.common.worldgen;

import java.util.Random;

import boilerplate.common.utils.WorldGenUtils;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import steamcraft.common.config.ConfigGeneral;
import steamcraft.common.config.ConfigWorldGen;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.worldgen.structure.WorldGenFissurePortal;
import steamcraft.common.worldgen.trees.WorldGenBrassTree;

/**
 * @author Surseance & warlordjones
 *
 */
public class WorldGenSteamcraft implements IWorldGenerator
{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider generator, IChunkProvider chunk)
	{
		int blockChunkX = chunkX * 16;
		int blockChunkZ = chunkZ * 16;

		if (((world.provider.dimensionId == 0) && ConfigWorldGen.overworldGenerationEnabled))
		{
			this.generateSurface(world, random, blockChunkX, blockChunkZ);
		}
	}

	private void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		if (ConfigWorldGen.aluminumOreGenEnabled)
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 10, 20, 40, ConfigWorldGen.aluminumCluster, InitBlocks.blockCustomOre, 0,
					Blocks.stone);

		if (ConfigWorldGen.copperOreGenEnabled)
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 15, 22, 45, ConfigWorldGen.copperCluster, InitBlocks.blockCustomOre, 1,
					Blocks.stone);

		if (ConfigWorldGen.tinOreGenEnabled)
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 15, 22, 45, ConfigWorldGen.tinCluster, InitBlocks.blockCustomOre, 2,
					Blocks.stone);

		if (ConfigWorldGen.zincOreGenEnabled)
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 12, 20, 36, ConfigWorldGen.zincCluster, InitBlocks.blockCustomOre, 3,
					Blocks.stone);

		if (ConfigWorldGen.uraniumOreGenEnabled)
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 1, 4, 24, ConfigWorldGen.uraniumCluster, InitBlocks.blockCustomOre, 4,
					Blocks.stone);

		if (ConfigWorldGen.brimstoneOreGenEnabled)
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 5, 20, 64, ConfigWorldGen.brimstoneCluster, InitBlocks.blockCustomOre, 5,
					Blocks.stone);

		if (ConfigWorldGen.phosphateOreGenEnabled)
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 7, 20, 36, ConfigWorldGen.phosphateCluster, InitBlocks.blockCustomOre, 6,
					Blocks.stone);

		if (ConfigWorldGen.slateGenEnabled)
		{
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 1, 10, 40, ConfigWorldGen.slateCluster, InitBlocks.blockSlate, 0, Blocks.stone);
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 1, 10, 40, ConfigWorldGen.slateCluster, InitBlocks.blockSlate, 1, Blocks.stone);
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 1, 10, 40, ConfigWorldGen.slateCluster, InitBlocks.blockSlate, 2, Blocks.stone);
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 1, 40, 70, ConfigWorldGen.slateCluster, InitBlocks.blockLightSlate, 0,
					Blocks.stone);
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 1, 40, 70, ConfigWorldGen.slateCluster, InitBlocks.blockLightSlate, 1,
					Blocks.stone);
			WorldGenUtils.generateOre(world, random, chunkX, chunkZ, 1, 40, 70, ConfigWorldGen.slateCluster, InitBlocks.blockLightSlate, 2,
					Blocks.stone);
		}

		if (ConfigWorldGen.brassTreeGenEnabled)
		{
			BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);

			if (BiomeDictionary.isBiomeOfType(biome, Type.FOREST))
				for (int i = 0; i < 1; i++)
				{
					int X = chunkX + random.nextInt(16);
					int Z = chunkZ + random.nextInt(16);
					int Y = random.nextInt(70);

					new WorldGenBrassTree().generate(world, random, X, Y, Z);
				}
		}
	}
}