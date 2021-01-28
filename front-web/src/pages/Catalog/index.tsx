import React, { useEffect, useState } from 'react';
import ProductCard from './components/ProductCard';
import './styles.scss';
import { Link } from 'react-router-dom';
import { makeRequest } from 'core/utils/request';
import { ProductResponse } from 'core/types/Product';
import ProductCardLouder from './components/Loaders/ProductCardLouder';

const Catalog = () => {
  
   //quando o componente iniciar, buscar a lista de produtos
   //quando a lista de produtos estiver disponível, popular um estado no componente e listar os produtos dinamicamente

   const [productResponse, setProductResponse] = useState<ProductResponse>();
   const [isLoading, setIsLoading] = useState(false);

   useEffect(() => {
      const params = {
         page: 0,
         linesPerPage: 12
      }

      setIsLoading(true);
      makeRequest({url: '/products', params})
      .then(response => setProductResponse(response.data))
      .finally(() => {
         setIsLoading(false);
      })
   }, []);
  
   return (
      <div className="catalog-container">
         <h1 className="catalog-title">
            Catálogo de produtos
         </h1>
         <div className="catalog-products">
            {isLoading ? <ProductCardLouder /> : (
               productResponse?.content.map(product => (
                  <Link to={`/products/${product.id}`} key={product.id}>
                     <ProductCard product={product}/>
                  </Link>
               ))
            )}
         </div>
      </div>
   )
};

export default Catalog;

      // limitações fetch (foi implementado antes do axios):
      // muito verboso
      // não tem suporte nativo para ler o progresso de upload de arquivos
      // não tem suporte nativo para enviar query strings