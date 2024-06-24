/*     */ package de.Ste3et_C0st.Furniture.Objects.electric;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class streetlamp
/*     */   extends Furniture {
/*     */   private Location light;
/*     */   private Location redstoneBlock;
/*  23 */   public static HashMap<Location, streetlamp> locationSet = new HashMap<>();
/*     */   
/*     */   public streetlamp(ObjectID id) {
/*  26 */     super(id);
/*  27 */     setBlock();
/*     */     
/*  29 */     this.light = getLutil().getRelative(getLocation(), getBlockFace(), -1.0D, 0.0D);
/*  30 */     this.redstoneBlock = getCenter().getBlock().getLocation();
/*  31 */     if (!locationSet.containsKey(this.redstoneBlock)) locationSet.put(this.redstoneBlock, this); 
/*  32 */     spawn(id.getStartLocation());
/*     */   }
/*     */   
/*     */   public void spawn(Location location) {}
/*     */   
/*     */   private void setBlock() {
/*  38 */     List<Location> blockLocation = new ArrayList<>();
                /*  39 */
                Location location = getLocation();
                /*  40 */
                location.setY(location.getY() - 1.0D);
                /*  41 */
                for (int i = 0; i <= 3; i++) {
                    /*  42 */
                    location.setY(location.getY() + 1.0D);
                    /*  43 */
                    blockLocation.add(location);
                    /*  44 */
                    if (!getObjID().isFinish()) {
                        /*  45 */
                        Block block = location.getBlock();
                        /*  46 */
                        Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),scheduledTask ->
                        block.setType(Material.BARRIER));
                        /*     */
                    }
                    /*     */
                    /*  49 */
                    if (i == 3) {
                        /*  50 */
                        Location loc = getLutil().getRelative(location, getBlockFace(), -1.0D, 0.0D);
                        /*  51 */
                        if (!getObjID().isFinish()) {
                            /*  52 */
                            Block blocks = loc.getBlock();
                            /*  53 */
                            Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),scheduledTask ->
                            blocks.setType(Material.BARRIER));
                            /*     */
                        }
                        /*  55 */
                        blockLocation.add(loc);
                        /*     */
                    }
                    /*     */
                }
                /*  58 */
                getObjID().addBlockLocations(blockLocation);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/*  63 */     if (getObjID() == null)
/*  64 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  65 */       return;  if (player == null)
/*  66 */       return;  if (canBuild(player)) {
/*  67 */       FurnitureLib.getInstance().getLightManager().removeLight(this.light);
/*  68 */       destroy(player);
/*  69 */       if (locationSet.containsKey(this.redstoneBlock)) locationSet.remove(this.redstoneBlock);
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onClick(Player player) {
/*  75 */     if (getObjID() == null)
/*  76 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  77 */       return;  if (player == null)
/*  78 */       return;  if (!canInteract(player))
/*  79 */       return;  if (isOn()) {
/*  80 */       setLight(Boolean.valueOf(false));
/*     */     } else {
/*  82 */       setLight(Boolean.valueOf(true));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setLight(Boolean b) {
/*  87 */     if (!b.booleanValue()) {
/*  88 */       fContainerEntity fContainerEntity = getPacket();
/*  89 */       if (fContainerEntity == null)
/*  90 */         return;  fContainerEntity.getInventory().setHelmet(new ItemStack(Material.valueOf(FurnitureHook.isNewVersion() ? "REDSTONE_TORCH" : "REDSTONE_LAMP_OFF")));
/*  91 */       getManager().updateFurniture(getObjID());
/*  92 */       FurnitureLib.getInstance().getLightManager().removeLight(this.light);
/*     */       return;
/*     */     } 
/*  95 */     fContainerEntity packet = getPacket();
/*  96 */     if (packet == null)
/*  97 */       return;  packet.getInventory().setHelmet(new ItemStack(Material.GLOWSTONE));
/*  98 */     getManager().updateFurniture(getObjID());
/*  99 */     FurnitureLib.getInstance().getLightManager().addLight(this.light, Integer.valueOf(15));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private fContainerEntity getPacket() {
/* 105 */     for (fEntity packet : getManager().getfArmorStandByObjectID(getObjID())) {
/* 106 */       if (packet instanceof fContainerEntity && packet.getName().equalsIgnoreCase("#LAMP#")) {
/* 107 */         return fContainerEntity.class.cast(packet);
/*     */       }
/*     */     } 
/* 110 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isOn() {
/* 114 */     for (fEntity as : getManager().getfArmorStandByObjectID(getObjID())) {
/* 115 */       if (as instanceof fContainerEntity && as.getName().equalsIgnoreCase("#LAMP#")) {
/* 116 */         fContainerEntity entity = fContainerEntity.class.cast(as);
/* 117 */         switch (entity.getInventory().getHelmet().getType()) { case GLOWSTONE:
/* 118 */             return true; }
/* 119 */          return false;
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\electric\streetlamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */