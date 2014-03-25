package common.steamcraft.common.core.handler.asm;

import net.minecraftforge.event.EventBus;

import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class SC2ModContainer extends DummyModContainer{
	public SC2ModContainer() {

}

public boolean registerBus(EventBus bus, LoadController controller) {
bus.register(this);
return true;
}
/*
 * Use this in place of @Init, @Preinit, @Postinit in the file.
 */
@Subscribe                 /* Remember to use the right event! */
public void onServerStarting(FMLServerStartingEvent ev) {
       
}
}
