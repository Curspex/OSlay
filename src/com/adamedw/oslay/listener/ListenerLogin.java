package com.adamedw.oslay.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.adamedw.oslay.OSlay;

public class ListenerLogin implements Listener {


	@EventHandler
	public void onLogin(PlayerLoginEvent event)
	{
		final Player player = event.getPlayer();
		if (!OSlay.get().getPending().contains(player.getUniqueId())) return;
		Bukkit.getScheduler().runTask(OSlay.get(), new Runnable()
		{
			@Override
			public void run()
			{
				player.setHealth(0);
			}
		});
		OSlay.get().getPending().remove(player.getUniqueId());
	}


}