package team.chisel.ctm.client.resource;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.resource.metadata.ResourceMetadataReader;

import team.chisel.ctm.api.texture.CTMMetadataSection;

public class CTMMetadataReader implements ResourceMetadataReader<CTMMetadataSection> {
	@Override
	@Nullable
	public CTMMetadataSection fromJson(@Nullable JsonObject json) throws JsonParseException {
		if (json != null && json.isJsonObject()) {
			JsonObject obj = json.getAsJsonObject();
			if (obj.has("ctm_version")) {
				JsonElement version = obj.get("ctm_version");
				if (version.isJsonPrimitive() && version.getAsJsonPrimitive().isNumber()) {
					switch (version.getAsInt()) {
					case 1:
						return CTMMetadataSectionV1.fromJson(obj);
					}
				}
			} else {
				throw new JsonParseException("Found ctm section without ctm_version");
			}
		}
		return null;
	}

	@Override
	@NotNull
	public String getKey() {
		return CTMMetadataSection.SECTION_NAME;
	}
}