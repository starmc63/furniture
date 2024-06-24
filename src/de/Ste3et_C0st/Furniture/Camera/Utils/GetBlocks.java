/*    */ package de.Ste3et_C0st.Furniture.Camera.Utils;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Camera.Utils.v1_13.BlockColor;
/*    */ import de.Ste3et_C0st.Furniture.Main.main;
/*    */ import de.Ste3et_C0st.FurnitureLib.Utilitis.LocationUtil;
/*    */ import de.Ste3et_C0st.FurnitureLib.Utilitis.Relative;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.Random;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ public class GetBlocks {
/*    */   public static MinecraftBlockColor colorBlock;
/*    */   
/*    */   static {
/*    */     try {
/* 21 */       Class<?> color = Class.forName("de.Ste3et_C0st.Furniture.Camera.Utils." + MinecraftBlockColor.getMainVersion() + ".BlockColor");
/* 22 */       if (Objects.nonNull(color)) {
/* 23 */         colorBlock = (MinecraftBlockColor)color.newInstance();
/*    */       } else {
/* 25 */         colorBlock = (MinecraftBlockColor)BlockColor.class.newInstance();
/*    */       } 
/* 27 */     } catch (Exception e) {
/* 28 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   public String getBukkitVersion() {
/* 32 */     return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
/*    */   }
/*    */   public List<Layer> returnBlocks(Location startLocation, int width, int heigt, int depth, int offsetZ) {
/*    */     try {
/* 36 */       main.getLocationUtil(); BlockFace face = LocationUtil.yawToFace(startLocation.getYaw()).getOppositeFace();
/* 37 */       List<Layer> layerList = new ArrayList<>();
/* 38 */       for (int i = depth; i >= 0; i--) {
/* 39 */         Layer l = new Layer(i);
/* 40 */         Location start = (new Relative(startLocation, (-offsetZ - i), 0.0D, (width / 2), face)).getSecondLocation();
/* 41 */         for (int z = 0; z < width; z++) {
/* 42 */           for (int y = 0; y < heigt; y++) {
/* 43 */             Location loc = (new Relative(start, 0.0D, y, -z, face)).getSecondLocation();
/* 44 */             Byte b = getByteFromBlock(loc.getBlock());
/* 45 */             l.addPixel(new Pixel(width - z, heigt - y, b.byteValue()));
/*    */           } 
/*    */         } 
/* 48 */         layerList.add(l);
/*    */       } 
/* 50 */       return layerList;
/* 51 */     } catch (Exception ex) {
/* 52 */       ex.printStackTrace();
/* 53 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Byte getByteFromBlock(Block b) {
/*    */     try {
/* 59 */       return colorBlock.getBlockColor(b);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     }
/* 69 */     catch (Exception e) {
/*    */       
/* 71 */       return Byte.valueOf((byte)0);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int randInt(int min, int max) {
/* 76 */     int randomNum = (new Random()).nextInt(max - min + 1) + min;
/* 77 */     return randomNum;
/*    */   }
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Camera\Utils\GetBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */