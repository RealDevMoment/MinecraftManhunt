package me.een.manhunt.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.een.manhunt.Main;
import me.een.manhunt.listeners.CompassPointer;

public class SelectRunner implements CommandExecutor{
	
	public SelectRunner(Main main) {
		main.getCommand("select").setExecutor(this);
		
		Bukkit.getLogger().info("Command /select activated!");
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(!(arg0 instanceof Player)) {
			return true;
		}
		
		Player p = (Player) arg0;
		
		if(arg3.length != 1) {
			p.sendMessage(ChatColor.RED + "Please specifiy a player!");
			return true;
		}
		
		if(Bukkit.getPlayer(arg3[0]) == null) {
			p.sendMessage(ChatColor.RED + "Please specifiy an online player!");
			return true;
		}
		
		Player t = Bukkit.getPlayer(arg3[0]);
		Start.t = t;
		
		Bukkit.broadcastMessage(ChatColor.GREEN + "Speed Runner set to " + ChatColor.WHITE + t.getName());
		
		for(Player p2 : Bukkit.getOnlinePlayers()) {
			p2.getInventory().clear();
			
			if(p2 != t) {
				p2.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
			}
		}
		
		CompassPointer.t = t;
		
		return false;
	}

}
