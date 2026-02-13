/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author MIGUELSTEINHORST
 */
import java.io.File;
// Importações para manipular arquivos de forma moderna
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
// Importações para trabalhar com data e hora 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;

public class ImageStorage {
    // Define o nome da pasta onde as imagens serão salvas 
    private static final String PASTA_IMAGENS = "imagens";
    // Método estático para salvar imagem 
    // Recebe um arquivo e retorna o caminho salvo 
    public static String salvarImagem( File arquivo) {
     try {
         // Se nenhum arquivo for enviado, retorna null
         if(arquivo == null) return null;
     
// Cria objeto que representa a pasta "imagens"
File pasta = new File(PASTA_IMAGENS);
// se a pasta não existir, ela é criada 
if(!pasta.exists())pasta.mkdir();

// Gera data/hora atual como texto
// Serve para criar nome único
String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
// Pega o nome original do arquivo 
// Remove espaços e troca por "_"
String nomeOrignal = arquivo.getName().replaceAll("\\s+", "_");
// Cria novo nome: data + nome original
String novoNome = timestamp + "_" + nomeOriginal;
// Define o caminho de destino
Path destino = Path.of(PASTA_IMAGENS, novoNome);
// Copia o arquivo para pasta 
// Se existir, substitui
Files.copy(
arquivo.toPath(),destino,
        StandardCopyOption.REPLACE_EXISTING
);
// Retorna o caminho da imagem salva
return PASTA_IMAGENS + "/" + novoNome;
     } catch (Exception e) {
         // Caso dê erro, mostra a mensagem 
         throw new RuntimeException(
         "Erro ao salvar imagem: " + e.getMessage());
     }
         
    }
    }
