package net.panno.wayla;

import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {

	public static final String modid  = "wayla";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("WAYLA initialized.");

	}

}
