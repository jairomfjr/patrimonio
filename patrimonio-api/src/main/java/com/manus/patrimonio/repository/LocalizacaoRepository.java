package com.manus.patrimonio.repository;

import com.manus.patrimonio.model.Localizacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {

    /**
     * Busca localização por nome (case insensitive)
     */
    Optional<Localizacao> findByNomeIgnoreCase(String nome);

    /**
     * Verifica se existe localização com o nome especificado
     */
    boolean existsByNomeIgnoreCase(String nome);

    /**
     * Busca localizações que contenham o texto no nome, endereço ou descrição
     */
    @Query("SELECT l FROM Localizacao l WHERE " +
           "LOWER(l.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.endereco) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.descricao) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.responsavel) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Localizacao> buscarPorTexto(@Param("termo") String termo, Pageable pageable);

    /**
     * Busca localizações ordenadas por nome
     */
    List<Localizacao> findAllByOrderByNomeAsc();

    /**
     * Conta o número de bens por localização
     */
    @Query("SELECT l.nome, COUNT(b) FROM Localizacao l LEFT JOIN l.bensAtuais b GROUP BY l.id, l.nome")
    List<Object[]> contarBensPorLocalizacao();

    /**
     * Busca localizações por responsável
     */
    List<Localizacao> findByResponsavelContainingIgnoreCase(String responsavel);

    /**
     * Busca localizações por endereço
     */
    List<Localizacao> findByEnderecoContainingIgnoreCase(String endereco);

    /**
     * Busca localizações que possuem bens
     */
    @Query("SELECT DISTINCT l FROM Localizacao l WHERE SIZE(l.bensAtuais) > 0")
    List<Localizacao> buscarLocalizacoesComBens();

    /**
     * Busca localizações que não possuem bens
     */
    @Query("SELECT l FROM Localizacao l WHERE SIZE(l.bensAtuais) = 0")
    List<Localizacao> buscarLocalizacoesSemBens();
}

