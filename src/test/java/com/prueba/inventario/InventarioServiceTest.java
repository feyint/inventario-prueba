/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.prueba.inventario;

import com.prueba.inventario.entity.Producto;
import com.prueba.inventario.repository.ProductoRepository;
import com.prueba.inventario.service.ProductoServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author feyin
 */
public class InventarioServiceTest {

    public InventarioServiceTest() {
    }

    @Autowired
    ProductoServiceImpl productoService;

    @Test
    public void testGetProductos() {
        ProductoRepository productoRepository = mock(ProductoRepository.class);

        List<Producto> productos = new ArrayList<>();
        Producto producto = new Producto();
        productos.add(producto);
        when(productoRepository.findAll()).thenReturn(productos);
        List<Producto> productosRetornados = productoService.getProdutos();
        assertEquals(productos, productosRetornados);
    }

    @Test
    public void testCrear() {
        ProductoRepository productoRepository = mock(ProductoRepository.class);
        Producto producto = new Producto();
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        Producto productoCreado = productoService.crear(producto);
        assertEquals(producto, productoCreado);
    }

    @Test
    public void testActualizar() {
        ProductoRepository productoRepository = mock(ProductoRepository.class);

        Producto productoExistente = new Producto();
        productoExistente.setId(1L);
        productoExistente.setNombre("Producto existente");
        Producto productoNuevo = new Producto();
        productoNuevo.setNombre("Producto nuevo");
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoExistente));
        when(productoRepository.save(any(Producto.class))).thenAnswer(i -> i.getArguments()[0]);
        Producto productoActualizado = productoService.actualizar(productoNuevo, 1L);
        assertEquals("Producto nuevo", productoActualizado.getNombre());
    }

    @Test
    public void testEliminar() {
        ProductoRepository productoRepository = mock(ProductoRepository.class);
        when(productoRepository.existsById(1L)).thenReturn(true);
        boolean resultado = productoService.eliminar(1L);
        verify(productoRepository, times(1)).deleteById(1L);
        assertTrue(resultado);
    }

    @Test
    public void testEliminarNoExistente() {
        ProductoRepository productoRepository = mock(ProductoRepository.class);
        when(productoRepository.existsById(1L)).thenReturn(false);
        boolean resultado = productoService.eliminar(1L);
        verify(productoRepository, times(0)).deleteById(1L);
        assertFalse(resultado);
    }
}
