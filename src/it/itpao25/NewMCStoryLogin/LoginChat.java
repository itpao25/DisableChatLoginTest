package it.itpao25.NewMCStoryLogin;


import java.util.logging.Logger;

import org.bukkit.ChatColor;
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
	
	public void onEnable(){
		
		getLogger().info("NewMcStoryLogin abilitato");
		PluginManager manager = getServer().getPluginManager();
	    manager.registerEvents(this, this);
		
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
    {
		Player player = event.getPlayer();
		
		if ((!player.hasPermission("NewMCStoryLogin.Bypass")) || (!player.isOp())) 
		{
			event.setCancelled(true);	
			player.sendMessage(ChatColor.RED + "Non hai permesso di usare la chat in quest'area, buttati nel portale");	
		}
		
     } 
    

} 