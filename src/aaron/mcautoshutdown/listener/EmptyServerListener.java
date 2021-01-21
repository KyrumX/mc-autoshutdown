package aaron.mcautoshutdown.listener;

import aaron.mcautoshutdown.McAutoshutdown;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EmptyServerListener implements Listener {

    private int schedulerId = -1;
    private final McAutoshutdown plugin;

    public EmptyServerListener(McAutoshutdown plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if(plugin.getServer().getOnlinePlayers().size() <= 0) {
            schedulerId = plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                @Override
                public void run() {
                    plugin.getServer().shutdown();
                }
            }, 6000);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (this.schedulerId != -1) {
            plugin.getServer().getScheduler().cancelTask(schedulerId);
            this.schedulerId = -1;
        }
    }
}
