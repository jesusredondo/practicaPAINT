import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class VentanaPrincipal {

	final int strokeGOMA = 10;

	/**
	 * IMPORTANTE: CADA HERRAMIENTA TENDRÃ UN CÃ“DIGO ASOCIADO
	 */
	final static int BOLIGRAFO = 0;
	final static int GOMA = 1;
	final static int TEXTO = 20;
	// AÃ‘ADE AQUÃ TU HERRAMIENTA;
	// TODO: AÃ±adir la herramienta

	// JTextField para introducir el texto
	JTextField jtextFieldTexto;

	// Labels
	JLabel labelEstiloLetra;
	JLabel labelFuenteLetra;
	JLabel labelTamanioLetra;

	// Fuente para establecer la fuente en el lienzo
	// fuente auxiliar se genera solo para el campo jtextfield con un numero fijo de tamaño de letra
	Font fuente;
	Font fuenteAux;

	// Color fuente
	Color colorFuente;

	// Listas componente texto
	JComboBox comboBoxEstiloLetra;
	JComboBox comboBoxFuenteLetra;
	JComboBox comboBoxTamanioLetra;

	// variables dinamicas, escuchas de los comboBox
	String estiloLetraComboBox;
	String fuenteLetraComboBox;
	String tamanioLetraComboBox;

	// Relleno ComboBox estilo de letra 0,1,2
	String[] estiloLetra = { "Normal", "Negrita", "Curisva" };

	// Relleno ComoboBox tamaño de letra
	String[] tamanioLetra;

	int herramientaActual = -1; // No hay nada por defecto.

	// La ventana principal, en este caso, guarda todos los componentes:
	JFrame ventana;

	// Paneles:
	JPanel panelSuperior;
	JPanel panelInferior;
	JPanel panelTexto;

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
	JButton botonTexto;

	// VARIABLES PROPIAS DE CADA GRUPO:
	// Grupo JesÃºs:
	int xAnt;
	int yAnt;
	BufferedImage canvasMouseMotion;
	BufferedImage canvasDibujado;

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

		/**
		 * VUESTRAS HERRAMIENTAS AQUÃ�
		 */
		// TODO: Insertar un botÃ³n e implementar mi herramienta.

		// Un elemento que ocupe todo el espacio a la derecha:
		JPanel panelEspacioDerecha = new JPanel();
		settings = new GridBagConstraints();
		settings.gridx = 6; /*** OJO ***/
		settings.gridy = 0;
		settings.weightx = 1;
		panelSuperior.add(panelEspacioDerecha, settings);

		// *******************************************************************************
		// PABLO Y JOSE
		// panel de texto y componente
		panelTexto = new JPanel();
		panelTexto.setLayout(new GridBagLayout());
		panelTexto.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		// Jtextfield para el texto
		jtextFieldTexto = new JTextField();
		jtextFieldTexto.setEditable(false); //por defecto
		settings = new GridBagConstraints();
		settings.gridwidth = 3;
		settings.gridx = 1;
		settings.gridy = 0;
		settings.fill = GridBagConstraints.HORIZONTAL;
		panelTexto.add(jtextFieldTexto, settings);

		// Herramienta para crear texto
		botonTexto = new JButton(cargarIconoBoton("Imagenes/texto.png"));
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.gridheight = 3;
		settings.fill = GridBagConstraints.BOTH;
		settings.insets = new Insets(1, 1, 1, 1);
		panelTexto.add(botonTexto, settings);

		// labels del componente
		labelFuenteLetra = new JLabel("Fuente");
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 1;
		settings.gridheight = 1;
		settings.fill = GridBagConstraints.BOTH;
		settings.insets = new Insets(1, 1, 1, 1);
		panelTexto.add(labelFuenteLetra, settings);

		labelTamanioLetra = new JLabel("Tamaño");
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 1;
		settings.gridheight = 1;
		settings.insets = new Insets(1, 1, 1, 1);
		panelTexto.add(labelTamanioLetra, settings);

		labelEstiloLetra = new JLabel("Estilo");
		settings = new GridBagConstraints();
		settings.gridx = 3;
		settings.gridy = 1;
		settings.gridheight = 1;
		settings.insets = new Insets(1, 1, 1, 1);
		panelTexto.add(labelEstiloLetra, settings);

		// jComboBox del componente
		comboBoxFuenteLetra = new JComboBox(fuentesLetras());
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 2;
		settings.gridheight = 1;
		settings.insets = new Insets(1, 1, 1, 1);
		panelTexto.add(comboBoxFuenteLetra, settings);

		// relleno del comboBox del tamaño de letra
		tamanioLetra = new String[100];
		for (int i = 0; i < 100; i++) {
			tamanioLetra[i] = "" + (i + 1);

		}
		comboBoxTamanioLetra = new JComboBox(tamanioLetra);
		comboBoxTamanioLetra.setSelectedIndex(16); // sale por defecto el tamaño
													// 12
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 2;
		settings.gridheight = 1;
		settings.insets = new Insets(1, 1, 1, 1);
		panelTexto.add(comboBoxTamanioLetra, settings);

		comboBoxEstiloLetra = new JComboBox(estiloLetra);
		settings = new GridBagConstraints();
		settings.gridx = 3;
		settings.gridy = 2;
		settings.gridheight = 1;
		settings.insets = new Insets(1, 1, 1, 1);
		panelTexto.add(comboBoxEstiloLetra, settings);

		// insertar panel de texto en el panel superior
		settings = new GridBagConstraints();
		settings.gridx = 5;
		settings.gridy = 0;
		settings.insets = new Insets(0, 10, 0, 0);
		panelSuperior.add(panelTexto, settings);

		// **************************************************************************FIN
		// FIN PABLO Y JOSE

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
		//pone a true o false el botonTexto para crear el texto
		botonTexto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				establecerCampoTexto();
			}
		});

		/**
		 * Cada nueva herramienta que aÃ±adas, tendrÃ¡ un nuevo lÃ­stener:
		 */
		botonBoligrafo.addActionListener(anadirListenerHerramienta(BOLIGRAFO));
		botonGoma.addActionListener(anadirListenerHerramienta(GOMA));
		// TODO: AÃ±adir nuevos listeners para las herramientas:
		botonTexto.addActionListener(anadirListenerHerramienta(TEXTO));

		lienzo.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// al hacer clic pinta en el lienzo el texto del campo jtextfield
				switch (herramientaActual) {
				case TEXTO:
					incluirCampoTexto(e);
					break;
				default:
					break;
				}
				/** OJO **/
				repintarLienzo();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// Dependiendo de la herramienta...
				switch (herramientaActual) {
				case BOLIGRAFO:
					mousePressedBoligrafo(e);
					break;
				case GOMA:
					borraGoma(e);
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
					
				default:
					break;
				}				
				/** OJO **/
				repintarLienzo();
			}

		});
		//************LISTENER PARA COMBOBOX*************
		// escucho el comboBox de la fuente de letra
		comboBoxFuenteLetra.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				fuenteLetraComboBox = e.getItem().toString();
				establecerFuenteCampoTexto();
			}
		});
		// escucho el comboBox del tamanio de letra
		comboBoxTamanioLetra.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				tamanioLetraComboBox = e.getItem().toString();
				establecerFuenteCampoTexto();
			}
		});
		// escucho el comboBox del estilo de letra
		comboBoxEstiloLetra.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				estiloLetraComboBox = e.getItem().toString();
				establecerFuenteCampoTexto();
			}
		});
		//*************FIN LISTENER COMBOBOX**************************

	}

	/**
	 * MÃ©todo que Borra el canvas para pintarlo completamente en Blanco. El
	 * nuevo canvas se adapta al tamanio del lienzo.
	 */
	public void borrarCanvas(){
		canvas = new BufferedImage(panelInferior.getWidth(), panelInferior.getHeight(), BufferedImage.TYPE_INT_ARGB);
		lienzo.setIcon(new ImageIcon(canvas));
		
		
		canvasDibujado = new BufferedImage(panelInferior.getWidth(), panelInferior.getHeight(), BufferedImage.TYPE_INT_ARGB);
		borrarCanvasMouseMotion();
		
		//Modificado para el mouseMotion
		Graphics graficos = canvasDibujado.getGraphics();
		graficos.setColor(selector2.getColor());
		graficos.fillRect(0, 0, panelInferior.getWidth(), panelInferior.getHeight());
		graficos.dispose();
		repintarLienzo();
		
		
	}
	
	public void borrarCanvasMouseMotion(){
		canvasMouseMotion = new BufferedImage(panelInferior.getWidth(), panelInferior.getHeight(), BufferedImage.TYPE_INT_ARGB);
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
	
	/**
	 * Pinta la línea del bolígrafo al arrastrar.
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
	 * 
	 * @param e
	 */
	/**
	 * Borra donde esté el ratón.
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
	public void establecerFuenteCampoTexto() {
		int tipofuente = 0;
		
		// por defecto se establece la fuente, tamaño y tipo de letra
		if (fuenteLetraComboBox == null) {
			fuenteLetraComboBox = comboBoxFuenteLetra.getSelectedItem().toString();
		}
		if (tamanioLetraComboBox == null) {
			tamanioLetraComboBox = comboBoxTamanioLetra.getSelectedItem().toString();
		}
		if (estiloLetraComboBox == null) {
			estiloLetraComboBox = "" + 0;
		}

		// dependiendo del estilo de letra
		switch (estiloLetraComboBox) {
		case "Normal":
			tipofuente = 0;
			break;
		case "Negrita":
			tipofuente = 1;
			break;
		case "Cursiva":
			tipofuente = 2;
			break;
		}
		// creamo una fuente nueva
		fuente = new Font(fuenteLetraComboBox, tipofuente, Integer.parseInt(tamanioLetraComboBox));
		fuenteAux = new Font(fuenteLetraComboBox, tipofuente, 16);
		//se asigna al jtextfield
		jtextFieldTexto.setFont(fuenteAux);
		// cambiar el color de texto del jtextField
		jtextFieldTexto.setForeground(selector1.getColor());

	}

	/**
	 * Si el jtextfiel es true incluye en el lienzo la fuente elegida
	 * en las coordenadas dadas por el ratón 
	 * @param e
	 * 
	 */
	// incluir el campo texto en el lienzo
	private void incluirCampoTexto(MouseEvent e) {
		if (jtextFieldTexto.isEditable()) {
			Graphics graficos = canvasDibujado.getGraphics();
			graficos.setColor(selector1.getColor());
			graficos.setFont(fuente);
			graficos.drawString(jtextFieldTexto.getText(), e.getX(), e.getY());
		}

	}
	/**
	 * cambia el valor de editable del botonTexto
	 */
	public void establecerCampoTexto(){
		if (jtextFieldTexto.isEditable()) {
			jtextFieldTexto.setEditable(false);
		}else{
			jtextFieldTexto.setEditable(true);
		}
	}
	/**
	 * Crea un String[] de las fuentes del sistema
	 * rellena el comboBoxFuenteLetra
	 * @return
	 */
	private String[] fuentesLetras() {

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fuentes = ge.getAvailableFontFamilyNames();
		return fuentes;

	}

}