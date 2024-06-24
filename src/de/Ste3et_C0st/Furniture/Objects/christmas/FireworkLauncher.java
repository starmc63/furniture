/*     */ package de.Ste3et_C0st.Furniture.Objects.christmas;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.*;
/*     */
/*     */
/*     */
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fArmorStand;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ThreadLocalRandom;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Firework;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.FireworkMeta;
/*     */ import org.bukkit.util.EulerAngle;
/*     */ 
/*     */ public class FireworkLauncher
/*     */   extends Furniture
/*     */ {
/*     */   public FireworkLauncher(ObjectID id) {
/*  30 */     super(id);
/*  31 */     if (isFinish()) {
/*     */       return;
/*     */     }
/*  34 */     spawn(id.getStartLocation());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/*  39 */     if (getObjID() == null)
/*  40 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  41 */       return;  if (player == null)
/*  42 */       return;  if (canBuild(player)) {
/*  43 */       for (fEntity packet : getManager().getfArmorStandByObjectID(getObjID())) {
/*  44 */         if (packet.getName().equalsIgnoreCase("#FIREWORK#") && 
/*  45 */           packet instanceof fContainerEntity) {
/*  46 */           fContainerEntity container = fContainerEntity.class.cast(packet);
/*  47 */           if (container.getInventory().getItemInMainHand() != null && !container.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
/*  48 */             ItemStack is = container.getInventory().getItemInMainHand();
/*  49 */             Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(), task->getWorld().dropItem(getLocation(), is));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  54 */       destroy(player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
    if (player == null)return;
    Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),player.getLocation(),task -> {
        /*  60 */     if (getObjID() == null)
            /*  61 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
            /*  62 */       return;    if (canInteract(player)) {
            /*  64 */       fContainerEntity stand = null;
            /*  65 */       for (fEntity st : getfAsList()) {
                /*  66 */         if (st.getName().equalsIgnoreCase("#FIREWORK#")) {
                    /*  67 */           stand = fContainerEntity.class.cast(st);
                    /*     */           break;
                    /*     */         }
                /*     */       }
            /*  71 */       if (stand == null)
                /*  72 */         return;  if (player.getInventory().getItemInMainHand() != null &&
                    /*  73 */         player.getInventory().getItemInMainHand().getType() != null &&
                    /*  74 */         player.getInventory().getItemInMainHand().getType().equals(Material.valueOf(FurnitureHook.isNewVersion() ? "FIREWORK_ROCKET" : "FIREWORK"))) {
                /*  75 */         drop(stand);
                /*  76 */         setItem(stand, player.getInventory().getItemInMainHand());
                /*     */
                /*  78 */         Bukkit.getScheduler().runTaskLater(getPlugin(), new Runnable()
                        /*     */             {
                    /*     */               public void run()
                    /*     */               {
                        /*  82 */                 FireworkLauncher.this.update();
                        /*     */               }
                    /*     */             },  5L);
                /*     */
                /*     */
                /*  87 */         if (player.getGameMode().equals(GameMode.CREATIVE) && FurnitureConfig.getFurnitureConfig().useGamemode())
                    /*  88 */           return;  Integer i = Integer.valueOf(player.getInventory().getHeldItemSlot());
                /*  89 */         ItemStack is = player.getInventory().getItemInMainHand();
                /*  90 */         is.setAmount(is.getAmount() - 1);
                /*  91 */         player.getInventory().setItem(i.intValue(), is);
                /*  92 */         player.updateInventory();
                /*     */
                /*     */
                /*     */         return;
                /*     */       }
            /*     */
            /*  98 */       if (canLaunch(stand)) {
                /*  99 */         Firework fw = (Firework)getWorld().spawnEntity(getCenter(), EntityType.FIREWORK);
                /* 100 */         FireworkMeta meta = (FireworkMeta)stand.getItemInMainHand().getItemMeta();
                /* 101 */         fw.setFireworkMeta(meta);
                /* 102 */         setItem(stand, new ItemStack(Material.AIR));
                /*     */       }
            /*     */     }
    });
/*     */   }
/*     */   
/*     */   public double getRandom(double min, double max) {
/* 108 */     return ThreadLocalRandom.current().nextDouble(min, max);
/*     */   }
/*     */   
/*     */   public boolean canLaunch(fContainerEntity stand) {
/* 112 */     if (stand.getItemInMainHand() != null && 
/* 113 */       stand.getItemInMainHand().getType().equals(Material.valueOf(FurnitureHook.isNewVersion() ? "FIREWORK_ROCKET" : "FIREWORK"))) {
/* 114 */       return true;
/*     */     }
/*     */     
/* 117 */     return false;
/*     */   }
/*     */   
/*     */   public void setItem(fContainerEntity stand, ItemStack is) {
/* 121 */     ItemStack stack = is.clone();
/* 122 */     stack.setAmount(1);
/* 123 */     stand.setItemInMainHand(stack);
/* 124 */     update();
/*     */   }
/*     */   
/*     */   public void drop(fContainerEntity stand) {
/* 128 */     if (stand.getItemInMainHand() != null) {
/* 129 */       Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getCenter(),task->getWorld().dropItem(getCenter(), stand.getItemInMainHand()));
/* 130 */       stand.setItemInMainHand(new ItemStack(Material.AIR));
/* 131 */       update();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawn(Location arg0) {
/* 137 */     List<fArmorStand> asList = new ArrayList<>();
/*     */     
/* 139 */     double d = -1.2D;
/* 140 */     fArmorStand stand = spawnArmorStand(getRelative(getCenter(), getBlockFace(), 0.5D, 0.4D).add(0.0D, d + 0.2D, 0.0D));
/* 141 */     stand.setItemInMainHand(new ItemStack(Material.STICK));
/* 142 */     stand.setRightArmPose(getLutil().degresstoRad(new EulerAngle(80.0D, 0.0D, 0.0D)));
/* 143 */     asList.add(stand);
/*     */     
/* 145 */     stand = spawnArmorStand(getRelative(getCenter(), getBlockFace(), 0.5D, 0.4D).add(0.0D, d + 0.9D, 0.0D));
/* 146 */     stand.setItemInMainHand(new ItemStack(Material.STICK));
/* 147 */     stand.setRightArmPose(getLutil().degresstoRad(new EulerAngle(80.0D, 0.0D, 0.0D)));
/* 148 */     asList.add(stand);
/*     */     
/* 150 */     stand = spawnArmorStand(getRelative(getCenter(), getBlockFace(), 0.05D, 0.85D).add(0.0D, d + 0.6D, 0.0D));
/* 151 */     stand.setName("#FIREWORK#");
/* 152 */     stand.setRightArmPose(getLutil().degresstoRad(new EulerAngle(-90.0D, 90.0D, 0.0D)));
/* 153 */     asList.add(stand);
/*     */     
/* 155 */     for (fArmorStand pack : asList) {
/* 156 */       pack.setInvisible(true);
/* 157 */       pack.setBasePlate(false);
/*     */     } 
/*     */     
/* 160 */     send();
/* 161 */     Bukkit.getPluginManager().registerEvents((Listener)this, getPlugin());
/*     */   }
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\christmas\FireworkLauncher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */