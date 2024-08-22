import React, { useState } from 'react';
import { createProduct } from './productService';

const AddProduct = () => {
    const [product, setProduct] = useState({ name: '', category: '', price: '' });

    const handleSubmit = async (e) => {
        e.preventDefault();
        await createProduct(product);
        setProduct({ name: '', category: '', price: '' });
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" placeholder="Name" value={product.name} onChange={(e) => setProduct({ ...product, name: e.target.value })} />
            <input type="text" placeholder="Category" value={product.category} onChange={(e) => setProduct({ ...product, category: e.target.value })} />
            <input type="number" placeholder="Price" value={product.price} onChange={(e) => setProduct({ ...product, price: e.target.value })} />
            <button type="submit">Add Product</button>
        </form>
    );
};

export default AddProduct;