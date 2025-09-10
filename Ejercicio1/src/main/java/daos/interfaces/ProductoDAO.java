package daos.interfaces;

import entity.Producto;

public interface ProductoDAO extends DAO<Producto, Integer> {
    Producto productoQueMasRecaudo() throws Exception;
}
