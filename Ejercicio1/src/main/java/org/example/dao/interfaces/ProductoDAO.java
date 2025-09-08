package org.example.dao.interfaces;

import org.example.entity.Producto;

import java.util.List;

public interface ProductoDAO extends DAO<Producto> {
 Producto productoQueMasRecaudo() throws Exception;
}
