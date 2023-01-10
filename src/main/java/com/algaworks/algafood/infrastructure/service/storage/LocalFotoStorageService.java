package com.algaworks.algafood.infrastructure.service.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.algaworks.algafood.domain.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService {

	@Value("${algafood.storage.local.diretorio-fotos}")
	private Path diretorioFotos;
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
		
		try {
			FileCopyUtils.copy(novaFoto.getInputStream(), 
					Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			throw new StorageException("Não foi possível armazenar o arquivo", e);
		}
	}
	
	@Override
	public void remover(String nomeArquivo) {
		Path arquivoPath = getArquivoPath(nomeArquivo);
		try {
			Files.deleteIfExists(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Não foi possível excluir o arquivo", e);
		}
	}
	
	@Override
	public InputStream recuperar(String nomerAquivo) {
		Path arquivoPath = getArquivoPath(nomerAquivo);
		try {
			return Files.newInputStream(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Não foi possível recuperar o arquivo", e);
		}
	}
	
	private Path getArquivoPath(String nomeArquivo) {
		return diretorioFotos.resolve(Path.of(nomeArquivo));
	}
}
