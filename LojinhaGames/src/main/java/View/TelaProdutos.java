/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;
// Importa o DAO (que conversa com MySQL)
import dao.JogoDAO;
// Importa o Model (objeto de dados)
import model.Jogo;
// Utilitário que copia a imagem para a pasta .imagens e devolve o caminho 
import util.ImageStorage;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.Image;
import java.io.File;
import java.util.List;

// Essa é a tela primcipal (View) da aplicação
// Ela herda de Jframe: uma janela do swing
/**
 *
 * @author MIGUELSTEINHORST
 */
public class TelaProdutos extends javax.swing.JFrame {
// Cria o DAO uma única vez para  usar em toda a tela 
    // Ele executa inserir/atualizar/excluir/listar no banco
    private final JogoDAO dao = new JogoDAO();
    
    // Guarda o ID selecionado na tabela 
    // null => modo "novo cadastro"
    // número => modo "edição" de um item existente 
    private Integer idselecionado = null;
    
    // Guarda o arquivo de imagem que o usuário escolheu no computador 
    // (ainda não foi salvo na pasta ./imagens)
    
    // Construtor da tela
    
    /**
     * Creates new form TelaProdutos
     */
    public TelaProdutos() {
        initComponents(); // Monta componentes do formulário (gerado pelo maravilhoso NetBeans sqn)
        configurarTabela(); // Configura clique/seleção da tabela
        recarregarTabela(); // Configura o carregamento dos dados do banco para JTable 
        novo(); // Deixa tudo limpo para cadastrar um game novo
    }
    // ================= LÓGICA (CRUD + IMAGEM) ===============
    private void configurarTabela(){
        // Garante que só um item por vez pode ser selecionado 
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Listener: dispara quando o usuário seleciona uma linha da tabela 
        tabela.getSelectionModel().addListSelectionListener(e-> {
            // Evita disparar duas vezes (um evento "ajustando" e outro final)
            if(!e.getValueIsAdjusting()){
                // pega a linha selecionada 
                int row = tabela.getSelectedRow();
                // Se realmente existe uma linha selecionada...
                if(row >= 0){
                    // Pega o ID (coluna 0)
                    int id = (int) tabela.getModel().getValueAt(row, 0);
                    // Carrega esse jogo nos campos para editar 
                    carregarParaEdicao(id);
                    
                }
            }
        });
        
    }
    private void carregarParaEdicao(int id){
        // Busca o jogo no banco pelo ID 
        Jogo j = dao.buscaPorId(id);
        
        // Se não encontrou, não faz nada 
        if( j == null) return;
        
        // Marca que agora estamos editando (não é mais "novo")
        idSelecionado = j.getID();
        // Preenche os campos do formulário
        txtTitulo.setText(j.getTitulo());
        txtPlataforma.setText(j.getPlataforma());
        txtPreco.setText(String.valueOf(j.getPreco()));
        
        // Mostra o caminho da imagem (ou mensagem se não tiver)
        txtImagem.setText(j.getImagemPath() == null ? "Nenhuma imagem" : j.getImagemPath());
        // Ao carregar para edição não obrigamos a escolher a imagem de novo
        imagemEscolhida = null;
        
        // Mostra a imagem do produto no preview
        mostrarImagem(j.getImagemPath(), false);
        
        
    }
    private void novo() {
        // volta ao modo "novo cadastro"
        idSelecionado = null;
        // Esquece a imagem escolhida no PC
        imagemEscolhida = null;
        
        // Limpa campos 
        txtTitulo.setText("");
        txtPlataforma.setText("");
        txtPreco.setText(t);
        txtImagem.setText("Nenhuma imagem");
        
        // Limpa preview
        lblCapa.setIcon(null);
        lblCapa.setText("Selecione um item");
        
        // Remove seleção da tabela 
        tabela.clearSelection();
        
    }
    
