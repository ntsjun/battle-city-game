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

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author asmateus
 */
public class World
{
    private final GraphDescriptor graph;
    private final GameArea container;
    private final JPanel playground = new JPanel();
    
    public World(GraphDescriptor graph, GameArea container)
    {
        this.graph = graph;
        this.container = container;
        setPlayground();
    }
    
    private void setPlayground()
    {
        playground.setLayout(new GridLayout(15, 17));
        playground.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
    }
    
    public void start()
    {
        GridBagConstraints c = new GridBagConstraints();
        // Add world title to game area
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 20;
        MenuOption world_title = new MenuOption(graph.getLevelName(), container);
        world_title.setForeground(new Color(250, 250, 250));
        world_title.setHorizontalAlignment(SwingConstants.CENTER);
        world_title.formatFont("resources/fonts/ARCADECLASSIC.ttf", 40.0f);
        container.add(world_title, c);
        
        // Adding playground to game area
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 1;
        c.ipady = 0;
        playground.setVisible(true);
        playground.setBackground(Color.BLUE);
        container.add(playground, c);
        container.updateUI();
        
        // Fill game area with graph descriptor content
        for(int i = 0; i < graph.graph.size(); ++i) {
            for(int j = 0; j < graph.graph.get(i).size(); ++j)
                playground.add(graph.graph.get(i).get(j));
        }
    }
}
