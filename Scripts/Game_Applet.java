package Scripts;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Game_Applet extends Applet implements Runnable, KeyListener,
		MouseListener {
	int windowsizex = 700; // window sizes
	public static int windowsizey = 600;

	public static void deletePrefab(ArrayList list, int unitname) {
		int size = list.size();
		for (int a = 0; a < size; a++) {
			cosmeticSprite spri = (cosmeticSprite) list.get(a);
			if (spri.targetname == unitname) {
				System.out.println("Unit Deleted");
				list.remove(a);
				break;
			}
		}
	}

	public static void Instantiate(ArrayList<cosmeticSprite> List,
			cosmeticSprite obj) {
		// USE THIS TO CREATE OBJECTS
		cosmeticSprite spri = obj;

		spri.targetname = gameController.getNewName();
		List.add(spri);
	}

	public static void main(String[] args) {
		Game_Applet app = new Game_Applet();
		app.setVisible(true);
	}

	int playerlives = 10;
	gameController controller = new gameController();
	Font font1 = new Font("consolas", Font.PLAIN, 24); // font
	Image offscreen; // offscreen is for buffering stuff

	public Game_Applet() {

	}

	/*
	 * public void update(){ //something to do with buffering }
	 */
	public void drawList(Graphics2D off, ArrayList<cosmeticSprite> list) {
		int tempSize = list.size(); // we're doing forloops with temp variables
									// for preformance
		for (int a = 0; a < tempSize; a++) {
			cosmeticSprite cos = list.get(a);// getting the transform and sprite
			if (cos.invisible_to_player == false && cos.isDead == false) {// some
																			// enemies
																			// may
																			// be
																			// invis
																			// to
																			// player
				int x1 = cos.x; // temp variables
				int y1 = cos.y;
				int height = cos.height;
				int width = cos.width;

				Image img = cos.defaultImage; // getting image

				System.out.println();

				int imgh = img.getHeight(this);
				int imgw = img.getWidth(this); // getting the size of image
												// itself to be able to resize

				float scalex = (float) ((width + .0) / (imgw + .0)); // scaling
																		// stuff
				float scaley = (float) ((height + .0) / (imgh + .0));

				AffineTransform newform = new AffineTransform();
				// use this for rotating, scaling, transforming ect

				float degreesToRadians = (float) (Math.PI / 180);
				float degreemeasure = cos.rotation;
				degreemeasure = degreemeasure + degreesToRadians
						* cos.naturalRotation;
				newform.setToRotation(degreemeasure, cos.x + .5 * cos.width,
						cos.y + .5 * cos.height);
				cos.rotation = 0;

				newform.translate(x1, y1); // we should change this so that the
											// affine transforms are held by the
											// cosmetic sprite object, do it
											// later when we finish everything
											// else
				newform.scale(scalex, scaley); // size rescaling
				off.drawImage(img, newform, this);
				// off.drawImage(img,x1, y1, this);
			}
		}
	}

	public void excecuteListAI(ArrayList<cosmeticSprite> list)
			throws IOException {
		// excecutes the AI state machines of all units in the given arraylist

		int size = list.size();
		for (int a = 0; a < size; a++) {
			// try{
			size = list.size();
			Unit sol;
			if (a >= size) {
				break;
			}
			sol = (Unit) list.get(a);
			System.out.println(a + " list index, unit name is "
					+ sol.targetname);
			System.out.println(sol.y + " " + sol.x + " soldier x and y");
			sol.checkIfAlive();
			if (sol.isDead == false) {
			}
		}
	}

	public float findDistance(ArrayList list, ArrayList targetList,
			int shooterName, int targetName) {
		Unit shooter = (Unit) list.get(shooterName);
		Unit targetSol = (Unit) list.get(targetName);
		float distance = (float) (Math.sqrt(Math
				.pow(shooter.x - targetSol.x, 2)
				+ Math.pow(shooter.y - targetSol.y, 2)));

		return distance;
	}

	public int findname(ArrayList list, String findString) {
		// scans through the list for something named the string and returns the
		// address
		// -1 for nothing
		int returnAddress = -1;

		int tempSize = list.size();
		for (int a = 0; a < tempSize; a++) {
			Unit tempSol = (Unit) list.get(a);
			String tempname = tempSol.displayName;
			if (tempname.equals(findString)) {
				returnAddress = a;
			}
		}
		return returnAddress;
	}

	@Override
	public void init() {
		setSize(windowsizex, windowsizey);
		setBackground(Color.WHITE);

		addKeyListener(this); // add listeners here
		addMouseListener(this);
	}

	@Override
	public void keyPressed(KeyEvent key) {
	}

	@Override
	public void keyReleased(KeyEvent key) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent mouse) {
		// clicked and released
		int buttonPressed = mouse.getButton();
		int x = mouse.getX();
		int y = mouse.getY();

		Collisions col = new Collisions();
		// collisions object, checks positions

		// in collisions create another method that returns all
		// things at the position instead of just the first one
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
		// when the mouse enters the applet window
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// mouse held
		// implement click and drag selection
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// mouse released
	}

	@Override
	public void paint(Graphics paint) {
		// when painting paint background objects first
		Graphics2D g = (Graphics2D) paint;
		int tempSize = gameController.structureList.size(); // temp variable for
															// preformance
															// reasons

		offscreen = createImage(windowsizex, windowsizey);
		Graphics2D off = (Graphics2D) offscreen.getGraphics();

		drawList(off, gameController.cosmeticList);
		drawList(off, gameController.structureList);
		drawList(off, gameController.Unitlist);
		// we're going to use graphics 2d to do all our painting instead of just
		// graphics
		/*
		 * AffineTransform for rotation;
		 * 
		 * g.drawImage(img, xform, this);
		 */

		paint.drawImage(offscreen, 0, 0, this);
	}

	public int RandomNumber(int range, int min) { // returns a random integer
		int returnnum = (int) (Math.random() * range + min);
		return returnnum;
	}

	public void rotatePrefab(float degrees) {
		//
	}

	@Override
	public void run() {
		try {
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY); // threading
																		// stuff

			while (true) {
				// update loop
				excecuteListAI(gameController.Unitlist); // excecute actions for
															// unit AIs, make
															// another one for
															// structure effects

				// run gameController here
				controller.excecuteController();

				repaint(); // end of run method
				Thread.sleep(65);
				Thread.yield();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {

		Thread th = new Thread(this);
		th.start();
	}

	@Override
	public void stop() {
	}
}
