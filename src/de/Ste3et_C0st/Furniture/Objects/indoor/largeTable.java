/*     */ package de.Ste3et_C0st.Furniture.Objects.indoor;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.*;
/*     */
/*     */
/*     */
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import org.bukkit.Bukkit;
import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class largeTable
/*     */   extends Furniture
/*     */ {
/*  26 */   private List<Integer> tellerIDs = new ArrayList<>();
/*     */   
/*     */   public largeTable(ObjectID id) {
/*  29 */     super(id);
/*  30 */     for (fEntity packet : getfAsList()) {
/*  31 */       if (packet.getName().startsWith("#TELLER")) {
/*  32 */         this.tellerIDs.add(Integer.valueOf(packet.getEntityID()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTeller(HashMap<Integer, ItemStack> itemList) {
/*  39 */     int i = 0;
/*  40 */     for (Integer id : this.tellerIDs) {
/*  41 */       fEntity as = getManager().getByArmorStandID(getWorld(), id.intValue());
/*  42 */       if (as instanceof fContainerEntity) {
/*  43 */         ((fContainerEntity)fContainerEntity.class.cast(as)).getInventory().setItemInMainHand(itemList.get(Integer.valueOf(i)));
/*     */       }
/*  45 */       i++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/*  51 */     if (getObjID() == null)
/*  52 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  53 */       return;  if (player == null)
/*  54 */       return;  if (canBuild(player)) {
/*  55 */       for (Integer id : this.tellerIDs) {
/*  56 */         fEntity asp = getManager().getByArmorStandID(getWorld(), id.intValue());
/*  57 */         if (asp != null && asp instanceof fContainerEntity) {
/*  58 */           fContainerEntity container = fContainerEntity.class.cast(asp);
/*  59 */           if (container.getInventory().getItemInMainHand() != null && 
/*  60 */             asp.getName().startsWith("#TELLER")) {
/*  61 */
                    Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),scheduledTask -> getWorld().dropItem(getLocation(), container.getInventory().getItemInMainHand()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*  66 */       destroy(player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/*  72 */     if (getObjID() == null)
/*  73 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  74 */       return;  if (player == null)
/*  75 */       return;  if (canInteract(player)) {
/*  76 */       if (FurnitureHook.isNewVersion()) {
/*  77 */         if (Type.DyeColor.getDyeColor(player.getInventory().getItemInMainHand().getType()) != null) {
/*  78 */           getLib().getColorManager().color(player, canBuild(player), "STAINED_GLASS_PANE", getObjID(), Type.ColorType.BLOCK, 3);
/*  79 */           update();
/*     */           
/*     */           return;
/*     */         } 
/*  83 */       } else if (player.getInventory().getItemInMainHand().getType().equals(Material.valueOf("INK_SACK"))) {
/*  84 */         getLib().getColorManager().color(player, canBuild(player), "STAINED_GLASS_PANE", getObjID(), Type.ColorType.BLOCK, 3);
/*  85 */         update();
/*     */
/*     */         return;
/*     */       } 
/*  89 */       setTeller(player, player.getInventory().getItemInMainHand());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTeller(Player player, ItemStack is) {
/*  95 */     Objects.requireNonNull(fContainerEntity.class); Objects.requireNonNull(fContainerEntity.class);
/*  96 */     fContainerEntity as = getfAsList().stream().filter(fContainerEntity.class::isInstance).map(fContainerEntity.class::cast).filter(entity -> entity.getName().startsWith("#TELLER")).sorted(Comparator.comparingDouble(p1 -> p1.getLocation().distance(player.getLocation()))).findFirst().orElse(null);
/*  97 */     if (as != null) {
/*  98 */       if (as.getInventory().getItemInMainHand() != null && as.getInventory().getItemInMainHand().equals(is))
/*  99 */         return;  if (as.getInventory().getItemInMainHand() != null && !as.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
/* 100 */         ItemStack item = as.getInventory().getItemInMainHand();
/* 101 */         item.setAmount(1);
/* 102 */         Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),scheduledTask ->as.getLocation().getWorld().dropItem(as.getLocation(), item));
/*     */       } 
/*     */       
/* 105 */       ItemStack IS = is.clone();
/* 106 */       if (IS.getAmount() <= 0) {
/* 107 */         IS.setAmount(0);
/*     */       } else {
/* 109 */         IS.setAmount(1);
/*     */       } 
/* 111 */       as.getInventory().setItemInMainHand(IS);
/*     */       
/* 113 */       update();
/*     */       
/* 115 */       if (player.getGameMode().equals(GameMode.CREATIVE) && FurnitureConfig.getFurnitureConfig().useGamemode())
/* 116 */         return;  Integer i = Integer.valueOf(player.getInventory().getHeldItemSlot());
/* 117 */       ItemStack itemstack = is.clone();
/* 118 */       itemstack.setAmount(itemstack.getAmount() - 1);
/* 119 */       player.getInventory().setItem(i.intValue(), itemstack);
/* 120 */       player.updateInventory();
/*     */     } 
/*     */   }
/*     */   
/*     */   public HashMap<Integer, ItemStack> getTeller() {
/* 125 */     HashMap<Integer, ItemStack> teller = new HashMap<>();
/* 126 */     for (Integer id : this.tellerIDs) {
/*     */       try {
/* 128 */         fEntity as = getManager().getByArmorStandID(getWorld(), id.intValue());
/* 129 */         if (as instanceof fContainerEntity) {
/* 130 */           teller.put(Integer.valueOf(teller.size()), ((fContainerEntity)fContainerEntity.class.cast(as)).getInventory().getItemInMainHand());
/*     */         }
/* 132 */       } catch (Exception e) {
/* 133 */         teller.put(Integer.valueOf(teller.size()), new ItemStack(Material.AIR));
/*     */       } 
/*     */     } 
/* 136 */     return teller;
/*     */   }
/*     */   
/*     */   public void spawn(Location location) {}
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\indoor\largeTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */