/*     */ package de.Ste3et_C0st.Furniture.Camera.Utils;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.map.MapCanvas;
/*     */ import org.bukkit.map.MapPalette;
/*     */ import org.bukkit.map.MapRenderer;
/*     */ import org.bukkit.map.MapView;
/*     */ 
/*     */ public class RenderClass
/*     */   extends MapRenderer {
/*     */   private boolean hasRendered = false;
/*  20 */   private List<Layer> layerList = new ArrayList<>();
/*     */   private ScaleMode mode;
/*     */   
/*     */   public enum ScaleMode {
/*  24 */     NORMAL(56, 56), FAR(72, 72), FAHRTEST(102, 102), COMPLETE(128, 128);
/*     */     int height;
/*     */     int width;
/*     */     
/*     */     ScaleMode(int width, int height) {
/*  29 */       this.width = width;
/*  30 */       this.height = height;
/*     */     }
/*     */     
/*  33 */     public int getWidth() { return this.width; } public int getHeight() {
/*  34 */       return this.height;
/*     */     }
/*     */   }
/*  37 */   Integer height = Integer.valueOf(55);
/*  38 */   Integer width = Integer.valueOf(55);
/*     */ 
/*     */   
/*     */   public RenderClass(Location loc, ScaleMode mode) {
/*  42 */     GetBlocks blocks = new GetBlocks();
/*  43 */     this.hasRendered = true;
/*  44 */     this.mode = mode;
/*  45 */     this.layerList = blocks.returnBlocks(loc, 56, 56, 29, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
/*  51 */     if (!this.hasRendered)
/*     */       return;  try {
/*  53 */       mapView.setWorld(player.getWorld());
/*  54 */       mapView.setCenterX(player.getLocation().getChunk().getX());
/*  55 */       mapView.setCenterZ(player.getLocation().getChunk().getZ());
/*  56 */       for (int x = 0; x <= 127; x++) {
/*  57 */         for (int j = 0; j <= 127; j++) {
/*  58 */           mapCanvas.setPixel(x, j, (byte)54);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  63 */       BufferedImage image = new BufferedImage(57, 57, 1); int i;
/*  64 */       for (i = 0; i < image.getWidth(); i++) {
/*  65 */         for (int z = 0; z < image.getHeight(); z++) {
/*     */           
/*  67 */           if (z > 52) {
/*  68 */             image.setRGB(i, z, (new Color(101, 151, 213)).getRGB());
/*     */           } else {
/*  70 */             image.setRGB(i, z, (new Color(43, 64, 151)).getRGB());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  76 */       for (Layer l : this.layerList) {
/*  77 */         for (Pixel p : l.getPixelList()) {
/*  78 */           Byte b = Byte.valueOf(p.getColor());
/*  79 */           if (b.byteValue() == 0)
/*     */             continue;  try {
/*  81 */             Color c = MapPalette.getColor(p.getColor());
/*  82 */             image.setRGB(p.getX(), p.getZ(), c.getRGB());
/*  83 */           } catch (Exception exception) {}
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  90 */       image = image.getSubimage(1, 1, 56, 56);
/*     */ 
/*     */       
/*  93 */       image = createResizedCopy(image, this.mode.getWidth(), this.mode.getHeight(), true);
/*     */ 
/*     */       
/*  96 */       i = (127 - image.getWidth()) / 2;
/*  97 */       int y = (127 - image.getHeight()) / 2;
/*     */       
/*  99 */       mapCanvas.drawImage(i, y, image);
/*     */       
/* 101 */       this.hasRendered = false;
/* 102 */     } catch (Exception e) {
/* 103 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
/* 109 */     int imageType = preserveAlpha ? 1 : 2;
/* 110 */     BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
/* 111 */     Graphics2D g = scaledBI.createGraphics();
/* 112 */     if (preserveAlpha) {
/* 113 */       g.setComposite(AlphaComposite.Src);
/*     */     }
/* 115 */     g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
/* 116 */     g.dispose();
/* 117 */     return scaledBI;
/*     */   }
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Camera\Utils\RenderClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */