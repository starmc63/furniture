/*     */ package de.Ste3et_C0st.Furniture.Objects.garden;
/*     */ 
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.BookMeta;
/*     */ 
/*     */ public class graveStone
/*     */   extends Furniture
/*     */ {
/*     */   private Location signLoc;
/*  20 */   private String[] lines = new String[4];
/*     */   
/*     */   public graveStone(ObjectID id) {
/*  23 */     super(id);
/*  24 */     setBlock();
/*     */   }
/*     */   
/*     */   private void setBlock() {
/*  28 */     this.signLoc = getObjID().getBlockList().stream().filter(b -> b.getBlock().getType().name().contains("SIGN")).findFirst().orElse(null);
/*     */
/*  30 */     this.lines = getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/*  35 */     if (getObjID() == null)
/*  36 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  37 */       return;  if (player == null)
/*  38 */       return;  if (canBuild(player)) {
/*  39 */       destroy(player);
/*  40 */       if (getSignLocation() != null) {
    Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),scheduledTask -> {getSignLocation().getBlock().setType(Material.AIR);});
/*  41 */
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/*  48 */     if (getObjID() == null)
/*  49 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  50 */       return;  if (player == null)
/*  51 */       return;  if (canInteract(player)) {
/*  52 */       if (FurnitureLib.getVersionInt() > 19) {
/*  53 */         player.openSign((Sign)getSignLocation().getBlock().getState());
/*     */       } else {
/*  55 */         ItemStack is = player.getInventory().getItemInMainHand();
/*  56 */         if (is == null)
/*  57 */           return;  if (!is.getType().equals(Material.WRITTEN_BOOK))
/*  58 */           return;  readFromBook(is);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void resetSign() {
/*  64 */     Bukkit.getScheduler().scheduleSyncDelayedTask(getPlugin(), new Runnable()
/*     */         {
/*     */           public void run() {
/*  67 */             graveStone.this.placetext();
/*     */           }
/*     */         });
/*     */   }
/*     */   public Location getSignLocation() {
/*  72 */     return this.signLoc;
/*     */   }
/*     */   public void removeSign() {
/*  75 */     if (this.signLoc != null) {
    Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),scheduledTask -> this.signLoc.getBlock().setType(Material.AIR));
/*  76 */
/*  77 */       getManager().remove(getObjID());
/*  78 */       delete();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readFromBook(ItemStack is) {
/*  83 */     BookMeta bm = (BookMeta)is.getItemMeta();
/*  84 */     if (bm == null)
/*  85 */       return;  String side = bm.getPage(1);
/*  86 */     if (side == null)
/*  87 */       return;  String[] lines = side.split("\\r?\\n");
/*     */     
/*  89 */     Integer line = Integer.valueOf(0);
/*  90 */     for (String s : lines) {
/*  91 */       if (s != null && line.intValue() <= 3) {
/*  92 */         Integer i = Integer.valueOf(15);
/*  93 */         if (s.length() >= 15) { i = Integer.valueOf(15); } else { i = Integer.valueOf(s.length()); }
/*  94 */          String a = s.substring(0, i.intValue());
/*  95 */         if (a != null) {
/*  96 */           a = ChatColor.translateAlternateColorCodes('&', a);
/*  97 */           setText(line, a);
/*     */         } 
/*  99 */         Integer integer1 = line; line = Integer.valueOf(line.intValue() + 1);
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     if (line.intValue() != 3) {
/* 104 */       for (int i = line.intValue(); i <= 3; i++) {
/* 105 */         setText(Integer.valueOf(i), "");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void placetext() {
/* 112 */     if (getSignLocation().getBlock().getState() instanceof Sign && this.lines != null) {
/* 113 */       Sign sign = (Sign)getSignLocation().getBlock().getState();
/* 114 */       Integer i = Integer.valueOf(0);
/* 115 */       for (String s : this.lines) {
/* 116 */         if (i.intValue() > 3)
/* 117 */           break;  sign.setLine(i.intValue(), s);
/* 118 */         Integer integer = i; i = Integer.valueOf(i.intValue() + 1);
/*     */       } 
/* 120 */       sign.update(true, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getText() {
/* 125 */     if (this.signLoc == null || !getSignLocation().getBlock().getType().name().contains("SIGN")) return null; 
/* 126 */     Sign sign = (Sign)getSignLocation().getBlock().getState();
/* 127 */     return sign.getLines();
/*     */   }
/*     */   
/*     */   public void setText(Integer line, String text) {
/* 131 */     if (line == null || text == null)
/* 132 */       return;  if (getSignLocation() == null || !getSignLocation().getBlock().getType().name().contains("SIGN"))
/* 133 */       return;  Sign sign = (Sign)getSignLocation().getBlock().getState();
/* 134 */     sign.setLine(line.intValue(), text);
/* 135 */     sign.update(true, false);
/* 136 */     this.lines[line.intValue()] = text;
/*     */   }
/*     */   
/*     */   public void spawn(Location location) {}
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\garden\graveStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */