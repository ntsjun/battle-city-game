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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asmateus
 * This class is a repainter that will update the position of all the elements
 * that needs to be updated
 */
public class TimeLine extends Thread
{
    private final List<Element> elements = new ArrayList<>();
    private final List<Integer> directions = new ArrayList<>();
    
    public TimeLine()
    {
        
    }
    
    public void addElement(Element e, int direction)
    {
        System.out.println("Someone added an element");
        this.elements.add(e);
        this.directions.add(direction);
    }
    
    public synchronized void removeElement(Element e)
    {
        System.out.println("Someone removed an element");
        this.directions.remove(this.elements.indexOf(e));
        this.elements.remove(e);
    }
    
    @Override
    public void run()
    {
        while(true) {
            //update();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeLine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void update()
    {
        //System.out.println(this.elements);
        for(int i = 0; i < this.elements.size(); ++i) {
            //this.elements.get(i).move(/*this.directions.get(i)*/);
        }
    }
}
