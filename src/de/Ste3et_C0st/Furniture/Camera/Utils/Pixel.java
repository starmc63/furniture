/*    */ package de.Ste3et_C0st.Furniture.Camera.Utils;
/*    */ 
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class Pixel
/*    */ {
/*  7 */   int x = 0;
/*  8 */   int z = 0;
/*  9 */   byte color = 0;
/*    */   
/* 11 */   private UUID serialID = UUID.randomUUID();
/*    */   
/*    */   public Pixel(int x, int z, byte color) {
/* 14 */     this.x = x;
/* 15 */     this.z = z;
/* 16 */     this.color = color;
/*    */   }
/*    */   
/* 19 */   public int getX() { return this.x; } public int getZ() {
/* 20 */     return this.z;
/*    */   } public byte getColor() {
/* 22 */     return this.color;
/*    */   }
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Camera\Utils\Pixel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */