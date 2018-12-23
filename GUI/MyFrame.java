
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
import GIS.Map;
import GIS.Packmen;
import GIS.Path;
import Geom.Point3D;

import Algoritmim.*;

public class MyFrame extends JFrame implements MouseListener {
	
	// private variables
	private Container window;
	private JPanel _panel;
	private Graphics _paper;
	private int x, y;
	private int isGamer;
	private Game game= new Game();
	//private Map map = new Map();
	private ImageIcon imgBck;
	private int counter = -1;
	private int counter2 = -1;

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
		Dimension d= new Dimension(1433,642);
		_panel.setPreferredSize(d);		               
		window.add(_panel);

		// A menu-bar contains menus. A menu contains menu-items (or sub-Menu)
		JMenuBar menuBar;   // the menu-bar
		JMenu menu, menu2;         // each menu in the menu-bar
		JMenuItem menuItem1, menuItem2, menuItem3,menuItem4,menuItem5,menuItem6; // an item in a menu

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

	public void PaintAgain() {
	isGamer = 3;
	paintElement();
}
	
	protected void paintElement() {
		//	The method getGraphics is called to obtain a Graphics object
		_paper = _panel.getGraphics();

		if(isGamer==1){
			Packmen p = new Packmen(x,y);
			p.setId(counter);
			System.out.println(p.getId());
			game.packmen_list.add(p);

			Iterator<Packmen> packmen = game.packmen_list.iterator();
			while(packmen.hasNext()) {

				p = packmen.next();
				_paper.setColor(Color.YELLOW);
				_paper.fillOval(x,y,10,10);
				_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
				_paper.drawString("("+Integer.toString((int)p.getPoint3D().x())+", "+Integer.toString((int)p.getPoint3D().y())+")",(int)p.getPoint3D().x(),(int)p.getPoint3D().y()-10);

			} }
		
		if(isGamer==2){ 
			Fruit f = new Fruit(x,y);
			f.setId(counter2);
			game.fruit_list.add(f);

			Iterator<Fruit> fruit = game.fruit_list.iterator();
			while(fruit.hasNext()) {
				f = fruit.next();
				_paper.setColor(Color.RED);
				_paper.fillOval(x,y,10,10);
				_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
				_paper.drawString("("+Integer.toString((int)f.getPoint3D().x())+", "+Integer.toString((int)f.getPoint3D().y())+")",(int)f.getPoint3D().x(),(int)f.getPoint3D().y()-10);
			}}
		
		if(isGamer==3){ 
			
			ShortestPathAlgo sp2 =new ShortestPathAlgo(this.game);
			Iterator<Path> it_path = sp2.AllPath.iterator();	
			while(it_path.hasNext()) {
				
				Path p = it_path.next();
				 myThread mt = new myThread(p.fruits);
					for(int j =0;j<p.fruits.size();j++) {
						if(!p.fruits.isEmpty()) {
							p.packmen.setPac_place(p.fruits.get(j).getPoint3D());
							//_paper.setColor(Color.yellow);
							
							_paper.fillOval((int)p.packmen.getPoint3D().x(),(int)p.packmen.getPoint3D().y(),10,10);
							_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
							_paper.drawString("("+Integer.toString((int)p.packmen.getPoint3D().x())+", "+Integer.toString((int)p.packmen.getPoint3D().y())+")",
									(int)p.packmen.getPoint3D().x(),(int)p.packmen.getPoint3D().y()-10);
							
						}
					}
					try {
						mt.run();
						mt.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						}
			System.out.println("bye");
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
						}
					}}
		}
		
		if(isGamer==5) {

			JButton open = new JButton();
			JFileChooser chooser = new JFileChooser();
		    chooser.setApproveButtonText("CSVfile");
		    int result = chooser.showOpenDialog(null);
		    if(result == JFileChooser.APPROVE_OPTION){
		        File selection = chooser.getSelectedFile();
		        Game game1 = new Game(selection.getAbsolutePath());
		        game.setFruit_list(game1.fruit_list);
		        game.setPackmen_list(game1.packmen_list);
		        
		        ArrayList<Fruit> ff = game.fruit_list;

		        Iterator<Fruit> fruit = ff.iterator();
				while(fruit.hasNext()) {
					Fruit f = fruit.next();
					_paper.setColor(Color.RED);
					_paper.fillOval((int)f.getPoint3D().x(),(int)f.getPoint3D().y(),10,10);
					_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
					_paper.drawString("("+Integer.toString((int)f.getPoint3D().x())+", "+Integer.toString((int)f.getPoint3D().y())+")",(int)f.getPoint3D().x(),(int)f.getPoint3D().y()-10);

				}
					Iterator<Packmen>it2 = game.packmen_list.iterator();
					System.out.println(game.packmen_list.size());
					while (it2.hasNext() ) {
						Packmen p = it2.next();
						_paper.setColor(Color.YELLOW);
						_paper.fillOval((int)p.getPoint3D().x(),(int)p.getPoint3D().y(),10,10);
						_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
						_paper.drawString("("+Integer.toString((int)p.getPoint3D().x())+", "+Integer.toString((int)p.getPoint3D().y())+")",(int)p.getPoint3D().x(),(int)p.getPoint3D().y()-10);

					}}
			
		    }
		
					
					
		if(isGamer==6) {
			 FileDialog fd = new FileDialog(this, "Save the text file", FileDialog.SAVE);
		        fd.setFile("*.csv");
		        fd.setFilenameFilter(new FilenameFilter() {
		            @Override
		            public boolean accept(File dir, String name) {
		                return name.endsWith(".csv");
		            }
		        });
		        fd.setVisible(true);
		        String folder = fd.getDirectory();
		      //  String fileName = fd.getFile();
		        try {
		            FileWriter fw = new FileWriter(folder);
		            PrintWriter outs = new PrintWriter(fw);
                    Game game2 = new Game();
                    game2.write2csv(game2);
		            outs.close();
		            fw.close();
		        } catch (IOException ex) {
		            System.out.print("Error writing file  " + ex);
		        }

		}
	}
	//	public void mouseClicked(MouseEvent event){
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

	}}
	
	// Not Used, but need to provide an empty body for compilation
	public void mouseReleased(MouseEvent event){}
	public void mouseClicked(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}

	public class myThread extends Thread{

		MyFrame mf = new MyFrame();
		ArrayList<Fruit> p = new ArrayList() ;

		public myThread(ArrayList path_packmen) {
			this.p = path_packmen;
		}
		
		@Override
		public void run() {
			for(int i = 0; i<p.size(); i++) {
				p.get(i).getPoint3D();
				//mf.PaintAgain();
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setBounds(0, 0, 1433, 642);
		frame.createGui();
		frame.setVisible(true);
	}

}
