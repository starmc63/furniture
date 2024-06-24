/*     */ package de.Ste3et_C0st.Furniture.Main;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Main.Event.redstoneEvent;
/*     */ import de.Ste3et_C0st.FurnitureLib.Crafting.Project;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.FurniturePlugin;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Function;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.plugin.Plugin;
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
/*     */ public class FurnitureHook
/*     */   extends FurniturePlugin
/*     */ {
/*  44 */   private final boolean editModels = FurnitureLib.getInstance().getConfig().getBoolean("config.editDiceFurnitureModels", false);
/*     */   
/*     */   public FurnitureHook(Plugin pluginInstance) {
/*  47 */     super(pluginInstance);
/*  48 */     Bukkit.getPluginManager().registerEvents((Listener)new redstoneEvent(), pluginInstance);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerProjects() {
/*     */     try {
/*  54 */       String modelFolder = isNewVersion() ? "Models113/" : "Models109/";
/*  55 */       String ending = isNewVersion() ? ".dModel" : ".yml";
/*  56 */       (new Project("HumanSkeleton", getPlugin(), getResource(modelFolder + "HumanSkeleton" + ending))).setSize(Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Type.CenterType.RIGHT);
/*  57 */       (new Project("CandyCane", getPlugin(), getResource(modelFolder + "CandyCane" + ending))).setSize(Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  58 */       (new Project("SnowGolem", getPlugin(), getResource(modelFolder + "SnowGolem" + ending))).setSize(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  59 */       (new Project("TV", getPlugin(), getResource(modelFolder + "TV" + ending))).setSize(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Type.CenterType.CENTER);
/*  60 */       (new Project("Chair", getPlugin(), getResource(modelFolder + "Chair" + ending))).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  61 */       (new Project("CampChair", getPlugin(), getResource(modelFolder + "CampChair" + ending))).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  62 */       (new Project("CactusPlant", getPlugin(), getResource(modelFolder + "CactusPlant" + ending))).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  63 */       (new Project("SleepingBag", getPlugin(), getResource(modelFolder + "SleepingBag" + ending))).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(2), Type.CenterType.RIGHT);
/*  64 */       (new Project("ChristmasTree", getPlugin(), getResource(modelFolder + "ChristmasTree" + ending))).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(2), Type.CenterType.RIGHT);
/*  65 */       (new Project("Table", getPlugin(), getResource(modelFolder + "Table" + ending))).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  66 */       (new Project("SchoolChair", getPlugin(), getResource(modelFolder + "SchoolChair" + ending))).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  67 */       (new Project("SchoolTable", getPlugin(), getResource(modelFolder + "SchoolTable" + ending))).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  68 */       (new Project("BlackBoard", getPlugin(), getResource(modelFolder + "BlackBoard" + ending))).setPlaceableSide(Type.PlaceableSide.SIDE).setSize(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Type.CenterType.RIGHT);
/*  69 */       (new Project("MailBox", getPlugin(), getResource(modelFolder + "MailBox" + ending))).setSize(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  70 */       (new Project("Sofa", getPlugin(), getResource(modelFolder + "Sofa" + ending))).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(3), Type.CenterType.RIGHT);
/*     */       
/*  72 */       (new Project("Catapult", getPlugin(), getResource(modelFolder + "Catapult" + ending), Type.PlaceableSide.TOP, de.Ste3et_C0st.Furniture.Objects.RPG.Catapult::new)).setSize(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(3), Type.CenterType.RIGHT);
/*  73 */       (new Project("Barrels", getPlugin(), getResource(modelFolder + "Barrels" + ending), Type.PlaceableSide.TOP, de.Ste3et_C0st.Furniture.Objects.outdoor.barrels::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  74 */       (new Project("WaxCandle", getPlugin(), getResource(modelFolder + "WaxCandle" + ending), Type.PlaceableSide.TOP, de.Ste3et_C0st.Furniture.Objects.light.WaxCandle::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  75 */       (new Project("Lantern", getPlugin(), getResource(modelFolder + "Lantern" + ending), Type.PlaceableSide.TOP, de.Ste3et_C0st.Furniture.Objects.light.WaxCandle::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  76 */       (new Project("Log", getPlugin(), getResource(modelFolder + "Log" + ending), de.Ste3et_C0st.Furniture.Objects.garden.log::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.CENTER);
/*  77 */       (new Project("Fence", getPlugin(), getResource(modelFolder + "Fence" + ending), de.Ste3et_C0st.Furniture.Objects.garden.fance::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  78 */       (new Project("Trunk", getPlugin(), getResource(modelFolder + "Trunk" + ending), de.Ste3et_C0st.Furniture.Objects.garden.Trunk::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(4), Type.CenterType.RIGHT);
/*  79 */       (new Project("Sunshade", getPlugin(), getResource(modelFolder + "Sunshade" + ending), de.Ste3et_C0st.Furniture.Objects.garden.sunshade::new)).setSize(Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  80 */       (new Project("Hammock", getPlugin(), getResource(modelFolder + "Hammock" + ending), de.Ste3et_C0st.Furniture.Objects.outdoor.hammock::new)).setSize(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(7), Type.CenterType.RIGHT);
/*  81 */       (new Project("Crossbow", getPlugin(), getResource(modelFolder + "Crossbow" + ending), de.Ste3et_C0st.Furniture.Objects.RPG.Crossbow::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  82 */       (new Project("Tent1", getPlugin(), getResource(modelFolder + "Tent1" + ending), de.Ste3et_C0st.Furniture.Objects.outdoor.tent_1::new)).setSize(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(5), Type.CenterType.RIGHT);
/*  83 */       (new Project("GraveStone", getPlugin(), getResource(modelFolder + "GraveStone" + ending), de.Ste3et_C0st.Furniture.Objects.garden.graveStone::new)).setSize(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Type.CenterType.CENTER);
/*  84 */       (new Project("Camera", getPlugin(), getResource(modelFolder + "Camera" + ending), de.Ste3et_C0st.Furniture.Objects.electric.camera::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  85 */       (new Project("LargeTable", getPlugin(), getResource(modelFolder + "LargeTable" + ending), de.Ste3et_C0st.Furniture.Objects.indoor.largeTable::new)).setSize(Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(2), Type.CenterType.RIGHT);
/*  86 */       (new Project("Campfire1", getPlugin(), getResource(modelFolder + "Campfire1" + ending), de.Ste3et_C0st.Furniture.Objects.outdoor.campfire_1::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  87 */       (new Project("Campfire2", getPlugin(), getResource(modelFolder + "Campfire2" + ending), de.Ste3et_C0st.Furniture.Objects.outdoor.campfire_2::new)).setSize(Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(2), Type.CenterType.RIGHT);
/*  88 */       (new Project("Tent2", getPlugin(), getResource(modelFolder + "Tent2" + ending), de.Ste3et_C0st.Furniture.Objects.outdoor.tent_2::new)).setSize(Integer.valueOf(6), Integer.valueOf(3), Integer.valueOf(5), Type.CenterType.RIGHT);
/*  89 */       (new Project("Tent3", getPlugin(), getResource(modelFolder + "Tent3" + ending), de.Ste3et_C0st.Furniture.Objects.outdoor.tent_3::new)).setSize(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(3), Type.CenterType.CENTER);
/*  90 */       (new Project("Streetlamp", getPlugin(), getResource(modelFolder + "Streetlamp" + ending), de.Ste3et_C0st.Furniture.Objects.electric.streetlamp::new)).setSize(Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(1), Type.CenterType.FRONT);
/*  91 */       (new Project("Billboard", getPlugin(), getResource(modelFolder + "Billboard" + ending), de.Ste3et_C0st.Furniture.Objects.electric.billboard::new)).setSize(Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(3), Type.CenterType.RIGHT);
/*  92 */       (new Project("WeaponStand", getPlugin(), getResource(modelFolder + "WeaponStand" + ending), de.Ste3et_C0st.Furniture.Objects.RPG.weaponStand::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  93 */       (new Project("Guillotine", getPlugin(), getResource(modelFolder + "Guillotine" + ending), de.Ste3et_C0st.Furniture.Objects.RPG.Guillotine::new)).setSize(Integer.valueOf(1), Integer.valueOf(5), Integer.valueOf(2), Type.CenterType.RIGHT);
/*  94 */       (new Project("FlowerPot", getPlugin(), getResource(modelFolder + "FlowerPot" + ending), de.Ste3et_C0st.Furniture.Objects.garden.TFlowerPot::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  95 */       (new Project("BearTrap", getPlugin(), getResource(modelFolder + "BearTrap" + ending), de.Ste3et_C0st.Furniture.Objects.trap.BearTrap::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  96 */       (new Project("TrashCan", getPlugin(), getResource(modelFolder + "TrashCan" + ending), de.Ste3et_C0st.Furniture.Objects.School.TrashCan::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  97 */       (new Project("Flag", getPlugin(), getResource(modelFolder + "Flag" + ending), de.Ste3et_C0st.Furniture.Objects.RPG.flag::new)).setSize(Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  98 */       (new Project("AdventCalender", getPlugin(), getResource(modelFolder + "AdventCalender" + ending), Type.PlaceableSide.TOP, de.Ste3et_C0st.Furniture.Objects.christmas.AdventCalender::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.RIGHT);
/*  99 */       (new Project("FireworkLauncher", getPlugin(), getResource(modelFolder + "FireworkLauncher" + ending), Type.PlaceableSide.TOP, de.Ste3et_C0st.Furniture.Objects.christmas.FireworkLauncher::new)).setSize(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Type.CenterType.CENTER);
/*     */       
/* 101 */       FurnitureLib.getInstance().getFurnitureManager().getProjects().stream().filter(pro -> pro.getPlugin().equals(getPlugin())).forEach(pro -> { if (Objects.nonNull(pro))
/*     */               pro.setEditorProject(this.editModels); 
/*     */           });
/* 104 */     } catch (Exception e) {
/* 105 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyPluginFunctions() {
/* 111 */     FurnitureLib.getInstance().getFurnitureManager().getProjects().stream()
/* 112 */       .filter(pro -> pro.getPlugin().getName().equals(getPlugin().getDescription().getName()))
/* 113 */       .forEach(Project::applyFunction);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onFurnitureLateSpawn(ObjectID obj) {}
/*     */ 
/*     */   
/*     */   public static boolean isNewVersion() {
/* 121 */     return FurnitureLib.isNewVersion();
/*     */   }
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Main\FurnitureHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */