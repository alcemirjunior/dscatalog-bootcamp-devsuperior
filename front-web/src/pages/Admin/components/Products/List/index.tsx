import React from 'react';
import { useHistory } from 'react-router-dom';
import './styles.scss'

const List = () => {
    const history = useHistory();

    const handleCreate= () => {
        history.push('/admin/products/create')
    }


    return (
        <div className="btn btn-primary btn-lg" onClick={handleCreate}>
            ADICIONAR
        </div>
    );
}

export default List;