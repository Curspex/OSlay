package com.adamedw.oslay;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.adamedw.oslay.command.CommandOSlay;
import com.adamedw.oslay.listener.ListenerLogin;
import com.google.common.collect.Lists;

public class OSlay extends JavaPlugin {


	private static OSlay i;
	public static OSlay get() { return OSlay.i; }


	@Override
	public void onEnable()
	{
		OSlay.i = this;
		Bukkit.getPluginManager().registerEvents(new ListenerLogin(), OSlay.i);
		OSlay.i.getCommand("oslay").setExecutor(new CommandOSlay());


		OSlay.i.getConfig().addDefault("pending", OSlay.i.pending);
		this.cns();
		for (String uuidStr : OSlay.i.getConfig().getStringList("pending"))
		{
			OSlay.i.pending.add(UUID.fromString(uuidStr));
		}
		OSlay.i.getConfig().set("pending", Lists.newArrayList());
		this.cns();
	}

	@Override
	public void onDisable()
	{
		List<String> pendingString = Lists.newArrayList();
		for (UUID uuid : OSlay.i.pending)
		{
			pendingString.add(uuid.toString());
		}
		OSlay.i.getConfig().set("pending", pendingString);
		this.cns();
	}

	private void cns()
	{
		OSlay.i.getConfig().options().copyDefaults(true);
		OSlay.i.saveConfig();
	}

	private List<UUID> pending = Lists.newArrayList();
	public List<UUID> getPending() { return OSlay.i.pending; }


}