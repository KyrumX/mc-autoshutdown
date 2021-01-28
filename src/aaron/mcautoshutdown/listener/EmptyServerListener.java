package aaron.mcautoshutdown.listener;

import aaron.mcautoshutdown.McAutoshutdown;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class EmptyServerListener implements Listener {

    private int schedulerId = -1;
    private final McAutoshutdown plugin;
    private BukkitScheduler scheduler;

    public EmptyServerListener(McAutoshutdown plugin) {
        this.plugin = plugin;
        this.scheduler = plugin.getServer().getScheduler();
    }

    /**
     The quit event handler, queues a task if the server becomes empty after the player has left
     @param event The player quit event
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Need to substract one since the server still counts the player as online while in the quit event
        if(plugin.getServer().getOnlinePlayers().size() - 1 <= 0) {
            Bukkit.broadcastMessage("Starting shutdown timer....");
            this.schedulerId = this.scheduler.scheduleSyncDelayedTask(this.plugin, new Runnable() {
                @Override
                public void run() {
                    plugin.getServer().shutdown();
                }
            }, 6000L);
        }
    }

    /**
     The quit event handler, cancels the shutdown task if it had been queued
     @param event The player join event
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (this.schedulerId != -1) {
            Bukkit.broadcastMessage("Cancelled shutdown timer!");
            this.scheduler.cancelTask(schedulerId);
            this.schedulerId = -1;
        }
    }
}
