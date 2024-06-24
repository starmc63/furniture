/*    */ package de.Ste3et_C0st.Furniture.Camera.Utils;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public abstract class MinecraftBlockColor {
/*    */   public abstract Byte getBlockColor(Block paramBlock);
/*    */   
/*    */   public static String getBukkitVersion() {
/* 10 */     return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3]; } public static String getMainVersion() {
/* 11 */     return "v1_" + getBukkitVersion().split("_")[1];
/*    */   }
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Camera\Utils\MinecraftBlockColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */