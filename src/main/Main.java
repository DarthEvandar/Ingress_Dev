package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;



@SuppressWarnings("serial")
public class Main extends JPanel implements Runnable{
	Rectangle player;
	Ellipse2D radius;
	ArrayList<Portal> portals = new ArrayList<Portal>();
	ArrayList<Link> links = new ArrayList<Link>();
	ArrayList<Field> fields = new ArrayList<Field>();
	ArrayList<Line2D> intr = new ArrayList<Line2D>();
	ArrayList<Polygon> poly = new ArrayList<Polygon>();
	ArrayList<Ellipse2D> circ = new ArrayList<Ellipse2D>();
	boolean burst = false;
	int firelev = 0;
	int currentRadius = 0;
	int x = 200;
	int y = 200;
	String move;
	Thread runner;
	boolean inInventory = false;
	BufferedReader read;
	Bag b = new HashBag();
	Bag k = new HashBag();
	Bag bb = new HashBag();
	Player p;
	boolean rightTrue,leftTrue,upTrue,downTrue = false;
	public void start(){
		if(runner==null){
			runner = new Thread(this);
			runner.start();
		}
	}
	public void stop(){
		if(runner!=null){
			runner.interrupt();
			runner = null;
		}
	}
	protected ImageIcon createImageIcon(String path,
            String description) {
				java.net.URL imgURL = getClass().getResource(path);
					if (imgURL != null) {
							return new ImageIcon(imgURL, description);
					} else {
						System.err.println("Couldn't find file: " + path);
						return null;
					}
			}			    		 
	 ImageIcon icon;
	 JButton deployy;
	 Object[] optionss;
	 JOptionPane opt;
	 JFrame status;	    				
	 JLabel portalStatus;
	 Portal pp;
	 JButton dep1;
	 JButton dep2;
	 JButton dep3;
	 JButton dep4;
	 JButton dep5;
	 JButton dep6;
	 JButton dep7;
	 JButton dep8;
	 int ressy;
	public void deployInit(){
		//System.out.println(it.toString());
		status = new JFrame();
		//itemss = new JComboBox(it);
		portalStatus = new JLabel(pp.resonators.toString());
		//icon = createImageIcon("res" + 2 + ".png","Level 1");
		deployy = new JButton("Deploy");
		if(b.contains(1)){
		dep1 = new JButton("Level 1 x" + b.getCount(1));
		dep1.setIcon(createImageIcon("resonator1.png", ""));
		}
		if(b.contains(2)){
		dep2 = new JButton("Level 2 x" + b.getCount(2));	
		dep2.setIcon(createImageIcon("resonator2.png",""));
		}
		if(b.contains(3)){
		dep3 = new JButton("Level 3 x" + b.getCount(3));
		dep3.setIcon(createImageIcon("resonator3.png",""));
		}
		if(b.contains(4)){
		dep4 = new JButton("Level 4 x" + b.getCount(4));
		dep4.setIcon(createImageIcon("resonator4.png",""));
		}
		if(b.contains(5)){
		dep5 = new JButton("Level 5 x" + b.getCount(5));
		dep5.setIcon(createImageIcon("resonator5.png",""));
		}
		if(b.contains(6)){
		dep6 = new JButton("Level 6 x" + b.getCount(6));
		dep6.setIcon(createImageIcon("resonator6.png",""));
		}
		if(b.contains(7)){
		dep7 = new JButton("Level 7 x" + b.getCount(7));
		dep7.setIcon(createImageIcon("resonator7.png",""));
		}
		if(b.contains(8)){
		dep8 = new JButton("Level 8 x" + b.getCount(8));
		dep8.setIcon(createImageIcon("resonator8.png",""));
		}
		optionss = new Object[]{deployy,portalStatus,dep1,dep2,dep3,dep4,dep5,dep6,dep7,dep8};
		opt = new JOptionPane(optionss);
		JScrollPane scrolling = new JScrollPane(opt);
		status.add(scrolling);
		status.validate();
		status.repaint();
	}
	public int app(){
		return p.apLevel();
	}
	public Main(){		
		p = new Player();
		/*portals.add(new Portal(200,200,"p1"));
		portals.add(new Portal(50,70,"p2"));
		portals.add(new Portal(10,40,"p3"));
		portals.add(new Portal(100,100,"p4"));
		portals.add(new Portal(300,100,"p5"));
		portals.add(new Portal(20,200,"p6"));
		portals.add(new Portal(340,70,"p7"));
		portals.add(new Portal(360,50,"p8"));
		portals.add(new Portal(380,40,"p9"));
		portals.add(new Portal(170,50,"p10"));
		portals.add(new Portal(275,300,"p11"));
		portals.add(new Portal(290,350,"p12"));
		portals.add(new Portal(350,450,"p13"));
		portals.add(new Portal(600,500,"p14"));*/
		File[] ports = new File("portals").listFiles();
		List<String> lines = new ArrayList<String>();
		for(File f:ports){
			try{
				lines = Files.readAllLines(Paths.get(f.getCanonicalPath()), Charset.defaultCharset());
			} catch(Exception e){
				System.out.println("Could not read config file");
				e.printStackTrace();
				System.exit(0);
			}
			portals.add(new Portal(Integer.parseInt(lines.get(0)),Integer.parseInt(lines.get(1)),f.getName().substring(0,f.getName().length()-4)));
		}
		
			for(Portal initPortal:portals){
				try {
					BufferedReader pr = new BufferedReader(new FileReader("portals\\" + initPortal.namee()+".txt"));
					//initPortal.setRes(Integer.parseInt(pr.readLine()));
					pr.readLine();
					pr.readLine();
					initPortal.setAlign(pr.readLine());
					//System.out.println(initPortal.team());
					String tryy = pr.readLine();
					if(tryy==null||tryy.equals("0")){}else{
						//System.out.println("numreses on " + initPortal.namee() + " = " + tryy);
						String s = pr.readLine();
						
					for(int i=0;i<Integer.parseInt(tryy);i++){						
						if(s==null||s.equals("0")){
							System.out.println("break");
							break;
						}
						//remSystem.out.println("adding lv " + s + "to " + initPortal.team());
						initPortal.addReso(Integer.parseInt(s));
						circ.add(new Ellipse2D.Float(Integer.parseInt(pr.readLine()),Integer.parseInt(pr.readLine()),5,5));
						s = pr.readLine();
					}
					//String ah = pr.readLine();
					String agg = pr.readLine();
					for(int i=0;i<Integer.parseInt(s);i++){
						initPortal.addLink(agg);
						agg = pr.readLine();
					}					
					}				
					//initPortal.addLink(pr.readLine());
					//initPortal.name = pr.readLine();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (java.lang.NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		try {
			read = new BufferedReader(new FileReader("inventory\\keys.txt"));		
			String tt = read.readLine();
		while(tt!=null){
			k.add(tt + "");	
			tt = read.readLine();
		}
		read.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			BufferedReader apread = new BufferedReader(new FileReader("inventory\\ap.txt"));
			int resr = Integer.parseInt(apread.readLine());
			p.addAp(resr);
			apread.close();
		}catch(FileNotFoundException e8){
			e8.printStackTrace();
		}catch(IOException e0){
			e0.printStackTrace();
		}
		
		try{
			BufferedReader resread = new BufferedReader(new FileReader("inventory\\resos.txt"));
			String resr = resread.readLine();
			while(resr!=null){
				b.add(Integer.parseInt(resr));
				resr = resread.readLine();
			}
			
		}catch(FileNotFoundException e8){
			e8.printStackTrace();
		}catch(IOException e0){
			e0.printStackTrace();
		}
		//
		try{
			BufferedReader linkRead = new BufferedReader(new FileReader("MU\\links.txt"));
			String linkk = linkRead.readLine();
			for(int i=0;i<Integer.parseInt(linkk);i++){
				int link1 = Integer.parseInt(linkRead.readLine());
				int link2 = Integer.parseInt(linkRead.readLine());
				int link3 = Integer.parseInt(linkRead.readLine());
				int link4 = Integer.parseInt(linkRead.readLine());
				String team1 = linkRead.readLine();
				links.add(new Link(link1,link2,link3,link4,team1));
				intr.add(new Line2D.Float(link1,link3,link2,link4));
				
			}
		}catch(FileNotFoundException e8){
			e8.printStackTrace();
		}catch(IOException e0){
			e0.printStackTrace();
		}catch(NumberFormatException e9){}
		//
		//
		try{
			BufferedReader fieldRead = new BufferedReader(new FileReader("MU\\fields.txt"));
			String linkk = fieldRead.readLine();
			if(Integer.parseInt(linkk)==0){}else{
			for(int i=0;i<Integer.parseInt(linkk);i++){
				int x1 = Integer.parseInt(fieldRead.readLine());
				int y1 = Integer.parseInt(fieldRead.readLine());
				int x2 = Integer.parseInt(fieldRead.readLine());
				int y2 = Integer.parseInt(fieldRead.readLine());
				int x3 = Integer.parseInt(fieldRead.readLine());
				int y3 = Integer.parseInt(fieldRead.readLine());
				String teem = fieldRead.readLine();
				fields.add(new Field(x1,y1,x2,y2,x3,y3,teem));
				int[] xpoints = {x1,x2,x3};
				int[] ypoints = {y1,y2,y3};
				poly.add(new Polygon(xpoints,ypoints,3));
			}
			}
		}catch(FileNotFoundException e8){
			e8.printStackTrace();
		}catch(IOException e0){
			e0.printStackTrace();
		}catch(NumberFormatException e9){}
		//
		getInputMap().put(KeyStroke.getKeyStroke("A"),"left");
		getActionMap().put("left", left);
		
		getInputMap().put(KeyStroke.getKeyStroke("D"),"right");
		getActionMap().put("right", right);
		
		getInputMap().put(KeyStroke.getKeyStroke("W"),"up");
		getActionMap().put("up", up);
		
		getInputMap().put(KeyStroke.getKeyStroke("S"),"down");
		getActionMap().put("down", down);
		
		 getInputMap().put(KeyStroke.getKeyStroke("released A"),"rleft");
		 getActionMap().put("rleft", rleft);
		
		 getInputMap().put(KeyStroke.getKeyStroke("released D"),"rright");
		 getActionMap().put("rright", rright);
		 
		 getInputMap().put(KeyStroke.getKeyStroke("released W"),"rup");
		 getActionMap().put("rup", rup);
		 
		 getInputMap().put(KeyStroke.getKeyStroke("released S"),"rdown");
		 getActionMap().put("rdown", rdown);
		 final JButton team = new JButton("Resistance");
		 team.addActionListener(new java.awt.event.ActionListener(){
			 public void actionPerformed(java.awt.event.ActionEvent evt){
				 if(p.getAlign().equals("enl")){
					p.setAlign("res");
					team.setText("Enlightened");					
				 }else{
					team.setText("Resistance");
					p.setAlign("enl");
				 }
				 Main.this.requestFocusInWindow();
			 }
		 });
		 this.add(team);
		 JButton ops = new JButton("Ops");
		 ops.addActionListener(new java.awt.event.ActionListener(){
			 public void actionPerformed(java.awt.event.ActionEvent evt){
				 JButton inventory = new JButton("Inventory");
				 Object[] optionss = {inventory};
					JOptionPane optionPane = new JOptionPane(optionss);
					final JFrame parent = new JFrame();					
				     parent.add(optionPane);
				     parent.setSize(200,200);
				     parent.setVisible(true);
				     parent.addComponentListener(new ComponentAdapter(){
		    			 @Override
		    			 public void componentHidden(ComponentEvent e){
		    				 	Main.this.requestFocusInWindow(true);
		    				 	System.out.println("hide");
		    				 ((JFrame)(e.getComponent())).dispose();
		    			 }
		    			 
		    		 });
				 inventory.addActionListener(new java.awt.event.ActionListener(){
			    	 @Override
			    	 public void actionPerformed(java.awt.event.ActionEvent evt){
			    		 parent.setVisible(false);
			    		 inInventory = true;
			    		 final JFrame items = new JFrame();
			    		 final JPanel scroll = new JPanel();
			    		 String a = b.uniqueSet() + "";
			    		 System.out.println(a);
			    		 for(int i=0;i<8;i++){
			    		 if(b.contains(i)){
			    			 ImageIcon icon = createImageIcon("resonator" + i + ".png","Level 1");
			    			 scroll.add(new JLabel("Level " + i +  "  x" + b.getCount(i),icon,JLabel.CENTER)); 
			    		 }
			    		 }
			    		 for(int i=0;i<8;i++){
				    		 if(bb.contains(i)){
				    			 ImageIcon icon = createImageIcon("XMP Burster" + i + ".png","Level 1");
				    			 scroll.add(new JLabel("Level " + i +  " XMP  x" + bb.getCount(i),icon,JLabel.CENTER)); 
				    		 }
				    		 }
			    		 for(Object h : k.uniqueSet()){
			    			 ImageIcon icon = createImageIcon(h + ".png","");
			    			 scroll.add(new JLabel(h + " x" + k.getCount(h),icon,JLabel.CENTER));
			    		 }
			    		 JScrollPane scrollPane = new JScrollPane(scroll);
			    		 items.add(scrollPane);
			    		 items.setSize(400,210);			    		 
			    		 items.setVisible(true);
			    		 items.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			    		 items.addComponentListener(new ComponentAdapter(){
			    			 @Override
			    			 public void componentHidden(ComponentEvent e){
			    				 	parent.setVisible(true);
			    				 	System.out.println("hide");
			    				 ((JFrame)(e.getComponent())).dispose();
			    			 }
			    			 
			    		 });
			    		 
			    	 }
			    	 });
			 }
		 });
		 this.add(ops);
		 addMouseListener(new MouseAdapter() {
		     @Override
		     public void mousePressed(MouseEvent e) {
		    	Rectangle click = new Rectangle(e.getX(),e.getY(),1,1);
		    	for(final Portal portal : portals){
		    		pp = portal;
		    		Ellipse2D.Float ell = new Ellipse2D.Float(portal.retX(),portal.retY(),20,20);
					if(hasCollision(portal.retX()+10,portal.retY()+10)&&ell.contains(e.getPoint())){
					//
					//portalStatus = new JLabel(portal.resonators.toString());
					//
					JLabel portalInfo = new JLabel();
					JLabel portalLevel = new JLabel();					
					portalInfo.setText("Level: " + portal.avgLevel());
					portalLevel.setText("	" + portal.namee());
					portalLevel.setHorizontalAlignment(SwingConstants.CENTER);
					JButton button = new JButton("Hack");
					JButton deploy = new JButton("Deploy");
					JButton xmp = new JButton("Fire XMP");
					JButton link = new JButton("Link");
					JButton mods = new JButton("Mods");
					Object[] optionss = {portalInfo,portalLevel,button,deploy,xmp,link,mods};
					JOptionPane optionPane = new JOptionPane(optionss);
					final JFrame parent = new JFrame();					
				     parent.add(optionPane);
				     parent.setSize(200,250);
				     parent.setVisible(true);
				     mods.addActionListener(new java.awt.event.ActionListener(){
				    	 @Override
				    	 public void actionPerformed(java.awt.event.ActionEvent evt){
				    		 parent.setVisible(false);
				    		 JFrame main = new JFrame();
				    		 main.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				    		 main.addComponentListener(new ComponentAdapter(){
    			    			 @Override
    			    			 public void componentHidden(ComponentEvent e){				    			    				 	
    			    				 	parent.setVisible(true);
    			    				 ((JFrame)(e.getComponent())).dispose();
    			    			 }
				    		 });
				    		 JPanel modList = new JPanel();
				    	 }
				    	 
				     });
				     xmp.addActionListener(new java.awt.event.ActionListener(){
				    	 @Override
				    	 public void actionPerformed(java.awt.event.ActionEvent evt){
				    		 parent.setVisible(false);
				    		 JFrame main = new JFrame();
				    		 main.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				    		 main.addComponentListener(new ComponentAdapter(){
    			    			 @Override
    			    			 public void componentHidden(ComponentEvent e){				    			    				 	
    			    				 	parent.setVisible(true);
    			    				 ((JFrame)(e.getComponent())).dispose();
    			    			 }
				    		 });
				    		 burst = true;
					    	 firelev = 8;
					    	 start();
					    	 stop();
				    		 
				    	 }
				    	 
				     });
				     
				     link.addActionListener(new java.awt.event.ActionListener(){
				    	 @Override
				    	 public void actionPerformed(java.awt.event.ActionEvent evt){
				    		 parent.setVisible(false);
				    		 final JFrame main = new JFrame();
				    		 main.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				    		 main.addComponentListener(new ComponentAdapter(){
    			    			 @Override
    			    			 public void componentHidden(ComponentEvent e){				    			    				 	
    			    				 	parent.setVisible(true);
    			    				 ((JFrame)(e.getComponent())).dispose();
    			    			 }
				    	 });
				    		 JPanel keyList = new JPanel();
				    		 for(final Portal y:portals){
				    			 if(y.namee().equals(portal.namee())){}else{
				    				 if(y.numReses()==8&&portal.numReses()==8&&k.contains(y.namee())&&y.team().equals(portal.team())){
				    					 JButton b = new JButton(y.namee());
				    					 ImageIcon ico = createImageIcon(y.namee() + ".png","");
				    					 b.setIcon(ico);
				    					 keyList.add(b);
				    					 b.addActionListener(new java.awt.event.ActionListener(){
				    				    	 @Override
				    				    	 public void actionPerformed(java.awt.event.ActionEvent evt){
				    				    		boolean a = true;
				    				    		for(Line2D ln:intr){
				    				    			Line2D.Float line = new Line2D.Float(portal.retX()+10,portal.retY()+10,y.retX()+10,y.retY()+10);
				    				    			if(ln.intersectsLine(line)&&(ln.ptLineDist(line.getP1())!=0&&ln.ptLineDist(line.getP2())!=0)||
				    				    					((ln.getP1().equals(line.getP1())&&ln.getP2().equals(line.getP2()))||(ln.getP2().equals(line.getP1())&&ln.getP1().equals(line.getP2())))){
				    				    				a = false;	
				    				    				System.out.println("tried: " + (portal.retX()+10) + " " + (portal.retY()+10) + " to " + (y.retX()+10) + " " + (y.retY()+10));
				    				    				System.out.println("blocked by: " + ln.getP1() + " " + ln.getP2());
				    				    				
				    				    			}
				    				    			
				    				    			for(Polygon pol : poly){
				    				    				if((pol.contains(line.getP2()))){
				    				    					if(!pol.contains(line.getP1())){
				    				    					
				    				    					}else{
				    				    					a = false;
				    				    					}
				    				    					System.out.println();
				    					 				}
				    				    			
				    				    			}
				    				    		}
				    				    		
				    				    		if(a){
				    				    			
				    				    			links.add(new Link(portal.retX()+10,y.retX()+10,portal.retY()+10,y.retY()+10,y.team())); 
					    				    		intr.add(new Line2D.Float(portal.retX()+10,portal.retY()+10,y.retX()+10,y.retY()+10));
					    				    		k.remove(y.namee(),1);
					    				    		repaint();
					    				    		y.addLink(portal.namee());
					    				    		portal.addLink(y.namee());
					    				    		if(y.links>=2&&portal.links>=2){
					    				    			for(Portal ft:portals){
					    				    				if(y.isLinkedTo(ft.namee())&&portal.isLinkedTo(ft.namee())&&!(ft.namee().equals(portal))){
					    				    					fields.add(new Field(y.retX()+10,y.retY()+10,portal.retX()+10,portal.retY()+10,ft.retX()+10,ft.retY()+10,portal.team()));
					    				    					poly.add(new Polygon(new int[]{y.retX()+10,portal.retX()+10,ft.retX()+10},new int[]{y.retY()+10,portal.retY()+10,ft.retY()+10},3));
					    				    					System.out.println("field");
					    				    					p.addAp(1563);
					    				    					//Rectangle muRect = new Polygon(new int[]{y.retX()+10,portal.retX()+10,ft.retX()+10},new int[]{y.retY()+10,portal.retY()+10,ft.retY()+10},3).getBounds();
					    				    					Polygon muPoly = new Polygon(new int[]{y.retX()+10,portal.retX()+10,ft.retX()+10},new int[]{y.retY()+10,portal.retY()+10,ft.retY()+10},3);
					    				    					int baseline = ((muPoly.xpoints[1] - muPoly.xpoints[0]) * (muPoly.xpoints[1] - muPoly.xpoints[0]) + (muPoly.ypoints[1] - muPoly.ypoints[0]) * (muPoly.ypoints[1] - muPoly.ypoints[0]));
					    				    					int hypline = ((muPoly.xpoints[2] - muPoly.xpoints[0]) * (muPoly.xpoints[2] - muPoly.xpoints[0]) + (muPoly.ypoints[2] - muPoly.ypoints[0]) * (muPoly.ypoints[2] - muPoly.ypoints[0]));
					    				    					int thirdline = ((muPoly.xpoints[2] - muPoly.xpoints[1]) * (muPoly.xpoints[2] - muPoly.xpoints[1]) + (muPoly.ypoints[2] - muPoly.ypoints[1]) * (muPoly.ypoints[2] - muPoly.ypoints[1]));
					    				    					int per = (baseline+hypline+thirdline)/2;
					    				    					int area = (int) Math.sqrt(per*(per-baseline)*(per-hypline)*(per-thirdline));
					    				    					//hypline^2 = baseline^2+thirdline^2-2*thirdline*baseline*cos(hypline)
					    				    					//c^2=a^2+b^2-2ab*cos(theta)
					    				    					/*
					    				    					p.addMu(muRect.width*muRect.height);
					    				    					System.out.println(muRect.width*muRect.height + " MU");*/
					    				    					p.addMu(area);
					    				    					System.out.println(area);
					    				    				}
					    				    			}
					    				    		}else{
					    				    			p.addAp(313);
					    				    		}
				    				    		}else{
				    				    			
				    				    		}
				    				    		
				    				    	 }	
				    					 });
				    				 }
				    			 }
				    		 }
				    		 JScrollPane sc = new JScrollPane(keyList);
				    		 main.add(sc);
				    		 main.pack();
				    		 main.setVisible(true);
				    	 }
				     });
				     
				     deploy.addActionListener(new java.awt.event.ActionListener(){
				    	 @Override
				    	 public void actionPerformed(java.awt.event.ActionEvent evt){
				    		 parent.setVisible(false);
				    		 deployInit();
				    		 status.pack();
				    			status.setVisible(true);
				    		status.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				    		status.addComponentListener(new ComponentAdapter(){
   			    			 @Override
   			    			 public void componentHidden(ComponentEvent e){				    			    				 	
   			    				 	parent.setVisible(true);
   			    				 ((JFrame)(e.getComponent())).dispose();
   			    			 }
				    	 });
				    		//items.setAlignmentX(status.LEFT_ALIGNMENT);
				    		if(dep1!=null){
				    		dep1.addActionListener(new java.awt.event.ActionListener(){
				    			 @Override
						    	 public void actionPerformed(java.awt.event.ActionEvent evt){
				    				 ressy = 1;				    			
				    			 }
				    		});
				    		}
				    		if(dep2!=null){
				    		dep2.addActionListener(new java.awt.event.ActionListener(){
				    			 @Override
						    	 public void actionPerformed(java.awt.event.ActionEvent evt){
				    				 ressy = 2;				    			
				    			 }
				    		});
				    		}
				    		if(dep3!=null){
				    		dep3.addActionListener(new java.awt.event.ActionListener(){
				    			 @Override
						    	 public void actionPerformed(java.awt.event.ActionEvent evt){
				    				 ressy = 3;				    			
				    			 }
				    		});
				    		}
				    		if(dep4!=null){
					    		dep4.addActionListener(new java.awt.event.ActionListener(){
					    			 @Override
							    	 public void actionPerformed(java.awt.event.ActionEvent evt){
					    				 ressy = 4;				    			
					    			 }
					    		});
					    		}
				    		if(dep5!=null){
					    		dep5.addActionListener(new java.awt.event.ActionListener(){
					    			 @Override
							    	 public void actionPerformed(java.awt.event.ActionEvent evt){
					    				 ressy = 5;				    			
					    			 }
					    		});
					    		}
				    		if(dep6!=null){
					    		dep6.addActionListener(new java.awt.event.ActionListener(){
					    			 @Override
							    	 public void actionPerformed(java.awt.event.ActionEvent evt){
					    				 ressy = 6;				    			
					    			 }
					    		});
					    		}
				    		if(dep7!=null){
					    		dep7.addActionListener(new java.awt.event.ActionListener(){
					    			 @Override
							    	 public void actionPerformed(java.awt.event.ActionEvent evt){
					    				 ressy = 7;				    			
					    			 }
					    		});
					    		}
				    		if(dep8!=null){
					    		dep8.addActionListener(new java.awt.event.ActionListener(){
					    			 @Override
							    	 public void actionPerformed(java.awt.event.ActionEvent evt){
					    				 ressy = 8;				    			
					    			 }
					    		});
					    		}
				    		//status.add(items);
				    		 deployy.addActionListener(new java.awt.event.ActionListener(){
				    			 @Override
						    	 public void actionPerformed(java.awt.event.ActionEvent evt){		
				    				// itemss.setModel(b);
				    				 if(b.contains(ressy)&&portal.numReses()<8&&p.pLevel()>=ressy&&(portal.team().equals(p.getAlign())||portal.team().equals("ntrl"))){	
				    					 if(portal.numReses()==0){
				    						 p.addAp(500);
				    						 portal.capture(p.getAlign());
				    						 repaint();
				    					 }else if(portal.numReses()==7){
				    						 p.addAp(250);
				    					 }else{
				    						 p.addAp(125);
				    					 }
				    				 portal.addReso(ressy);				    				 
				    				 b.remove(ressy, 1);
				    				 switch(portal.numReses()){
				    					case 1:	
				    						circ.add(new Ellipse2D.Float(portal.retX()+8,portal.retY()-15+8,5,5));
				    						break;
				    					case 2:
				    						circ.add(new Ellipse2D.Float(portal.retX()+15+8,portal.retY()-15+8,5,5));
				    						break;
				    					case 3:
				    						circ.add(new Ellipse2D.Float(portal.retX()+15+8,portal.retY()+8,5,5));
				    						break;
				    					case 4:
				    						circ.add(new Ellipse2D.Float(portal.retX()+15+8,portal.retY()+15+8,5,5));
				    						break;
				    					case 5:
				    						circ.add(new Ellipse2D.Float(portal.retX()+8,portal.retY()+15+8,5,5));
				    						break;
				    					case 6:
				    						circ.add(new Ellipse2D.Float(portal.retX()-15+8,portal.retY()+15+8,5,5));
				    						break;
				    					case 7:
				    						circ.add(new Ellipse2D.Float(portal.retX()-15+8,portal.retY()+8,5,5));
				    						break;
				    					case 8:
				    						circ.add(new Ellipse2D.Float(portal.retX()-15+8,portal.retY()-15+8,5,5));
				    						break;				
				    					}
				    				 repaint();
				    				 if(ressy == 1){
				    					 dep1.setText("Level 1 x" + b.getCount(1));
				    				 }else if(ressy == 2){
				    					 dep2.setText("Level 2 x" + b.getCount(2));
				    				 }else if(ressy == 3){
				    					 dep3.setText("Level 3 x" + b.getCount(3));
				    				 }else if(ressy == 4){
				    					 dep4.setText("Level 4 x" + b.getCount(4));
				    				 }else if(ressy == 5){
				    					 dep5.setText("Level 5 x" + b.getCount(5));
				    				 }else if(ressy == 6){
				    					 dep6.setText("Level 6 x" + b.getCount(6));
				    				 }else if(ressy == 7){
				    					 dep7.setText("Level 7 x" + b.getCount(7));
				    				 }else if(ressy == 8){
				    					 dep8.setText("Level 8 x" + b.getCount(8));
				    				 }
				    				 opt.revalidate();
				    				 status.validate();
				    				 pp = portal;
				    				// portalStatus = new JLabel(portal.resonators.toString());
				    				// deployInit();
				    				 //itemss = new JComboBox(b.uniqueSet().toArray());
				    				 portalStatus.setText(portal.resonators.toString());				    				
				    				 }else{
				    					if(!p.getAlign().equals(portal.team())){
				    						System.out.println("wrong team");
				    					}
				    					if(portal.numReses()>=8){
				    						System.out.println("too many reses");
				    					}
				    					if(!b.contains(ressy)){
				    						System.out.println("no res");
				    					}
				    					if(p.pLevel()<ressy){
				    						System.out.println("low level");
				    					}
				    				 }
				    				 				    				 
				    			 }
				    		 });
				    	 }
				     });
				     button.addActionListener(new java.awt.event.ActionListener() {
					     @Override
					     public void actionPerformed(java.awt.event.ActionEvent evt) {					    	
					    	 p.addAp(100);
					    	
					    	 Random rand = new Random();
					    	 int amount = rand.nextInt(3);
					    	 int key = rand.nextInt(2);
					    	 int bur = rand.nextInt(3);
					    	 double zeroChancee = Math.random()*10;
					    	 double zeroChance = Math.random()*10;
					    	 if(amount==0&&zeroChance<=5.0){
					         
					    	 }else if(amount==0){
					    	 amount = 1;
					    	 }else{
					    	 for(int i=0;i<amount;i++){				    		 					    		 
					    		 b.add(calculateLevel(p,portal));
					    	 }
					    	 }
					    	 if(bur==0&&zeroChancee<=5.0){
						         
					    	 }else if(bur==0){
					    	 bur = 1;
					    	 }else{
					    	 for(int i=0;i<bur;i++){				    		 					    		 
					    		 bb.add(calculateLevel(p,portal));
					    	 }
					    	 }
					    	 if(key==1){
					    		 k.add(portal.namee());
					    	 }
					    	 System.out.println("ap: " + p.apLevel());
					    	 System.out.println("Level: " + p.pLevel());
					    	 System.out.println("Level 1 Resos: " + b.getCount(1));
					    	 System.out.println("Resos on Portal: " + portal.resonators.size());
					    	 
					     }
				     });
				     break;
		    	   }										
		    	}
		     }
		 });
	}
	public int calculateLevel(Player p,Portal po){
		 Random randd = new Random();		 
		 int lv = randd.nextInt(po.avgLevel()+2);
		 if(lv==0){
			 lv = po.avgLevel();
		 }
		 if(lv>8){
			 lv = 8;
		 }
		 
		 return lv;
	}
	Action left = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    	move = "left";
	    	leftTrue = true;
	    	rightTrue = false;
	    	start();
	    }
	};
	Action right = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    	move = "right";
	    	rightTrue = true;
	    	leftTrue = false;
	    	start();
	    }
	};
	Action up = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    	move = "up";
	    	upTrue = true;
	    	downTrue = false;
	    	start();
	    }
	};
	Action down = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    	move = "down";
	    	downTrue = true;
	    	upTrue = false;
	    	start();
	    }
	};
	Action rleft = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    	leftTrue = false;
	    	start();
	    }
	};
	Action rright = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    	rightTrue = false;
	    	start();
	    }
	};
	Action rup = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    	upTrue = false;
	    	start();
	    }
	};
	Action rdown = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    	downTrue = false;
	    	start();
	    }
	};
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Rectangle player = new Rectangle(x,y,10,10);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		//g2d.fill(player);	
		
		
		g.setColor(Color.BLACK);
		
		for(Field fi: fields){
			if(fi.getTeam().equals("enl")){
				
				g2d.setColor(new Color(0,255,0,50));
				//Color.getHSBColor(1.0f, 0.5f, 12.0f)
			}else if(fi.getTeam().equals("res")){
				g2d.setColor(new Color(0,255,255,50));
			}
			
			int[]xs = {fi.getP1x(),fi.getP2x(),fi.getP3x()};
			int[]ys = {fi.getP1y(),fi.getP2y(),fi.getP3y()};
			g2d.drawPolygon(xs,ys,3);
			g2d.fillPolygon(xs, ys, 3);
		}
		/*for(Line2D ln : intr){
			g.setColor(Color.GREEN);
			g2d.draw(ln);
		}*/
		for(Link l:links){
			if(l.getTeam().equals("enl")){
				g.setColor(Color.GREEN);
			}else if(l.getTeam().equals("res")){
				g.setColor(Color.BLUE);
			}
			g.drawLine(l.x1(), l.y1(), l.x2(), l.y2());
		}
		for(Portal portal : portals){
			
			for(int i=0;i<portal.numRes;i++){
				try{
				switch(i){
				case 0:
					g.setColor(resColor(portal.resonators.get(i).getLevel()));
					g.drawOval(portal.retX()+8,portal.retY()-15+8,5,5);
					break;
				case 1:
					g.setColor(resColor(portal.resonators.get(i).getLevel()));
					g.drawOval(portal.retX()+15+8,portal.retY()-15+8,5,5);
					break;
				case 2:
					g.setColor(resColor(portal.resonators.get(i).getLevel()));
					g.drawOval(portal.retX()+15+8,portal.retY()+8,5,5);
					break;
				case 3:
					g.setColor(resColor(portal.resonators.get(i).getLevel()));
					g.drawOval(portal.retX()+15+8,portal.retY()+15+8,5,5);
					break;
				case 4:
					g.setColor(resColor(portal.resonators.get(i).getLevel()));
					g.drawOval(portal.retX()+8,portal.retY()+15+8,5,5);
					break;
				case 5:
					g.setColor(resColor(portal.resonators.get(i).getLevel()));
					g.drawOval(portal.retX()-15+8,portal.retY()+15+8,5,5);
					break;
				case 6:
					g.setColor(resColor(portal.resonators.get(i).getLevel()));
					g.drawOval(portal.retX()-15+8,portal.retY()+8,5,5);
					break;
				case 7:
					g.setColor(resColor(portal.resonators.get(i).getLevel()));
					g.drawOval(portal.retX()-15+8,portal.retY()-15+8,5,5);
					break;				
				}
				}catch(IndexOutOfBoundsException e){}
				
			}
			if(portal.team().equals("ntrl")){
				g.setColor(Color.GRAY);
				}else if(portal.team().equals("res")){
				g.setColor(Color.BLUE);
				}else{
				g.setColor(Color.GREEN);				
				}
		g.drawOval(portal.retX(),portal.retY(),20,20);
		}
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 50, 50);
		g.setColor(Color.GRAY);
		if(burst){
			for(Ellipse2D e:circ){
			
				if(e.intersects(x+10-currentRadius/2,y+10-currentRadius/2,currentRadius,currentRadius)){
					//System.out.println(e.getX() + " " + e.getY());
					for(Portal por : portals){
						for(Resonator res : por.resonators){
							if(e.getX()==res.x&&e.getY()==res.y&&!p.align.equals(por.team())){								
								res.damage(2000);
								if(res.dead()){
									por.resonators.remove(res);
									por.numRes--;
									if(por.numReses()==0){
									por.setAlign("ntrl");
									}
									//por.linked.clear();
									for(Line2D ln : intr){
										if(ln.ptLineDist(por.retX()+10, por.retY()+10)==0){
											intr.remove(ln);
											System.out.println("link removed");
										}
									}
									for(Link li : links){
										Line2D.Float line = new Line2D.Float(li.x1(),li.y1(),li.x2(),li.y2());
										if(line.ptLineDist(por.retX()+10, por.retY()+10)==0){
											links.remove(li); 
											System.out.println("link dead");
										}
									}
								}
								System.out.println(res.getEnergy());
							}
						}
					}
				}
			}		
			g.drawOval(x+10-currentRadius/2,y+10-currentRadius/2,currentRadius,currentRadius);
		}
	}
	public Color resColor(int a){
		if(a==1){
			return new Color(225,225,0);
		}else if(a==2){
			return new Color(255,175,0);
		}else if(a==3){
			return new Color(255,128,0);
		}else if(a==4){
			return new Color(255,0,0);
		}else if(a==5){
			return new Color(255,50,255);
		}else if(a==6){
			return new Color(248,0,255);
		}else if(a==7){
			return new Color(228,0,255);
		}else if(a==8){
			return new Color(170,0,255);
		}
		return new Color(153,102,0);
	}
	public boolean hasCollision(int x2, int y2){
		int x1 = x + 25;
		int y1 = y + 25;
		int distance = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
		int r1 = 25;
		int r2 = 10;
		return distance <= (r1 + r2) * (r1 + r2);
	}
	public void run(){
		if(burst){
			Burster b = new Burster(firelev);
			currentRadius=1;
			for(int i=0;i<b.burstRange(firelev)*2;i++){				
				repaint();
				currentRadius++;
				try{Thread.sleep(7);}catch(InterruptedException e){}
			}
			
			burst = false;
			currentRadius = 0;
			repaint();
		}
		try {
			if(move.equals("up")){
				for(int i=0;i<100;i++){
					if(upTrue){
					y--;
					repaint();
					try{Thread.sleep(7);}catch(InterruptedException e){}
					}else break;
					}
			}else if(move.equals("down")){
				for(int i=0;i<100;i++){
					if(downTrue){
					y++;
					repaint();
					try{Thread.sleep(7);}catch(InterruptedException e){}
					}else break;
					}
			}
			if(move.equals("left")){
				for(int i=0;i<100;i++){
					if(leftTrue){
					x--;
					repaint();
					try{Thread.sleep(7);}catch(InterruptedException e){}
					}else break;
					}
			}else if(move.equals("right")){
				for(int i=0;i<100;i++){
				if(rightTrue){
				x++;
				repaint();
				try{Thread.sleep(7);}catch(InterruptedException e){}
				}else break;
				}
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			
		}
		catch(ConcurrentModificationException z){
			
		}
		stop();
	}
}
