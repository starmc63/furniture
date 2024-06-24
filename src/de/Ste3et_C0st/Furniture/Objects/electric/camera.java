/*     */ package de.Ste3et_C0st.Furniture.Objects.electric;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Camera.Utils.RenderClass;
/*     */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*     */ import de.Ste3et_C0st.FurnitureLib.Utilitis.LocationUtil;
/*     */ import de.Ste3et_C0st.FurnitureLib.Utilitis.Relative;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.meta.MapMeta;
/*     */ import org.bukkit.map.MapRenderer;
/*     */ import org.bukkit.map.MapView;
/*     */ 
/*     */ public class camera
/*     */   extends Furniture
/*     */ {
/*  23 */   private fEntity entity = null; private fEntity entity2 = null;
/*  24 */   private String zoom = "#ZOOM0#";
/*  25 */   private RenderClass.ScaleMode mode = RenderClass.ScaleMode.NORMAL;
/*     */   
/*     */   public camera(ObjectID id) {
/*  28 */     super(id);
/*  29 */     boolean b = false;
/*  30 */     for (fEntity stand : id.getPacketList()) {
/*  31 */       if (stand.isCustomNameVisible()) {
/*  32 */         b = true;
/*  33 */         stand.setNameVasibility(false);
/*     */       } 
/*  35 */       if (stand.getCustomName().startsWith("#ZOOM")) {
/*  36 */         this.entity2 = stand;
/*  37 */         this.zoom = stand.getCustomName();
/*     */       } 
/*     */       
/*  40 */       if (stand instanceof fContainerEntity) {
/*  41 */         fContainerEntity containerEntity = fContainerEntity.class.cast(stand);
/*  42 */         if (containerEntity.getItemInMainHand() != null && 
/*  43 */           containerEntity.getItemInMainHand().getType().equals(Material.TRIPWIRE_HOOK)) {
/*  44 */           this.entity = stand;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  49 */     if (b) update(); 
/*     */   }
/*     */   
/*     */   public void setZoom() {
/*  53 */     if (this.entity == null)
/*  54 */       return;  Relative r = new Relative(getCenter(), -0.07D, 0.0625D, -0.13D, getBlockFace().getOppositeFace());
/*  55 */     Location loc = r.getSecondLocation();
/*  56 */     if (this.zoom.equalsIgnoreCase("#ZOOM1#")) {
/*  57 */       loc = r.getSecondLocation().subtract(0.0D, 0.15D, 0.0D);
/*  58 */     } else if (this.zoom.equalsIgnoreCase("#ZOOM2#")) {
/*  59 */       loc = r.getSecondLocation().subtract(0.0D, 0.3D, 0.0D);
/*  60 */     } else if (this.zoom.equalsIgnoreCase("#ZOOM3#")) {
/*  61 */       loc = r.getSecondLocation().subtract(0.0D, 0.4D, 0.0D);
/*     */     } 
/*  63 */     loc.setYaw(this.entity.getLocation().getYaw());
/*  64 */     this.entity.teleport(loc);
/*  65 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/*  70 */     if (getObjID() == null)
/*  71 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  72 */       return;  if (player == null)
/*  73 */       return;  if (canBuild(player)) {
/*  74 */       destroy(player);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/*  81 */     if (getObjID() == null)
/*  82 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  83 */       return;  if (player == null)
/*  84 */       return;  Location pLocation = getLutil().getRelative(player.getLocation().getBlock().getLocation(), getBlockFace(), -1.0D, 0.0D).clone();
/*  85 */     Location locCopy = getLocation().getBlock().getLocation().clone();
/*  86 */     pLocation.setYaw(locCopy.getYaw());
/*  87 */     if (pLocation.equals(locCopy) && 
/*  88 */       LocationUtil.yawToFace(player.getLocation().getYaw()).getOppositeFace().equals(getBlockFace())) {
/*  89 */       if (canInteract(player)) {
/*  90 */         if (!player.getInventory().getItemInMainHand().getType().equals(Material.valueOf(FurnitureHook.isNewVersion() ? "FILLED_MAP" : "MAP"))) {
/*  91 */           if (this.entity == null || this.entity2 == null)
/*  92 */             return;  if (this.zoom.equalsIgnoreCase("#ZOOM0#")) {
/*  93 */             this.mode = RenderClass.ScaleMode.FAR;
/*  94 */             this.zoom = "#ZOOM1#";
/*  95 */           } else if (this.zoom.equalsIgnoreCase("#ZOOM1#")) {
/*  96 */             this.mode = RenderClass.ScaleMode.FAHRTEST;
/*  97 */             this.zoom = "#ZOOM2#";
/*  98 */           } else if (this.zoom.equalsIgnoreCase("#ZOOM2#")) {
/*  99 */             this.mode = RenderClass.ScaleMode.COMPLETE;
/* 100 */             this.zoom = "#ZOOM3#";
/* 101 */           } else if (this.zoom.equalsIgnoreCase("#ZOOM3#")) {
/* 102 */             this.mode = RenderClass.ScaleMode.NORMAL;
/* 103 */             this.zoom = "#ZOOM0#";
/*     */           } 
/* 105 */           this.entity2.setName(this.zoom);
/* 106 */           setZoom();
/* 107 */           update();
/*     */           return;
/*     */         } 
/* 110 */       } else if (!player.getInventory().getItemInMainHand().getType().equals(Material.MAP)) {
/*     */         return;
/*     */       } 
/* 113 */       MapMeta meta = (MapMeta)player.getInventory().getItemInMainHand().getItemMeta();
/* 114 */       if (FurnitureHook.isNewVersion())
/* 115 */       { if (meta.hasMapId()) {
/* 116 */           MapView view = Bukkit.getMap((short)meta.getMapId());
/* 117 */           Location l = getLocation().clone();
/* 118 */           l.setYaw(getLutil().FaceToYaw(getBlockFace().getOppositeFace()));
/* 119 */           view.getRenderers().clear(); 
/* 120 */           try { view.addRenderer((MapRenderer)new RenderClass(l, this.mode)); } catch (Exception ex) { ex.printStackTrace(); }
/*     */         
/*     */         }  }
/* 123 */       else { MapView view = Bukkit.getMap(player.getInventory().getItemInMainHand().getDurability());
/* 124 */         Location l = getLocation().clone();
/* 125 */         l.setYaw(getLutil().FaceToYaw(getBlockFace().getOppositeFace()));
/* 126 */         view.getRenderers().clear(); 
/* 127 */         try { view.addRenderer((MapRenderer)new RenderClass(l, this.mode)); } catch (Exception ex) { ex.printStackTrace(); }
/*     */          }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public void spawn(Location location) {}
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\electric\camera.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */