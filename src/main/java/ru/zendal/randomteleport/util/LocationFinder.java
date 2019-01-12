package ru.zendal.randomteleport.util;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Collection;
import java.util.Random;

/**
 * The type Location finder.
 */
public class LocationFinder {

    /**
     * Gets random location.
     *
     * @param pointLocation  the point location
     * @param maxX           the max x
     * @param maxZ           the max z
     * @param blackListBlock the black list block
     * @return the random location
     */
    public static Location getRandomLocation(Location pointLocation, int maxX, int maxZ, Collection<Material> blackListBlock) {
        Location result;
        do {
            Location randomLocation = LocationFinder.getRandomLocation(pointLocation, maxX, maxZ);
            result = LocationFinder.getLocationForTeleport(randomLocation, blackListBlock);
        } while (result == null);
        return result;
    }


    private static Location getRandomLocation(Location center, int maxX, int maxZ) {
        int newX = LocationFinder.getRandomInteger(maxX * -1, maxX);
        int newZ = LocationFinder.getRandomInteger(maxZ * -1, maxZ);
        return new Location(center.getWorld(), newX, center.getWorld().getMaxHeight(), newZ);
    }


    private static int getRandomInteger(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min + 1);
    }

    private static Location getLocationForTeleport(Location location, Collection<Material> blackListBlock) {
        while (location.getBlockY() != 0) {
            location.setY(location.getY() - 1);
            if (location.getBlock().getType() == Material.AIR) {
                continue;
            }
            if (blackListBlock.stream().anyMatch(material -> material == location.getBlock().getType())) {
                return null;
            }
            location.setY(location.getY() + 2);
            return location;
        }
        return null;
    }
}
