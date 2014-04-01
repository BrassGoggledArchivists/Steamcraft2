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
 * File created @ [1 Apr 2014, 22:45:34]
 */
package common.steamcraft.common.util;

import net.minecraft.world.World;

/**
 * @author warlordjones
 *
 * 1 Apr 201422:45:34
 */
public class SafeTimeTracker {

	private long lastMark = Long.MIN_VALUE;
	private long duration = -1;
	private long randomRange = 0;
	private long lastRandomDelay = 0;
	private long internalDelay = 1;

	/**
	 * @deprecated should use constructors with parameters instead
	 */
	public SafeTimeTracker () {

	}

	public SafeTimeTracker (long delay) {
		internalDelay = delay;
	}

	/**
	 * In many situations, it is a bad idea to have all objects of the same
	 * kind to be waiting for the exact same amount of time, as that can lead
	 * to some situation where they're all synchronized and got to work all
	 * at the same time. When created with a random range, the mark that is set
	 * when reaching the expect delay will be added with a random number
	 * between [0, range[, meaning that the event will take between 0 and range
	 * more tick to run.
	 */
	public SafeTimeTracker (long delay, long random) {
		internalDelay = delay;
		randomRange = random;
	}

	public boolean markTimeIfDelay(World world) {
		return markTimeIfDelay(world, internalDelay);
	}

	/**
	 * Return true if a given delay has passed since last time marked was called
	 * successfully.
	 *
	 * @deprecated should use the constructor with a delay instead, and call
	 * this function without a parameter
	 */
	public boolean markTimeIfDelay(World world, long delay) {
		if (world == null)
			return false;

		long currentTime = world.getTotalWorldTime();

		if (currentTime < lastMark) {
			lastMark = currentTime;
			return false;
		} else if (lastMark + delay + lastRandomDelay <= currentTime) {
			duration = currentTime - lastMark;
			lastMark = currentTime;
			lastRandomDelay = (int) (Math.random() * randomRange);

			return true;
		} else {
			return false;
		}
	}

	public long durationOfLastDelay() {
		return duration > 0 ? duration : 0;
	}

	public void markTime(World world) {
		lastMark = world.getTotalWorldTime();
	}
}
