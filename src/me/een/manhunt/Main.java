package me.een.manhunt;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.een.manhunt.commands.SelectRunner;
import me.een.manhunt.commands.SetDelay;
import me.een.manhunt.commands.Start;
import me.een.manhunt.listeners.CompassPointer;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		new SelectRunner(this);
		new SetDelay(this);
		new Start(this);
		
		PluginManager pm = getServer().getPluginManager();
		CompassPointer listener = new CompassPointer(this);
		pm.registerEvents(listener, this);
	}
}
