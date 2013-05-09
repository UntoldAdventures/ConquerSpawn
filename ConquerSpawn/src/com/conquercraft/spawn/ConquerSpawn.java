package com.conquercraft.spawn;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import com.conquercraft.spawn.command.CommandSpawn;
import com.conquercraft.spawn.commands.CommandSetSpawn;

public class ConquerSpawn extends JavaPlugin
{
	// Initialize the variables needed for the Config
	public static File pluginFolder;
	public static File configFile;
	public static FileConfiguration spawnConfig;
	
	public void onEnable()
	{
		// Commands
		getCommand("setspawn").setExecutor(new CommandSetSpawn(this));
		getCommand("spawn").setExecutor(new CommandSpawn(this));
		
		//Permissions
		getServer().getPluginManager().addPermission(new Permission("conquerspawn.setspawn"));
		getServer().getPluginManager().addPermission(new Permission("conquerspawn.spawn"));
		
		//Generating the Plugin Folder and Config File
		pluginFolder = getDataFolder();
		configFile = new File(pluginFolder, "config.yml");
		spawnConfig = new YamlConfiguration();
		if (!pluginFolder.exists())
		{
			try
			{
				pluginFolder.mkdir();
			} catch (Exception localException)
			{
			}
		}
		if (!configFile.exists())
		{
			try
			{
				configFile.createNewFile();
			} catch (Exception localException1)
			{
			}
		}
		try
		{
			spawnConfig.load(configFile);
		} catch (Exception localException2)
		{
		}
	}
}
