/*     */ package de.Ste3et_C0st.Furniture.Objects.RPG;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*     */ import de.Ste3et_C0st.Furniture.Main.main;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class weaponStand
/*     */   extends Furniture
/*     */   implements Listener {
/*  30 */   List<Integer> slotList1 = Arrays.asList(new Integer[] { Integer.valueOf(6), Integer.valueOf(11), Integer.valueOf(14), Integer.valueOf(16), Integer.valueOf(19), Integer.valueOf(21), Integer.valueOf(24), Integer.valueOf(29), Integer.valueOf(32), Integer.valueOf(34), Integer.valueOf(42) });
/*  31 */   List<Integer> slotList2 = Arrays.asList(new Integer[] { Integer.valueOf(20), Integer.valueOf(15), Integer.valueOf(33) });
/*  32 */   List<Material> matList = Arrays.asList(new Material[] {
/*  33 */         Material.valueOf(FurnitureHook.isNewVersion() ? "OAK_FENCE_GATE" : "FENCE_GATE"), Material.SPRUCE_FENCE_GATE, Material.BIRCH_FENCE_GATE, Material.JUNGLE_FENCE_GATE, Material.DARK_OAK_FENCE_GATE, Material.ACACIA_FENCE_GATE
/*     */       });
/*     */   
/*     */   Player p;
/*     */   
/*     */   Inventory inv;
/*     */   
/*     */   public weaponStand(ObjectID id) {
/*  41 */     super(id);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     this.p = null;
/*  50 */     this.inv = null; if (isFinish()) {
/*     */       Bukkit.getPluginManager().registerEvents(this, main.getInstance());
/*     */       return;
/*     */     } 
/*  54 */     spawn(id.getStartLocation()); } public void onBreak(Player player) { if (getObjID() == null)
/*  55 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  56 */       return;  if (player == null)
/*  57 */       return;  if (canBuild(player)) {
/*  58 */       if (this.p != null) {
/*  59 */         this.p.closeInventory();
/*  60 */         this.inv = null;
/*     */       } 
/*  62 */       HashSet<fEntity> asList = getObjID().getPacketList();
/*  63 */       for (fEntity packet : asList) {
/*  64 */         if (packet.getName() != null && !packet.getName().equalsIgnoreCase("") && 
/*  65 */           packet instanceof fContainerEntity) {
/*  66 */           fContainerEntity containerEntity = fContainerEntity.class.cast(packet);
/*  67 */           if (containerEntity.getInventory().getItemInMainHand() != null && 
/*  68 */             !containerEntity.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
/*  69 */             Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(), task->getWorld().dropItem(getLocation(), containerEntity.getInventory().getItemInMainHand()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  75 */       forceCloseInventory();
/*  76 */       destroy(player);
/*     */     }  }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/*  82 */     if (getObjID() == null)
/*  83 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  84 */       return;  if (player == null)
/*  85 */       return;  if (canInteract(player)) {
/*  86 */       if (this.p != null)
/*  87 */         return;  this.p = player;
/*  88 */       ItemStack itemstack = this.p.getInventory().getItemInMainHand();
/*  89 */       if (itemstack != null && this.matList.contains(itemstack.getType())) {
/*  90 */         for (fEntity packet : getManager().getfArmorStandByObjectID(getObjID())) {
/*  91 */           if (packet instanceof fContainerEntity) {
/*  92 */             fContainerEntity containerEntity = fContainerEntity.class.cast(packet);
/*  93 */             if (containerEntity.getInventory().getHelmet() != null && 
/*  94 */               containerEntity.getInventory().getHelmet().getType().name().toLowerCase().endsWith("gate")) {
/*  95 */               ItemStack itemStack = new ItemStack(itemstack.getType(), 1);
/*  96 */               containerEntity.getInventory().setHelmet(itemStack);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 101 */         getManager().updateFurniture(getObjID());
/* 102 */         this.p = null;
/*     */         
/*     */         return;
/*     */       } 
/* 106 */       ItemStack is1 = FurnitureHook.isNewVersion() ? new ItemStack(Material.valueOf("BLACK_STAINED_GLASS_PANE"), 1) : new ItemStack(Material.valueOf("STAINED_GLASS_PANE"), 1, (short)15);
/* 107 */       ItemStack is3 = FurnitureHook.isNewVersion() ? new ItemStack(Material.valueOf("BLACK_STAINED_GLASS_PANE"), 1) : new ItemStack(Material.valueOf("STAINED_GLASS_PANE"), 1, (short)14);
/*     */       
/* 109 */       ItemMeta im1 = is1.getItemMeta();
/* 110 */       ItemMeta im3 = is3.getItemMeta();
/* 111 */       im1.setDisplayName("§c");
/* 112 */       im3.setDisplayName("§c");
/* 113 */       is1.setItemMeta(im1);
/* 114 */       is3.setItemMeta(im3);
/*     */       
/* 116 */       this.inv = Bukkit.createInventory(null, 45, "§cWeaponBox");
/*     */       
/* 118 */       HashSet<fEntity> asList = getObjID().getPacketList();
/*     */       
/* 120 */       int j = 1;
/* 121 */       for (int i = 0; i < this.inv.getSize(); i++) {
/* 122 */         this.inv.setItem(i, is1);
/* 123 */         if (this.slotList1.contains(Integer.valueOf(i))) {
/* 124 */           this.inv.setItem(i, is3);
/* 125 */         } else if (this.slotList2.contains(Integer.valueOf(i))) {
/* 126 */           for (fEntity packet : asList) {
/* 127 */             if (packet instanceof fContainerEntity) {
/* 128 */               fContainerEntity containerEntity = fContainerEntity.class.cast(packet);
/* 129 */               if (packet.getName() != null && !packet.getName().equalsIgnoreCase("") && 
/* 130 */                 packet.getName().equalsIgnoreCase("#SLOT" + j + "#")) {
/* 131 */                 ItemStack is = new ItemStack(Material.AIR);
/* 132 */                 if (containerEntity.getInventory().getItemInMainHand() != null) is = containerEntity.getInventory().getItemInMainHand(); 
/* 133 */                 this.inv.setItem(i, is);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 138 */           j++;
/*     */         } 
/*     */       } 
/*     */       
/* 142 */       this.p.openInventory(this.inv);
/* 143 */       this.p.updateInventory();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void forceCloseInventory() {
/* 148 */     if (Objects.nonNull(this.inv)) this.inv.getViewers().forEach(HumanEntity::closeInventory); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onClick(InventoryClickEvent e) {
/* 153 */     if (getObjID() == null)
/* 154 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 155 */       return;  if (this.p == null)
/* 156 */       return;  if (this.inv == null)
/* 157 */       return;  if (e.getInventory() == null)
/* 158 */       return;  if (e.getCurrentItem() == null)
/* 159 */       return;  if (!e.getInventory().equals(this.inv))
/* 160 */       return;  ItemStack is = e.getCurrentItem();
/* 161 */     Material m = is.getType();
/* 162 */     if (!isValid(m)) e.setCancelled(true); 
/*     */   }
/*     */   
/*     */   public boolean isValid(Material m) {
/* 166 */     String matName = m.toString().toLowerCase();
/* 167 */     boolean b = false;
/* 168 */     if (matName.endsWith("axe")) b = true; 
/* 169 */     if (matName.endsWith("hoe")) b = true; 
/* 170 */     if (matName.endsWith("pickaxe")) b = true; 
/* 171 */     if (matName.endsWith("spade")) b = true; 
/* 172 */     if (matName.endsWith("sword")) b = true; 
/* 173 */     if (matName.equalsIgnoreCase("air")) b = true; 
/* 174 */     return b;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onClose(InventoryCloseEvent e) {
/* 179 */     if (getObjID() == null)
/* 180 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 181 */       return;  if (this.p == null)
/* 182 */       return;  if (this.inv == null)
/* 183 */       return;  if (!e.getView().getTopInventory().equals(this.inv))
/*     */       return; 
/* 185 */     HashSet<fEntity> asList = getObjID().getPacketList();
/* 186 */     int j = 1;
/* 187 */     for (int i = 0; i < this.inv.getSize(); i++) {
/* 188 */       if (this.slotList2.contains(Integer.valueOf(i))) {
/* 189 */         for (fEntity packet : asList) {
/* 190 */           if (packet instanceof fContainerEntity) {
/* 191 */             fContainerEntity containerEntity = fContainerEntity.class.cast(packet);
/* 192 */             if (packet.getName() != null && !packet.getName().equalsIgnoreCase("") && 
/* 193 */               packet.getName().equalsIgnoreCase("#SLOT" + j + "#")) {
/* 194 */               ItemStack is = this.inv.getItem(i);
/* 195 */               if (is == null) is = new ItemStack(Material.AIR); 
/* 196 */               containerEntity.getInventory().setItemInMainHand(is);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 201 */         j++;
/*     */       } 
/*     */     } 
/*     */     
/* 205 */     if (e.getPlayer().equals(this.p)) {
/* 206 */       this.p = null;
/* 207 */       this.inv = null;
/* 208 */       getManager().updateFurniture(getObjID());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawn(Location loc) {
/* 214 */     Bukkit.getPluginManager().registerEvents(this, getPlugin());
/*     */   }
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\RPG\weaponStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */