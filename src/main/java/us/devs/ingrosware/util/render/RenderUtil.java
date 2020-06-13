package us.devs.ingrosware.util.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
public class RenderUtil {

    public static void drawRect(float x, float y, float w, float h, int color) {
        float lvt_5_2_;
        float p_drawRect_2_ = x + w;
        float p_drawRect_3_ = y + h;
        if (x < p_drawRect_2_) {
            lvt_5_2_ = x;
            x = p_drawRect_2_;
            p_drawRect_2_ = lvt_5_2_;
        }

        if (y < p_drawRect_3_) {
            lvt_5_2_ = y;
            y = p_drawRect_3_;
            p_drawRect_3_ = lvt_5_2_;
        }

        float lvt_5_3_ = (float)(color >> 24 & 255) / 255.0F;
        float lvt_6_1_ = (float)(color >> 16 & 255) / 255.0F;
        float lvt_7_1_ = (float)(color >> 8 & 255) / 255.0F;
        float lvt_8_1_ = (float)(color & 255) / 255.0F;
        Tessellator lvt_9_1_ = Tessellator.getInstance();
        BufferBuilder lvt_10_1_ = lvt_9_1_.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(lvt_6_1_, lvt_7_1_, lvt_8_1_, lvt_5_3_);
        lvt_10_1_.begin(7, DefaultVertexFormats.POSITION);
        lvt_10_1_.pos(x, p_drawRect_3_, 0.0D).endVertex();
        lvt_10_1_.pos(p_drawRect_2_, p_drawRect_3_, 0.0D).endVertex();
        lvt_10_1_.pos(p_drawRect_2_, y, 0.0D).endVertex();
        lvt_10_1_.pos(x, y, 0.0D).endVertex();
        lvt_9_1_.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

}
