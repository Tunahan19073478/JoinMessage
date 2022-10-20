package org.message.message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Message extends JavaPlugin implements Listener {


    protected Message plugin;

    @Override
    public void onEnable() {

        plugin = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("jmtk").setExecutor(this);
        Bukkit.getConsoleSender().sendMessage("JoinMessge Plugin Aktif Edildi");

    }

    @Override
    public void onDisable() {


    }

    public Message getInstance(){return plugin;}


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        Player player = event.getPlayer();

//        player.sendMessage(new String[] {
//              "",
//                "          Sunucumuza Hoşgeldin!",
//                ""
//            });
        for (String string : getConfig().getStringList("motd"))
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', string));

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("jmtk")) {
            if (args.length == 0) {
                if (sender.hasPermission("joinmessage.permission")) {
                    sender.sendMessage("Bu Komutu Kullanmaya Yetkin Yok");
                    return true;
                }
            }
            if ((args.length ==1)&&(args[0].equalsIgnoreCase("reload"))) {
                if (sender.hasPermission("joinmessage.reload")) {
                    reloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "Plugin Reloadlandı!");
                    return true;
                }
            }
        }
        return true;
    }
}
