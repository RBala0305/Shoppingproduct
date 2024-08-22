import React, { useState, useEffect } from 'react';
import { getProducts, deleteProduct } from './productService';

const ProductList = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        loadProducts();
    }, []);

    const loadProducts = async () => {
        const result = await getProducts();
        setProducts(result.data);
    };

    const handleDelete = async (id) => {
        await deleteProduct(id);
        loadProducts();  // Reload products after deletion
    };

    return (
        <div>
            <h2>Product List</h2>
            <ul>
                {products.map(product => (
                    <li key={product.id}>
                        {product.name} - {product.category} - ${product.price}
                        <button onClick={() => handleDelete(product.id)}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ProductList;