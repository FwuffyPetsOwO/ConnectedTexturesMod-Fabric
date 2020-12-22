package team.chisel.ctm.client.handler;

import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.profiler.Profiler;

import team.chisel.ctm.client.event.ModelsLoadedCallback;
import team.chisel.ctm.client.model.AbstractCTMBakedModel;

public class CTMModelsLoadedCallbackHandler implements ModelsLoadedCallback {
	private WrappingCache wrappingCache;

	public CTMModelsLoadedCallbackHandler(WrappingCache wrappingCache) {
		this.wrappingCache = wrappingCache;
	}

	@Override
	public void onModelsLoaded(ModelLoader modelLoader, ResourceManager resourceManager, Profiler profiler) {
		wrappingCache.invalidate();
		AbstractCTMBakedModel.invalidateCaches();
	}
}