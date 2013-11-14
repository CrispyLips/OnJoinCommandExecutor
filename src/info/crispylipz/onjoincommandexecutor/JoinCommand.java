package info.crispylipz.onjoincommandexecutor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class JoinCommand extends JavaPlugin implements Listener {

    private File file;
    private World world;
    private String command;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        file = new File(getDataFolder(), "config.yml");
        loadFile(file);
        world = Bukkit.getWorld(getConfig().getString("world"));
        command = getConfig().getString("Command on Join");
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info(ChatColor.WHITE + "CL-JoinCommandExecutor is enabling...");
        getLogger().info(ChatColor.GREEN + "CL-JoinCommandExecutor has successfully enabled!");
        getLogger().info(ChatColor.DARK_RED + "Created by CrispyLipz");
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (player.getWorld().equals(world))
            Bukkit.getServer().dispatchCommand((CommandSender) player, command);
    }

    private void log(Level lvl, String message) {
        Bukkit.getLogger().log(lvl, message);
    }

    private void loadFile(File file) {
        if (!file.exists()) {
            try {
                log(Level.WARNING, file.getName() + "could not be found. Generating new one!");
                file.createNewFile();
                log(Level.INFO, file.getName() + "generated file successfully!");
            }catch (IOException ioe) {
                log(Level.SEVERE, file.getName() + "could not be generated!");
                ioe.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.RED + "CL-JoinCommandExecutor is now being disabled...");
        getLogger().info(ChatColor.RED + "CL-JoinCommandExecutor has successfully been disabled!");
    }

}
