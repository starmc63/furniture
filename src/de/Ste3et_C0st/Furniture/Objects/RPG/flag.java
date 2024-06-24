/*     */ package de.Ste3et_C0st.Furniture.Objects.RPG;
/*     */ 
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureConfig;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class flag
/*     */   extends Furniture {
/*     */   public flag(ObjectID id) {
/*  17 */     super(id);
/*  18 */     setBlock();
/*  19 */     if (isFinish()) {
/*  20 */       setState(3, (fEntity)getStand());
/*     */       return;
/*     */     } 
/*  23 */     spawn(id.getStartLocation());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setBlock() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawn(Location location) {}
/*     */ 
/*     */   
/*     */   public int getState() {
/*  36 */     for (fEntity stand : getfAsList()) {
/*  37 */       if (stand.getName().startsWith("#FLAG")) {
/*  38 */         return Integer.parseInt(stand.getName().split(":")[1]);
/*     */       }
/*     */     } 
/*  41 */     return 1;
/*     */   }
/*     */   
/*     */   public fContainerEntity getStand() {
/*  45 */     for (fEntity stand : getfAsList()) {
/*  46 */       if (stand instanceof fContainerEntity && 
/*  47 */         stand.getName().startsWith("#FLAG")) {
/*  48 */         return fContainerEntity.class.cast(stand);
/*     */       }
/*     */     } 
/*     */     
/*  52 */     return null;
/*     */   }
/*     */   public void setState(int i, fEntity stand) {
/*     */     Location loc;
/*  56 */     if (i < 1 || i > 3)
/*  57 */       return;  if (stand == null)
/*  58 */       return;  switch (i) {
/*     */       case 3:
/*  60 */         loc = getRelative(getCenter().add(0.0D, 0.7D, 0.0D), getBlockFace(), -0.35D, -0.28D);
/*  61 */         loc.setYaw(getYaw() + 90.0F);
/*  62 */         stand.teleport(loc);
/*  63 */         stand.setName("#FLAG:" + i);
/*     */         break;
/*     */       case 2:
/*  66 */         loc = getRelative(getCenter().add(0.0D, -0.2D, 0.0D), getBlockFace(), -0.35D, -0.28D);
/*  67 */         loc.setYaw(getYaw() + 90.0F);
/*  68 */         stand.teleport(loc);
/*  69 */         stand.setName("#FLAG:" + i);
/*     */         break;
/*     */       case 1:
/*  72 */         loc = getRelative(getCenter().add(0.0D, -0.9D, 0.0D), getBlockFace(), -0.35D, -0.28D);
/*  73 */         loc.setYaw(getYaw() + 90.0F);
/*  74 */         stand.teleport(loc);
/*  75 */         stand.setName("#FLAG:" + i);
/*     */         break;
/*     */     } 
/*  78 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/*  83 */     if (getObjID() == null)
/*  84 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  85 */       return;  if (player == null)
/*  86 */       return;  if (canBuild(player)) {
/*  87 */       destroy(player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/*  93 */     if (getObjID() == null)
/*  94 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  95 */       return;  if (player == null)
/*  96 */       return;  if (canInteract(player)) {
/*  97 */       if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().getType() != null && 
/*  98 */         player.getInventory().getItemInMainHand().getType().name().contains("BANNER")) {
/*  99 */         getStand().setHelmet(player.getInventory().getItemInMainHand()); update();
/* 100 */         if (player.getGameMode().equals(GameMode.CREATIVE) && FurnitureConfig.getFurnitureConfig().useGamemode())
/* 101 */           return;  Integer i = Integer.valueOf(player.getInventory().getHeldItemSlot());
/* 102 */         ItemStack is = player.getInventory().getItemInMainHand();
/* 103 */         is.setAmount(is.getAmount() - 1);
/* 104 */         player.getInventory().setItem(i.intValue(), is);
/* 105 */         player.updateInventory();
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 110 */       int state = getState();
/* 111 */       switch (state) { case 3:
/* 112 */           state = 2; break;
/* 113 */         case 2: state = 1; break;
/* 114 */         case 1: state = 3; break; }
/*     */       
/* 116 */       setState(state, (fEntity)getStand());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\RPG\flag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */