package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.InventarioDTO;
import com.manus.patrimonio.enums.StatusInventario;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.mapper.PatrimonioMapper;
import com.manus.patrimonio.model.Inventario;
import com.manus.patrimonio.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private PatrimonioMapper patrimonioMapper;

    /**
     * Busca todos os inventários com paginação
     */
    public Page<InventarioDTO> buscarTodos(Pageable pageable) {
        Page<Inventario> inventarios = inventarioRepository.findAll(pageable);
        return inventarios.map(patrimonioMapper::inventarioToInventarioDTO);
    }

    /**
     * Busca um inventário por ID
     */
    public InventarioDTO buscarPorId(Long id) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Inventário não encontrado com ID: " + id));
        return patrimonioMapper.inventarioToInventarioDTO(inventario);
    }

    /**
     * Cria um novo inventário
     */
    public InventarioDTO criar(InventarioDTO inventarioDTO) {
        Inventario inventario = patrimonioMapper.inventarioDTOToInventario(inventarioDTO);
        Inventario inventarioSalvo = inventarioRepository.save(inventario);
        return patrimonioMapper.inventarioToInventarioDTO(inventarioSalvo);
    }

    /**
     * Atualiza um inventário existente
     */
    public InventarioDTO atualizar(Long id, InventarioDTO inventarioDTO) {
        if (!inventarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Inventário não encontrado com ID: " + id);
        }
        
        inventarioDTO.setId(id);
        Inventario inventario = patrimonioMapper.inventarioDTOToInventario(inventarioDTO);
        Inventario inventarioSalvo = inventarioRepository.save(inventario);
        return patrimonioMapper.inventarioToInventarioDTO(inventarioSalvo);
    }

    /**
     * Remove um inventário
     */
    public void remover(Long id) {
        if (!inventarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Inventário não encontrado com ID: " + id);
        }
        inventarioRepository.deleteById(id);
    }

    /**
     * Busca inventários por status
     */
    public List<InventarioDTO> buscarPorStatus(StatusInventario status) {
        List<Inventario> inventarios = inventarioRepository.findByStatusOrderByDataInicioDesc(status);
        return patrimonioMapper.inventariosToInventarioDTOs(inventarios);
    }

    /**
     * Busca inventários por responsável
     */
    public List<InventarioDTO> buscarPorResponsavel(String responsavel) {
        List<Inventario> inventarios = inventarioRepository.findByResponsavelContainingIgnoreCase(responsavel, Pageable.unpaged()).getContent();
        return patrimonioMapper.inventariosToInventarioDTOs(inventarios);
    }

    /**
     * Busca inventários em andamento
     */
    public List<InventarioDTO> buscarEmAndamento() {
        List<Inventario> inventarios = inventarioRepository.findByStatusOrderByDataInicioDesc(StatusInventario.EM_ANDAMENTO);
        return patrimonioMapper.inventariosToInventarioDTOs(inventarios);
    }

    /**
     * Finaliza um inventário
     */
    public InventarioDTO finalizarInventario(Long id) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Inventário não encontrado com ID: " + id));
        
        inventario.setStatus(StatusInventario.CONCLUIDO);
        Inventario inventarioSalvo = inventarioRepository.save(inventario);
        return patrimonioMapper.inventarioToInventarioDTO(inventarioSalvo);
    }
}
