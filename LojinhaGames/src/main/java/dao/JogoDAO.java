/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

    import model.Jogo;
import util.ConnectionFactory;
// Importações do JDBC (Java Database Connectivity)
import java.sql.*; // quando queremos importar muitas coisas usamos o *

// Importações para trabalhar com listas 
import java.util.ArrayList;
import java.util.List;
/**

/**
 *
 * @author MIGUELSTEINHORST
 */



public class JogoDAO {
    // ===========================
    // INSERIR (CREATE)
    // ===========================
    public void inserir(Jogo j){
        // Comando SQL para inserir dados no banco 
        // ? são parâmetros que serão preenchidos depois 
        String sql = "INSERT INTO jogo(titulo, plataforma, preco, imagem_path) VALUES (?, ?, ?, ?,";
        // try-with-resources:
        // Abre a conexão e fecha automaticamente depois 
        try (Connection c = ConnectionFactory.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)){
            // Define os valores nos ? da query
            ps.setString(1, j.getTitulo());
            ps.setString(2, j.getPlataforma());
            ps.setDouble(3, j.getPreco());
            ps.setString(4, j.getImagemPath());
            // Executa o INSERT no banco 
            ps.executeLargeUpdate();
            
        } catch (Exception e){
            // Caso dê erro, mostre a mensagem
            throw new RuntimeException("Erro ao inserir: " + e.getMessage());
                }
    }
    

// ==================
// ATUALIZAR (UPDATE)
// ==================

public void atualizar(Jogo j){
String sql = "UPDATE jogo SET titulo = ?, plataforma = ?, preco = ?, imagem_path = ? WHERE id?";
try (Connection c = ConnectionFactory.getConnection();
PreparedStatement ps = c.prepareStatement(sql)) {
ps.setString(1, j.getTitulo());
ps.setString(2, j.getPlataforma());
ps.setDouble(3, j.getPreco());
ps.setString(4, j.getImagemPath());

// Define qual ID será atualizado 
ps.setInt(5, j.getId());
// Executa o UPDATE
ps.executeUpdate();

}catch (Exception e){
throw new RuntimeException("Erro ao atualizar: " + e.getMessage());

}
}
// ==========================
// EXCLUIR (DELETE)
// ==========================

public void excluir(int id){
    // SQL para deletar pelo ID
    String sql = "DELETE FROM jogo WHERE id = ?";
    try (Connection c = ConnectionFactory.getConnection();
            PreparedStatement ps = c.preparedStatement(sql)){
        // Define qual ID será excluido
        ps.setInt(1,id);
        // Executa o DELETE 
        ps.executeUpdate();
        
    } catch (Exception e){
        throw new RuntimeException("Erro ao excluir: " + e.getMessage());
        
    } 
}
// ========================
// LISTAR (READ)
// ========================
public List<Jogo> listar(){
    //Lista para guardar os jogos
    List<Jogo> lista = new ArrayList<>();
    // SQL para buscar todos
    String sql = "SELECT * FROM jogo ORDER BY titulo";
    try (Connection c = ConnectionFactory.getConnection());
    Statement st = c.createStatement();
    ResultSet rs = st.executeQuery(sql)){
        // Equanto houver resultados...
        while(rs.next()){
            
            // Cria um objeto Jogo
            Jogo j = new Jogo();
            // Pega dados do banco e coloca no objeto 
            j.setId(rs.getInt("id"));
            j.setTitulo(rs.getString("titulo"));
            j.setPlataforma(rs.getString("plataforma"));
            j.setPreco(rs.getDouble("preco"));
            j.setImagemPath(rs.getString("imagem_path"));
            
            // Adiciona na lista 
            lista.add(j);
        }            
        } catch (Exception e) {
          throw new RuntimeException("Erro ao listar: " + e.getMessage());
                
                }
        // Retorna a lista completa 
        return lista;
        
        }
    // =======================
    // BUSCAR POR ID 
    // =======================
    public Jogo buscaPorId(int id){
        String sql = "SELCT * FROM jogo WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)){
            // Define ID a buscar
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()){
                // se encontrou...
                if(rs.next()){
                    Jogo j = new Jogo();
                    
                    j.setId(rs.getInt("id"));
                    j.setTitulo(rs.getString("titulo"));
                    j.setPlataforma(rs.getString("plataforma"));
                    j.setPreco(rs.getDouble("preco"));
                    j.setImagemPath(rs.getString("imagem_path"));
                    
                    return j;
                }
                
                
            }
        }catch (Exception e) {
            throw new RuntimeException ("Erro ao buscar: " + e.getMessage());
            
        }
        // Se não encontar, retorna null
        return null;
            
        }
