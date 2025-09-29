package daos.interfaces;

import dtos.ProductoDTO;
import entity.Producto;

public interface ProductoDAO extends DAO<Producto, Integer> {
    ProductoDTO productoQueMasRecaudo() throws Exception;
}
