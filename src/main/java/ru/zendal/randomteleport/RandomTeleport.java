package ru.zendal.randomteleport;

import org.bukkit.plugin.java.JavaPlugin;
import ru.zendal.randomteleport.config.FileConfigurationWrapper;
import ru.zendal.randomteleport.event.SignRandomTeleport;

public final class RandomTeleport extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new SignRandomTeleport(new FileConfigurationWrapper(this.getConfig())), this);
        this.saveConfig();

    }

    @Override
    public void onDisable() {
    }
}
