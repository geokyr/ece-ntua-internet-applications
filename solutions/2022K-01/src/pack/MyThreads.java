package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;


/**The program demonstrates how several threads can run in parallel.
 * Upon running the program, circles appear and move on a canvas.
 * The 1st thread just prints "Hello" to the console, the 2nd one creates new circle objects,
 * adds them to an array list, and paints them, a 3rd one (re)paints the circles to the canvas based on
 * the said list and animates their motion, and the 4th one prints the saved state of the canvas (see "SAVE" button).
 * Also, a timer (a 5th thread) is used for changing the "CLEAR" button color.
 * 
 * @author O.Voutyras
 * 
 */


public class MyThreads extends JFrame implements ActionListener {
	
	// Attributes of moving object
	private int rad = 10;
	
	private static boolean isColor=true;  // button
	
	//Object types
	Canvas canvas;
	JButton radius;
	JButton save;
	JButton clear;
	JTextField field;
	
	Thread thread3;
	
	ArrayList<myCircle> myCircles;
	Timer timer;
	Graphics2D g2;
	
	private static final long serialVersionUID = -1989891553971170424L;
	
	// Constructor to setup the GUI components, event handlers, and threads
		
	MyThreads() {
		// GUI components and event handlers
		
		canvas = new Canvas();
		addCircle();
		
		radius = new JButton("Set Radius");
		radius.addActionListener(this);
		
		field = new JTextField("10");
		field.setFont(new Font("Times New Roman", Font.ITALIC, 19));
		
		clear = new JButton("Clear Canvas");
		clear.addActionListener(this);
		
		save = new JButton("Save Canvas");
		save.addActionListener(this);
		
		JPanel pnl = new JPanel();
		pnl.add(radius);
		pnl.add(field);
		
		this.getContentPane().add(pnl, BorderLayout.NORTH);
		
		JPanel pnl2 = new JPanel();
		pnl2.add(save);
		pnl2.add(clear);
		
		this.getContentPane().add(pnl2, BorderLayout.SOUTH);
		this.getContentPane().add(canvas);
		
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Growing Disks");
		this.setVisible(true);
		this.setResizable(true);
		
		// Threads
		
		Runnable hello = new DisplayMessage("Hello");
		Thread thread1 = new Thread(hello);
		thread1.setName("hello");
		thread1.setDaemon(true);
		thread1.setPriority(Thread.MIN_PRIORITY);
		System.out.println("Starting " + thread1.getName() +" thread...");
		
		thread1.start();
		
				
		Runnable newcircle = new NewCircle("NewCircle");
		Thread thread2= new Thread(newcircle);
		thread2.setName("newcircle");
		System.out.println("Starting newcircle thread...");
		
		thread2.start();
		
		
		System.out.println("Starting thread3...");
		Thread thread3 = new Thread(canvas);
		thread3.start();
		
		
		Runnable information = new Information("INFORMATION");
		Thread thread4 = new Thread(information);
		thread4.setPriority(Thread.MIN_PRIORITY);
		thread4.setName("information");
		System.out.println("Starting information thread...");
		thread4.start();
		
		
		// wait for threads to end
	    /*    try
	        {
	            thread1.join();
	            thread2.join();
	            thread3.join();
	            thread4.join();
	        }
	        catch(Exception e)
	        {
	            System.out.println("Interrupted");
	        }*/
	}
	
	
	// For thread 3 (paint circles motion)
	@SuppressWarnings("serial")
	// Define inner class Canvas, which is a JPanel used for custom drawing
	class Canvas extends JPanel implements Runnable {
		
		public Canvas() {
			myCircles = new ArrayList<>();
		}
		
		// Draw a circle
		
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D) g;
	   	        
