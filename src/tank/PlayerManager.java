/*
 * Copyright (c) 2016, asmateus
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package tank;

import java.awt.Point;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asmateus
 */
public class PlayerManager extends Thread
{
    
    private final ToysArea area;
    private final Timer timer = new Timer(true);
    
    public PlayerManager(ToysArea area)
    {
        this.area = area;
    }
    
    public void spawnPlayer()
    {
        Player p = new Player(Player.MACHINE);
        p.addToysArea(area);
        Tank t = new Tank(p, area, Tank.CONTINUOUS);
        t.addCollisionSystem(area.coll_sys);
        t.position = new Point(512, 0);
        p.subscribeTank(t);
        
        area.coll_sys.addSubscriber(t);
        area.enemies.add(t);
        area.toys.add(t);
        area.repaint();
        
        // Init AI
        p.personality = AI.CRAZY;
        p.initAI();
    }
    
    @Override
    public void run()
    {
        // Check if a new tank can be created
        //timer.scheduleAtFixedRate(deus, 0, 500);
        while(area.local.live_points > 0 || (area.enemies.size() > 0 && area.pg.world.getEnemyCounter().counter > 0)) {
            if(this.area.enemies.size() < 8) {
                if(this.area.pg.world.getEnemyCounter().getLocalCount() > 0) {
                    spawnPlayer();
                }
                
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(PlayerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
