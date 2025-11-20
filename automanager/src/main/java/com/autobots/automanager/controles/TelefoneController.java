package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.modelo.TelefoneSelect;
import com.autobots.automanager.repositorios.ClienteRepository;
import com.autobots.automanager.repositorios.TelefoneRepository;



@RestController
@RequestMapping("/telefone")
public class TelefoneController {
    // Using TelefoneSelect (not TelefoneSelecionador)
    @Autowired
    private TelefoneRepository repositorio;
    @Autowired
    private ClienteRepository clienteRepositorio;
    @Autowired
    private TelefoneSelect selecionador;

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> obterTelefone(@PathVariable long id) {
        List<Telefone> telefones = repositorio.findAll();
        Telefone telefone = selecionador.selecionar(telefones, id);
        if (telefone == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(telefone, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Telefone>> obterTelefones() {
        List<Telefone> telefones = repositorio.findAll();
        return new ResponseEntity<>(telefones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Telefone> cadastrarTelefone(@RequestBody Telefone telefone) {
        repositorio.save(telefone);
        return new ResponseEntity<>(telefone, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarTelefone(@PathVariable long id, @RequestBody Telefone atualizacao) {
        if (repositorio.existsById(id)) {
            Telefone telefone = repositorio.findById(id).get();
            TelefoneAtualizador atualizador = new TelefoneAtualizador();
            atualizador.atualizar(telefone, atualizacao);
            repositorio.save(telefone);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTelefone(@PathVariable long id) {
        if (!repositorio.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        List<Cliente> clientes = clienteRepositorio.findAll();
        boolean wasRemoved = false;
        for (Cliente cliente : clientes) {
            boolean removed = cliente.getTelefones().removeIf(tel -> tel.getId().equals(id));
            if (removed) {
                clienteRepositorio.save(cliente);
                wasRemoved = true;
            }
        }
        
        if (!wasRemoved && repositorio.existsById(id)) {
            repositorio.deleteById(id);
        }
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}