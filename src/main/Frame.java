package main;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;

public class Frame {
    static BufferedWriter writer;
    static BufferedReader read;
    static BufferedWriter keyWriter;
    static BufferedWriter resoWriter;  
    static String line;
    static StringBuffer sb; 
	private static void initGui(){		
		JFrame frame = new JFrame("Ingress_Dev");
		//frame.setDefaultCloseOperation(exiting());
		frame.setSize(800,600);
		//Frame m = new Frame();
		final Main f = new Main();	
		
		frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
            	try {           		   		
        			writer = new BufferedWriter(new FileWriter("inventory\\ap.txt",false));
        			keyWriter = new BufferedWriter(new FileWriter("inventory\\keys.txt",false));
        			resoWriter = new BufferedWriter(new FileWriter("inventory\\resos.txt",false));        			
        			//writer.write();
        			//writer.write(f.)
        			writer.write(f.app() + "");
        			System.out.println(f.app());
        			writer.close();
        			PrintWriter p = new PrintWriter(new FileWriter("inventory\\keys.txt"));      			
        			for(Object a: f.k){
        				p.println(a);
        			}
        			p.close();
        			keyWriter.close();
        			} catch (IOException ee) {
        			
        			ee.printStackTrace();
        		}
    			try {
					PrintWriter r = new PrintWriter(new FileWriter("inventory\\resos.txt"));					
					for(Object a: f.b){
						r.println(a);
					}
					r.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			try {
					PrintWriter r = new PrintWriter(new FileWriter("inventory\\mods.txt"));					
					for(Object a: f.mm){
						Mod m = (Mod)a;
						r.println(m.getType());
					}
					r.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    				for(Portal p:f.portals){
    				try {
						PrintWriter port = new PrintWriter(new FileWriter("portals\\" + p.namee() + ".txt"));
						port.println("x"+p.retX());
						port.println("y"+p.retY());
						port.println("t"+p.team());
						port.println("n"+p.mods.size());
						for(Mod m:p.mods){
							port.println("m"+m.getType());
						}
						port.println("r"+p.numReses());
						for(Resonator re:p.resonators){
							port.println("l"+re.getLevel());
							port.println(re.getX());
							port.println(re.getY());
						}
						port.println("w"+p.links);
						for(String s:p.linked){
							port.println("z"+s);
						}
						port.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				
    				}
    				try{
    					PrintWriter linkin = new PrintWriter(new FileWriter("MU\\links.txt"));
    					linkin.println(f.links.size());
    					for(Link l:f.links){
        					linkin.println(l.x1());
        					linkin.println(l.x2());
        					linkin.println(l.y1());
        					linkin.println(l.y2());
        					linkin.println(l.getTeam());
        				}
    					linkin.close();
    				}catch(IOException eee){}
    				try{
    					PrintWriter fieldin = new PrintWriter(new FileWriter("MU\\fields.txt"));
    					fieldin.println(f.fields.size());
    					for(Field ff:f.fields){
        					fieldin.println(ff.getP1x());
        					fieldin.println(ff.getP1y());
        					fieldin.println(ff.getP2x());
        					fieldin.println(ff.getP2y());
        					fieldin.println(ff.getP3x());
        					fieldin.println(ff.getP3y());
        					fieldin.println(ff.p1.name);
        					fieldin.println(ff.p2.name);
        					fieldin.println(ff.p3.name);
        					fieldin.println(ff.getTeam());
        				}
    					fieldin.close();
    				}catch(IOException eee){}
    				
                ((JFrame)(e.getComponent())).dispose();
                System.exit(0);
            }
            
        });
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		
		frame.add(f);
		frame.setResizable(false);
	    frame.setVisible(true);
	}
	
	public static void main(String[] args) {		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initGui();
            }
        });	
	}
}