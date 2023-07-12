/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.prueba.inventario.service;

import com.prueba.inventario.entity.Producto;
import com.prueba.inventario.repository.ProductoRepository;
import java.time.Year;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author feyin
 */
@Service
public class ProductoServiceImpl implements ProductoService{
    
    @Autowired
    private ProductoRepository productoRepository;
    

    @Override
    public List<Producto> getProdutos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Producto productoNuevo, Long id) {
        return productoRepository.findById(id)
            .map(producto -> {
                producto.setNombre(productoNuevo.getNombre());
                producto.setDescripcion(productoNuevo.getDescripcion());
                producto.setFechaCompra(productoNuevo.getFechaCompra());
                producto.setValor(productoNuevo.getValor());
                return productoRepository.save(producto);
            })
            .orElseGet(() -> {
                productoNuevo.setId(id);
                return productoRepository.save(productoNuevo);
            });
    }

    @Override
    public boolean eliminar(Long id) {
        if (productoRepository.existsById(id)) {
        productoRepository.deleteById(id);
        return true;
    }
    return false;
    }

    @Override
    public double calcularDepreciacion(Long id) {
       return productoRepository.findById(id)
        .map(producto -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(producto.getFechaCompra());
            int anoCompra = calendar.get(Calendar.YEAR);
            
            int currentYear = Year.now().getValue();
            int anosDesdeCompra = currentYear - anoCompra;
            
            double depreciacion = producto.getValor();
            for (int i = 0; i < anosDesdeCompra; i++) {
                depreciacion *= 0.96;
            }
            return depreciacion;
        }).orElse(0.0);
           
    }
    
}
