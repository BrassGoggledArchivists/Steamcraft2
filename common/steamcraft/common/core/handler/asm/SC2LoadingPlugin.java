package common.steamcraft.common.core.handler.asm;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class SC2LoadingPlugin implements IFMLLoadingPlugin {



	@Override
	public String[] getASMTransformerClass() {
		return new String[] {"common.steamcraft.common.core.handler.asm.SC2AccessTransformer"};
	}

	@Override
	public String getModContainerClass() {
		return "common.steamcraft.common.core.handler.asm.SC2ModContainer";
	}

	@Override
	public String getSetupClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		// TODO Auto-generated method stub

	}

	@Override
	@Deprecated
	public
	String[] getLibraryRequestClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
