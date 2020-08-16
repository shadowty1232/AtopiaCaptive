package net.atopiamc.dev.captive.Utils;

import net.atopiamc.dev.captive.Main;
import org.bukkit.Bukkit;

import java.util.function.Consumer;

public class CountdownTimer implements Runnable {
    private final Main plugin;

    private Integer assignedTaskId;

    private final int seconds;
    private int secondsLeft;

    private final Consumer<CountdownTimer> everySecond;
    private final Runnable beforeTimer;
    private final Runnable afterTimer;

    public CountdownTimer(Main plugin, int seconds, Runnable beforeTimer, Runnable afterTimer, Consumer<CountdownTimer> everySecond)
    {
        this.plugin = plugin;

        this.seconds = seconds;
        this.secondsLeft = seconds;

        this.beforeTimer = beforeTimer;
        this.afterTimer = afterTimer;
        this.everySecond = everySecond;
    }

    @Override
    public void run()
    {
        if (secondsLeft < 1)
        {
            afterTimer.run();

            if (assignedTaskId != null) Bukkit.getScheduler().cancelTask(assignedTaskId);
            return;
        }

        if (secondsLeft == seconds) beforeTimer.run();

        everySecond.accept(this);

        secondsLeft--;
    }

    public int getTotalSeconds()
    {
        return seconds;
    }

    public int getSecondsLeft()
    {
        return secondsLeft;
    }

    public int getAssignedTaskId()
    {
        return assignedTaskId;
    }

    public void scheduleTimer()
    {
        this.assignedTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, 20L);
    }
}
