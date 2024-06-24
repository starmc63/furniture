/*     */ package de.Ste3et_C0st.Furniture.Objects.RPG;
/*     */ 
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class Crossbow
/*     */   extends Furniture
/*     */ {
/*     */   public Crossbow(ObjectID id) {
/*  24 */     super(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/*  29 */     if (getObjID() == null)
/*  30 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  31 */       return;  if (player == null)
/*  32 */       return;  if (canInteract(player)) {
/*  33 */       fContainerEntity fContainerEntity = getArmorStand();
/*  34 */       if (fContainerEntity == null)
/*  35 */         return;  ItemStack is = player.getInventory().getItemInMainHand();
/*  36 */       if (is != null && (is.getType().equals(Material.ARROW) || is.getType().equals(Material.SPECTRAL_ARROW) || is.getType().equals(Material.TIPPED_ARROW)) && 
/*  37 */         !hasArrow()) {
/*  38 */         fContainerEntity entity = getArmorStand();
/*  39 */         entity.setItemInMainHand(is.clone());
/*  40 */         update();
/*  41 */         consumeItem(player);
/*     */         
/*     */         return;
/*     */       } 
/*  45 */       if (hasArrow()) {
/*  46 */         spawnArrow(getArrow().getType(), player);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void spawnArrow(Material mat, Player p) {
/*  52 */     Location loc = getRelative(getCenter(), getBlockFace(), 0.0D, 18.0D);
/*  53 */     loc.setYaw(getYaw());
/*  54 */     Vector v = getLaunchVector(getBlockFace());
/*  55 */     if (v == null)
/*  56 */       return;  getWorld().playSound(getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0F, 1.0F);
/*  57 */     Location start = getRelative(getCenter(), getBlockFace(), 0.0D, 0.0D);
/*  58 */     start.setYaw(getYaw());
/*  59 */     start = start.add(0.0D, 1.8D, 0.0D);
/*  60 */     Arrow a = (Arrow)getWorld().spawnEntity(start, EntityType.ARROW);
/*  61 */     a.setCritical(true);
/*  62 */     a.setVelocity(v);
/*  63 */     a.setShooter((ProjectileSource)p);
/*  64 */     fContainerEntity entity = getArmorStand();
/*  65 */     entity.setItemInMainHand(null);
/*  66 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector getLaunchVector(BlockFace face) {
/*  71 */     int l = random(300, 150);
/*  72 */     double h = l / 100.0D;
/*     */     
/*  74 */     Vector v = new Vector(0.0D, 0.0D, h);
/*  75 */     switch (face) { case SOUTH:
/*  76 */         v = new Vector(-v.getY(), v.getY(), -v.getZ()); break;
/*  77 */       case EAST: v = new Vector(-v.getZ(), v.getY(), -v.getY()); break;
/*  78 */       case WEST: v = new Vector(v.getZ(), v.getY(), v.getY());
/*     */         break; }
/*     */     
/*  81 */     return v;
/*     */   }
/*     */   
/*     */   public int random(int max, int min) {
/*  85 */     Random r = new Random();
/*  86 */     int randInt = r.nextInt(max - min) + min;
/*  87 */     return randInt;
/*     */   }
/*     */   
/*     */   private fContainerEntity getArmorStand() {
/*  91 */     for (fEntity stand : getfAsList()) {
/*  92 */       if (stand instanceof fContainerEntity && 
/*  93 */         stand.getName().equalsIgnoreCase("#ARROW#")) {
/*  94 */         return fContainerEntity.class.cast(stand);
/*     */       }
/*     */     } 
/*     */     
/*  98 */     return null;
/*     */   }
/*     */   
/*     */   private ItemStack getArrow() {
/* 102 */     for (fEntity stand : getfAsList()) {
/* 103 */       if (stand.getName().equalsIgnoreCase("#ARROW#") && 
/* 104 */         stand instanceof fContainerEntity) {
/* 105 */         fContainerEntity containerEntity = fContainerEntity.class.cast(stand);
/* 106 */         if (containerEntity.getItemInMainHand() != null && containerEntity.getItemInMainHand().getType() != null && !containerEntity.getItemInMainHand().getType().equals(Material.AIR)) {
/* 107 */           return containerEntity.getItemInMainHand();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 112 */     return null;
/*     */   }
/*     */   
/*     */   private boolean hasArrow() {
/* 116 */     for (fEntity stand : getfAsList()) {
/* 117 */       if (stand.getName().equalsIgnoreCase("#ARROW#") && 
/* 118 */         stand instanceof fContainerEntity) {
/* 119 */         fContainerEntity containerEntity = fContainerEntity.class.cast(stand);
/* 120 */         if (containerEntity.getItemInMainHand() == null || containerEntity.getItemInMainHand().getType() == null || containerEntity.getItemInMainHand().getType().equals(Material.AIR)) {
/* 121 */           return false;
/*     */         }
/* 123 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/* 133 */     if (getObjID() == null)
/* 134 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 135 */       return;  if (player == null)
/* 136 */       return;  if (canBuild(player))
/* 137 */       destroy(player); 
/*     */   }
/*     */   
/*     */   public void spawn(Location location) {}
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\RPG\Crossbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */