package net.atopiamc.dev.captive.Listener;

import net.atopiamc.dev.captive.API.Events.PlayerGameLeaveEvent;
import net.atopiamc.dev.captive.API.Game.Game;
import net.atopiamc.dev.captive.API.Game.GameFunctions;
import net.atopiamc.dev.captive.API.Game.GamePlayer;
import net.atopiamc.dev.captive.API.GameAPI;
import net.atopiamc.dev.captive.API.Listener.GameReset;
import net.atopiamc.dev.captive.API.Listener.GameStart;
import net.atopiamc.dev.captive.API.Teams.Cop;
import net.atopiamc.dev.captive.API.Teams.Criminals;
import net.atopiamc.dev.captive.API.Teams.Prisoner;
import net.atopiamc.dev.captive.API.Teams.Teams;
import net.atopiamc.dev.captive.Kits.CopsKit;
import net.atopiamc.dev.captive.Kits.CriminalKit;
import net.atopiamc.dev.captive.Kits.Kit;
import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.CountdownTimer;
import net.atopiamc.dev.captive.Utils.ResetBlock;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameListener implements Listener {

    private HashMap<Game, CountdownTimer> timer;
    private HashMap<Player, Kit> kits;
    private HashMap<Game, Boolean> won;
    private ArrayList<Player> cops;
    private ArrayList<Player> criminals;
    private ArrayList<Player> prisoner;
    private HashMap<String, ArrayList<ResetBlock>> blocksPlaced;
    private HashMap<String, ArrayList<ResetBlock>> blocksBroke;
    private GameAPI api = Main.getPlugin().getApi();


    public GameListener() {
        this.timer = new HashMap<Game, CountdownTimer>();
        this.kits = new HashMap<Player, Kit>();
        this.won = new HashMap<Game, Boolean>();
        this.blocksPlaced = new HashMap<String, ArrayList<ResetBlock>>();
        this.blocksBroke = new HashMap<String, ArrayList<ResetBlock>>();
        this.cops = Teams.getCopTeam();
        this.criminals = Teams.getPrisonerTeam();
        this.prisoner = Teams.getPrisonerTeam();
    }

    @EventHandler
    public void onGameLeave(PlayerGameLeaveEvent e) {
        Player p = e.getPlayer();
        if (won.containsKey(e.getGame())) {
            return;
        }
        if (cops.contains(p)) {
            cops.remove(p);
            if (cops.size() <= 0) {
                firstWin(e.getGame());
                won.put(e.getGame(), true);
            }
        }
        else if (criminals.contains(p)) {
            criminals.remove(p);
            if (criminals.size() <= 0) {
                secondWin(e.getGame());
                won.put(e.getGame(), true);
            }
        }
        else if (prisoner.contains(p)) {
            prisoner.remove(p);
            if (prisoner.size() <= 0) {
                firstWin(e.getGame());
                won.put(e.getGame(), true);
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getEntity();
        if (api.inGame(p)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (!api.inGame(p)) {
            return;
        }
        Game game = api.gamePlayers.get(p).getGame();
        ArrayList<ResetBlock> list = blocksPlaced.get(game.getName()) == null ? new ArrayList<>() : blocksPlaced.get(game.getName());

        list.add(new ResetBlock(e.getBlock().getLocation(), e.getBlock()));
        blocksPlaced.put(game.getName(), list);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!api.inGame(p)) {
            return;
        }
        Game game = api.gamePlayers.get(p).getGame();
        ArrayList<ResetBlock> list = blocksBroke.get(game.getName()) == null ? new ArrayList<>() : blocksBroke.get(game.getName());

        list.add(new ResetBlock(e.getBlock().getLocation(), e.getBlock()));
        blocksBroke.put(game.getName(), list);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getDamager();
        Player t = (Player) e.getEntity();

        if (Teams.getCopTeam().contains(p) && Teams.getCopTeam().contains(t)) {
            e.setCancelled(true);
        } else if (Teams.getCriminalTeam().contains(p) && Teams.getCriminalTeam().contains(t)) {
            e.setCancelled(true);
        } else {
            return;
        }
    }

    @EventHandler
    public void onReset(GameReset e) {
        won.remove(e.getGame());
        if (blocksBroke.get(e.getGame().getName()) == null && blocksPlaced.get(e.getGame().getName()) == null) {
            return;
        }
        if (blocksPlaced.get(e.getGame().getName()) != null) {
            for (ResetBlock block : blocksPlaced.get(e.getGame().getName())) {
                block.getLocation().getWorld().getBlockAt(block.getLocation()).setType(Material.AIR);
            }
        }
        if (blocksBroke.get(e.getGame().getName()) != null) {
            for (ResetBlock block : blocksBroke.get(e.getGame().getName())) {
                block.getLocation().getWorld().getBlockAt(block.getLocation()).setType(block.getType());
            }
        }
        for (Player p : Teams.getPrisonerTeam()) {
            p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        }

    }

    @EventHandler
    public void onStart(GameStart e) {
        Location copSpawn = Cop.getInstance().getCopSpawn();
        Location criminalSpawn = Criminals.getInstance().getCriminalsSpawn();
        Location prisonerSpawn = Prisoner.getInstance().getPrisonerSpawn();
        int TeamID;
        for (int i = 0; i < api.gamePlayers.size(); ++i) {
            Random randomTeam = new Random();
            Player p = api.gamePlayers.get(i).getPlayer();
            if (prisoner.size() != 0) {
                TeamID = randomTeam.nextInt(1);
            } else {
                TeamID = randomTeam.nextInt(2);
            }
            // Make Teams.AddXTeam to Teams.add(Team, Player);
            if (TeamID == 0) {
                if (!cops.contains(p)) {
                    if (!Teams.addCopTeam(p)) {
                        Teams.addCriminalTeam(p);
                    }
                    continue;
                }
            }
            if (TeamID == 1) {
                if (!criminals.contains(p)) {
                    if (!Teams.addCriminalTeam(p)) {
                        Teams.addCopTeam(p);
                    }
                    continue;
                }
            }
            if (TeamID == 2) {
                if (prisoner.isEmpty()) {
                    Teams.addPrisonerTeam(p);
                    continue;
                }
            }
        }

        /*
        for (GamePlayer p : api.gamePlayers.values()) {
            for (int i = 0; i < api.gamePlayers.size(); ++i) {
                    Random randomTeam = new Random();
                    if (prisoner.size() == 1) {
                        TeamID = randomTeam.nextInt(1);
                    } else {
                        TeamID = randomTeam.nextInt(2);
                    }
                    if (TeamID == 0){
                        if (!cops.contains(p.getPlayer())) {
                            Teams.addCopTeam(p.getPlayer());
                        }
                    }
                    if (TeamID == 1){
                        if (!criminals.contains(p.getPlayer())) {
                            Teams.addCriminalTeam(p.getPlayer());
                        }
                    }
                    if (TeamID == 2){
                        if (prisoner.isEmpty()) {
                            Teams.addPrisonerTeam(p.getPlayer());
                        }
                    }
              }
          }
         */
        for (Player p : cops) {
            p.teleport(copSpawn);
            CopsKit.getInstance().receiveItems(p);
            p.sendMessage(Utils.Color("&9You are in the Cop Team!"));
            p.sendMessage(Utils.Color("&7Objective:"));
            p.sendMessage(Utils.Color("&7Make sure the Criminals don't break out the Prisoner!"));
            startDelay(p);
        }
        for (Player p : criminals) {
            p.teleport(criminalSpawn);
            CriminalKit.getInstance().receiveItems(p);
            p.sendMessage(Utils.Color("&9You are in the Criminal Team!"));
            p.sendMessage(Utils.Color("&7Objective:"));
            p.sendMessage(Utils.Color("&7Take out the Cops and retrieve the Prisoner!"));
            p.sendMessage(Utils.Color("&7&oBring the prisoner to your spawn."));
            startDelay(p);
        }
        for (Player p : prisoner) {
            p.teleport(prisonerSpawn);
            p.getInventory().addItem(new ItemStack(Material.WOOD_PICKAXE, 1));
            p.getInventory().addItem(new ItemStack(Material.BOW, 1));
            p.getInventory().addItem(new ItemStack(Material.ARROW, 20));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 1));
            p.sendMessage(Utils.Color("&9You are the Prisoner!"));
            p.sendMessage(Utils.Color("&7Objective:"));
            p.sendMessage(Utils.Color("&7Wait for your fellow Criminals to bust you out!"));
            p.sendMessage(Utils.Color("&7&oYou can try to escape yourself with your tools."));
            startDelay(p);
        }
        gameTimer(e.getGame());
    }

    int i = 0;

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (GameAPI.gamePlayers.get(p) == null){
            return;
        }
        GamePlayer gp = GameAPI.gamePlayers.get(p);
        Game game = gp.getGame();
        if (i == 0) {
            if (Teams.getPrisonerTeam().contains(p)) {
                if (p.getLocation().distance(Criminals.getInstance().getCriminalsSpawn()) <= 3) {
                    secondWin(game);
                    won.put(game, true);
                    i++;
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

    private void firstWin(Game game) {
        for (Player ppl : Game.players) {
            ppl.sendTitle(Utils.Color("&9Cop Team"), Utils.Color("&7Has won."));
            ppl.playSound(ppl.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> game.stop(), 100L);
    }
    private void secondWin(Game game) {
        for (Player ppl : Game.players) {
            ppl.sendTitle(Utils.Color("&cCriminal Team"), Utils.Color("&7Has won."));
            ppl.playSound(ppl.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> game.stop(), 100L);
    }

    public void startDelay(Player p) {
        new BukkitRunnable() {
            int i = 10;
            @Override
            public void run() {
                if (i == 0) {
                    p.sendMessage(Utils.Color("&b&lCaptive has Started!"));
                    cancel();
                }
                p.sendMessage(Utils.Color("&9Captive starts in " + i + " seconds."));
                p.teleport(p.getLocation());
                i--;
            }
        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }

    public void gameTimer(Game game) {
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i == 2400) {
                    firstWin(game);
                    won.put(game, true);
                    cancel();
                }
                if (won.containsKey(game)) {
                    cancel();
                }
                if (game.inLobby() == true) {
                    cancel();
                }
                i++;
            }
        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }

}
