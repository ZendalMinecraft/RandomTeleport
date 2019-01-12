package ru.zendal.randomteleport.config;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.List;

public interface ConfigurationRandomTeleport {

    String getPrettySignTitle();

    String getRawSignTitle();

    Integer getMaxXAtLocation();

    Integer getMaxZAtLocation();

    List<Material> getBlockBlackList();
}
