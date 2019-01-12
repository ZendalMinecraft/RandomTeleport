package ru.zendal.randomteleport.config;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper of FileConfiguration
 */
public class FileConfigurationWrapper implements ConfigurationRandomTeleport {


    private FileConfiguration source;

    /**
     * Instantiates a new File configuration wrapper.
     *
     * @param fileConfiguration the file configuration
     */
    public FileConfigurationWrapper(FileConfiguration fileConfiguration) {
        this.source = fileConfiguration;
        this.initialize();
    }


    private void initialize() {

        if (!this.source.contains("settings.sign.name.pretty")) {
            this.source.set("settings.sign.name.pretty", "§l§b[§4RandomTP§b]");
        }

        if (!this.source.contains("settings.sign.name.raw")) {
            this.source.set("settings.sign.name.raw", "[RandomTP]");
        }

        if (!this.source.contains("settings.teleport.max.x")) {
            this.source.set("settings.teleport.max.x", 3000);
        }

        if (!this.source.contains("settings.teleport.max.z")) {
            this.source.set("settings.teleport.max.z", 3000);
        }

        if (!this.source.contains("settings.teleport.block.blackList")) {
            List<String> blockBlackList = new ArrayList<>();
            blockBlackList.add("LAVA");
            blockBlackList.add("WATER");
            blockBlackList.add("CACTUS");
            blockBlackList.add("CACTUS_GREEN");
            this.source.set("settings.teleport.block.blackList", blockBlackList);
        }

    }

    @Override
    public String getPrettySignTitle() {
        return this.source.getString("settings.sign.name.pretty");
    }

    @Override
    public String getRawSignTitle() {
        return this.source.getString("settings.sign.name.raw");
    }

    @Override
    public Integer getMaxXAtLocation() {
        return this.source.getInt("settings.teleport.max.x");
    }

    @Override
    public Integer getMaxZAtLocation() {
        return this.source.getInt("settings.teleport.max.z");
    }

    @Override
    public List<Material> getBlockBlackList() {
        List<String> rawBlockBlackList = this.source.getStringList("settings.teleport.block.blackList");
        List<Material> blockBlackList = new ArrayList<>();

        rawBlockBlackList.forEach(rawBlockBlack -> {
            blockBlackList.add(Material.getMaterial(rawBlockBlack));
        });
        return blockBlackList;
    }
}
