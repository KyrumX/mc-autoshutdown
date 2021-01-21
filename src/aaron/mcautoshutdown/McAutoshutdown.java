package aaron.mcautoshutdown;

import aaron.mcautoshutdown.listener.EmptyServerListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class McAutoshutdown extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();

        // Register events
        getServer().getPluginManager().registerEvents(new EmptyServerListener(this), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
