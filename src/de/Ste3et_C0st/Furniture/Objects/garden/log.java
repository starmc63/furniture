/*     */ package de.Ste3et_C0st.Furniture.Objects.garden;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*     */ import de.Ste3et_C0st.Furniture.Main.main;
/*     */ import de.Ste3et_C0st.FurnitureLib.Crafting.Project;
/*     */ import de.Ste3et_C0st.FurnitureLib.Utilitis.HiddenStringUtils;
/*     */ import de.Ste3et_C0st.FurnitureLib.Utilitis.ManageInv;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureConfig;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fArmorStand;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class log
/*     */   extends Furniture implements Listener {
/*  36 */   private int mode = 0;
/*  37 */   private Inventory inv = Bukkit.createInventory(null, 9, "§2Settings");
/*  38 */   private ItemStack pane = FurnitureHook.isNewVersion() ? new ItemStack(Material.valueOf("BLACK_STAINED_GLASS_PANE")) : new ItemStack(Material.valueOf("STAINED_GLASS_PANE"), 1, (short)15);
/*  39 */   private ItemStack permissions = new ItemStack(Material.ARROW);
/*     */   private Player p;
/*  41 */   private List<ItemStack> isList = new ArrayList<>();
/*     */   
/*     */   public log(ObjectID id) {
/*  44 */     super(id);
/*  45 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)main.instance);
/*  46 */     getObjID().getPacketList().stream().forEach(entity -> {
/*     */           entity.setNameVasibility(false);
/*     */           ((fArmorStand)entity).setMarker(false);
/*     */         });
/*  50 */     update();
/*  51 */     setList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/*  56 */     if (getObjID() == null)
/*  57 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  58 */       return;  if (player == null)
/*  59 */       return;  if (canBuild(player)) {
/*  60 */       fEntity entity = entityByCustomName(this.mode + "");
/*  61 */       if (entity != null && entity instanceof fContainerEntity) {
/*  62 */         fContainerEntity container = fContainerEntity.class.cast(entity);
/*  63 */         if (container.getItemInMainHand() != null && 
/*  64 */           container.getItemInMainHand().getType() != Material.AIR) {
/*  65 */           player.getWorld().dropItemNaturally(getCenter().add(0.0D, 1.0D, 0.0D), container.getItemInMainHand());
/*     */         }
/*     */       } 
/*     */       
/*  69 */       destroy(player);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Project getProjectByItem(ItemStack is) {
/*  74 */     ItemStack stack = getItemStackCopy(is);
/*  75 */     if (stack == null) return null; 
/*  76 */     String systemID = "";
/*  77 */     if (stack.hasItemMeta() && 
/*  78 */       stack.getItemMeta().hasLore()) {
/*  79 */       List<String> s = stack.getItemMeta().getLore();
/*  80 */       if (HiddenStringUtils.hasHiddenString(s.get(0))) systemID = HiddenStringUtils.extractHiddenString(s.get(0));
/*     */     
/*     */     } 
/*  83 */     for (Project pro : FurnitureLib.getInstance().getFurnitureManager().getProjects()) {
/*  84 */       if (pro != null && 
/*  85 */         pro.getSystemID() != null && 
/*  86 */         pro.getSystemID().equalsIgnoreCase(systemID)) {
/*  87 */         return pro;
/*     */       }
/*     */     } 
/*     */     
/*  91 */     return null;
/*     */   }
/*     */   
/*     */   private ItemStack getItemStackCopy(ItemStack is) {
/*  95 */     ItemStack copy = is.clone();
/*  96 */     copy.setAmount(1);
/*  97 */     return copy;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/* 102 */     if (getObjID() == null)
/* 103 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 104 */       return;  if (player == null)
/* 105 */       return;  if (canInteract(player)) {
/* 106 */       if (player.isSneaking()) {
/* 107 */         if (player.getInventory().getItemInMainHand().getType().isBlock() && 
/* 108 */           !player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
/*     */           return;
/*     */         }
/* 111 */         openInventory(player);
/*     */       } else {
/* 113 */         ItemStack stack = player.getInventory().getItemInMainHand();
/* 114 */         if (stack == null || stack.getType().equals(Material.AIR)) {
/*     */           
/* 116 */           fEntity entity = entityByCustomName(this.mode + "");
/* 117 */           if (entity != null && entity instanceof fContainerEntity) {
/* 118 */             fContainerEntity container = fContainerEntity.class.cast(entity);
/* 119 */             if (container.getItemInMainHand() != null && 
/* 120 */               !container.getItemInMainHand().getType().equals(Material.AIR)) {
/* 121 */               if (container.getItemInMainHand().equals(stack))
/* 122 */                 return;  Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),task->getWorld().dropItem(getLocation(), container.getItemInMainHand()));
/* 123 */               container.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
/* 124 */               update();
/*     */             } 
/*     */           } 
/*     */         } else {
/*     */           
/* 129 */           if (getProjectByItem(stack) != null)
/* 130 */             return;  if (stack.getType().isBlock()) {
/* 131 */             Block b = getCenter().getBlock();
                    Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),b.getLocation(),task->
/* 132 */             b.setType(stack.getType()));
/*     */           } else {
/* 134 */             fEntity entity = entityByCustomName(this.mode + "");
/* 135 */             if (entity != null && entity instanceof fContainerEntity) {
/* 136 */               fContainerEntity container = fContainerEntity.class.cast(entity);
/* 137 */               if (container.getItemInMainHand() != null && 
/* 138 */                 !container.getItemInMainHand().getType().equals(Material.AIR)) {
/* 139 */                 if (container.getItemInMainHand().equals(stack))
/* 140 */                   return;  Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),task->getWorld().dropItem(getLocation(), container.getItemInMainHand()));
/*     */               } 
/*     */               
/* 143 */               ItemStack placeItem = stack.clone();
/* 144 */               placeItem.setAmount(1);
/* 145 */               container.getInventory().setItemInMainHand(placeItem);
/* 146 */               consumeItem(player);
/* 147 */               update();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void setList() {
/* 156 */     ItemStack stack = FurnitureHook.isNewVersion() ? new ItemStack(Material.valueOf("WHITE_BANNER")) : new ItemStack(Material.valueOf("BANNER"), 1, (short)1);
/* 157 */     ItemMeta meta = stack.getItemMeta();
/* 158 */     meta.setDisplayName("§6Mode: §cTop");
/* 159 */     stack.setItemMeta(meta);
/* 160 */     this.isList.add(stack);
/* 161 */     stack = FurnitureHook.isNewVersion() ? new ItemStack(Material.valueOf("ORANGE_BANNER")) : new ItemStack(Material.valueOf("BANNER"), 1, (short)2);
/* 162 */     meta = stack.getItemMeta();
/* 163 */     meta.setDisplayName("§6Mode: §cFront I");
/* 164 */     stack.setItemMeta(meta);
/* 165 */     this.isList.add(stack);
/* 166 */     stack = FurnitureHook.isNewVersion() ? new ItemStack(Material.valueOf("BLUE_BANNER")) : new ItemStack(Material.valueOf("BANNER"), 1, (short)11);
/* 167 */     meta = stack.getItemMeta();
/* 168 */     meta.setDisplayName("§6Mode: §cFront II");
/* 169 */     stack.setItemMeta(meta);
/* 170 */     this.isList.add(stack);
/*     */     
/* 172 */     meta = this.permissions.getItemMeta();
/* 173 */     meta.setDisplayName("§cChange Permissions (Owner Only)");
/* 174 */     this.permissions.setItemMeta(meta);
/*     */   }
/*     */   
/*     */   public void removeItem(Player p) {
/* 178 */     Boolean useGameMode = Boolean.valueOf(FurnitureConfig.getFurnitureConfig().useGamemode());
/* 179 */     if (useGameMode.booleanValue() && p.getGameMode().equals(GameMode.CREATIVE))
/* 180 */       return;  Integer slot = Integer.valueOf(p.getInventory().getHeldItemSlot());
/* 181 */     ItemStack itemStack = p.getInventory().getItemInMainHand().clone();
/* 182 */     itemStack.setAmount(itemStack.getAmount() - 1);
/* 183 */     p.getInventory().setItem(slot.intValue(), itemStack);
/* 184 */     p.updateInventory();
/*     */   }
/*     */   
/*     */   private void openInventory(Player p) {
/* 188 */     if (this.p != null)
/* 189 */       return;  this.p = p;
/* 190 */     this.inv.clear();
/* 191 */     int j = this.mode;
/* 192 */     for (int i = 0; i < this.inv.getSize(); i++) {
/* 193 */       if (i == 2) {
/* 194 */         this.inv.addItem(new ItemStack[] { this.isList.get(j) });
/*     */       }
/* 196 */       else if (i == 6) {
/* 197 */         this.inv.addItem(new ItemStack[] { this.permissions });
/*     */       } else {
/*     */         
/* 200 */         this.inv.setItem(i, this.pane);
/*     */       } 
/* 202 */     }  p.openInventory(this.inv);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onClick(InventoryClickEvent e) {
/* 207 */     if (getObjID() == null)
/* 208 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 209 */       return;  if (e.getClickedInventory() == null || !e.getClickedInventory().equals(this.inv))
/* 210 */       return;  e.setCancelled(true);
/* 211 */     if (e.getCurrentItem() == null)
/* 212 */       return;  if (e.getCurrentItem().getType().name().contains("BANNER")) {
/* 213 */       Integer i = Integer.valueOf(this.isList.indexOf(e.getCurrentItem()));
/* 214 */       ItemStack is = null;
/* 215 */       if (i.intValue() >= 2) i = Integer.valueOf(-1); 
/* 216 */       Integer integer1 = i; i = Integer.valueOf(i.intValue() + 1);
/* 217 */       is = this.isList.get(i.intValue());
/* 218 */       this.mode = i.intValue();
/* 219 */       if (FurnitureHook.isNewVersion()) { modeSwitch(e.getCurrentItem().getType()); } else { modeSwitch(e.getCurrentItem().getDurability()); }
/* 220 */        this.inv.setItem(e.getSlot(), is);
/* 221 */       this.p.updateInventory();
/* 222 */     } else if (e.getCurrentItem().getType().equals(Material.ARROW)) {
/* 223 */       if (!getObjID().getUUID().equals(this.p.getUniqueId()) && 
/* 224 */         !getLib().getPermission().hasPerm((CommandSender)this.p, "furniture.admin") && !this.p.isOp() && !getLib().getPermission().hasPerm((CommandSender)this.p, "furniture.manage.other")) {
/*     */         return;
/*     */       }
/*     */       
/* 228 */       this.p.closeInventory();
/* 229 */       new ManageInv((Player)e.getWhoClicked(), getObjID());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void modeSwitch(Material type) {
/* 234 */     int oldArmorStand = 0;
/* 235 */     int currentArmorStand = 0;
/*     */     
/* 237 */     switch (type) {
/*     */       case WHITE_BANNER:
/* 239 */         oldArmorStand = 0;
/* 240 */         currentArmorStand = 1;
/*     */         break;
/*     */       case ORANGE_BANNER:
/* 243 */         oldArmorStand = 1;
/* 244 */         currentArmorStand = 2;
/*     */         break;
/*     */       case BLUE_BANNER:
/* 247 */         oldArmorStand = 2;
/* 248 */         currentArmorStand = 0;
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 253 */     fContainerEntity standOld = null;
/* 254 */     fContainerEntity standCurrent = null;
/* 255 */     for (fEntity s : getObjID().getPacketList()) {
/* 256 */       if (s instanceof fContainerEntity) {
/* 257 */         fContainerEntity container = fContainerEntity.class.cast(s);
/* 258 */         if (s.getName().equalsIgnoreCase(oldArmorStand + "")) {
/* 259 */           standOld = container; continue;
/* 260 */         }  if (s.getName().equalsIgnoreCase(currentArmorStand + "")) {
/* 261 */           standCurrent = container;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 266 */     if (standOld != null && standCurrent != null) {
/* 267 */       standCurrent.setItemInMainHand(standOld.getItemInMainHand());
/* 268 */       standOld.setItemInMainHand(new ItemStack(Material.AIR));
/* 269 */       update();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void modeSwitch(short dura) {
/* 274 */     int oldArmorStand = 0;
/* 275 */     int currentArmorStand = 0;
/*     */     
/* 277 */     switch (dura) {
/*     */       case 1:
/* 279 */         oldArmorStand = 0;
/* 280 */         currentArmorStand = 1;
/*     */         break;
/*     */       case 2:
/* 283 */         oldArmorStand = 1;
/* 284 */         currentArmorStand = 2;
/*     */         break;
/*     */       case 11:
/* 287 */         oldArmorStand = 2;
/* 288 */         currentArmorStand = 0;
/*     */         break;
/*     */     } 
/*     */     
/* 292 */     fContainerEntity standOld = null;
/* 293 */     fContainerEntity standCurrent = null;
/* 294 */     for (fEntity s : getObjID().getPacketList()) {
/* 295 */       if (s instanceof fContainerEntity) {
/* 296 */         fContainerEntity container = fContainerEntity.class.cast(s);
/* 297 */         if (s.getName().equalsIgnoreCase(oldArmorStand + "")) {
/* 298 */           standOld = container; continue;
/* 299 */         }  if (s.getName().equalsIgnoreCase(currentArmorStand + "")) {
/* 300 */           standCurrent = container;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 305 */     if (standOld != null && standCurrent != null) {
/* 306 */       standCurrent.setItemInMainHand(standOld.getItemInMainHand());
/* 307 */       standOld.setItemInMainHand(new ItemStack(Material.AIR));
/* 308 */       update();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onClose(InventoryCloseEvent e) {
/* 315 */     if (getObjID() == null)
/* 316 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 317 */       return;  if (e.getInventory() == null)
/* 318 */       return;  if (e.getInventory().equals(this.inv)) this.p = null; 
/*     */   }
/*     */   
/*     */   public void spawn(Location location) {}
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\garden\log.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */