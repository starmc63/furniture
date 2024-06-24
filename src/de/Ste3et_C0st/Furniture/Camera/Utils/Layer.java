/*    */ package de.Ste3et_C0st.Furniture.Camera.Utils;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Layer
/*    */ {
/*  8 */   private int layerID = 0;
/*  9 */   private List<Pixel> pixelList = new ArrayList<>();
/*    */   
/*    */   public Layer(int layerID) {
/* 12 */     this.layerID = layerID;
/*    */   }
/*    */   
/*    */   public void addPixel(Pixel p) {
/* 16 */     this.pixelList.add(p);
/*    */   }
/*    */   
/*    */   public List<Pixel> getPixelList() {
/* 20 */     return this.pixelList;
/*    */   }
/*    */   
/*    */   public int getLayerID() {
/* 24 */     return this.layerID;
/*    */   }
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Camera\Utils\Layer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */