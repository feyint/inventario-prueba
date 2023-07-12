/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.prueba.inventario.repository;

import com.prueba.inventario.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author feyin
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
