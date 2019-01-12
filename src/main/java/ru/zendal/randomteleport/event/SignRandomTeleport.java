package ru.zendal.randomteleport.event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import ru.zendal.randomteleport.config.ConfigurationRandomTeleport;
import ru.zendal.randomteleport.util.LocationFinder;

/**
 * The type Sign random teleport.
 */
public class SignRandomTeleport implements Listener {

    /**
     * Configuration of plugin
     */
    private ConfigurationRandomTeleport configuration;

    /**
     * Instantiates a new Sign random teleport.
     *
     * @param configuration the configuration
     */
    public SignRandomTeleport(ConfigurationRandomTeleport configuration) {
        this.configuration = configuration;
    }

    /**
     * On sign call.
     *
     * @param event the event
     */
    @EventHandler
    public void onSignCall(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.LEFT_CLICK_BLOCK) {
            return;
        }
        if (event.getClickedBlock() == null) {
            return;
        }

        Block block = event.getClickedBlock();
        if (block.getType() == Material.SIGN || block.getType() == Material.WALL_SIGN && block.getState() instanceof Sign) {
            Sign sign = (Sign) block.getState();
            if (sign.getLine(0).equals(this.preparePrettySignTitle(configuration.getPrettySignTitle()))) {
                event.getPlayer().teleport(LocationFinder.getRandomLocation(sign.getLocation(), configuration.getMaxXAtLocation(), configuration.getMaxZAtLocation(), configuration.getBlockBlackList()));
            }
        }
    }

    /**
     * Prepare title for compare
     *
     * @param prettySignTitle title in config
     * @return prepare title
     */
    private String preparePrettySignTitle(String prettySignTitle) {
        return prettySignTitle.replaceAll("§l", "");
    }

    /**
     * On sign create.
     *
     * @param event the event
     */
    @EventHandler
    public void onSignCreate(SignChangeEvent event) {
        if (!this.isServiceSign(event) || !event.getPlayer().hasPermission("randomteleport.sign.create")) {
            return;
        }
        event.setLine(0, configuration.getPrettySignTitle());
    }

    /**
     * Check is sign service
     *
     * @param event sing change event
     * @return {@code true} if service sign else {@code false}
     */
    private boolean isServiceSign(SignChangeEvent event) {
        return event.getLine(0).toLowerCase().equals(configuration.getRawSignTitle().toLowerCase());
    }

}
