    package com.delivery.trabalhoDelivery.controller;

    import com.delivery.trabalhoDelivery.model.Doacao;
    import com.delivery.trabalhoDelivery.repositories.DoacaoRepositorio;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("/doacao")
    @CrossOrigin(origins = "http://localhost:3000")  // Permite requisições apenas do front-end no localhost:3000
    public class DoacaoController {

        @Autowired
        DoacaoRepositorio repositorio;

        @PostMapping("/cadastrar")
        public Doacao cadastrar(@RequestBody Doacao doacao) {
            return this.repositorio.save(doacao);
        }

        @GetMapping("/{id}")
        public Doacao obterDoacao(@PathVariable("id") Long id) {
            Optional<Doacao> doacao = repositorio.findById(id);
            return doacao.orElse(null); // Retorna null se a doação não for encontrada
        }

        @DeleteMapping("/deletar/{id}")
        public List<Doacao> deletar(@PathVariable("id") Long id) {
            Optional<Doacao> doacao = repositorio.findById(id);
            if (doacao.isPresent()) {
                repositorio.delete(doacao.get());
            }
            return listar();
        }

        @PostMapping("/editar")
        public List<Doacao> editar(@RequestBody Doacao doacao) {
            this.repositorio.save(doacao);
            return listar();
        }

        @GetMapping("/listar")
        public List<Doacao> listar() {
            List<Doacao> doacoes = new ArrayList<>();
            doacoes.addAll(repositorio.findAll());
            return doacoes;
        }
    }
