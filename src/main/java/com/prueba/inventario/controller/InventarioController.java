/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.prueba.inventario.controller;

import com.prueba.inventario.entity.Producto;
import com.prueba.inventario.service.ProductoService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author feyin
 */
@RestController
@RequestMapping("/api")
public class InventarioController {
    
    @Autowired
    private ProductoService productoService;
    
  
    @GetMapping("/lista")
    public List<Producto> lista() {
        return productoService.getProdutos();
    }
    
    
    @PostMapping
    public Producto crear(@RequestBody Producto producto){
        return productoService.crear(producto);
    }
    
    @PutMapping("/{id}")
    public Producto actualizar(@RequestBody Producto producto, @PathVariable Long id) {
        return productoService.actualizar(producto, id);
    }
   
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return productoService.eliminar(id) 
            ? ResponseEntity.ok().build()
            : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}/depreciacion")
    public ResponseEntity<Double> calcularDepreciacion(@PathVariable Long id) {
        double depreciacion = productoService.calcularDepreciacion(id);
        return ResponseEntity.ok(depreciacion);
    }
}
