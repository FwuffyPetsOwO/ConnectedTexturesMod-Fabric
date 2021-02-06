package team.chisel.ctm.client.render;

import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.MathHelper;

import team.chisel.ctm.client.mixin.BakedQuadAccessor;

public class RenderUtil {
	public static final int UV_OFFSET = 4;

	public static BakedQuad retextureQuad(BakedQuad quad, Sprite sprite) {
		int[] newData = quad.getVertexData().clone();
		retextureQuadData(newData, ((BakedQuadAccessor) quad).getSprite(), sprite);
		return new BakedQuad(newData, quad.getColorIndex(), quad.getFace(), sprite, quad.hasShade());
	}

	public static void retextureQuadData(int[] vertexData, Sprite oldSprite, Sprite sprite) {
		for (int i = 0; i < 4; ++i) {
			int j = VertexFormats.POSITION_COLOR_TEXTURE_LIGHT_NORMAL.getVertexSizeInteger() * i;
			vertexData[j + UV_OFFSET] = Float.floatToRawIntBits(MathHelper.lerp((float) MathHelper.getLerpProgress(Float.intBitsToFloat(vertexData[j + UV_OFFSET]), oldSprite.getMinU(), oldSprite.getMaxU()), sprite.getMinU(), sprite.getMaxU()));
			vertexData[j + UV_OFFSET + 1] = Float.floatToRawIntBits(MathHelper.lerp((float) MathHelper.getLerpProgress(Float.intBitsToFloat(vertexData[j + UV_OFFSET + 1]), oldSprite.getMinV(), oldSprite.getMaxV()), sprite.getMinV(), sprite.getMaxV()));
		}
	}

	public static int getLightmap(int skyLight, int blockLight) {
		return MathHelper.clamp(skyLight, 0, 15) << 20 | MathHelper.clamp(blockLight, 0, 15) << 4;
	}

	/**
	 * Casts to int but keeps the bits the same.
	 */
	public static int bitIntCast(byte b) {
		return ((int) b) & 0xFF;
	}

	/**
	 * Casts to int but keeps the bits the same.
	 */
	public static int bitIntCast(short s) {
		return ((int) s) & 0xFFFF;
	}
}