    private void escolherImagem(){
        // Abre a janela do windows para escolher o arquivo 
        JFileChosser chooser = new JFileChooser();
        chooser.setDialogTitle("Escolher a imagem do produto");
        
        // Mostra a janela 
        int result = chooser.showOpenDialog(this);
        // Se o usuário clicou em "Abrir"
        if(result == JFileChooser.APPROVE_OPTION) {
            // Salva o arquivo escolhido
            imagemEscolhida = chooser.getSelectedFile();
            
            // Mostra preview imediatamente usando o caminho absoluto do Windows 
            mostrarImagem(imagemEscolhida.getAbolutePath(), true);
            
            // Informa no campo que a imagem ainda será copiada quando salvar
            txtImagem.setText("Será copiada ao salvar (./imagens/)");
            
        }
    }
    private void salvarOuAtualizar(){
        // Validação: impede salvar campos vazios
        if(txtTitulo.getText().isBlank() || )
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        painelForm = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPlataforma = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtImagem = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btmExcluir = new javax.swing.JButton();
        btnEscolherImagem = new javax.swing.JButton();
        painelTabela = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        painrlPreview = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblCapa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Algerian", 0, 24)); // NOI18N
        jLabel1.setText("Banca de games do Seu Zé");

        jLabel2.setFont(new java.awt.Font("Castellar", 0, 12)); // NOI18N
        jLabel2.setText("CRUD + Upload de Imagem + MySQL(JDBC + DAO)");

        painelForm.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro/Edição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Gothic", 0, 12), new java.awt.Color(255, 0, 0))); // NOI18N
        painelForm.setForeground(new java.awt.Color(204, 204, 204));

        jLabel3.setText("Título");

        jLabel4.setText("Plataforma");

        jLabel5.setText("Preço");

        jLabel6.setText("Imagem");

        txtImagem.setEditable(false);

        btnNovo.setText("Novo");

        btnSalvar.setText("Salvar/editar");

        btmExcluir.setText("Excluir");

        btnEscolherImagem.setText("Escolher imagem");

        javax.swing.GroupLayout painelFormLayout = new javax.swing.GroupLayout(painelForm);
        painelForm.setLayout(painelFormLayout);
        painelFormLayout.setHorizontalGroup(
            painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormLayout.createSequentialGroup()
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addGroup(painelFormLayout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btmExcluir))
                    .addComponent(txtImagem)
                    .addComponent(txtPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTitulo)
                    .addComponent(btnEscolherImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        painelFormLayout.setVerticalGroup(
            painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEscolherImagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btmExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(148, Short.MAX_VALUE))
        );

        painelTabela.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos Cadastrados\n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS PGothic", 0, 12))); // NOI18N

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Título", "Plataforma", "Preço", "Imagem", "Id"
            }
        ));
        jScrollPane1.setViewportView(tabela);

        javax.swing.GroupLayout painelTabelaLayout = new javax.swing.GroupLayout(painelTabela);
        painelTabela.setLayout(painelTabelaLayout);
        painelTabelaLayout.setHorizontalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTabelaLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        painelTabelaLayout.setVerticalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTabelaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painrlPreviewLayout = new javax.swing.GroupLayout(painrlPreview);
        painrlPreview.setLayout(painrlPreviewLayout);
        painrlPreviewLayout.setHorizontalGroup(
            painrlPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        painrlPreviewLayout.setVerticalGroup(
            painrlPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );

        lblCapa.setText("Selecione um item ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblCapa, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblCapa, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(391, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(557, 557, 557))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(painelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(716, 716, 716)
                        .addComponent(painrlPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(painelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(painrlPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmExcluir;
    private javax.swing.JButton btnEscolherImagem;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCapa;
    private javax.swing.JPanel painelForm;
    private javax.swing.JPanel painelTabela;
    private javax.swing.JPanel painrlPreview;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtImagem;
    private javax.swing.JTextField txtPlataforma;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
