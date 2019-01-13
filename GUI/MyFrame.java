
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.color.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;

import GIS.Fruit;
import GIS.Game;
import GIS.Ghost;
import GIS.GisElement;
import GIS.Map;
import GIS.Packmen;
import GIS.Path;
import GIS.Player;
import Geom.Point3D;
import Robot.Play;
import graph.Graph;
import graph.Node;
import Algoritmim.*;
import Coords.MyCoords;
import java.awt.Color;

public class MyFrame extends JFrame implements MouseListener {

	// private variables
	private Container window;
	private JPanel _panel;
	private Graphics _paper;
	private int x, y;
	private int isGamer = 0;
	private Game game= new Game();
	private ImageIcon imgBck;
	private int counter = -1;
	private int counter2 = -1;
	private Player player;
	private static Play play;
	private ArrayList<Fruit> fruits=new ArrayList();
	private double angle = 0;
	

	public MyFrame(){
		super("Map Demo"); //setTitle("Map Counter");  // "super" Frame sets its title
		//	Moves and resizes this component. 
		//	The new location of the top-left corner is  specified by x and y, 
		//	and the new size is specified by width and height
		//	setBounds(x,y,width,height)
		this.setBounds(0,0,1433,700); //setSize(1433,700);        // "super" Frame sets its initial window size
		//      Exit the program when the close-window button clicked
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

	}
	public void createGui(){              				
		//	A Container is a component  that can contain other GUI components
		window = this.getContentPane(); 
		window.setLayout(new FlowLayout());

		//	Add "panel" to be used for drawing            
		_panel = new JPanel();
		Dimension d= new Dimension(1433,700);
		_panel.setPreferredSize(d);		               
		window.add(_panel);

		// A menu-bar contains menus. A menu contains menu-items (or sub-Menu)
		JMenuBar menuBar;   // the menu-bar
		JMenu menu, menu2;         // each menu in the menu-bar
		JMenuItem menuItem1, menuItem2, menuItem3,menuItem4,menuItem5,menuItem6,menuItem7,menuItem8,menuItem9; // an item in a menu

		menuBar = new JMenuBar();

		// First Menu
		menu = new JMenu("Add");
		menu.setMnemonic(KeyEvent.VK_A);  // alt short-cut key
		menuBar.add(menu);  // the menu-bar adds this menu

		menuItem1 = new JMenuItem("Packmen", KeyEvent.VK_F);
		menu.add(menuItem1); // the menu adds this item
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 1;
			}
		});
		menuItem2 = new JMenuItem("Fruit", KeyEvent.VK_S);
		menu.add(menuItem2); // the menu adds this item
		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 2;
			}
		}); 

		menuItem7 = new JMenuItem("Player", KeyEvent.VK_S);
		menu.add(menuItem7); // the menu adds this item
		menuItem7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 7;

			}
		});  

		////////***********
		menu2 = new JMenu("Game");
		menu2.setMnemonic(KeyEvent.VK_A);  // alt short-cut key
		menuBar.add(menu2);  // the menu-bar adds this menu

		menuItem3 = new JMenuItem("Play", KeyEvent.VK_F);
		menu2.add(menuItem3); // the menu adds this item
		menuItem3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 3;
				paintElement();
			}});

		menuItem4 = new JMenuItem("Paint path", KeyEvent.VK_S);
		menu2.add(menuItem4); // the menu adds this item
		menuItem4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				isGamer = 4;
				paintElement();
			}});

		menuItem5 = new JMenuItem("CSV file", KeyEvent.VK_F);
		menu2.add(menuItem5); // the menu adds this item
		menuItem5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 5;
				paintElement();
			}});

		menuItem6 = new JMenuItem("Save", KeyEvent.VK_F);
		menu2.add(menuItem6); // the menu adds this item
		menuItem6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 6;
				paintElement();
			}});
		menuItem8 = new JMenuItem("start game", KeyEvent.VK_F);
		menu2.add(menuItem8); // the menu adds this item
		menuItem8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 8;

			}});
		menuItem9 = new JMenuItem("Automatically games", KeyEvent.VK_F);
		menu2.add(menuItem9); // the menu adds this item
		menuItem9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 9;

			}});


		setJMenuBar(menuBar);  // "this" JFrame sets its menu-bar

		//		// Prepare an ImageIcon
		String imgMapFilename = "Ariel1.png";    
		imgBck = new ImageIcon(getClass().getResource(imgMapFilename));
		JLabel labelMap = new JLabel();
		labelMap.setIcon(imgBck);
		_panel.add(labelMap);

		// panel (source) fires the MouseEvent.
		//panel adds "this" object as a MouseEvent listener.
		_panel.addMouseListener(this);
	}


	protected void paintElement() {

		//	The method getGraphics is called to obtain a Graphics object
		_paper = _panel.getGraphics();

		if(isGamer==1){
			Packmen p = new Packmen(x,y);
			p.setId(counter);
			game.packmen_list.add(p);

			Iterator<Packmen> packmen = game.packmen_list.iterator();
			while(packmen.hasNext()) {

				p = packmen.next();
				_paper.setColor(Color.YELLOW);
				_paper.fillOval(x,y,20,20);
				_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               

			} }

		if(isGamer==2){ 
			Fruit f = new Fruit(x,y);
			f.setId(counter2);
			game.fruit_list.add(f);
			fruits.add(f);

			Iterator<Fruit> fruit = game.fruit_list.iterator();
			while(fruit.hasNext()) {
				f = fruit.next();
				_paper.setColor(Color.RED);
				_paper.fillOval(x,y,10,10);
				_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               

			}}

		if(isGamer==3){ 

			ArrayList<Fruit> it1 = game.fruit_list;

			Iterator<Fruit> fruit = it1.iterator();
			while(fruit.hasNext()) {
				Fruit f = fruit.next();
				_paper.setColor(Color.RED);
				_paper.fillOval((int)f.getPoint3D().x(),(int)f.getPoint3D().y(),10,10);
				_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               


			}
			Iterator<Packmen>it2 = game.packmen_list.iterator();

			while (it2.hasNext() ) {
				Packmen p = it2.next();
				_paper.setColor(Color.YELLOW);
				_paper.fillOval((int)p.getPoint3D().x(),(int)p.getPoint3D().y(),20,20);
				_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               


			}
			ShortestPathAlgo sp2 =new ShortestPathAlgo(this.game);
			Iterator<Path> it_path = sp2.AllPath.iterator();	
			while(it_path.hasNext()) {

				Path p = it_path.next();
				System.out.println("The packmen path is:\n " +p);
				myThread mt = new myThread(p.fruits,this,p.packmen);
				mt.start();
				for(int j =0;j<p.fruits.size();j++) {
					if(!p.fruits.isEmpty()) {
						p.packmen.setPac_place(p.fruits.get(j).getPoint3D());
					}}
			}
		}

		if(isGamer==4) {
			ShortestPathAlgo sp =new ShortestPathAlgo(this.game);
			Iterator<Path> it_path = sp.AllPath.iterator();

			while(it_path.hasNext()) {
				Path p = it_path.next();	
				for(int j =0;j<p.fruits.size()-1;j++) {
					if(!p.fruits.isEmpty()) {
						Point3D p1 = p.fruits.get(j).getPoint3D();	
						Point3D p2 = p.fruits.get(j+1).getPoint3D();
						_paper.setColor(Color.BLUE);
						_paper.drawLine((int)p1.x(),(int) p1.y(),(int) p2.x(), (int)p2.y());
					}}
			}}

		if(isGamer==5) {

			JButton open = new JButton();
			JFileChooser chooser = new JFileChooser();
			chooser.setApproveButtonText("CSVfile");
			int result = chooser.showOpenDialog(null);
			if(result == JFileChooser.APPROVE_OPTION){
				File selection = chooser.getSelectedFile();
				Game game1 = new Game(selection.getAbsolutePath());
				this.play= new Play(selection.getAbsolutePath());
				game.setFruit_list(game1.fruit_list);
				game.setPackmen_list(game1.packmen_list);
				game.setGohst_list(game1.ghost_list);
				game.setBox_list(game1.box_list);
				game.setPlayer(game1.player);
				ArrayList<Fruit> it1 = game.fruit_list;
				fruits=game1.fruit_list2;

				Iterator<Fruit> fruit = it1.iterator();
				while(fruit.hasNext()) {
					Fruit f = fruit.next();
					_paper.setColor(Color.RED);
					_paper.fillOval((int)f.getPoint3D().x(),(int)f.getPoint3D().y(),10,10);
					_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               

				}
				Iterator<Packmen>it2 = game.packmen_list.iterator();

				while (it2.hasNext() ) {
					Packmen p = it2.next();
					_paper.setColor(Color.YELLOW);
					_paper.fillOval((int)p.getPoint3D().x(),(int)p.getPoint3D().y(),20,20);
					_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               

				}

				Iterator<Ghost>it3 = game.ghost_list.iterator();
				while (it3.hasNext() ) {
					Ghost g = it3.next();
					_paper.setColor(Color.WHITE);
					_paper.fillOval((int)g.Ghost_location.x(),(int)g.Ghost_location.y(),30,30);
					_paper.setFont(new Font("Monospaced", Font.PLAIN, 25));               

				}

				Iterator<GIS.Box>it4 = game.box_list.iterator();
				while (it4.hasNext() ) {
					GIS.Box b = it4.next();
					double area = b.area(b.getUp(), b.getDown());
					Point3D left_up =  new Point3D (b.down.x(), b.up.y());
					double width = Math.abs(b.up.x() - left_up.x());
					double height = Math.abs(left_up.y() - b.down.y());

					_paper.setColor(Color.black);
					_paper.fillRect((int)left_up.x(), (int)left_up.y(), (int)width, (int)height);
					_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));
				}

			}

		}

		if(isGamer==6) {

			game.write2csv(game);
		}

		if(isGamer==7) {

			_paper.setColor(Color.PINK);
			_paper.fillOval(x,y,40,40);
			_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));  
			Point3D point_pixel = new Point3D(x,y);

			String imgMapFilename = "Ariel1.png";
			ImageIcon imgBck = new ImageIcon(imgMapFilename);
			Map m = new Map(imgBck.getIconWidth(),imgBck.getIconHeight());

			Point3D point_gps = m.Pixel2Gps(imgBck, point_pixel.x(), point_pixel.y());
			player.setPlayer_location(point_gps);		

		}
	}

	@Override
	public void mousePressed(MouseEvent event) {

		if(isGamer==1) {
			x = event.getX();
			y = event.getY();
			System.out.println("mouse Clicked");
			System.out.println("("+ event.getX() + "," + event.getY() +")");
			counter++;

			paintElement();
		}
		if(isGamer==2) {
			x = event.getX();
			y = event.getY();
			System.out.println("mouse Clicked");
			System.out.println("("+ event.getX() + "," + event.getY() +")");
			counter2++;

			paintElement();

		}

		if(isGamer==7) {

			x = event.getX();
			y = event.getY();
			System.out.println("mouse Clicked");
			System.out.println("("+ event.getX() + "," + event.getY() +")");

			Point3D point_pixel = new Point3D(x,y);
			String imgMapFilename = "Ariel1.png";
			ImageIcon imgBck = new ImageIcon(imgMapFilename);
			Map m = new Map(imgBck.getIconWidth(),imgBck.getIconHeight());

			Point3D point_gps = m.Pixel2Gps(imgBck, point_pixel.x(), point_pixel.y());
			this.player = new Player(point_gps);
			game.setPlayer(player);
			play.setInitLocation(player.getPlayer_location().x(), player.getPlayer_location().y());	

			paintElement();	
		}

		if(isGamer==8) {
			this.play.setIDs(205443583, 312502537);
			play.start();
			x = event.getX();
			y = event.getY();
			System.out.println("mouse Clicked");
			System.out.println("("+ event.getX() + "," + event.getY() +")");

			MyCoords mc = new MyCoords();
			Point3D point_pixel = new Point3D(x,y);
			String imgMapFilename = "Ariel1.png";
			ImageIcon imgBck = new ImageIcon(imgMapFilename);
			Map m = new Map(imgBck.getIconWidth(),imgBck.getIconHeight());

			Point3D point_gps = m.Pixel2Gps(imgBck, point_pixel.x(), point_pixel.y());

			double azimuth[]=mc.azimuth_elevation_dist(player.getPlayer_location(),point_gps);
			this.angle = azimuth[0];

			play.rotate((angle)%360);
			System.out.println(play.getStatistics());
			repaint();

			game = new Game(play.getBoard());
			for(int i = 0; i<game.fruit_list.size(); i++) {
				System.out.println(game.fruit_list.get(i));
			}
			for(int i = 0; i<game.packmen_list.size(); i++) {
				System.out.println(game.packmen_list.get(i));
			}

		}}

	//	public void startGame() {
	//
	//		isGamer = 8;
	//		this.play.setIDs(000);
	//		
	//		this.play.setInitLocation(player.getPlayer_location().x(), player.getPlayer_location().y());
	//		this.play.start();
	//
	//		Thread thread = new Thread() {
	//			public void run() {
	//				while(play.isRuning()) {
	//					game = new Game(play.getBoard());
	//					play.rotate(angle);
	//					paintElement();
	//					try {
	//						
	//						Thread.sleep(200);
	//						paintElement();
	//					}
	//					catch(Exception e) {
	//
	//					}
	//				}
	//			}
	//		};
	//		thread.start();
	//
	//	}

	@Override
	public void paint(Graphics arg0) {

		super.paint(arg0);

		Iterator<Packmen>it2 = game.packmen_list.iterator();

		while (it2.hasNext() ) {
			Packmen p = it2.next();
			_paper.setColor(Color.YELLOW);
			_paper.fillOval((int)p.getPoint3D().x(),(int)p.getPoint3D().y(),10,10);
			_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               

		}


		Iterator<Fruit>it_f = game.fruit_list.iterator();

		while(it_f.hasNext()) {
			Fruit f = it_f.next();
			_paper.setColor(Color.RED);
			_paper.fillOval((int)f.getPoint3D().x(),(int)f.getPoint3D().y(),10,10);
			_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               	
		}

		Iterator<Ghost>it_g = game.ghost_list.iterator();

		while (it_g.hasNext() ) {
			Ghost g = it_g.next();
			_paper.setColor(Color.WHITE);
			_paper.fillOval((int)g.Ghost_location.x(),(int)g.Ghost_location.y(),30,30);
			_paper.setFont(new Font("Monospaced", Font.PLAIN, 25));               

		}
		Iterator<GIS.Box>it_b = game.box_list.iterator();
		while (it_b.hasNext() ) {
			GIS.Box b = it_b.next();
			double area = b.area(b.getUp(), b.getDown());
			Point3D left_up =  new Point3D (b.down.x(), b.up.y());
			double width = Math.abs(b.up.x() - left_up.x());
			double height = Math.abs(left_up.y() - b.down.y());

			_paper.setColor(Color.black);
			_paper.fillRect((int)left_up.x(), (int)left_up.y(), (int)width, (int)height);
			_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));
		}
		
		if(isGamer == 8) {
		_paper.setColor(Color.PINK);
		_paper.fillOval((int)game.player.getPlayer_location().x(),(int)game.player.getPlayer_location().y(),40,40);
		_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));
		}
	}

	// Not Used, but need to provide an empty body for compilation
	public void mouseReleased(MouseEvent event){}
	public void mouseClicked(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}

	public class myThread extends Thread{

		MyFrame mf = new MyFrame();
		ArrayList<Fruit> p = new ArrayList() ;
		Packmen packmen;

		public myThread(ArrayList path_packmen,MyFrame mf, Packmen pack) {
			packmen = pack;
			this.p = path_packmen;
			this.mf=mf;
		}

		@Override
		public void run() {
			for(int i = 0; i<p.size(); i++) {
				packmen.setPac_place(p.get(i).getPoint3D());
				mf.repaint();
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setBounds(0, 0, 1433, 700);
		frame.createGui();
		frame.setVisible(true);
	}

}
