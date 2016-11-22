import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class pintaSpray extends Thread {

	MouseEvent e;
	VentanaPrincipal ventana;
	int x;
	int y;

	public pintaSpray(VentanaPrincipal ventana, MouseEvent e) {
		this.ventana = ventana;
		this.e = e;
	}

	public void cambiarCordenadas(int posX, int posY) {

		x = posX;
		y = posY;
	}

	@Override
	public void run() {
		while (VentanaPrincipal.sprayBotonPulsado) {
			Graphics graficos = ventana.canvasDibujado.getGraphics();
			graficos.setColor(ventana.selector1.getColor());
			double angulo = Math.random() * Math.PI * 2;
			double radio = Math.random() * ventana.strokeGOMA;
			int posXSpray = (int) (Math.cos(angulo) * radio);
			int posYSpray = (int) (Math.sin(angulo) * radio);
			graficos.fillOval(x + posXSpray, y + posYSpray, 3, 3);
			
			ventana.repintarLienzo();
			try {
				sleep(3);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
