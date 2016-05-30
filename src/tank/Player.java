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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asmateus
 * The player has Tanks, one or many (if player is an AI). The Tanks need to subscribe to 
 * the player to get feedback events
 * 
 */
public class Player
{
    private String name = "local";
    private final List<Tank> tanks;
    public int party;
    
    // local player code
    public static final int LOCAL = 1000;
    
    public Player(int party)
    {
        tanks = new ArrayList<>();
        this.party = party;
    }
    
    public void subscribeTank(Tank tank)
    {
        tanks.add(tank);
    }
    
    public void notifyTanks(int order)
    {
        tanks.stream().forEach((t) -> {
            t.playerIssuedOrder(order);
        });
    }
    
    public void setParty(int party)
    {
        this.party = party;
    }
    
    public String getName()
    {
        return name;
    }
    
    public List<Tank> getTanks()
    {
        return this.tanks;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
}
