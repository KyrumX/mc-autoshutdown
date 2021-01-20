package aaron.mcautoshutdown;

import aaron.mcautoshutdown.listener.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public class McAutoshutdown extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();

        // Register events
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
