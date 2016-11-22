import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class VentanaPrincipal {

	final int strokeGOMA = 10;

	/**
	 * IMPORTANTE: CADA HERRAMIENTA TENDRÃ� UN CÃ“DIGO ASOCIADO
	 */
	final static int BOLIGRAFO = 0;
	final static int GOMA = 1;
	// NUESTRA HERRAMIENTA
	final static int POLIGONO = 2;

	int herramientaActual = -1; // No hay nada por defecto.

	// La ventana principal, en este caso, guarda todos los componentes:
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

	// VARIABLES PROPIAS DE CADA GRUPO:
	// Grupo Jesús:
	int xAnt;
	int yAnt;
	BufferedImage canvasMouseMotion;
	BufferedImage canvasDibujado;

	// Variables nuestras

	JButton botonPoligono;
	int x[];
	int y[];

	// Constructor, marca el tamaÃ±o y el cierre del frame
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

		// BotÃ³n nuevo
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

		// Herramienta de bolÃ­grafo
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

		/* NUESTRA HERRAMIENTA */

		botonPoligono = new JButton(cargarIconoBoton("Imagenes/poligono.png"));
		settings = new GridBagConstraints();
		settings.gridx = 5;
		settings.gridy = 0;
		settings.insets = new Insets(0, 10, 0, 0);
		panelSuperior.add(botonPoligono, settings);

		// Un elemento que ocupe todo el espacio a la derecha:
		JPanel panelEspacioDerecha = new JPanel();
		settings = new GridBagConstraints();
		settings.gridx = 6; /*** OJO ***/
		settings.gridy = 0;
		settings.weightx = 1;
		panelSuperior.add(panelEspacioDerecha, settings);

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

		// LÃ­stener de carga de VentanaPrincipal. Cuando se carga la pantalla
		// es cuando se puede inicializar el canvas.
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

		// AÑADIMOS LOS LISTENERS A NUESTRO BOTON
		botonPoligono.addActionListener(anadirListenerHerramienta(POLIGONO));

		botonPoligono.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int lados = 0;
					do {
						lados = Integer.parseInt(JOptionPane.showInputDialog("Introduce un número de lados"));
					} while (lados < 3);
					int radio = Integer.parseInt(
							JOptionPane.showInputDialog("Introduce la medida del radio que contendría el polígono"));

					cogePuntos(lados, radio, 1, 1);
				} catch (NumberFormatException e) {
					actionPerformed(arg0);
				}
			}

			private void cogePuntos(int n, int r, int p1, int p2) {
				double xAux[] = new double[n];
				double yAux[] = new double[n];
				for (int i = 0; i < n; i++) {

					xAux[i] = (p1 + r * Math.cos(2 * Math.PI * i / n));
					yAux[i] = (p2 + r * Math.sin(2 * Math.PI * i / n));
				}
				x = new int[n];
				y = new int[n];

				for (int j = 0; j < x.length; j++) {
					x[j] = (int) Math.round(xAux[j]);
					y[j] = (int) Math.round(yAux[j]);
				}
			}

		});

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
				case POLIGONO:

					mousePressedPolygono(e);

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
					mouseDraggedBoligrafo(e); // Me vale este mÃ©todo
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

				case POLIGONO:// AÑADIMOS LA FUNCIONALIDAD DE QUE EL POLÍGONO SE
								// PINTE MIENTRAS SE ARRASTRA EL RATÓN CON EL
								// BOTÓN PULSADO
					arrastrarPoligono(e);
					break;
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

				case POLIGONO:// AÑADIMOS LA FUNCIONALIDAD DE QUE EL POLÍGONO
								// APAREZCA JUNTO AL PUNTERO DEL RATÓN
					poligonoMouseMoved(e);
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
	 * MÃ©todo que Borra el canvas para pintarlo completamente en Blanco. El
	 * nuevo canvas se adapta al tamanio del lienzo.
	 */
	public void borrarCanvas() {
		canvas = new BufferedImage(panelInferior.getWidth(), panelInferior.getHeight(), BufferedImage.TYPE_INT_ARGB);
		lienzo.setIcon(new ImageIcon(canvas));

		canvasDibujado = new BufferedImage(panelInferior.getWidth(), panelInferior.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		borrarCanvasMouseMotion();

		// Modificado para el mouseMotion
		Graphics graficos = canvasDibujado.getGraphics();
		graficos.setColor(selector2.getColor());
		graficos.fillRect(0, 0, panelInferior.getWidth(), panelInferior.getHeight());
		graficos.dispose();
		repintarLienzo();

	}

	public void borrarCanvasMouseMotion() {
		canvasMouseMotion = new BufferedImage(panelInferior.getWidth(), panelInferior.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
	}

	/**
	 * MÃ©todo que nos devuelve un icono para la barra de herramientas superior.
	 * NOTA: SerÃ­a conveniente colocar una imagen con fondo transparente y que
	 * sea cuadrada, para no estropear la interfaz.
	 * 
	 * @param rutaImagen:
	 *            La ruta de la imagen.
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
	 * MÃ©todo que devuelve un actionListener que cambia la herramienta Actual a
	 * la que se pasa por parÃ¡metros
	 * 
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
	 * MÃ©todo que realiza todas las llamadas necesarias para inicializar la
	 * ventana correctamente.
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

	/* Pinta el polígno cuando se presiona */

	private void mousePressedPolygono(MouseEvent e) {

		int aux1 = e.getX();
		int aux2 = e.getY();
		for (int i = 0; i < x.length; i++) {
			x[i] += aux1;
			y[i] += aux2;

		}
		Graphics graficos = canvasDibujado.getGraphics();
		graficos.setColor(selector1.getColor());

		graficos.drawPolygon(x, y, x.length);
		graficos.dispose();
		for (int i = 0; i < x.length; i++) {
			x[i] -= aux1;
			y[i] -= aux2;

		}

	}

	// Aparece el polígono con el ratón después de crearlo
	private void poligonoMouseMoved(MouseEvent e) {
		borrarCanvasMouseMotion();
		int aux1 = e.getX();
		int aux2 = e.getY();
		for (int i = 0; i < x.length; i++) {
			x[i] += aux1;
			y[i] += aux2;

		}
		Graphics graficos = canvasMouseMotion.getGraphics();
		graficos.setColor(selector1.getColor());
		graficos.drawPolygon(x, y, x.length);
		graficos.dispose();
		for (int i = 0; i < x.length; i++) {
			x[i] -= aux1;
			y[i] -= aux2;

		}
	}

	// Se pinta el polígono mientras se arrastra el ratón con el botón pulsado
	private void arrastrarPoligono(MouseEvent e) {
		System.out.println("Pintando");
		int aux1 = e.getX();
		int aux2 = e.getY();
		for (int i = 0; i < x.length; i++) {
			x[i] += aux1;
			y[i] += aux2;

		}
		Graphics graficos = canvasDibujado.getGraphics();
		graficos.setColor(selector1.getColor());

		graficos.drawPolygon(x, y, x.length);
		graficos.dispose();
		for (int i = 0; i < x.length; i++) {
			x[i] -= aux1;
			y[i] -= aux2;

		}

	}

	/**
	 * Pinta la lÃ­nea del bolÃ­grafo al arrastrar.
	 * 
	 * @param e
	 */
	private void mouseDraggedBoligrafo(MouseEvent e) {
		Graphics graficos = canvasDibujado.getGraphics();
		graficos.setColor(selector1.getColor());
		graficos.drawLine(xAnt, yAnt, e.getX(), e.getY());
		graficos.dispose();

		xAnt = e.getX();
		yAnt = e.getY();
	}

	/**
	 * Borra donde estÃ© el ratÃ³n.
	 * 
	 * @param e
	 */
	private void borraGoma(MouseEvent e) {
		Graphics graficos = canvasDibujado.getGraphics();
		graficos.setColor(selector2.getColor());
		graficos.fillOval(e.getX() - (strokeGOMA / 2), e.getY() - (strokeGOMA / 2), strokeGOMA, strokeGOMA);
		graficos.dispose();
	}

	/**
	 * Método que pinta el movimiento de la goma de borrar. Este método utiliza
	 * un canvas auxiliar, de tal modo que no se pinte el canvas original
	 * 
	 * @param e
	 */
	private void gomaMouseMoved(MouseEvent e) {
		borrarCanvasMouseMotion();
		Graphics graficos = canvasMouseMotion.getGraphics();
		graficos.setColor(selector2.getColor());
		graficos.fillOval(e.getX() - (strokeGOMA / 2), e.getY() - (strokeGOMA / 2), strokeGOMA, strokeGOMA);
		graficos.dispose();
	}

	/**
	 * Con la inclusión del canvas auxiliar para mouseMotion, el método
	 * repintarLienzo es necesario. Lo que hace este método es pintar sobre el
	 * canvas otros dos BufferedImage: --> canvasDibujado: Es el canvas en el
	 * cual se encuentran los dibujos. Estos siempre se mantienen a lo largo del
	 * tiempo. --> canvasMouseMotion: Es el canvas que se refresca cada vez que
	 * se mueve el ratón
	 */
	private void repintarLienzo() {
		Graphics graficos = canvas.getGraphics();
		graficos.drawImage(canvasDibujado, 0, 0, null);
		graficos.drawImage(canvasMouseMotion, 0, 0, null);
		lienzo.repaint();
	}

}