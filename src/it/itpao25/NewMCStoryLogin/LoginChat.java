package it.itpao25.NewMCStoryLogin;


import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class LoginChat extends JavaPlugin 
 implements Listener
{
	public final Logger logger = Logger.getLogger("Minecraft");
	public static LoginChat plugin;
	
	public void onEnable() 
	{
		getLogger().info("NewMcStoryLogin abilitato");
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(this, this);
		loadConfiguration();
	}
	
    	public void loadConfiguration() 
    	{
		this.getConfig().options().copyDefaults(true); 
		this.saveDefaultConfig();
    	}     
    
    	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
    		if(label.equalsIgnoreCase("NewMcStoryLogin")) 
		{
			if (sender instanceof Player) 
			{
				Player playerSend = (Player)sender;
				if (args.length == 0) 
				{
					playerSend.sendMessage(ChatColor.RED + this.getConfig().getString("messaggi.nessun-argomento"));
				}
				else if (args.length == 1) 
				{
					if (args[0].equalsIgnoreCase("reload")) 
					{
					if (playerSend.hasPermission("NewMCStoryLogin.reload") | (playerSend.isOp()))  
					{
						loadConfiguration();
						this.reloadConfig();
						this.saveConfig();
						playerSend.sendMessage(ChatColor.GREEN + this.getConfig().getString("messaggi.reload-completo"));
					} 
					else {
						playerSend.sendMessage(ChatColor.RED + this.getConfig().getString("messaggi.nessun-permesso"));
					}
					}
				}
			}
		}
		
		return false;
    	}
      
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
    	{
		Player player = event.getPlayer();
		
		if (!player.hasPermission("NewMCStoryLogin.Bypass") | (!player.isOp()))  
		{
			event.setCancelled(true);	
			player.sendMessage(ChatColor.RED + "Non hai permesso di usare la chat in quest'area, buttati nel portale");
			
		}
		
     	} 
    
} 
