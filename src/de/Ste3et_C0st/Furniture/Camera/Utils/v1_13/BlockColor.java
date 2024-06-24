/*    */ package de.Ste3et_C0st.Furniture.Camera.Utils.v1_13;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Camera.Utils.MinecraftBlockColor;
/*    */ import org.apache.commons.lang3.reflect.MethodUtils;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public class BlockColor
/*    */   extends MinecraftBlockColor {
/*    */   private static Class<?> CraftMagicNumbersClass;
/*    */   
/*    */   static {
/*    */     try {
/* 14 */       CraftMagicNumbersClass = Class.forName("org.bukkit.craftbukkit." + getBukkitVersion() + ".util.CraftMagicNumbers");
/* 15 */     } catch (Exception e) {
/* 16 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Byte getBlockColor(Block b) {
/*    */     try {
/* 23 */       Object nmsBlock = CraftMagicNumbersClass.getMethod("getBlock", new Class[] { Material.class }).invoke(null, new Object[] { b.getType() });
/* 24 */       Object iBlockData = nmsBlock.getClass().getMethod("getBlockData", new Class[0]).invoke(nmsBlock, new Object[0]);
/* 25 */       Object Material = MethodUtils.invokeMethod(iBlockData, "getMaterial", null);
/* 26 */       Object MaterialMapColor = MethodUtils.invokeMethod(Material, "i", null);
/* 27 */       int color = MaterialMapColor.getClass().getField("ac").getInt(MaterialMapColor) * 4;
/* 28 */       return Byte.valueOf((byte)color);
/* 29 */     } catch (Exception e) {
/* 30 */       e.printStackTrace();
/*    */       
/* 32 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Camera\Utils\v1_13\BlockColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */