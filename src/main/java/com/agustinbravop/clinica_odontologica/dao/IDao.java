package com.agustinbravop.clinica_odontologica.dao;

import java.util.Collection;
import java.util.Optional;

public interface IDao<T> {
    T guardar(T t);

    T actualizar(T t);

    Optional<T> buscar(Integer id);

    Collection<T> buscarTodos();

    void eliminar(Integer id);
}
