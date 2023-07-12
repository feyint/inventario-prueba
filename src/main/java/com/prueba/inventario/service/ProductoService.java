/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prueba.inventario.service;

import com.prueba.inventario.entity.Producto;
import java.util.List;

/**
 *
 * @author feyin
 */
public interface ProductoService {
    public List<Producto> getProdutos();

    public Producto crear(Producto producto);

    public Producto actualizar(Producto producto, Long id);

    public boolean eliminar(Long id);

    public double calcularDepreciacion(Long id);
}
