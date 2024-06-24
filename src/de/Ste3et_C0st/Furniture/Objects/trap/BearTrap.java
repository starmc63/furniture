/*     */ package de.Ste3et_C0st.Furniture.Objects.trap;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*     */ import de.Ste3et_C0st.Furniture.Main.main;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fArmorStand;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.PlayerMoveEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class BearTrap
/*     */   extends Furniture implements Listener {
/*     */   fArmorStand stand1;
/*     */   fArmorStand stand2;
/*     */   
/*     */   public BearTrap(ObjectID id) {
/*  26 */     super(id);
/*  27 */     for (fEntity stand : getfAsList()) {
/*  28 */       if (stand.getName().equalsIgnoreCase("#IRON1#")) {
/*  29 */         this.stand1 = (fArmorStand)stand; continue;
/*  30 */       }  if (stand.getName().equalsIgnoreCase("#IRON2#")) {
/*  31 */         this.stand2 = (fArmorStand)stand; continue;
/*  32 */       }  if (stand.getName().equalsIgnoreCase("#IRON3#")) {
/*  33 */         this.stand3 = (fArmorStand)stand; continue;
/*  34 */       }  if (stand.getName().equalsIgnoreCase("#IRON4#")) {
/*  35 */         this.stand4 = (fArmorStand)stand;
/*     */       }
/*     */     } 
/*  38 */     Bukkit.getPluginManager().registerEvents(this, main.getInstance());
/*     */   }
/*     */   fArmorStand stand3; fArmorStand stand4; boolean b;
/*     */   
/*     */   public void spawn(Location loc) {
/*  43 */     Bukkit.getPluginManager().registerEvents(this, getPlugin());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/*  48 */     if (getObjID() == null)
/*  49 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  50 */       return;  if (player == null)
/*  51 */       return;  if (canBuild(player)) {
/*  52 */       destroy(player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/*  58 */     if (getObjID() == null)
/*  59 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  60 */       return;  if (player == null)
/*  61 */       return;  if (canInteract(player) && 
/*  62 */       this.b) {
/*  63 */       setStatus(false);
/*  64 */       getWorld().playSound(getLocation(), Sound.BLOCK_ANVIL_LAND, 10.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setStatus(boolean b) {
/*  70 */     if (!b) {
/*  71 */       this.stand1.setHelmet(new ItemStack(Material.valueOf(FurnitureHook.isNewVersion() ? "IRON_BARS" : "IRON_FENCE")));
/*  72 */       this.stand2.setHelmet(new ItemStack(Material.valueOf(FurnitureHook.isNewVersion() ? "IRON_BARS" : "IRON_FENCE")));
/*  73 */       this.stand3.setHelmet(new ItemStack(Material.AIR));
/*  74 */       this.stand4.setHelmet(new ItemStack(Material.AIR));
/*     */     } else {
/*  76 */       this.stand3.setHelmet(new ItemStack(Material.valueOf(FurnitureHook.isNewVersion() ? "IRON_BARS" : "IRON_FENCE")));
/*  77 */       this.stand4.setHelmet(new ItemStack(Material.valueOf(FurnitureHook.isNewVersion() ? "IRON_BARS" : "IRON_FENCE")));
/*  78 */       this.stand1.setHelmet(new ItemStack(Material.AIR));
/*  79 */       this.stand2.setHelmet(new ItemStack(Material.AIR));
/*  80 */       getWorld().playSound(getLocation(), Sound.ENTITY_ITEM_BREAK, 5.0F, 1.0F);
/*     */     } 
/*  82 */     this.b = b;
/*  83 */     update();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerMove(PlayerMoveEvent e) {
/*  88 */     if (this.b)
/*  89 */       return;  if (getObjID() == null)
/*  90 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  91 */       return;  if (e.isCancelled())
/*     */       return; 
/*  93 */     if (e.getFrom().getWorld() == e.getTo().getWorld() && e
/*  94 */       .getFrom().getBlockX() == e.getTo().getBlockX() && e
/*  95 */       .getFrom().getBlockY() == e.getTo().getBlockY() && e
/*  96 */       .getFrom().getBlockZ() == e.getTo().getBlockZ()) {
/*     */       return;
/*     */     }
/*  99 */     Player player = e.getPlayer();
/* 100 */     if (player.getHealth() <= 0.0D)
/* 101 */       return;  Location loc = e.getTo().getBlock().getLocation();
/* 102 */     Location loc2 = getLocation();
/* 103 */     if (loc.toVector().distance(loc2.toVector()) < 1.0D) {
/* 104 */       setStatus(true);
/* 105 */       player.damage(main.damage);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\trap\BearTrap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */