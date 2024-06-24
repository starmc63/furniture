/*     */ package de.Ste3et_C0st.Furniture.Objects.garden;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fArmorStand;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.EulerAngle;
/*     */ 
/*     */ 
/*     */ public class sunshade
/*     */   extends Furniture
/*     */ {
/*     */   public boolean isRunning = false;
/*     */   Integer timer;
/*     */   
/*     */   public sunshade(ObjectID id) {
/*  25 */     super(id);
/*     */   }
/*     */   
/*     */   private boolean isOpen(fArmorStand packet) {
/*  29 */     if (packet.getPose(Type.BodyPart.HEAD).getX() < -1.85D)
/*  30 */       return false; 
/*  31 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isClose(fArmorStand packet) {
/*  35 */     if (packet.getPose(Type.BodyPart.HEAD).getX() > -3.054D)
/*  36 */       return false; 
/*  37 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/*  42 */     if (getObjID() == null)
/*  43 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  44 */       return;  if (player == null)
/*  45 */       return;  ItemStack is = player.getInventory().getItemInMainHand();
/*  46 */     if (is.getType().name().contains("BANNER")) {
/*  47 */       if (canBuild(player)) {
/*  48 */         for (fEntity packet : getfAsList()) {
/*  49 */           if (packet instanceof fContainerEntity) {
/*  50 */             fContainerEntity containerEntity = fContainerEntity.class.cast(packet);
/*  51 */             if (containerEntity.getInventory().getHelmet() != null && containerEntity.getInventory().getHelmet().getType().name().contains("BANNER")) {
/*  52 */               containerEntity.getInventory().setHelmet(is.clone()); continue;
/*  53 */             }  if (containerEntity.getInventory().getHelmet() != null && containerEntity.getInventory().getHelmet().getType().name().contains("CARPET")) {
/*  54 */               ItemStack item = new ItemStack(Material.valueOf(FurnitureHook.isNewVersion() ? "WHITE_CARPET" : "CARPET"));
/*  55 */               containerEntity.getInventory().setHelmet(item);
/*     */             } 
/*     */           } 
/*     */         } 
/*  59 */         update();
/*  60 */         consumeItem(player);
/*     */       } 
/*     */       return;
/*     */     } 
/*  64 */     if (canInteract(player)) {
/*  65 */       if (!isOpen()) {
/*  66 */         open();
/*     */       } else {
/*  68 */         close();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/*  76 */     if (getObjID() == null)
/*  77 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  78 */       return;  if (player == null)
/*  79 */       return;  if (canBuild(player)) {
/*  80 */       destroy(player);
/*  81 */       if (this.isRunning) stopTimer(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void open() {
/*  86 */     if (this.isRunning)
/*  87 */       return;  this.isRunning = true;
/*  88 */     this.timer = Integer.valueOf(Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable()
/*     */           {
/*     */             public void run() {
/*  91 */               for (fEntity packet : sunshade.this.getfAsList()) {
/*  92 */                 if (packet.getName().startsWith("#ELEMENT#")) {
/*  93 */                   if (!sunshade.this.isOpen((fArmorStand)packet)) {
/*  94 */                     Double x = Double.valueOf(((fArmorStand)packet).getPose(Type.BodyPart.HEAD).getX());
/*  95 */                     ((fArmorStand)packet).setPose(new EulerAngle(x.doubleValue() + 0.02D, 0.0D, 0.0D), Type.BodyPart.HEAD); continue;
/*     */                   } 
/*  97 */                   sunshade.this.stopTimer();
/*     */                   
/*     */                   return;
/*     */                 } 
/*     */               } 
/* 102 */               sunshade.this.update();
/*     */             }
    }, 0, 1));
}
/*     */   
/*     */   private void close() {
/* 108 */     if (this.isRunning)
/* 109 */       return;  this.isRunning = true;
/* 110 */     this.timer = Integer.valueOf(Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable()
/*     */           {
/*     */             public void run() {
/* 113 */               for (fEntity packet : sunshade.this.getfAsList()) {
/* 114 */                 if (packet.getName().startsWith("#ELEMENT#")) {
/* 115 */                   if (!sunshade.this.isClose((fArmorStand)packet)) {
/* 116 */                     Double x = Double.valueOf(((fArmorStand)packet).getPose(Type.BodyPart.HEAD).getX());
/* 117 */                     ((fArmorStand)packet).setPose(new EulerAngle(x.doubleValue() - 0.02D, 0.0D, 0.0D), Type.BodyPart.HEAD); continue;
/*     */                   } 
/* 119 */                   sunshade.this.stopTimer();
/*     */                   
/*     */                   return;
/*     */                 } 
/*     */               } 
/* 124 */               sunshade.this.update();
/*     */            }
    }, 0, 1));
}
/*     */   
/*     */   private boolean isOpen() {
/* 130 */     for (fEntity packet : getfAsList()) {
/* 131 */       if (packet.getName().startsWith("#ELEMENT#") && (
/* 132 */         (fArmorStand)packet).getPose(Type.BodyPart.HEAD).getX() < -1.85D) {
/* 133 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 137 */     return true;
/*     */   }
/*     */   
/*     */   private void stopTimer() {
/* 141 */     if (this.timer != null) {
/* 142 */       Bukkit.getScheduler().cancelTask(this.timer.intValue());
/* 143 */       this.timer = null;
/* 144 */       this.isRunning = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void spawn(Location location) {}
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\garden\sunshade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */