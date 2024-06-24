/*     */ package de.Ste3et_C0st.Furniture.Objects.christmas;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*     */ import de.Ste3et_C0st.Furniture.Objects.garden.config;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.ConsoleCommandSender;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Firework;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerTeleportEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.FireworkMeta;
/*     */ import org.bukkit.util.io.BukkitObjectInputStream;
/*     */ import org.bukkit.util.io.BukkitObjectOutputStream;
/*     */ import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
/*     */ 
/*     */ public class AdventCalender
/*     */   extends Furniture
/*     */   implements Listener {
/*  46 */   double sub = 0.9D;
/*     */   
/*  48 */   HashMap<Integer, ItemStack[]> isList = (HashMap)new HashMap<>();
/*  49 */   HashMap<UUID, Integer> uuidList = new HashMap<>();
/*     */   Player p;
/*     */   int i;
/*  52 */   int currentDay = 0;
/*     */   
/*     */   Inventory inv;
/*  55 */   String ac_1 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFiYzJiY2ZiMmJkMzc1OWU2YjFlODZmYzdhNzk1ODVlMTEyN2RkMzU3ZmMyMDI4OTNmOWRlMjQxYmM5ZTUzMCJ9fX0=";
/*  56 */   String ac_2 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNkOWVlZWU4ODM0Njg4ODFkODM4NDhhNDZiZjMwMTI0ODVjMjNmNzU3NTNiOGZiZTg0ODczNDE0MTk4NDcifX19";
/*  57 */   String ac_3 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQ0ZWFlMTM5MzM4NjBhNmRmNWU4ZTk1NTY5M2I5NWE4YzNiMTVjMzZiOGI1ODc1MzJhYzA5OTZiYzM3ZTUifX19";
/*  58 */   String ac_4 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDJlNzhmYjIyNDI0MjMyZGMyN2I4MWZiY2I0N2ZkMjRjMWFjZjc2MDk4NzUzZjJkOWMyODU5ODI4N2RiNSJ9fX0=";
/*  59 */   String ac_5 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ1N2UzYmM4OGE2NTczMGUzMWExNGUzZjQxZTAzOGE1ZWNmMDg5MWE2YzI0MzY0M2I4ZTU0NzZhZTIifX19";
/*  60 */   String ac_6 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzM0YjM2ZGU3ZDY3OWI4YmJjNzI1NDk5YWRhZWYyNGRjNTE4ZjVhZTIzZTcxNjk4MWUxZGNjNmIyNzIwYWIifX19";
/*  61 */   String ac_7 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmRiNmViMjVkMWZhYWJlMzBjZjQ0NGRjNjMzYjU4MzI0NzVlMzgwOTZiN2UyNDAyYTNlYzQ3NmRkN2I5In19fQ==";
/*  62 */   String ac_8 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkxOTQ5NzNhM2YxN2JkYTk5NzhlZDYyNzMzODM5OTcyMjI3NzRiNDU0Mzg2YzgzMTljMDRmMWY0Zjc0YzJiNSJ9fX0=";
/*  63 */   String ac_9 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY3Y2FmNzU5MWIzOGUxMjVhODAxN2Q1OGNmYzY0MzNiZmFmODRjZDQ5OWQ3OTRmNDFkMTBiZmYyZTViODQwIn19fQ==";
/*  64 */   String ac_0 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMGViZTdlNTIxNTE2OWE2OTlhY2M2Y2VmYTdiNzNmZGIxMDhkYjg3YmI2ZGFlMjg0OWZiZTI0NzE0YjI3In19fQ==";
/*  65 */   String ac_NULL = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTBlOWQyYmViODRiMzJlM2YxNWUzODBjYzJjNTUxMDY0MjkxMWE1MTIxMDVmYTJlYzY3OWJjNTQwZmQ4MTg0In19fQ==";
/*     */   
/*     */   public AdventCalender(ObjectID id) {
/*  68 */     super(id);
/*  69 */     spawn(id.getStartLocation());
/*  70 */     load();
/*  71 */     check();
/*  72 */     Bukkit.getPluginManager().registerEvents(this, getPlugin());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/*  77 */     if (getObjID() == null)
/*  78 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  79 */       return;  if (player == null)
/*  80 */       return;  if (canBuild(player)) {
/*  81 */       destroy(player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/*  87 */     if (getObjID() == null)
/*  88 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*  89 */       return;  if (player == null)
/*  90 */       return;  ItemStack is = player.getInventory().getItemInMainHand();
/*  91 */     if (is == null) { open(player); return; }
/*  92 */      if (is.getType() == null) { open(player); return; }
/*  93 */      if (is.getType().equals(Material.ARROW)) {
/*  94 */       if (this.p != null) { open(player); return; }
/*  95 */        if (!getObjID().getUUID().equals(player.getUniqueId())) { open(player); return; }
/*  96 */        if (!canBuild(player))
/*  97 */         return;  this.i = is.getAmount();
/*  98 */       if (this.i > 31 || this.i < 1)
/*  99 */         return;  this.p = player;
/* 100 */       if (this.isList.containsKey(Integer.valueOf(this.i))) {
/* 101 */         openInventory(this.isList.get(Integer.valueOf(this.i)));
/*     */       } else {
/* 103 */         openInventory((ItemStack[])null);
/*     */       } 
/*     */     } else {
/* 106 */       open(player);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void open(Player p) {
/* 111 */     if (this.isList.containsKey(Integer.valueOf(getDay()))) {
/* 112 */       if (this.uuidList.containsKey(p.getUniqueId()) && (
/* 113 */         (Integer)this.uuidList.get(p.getUniqueId())).intValue() == getDay()) {
/*     */         return;
/*     */       }
/*     */       
/* 117 */       ItemStack[] stack = this.isList.get(Integer.valueOf(getDay()));
/* 118 */       for (ItemStack iS : stack) {
/* 119 */         if (iS != null && iS.getType() != null) {
/* 120 */           if (iS.getType().equals(Material.valueOf(FurnitureHook.isNewVersion() ? "FIREWORK_ROCKET" : "FIREWORK"))) {
/* 121 */             Firework fw = (Firework)getWorld().spawnEntity(getCenter(), EntityType.FIREWORK);
/* 122 */             FireworkMeta meta = (FireworkMeta)iS.getItemMeta();
/* 123 */             fw.setFireworkMeta(meta); continue;
/* 124 */           }  if (iS.getType().equals(Material.NAME_TAG)) {
/* 125 */             if (iS.getItemMeta() != null && 
/* 126 */               iS.getItemMeta().hasDisplayName()) {
/* 127 */               String name = iS.getItemMeta().getDisplayName();
/* 128 */               if (name.startsWith("@PLAYER ")) {
/* 129 */                 name = name.replace("%player%", p.getName());
/* 130 */                 name = name.replace("@PLAYER ", "");
/* 131 */                 name = ChatColor.translateAlternateColorCodes('&', name);
/* 132 */                 p.sendMessage(name);
/* 133 */               } else if (name.startsWith("@BROADCAST ")) {
/* 134 */                 name = name.replace("%player%", p.getName());
/* 135 */                 name = name.replace("@BROADCAST ", "");
/* 136 */                 name = ChatColor.translateAlternateColorCodes('&', name);
/* 137 */                 getLib().getServer().broadcastMessage(name);
/*     */               } 
/*     */             } 
/*     */             continue;
/*     */           } 
/* 142 */           if (iS.getItemMeta() != null && 
/* 143 */             iS.getItemMeta().getDisplayName() != null) {
/* 144 */             if (iS.getItemMeta().getDisplayName().startsWith("@CONSOLE ")) {
/* 145 */               if (isOP()) {
/* 146 */                 String s = iS.getItemMeta().getDisplayName();
/* 147 */                 s = s.replace("@CONSOLE ", "");
/* 148 */                 s = s.replace("%player%", p.getName());
/* 149 */                 ConsoleCommandSender sender = Bukkit.getConsoleSender();
/* 150 */                 Bukkit.dispatchCommand((CommandSender)sender, s);
/*     */               }  continue;
/* 152 */             }  if (iS.getItemMeta().getDisplayName().startsWith("@PLAYER ")) {
/* 153 */               if (isOP()) {
/* 154 */                 String s = iS.getItemMeta().getDisplayName();
/* 155 */                 s = s.replace("@PLAYER ", "");
/* 156 */                 s = s.replace("%player%", p.getName());
/* 157 */                 p.chat("/" + s);
/*     */               } 
/*     */               continue;
/*     */             } 
/*     */           } 
/* 162 */           p.getInventory().addItem(new ItemStack[] { iS });
/*     */         } 
/*     */         continue;
/*     */       } 
/* 166 */       savePlayer(p.getUniqueId());
/* 167 */       this.uuidList.put(p.getUniqueId(), Integer.valueOf(getDay()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isOP() {
/* 172 */     if (getObjID().getUUID() != null) {
/* 173 */       UUID uuid = getObjID().getUUID();
/* 174 */       OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
/* 175 */       if (player == null) return false; 
/* 176 */       return player.isOp();
/*     */     } 
/* 178 */     return false;
/*     */   }
/*     */   
/*     */   public static String itemStackArrayToBase64(ItemStack[] items) {
/*     */     try {
/* 183 */       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 184 */       BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
/* 185 */       dataOutput.writeInt(items.length);
/* 186 */       for (int i = 0; i < items.length; i++) {
/* 187 */         ItemStack is = items[i];
/* 188 */         dataOutput.writeObject(is);
/*     */       } 
/* 190 */       dataOutput.close();
/* 191 */       return Base64Coder.encodeLines(outputStream.toByteArray());
/* 192 */     } catch (Exception e) {
/* 193 */       e.printStackTrace();
/*     */       
/* 195 */       return "";
/*     */     } 
/*     */   }
/*     */   public static ItemStack[] itemStackArrayFromBase64(String data) throws IOException {
/*     */     try {
/* 200 */       ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
/* 201 */       BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
/* 202 */       ItemStack[] items = new ItemStack[dataInput.readInt()];
/* 203 */       for (int i = 0; i < items.length; i++) {
/* 204 */         items[i] = (ItemStack)dataInput.readObject();
/*     */       }
/* 206 */       dataInput.close();
/* 207 */       return items;
/* 208 */     } catch (ClassNotFoundException e) {
/* 209 */       throw new IOException("Unable to decode class type.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void load() {
/* 214 */     config conf = new config();
/* 215 */     FileConfiguration file = conf.getConfig(getObjID().getSerial(), "plugin/AdventCalender/Data/");
/* 216 */     if (file == null)
/* 217 */       return;  for (int i = 1; i < 32; i++) {
/* 218 */       if (file.isSet(i + "")) {
/* 219 */         String s = file.getString(i + "");
/*     */         
/* 221 */         try { ItemStack[] is = itemStackArrayFromBase64(s);
/* 222 */           this.isList.put(Integer.valueOf(i), is); }
/* 223 */         catch (IOException e) { e.printStackTrace(); }
/*     */       
/*     */       } 
/*     */     } 
/* 227 */     conf = new config();
/* 228 */     file = conf.getConfig(getObjID().getSerial() + "_Players", "plugin/AdventCalender/Data/");
/* 229 */     if (file == null)
/* 230 */       return;  if (!file.contains("Players"))
/* 231 */       return;  for (String s : file.getConfigurationSection("Players").getKeys(false)) {
/* 232 */       UUID uuid = UUID.fromString(s);
/* 233 */       int j = file.getInt("Players." + s);
/* 234 */       this.uuidList.put(uuid, Integer.valueOf(j));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void save(int i) {
/* 239 */     config conf = new config();
/* 240 */     FileConfiguration file = conf.getConfig(getObjID().getSerial(), "plugin/AdventCalender/Data/");
/* 241 */     file.set(i + "", getSerialze(i));
/* 242 */     conf.saveConfig(getObjID().getSerial(), file, "plugin/AdventCalender/Data/");
/*     */   }
/*     */   
/*     */   public void savePlayer(UUID uuid) {
/* 246 */     if (uuid != null) {
/* 247 */       config conf = new config();
/* 248 */       FileConfiguration file = conf.getConfig(getObjID().getSerial() + "_Players", "plugin/AdventCalender/Data/");
/* 249 */       file.set("Players." + uuid.toString(), Integer.valueOf(getDay()));
/* 250 */       conf.saveConfig(getObjID().getSerial() + "_Players", file, "plugin/AdventCalender/Data/");
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getSerialze(int i) {
/* 255 */     if (!this.isList.containsKey(Integer.valueOf(i))) return ""; 
/* 256 */     return itemStackArrayToBase64(this.isList.get(Integer.valueOf(i)));
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onJoin(PlayerJoinEvent e) {
/* 261 */     check();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onTeleport(PlayerTeleportEvent e) {
/* 266 */     check();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onClose(InventoryCloseEvent e) {
/* 271 */     if (this.p == null)
/* 272 */       return;  if (e.getInventory() == null)
/* 273 */       return;  if (!e.getInventory().equals(this.inv))
/* 274 */       return;  if (!e.getPlayer().equals(this.p))
/* 275 */       return;  this.isList.put(Integer.valueOf(this.i), e.getInventory().getContents());
/* 276 */     save(this.i);
/* 277 */     this.p = null;
/* 278 */     this.inv = null;
/*     */   }
/*     */   
/*     */   private void openInventory(ItemStack[] is) {
/* 282 */     this.inv = Bukkit.createInventory(null, 54, "§8Christmas Reward [§c" + this.i + "§8]");
/* 283 */     if (is != null) this.inv.setContents(is); 
/* 284 */     this.p.openInventory(this.inv);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawn(Location arg0) {}
/*     */ 
/*     */   
/*     */   public int getDay() {
/* 293 */     DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
/* 294 */     Date today = new Date();
/*     */     try {
/* 296 */       Date todayWithZeroTime = formatter.parse(formatter.format(today));
/* 297 */       int i = todayWithZeroTime.getDate();
/* 298 */       return i;
/* 299 */     } catch (ParseException e) {
/* 300 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMonth() {
/* 306 */     DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
/* 307 */     Date today = new Date();
/*     */     try {
/* 309 */       Date todayWithZeroTime = formatter.parse(formatter.format(today));
/* 310 */       int i = todayWithZeroTime.getMonth();
/* 311 */       return i;
/* 312 */     } catch (ParseException e) {
/* 313 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void check() {
/* 319 */     if (this.currentDay == getDay())
/* 320 */       return;  if (getfAsList() == null)
/* 321 */       return;  ItemStack[] is = getStack();
/* 322 */     fContainerEntity stand1 = null;
/* 323 */     fContainerEntity stand2 = null;
/* 324 */     for (fEntity stand : getfAsList()) {
/* 325 */       if (stand.getName().equalsIgnoreCase("#Advent1#")) {
/* 326 */         stand1 = fContainerEntity.class.cast(stand); continue;
/* 327 */       }  if (stand.getName().equalsIgnoreCase("#Advent2#")) {
/* 328 */         stand2 = fContainerEntity.class.cast(stand);
/*     */       }
/*     */     } 
/* 331 */     if (stand1 != null) stand1.setHelmet(is[0]); 
/* 332 */     if (stand2 != null) stand2.setHelmet(is[1]); 
/* 333 */     this.currentDay = getDay();
/* 334 */     update();
/*     */   }
/*     */   
/*     */   public ItemStack[] getStack() {
/* 338 */     ItemStack[] stack = new ItemStack[2];
/* 339 */     stack[0] = getSkull(this.ac_NULL);
/* 340 */     stack[1] = getSkull(this.ac_NULL);
/* 341 */     int i = getDay();
/* 342 */     int y = getMonth();
/* 343 */     if (y == 11)
/* 344 */     { switch (i)
/*     */       { case 1:
/* 346 */           stack[0] = getSkull(this.ac_0);
/* 347 */           stack[1] = getSkull(this.ac_1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 423 */           return stack;case 2: stack[0] = getSkull(this.ac_0); stack[1] = getSkull(this.ac_2); return stack;case 3: stack[0] = getSkull(this.ac_0); stack[1] = getSkull(this.ac_3); return stack;case 4: stack[0] = getSkull(this.ac_0); stack[1] = getSkull(this.ac_4); return stack;case 5: stack[0] = getSkull(this.ac_0); stack[1] = getSkull(this.ac_5); return stack;case 6: stack[0] = getSkull(this.ac_0); stack[1] = getSkull(this.ac_6); return stack;case 7: stack[0] = getSkull(this.ac_0); stack[1] = getSkull(this.ac_7); return stack;case 8: stack[0] = getSkull(this.ac_0); stack[1] = getSkull(this.ac_8); return stack;case 9: stack[0] = getSkull(this.ac_0); stack[1] = getSkull(this.ac_9); return stack;case 10: stack[0] = getSkull(this.ac_1); stack[1] = getSkull(this.ac_0); return stack;case 11: stack[0] = getSkull(this.ac_1); stack[1] = getSkull(this.ac_1); return stack;case 12: stack[0] = getSkull(this.ac_1); stack[1] = getSkull(this.ac_2); return stack;case 13: stack[0] = getSkull(this.ac_1); stack[1] = getSkull(this.ac_3); return stack;case 14: stack[0] = getSkull(this.ac_1); stack[1] = getSkull(this.ac_4); return stack;case 15: stack[0] = getSkull(this.ac_1); stack[1] = getSkull(this.ac_5); return stack;case 16: stack[0] = getSkull(this.ac_1); stack[1] = getSkull(this.ac_6); return stack;case 17: stack[0] = getSkull(this.ac_1); stack[1] = getSkull(this.ac_7); return stack;case 18: stack[0] = getSkull(this.ac_1); stack[1] = getSkull(this.ac_8); return stack;case 19: stack[0] = getSkull(this.ac_1); stack[1] = getSkull(this.ac_9); return stack;case 20: stack[0] = getSkull(this.ac_2); stack[1] = getSkull(this.ac_0); return stack;case 21: stack[0] = getSkull(this.ac_2); stack[1] = getSkull(this.ac_1); return stack;case 22: stack[0] = getSkull(this.ac_2); stack[1] = getSkull(this.ac_2); return stack;case 23: stack[0] = getSkull(this.ac_2); stack[1] = getSkull(this.ac_3); return stack;case 24: stack[0] = getSkull(this.ac_2); stack[1] = getSkull(this.ac_4); return stack; }  stack[0] = getSkull(this.ac_NULL); stack[1] = getSkull(this.ac_NULL); }  return stack;
/*     */   }
/*     */   
/*     */   public String generateSessionKey(int length) {
/* 427 */     String alphabet = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
/* 428 */     int n = alphabet.length();
/* 429 */     String result = new String();
/* 430 */     Random r = new Random();
/* 431 */     for (int i = 0; i < length; ) { result = result + alphabet.charAt(r.nextInt(n)); i++; }
/* 432 */      return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getSkull(String s) {
/* 437 */     ItemStack skull = new ItemStack(Material.valueOf(FurnitureHook.isNewVersion() ? "PLAYER_HEAD" : "SKULL_ITEM"), 1);
/* 438 */     if (FurnitureHook.isNewVersion()) skull.setDurability((short)3); 
/* 439 */     UUID hashAsId = new UUID(s.hashCode(), s.hashCode());
/* 440 */     return Bukkit.getUnsafe().modifyItemStack(skull, "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + s + "\"}]}}}");
/*     */   }
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\christmas\AdventCalender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */