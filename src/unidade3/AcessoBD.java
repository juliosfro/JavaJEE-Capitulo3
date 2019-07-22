package unidade3;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AcessoBD {
	
	public static String usuario = "curso_java";
	public static String senha = "schema";
	public static String url = "jdbc:oracle:thin:@192.168.237.89:1521:XE";
	public static Connection conexao;
	
	public static void conectaBancoOracle() throws SQLException {
		conexao = DriverManager.getConnection(url, usuario, senha);
		conexao.setAutoCommit(false); // Realiza todas as operacoes para depois confirmar.
	}
	
	public static void consultaCliente() throws SQLException {
		String consultaSQL = "select * from cliente";
		Statement statement = conexao.createStatement();
		ResultSet rs = statement.executeQuery(consultaSQL);
		
		while (rs.next()) {
			JOptionPane.showMessageDialog(null, rs.getString("nome"));
			JOptionPane.showMessageDialog(null, rs.getString("email"));
		}
	}
	
	public static void mostraMetaInfoBD() throws SQLException {
		DatabaseMetaData meta = conexao.getMetaData();
		String fabricanteBD = meta.getDatabaseProductName();
		String versaoBD = meta.getDatabaseProductVersion();
		JOptionPane.showMessageDialog(null, fabricanteBD + " " + versaoBD); 
	}

	public static void main(String[] args) {
		try {
			conectaBancoOracle();
			mostraMetaInfoBD();
			consultaCliente();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
