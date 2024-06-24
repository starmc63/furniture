/*     */ package de.Ste3et_C0st.Furniture.Objects.RPG;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*     */ import de.Ste3et_C0st.Furniture.Main.main;
/*     */ import de.Ste3et_C0st.FurnitureLib.Utilitis.LocationUtil;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fArmorStand;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.stream.Collectors;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.util.EulerAngle;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Guillotine
/*     */   extends Furniture
/*     */   implements Listener
/*     */ {
/*  39 */   Boolean soundPlaying = Boolean.valueOf(false); fArmorStand packet1; fArmorStand packet2; fArmorStand packet3; Inventory invI; Boolean isFinish = Boolean.valueOf(false);
/*     */   Inventory invII;
/*     */   Inventory invIII;
/*     */   Integer timer;
/*     */   Player p;
/*  44 */   List<Integer> intList = Arrays.asList(new Integer[] { Integer.valueOf(10), Integer.valueOf(16), Integer.valueOf(19), Integer.valueOf(28), Integer.valueOf(37), Integer.valueOf(43) }); private static List<Material> matList; private static List<Material> matListI; private static List<Material> matListII;
/*     */   private static List<Material> matListIII;
/*     */   
/*     */   static {
/*  48 */     List<Material> listMaterial = Arrays.asList(Material.values());
/*  49 */     matList = (List<Material>)listMaterial.stream().filter(mat -> (mat.name().contains("SWORD") || mat.name().contains("_AXE") || mat.name().contains("HOE"))).collect(Collectors.toList());
/*  50 */     matListI = (List<Material>)listMaterial.stream().filter(mat -> mat.name().contains("CHESTPLATE")).collect(Collectors.toList());
/*  51 */     matListII = (List<Material>)listMaterial.stream().filter(mat -> mat.name().contains("LEGGINGS")).collect(Collectors.toList());
/*  52 */     matListIII = (List<Material>)listMaterial.stream().filter(mat -> mat.name().contains("BOOTS")).collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  57 */   List<fEntity> armorStandList = new ArrayList<>();
/*  58 */   ItemStack pane = FurnitureHook.isNewVersion() ? new ItemStack(Material.valueOf("BLACK_STAINED_GLASS_PANE")) : new ItemStack(Material.valueOf("STAINED_GLASS_PANE"), 1, (short)15);
/*     */   public Guillotine(ObjectID id) {
/*  60 */     super(id);
/*  61 */     if (isFinish()) {
/*  62 */       setDefault();
/*  63 */       initializeInventory();
/*  64 */       Bukkit.getPluginManager().registerEvents(this, main.getInstance());
/*     */       
/*     */       return;
/*     */     } 
/*  68 */     getfAsList().stream().filter(entity -> entity.getCustomName().startsWith("iron")).forEach(entity -> {
/*     */           String[] a = entity.getCustomName().split(":");
/*     */           
/*     */           String b = a[0] + ":" + entity.getLocation().getX() + ":" + entity.getLocation().getY() + ":" + entity.getLocation().getZ();
/*     */           entity.setCustomName(b);
/*     */         });
/*  74 */     setDefault();
/*  75 */     initializeInventory();
/*  76 */     Bukkit.getPluginManager().registerEvents(this, getPlugin());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeInventory() {
/*  82 */     ItemMeta im = this.pane.getItemMeta();
/*  83 */     im.setDisplayName("§c");
/*  84 */     this.pane.setItemMeta(im);
/*     */ 
/*     */     
/*  87 */     String s = getObjID().getProjectOBJ().getCraftingFile().getRecipe().getResult().getItemMeta().getDisplayName();
/*  88 */     this.invI = Bukkit.createInventory(null, 9, s + "I");
/*  89 */     this.invII = Bukkit.createInventory(null, 54, s + "II");
/*  90 */     this.invIII = Bukkit.createInventory(null, 54, s + "III");
/*     */   }
/*     */   
/*     */   public void forceCloseInventory() {
/*  94 */     if (Objects.nonNull(this.invI)) this.invI.getViewers().forEach(HumanEntity::closeInventory); 
/*  95 */     if (Objects.nonNull(this.invII)) this.invI.getViewers().forEach(HumanEntity::closeInventory); 
/*  96 */     if (Objects.nonNull(this.invIII)) this.invI.getViewers().forEach(HumanEntity::closeInventory); 
/*     */   }
/*     */   
/*     */   private void setDefault() {
/* 100 */     this.armorStandList.clear();
/*     */     
/* 102 */     this.packet1 = (fArmorStand)entityByCustomName("#Executioner#");
/* 103 */     this.packet3 = (fArmorStand)entityByCustomName("#Head#");
/* 104 */     this.packet2 = (fArmorStand) getfAsList().stream().filter(entity -> entity.getCustomName().startsWith("#Oblation#")).findFirst().orElse(null);
/*     */     
/* 106 */     if (!isFinish()) {
/* 107 */       Location oblationLocation = this.packet2.getLocation();
/* 108 */       this.packet2.setName("#Oblation#:" + oblationLocation.getX() + ":" + oblationLocation.getY() + ":" + oblationLocation.getZ());
/*     */     } 
/*     */     
/* 111 */     getfAsList().stream().filter(entity -> entity.getCustomName().startsWith("iron")).forEach(entity -> {
/*     */           this.armorStandList.add(entity);
/*     */           
/*     */           entity.teleport(getStartLocation(entity.getCustomName()));
/*     */         });
/* 116 */     if (this.packet3 != null && 
/* 117 */       this.packet3.getHelmet() != null) {
/* 118 */       this.packet2.setHelmet(this.packet3.getHelmet());
/* 119 */       this.packet3.setHelmet(new ItemStack(Material.AIR));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 124 */     this.soundPlaying = Boolean.valueOf(false);
/* 125 */     if (this.packet1 != null) {
/* 126 */       this.packet1.setPose(getLutil().degresstoRad(new EulerAngle(190.0D, 0.0D, 329.0D)), Type.BodyPart.RIGHT_ARM);
/* 127 */       this.packet1.setLeftArmPose(Type.BodyPart.LEFT_ARM.getDefAngle());
/*     */     } 
/*     */     
/* 130 */     if (this.packet2 != null) {
/* 131 */       Location loc13 = getStartLocation(this.packet2.getName());
/* 132 */       this.packet2.teleport(loc13);
/* 133 */       this.packet2.setPose(getLutil().degresstoRad(new EulerAngle(40.0D, 0.0D, 320.0D)), Type.BodyPart.RIGHT_ARM);
/* 134 */       this.packet2.setPose(getLutil().degresstoRad(new EulerAngle(33.0D, 0.0D, 41.0D)), Type.BodyPart.LEFT_ARM);
/* 135 */       this.packet2.setPose(getLutil().degresstoRad(new EulerAngle(40.0D, 25.0D, 0.0D)), Type.BodyPart.HEAD);
/*     */     } 
/* 137 */     this.timer = Integer.valueOf(Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable() {
/* 138 */             int x = 0;
/*     */             
/*     */             public void run() {
/* 141 */               if (this.x != 3) {
/* 142 */                 Guillotine.this.update();
/* 143 */                 this.x++;
/*     */               } else {
/* 145 */                 Guillotine.this.stopTimer();
/*     */               } 
/*     */             }
/*     */           },  0L, 10L));
/* 149 */     this.isFinish = Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   private Location getStartLocation(String s) {
/* 153 */     String[] split = s.split(":");
/* 154 */     double x = Double.parseDouble(split[1]);
/* 155 */     double y = Double.parseDouble(split[2]);
/* 156 */     double z = Double.parseDouble(split[3]);
/* 157 */     Location loc = new Location(getWorld(), x, y, z);
/* 158 */     loc.setYaw(getLutil().FaceToYaw(getBlockFace()));
/* 159 */     return loc;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawn(Location loc) {}
/*     */ 
/*     */   
/*     */   private boolean canDrop(ItemStack stack) {
/* 168 */     if (stack == null) return false; 
/* 169 */     if (stack.getType().equals(Material.AIR)) return false; 
/* 170 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/* 175 */     if (getObjID() == null)
/* 176 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 177 */       return;  if (player == null)
/* 178 */       return;  if (canBuild(player)) {
/* 179 */       if (canDrop(this.packet1.getHelmet())) player.getScheduler().run(FurnitureLib.getInstance(), task->getWorld().dropItem(getLocation(), this.packet1.getHelmet()),null);
        /* 180 */       if (canDrop(this.packet1.getChestPlate())) player.getScheduler().run(FurnitureLib.getInstance(),task->getWorld().dropItem(getLocation(), this.packet1.getChestPlate()),null);
        /* 181 */       if (canDrop(this.packet1.getLeggings())) player.getScheduler().run(FurnitureLib.getInstance(),task->getWorld().dropItem(getLocation(), this.packet1.getLeggings()),null);
        /* 182 */       if (canDrop(this.packet1.getBoots())) player.getScheduler().run(FurnitureLib.getInstance(),task->getWorld().dropItem(getLocation(), this.packet1.getBoots()),null);
        /* 183 */       if (canDrop(this.packet1.getItemInMainHand())) player.getScheduler().run(FurnitureLib.getInstance(),task->getWorld().dropItem(getLocation(), this.packet1.getItemInMainHand()),null);
        /* 184 */       if (canDrop(this.packet2.getHelmet())) player.getScheduler().run(FurnitureLib.getInstance(),task->getWorld().dropItem(getLocation(), this.packet2.getHelmet()),null); ;
/* 185 */       if (canDrop(this.packet2.getChestPlate())) player.getScheduler().run(FurnitureLib.getInstance(),task->getWorld().dropItem(getLocation(), this.packet2.getChestPlate()),null);
/* 186 */       if (canDrop(this.packet2.getLeggings())) player.getScheduler().run(FurnitureLib.getInstance(),task->getWorld().dropItem(getLocation(), this.packet2.getLeggings()),null);
/* 187 */       if (canDrop(this.packet2.getBoots())) player.getScheduler().run(FurnitureLib.getInstance(),task->getWorld().dropItem(getLocation(), this.packet2.getBoots()),null);
/* 188 */       if (canDrop(this.packet3.getHelmet())) player.getScheduler().run(FurnitureLib.getInstance(),task->getWorld().dropItem(getLocation(), this.packet3.getHelmet()),null);
/* 189 */       if (!this.packet1.getName().equalsIgnoreCase("#Executioner#")) {
/* 190 */         ItemStack is = new ItemStack(Material.NAME_TAG);
/* 191 */         ItemMeta im = is.getItemMeta();
/* 192 */         im.setDisplayName(this.packet1.getName());
/* 193 */         player.getScheduler().run(FurnitureLib.getInstance(),task->getWorld().dropItem(getLocation(), is),null);
/*     */       } 
/* 195 */       forceCloseInventory();
/* 196 */       destroy(player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/* 202 */     if (getObjID() == null)
/* 203 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 204 */       return;  if (player == null)
/* 205 */       return;  if (canInteract(player)) {
/* 206 */       if (player.isSneaking()) {
/* 207 */         if (this.isFinish.booleanValue())
/* 208 */           return;  player.openInventory(this.invI);
/* 209 */         for (int i = 0; i < 9; ) { this.invI.setItem(i, this.pane); i++; }
/*     */         
/* 211 */         ItemStack is = FurnitureHook.isNewVersion() ? new ItemStack(Material.valueOf("ZOMBIE_HEAD")) : new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (short)1);
/* 212 */         ItemMeta im = is.getItemMeta();
/* 213 */         im.setDisplayName("§2Executioner");
/* 214 */         is.setItemMeta(im);
/* 215 */         this.invI.setItem(2, is);
/*     */         
/* 217 */         is = FurnitureHook.isNewVersion() ? new ItemStack(Material.valueOf("PLAYER_HEAD")) : new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (short)3);
/* 218 */         im = is.getItemMeta();
/* 219 */         im.setDisplayName("§cOblation");
/* 220 */         is.setItemMeta(im);
/* 221 */         this.invI.setItem(6, is);
/* 222 */         this.p = player;
/*     */       } else {
/* 224 */         if (isRunning())
/* 225 */           return;  if (canStart() && !this.isFinish.booleanValue()) {
/* 226 */           move();
/* 227 */         } else if (canStart() && this.isFinish.booleanValue()) {
/* 228 */           setDefault();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onClick(InventoryClickEvent e) {
/* 236 */     if (e.getClickedInventory() == null)
/* 237 */       return;  if (e.getCurrentItem() == null)
/* 238 */       return;  Player p = (Player)e.getWhoClicked();
/* 239 */     if (p == null)
/* 240 */       return;  if (e.getClickedInventory().equals(this.invI)) {
/* 241 */       e.setCancelled(true);
/* 242 */       if (e.getSlot() == 2) {
/* 243 */         p.closeInventory();
/* 244 */         p.openInventory(this.invII);
/* 245 */         openInv(1);
/* 246 */         p.updateInventory();
/* 247 */       } else if (e.getSlot() == 6) {
/* 248 */         p.closeInventory();
/* 249 */         p.openInventory(this.invIII);
/* 250 */         openInv(2);
/* 251 */         p.updateInventory();
/*     */       } 
/* 253 */     } else if (e.getClickedInventory().equals(this.invII)) {
/* 254 */       if (e.getCurrentItem().equals(this.pane)) e.setCancelled(true); 
/* 255 */       Material m = e.getCurrentItem().getType();
/* 256 */       if (m == null || m.equals(Material.AIR))
/* 257 */         return;  switch (e.getSlot()) {
/*     */         case 10:
/* 259 */           if (!m.equals(Material.valueOf(FurnitureHook.isNewVersion() ? "PLAYER_HEAD" : "SKULL_ITEM"))) e.setCancelled(true); 
/*     */           break;
/*     */         case 16:
/* 262 */           if (!matList.contains(m)) e.setCancelled(true); 
/*     */           break;
/*     */         case 19:
/* 265 */           if (!matListI.contains(m)) e.setCancelled(true); 
/*     */           break;
/*     */         case 28:
/* 268 */           if (!matListII.contains(m)) e.setCancelled(true); 
/*     */           break;
/*     */         case 37:
/* 271 */           if (!matListIII.contains(m)) e.setCancelled(true); 
/*     */           break;
/*     */         case 43:
/* 274 */           if (!m.equals(Material.NAME_TAG)) e.setCancelled(true);
/*     */           
/*     */           break;
/*     */       } 
/* 278 */     } else if (e.getClickedInventory().equals(this.invIII)) {
/* 279 */       if (e.getCurrentItem().equals(this.pane)) e.setCancelled(true); 
/* 280 */       Material m = e.getCurrentItem().getType();
/* 281 */       if (m == null || m.equals(Material.AIR))
/* 282 */         return;  switch (e.getSlot()) {
/*     */         case 10:
/* 284 */           if (!m.equals(Material.valueOf(FurnitureHook.isNewVersion() ? "PLAYER_HEAD" : "SKULL_ITEM"))) e.setCancelled(true); 
/*     */           break;
/*     */         case 19:
/* 287 */           if (!matListI.contains(m)) e.setCancelled(true); 
/*     */           break;
/*     */         case 28:
/* 290 */           if (!matListII.contains(m)) e.setCancelled(true); 
/*     */           break;
/*     */         case 37:
/* 293 */           if (!matListIII.contains(m)) e.setCancelled(true); 
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isRunning() {
/* 300 */     if (this.timer != null) return true; 
/* 301 */     return false;
/*     */   }
/*     */   
/*     */   private boolean canStart() {
/* 305 */     if (isRunning()) return false; 
/* 306 */     boolean a = false, b = false, c = false;
/* 307 */     a = !this.packet1.isInvisible();
/* 308 */     b = !this.packet2.isInvisible();
/*     */     
/* 310 */     if (this.packet1.getItemInMainHand() == null) {
/* 311 */       return false;
/*     */     }
/*     */     
/* 314 */     if (this.packet1.getHelmet() == null) {
/* 315 */       return false;
/*     */     }
/*     */     
/* 318 */     if (this.packet1.getItemInMainHand() != null && !this.packet1.getItemInMainHand().getType().equals(Material.AIR)) {
/* 319 */       c = true;
/*     */     }
/*     */     
/* 322 */     if (a && b && c) {
/* 323 */       return true;
/*     */     }
/* 325 */     return false;
/*     */   }
/*     */   
/*     */   private void move() {
/* 329 */     if (isRunning())
/* 330 */       return;  this.timer = Integer.valueOf(Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable() {
/*     */             boolean b1 = false;
/*     */             boolean b2 = false;
/*     */             boolean b3 = false;
/* 334 */             double d1 = 0.0D;
/* 335 */             double d2 = 0.7D;
/* 336 */             double d3 = -1.7D;
/* 337 */             double d4 = 0.0D;
/* 338 */             int i = 0;
/* 339 */             int j = 0;
/*     */             
/*     */             public void run() {
/*     */               try {
/* 343 */                 EulerAngle angle = Guillotine.this.getLutil().Radtodegress(Guillotine.this.packet2.getHeadPose());
/* 344 */                 if (angle.getX() < 90.0D)
/* 345 */                 { angle = angle.setX(angle.getX() + 4.0D); }
/* 346 */                 else { this.b1 = true; }
/* 347 */                  if (angle.getY() > 0.0D)
/* 348 */                 { angle = angle.setY(angle.getY() - 6.0D); }
/* 349 */                 else { this.b2 = true; }
/* 350 */                  Guillotine.this.packet2.setHeadPose(Guillotine.this.getLutil().degresstoRad(angle));
/* 351 */                 Guillotine.this.update();
/* 352 */                 if (this.b1 && this.b2) {
/* 353 */                   if (this.d1 != this.d2) {
/* 354 */                     Guillotine.this.packet2.teleport(Guillotine.this.getLutil().getRelative(Guillotine.this.packet2.getLocation(), Guillotine.this.getBlockFace(), 0.1D, 0.0D));
/* 355 */                     Guillotine.this.update();
/* 356 */                     this.d1 += 0.1D;
/*     */                   }
/* 358 */                   else if (!this.b3) {
/* 359 */                     Guillotine.this.packet1.setRightArmPose(Guillotine.this.getLutil().degresstoRad(new EulerAngle(0.0D, 0.0D, 15.0D)));
/* 360 */                     Guillotine.this.packet1.setLeftArmPose(Guillotine.this.getLutil().degresstoRad(new EulerAngle(0.0D, 0.0D, 289.0D)));
/* 361 */                     this.b3 = true;
/*     */                   }
/* 363 */                   else if (this.i != 10) {
/* 364 */                     Guillotine.this.playSound();
/* 365 */                     this.i++;
/*     */                   }
/* 367 */                   else if (this.d4 > this.d3) {
/* 368 */                     for (fEntity stand : Guillotine.this.armorStandList) {
/* 369 */                       stand.teleport(stand.getLocation().add(0.0D, -1.7D, 0.0D));
/*     */                     }
/* 371 */                     this.d4 = -1.7D;
/* 372 */                     Guillotine.this.update();
/*     */                   } else {
/* 374 */                     if (this.j == 0) {
/* 375 */                       Guillotine.this.packet3.getInventory().setHelmet(Guillotine.this.packet2.getInventory().getHelmet());
/* 376 */                       Guillotine.this.packet2.getInventory().setHelmet(new ItemStack(Material.AIR));
/* 377 */                       Guillotine.this.update();
/* 378 */                     } else if (this.j != 0 && this.j < 3) {
/* 379 */                       Guillotine.this.update();
/* 380 */                     } else if (this.j == 3) {
/* 381 */                       Guillotine.this.getWorld().playEffect(Guillotine.this.getLutil().getRelative(Guillotine.this.getLocation(), Guillotine.this.getBlockFace(), 0.0D, -0.1D).add(0.0D, 1.3D, 0.0D), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
/* 382 */                       Guillotine.this.stopTimer();
/* 383 */                       Guillotine.this.isFinish = Boolean.valueOf(true);
/*     */                       return;
/*     */                     } 
/* 386 */                     this.j++;
/*     */                   
/*     */                   }
/*     */                 
/*     */                 }
/*     */               }
/* 392 */               catch (Exception e) {
/* 393 */                 Guillotine.this.stopTimer();
/*     */               }
}
    }, 0, 1));
}
/*     */ 
/*     */ 
/*     */   
/*     */   private void stopTimer() {
/* 402 */     if (isRunning()) {
/* 403 */       Bukkit.getScheduler().cancelTask(this.timer.intValue());
/* 404 */       this.timer = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onClose(InventoryCloseEvent e) {
/* 410 */     if (e.getInventory() == null)
/* 411 */       return;  if (e.getInventory().equals(this.invII)) {
/* 412 */       boolean invisible = true;
/*     */       
/* 414 */       this.packet1.getInventory().setHelmet(this.invII.getItem(10));
/* 415 */       this.packet1.getInventory().setChestPlate(this.invII.getItem(19));
/* 416 */       this.packet1.getInventory().setLeggings(this.invII.getItem(28));
/* 417 */       this.packet1.getInventory().setBoots(this.invII.getItem(37));
/*     */       
/* 419 */       if (this.invII.getItem(10) != null) invisible = false; 
/* 420 */       if (this.invII.getItem(19) != null) invisible = false; 
/* 421 */       if (this.invII.getItem(28) != null) invisible = false; 
/* 422 */       if (this.invII.getItem(37) != null) invisible = false;
/*     */       
/* 424 */       if (!invisible) {
/* 425 */         if (this.invII.getItem(43) != null) {
/* 426 */           if (this.invII.getItem(43).hasItemMeta() && this.invII.getItem(43).getItemMeta().hasDisplayName()) {
/* 427 */             this.packet1.setName(ChatColor.translateAlternateColorCodes('&', this.invII.getItem(43).getItemMeta().getDisplayName()));
/* 428 */             this.packet1.setNameVasibility(true);
/*     */           } else {
/* 430 */             if (this.invII.getItem(43) != null && !this.invII.getItem(43).getType().equals(Material.AIR)) Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),task->getWorld().dropItem(getLocation().clone().add(0.0D, 1.0D, 0.0D), this.invII.getItem(43)));
/* 431 */             this.packet1.setName("#Executioner#");
/* 432 */             this.packet1.setNameVasibility(false);
/* 433 */             this.packet1.setInvisible(false);
/*     */           } 
/*     */         } else {
/* 436 */           if (this.invII.getItem(43) != null && !this.invII.getItem(43).getType().equals(Material.AIR)) Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),task->getWorld().dropItem(getLocation().clone().add(0.0D, 1.0D, 0.0D), this.invII.getItem(43)));
/* 437 */           this.packet1.setName("#Executioner#");
/* 438 */           this.packet1.setNameVasibility(false);
/*     */         } 
/*     */         
/* 441 */         if (this.invII.getItem(16) != null) {
/* 442 */           this.packet1.getInventory().setItemInMainHand(this.invII.getItem(16));
/*     */         } else {
/* 444 */           this.packet1.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
/*     */         } 
/*     */       } else {
/* 447 */         if (this.invII.getItem(43) != null && !this.invII.getItem(43).getType().equals(Material.AIR)) Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),task->getWorld().dropItem(getLocation().clone().add(0.0D, 1.0D, 0.0D), this.invII.getItem(43)));
/* 448 */         if (this.invII.getItem(16) != null && !this.invII.getItem(16).getType().equals(Material.AIR)) Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),task->getWorld().dropItem(getLocation().clone().add(0.0D, 1.0D, 0.0D), this.invII.getItem(16)));
/* 449 */         this.packet1.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
/* 450 */         this.packet1.setName("#Executioner#");
/* 451 */         this.packet1.setNameVasibility(!invisible);
/*     */       } 
/* 453 */       this.packet1.setArms(true);
/* 454 */       this.packet1.setBasePlate(false);
/* 455 */       this.packet1.setInvisible(invisible);
/* 456 */       update();
/* 457 */       this.p = null;
/* 458 */     } else if (e.getInventory().equals(this.invIII)) {
/* 459 */       boolean invisible = true;
/* 460 */       this.packet2.getInventory().setHelmet(this.invIII.getItem(10));
/* 461 */       this.packet2.getInventory().setChestPlate(this.invIII.getItem(19));
/* 462 */       this.packet2.getInventory().setLeggings(this.invIII.getItem(28));
/* 463 */       this.packet2.getInventory().setBoots(this.invIII.getItem(37));
/*     */       
/* 465 */       if (this.invIII.getItem(10) != null) invisible = false; 
/* 466 */       if (this.invIII.getItem(19) != null) invisible = false; 
/* 467 */       if (this.invIII.getItem(28) != null) invisible = false; 
/* 468 */       if (this.invIII.getItem(37) != null) invisible = false; 
/* 469 */       this.packet2.setBasePlate(false);
/* 470 */       this.packet2.setArms(true);
/* 471 */       this.packet2.setInvisible(invisible);
/* 472 */       update();
/* 473 */       LocationUtil.particleBlock(this.packet2.getLocation().getBlock());
/* 474 */       this.p = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playSound() {
/* 479 */     if (!this.soundPlaying.booleanValue()) {
/* 480 */       getWorld().playSound(getLocation(), Sound.ENTITY_GHAST_DEATH, 2.0F, 1.0F);
/* 481 */       getWorld().playSound(getLocation(), Sound.ENTITY_GHAST_SCREAM, 1.0F, 1.0F);
/* 482 */       getWorld().playSound(getLocation(), Sound.ENTITY_GHAST_WARN, 1.0F, 1.0F);
/* 483 */       this.soundPlaying = Boolean.valueOf(true);
/*     */     } 
/*     */   } private void openInv(int i) {
/*     */     fArmorStand packet;
/*     */     Inventory inv;
/* 488 */     if (i > 2 || i < 1) {
/*     */       return;
/*     */     }
/* 491 */     if (i == 1) {
/* 492 */       packet = this.packet1;
/* 493 */       inv = this.invII;
/*     */     } else {
/* 495 */       packet = this.packet2;
/* 496 */       inv = this.invIII;
/*     */     } 
/* 498 */     for (int j = 0; j < inv.getSize(); j++) {
/* 499 */       Material m = Material.AIR;
/* 500 */       if (!packet.getName().equalsIgnoreCase("#Executioner#")) m = Material.NAME_TAG; 
/* 501 */       ItemStack stack = new ItemStack(Material.AIR);
/* 502 */       ItemMeta meta = stack.getItemMeta();
/* 503 */       if (!m.equals(Material.AIR) && 
/* 504 */         packet.getName() != null && 
/* 505 */         meta != null) {
/* 506 */         meta.setDisplayName(packet.getName());
/* 507 */         stack.setItemMeta(meta);
/*     */       } 
/*     */ 
/*     */       
/* 511 */       switch (j) { case 10:
/* 512 */           inv.setItem(j, packet.getHelmet()); break;
/* 513 */         case 19: inv.setItem(j, packet.getChestPlate()); break;
/* 514 */         case 28: inv.setItem(j, packet.getLeggings()); break;
/* 515 */         case 37: inv.setItem(j, packet.getBoots()); break;
/* 516 */         case 16: if (i == 1) { inv.setItem(j, packet.getItemInMainHand()); break; } 
/* 517 */         case 43: if (i == 1) { inv.setItem(j, stack); break; } 
/* 518 */         default: inv.setItem(j, this.pane);
/*     */           break; }
/*     */     
/*     */     } 
/* 522 */     if (i == 1) {
/* 523 */       this.invII = inv;
/*     */     } else {
/* 525 */       this.invIII = inv;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\RPG\Guillotine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */