package com.manus.patrimonio.repository;

import com.manus.patrimonio.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    /**
     * Busca categoria por nome (case insensitive)
     */
    Optional<Categoria> findByNomeIgnoreCase(String nome);

    /**
     * Verifica se existe categoria com o nome especificado
     */
    boolean existsByNomeIgnoreCase(String nome);

    /**
     * Busca categorias que contenham o texto no nome ou descrição
     */
    @Query("SELECT c FROM Categoria c WHERE " +
           "LOWER(c.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(c.descricao) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Categoria> buscarPorNomeOuDescricao(@Param("termo") String termo, Pageable pageable);

    /**
     * Busca categorias ordenadas por nome
     */
    List<Categoria> findAllByOrderByNomeAsc();

    /**
     * Conta o número de bens por categoria
     */
    @Query("SELECT c.nome, COUNT(b) FROM Categoria c LEFT JOIN c.bens b GROUP BY c.id, c.nome")
    List<Object[]> contarBensPorCategoria();

    /**
     * Busca categorias que possuem bens
     */
    @Query("SELECT DISTINCT c FROM Categoria c WHERE SIZE(c.bens) > 0")
    List<Categoria> buscarCategoriasComBens();

    /**
     * Busca categorias que não possuem bens
     */
    @Query("SELECT c FROM Categoria c WHERE SIZE(c.bens) = 0")
    List<Categoria> buscarCategoriasSemBens();
}

