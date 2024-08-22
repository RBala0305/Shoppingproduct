import React, { useState, useEffect } from 'react';
import { getProductById, updateProduct } from './productService';

const EditProduct = ({ match }) => {
    const [product, setProduct] = useState({ name: '', category: '', price: '' });

    useEffect(() => {
        loadProduct();
    }, []);

    const loadProduct = async () => {
        const result = await getProductById(match.params.id);
        setProduct(result.data);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        await updateProduct(match.params.id, product);
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" placeholder="Name" value={product.name} onChange={(e) => setProduct({ ...product, name: e.target.value })} />
            <input type="text" placeholder="Category" value={product.category} onChange={(e) => setProduct({ ...product, category: e.target.value })} />
            <input type="number" placeholder="Price" value={product.price} onChange={(e) => setProduct({ ...product, price: e.target.value })} />
            <button type="submit">Update Product</button>
        </form>
    );
};

export default EditProduct;