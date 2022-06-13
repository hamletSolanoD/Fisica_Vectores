package RecursosCustomizados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ValoresDefault.Constantes;
import javax.swing.JLabel;

public class MensajeError extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public MensajeError(JFrame Padre,String Mensaje) {
		super(Padre, "Error", Dialog.ModalityType.TOOLKIT_MODAL);
		
		setBounds(100, 100, Constantes.PantallaOrdenadorX/6, Constantes.PantallaOrdenadorY/5);
		getContentPane().setLayout(new BorderLayout());
		
		JTextPane lbl_Titulo = new JTextPane();
		lbl_Titulo.setForeground(Constantes.DetallesColor);
		lbl_Titulo.setEditable(false);
		lbl_Titulo.setFont(Constantes.botones);
		lbl_Titulo.setText(Mensaje);
		lbl_Titulo.setBackground(Constantes.SecundarioColor);
		StyledDocument doc = lbl_Titulo.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(lbl_Titulo);
		contentPanel.setBackground(Constantes.SecundarioColor);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel Imagen;
			try {
				Image imagen = ImageIO.read(this.getClass().getResource("/error.png"))
						.getScaledInstance(this.getWidth()/7, this.getWidth()/7, Image.SCALE_SMOOTH);
				Imagen = new JLabel(new ImageIcon(imagen));
				Imagen.setOpaque(false);
				Imagen.setBackground(Constantes.SecundarioColor);
				contentPanel.add(Imagen, BorderLayout.SOUTH);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Constantes.SecundarioColor);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = Constantes.BotonRedondeado("OK",new Color(0,0,0,0),Constantes.TerciarioColor);
				okButton.addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
					}
				});
				
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setVisible(true);
	}

}
