/*    */ package de.Ste3et_C0st.Furniture.Camera.Utils.v1_12;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Camera.Utils.MinecraftBlockColor;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Method;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public class BlockColor
/*    */   extends MinecraftBlockColor {
/*    */   private static Class<?> iBlockDataClazz;
/*    */   private static Class<?> BlockClazz;
/*    */   private static Class<?> MaterialMapColorClazz;
/*    */   
/*    */   static {
/*    */     try {
/* 16 */       BlockClazz = Class.forName("net.minecraft.server." + getBukkitVersion() + ".Block");
/* 17 */       iBlockDataClazz = Class.forName("net.minecraft.server." + getBukkitVersion() + ".IBlockData");
/* 18 */       MaterialMapColorClazz = Class.forName("net.minecraft.server." + getBukkitVersion() + ".MaterialMapColor");
/* 19 */     } catch (Exception e) {
/* 20 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Byte getBlockColor(Block b) {
/*    */     try {
/* 27 */       int combined = b.getType().getId() + (b.getData() << 12);
/*    */       
/* 29 */       Method iBlockDataMethod = BlockClazz.getDeclaredMethod("getByCombinedId", new Class[] { int.class });
/* 30 */       Object iblockData = iBlockDataMethod.invoke(null, new Object[] { Integer.valueOf(combined) });
/*    */       
/* 32 */       Method getBlockMethod = iBlockDataClazz.getMethod("getBlock", new Class[0]);
/* 33 */       Object nmsBlock = getBlockMethod.invoke(iblockData, new Object[0]);
/*    */       
/* 35 */       Field BlockFieldy = BlockClazz.getDeclaredField("y");
/* 36 */       BlockFieldy.setAccessible(true);
/* 37 */       Object materialMapColor = BlockFieldy.get(nmsBlock);
/*    */       
/* 39 */       Field MaterialMapColorFieldM = MaterialMapColorClazz.getDeclaredField("ad");
/* 40 */       MaterialMapColorFieldM.setAccessible(true);
/* 41 */       int color = MaterialMapColorFieldM.getInt(materialMapColor) * 4;
/*    */       
/* 43 */       return Byte.valueOf((byte)color);
/* 44 */     } catch (Exception e) {
/* 45 */       e.printStackTrace();
/*    */       
/* 47 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Camera\Utils\v1_12\BlockColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */