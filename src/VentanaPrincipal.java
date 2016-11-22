import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
=======
import java.awt.event.InputEvent;
>>>>>>> origin/master
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Control;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.Line.Info;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
<<<<<<< HEAD
import javax.swing.JComboBox;
=======
>>>>>>> origin/master
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
<<<<<<< HEAD
import javax.swing.filechooser.FileNameExtensionFilter;
=======
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


>>>>>>> origin/master

public class VentanaPrincipal {

	  static int strokeGOMA = 10;

	/**
	 * IMPORTANTE: CADA HERRAMIENTA TENDRÃ� UN CÃ“DIGO ASOCIADO
	 */
	final static int BOLIGRAFO = 0;
	final static int GOMA = 1;
<<<<<<< HEAD
	// AÃ‘ADE AQUÃ� TU HERRAMIENTA;
	// TODO: AÃ±adir la herramienta

	int herramientaActual = -1; // No hay nada por defecto.

	// La ventana principal, en este caso, guarda todos los componentes:
=======
	//AÃ‘ADE AQUÃ� TU HERRAMIENTA;
	//TODO: AÃ±adir la herramienta	
	final static int INSERTAR_IMAGEN = 2;
	
	
	
	int herramientaActual = -1; //No hay nada por defecto.	
	
	//La ventana principal, en este caso, guarda todos los componentes:
>>>>>>> origin/master
	JFrame ventana;

	// Paneles:
	JPanel panelSuperior;
	JPanel panelInferior;

	// Variables para dibujo
	JLabel lienzo;
	BufferedImage canvas;

	// Selector de colores;
	SelectorColor selector1;
	SelectorColor selector2;

	// Botones:
	JButton botonNuevo;
	JButton botonBoligrafo;
	JButton botonGoma;
<<<<<<< HEAD
	JButton botonImagen;

	// Buffers para cargar la imagen
	BufferedImage bufferedImageCanvas;

	// Graficos de la goma y lapiz;
	Graphics graficos;
	Graphics2D graphics2d;
	static String respuesta;

	// VARIABLES PROPIAS DE CADA GRUPO:
	// Grupo JesÃºs:
=======
	
	
	//VARIABLES PROPIAS DE CADA GRUPO:
	//Grupo JesÃºs:
>>>>>>> origin/master
	int xAnt;
	int yAnt;


	// Variables para el grosos del lapiz
	private JLabel botonGrosor;

	private JComboBox ComboGrosor;
	String opciones [] ={"2px","5px","10px","20px","30px","40px", "50px","Tamaño XXL"};
	MouseEvent event;

	// Constructor, marca el tamaÃ±o y el cierre del frame

	BufferedImage canvasMouseMotion;
	BufferedImage canvasDibujado;
	
	//Grupo: Álvaro,Ivan y Sergio
	JButton botonSeleccionarImagen;
	BufferedImage img;
	
	
<<<<<<< HEAD
	//Constructor, marca el tamaño y el cierre del frame

=======
	
	//Constructor, marca el tamaÃ±o y el cierre del frame
>>>>>>> origin/master
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(100, 50, 800, 600);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * MÃ©todo que inicializa todos los componentes de la ventana
	 */
	public void inicializarComponentes() {

		ventana.setLayout(new GridBagLayout());

		// ************************************************
		// PANEL SUPERIOR Y COMPONENTES DE PANEL SUPERIOR
		// ************************************************
		panelSuperior = new JPanel();
		panelSuperior.setLayout(new GridBagLayout());
		panelSuperior.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),
				"Herramientas", TitledBorder.CENTER, TitledBorder.TOP));

		GridBagConstraints settings;
		settings = new GridBagConstraints();

		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.ipady = 10;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelSuperior, settings);

		// panel inferior

		panelInferior = new JPanel(new GridBagLayout());

		settings = new GridBagConstraints();

		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelInferior, settings);
<<<<<<< HEAD

		// BotÃ³n nuevo
=======
		
		//BotÃ³n nuevo
>>>>>>> origin/master
		botonNuevo = new JButton(cargarIconoBoton("Imagenes/nuevo.png"));
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.insets = new Insets(0, 10, 0, 0);
		panelSuperior.add(botonNuevo, settings);

		// Selector de color1
		selector1 = new SelectorColor(Color.ORANGE);
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.insets = new Insets(0, 10, 0, 0);
		settings.fill = GridBagConstraints.BOTH;
		panelSuperior.add(selector1, settings);

		// Selector de color2
		selector2 = new SelectorColor(Color.WHITE);
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.insets = new Insets(0, 10, 0, 0);
		settings.fill = GridBagConstraints.BOTH;
		panelSuperior.add(selector2, settings);
<<<<<<< HEAD

		// Herramienta de bolÃ­grafo
=======
		
		
		//Herramienta de bolÃ­grafo
>>>>>>> origin/master
		botonBoligrafo = new JButton(cargarIconoBoton("Imagenes/boligrafo.png"));
		settings = new GridBagConstraints();
		settings.gridx = 3;
		settings.gridy = 0;
		settings.insets = new Insets(0, 10, 0, 0);
		panelSuperior.add(botonBoligrafo, settings);

		// Herramienta de borrar
		botonGoma = new JButton(cargarIconoBoton("Imagenes/borrar.png"));
		settings = new GridBagConstraints();
		settings.gridx = 4;
		settings.gridy = 0;
		settings.insets = new Insets(0, 10, 0, 0);
		panelSuperior.add(botonGoma, settings);

		/**
		 * VUESTRAS HERRAMIENTAS AQUÃ�
		 */
<<<<<<< HEAD
		// TODO: Insertar un botÃ³n e implementar mi herramienta.

		// Un elemento que ocupe todo el espacio a la derecha:
		JPanel panelEspacioDerecha = new JPanel();
		settings = new GridBagConstraints();
		settings.gridx = 6;
=======
		//TODO: Insertar un botÃ³n e implementar mi herramienta.
		
		botonSeleccionarImagen = new JButton(cargarIconoBoton("Imagenes/Icono_imagen.png"));
		settings = new GridBagConstraints();
		settings.gridx = 5; /*** OJO ***/
		settings.gridy = 0;
		settings.insets = new Insets(0, 10, 0, 0);
		panelSuperior.add(botonSeleccionarImagen, settings);
		
		
		
		
		//Un elemento que ocupe todo el espacio a la derecha:
		JPanel panelEspacioDerecha = new JPanel();
		settings = new GridBagConstraints();
		settings.gridx = 6; /*** OJO ***/
>>>>>>> origin/master
		settings.gridy = 0;
		settings.weightx = 1;
		panelSuperior.add(panelEspacioDerecha, settings);

		ComboGrosor = new JComboBox(opciones);
		settings = new GridBagConstraints();
		settings.gridx = 5;
		settings.gridy = 0;
		settings.insets = new Insets(0, 10, 0, 0);
		panelSuperior.add(ComboGrosor,settings);
		

		// ***************************
		// EL LIENZO DONDE PINTAMOS.
		// ***************************
		lienzo = new JLabel();
		lienzo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lienzo.setHorizontalAlignment(SwingConstants.CENTER);
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		panelInferior.add(lienzo, settings);
		ventana.repaint();

	}

	/**
	 * MÃ©todo que inicializa todos los listeners del programa.
	 */
	public void inicializarListeners() {
		
		ComboGrosor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox Auxiliar = (JComboBox)e.getSource();
				superGoma(Auxiliar.getSelectedItem().toString());
				}
		});
		
<<<<<<< HEAD
		
		
		

		// LÃ­stener de carga de VentanaPrincipal. Cuando se carga la pantalla
		// es cuando se puede inicializar el canvas.
=======
		//LÃ­stener de carga de VentanaPrincipal. Cuando se carga la pantalla es cuando se puede inicializar el canvas.
>>>>>>> origin/master
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				borrarCanvas();
			}
		});

		botonNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarCanvas();
			}
		});

		/**
		 * Cada nueva herramienta que aÃ±adas, tendrÃ¡ un nuevo lÃ­stener:
		 */
		botonBoligrafo.addActionListener(anadirListenerHerramienta(BOLIGRAFO));
		botonGoma.addActionListener(anadirListenerHerramienta(GOMA));
<<<<<<< HEAD
		// TODO: AÃ±adir nuevos listeners para las herramientas:

=======
		//TODO: AÃ±adir nuevos listeners para las herramientas:
		botonSeleccionarImagen.addActionListener(anadirListenerHerramienta(INSERTAR_IMAGEN));
		
		
		
>>>>>>> origin/master
		lienzo.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// Dependiendo de la herramienta...
				switch (herramientaActual) {
				case BOLIGRAFO:
					mousePressedBoligrafo(e);
					break;

				case GOMA:
					borraGoma(e);
					break;
				case INSERTAR_IMAGEN:
					insertarSeleccionarImagen(e);
					break;
				default:
					break;
				}

				repintarLienzo();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// Dependiendo de la herramienta...
				switch (herramientaActual) {
				case BOLIGRAFO:
<<<<<<< HEAD
					mouseDraggedBoligrafo(e); // Me vale este mÃ©todo
=======
					mouseDraggedBoligrafo(e); //Me vale este mÃ©todo
>>>>>>> origin/master
					break;

				case GOMA:
					borraGoma(e);
					break;

				default:
					break;
				}
				/** OJO **/
				repintarLienzo();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				borrarCanvasMouseMotion();
				repintarLienzo();
			}

		});

		lienzo.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// Dependiendo de la herramienta...
				switch (herramientaActual) {
				case BOLIGRAFO:
					mouseDraggedBoligrafo(e);
					break;

				case GOMA:
					borraGoma(e);
					break;
<<<<<<< HEAD

=======
				case INSERTAR_IMAGEN:
					insertarSeleccionarImagen(e);
					break;
>>>>>>> origin/master
				default:
					break;
				}
				/** OJO **/
				repintarLienzo();
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				switch (herramientaActual) {
				case GOMA:
					gomaMouseMoved(e);
					break;
				case INSERTAR_IMAGEN:
					imagenMouseMoved(e);
					break;
				default:
					break;
				}				
				/** OJO **/
				repintarLienzo();
			}

		});

	}

	/**
<<<<<<< HEAD
	 * MÃ©todo que Borra el canvas para pintarlo completamente en Blanco. El
	 * nuevo canvas se adapta al tamanio del lienzo.
=======
	 * MÃ©todo que Borra el canvas para pintarlo completamente en Blanco.
	 * El nuevo canvas se adapta al tamanio del lienzo.
>>>>>>> origin/master
	 */
	public void borrarCanvas() {
		canvas = new BufferedImage(panelInferior.getWidth(), panelInferior.getHeight(), BufferedImage.TYPE_INT_ARGB);
		lienzo.setIcon(new ImageIcon(canvas));

		Graphics graficos = canvas.getGraphics();

		
		canvasDibujado = new BufferedImage(panelInferior.getWidth(), panelInferior.getHeight(), BufferedImage.TYPE_INT_ARGB);
		borrarCanvasMouseMotion();
		
		//Modificado para el mouseMotion
		Graphics graphics = canvasDibujado.getGraphics();
		graphics.setColor(selector2.getColor());
		graphics.fillRect(0, 0, panelInferior.getWidth(), panelInferior.getHeight());
		graphics.dispose();
		repintarLienzo();
		
		
	}
	
	public void borrarCanvasMouseMotion(){
		canvasMouseMotion = new BufferedImage(panelInferior.getWidth(), panelInferior.getHeight(), BufferedImage.TYPE_INT_ARGB);
	}
	
	/**
	 * MÃ©todo que nos devuelve un icono para la barra de herramientas superior.
<<<<<<< HEAD
	 * NOTA: SerÃ­a conveniente colocar una imagen con fondo transparente y que
	 * sea cuadrada, para no estropear la interfaz.
	 * 
	 * @param rutaImagen:
	 *            La ruta de la imagen.
=======
	 * NOTA: SerÃ­a conveniente colocar una imagen con fondo transparente y que sea cuadrada, para no estropear la interfaz.
	 * @param rutaImagen: La ruta de la imagen.
>>>>>>> origin/master
	 * @return El ImageIcon que se utilizarÃ¡ en un botÃ³n.
	 */
	public ImageIcon cargarIconoBoton(String rutaImagen) {
		BufferedImage bufferAuxiliar = null;
		try {
			bufferAuxiliar = ImageIO.read(new File(rutaImagen));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ImageIcon(bufferAuxiliar.getScaledInstance(40, 40, BufferedImage.SCALE_SMOOTH));
	}

	/**
<<<<<<< HEAD
	 * MÃ©todo que devuelve un actionListener que cambia la herramienta Actual a
	 * la que se pasa por parÃ¡metros
	 * 
=======
	 * MÃ©todo que devuelve un actionListener que cambia la herramienta Actual a la que se pasa por parÃ¡metros
>>>>>>> origin/master
	 * @param herramienta
	 * @return Un action listener que cambia la herramienta actual. Se puede
	 *         utilizar sobre los botones, por ejemplo.
	 */
	public ActionListener anadirListenerHerramienta(int herramienta) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				herramientaActual = herramienta;
			}
		};
	}

	/**
<<<<<<< HEAD
	 * MÃ©todo que realiza todas las llamadas necesarias para inicializar la
	 * ventana correctamente.
=======
	 * MÃ©todo que realiza todas las llamadas necesarias para inicializar la ventana correctamente.
>>>>>>> origin/master
	 */
	public void inicializar() {
		ventana.setVisible(true);
		inicializarComponentes();
		inicializarListeners();
	}

	/*****************************************
	 *****************************************
	 * AQUÃ� VAN LOS MÃ‰TODOS DE LOS LISTENERS:
	 *****************************************
	 *****************************************/

	/**
	 * Determina xAnt e yAnt para el resto del trazo
	 * 
	 * @param e
	 */
	private void mousePressedBoligrafo(MouseEvent e) {
		xAnt = e.getX();
		yAnt = e.getY();
	}

	/**
	 * Pinta la lÃ­nea del bolÃ­grafo al arrastrar.
<<<<<<< HEAD
	 * 
=======
>>>>>>> origin/master
	 * @param e
	 */

	

	private void mouseDraggedBoligrafo(MouseEvent e){
		Graphics graficos = canvasDibujado.getGraphics();

		graficos.setColor(selector1.getColor());
		graficos.drawLine(xAnt, yAnt, e.getX(), e.getY());
		graficos.dispose();

		xAnt = e.getX();
		yAnt = e.getY();
	}

	/**
	 * Borra donde estÃ© el ratÃ³n.
<<<<<<< HEAD
	 * 
=======
>>>>>>> origin/master
	 * @param e
	 */

	

	private void borraGoma(MouseEvent e){
		Graphics graficos = canvasDibujado.getGraphics();
		graficos.setColor(selector2.getColor());
		graficos.fillOval(e.getX()-(strokeGOMA/2), 
				e.getY()-(strokeGOMA/2), 
				strokeGOMA, 
				strokeGOMA);
		graficos.dispose();
		
	}
	
	private void superGoma(String s){
		switch (s) {
		case "2px":{
			strokeGOMA=2;
			break;
		}
		case "5px":{
			strokeGOMA=5;
			break;
		}
		case "10px":{
			strokeGOMA=10;
			break;
		}
		case "20px":{
			strokeGOMA=20;
			break;
		}
		case "30px":{
			strokeGOMA=30;
			break;
		}
		case "40px":{
			strokeGOMA=40;
			break;
		}
		case "50px":{
			strokeGOMA=50;
			break;
		}
		case "Tamaño XXL":{
			strokeGOMA=1000;
			break;
		}
		}
		
	}

	
	/**
	 * Selecciona o Inserta Imagen dependiendo de lo que pulse el usuario, 
	 * boton izquierda inserta, 
	 * boton derecho busca imagen.
	 * @param e
	 */
	private void insertarSeleccionarImagen(MouseEvent e){
		switch(e.getModifiers()){
		case InputEvent.BUTTON1_MASK:
			insertarImagen(e);
			break;
		case InputEvent.BUTTON3_MASK:
			buscarImagen(e);
			break;
		default:
			break;
		 }
		}
	/**
	 * Método que pinta el movimiento de la goma de borrar. Este método utiliza un canvas auxiliar, de tal modo que no se pinte el canvas original
	 * @param e
	 */
	private void gomaMouseMoved(MouseEvent e){
		borrarCanvasMouseMotion();
		Graphics graficos = canvasMouseMotion.getGraphics();
		graficos.setColor(selector2.getColor());
		graficos.fillOval(e.getX()-(strokeGOMA/2), 
				e.getY()-(strokeGOMA/2), 
				strokeGOMA, 
				strokeGOMA);
		graficos.dispose();
	}
	
	/**
	 * Con la inclusión del canvas auxiliar para mouseMotion, el método repintarLienzo es necesario.
	 * Lo que hace este método es pintar sobre el canvas otros dos BufferedImage:
	 * 		--> canvasDibujado: Es el canvas en el cual se encuentran los dibujos. Estos siempre se mantienen a lo largo del tiempo.
	 * 		--> canvasMouseMotion: Es el canvas que se refresca cada vez que se mueve el ratón
	 */
	private void repintarLienzo(){
		Graphics graficos = canvas.getGraphics();
		graficos.drawImage(canvasDibujado, 0, 0, null);
		graficos.drawImage(canvasMouseMotion, 0, 0, null);
		lienzo.repaint();
	}
	
	private void buscarImagen(MouseEvent e){
		FileFilter filtro = new FileNameExtensionFilter("Imagenes JPG, PNG, GIF", "jpg","png","gif");
		JFileChooser file = new JFileChooser();
		file.addChoosableFileFilter(filtro);
		
		int result = file.showOpenDialog(ventana);
		if(result == JFileChooser.APPROVE_OPTION){
			File selec = file.getSelectedFile();
			try {
				img = ImageIO.read(selec);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	private void imagenMouseMoved(MouseEvent e){
		if(img!=null){
		borrarCanvasMouseMotion();
		int x = e.getX()-(img.getWidth()/4);
		int y = e.getY()-(img.getHeight()/4);
		Graphics gra = canvasMouseMotion.getGraphics();
		gra.drawImage(img.getScaledInstance(img.getWidth()/2, img.getHeight()/2, Image.SCALE_SMOOTH), x, y, null);
		gra.dispose();
		}
		
	}
	
	private void insertarImagen(MouseEvent e){
		if(img!=null){
			int x = e.getX()-(img.getWidth()/4);
			int y = e.getY()-(img.getHeight()/4);
			Graphics gra = canvasDibujado.getGraphics();
			
			gra.drawImage(img.getScaledInstance(img.getWidth()/2, img.getHeight()/2, Image.SCALE_SMOOTH), x, y, null);
			gra.dispose();
		}
		
	}
}