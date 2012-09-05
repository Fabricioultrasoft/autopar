package autopar.controller.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import autopar.controller.db.MySQLDBController;
import autopar.model.Produto;
import autopar.window.TelaPrincipal;
import autopar.window.TelaUploadImagens;
import javax.xml.bind.DatatypeConverter;

public class TelaUploadImagensController implements ActionListener {

	private TelaUploadImagens tela;
	private MySQLDBController msc;
	private File [] _files;
	

	public TelaUploadImagensController(TelaPrincipal tela, MySQLDBController msc, Produto p)
	{
		this.msc = msc;
		try {
			this.msc.getImagens(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tela = new TelaUploadImagens(tela, p);
		this.tela.setListener(this);
	}

	public void show()
	{
		tela.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getActionCommand().equals("SEND_FILE") ) {

			JFileChooser _fileChooser = new JFileChooser();
			_fileChooser.setMultiSelectionEnabled(true);

			int returnval = _fileChooser.showOpenDialog(this.tela);

			if (returnval == JFileChooser.APPROVE_OPTION) {
				_files = _fileChooser.getSelectedFiles();

				StringBuilder sb = new StringBuilder();
				
				for (File _file : _files) {
					if( !uploadFile(_file) )
						sb.append("Erro ao enviar a imagem: " + _file.getName()).append('\n');
				}
				
				if (sb.length() > 0)
					JOptionPane.showMessageDialog(this.tela, sb.toString(), "Confirma��o", JOptionPane.ERROR_MESSAGE);

			}
		} else if (arg0.getActionCommand().equals("DELETE_FILE") ) {
			
			int [] arquivos = this.tela.getV_imagens().getSelectedIndices();
			String [] files = new String[arquivos.length];
			
			StringBuilder sb = new StringBuilder();
			
			for (int i=0; i<arquivos.length; i++) {
				files[i] = this.tela.get_dados().get(arquivos[i]);
			}

			for (int i=0; i<files.length; i++) {
				if ( !removeImagem( files[i] ) )
					sb.append("Erro ao excluir a imagem: " + files[i]).append('\n');
			}
			
			if (sb.length() > 0)
				JOptionPane.showMessageDialog(this.tela, sb.toString(), "Confirma��o", JOptionPane.ERROR_MESSAGE);
			
		} else if (arg0.getActionCommand().equals("CLOSE") ) {
			tela.dispose();
		}
	}

	public boolean uploadFile(File f)
	{
		try {
			String imageDataString = DatatypeConverter.printBase64Binary(imageToBytes(f));
			String data = "nome=" + URLEncoder.encode(f.getName(), "UTF-8") + "&imagem=" + URLEncoder.encode(imageDataString, "UTF-8");    

			// Send data
			URL url = new URL("http://autopar1.dominiotemporario.com/up/up.php");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			BufferedReader rd;
			try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
				wr.write(data);
				wr.flush();
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				if ((line = rd.readLine()).equals("estevan vc e foda e pintudo")) {
					addImagem(f);
				} else {
					rd.close();
					return false;
				}
			}
			rd.close();
		} catch (Exception e) {
		}

		return true;
	}

	public static byte[] imageToBytes(File inFile) throws IOException {   
		InputStream is = null;    
		byte[] buffer = null;
		is = new FileInputStream(inFile);   
		buffer = new byte[is.available()];   
		is.read(buffer);   
		is.close();   
		return buffer;  
	}
	
	public void addImagem(File f) {
		try {
			this.msc.addImagem(this.tela.get_p(), f.getName());
			this.tela.get_dados().addElement(f.getName());
		} catch (SQLException e) {
			System.out.println("Erro ao inserir a imagem");
		}
	}
	
	public boolean removeImagem(String f) {

		try {
			String data = "nome=" + URLEncoder.encode(f, "UTF-8");    

			// Send data
			URL url = new URL("http://autopar1.dominiotemporario.com/up/delete.php");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			BufferedReader rd;
			try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
				wr.write(data);
				wr.flush();
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				if ((line = rd.readLine()).equals("estevan vc e foda e pintudo")) {
						this.msc.removeImagem(this.tela.get_p(), f);
						this.tela.get_dados().removeElement(f);
				} else {
					rd.close();
					return false;
				}
			}
			rd.close();
		} catch (SQLException e) {
			System.out.println("Erro ao deletar a imagem");
		} catch (Exception e) {
			System.out.println("Erro ao deletar a imagem2" + e.getMessage());
		}
		
		return true;
	}

	public MySQLDBController getMsc() {
		return msc;
	}

	public void setMsc(MySQLDBController msc) {
		this.msc = msc;
	}
}