	        for (myCircle circle: myCircles) {
	            int x = circle.getX();
	            int y = circle.getY();
	       
	           Ellipse2D ellipse = new Ellipse2D.Double(x-rad, y-rad, 2*rad, 2*rad);
		       g2.fill(ellipse); 
	        }
	    }
	
	    
		@Override
	    public void run() {
			
			while(true) {
				
				repaint();								// Refresh the JFrame, callback paintComponent()
				
				try {
					Thread.sleep(100); 					//Time for next repaint
				}
				catch (InterruptedException e) {
					System.out.println("interrupted");
				}
			
			}   
		}
	
	}
	
	
	// Collision with borders check for moving objects and direction update
	
	
	public void addCircle() {
    
	    int x = (int )(Math.random() * 560);
	    int y = (int )(Math.random() * 360);
	    myCircles.add(new myCircle(x, y, rad));
	}


	public void removeCircles() {
		myCircles.clear();
	}
		
		
	class myCircle implements Serializable {

		private static final long serialVersionUID = 1L;
		
		int x;
	    int y;
	    int rad1;

	    public myCircle(int x, int y, int rad) {
	        this.x = (int )(Math.random() * 560);
	        this.y = (int )(Math.random() * 360);					// Similarly on the y axis
	        this.rad1=rad;
	    }

	    public int getX() {
	        return x;
	    }

	    public int getY() {
	        return y;
	    }
	    
	    
	    // "Formatter"
	    @Override
	    public String toString() {
	        return "myCircle{" +
	                "X='" + x + '\'' +
	                ",Y='" + y + '\'' +
	                ", RAD='" + rad1 +'\''+
	                "}\n";
	    }
	}
	
	
	// handler ActionListener
	@Override
	
	public void actionPerformed(ActionEvent ev) {
		
		if (ev.getSource() == clear) {
			
			if(!isColor) {
				System.out.println("Clear Canvas!");
				removeCircles();
				
				rad=10;
				addCircle();
				repaint();
			}
			else {
				System.out.println("Warning: Clear Button is red!");
			}
		}
		
		else if (ev.getSource() == radius) {
			System.out.println("Radius Update: Successfully Updated Radius");
			rad = Integer.parseInt(field.getText());
		}
		
		else {
			System.out.println("Save Canvas!");
			saveList();
		}
		
     }
	 
	 
	// The entry main method
	public static void main(String args[]) {
		new MyThreads();
	}
	
	 
	// For thread 1 ("hello")
	class DisplayMessage implements Runnable {
		 
		 private String message;

		 public DisplayMessage(String message) {
		    this.message = message;
		 }

		 public void run() {
		
		    	try {
					Thread.sleep(5000);
				}
		    	catch (InterruptedException e) {
		    		// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       System.out.println(message);
		 }
	 }
	 
	 
	 //For thread 4 (print saved list)
	 class Information implements Runnable {
		 
		 private String message;
		 public Information(String message) {
		    this.message = message;
		 }

		 public void run() {
							     
		    while(true) {
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		    	System.out.println(message);
		       
	 	        readList();
	    
		    }
		 }
	
	 }
	 
	 
	 // For thread 2 (paint new circles)
	 class NewCircle implements Runnable {
			 
		private String message;
		
		public NewCircle(String message) {
			this.message = message;
		}
		
		public void run() {
			
			while(true) {
				
				try {
					Thread.sleep(2000);
				}
				
				catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				
				System.out.println(message);
				      
			    rad = rad + 2;
				addCircle();
				canvas.repaint();
				
//			    System.out.println ("myCircles:  " + myCircles.size());
//				System.out.println("\nUsing Iterator");
//				
//				Iterator<MyThreads.myCircle> itr = myCircles.iterator();
//				while(itr.hasNext()) {
//			    	  MyThreads.myCircle obj = itr.next();
//			          System.out.println(obj);
//			    }
			
			}
		
		}
	
	 }
	 
	 
	 // For thread 5 (timer and change button color)
	 {
		 Runnable updateButton = new Runnable() {
			 
			 public void run() {
				 
				 if (isColor) {
					 
					 clear.setForeground(Color.BLACK);	//JBUTTON change color
					 clear.setBackground(Color.GREEN);
					 isColor=false;
				}
				 
				 else {
					 clear.setForeground(Color.BLACK);	//JBUTTON change color
		  			 clear.setBackground(Color.RED);
		  			 isColor=true;
		  	 	}
			}
		 };
		 
		 //Use of timer
		 Timer timer = new Timer();
		 
		 timer.schedule(new TimerTask() {
			 
			 @Override
			 public void run() {
				 
				 System.out.println("The timer is working");
				 
				 try {
					 SwingUtilities.invokeAndWait(updateButton);
				}
				 
				 catch (InvocationTargetException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				}
				 
				 catch (InterruptedException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				}
			}
		},1000, 5000);	//wait 1000 ms before doing the action and do it every 4000ms (4seconds)
	
	 }
	 
	 
	 private void saveList() {
		 // TODO Auto-generated method stub
		 try {
			 FileOutputStream writeData = new FileOutputStream("diskdata.ser");
			 ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
			 
			 writeStream.writeObject(myCircles);
			 writeStream.flush();
			 writeStream.close();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	 private void readList() {
		 
		 try{
			 FileInputStream readData = new FileInputStream("diskdata.ser");
			 ObjectInputStream readStream = new ObjectInputStream(readData);
			 
			 @SuppressWarnings("unchecked")
			 ArrayList<myCircle> myCircles2 = (ArrayList<myCircle>) readStream.readObject();
			 
			 readStream.close();
			 
			 System.out.println(myCircles2.toString());
		}
		
		catch (IOException | ClassNotFoundException e) {
		            e.printStackTrace();
		}
		 
	}
		
}